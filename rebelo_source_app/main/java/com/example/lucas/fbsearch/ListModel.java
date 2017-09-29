package com.example.lucas.fbsearch;

public class ListModel {

    private  String Name="";
    private  String Image="";
    private String id;
    private String type="";

    /*********** Set Methods ******************/

    public void setName(String Name)
    {
        this.Name = Name;
    }

    public void setImage(String Image)
    {
        this.Image = Image;
    }
    public void setId (String x){
        this.id = x;
    }
    public void setType (String x){
        this.type = x;
    }

    /*********** Get Methods ****************/

    public String getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public String getName()
    {
        return this.Name;
    }

    public String getImage()
    {
        return this.Image;
    }

}