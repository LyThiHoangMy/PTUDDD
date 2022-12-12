package com.example.cvasport.Model;

public class Pitch {
    private String image;
    private String typePitch;
    private String pricePitch;
    private boolean isReserved = true;

    public Pitch() {}

    public Pitch(String image, String typePitch, String pricePitch, boolean isReserved) {
        this.image = image;
        this.typePitch = typePitch;
        this.pricePitch = pricePitch;
        this.isReserved = isReserved;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTypePitch() {
        return typePitch;
    }

    public void setTypePitch(String typePitch) {
        this.typePitch = typePitch;
    }

    public String getPricePitch() {
        return pricePitch;
    }

    public void setPricePitch(String pricePitch) {
        this.pricePitch = pricePitch;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}
