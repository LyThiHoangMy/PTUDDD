package com.example.cva.Model;

public class notification {
    private String nameUser, time, date, namePitch;

    public notification(String nameUser, String time, String date, String namePitch) {
        this.nameUser = nameUser;
        this.time = time;
        this.date = date;
        this.namePitch = namePitch;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNamePitch() {
        return namePitch;
    }

    public void setNamePitch(String namePitch) {
        this.namePitch = namePitch;
    }
}
