package applogic;

import datamodel.Fertilizer;
import datamodel.Price;
import dataproviding.DataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class SoilDefinerTest {

    @Test
    public void soilDefiningTest(){
        SoilDefiner soilDefiner = new SoilDefiner();
        String output = null;
        try {
           output = soilDefiner.defineSoil("user1");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        Assertions.assertEquals("Medium", output);
    }

    @Test
    void readPrices(){
        DataLogicProvider dataLogicProvider = new DataLogicProvider(new DataProvider());
        Price price = dataLogicProvider.readPrice();
        Assertions.assertEquals(2, price.getId());
    }

    @Test
    void readFertilizers(){
        DataLogicProvider dataLogicProvider = new DataLogicProvider(new DataProvider());
        dataLogicProvider.connectToDB();
        List<Fertilizer> fertilizers = dataLogicProvider.readFertilizer("Soybean");
        dataLogicProvider.close();
        Assertions.assertEquals("DAP", fertilizers.get(0).getName());
    }
}