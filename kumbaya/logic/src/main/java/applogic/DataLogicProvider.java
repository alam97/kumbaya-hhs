package applogic;

import datamodel.Fertilizer;
import datamodel.Price;
import datamodel.Range;
import datamodel.SoilMeasurement;
import dataproviding.DataProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataLogicProvider {

    //region Fields
    DataProvider dataProvider;
    //endregion

    //region Constructors
    //Dependency Injection Constructor
    public DataLogicProvider(DataProvider dataProvider) { this.dataProvider = dataProvider; }
    //Non-DI Constructor
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

    public HashMap<String, ArrayList<String>> readFertilizers(){
        connectToDB();
        List<Fertilizer> all = dataProvider.readFertilizer();
        close();
        ArrayList<String> sp = new ArrayList<>();
        ArrayList<String> mz = new ArrayList<>();
        ArrayList<String> sb = new ArrayList<>();
        HashMap<String, ArrayList<String>> fertilizerMap = new HashMap<>();
        for(Fertilizer f : all){
            if (f.getCroptype().equals("Sweet Potato")){
                sp.add(f.getName());
            }
            if (f.getCroptype().equals("Maize")){
                mz.add(f.getName());
            }
            if (f.getCroptype().equals("Soybean")){
                sb.add(f.getName());
            }
        }
        fertilizerMap.put("Sweet Potato", sp);
        fertilizerMap.put("Maize", mz);
        fertilizerMap.put("Soybean", sb);
        return fertilizerMap;
    }

    public ArrayList<String> readSPFertilizer(){ return readFertilizers().get("Sweet Potato"); }
    public ArrayList<String> readMZFertilizer(){ return readFertilizers().get("Maize"); }
    public ArrayList<String> readSBFertilizer(){ return readFertilizers().get("Soybean"); }


    //endregion

    //region Update
    public void updateSoilType(SoilMeasurement soilMeasurement) throws Exception{ dataProvider.updateSoilType(soilMeasurement);}
    //endregion
}
