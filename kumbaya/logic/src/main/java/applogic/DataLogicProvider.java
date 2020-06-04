package applogic;

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
    public DataLogicProvider(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }
    //endregion

    //region Connect + Close to DB
    public void connectToDB(){
        dataProvider.connectToDB();
    }
    public void close(){
        dataProvider.close();
    }
    //endregion

    //region Read
    public SoilMeasurement readSoilMeasurement(String userid){ return dataProvider.readSoilMeasurement(userid);}
    public List<Range> readRanges() { return dataProvider.readRanges();}
    public List<Price> readPrice(){ return dataProvider.readPrice();}
    //endregion

    //region Update
    public void updateSoilType(SoilMeasurement soilMeasurement) throws Exception{ dataProvider.updateSoilType(soilMeasurement);}
    //endregion
}
