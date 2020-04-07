package dataproviding;


import com.azure.cosmos.*;
import com.azure.cosmos.models.*;
import com.google.common.collect.Lists;
import datamodel.SoilMeasurement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class DataProvider {

    private final String EndpointUri = "https://malawi-sensing.documents.azure.com:443/";
    private final String AuthKey = "yBWJ4Nmp8e6WpfY7jFrgdGQ1DdbonOKebQ5M6M1bvpT44tKprKCTZqvbBW2GpNvbru2wKn3vYOxZBbFgBXw2ZQ==";

    private CosmosAsyncClient client;
    private final String databaseName = "malawi-sensing";
    private final String containerName = "Range";

    private CosmosAsyncDatabase database;
    private CosmosAsyncContainer container;

    public void close(){ client.close(); }

    public void connectToDB() throws Exception {

        ConnectionPolicy defaultPolicy = ConnectionPolicy.getDefaultPolicy();
        defaultPolicy.setPreferredLocations(Lists.newArrayList("East US"));

        //  Create async client
        //  <CreateAsyncClient>
        client = new CosmosClientBuilder()
                .setEndpoint(EndpointUri)
                .setKey(AuthKey)
                .setConnectionPolicy(defaultPolicy)
                .setConsistencyLevel(ConsistencyLevel.EVENTUAL)
                .buildAsyncClient();

        //  </CreateAsyncClient>

        createDatabaseIfNotExists();
        createContainerIfNotExists();

    }

    private void createDatabaseIfNotExists() throws Exception {
     //   System.out.println("Create database " + databaseName + " if not exists.");

        //  Create database if not exists
        //  <CreateDatabaseIfNotExists>
        Mono<CosmosAsyncDatabaseResponse> databaseIfNotExists = client.createDatabaseIfNotExists(databaseName);
        databaseIfNotExists.flatMap(databaseResponse -> {
            database = databaseResponse.getDatabase();
            //System.out.println("Checking database " + database.getId() + " completed!\n");
            return Mono.empty();
        }).block();
        //  </CreateDatabaseIfNotExists>
    }

    private void createContainerIfNotExists() throws Exception {
      //  System.out.println("Create container " + containerName + " if not exists.");

        //  Create container if not exists
        //  <CreateContainerIfNotExists>

        CosmosContainerProperties containerProperties = new CosmosContainerProperties(containerName, "/lastName");
        Mono<CosmosAsyncContainerResponse> containerIfNotExists = database.createContainerIfNotExists(containerProperties, 400);

        //  Create container with 400 RU/s
        containerIfNotExists.flatMap(containerResponse -> {
            container = containerResponse.getContainer();
          //  System.out.println("Checking container " + container.getId() + " completed!\n");
            return Mono.empty();
        }).block();

        //  </CreateContainerIfNotExists>
    }

    public void readItems(Flux<SoilMeasurement> soilMeasurementToCreate) {
        //  Using partition key for point read scenarios.
        //  This will help fast look up of items because of partition key
        //  <ReadItem>

        final CountDownLatch completionLatch = new CountDownLatch(1);

        soilMeasurementToCreate.flatMap(soilMeasurement -> {
            Mono<CosmosAsyncItemResponse<SoilMeasurement>> asyncItemResponseMono = container.readItem(soilMeasurement.getId(), new PartitionKey(soilMeasurement.getUserid()), SoilMeasurement.class);
            return asyncItemResponseMono;
        })
                .subscribe(
                        itemResponse -> {
                            double requestCharge = itemResponse.getRequestCharge();
                            Duration requestLatency = itemResponse.getRequestLatency();
                            // System.out.println(String.format("Item successfully read with id %s with a charge of %.2f and within duration %s",
                            //        itemResponse.getItem().getId(), requestCharge, requestLatency));
                        },
                        err -> {
                            if (err instanceof CosmosClientException) {
                                //Client-specific errors
                                CosmosClientException cerr = (CosmosClientException)err;
                                cerr.printStackTrace();
                                System.err.println(String.format("Read Item failed with %s\n", cerr));
                            } else {
                                //General errors
                                err.printStackTrace();
                            }

                            completionLatch.countDown();
                        },
                        () -> {completionLatch.countDown();}
                );

        try {
            completionLatch.await();
        } catch (InterruptedException err) {
            throw new AssertionError("Unexpected Interruption",err);
        }

        //  </ReadItem>
    }

    public void queryItems() {
        //  <QueryItems>
        // Set some common query options

        FeedOptions queryOptions = new FeedOptions();
        //queryOptions.setEnableCrossPartitionQuery(true); //No longer needed in SDK v4
        //  Set populate query metrics to get metrics around query executions
        queryOptions.setPopulateQueryMetrics(true);

        CosmosPagedFlux<SoilMeasurement> pagedFluxResponse = container.queryItems(
                "SELECT * FROM Family WHERE Family.lastName IN ('Andersen', 'Wakefield', 'Johnson')", queryOptions, SoilMeasurement.class);

        final CountDownLatch completionLatch = new CountDownLatch(1);

        pagedFluxResponse.byPage(10).subscribe(
                fluxResponse -> {
                    System.out.println("Got a page of query result with " +
                            fluxResponse.getResults().size() + " items(s)"
                            + " and request charge of " + fluxResponse.getRequestCharge());

                    System.out.println("Item Ids " + fluxResponse
                            .getResults()
                            .stream()
                            .map(SoilMeasurement::getId)
                            .collect(Collectors.toList()));
                },
                err -> {
                    if (err instanceof CosmosClientException) {
                        //Client-specific errors
                        CosmosClientException cerr = (CosmosClientException)err;
                        cerr.printStackTrace();
                        System.err.println(String.format("Read Item failed with %s\n", cerr));
                    } else {
                        //General errors
                        err.printStackTrace();
                    }

                    completionLatch.countDown();
                },
                () -> {completionLatch.countDown();}
        );

        try {
            completionLatch.await();
        } catch (InterruptedException err) {
            throw new AssertionError("Unexpected Interruption",err);
        }

        // </QueryItems>
    }

}
