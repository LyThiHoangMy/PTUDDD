package com.example.cvasport.model;

public class LoaiSanPham {
    int id;
    String tenloaisp;
    String hinhanh;

    public LoaiSanPham() {}

    public LoaiSanPham(int id, String tenloaisp, String hinhanh) {
        this.id = id;
        this.tenloaisp = tenloaisp;
        this.hinhanh = hinhanh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenloaisp() {
        return tenloaisp;
    }

    public void setTenloaisp(String tenloaisp) {
        this.tenloaisp = tenloaisp;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
}
