package datamodel;


public class Range {

    //region Fields
    private int id;
    private String soiltype;
    private double nUpperThreshold;
    private double pUpperThreshold;
    private double kUpperThreshold;
    private double pHUpperThreshold;
    private double nLowerThreshold;
    private double pLowerThreshold;
    private double kLowerThreshold;
    private double pHLowerThreshold;
    //endregion

    //region Constructors
    public Range(int id, String soiltype, double nUpperThreshold, double pUpperThreshold, double kUpperThreshold, double pHUpperThreshold, double nLowerThreshold, double pLowerThreshold, double kLowerThreshold, double pHLowerThreshold) {
        this.id = id;
        this.soiltype = soiltype;
        this.nUpperThreshold = nUpperThreshold;
        this.pUpperThreshold = pUpperThreshold;
        this.kUpperThreshold = kUpperThreshold;
        this.pHUpperThreshold = pHUpperThreshold;
        this.nLowerThreshold = nLowerThreshold;
        this.pLowerThreshold = pLowerThreshold;
        this.kLowerThreshold = kLowerThreshold;
        this.pHLowerThreshold = pHLowerThreshold;
    }
    //endregion

    //region Getters
    public int getId() {
        return id;
    }

    public String getSoiltype() {
        return soiltype;
    }

    public double getnUpperThreshold() {
        return nUpperThreshold;
    }

    public double getpUpperThreshold() {
        return pUpperThreshold;
    }

    public double getkUpperThreshold() {
        return kUpperThreshold;
    }

    public double getpHUpperThreshold() {
        return pHUpperThreshold;
    }

    public double getnLowerThreshold() {
        return nLowerThreshold;
    }

    public double getpLowerThreshold() {
        return pLowerThreshold;
    }

    public double getkLowerThreshold() {
        return kLowerThreshold;
    }

    public double getpHLowerThreshold() {
        return pHLowerThreshold;
    }
    //endregion

    //region Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setSoiltype(String soiltype) {
        this.soiltype = soiltype;
    }

    public void setnUpperThreshold(double nUpperThreshold) {
        this.nUpperThreshold = nUpperThreshold;
    }

    public void setpUpperThreshold(double pUpperThreshold) {
        this.pUpperThreshold = pUpperThreshold;
    }

    public void setkUpperThreshold(double kUpperThreshold) {
        this.kUpperThreshold = kUpperThreshold;
    }

    public void setpHUpperThreshold(double pHUpperThreshold) {
        this.pHUpperThreshold = pHUpperThreshold;
    }

    public void setnLowerThreshold(double nLowerThreshold) {
        this.nLowerThreshold = nLowerThreshold;
    }

    public void setpLowerThreshold(double pLowerThreshold) {
        this.pLowerThreshold = pLowerThreshold;
    }

    public void setkLowerThreshold(double kLowerThreshold) {
        this.kLowerThreshold = kLowerThreshold;
    }

    public void setpHLowerThreshold(double pHLowerThreshold) {
        this.pHLowerThreshold = pHLowerThreshold;
    }
    //endregion
}
