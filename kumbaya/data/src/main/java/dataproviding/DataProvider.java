package dataproviding;


import com.azure.data.cosmos.*;
import com.azure.data.cosmos.sync.CosmosSyncClient;
import com.azure.data.cosmos.sync.CosmosSyncContainer;
import com.azure.data.cosmos.sync.CosmosSyncDatabase;
import datamodel.Fertilizer;
import datamodel.Price;
import datamodel.Range;
import datamodel.SoilMeasurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class DataProvider {

    //region Fields
    private final static ResourceBundle bundle = ResourceBundle.getBundle("databasekeys");
    private static CosmosSyncClient client;
    private static CosmosSyncDatabase database;
    private final static FeedOptions queryOptions = new FeedOptions();
    //endregion

    //region Connect + Close to DB
    public void close() {
        client.close();
    }

    public void connectToDB() {
        ConnectionPolicy defaultPolicy = ConnectionPolicy.defaultPolicy();
        client = new CosmosClientBuilder()
                .endpoint(bundle.getString("endpoint"))
                .key(bundle.getString("key"))
                .connectionPolicy(defaultPolicy)
                .consistencyLevel(ConsistencyLevel.EVENTUAL)
                .buildSyncClient();

        database = client.getDatabase(bundle.getString("databaseName"));

    }
    //endregion

    //region Read

    public SoilMeasurement readSoilMeasurement(String userid) {
        List < SoilMeasurement > measurements = new ArrayList < > ();
        queryOptions.populateQueryMetrics(true);
        CosmosSyncContainer container = database.getContainer(bundle.getString("soilmeasurement"));
        String query = "SELECT TOP 1 * FROM c WHERE c.userid=" + "'" + userid + "'" + " AND c.measureDate <= GetCurrentDateTime() ORDER BY c.measureDate DESC";
        Iterator < FeedResponse < CosmosItemProperties >> responseIterator = container.queryItems(query, queryOptions);
        responseIterator.forEachRemaining(response -> {
            response.results().forEach(item -> {
                SoilMeasurement sm = new SoilMeasurement(item.getString("id"), item.getString("userid"), item.getString("measureDate"), item.getDouble("nParam"), item.getDouble("pParam"), item.getDouble("kParam"), item.getDouble("pHParam"));
                measurements.add(sm);
            });
        });
        return measurements.get(0);
    }

    public List < Range > readRanges() {
        List < Range > ranges = new ArrayList < > ();
        queryOptions.populateQueryMetrics(true);
        CosmosSyncContainer container = database.getContainer(bundle.getString("range"));

        Iterator < FeedResponse < CosmosItemProperties >> responseIterator = container.queryItems("SELECT * FROM c", queryOptions);

        responseIterator.forEachRemaining(response -> {
            response.results().forEach(item -> {
                Range tmpRange = new Range(item.getInt("id"), item.getString("soiltype"), item.getDouble("min"), item.getDouble("max"));
                ranges.add(tmpRange);
            });
        });
        return ranges;
    }

    public Price readPrice() {
        List < Price > prices = new ArrayList < > ();
        queryOptions.populateQueryMetrics(true);
        CosmosSyncContainer container = database.getContainer(bundle.getString("price"));
        String query = "SELECT TOP 1 * FROM c WHERE c.updateDate <= GetCurrentDateTime() ORDER BY c.updateDate DESC";
        Iterator < FeedResponse < CosmosItemProperties >> responseIterator = container.queryItems(query, queryOptions);
        responseIterator.forEachRemaining(response -> {
            response.results().forEach(item -> {
                Price p = new Price(item.getInt("id"), item.getString("updateDate"), item.getDouble("sweetpotatoPrice"), item.getDouble("maizePrice"), item.getDouble("soybeanPrice"));
                prices.add(p);
            });
        });
        return prices.get(0);
    }


    public List < Fertilizer > readFertilizer() {
        List < Fertilizer > fertilizers = new ArrayList < > ();
        queryOptions.populateQueryMetrics(true);
        CosmosSyncContainer container = database.getContainer(bundle.getString("fertilizer"));
        String query = "SELECT * FROM c";
        Iterator < FeedResponse < CosmosItemProperties >> responseIterator = container.queryItems(query, queryOptions);
        responseIterator.forEachRemaining(response -> {
            response.results().forEach(item -> {
                Fertilizer f = new Fertilizer(item.getInt("id"), item.getString("croptype"), item.getString("name"));
                fertilizers.add(f);
            });
        });
        return fertilizers;

    }



    //endregion

    //region Update
    public void updateSoilType(SoilMeasurement soilMeasurement) throws Exception {
        queryOptions.populateQueryMetrics(true);
        CosmosSyncContainer container = database.getContainer(bundle.getString("soilmeasurement"));
        container.upsertItem(soilMeasurement);
    }
    //endregion

}