package applogic;

import datamodel.Fertilizer;
import datamodel.Price;
import datamodel.Range;
import datamodel.SoilMeasurement;
import dataproviding.DataProvider;

import java.util.List;

public class DataLogicProvider {

    //region Fields
    DataProvider dataProvider;
    //endregion

    //region Constructors
    //Dependency Injection Constructor
    public DataLogicProvider(DataProvider dataProvider) { this.dataProvider = dataProvider; }
    public DataLogicProvider() { this.dataProvider = new DataProvider();}
    //endregion

    //region Connect + Close to DB
    public void connectToDB(){
        dataProvider.connectToDB();
    }
    public void close(){ dataProvider.close(); }
    //endregion

    //region Read
    public SoilMeasurement readSoilMeasurement(String userid){ return dataProvider.readSoilMeasurement(userid);}
    public List<Range> readRanges() { return dataProvider.readRanges();}
    public Price readPrice(){
        connectToDB();
        Price price = dataProvider.readPrice();
        close();
        return price;
    }
    public Double readPriceSweetPotato() { return readPrice().getSweetpotatoPrice();}
    public Double readPriceMaize() { return readPrice().getMaizePrice();}
    public Double readPriceSoybean() { return readPrice().getSoybeanPrice();}
    public List<Fertilizer> readFertilizer(String croptype) {
        connectToDB();
        List<Fertilizer> fertilizers = dataProvider.readFertilizer(croptype);
        close();
        return fertilizers;
    }
    //endregion

    //region Update
    public void updateSoilType(SoilMeasurement soilMeasurement) throws Exception{ dataProvider.updateSoilType(soilMeasurement);}
    //endregion
}
