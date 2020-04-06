package datamodel;

import java.util.Calendar;

public class SoilMeasurement {

    //region Fields
    private String id;
    private String userid;
    private Calendar measureDate;
    private String soiltype;
    private double nParam;
    private double pParam;
    private double kParam;
    private double pHParam;
    //endregion

    //region Constructors
    public SoilMeasurement(String id, String userid, Calendar measureDate, String soiltype, double nParam, double pParam, double kParam, double pHParam) {
        this.id = id;
        this.userid = userid;
        this.measureDate = measureDate;
        this.soiltype = soiltype;
        this.nParam = nParam;
        this.pParam = pParam;
        this.kParam = kParam;
        this.pHParam = pHParam;
    }
    //endregion

    //region Getters
    public String getId() {
        return id;
    }

    public String getUserid() {
        return userid;
    }

    public Calendar getMeasureDate() {
        return measureDate;
    }

    public String getSoiltype() {
        return soiltype;
    }

    public double getnParam() {
        return nParam;
    }

    public double getpParam() {
        return pParam;
    }

    public double getkParam() {
        return kParam;
    }

    public double getpHParam() {
        return pHParam;
    }
    //endregion

    //region Setters

    public void setId(String id) {
        this.id = id;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setMeasureDate(Calendar measureDate) {
        this.measureDate = measureDate;
    }

    public void setSoiltype(String soiltype) {
        this.soiltype = soiltype;
    }

    public void setnParam(double nParam) {
        this.nParam = nParam;
    }

    public void setpParam(double pParam) {
        this.pParam = pParam;
    }

    public void setkParam(double kParam) {
        this.kParam = kParam;
    }

    public void setpHParam(double pHParam) {
        this.pHParam = pHParam;
    }
    //endregion
}
