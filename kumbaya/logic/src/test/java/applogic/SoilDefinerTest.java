package applogic;

import datamodel.Price;
import dataproviding.DataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

class SoilDefinerTest {

    @Test
    void soilDefiningTest(){
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
    void read(){
        DataLogicProvider dataLogicProvider = new DataLogicProvider(new DataProvider());
        Map<String, ArrayList<String>> map = dataLogicProvider.readFertilizers();

        Assertions.assertEquals(3, map.size());

        //Iterator check
        /*Iterator<Map.Entry<String, List<String>>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, List<String>> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }*/
    }

    @Test
    void readSP(){
        DataLogicProvider dataLogicProvider = new DataLogicProvider(new DataProvider());
        ArrayList<String> sp = dataLogicProvider.readSPFertilizer();
        Assertions.assertTrue(sp.contains("Urea"));
    }

    @Test
    void readSB(){
        DataLogicProvider dataLogicProvider = new DataLogicProvider(new DataProvider());
        ArrayList<String> sb = dataLogicProvider.readSBFertilizer();
        Assertions.assertTrue(sb.contains("Compound S"));
    }

    @Test
    void readMZ(){
        DataLogicProvider dataLogicProvider = new DataLogicProvider(new DataProvider());
        ArrayList<String> mz = dataLogicProvider.readMZFertilizer();
        Assertions.assertTrue(mz.contains("DAP"));
    }
}