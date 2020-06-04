package applogic;

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
        dataLogicProvider.connectToDB();
        List<Price> prices = dataLogicProvider.readPrice();
        dataLogicProvider.close();
        Assertions.assertEquals(2, prices.get(0).getId());
    }
}