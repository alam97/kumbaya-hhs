package dataproviding;


import com.azure.data.cosmos.*;
import com.azure.data.cosmos.sync.CosmosSyncClient;
import com.azure.data.cosmos.sync.CosmosSyncContainer;
import com.azure.data.cosmos.sync.CosmosSyncDatabase;
import datamodel.Range;
import datamodel.SoilMeasurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProvider {

    //region Fields
    private final String EndpointUri = "https://malawi-sensing.documents.azure.com:443/";
    private final String AuthKey = "yBWJ4Nmp8e6WpfY7jFrgdGQ1DdbonOKebQ5M6M1bvpT44tKprKCTZqvbBW2GpNvbru2wKn3vYOxZBbFgBXw2ZQ==";
    private CosmosSyncClient client;
    private final String databaseName = "KumbayaSensing";
    private CosmosSyncDatabase database;
    private FeedOptions queryOptions = new FeedOptions();
    //endregion

    //region Connect + Close to DB
    public void close(){ client.close(); }

    public void connectToDB() {
        ConnectionPolicy defaultPolicy = ConnectionPolicy.defaultPolicy();
        client = new CosmosClientBuilder()
                .endpoint(EndpointUri)
                .key(AuthKey)
                .connectionPolicy(defaultPolicy)
                .consistencyLevel(ConsistencyLevel.EVENTUAL)
                .buildSyncClient();

        database = client.getDatabase(databaseName);

    }
    //endregion


    public SoilMeasurement readSoilMeasurement(String userid) {
        List<SoilMeasurement> measurements = new ArrayList<>();
        queryOptions.populateQueryMetrics(true);
        CosmosSyncContainer container = database.getContainer("SoilMeasurement");
        String query = "SELECT TOP 1 * FROM c WHERE c.userid=" + "'" + userid +"'" + " AND c.measureDate <= GetCurrentDateTime() ORDER BY c.measureDate DESC";
        Iterator<FeedResponse<CosmosItemProperties>> responseIterator = container.queryItems(query, queryOptions);
        responseIterator.forEachRemaining( response -> {
            response.results().forEach( item -> {
                SoilMeasurement sm = new SoilMeasurement(item.getString("id"), item.getString("userid"), item.getString("measureDate"), item.getDouble("nParam"), item.getDouble("pParam"), item.getDouble("kParam"), item.getDouble("pHParam"));
                measurements.add(sm);
            });
        });
        return measurements.get(0);
    }

    public List<Range> readRanges() {
        List<Range> ranges = new ArrayList<>();
        queryOptions.populateQueryMetrics(true);
        CosmosSyncContainer container = database.getContainer("Range");

        Iterator<FeedResponse<CosmosItemProperties>> responseIterator = container.queryItems("SELECT * FROM c", queryOptions);

        responseIterator.forEachRemaining(response -> {
            response.results().forEach(item -> {
                Range tmpRange = new Range(item.getInt("id"), item.getString("soiltype"), item.getDouble("min"), item.getDouble("max"));
                ranges.add(tmpRange);
            });
        });
        return ranges;
    }

    public void readPrice(){

        /* To be Implemented in the future */
    }


    //endregion

    //region UPDATE
    public void updateSoilType(SoilMeasurement soilMeasurement) throws Exception{
        queryOptions.populateQueryMetrics(true);
        CosmosSyncContainer container = database.getContainer("SoilMeasurement");
        container.upsertItem(soilMeasurement);
    }
    //endregion

    //endregion


}
