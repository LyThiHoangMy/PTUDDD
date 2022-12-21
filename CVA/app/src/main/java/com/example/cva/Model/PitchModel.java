package com.example.cva.Model;

import java.io.Serializable;

public class PitchModel implements Serializable {
    private String title, pic, des, start, fee;

    public PitchModel() {}

    public PitchModel(String title, String pic, String des, String start, String fee) {
        this.title = title;
        this.pic = pic;
        this.des = des;
        this.start = start;
        this.fee = fee;
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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
