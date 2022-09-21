package com.kenzie.appserver.service.model;

public class User {

    private String userId;
    private String name;
    private String dateJoined;

    public User(String userId, String name, String dateJoined) {
        this.userId = userId;
        this.name = name;
        this.dateJoined = dateJoined;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

}


