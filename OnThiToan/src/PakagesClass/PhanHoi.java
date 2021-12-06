/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PakagesClass;

/**
 *
 * @author Administrator
 */
public class PhanHoi {
    private int ID_PhanHoi,ID_MaND;
    private String DanhGia,NhanXet;

    public PhanHoi() {
    }

    public PhanHoi(int ID_PhanHoi, int ID_MaND, String DanhGia, String NhanXet) {
        this.ID_PhanHoi = ID_PhanHoi;
        this.ID_MaND = ID_MaND;
        this.DanhGia = DanhGia;
        this.NhanXet = NhanXet;
    }

    public int getID_PhanHoi() {
        return ID_PhanHoi;
    }

    public void setID_PhanHoi(int ID_PhanHoi) {
        this.ID_PhanHoi = ID_PhanHoi;
    }

    public int getID_MaND() {
        return ID_MaND;
    }

    public void setID_MaND(int ID_MaND) {
        this.ID_MaND = ID_MaND;
    }

    public String getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(String DanhGia) {
        this.DanhGia = DanhGia;
    }

    public String getNhanXet() {
        return NhanXet;
    }

    public void setNhanXet(String NhanXet) {
        this.NhanXet = NhanXet;
    }
    
}
