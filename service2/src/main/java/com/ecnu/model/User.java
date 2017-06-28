package com.ecnu.model;


import java.util.Collection;
import java.util.Date;

/**
 * Created by kun on 17-6-26.
 */
public class User {
    private int id;
    private String loginId;
    private String name;
    private String passwd;

    public User(int id,String loginId, String name, String passwd){
        this.id=id;
        this.loginId=loginId;
        this.name=name;
        this.passwd=passwd;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", name='" + name + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }

    public User(){

    }
}
