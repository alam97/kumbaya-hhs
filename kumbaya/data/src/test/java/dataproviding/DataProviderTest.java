package dataproviding;

import datamodel.Fertilizer;
import datamodel.Price;
import datamodel.Range;
import datamodel.SoilMeasurement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DataProviderTest {

    @Test
    void readSoilMeasurement() {
        DataProvider dataProvider = new DataProvider();
        dataProvider.connectToDB();
        SoilMeasurement sm = dataProvider.readSoilMeasurement("user1");
        dataProvider.close();
       // Assertions.assertEquals("2020-06-02", sm.getMeasureDate());
    }

    @Test
    void readRanges() {
        DataProvider dataProvider = new DataProvider();
        dataProvider.connectToDB();
        List<Range> ranges = dataProvider.readRanges();
        dataProvider.close();
        Assertions.assertEquals(3, ranges.size());
    }

    @Test
    void readPrices(){
        DataProvider dataProvider = new DataProvider();
        dataProvider.connectToDB();
        Price price = dataProvider.readPrice();
        dataProvider.close();
        Assertions.assertEquals(2, price.getId());
    }

    @Test
    void readFertilizer(){
        DataProvider dataProvider = new DataProvider();
        dataProvider.connectToDB();
        List<Fertilizer> fertilizers = dataProvider.readFertilizer();
        dataProvider.close();
        Assertions.assertEquals(7, fertilizers.size());
    }
}