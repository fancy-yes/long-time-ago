package com.hut.xj.SuperMarket.domain;

public class User {
    private String user_number;
    private String user_name;
    private String user_passwd;
    private String user_sex;
    private int user_age;
    private String user_birth;
    private String user_phone;
    private String user_address;
    private String user_type;

    public User() {
    }

    public User(String user_number, String user_name, String user_passwd, String user_sex, int user_age, String user_birth, String user_phone, String user_address, String user_type) {
        this.user_number = user_number;
        this.user_name = user_name;
        this.user_passwd = user_passwd;
        this.user_sex = user_sex;
        this.user_age = user_age;
        this.user_birth = user_birth;
        this.user_phone = user_phone;
        this.user_address = user_address;
        this.user_type = user_type;
    }

    public String getUser_passwd() {

        return user_passwd;
    }

    public void setUser_passwd(String user_passwd) {
        this.user_passwd = user_passwd;
    }

    public String getUser_birth() {
        return user_birth;
    }

    public void setUser_birth(String user_birth) {
        this.user_birth = user_birth;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }


    public String getUser_number() {
        return user_number;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}
