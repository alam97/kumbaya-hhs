package dataproviding;


import com.azure.cosmos.*;
import com.azure.cosmos.models.FeedOptions;
import com.azure.cosmos.models.SqlParameter;
import com.azure.cosmos.models.SqlParameterList;
import com.azure.cosmos.models.SqlQuerySpec;
import datamodel.Price;
import datamodel.Range;
import datamodel.SoilMeasurement;

public class DataProvider {

    //region Fields
    private final String EndpointUri = "https://malawi-sensing.documents.azure.com:443/";
    private final String AuthKey = "yBWJ4Nmp8e6WpfY7jFrgdGQ1DdbonOKebQ5M6M1bvpT44tKprKCTZqvbBW2GpNvbru2wKn3vYOxZBbFgBXw2ZQ==";
    private CosmosAsyncClient client;
    private final String databaseName = "malawi-sensing";
    private CosmosAsyncDatabase database;
    private FeedOptions queryOptions = new FeedOptions();
    //endregion

    //region Connect + Close to DB
    public void close(){ client.close(); }

    public void connectToDB() {
        ConnectionPolicy defaultPolicy = ConnectionPolicy.getDefaultPolicy();
        // defaultPolicy.setPreferredLocations(Lists.newArrayList("West US"));

        // maybe make it runnable?
            try {
                client = new CosmosClientBuilder()
                        .setEndpoint(EndpointUri)
                        .setKey(AuthKey)
                        .setConnectionPolicy(defaultPolicy)
                        .setConsistencyLevel(ConsistencyLevel.EVENTUAL)
                        .buildAsyncClient();
            }

            catch (CosmosClientException e) {
                e.printStackTrace();
            }

            database = client.getDatabase(databaseName);

    }

    //endregion

    //region C.R.U.D.

    //region READ

    // return data types needs to be decided

    public void readSoilMeasurement(String userid) {

        CosmosAsyncContainer container = database.getContainer("SoilMeasurement");
        queryOptions.setPopulateQueryMetrics(true);
        CosmosPagedFlux<SoilMeasurement> pagedFluxResponse = container.queryItems(new SqlQuerySpec("SELECT TOP 1 c.measureDate FROM c\n" +
                "WHERE c.userid=@userid" +
                "AND c.measureDate <= GetCurrentDateTime()\n" +
                "ORDER BY c.measureDate DESC\n",
                                                                            new SqlParameterList(new SqlParameter("@userid", userid))), queryOptions, SoilMeasurement.class);

        // get pages with items and put them into a collection<T> -> will probably use hashmap
    }

    // will return list of ranges containing ranges of 3 soiltypes
    public void readRanges() {
        queryOptions.setPopulateQueryMetrics(true);
        CosmosAsyncContainer container = database.getContainer("Range");
        CosmosPagedFlux<Range> pagedFluxResponse = container.readAllItems(Range.class);

        // get pages with items and put them into a collection<T> -> will probably use hashmap

    }

    //LAST Updated
    public void readPrice(){
        CosmosAsyncContainer container = database.getContainer("Price");
        // what's the difference between query and read?
        CosmosPagedFlux<Price> pagedFluxResponse = container.readAllItems(Price.class);

        // get pages with items and put them into a collection<T> -> will probably use hashmap

    }


    //endregion

    //region UPDATE

    // will take String value and userid
    public void updateSoilType(String userid, String soiltype){

        String query = "UPDATE SoilMeasurement SET soiltype=" + "'" + soiltype + "' WHERE userid=" + "'" + userid + "'";

    }
    //endregion

    //endregion

}
