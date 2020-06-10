package applogic;

import datamodel.Range;
import datamodel.SoilMeasurement;
import dataproviding.DataProvider;
import java.util.ArrayList;
import java.util.List;

public class SoilDefiner {

    private DataLogicProvider dataLogicProvider = new DataLogicProvider(new DataProvider());
    private final int pH_weight = 7;
    private final int npk_weight = 3;
    private List <Range> rangeList = new ArrayList <> ();
    private List <Range> possibleSolutions = new ArrayList <> ();

    public String defineSoil(String userid) throws Exception {

        dataLogicProvider.connectToDB();
        SoilMeasurement soilMeasurement = dataLogicProvider.readSoilMeasurement(userid);
        rangeList = dataLogicProvider.readRanges();

        double harmonic_mean = 3 / (1 / soilMeasurement.getkParam() + 1 / soilMeasurement.getnParam() + 1 / soilMeasurement.getpParam());
        double weighted_mean = (pH_weight * soilMeasurement.getpHParam() + npk_weight * harmonic_mean) / (pH_weight + npk_weight);

        for (Range range: rangeList) {
            if (weighted_mean >= range.getMin() && weighted_mean <= range.getMax()) {
                possibleSolutions.add(range);
            }
        }

        if (possibleSolutions.size() == 1) {
            soilMeasurement.setSoiltype(possibleSolutions.get(0).getSoiltype());
        } else if (possibleSolutions.size() > 1) {
            for (Range range: possibleSolutions) {
                if (range.getMin() == weighted_mean) {
                    soilMeasurement.setSoiltype(range.getSoiltype());
                }
            }

        } else throw new Exception();
        dataLogicProvider.updateSoilType(soilMeasurement);
        dataLogicProvider.close();
        return soilMeasurement.getSoiltype();
    }
}