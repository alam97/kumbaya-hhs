package datamodel;


public class Range {

    //region Fields
    private int id;
    private String soiltype;
    private double min;
    private double max;
    //endregion

    //region Constructors
    public Range(int id, String soiltype, double min, double max) {
        this.id = id;
        this.soiltype = soiltype;
        this.min = min;
        this.max = max;
    }
    //endregion

    //region Getters
    public int getId() {
        return id;
    }

    public String getSoiltype() {
        return soiltype;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
    //endregion

    //region Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setSoiltype(String soiltype) {
        this.soiltype = soiltype;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public void setMax(double max) {
        this.max = max;
    }
    //endregion
}
