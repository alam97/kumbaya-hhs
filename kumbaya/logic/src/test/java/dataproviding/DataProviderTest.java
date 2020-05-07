package dataproviding;

import org.junit.jupiter.api.Test;

class DataProviderTest {

    @Test
    public void readTest(){
        DataProvider dataProvider = new DataProvider();
        System.out.println(" =============== Start test =============== ");
        System.out.println(" ===============  Tryna Connect");
        dataProvider.connectToDB();
        System.out.println(" ===============  Tryna Close");
        dataProvider.close();
    }

}