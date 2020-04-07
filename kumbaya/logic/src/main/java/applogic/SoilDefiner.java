package applogic;

import datamodel.Range;
import datamodel.SoilMeasurement;
import dataproviding.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class SoilDefiner {

    private DataProvider dataProvider;
    private List<SoilMeasurement> measurementList = new ArrayList<>();
    private List<Range> rangeList = new ArrayList<>();


    public void defineSoil() throws Exception {

        dataProvider.connectToDB();
        // read in data layer and put into lists -> alternatively, should I do it all in data layer?
     //   measurementList.addAll(dataProvider.readItems();)
        // similar with rangeList
        dataProvider.close();

        // based on pH
        for( SoilMeasurement soilmeasurement : measurementList ){
            for (Range range : rangeList ){
                if (soilmeasurement.getpHParam() <= range.getpHUpperThreshold() && soilmeasurement.getpHParam() >= range.getpHLowerThreshold())
                    soilmeasurement.setSoiltype(range.getSoiltype());
            }
        }
    }


}
