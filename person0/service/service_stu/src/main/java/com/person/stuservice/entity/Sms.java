package com.person.stuservice.entity;

public class Sms {
    String phonenumber;
    String code;
    Integer min;
    String name;
    String username;
    String password;
    Integer age;
    String sex;
    String address;
    String permission;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }
}
