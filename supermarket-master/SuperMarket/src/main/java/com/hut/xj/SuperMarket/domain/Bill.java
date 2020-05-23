package com.hut.xj.SuperMarket.domain;

import java.util.Date;

public class Bill {
    private String bill_id;
    private String bill_name;
    private String bill_com;
    private String bill_supplier;
    private String bill_num;
    private String bill_money;
    private String bill_pay;
    private String bill_time;

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getBill_name() {
        return bill_name;
    }

    public void setBill_name(String bill_name) {
        this.bill_name = bill_name;
    }

    public String getBill_com() {
        return bill_com;
    }

    public void setBill_com(String bill_com) {
        this.bill_com = bill_com;
    }

    public String getBill_supplier() {
        return bill_supplier;
    }

    public void setBill_supplier(String bill_supplier) {
        this.bill_supplier = bill_supplier;
    }

    public String getBill_num() {
        return bill_num;
    }

    public void setBill_num(String bill_num) {
        this.bill_num = bill_num;
    }

    public String getBill_money() {
        return bill_money;
    }

    public void setBill_money(String bill_money) {
        this.bill_money = bill_money;
    }

    public String getBill_pay() {
        return bill_pay;
    }

    public void setBill_pay(String bill_pay) {
        this.bill_pay = bill_pay;
    }

    public String getBill_time() {
        return bill_time;
    }

    public void setBill_time(String bill_time) {
        this.bill_time = bill_time;
    }
}
