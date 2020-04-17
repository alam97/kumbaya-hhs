package datamodel;

import java.util.Date;

public class Price {


    //region Fields
    private int id;
    private Date updateDate;
    private double sweetpotatoPrice;
    private double maizePrice;
    private double soybeanPrice;
    //endregion

    //region Contructors
    public Price(int id, Date updateDate, double sweetpotatoPrice, double maizePrice, double soybeanPrice) {
        this.id = id;
        this.updateDate = updateDate;
        this.sweetpotatoPrice = sweetpotatoPrice;
        this.maizePrice = maizePrice;
        this.soybeanPrice = soybeanPrice;
    }
    //endregion

    //region Getters
    public int getId() {
        return id;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public double getSweetpotatoPrice() {
        return sweetpotatoPrice;
    }

    public double getMaizePrice() {
        return maizePrice;
    }

    public double getSoybeanPrice() {
        return soybeanPrice;
    }
    //endregion

    //region Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setSweetpotatoPrice(double sweetpotatoPrice) {
        this.sweetpotatoPrice = sweetpotatoPrice;
    }

    public void setMaizePrice(double maizePrice) {
        this.maizePrice = maizePrice;
    }

    public void setSoybeanPrice(double soybeanPrice) {
        this.soybeanPrice = soybeanPrice;
    }
    //endregion

}
