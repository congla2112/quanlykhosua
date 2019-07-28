/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author DELL
 */
public class Nhapkho {
    private String maphieu;
    private String hangcungcap;
    private String ngaynhap;
    private float tongtien;
    private String xuatxu;
    private String hansd;
    private String manv1;
    private String vitri;

    public Nhapkho(String maphieu, String hangcungcap, String ngaynhap, float tongtien, String xuatxu, String hansd, String manv1, String vitri) {
        this.maphieu = maphieu;
        this.hangcungcap = hangcungcap;
        this.ngaynhap = ngaynhap;
        this.tongtien = tongtien;
        this.xuatxu = xuatxu;
        this.hansd = hansd;
        this.manv1 = manv1;
        this.vitri = vitri;
    }

    public Nhapkho() {
    }

    public String getMaphieu() {
        return maphieu;
    }

    public void setMaphieu(String maphieu) {
        this.maphieu = maphieu;
    }

    public String getHangcungcap() {
        return hangcungcap;
    }

    public void setHangcungcap(String hangcungcap) {
        this.hangcungcap = hangcungcap;
    }

    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }

    public String getXuatxu() {
        return xuatxu;
    }

    public void setXuatxu(String xuatxu) {
        this.xuatxu = xuatxu;
    }

    public String getHansd() {
        return hansd;
    }

    public void setHansd(String hansd) {
        this.hansd = hansd;
    }

    public String getManv1() {
        return manv1;
    }

    public void setManv1(String manv1) {
        this.manv1 = manv1;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    
}
