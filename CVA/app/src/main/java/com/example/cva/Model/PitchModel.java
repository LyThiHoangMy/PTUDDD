package com.example.cva.Model;

public class PitchModel {
    private String Name, Price, Date, Time;
    private boolean isReserved = true;

    public PitchModel() {}

    public PitchModel(String name, String price, String date, String time, boolean isReserved) {
        Name = name;
        Price = price;
        Date = date;
        Time = time;
        this.isReserved = isReserved;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}
