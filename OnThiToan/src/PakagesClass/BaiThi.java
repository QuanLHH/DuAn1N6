/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PakagesClass;

/**
 *
 * @author taola
 */
public class BaiThi {
     private int ID_BaiThi;
    private int MaDe;
    private int DoKho;
    private int ID_CauHoi;
    public BaiThi() {
        
    }

    public BaiThi(int ID_BaiThi, int MaDe, int DoKho, int ID_CauHoi) {
        this.ID_BaiThi = ID_BaiThi;
        this.MaDe = MaDe;
        this.DoKho = DoKho;
        this.ID_CauHoi = ID_CauHoi;
    }

    public int getID_CauHoi() {
        return ID_CauHoi;
    }

    public void setID_CauHoi(int ID_CauHoi) {
        this.ID_CauHoi = ID_CauHoi;
    }
    
    public int getID_BaiThi() {
        return ID_BaiThi;
    }

    public void setID_BaiThi(int ID_BaiThi) {
        this.ID_BaiThi = ID_BaiThi;
    }

    public int getMaDe() {
        return MaDe;
    }

    public void setMaDe(int MaDe) {
        this.MaDe = MaDe;
    }

    public int getDoKho() {
        return DoKho;
    }

    public void setDoKho(int DoKho) {
        this.DoKho = DoKho;
    }
    
}
