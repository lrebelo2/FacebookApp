package com.example.lucas.fbsearch;

/**
 * Created by lucas on 23-Apr-17.
 */

public class ListModelPost {
    private String message = "";
    private String created_time = "";
    private String pic = "";
    private String Name = "";
    private String id;

    /*********** Set Methods ******************/
    public void setName(String x) {
        this.Name = x;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }
    public void setId (String x){
        this.id = x;
    }

    /*********** Get Methods ****************/

    public String getId() {
        return id;
    }

    public String getPic() {
        return this.pic;
    }

    public String getName() {
        return this.Name;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCreated_time() {
        return this.created_time;
    }
}
