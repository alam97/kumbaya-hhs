package applogic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SoilDefinerTest {


    @Test
    private void soilDefiningTest(){
        SoilDefiner soilDefiner = new SoilDefiner();
        String output = null;
        try {
           output = soilDefiner.defineSoil("user1");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        Assertions.assertEquals("High", output);
    }
}