package com.example.domain.entity;


public class UserEntity {

    private String userName;
    private int age;
    private String profileUrl;
    private String id;

    public UserEntity() {
    }

    public UserEntity(String userName, int age, String profileUrl, String id) {
        this.userName = userName;
        this.age = age;
        this.profileUrl = profileUrl;
        this.id = id;
    }

    public UserEntity(String userName, int age, String profileUrl) {
        this.userName = userName;
        this.age = age;
        this.profileUrl = profileUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;


    }
}
