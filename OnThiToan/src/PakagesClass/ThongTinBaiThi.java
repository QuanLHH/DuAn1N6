/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PakagesClass;

public class ThongTinBaiThi {
    private int ID_TTBaiThi,ID_CauHoi,ID_BaiThiCT;
    private String MaDe;
    private int DoKho;
    private String DapAnChon;

    public ThongTinBaiThi() {
    }

    public ThongTinBaiThi(int ID_TTBaiThi, int ID_CauHoi, int ID_BaiThiCT, String MaDe, int DoKho, String DapAnChon) {
        this.ID_TTBaiThi = ID_TTBaiThi;
        this.ID_CauHoi = ID_CauHoi;
        this.ID_BaiThiCT = ID_BaiThiCT;
        this.MaDe = MaDe;
        this.DoKho = DoKho;
        this.DapAnChon = DapAnChon;
    }

    public int getID_TTBaiThi() {
        return ID_TTBaiThi;
    }

    public void setID_TTBaiThi(int ID_TTBaiThi) {
        this.ID_TTBaiThi = ID_TTBaiThi;
    }

    public int getID_CauHoi() {
        return ID_CauHoi;
    }

    public void setID_CauHoi(int ID_CauHoi) {
        this.ID_CauHoi = ID_CauHoi;
    }

    public int getID_BaiThiCT() {
        return ID_BaiThiCT;
    }

    public void setID_BaiThiCT(int ID_BaiThiCT) {
        this.ID_BaiThiCT = ID_BaiThiCT;
    }

    public String getMaDe() {
        return MaDe;
    }

    public void setMaDe(String MaDe) {
        this.MaDe = MaDe;
    }

    public int getDoKho() {
        return DoKho;
    }

    public void setDoKho(int DoKho) {
        this.DoKho = DoKho;
    }

    public String getDapAnChon() {
        return DapAnChon;
    }

    public void setDapAnChon(String DapAnChon) {
        this.DapAnChon = DapAnChon;
    }
    
}
