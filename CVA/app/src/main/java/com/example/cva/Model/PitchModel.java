package com.example.cva.Model;

import java.io.Serializable;

public class PitchModel implements Serializable {
    private String title, pic, des;
    private int start, time, numberInCart;
    private double fee;

    public PitchModel() {}

    public PitchModel(String title, String pic, String des, double fee, int start, int time) {
        this.title = title;
        this.pic = pic;
        this.des = des;
        this.fee = fee;
        this.start = start;
        this.time = time;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
