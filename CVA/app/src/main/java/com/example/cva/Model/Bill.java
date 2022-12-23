package com.example.cva.Model;

public class Bill {
    private String date, name, fee;

    public Bill() {}

    public Bill(String date, String name, String fee) {
        this.date = date;
        this.name = name;
        this.fee = fee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
