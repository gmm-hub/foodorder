package com.javaee.foodshop.entity;

import java.util.Date;

public class User {
    private String id;
    private String username;
    private String password;
    private String sex;
    private String realname;
    private String tel;
    private String address;
    private String email;
    private Integer state;
    private String code;
    private Date birthday;

    public User() {
    }

    public User(String id, String username, String password, String sex, String realname, String tel, String address, String email, Integer state, String code, Date birthday) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.realname = realname;
        this.tel = tel;
        this.address = address;
        this.email = email;
        this.state = state;
        this.code = code;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", realname='" + realname + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", state=" + state +
                ", code='" + code + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
