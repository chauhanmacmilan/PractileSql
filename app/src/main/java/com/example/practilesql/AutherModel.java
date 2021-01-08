package com.example.practilesql;

public class AutherModel {
    String id;
    String auther;

    public AutherModel(String id, String auther) {
        this.id = id;
        this.auther = auther;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }
}
