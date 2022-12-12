package com.example.cvasport.Model;

public class Drinkable {
    private String imageDrink;
    private String nameDrink;
    private String priceDrink;

    public Drinkable() {}

    public Drinkable(String imageDrink, String nameDrink, String priceDrink) {
        this.imageDrink = imageDrink;
        this.nameDrink = nameDrink;
        this.priceDrink = priceDrink;
    }

    public String getImageDrink() {
        return imageDrink;
    }

    public void setImageDrink(String imageDrink) {
        this.imageDrink = imageDrink;
    }

    public String getNameDrink() {
        return nameDrink;
    }

    public void setNameDrink(String nameDrink) {
        this.nameDrink = nameDrink;
    }

    public String getPriceDrink() {
        return priceDrink;
    }

    public void setPriceDrink(String priceDrink) {
        this.priceDrink = priceDrink;
    }
}
