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
public class Sanpham {

    String masp, tensp, donvi, hangsp, vitri;
    double GiaNhap, GiaBan;
    int soluongnhapkho;

    public Sanpham() {
    }

    public Sanpham(String masp, String tensp, String donvi, String hangsp, String vitri, double GiaNhap, double GiaBan, int soluongnhapkho) {
        this.masp = masp;
        this.tensp = tensp;
        this.donvi = donvi;
        this.hangsp = hangsp;
        this.vitri = vitri;
        this.GiaNhap = GiaNhap;
        this.GiaBan = GiaBan;
        this.soluongnhapkho = soluongnhapkho;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public String getHangsp() {
        return hangsp;
    }

    public void setHangsp(String hangsp) {
        this.hangsp = hangsp;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    public double getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(double GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    public double getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(double GiaBan) {
        this.GiaBan = GiaBan;
    }

    public int getSoluongnhapkho() {
        return soluongnhapkho;
    }

    public void setSoluongnhapkho(int soluongnhapkho) {
        this.soluongnhapkho = soluongnhapkho;
    }

   
}
