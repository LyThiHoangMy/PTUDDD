package com.example.cva.Model;

public class PitchModelAd {
    private String des, fee, star, name;

    public PitchModelAd() {}

    public PitchModelAd(String des, String fee, String star, String name) {
        this.des = des;
        this.fee = fee;
        this.star = star;
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
