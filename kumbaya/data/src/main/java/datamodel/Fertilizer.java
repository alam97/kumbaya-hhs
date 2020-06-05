package datamodel;

public class Fertilizer {

    //region Fields
    private int id;
    private String croptype;
    private String name;
    //endregion

    //region Constructor
    public Fertilizer(int id, String croptype, String name) {
        this.id = id;
        this.croptype = croptype;
        this.name = name;
    }
    //endregion

    //region Getters

    public int getId() {
        return id;
    }

    public String getCroptype() {
        return croptype;
    }

    public String getName() {
        return name;
    }

    //endregion

    //region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setCroptype(String croptype) {
        this.croptype = croptype;
    }

    public void setName(String name) {
        this.name = name;
    }

    //endregion
}
