
package PakagesClass;

import java.util.Date;

public class CauHoi {
    private int ID_CauHoi;
    private boolean Role_ID=true;
    private String CauHoi;
    private int doKho;
    private String tenBai;
    private String DapAn1,DapAn2,DapAn3,DapAn4,DapAnDung;
    private Date ngayTao;

    public CauHoi() {
    }

    public CauHoi(int ID_CauHoi, String CauHoi, int doKho, String tenBai, String DapAn1, String DapAn2, String DapAn3, String DapAn4, String DapAnDung, Date ngayTao) {
        this.ID_CauHoi = ID_CauHoi;
        this.CauHoi = CauHoi;
        this.doKho = doKho;
        this.tenBai = tenBai;
        this.DapAn1 = DapAn1;
        this.DapAn2 = DapAn2;
        this.DapAn3 = DapAn3;
        this.DapAn4 = DapAn4;
        this.DapAnDung = DapAnDung;
        this.ngayTao = ngayTao;
    }

    public int getID_CauHoi() {
        return ID_CauHoi;
    }

    public void setID_CauHoi(int ID_CauHoi) {
        this.ID_CauHoi = ID_CauHoi;
    }

    public boolean getRole_ID() {
        return Role_ID;
    }

    public void setRole_ID(boolean Role_ID) {
        this.Role_ID = Role_ID;
    }

    public String getCauHoi() {
        return CauHoi;
    }

    public void setCauHoi(String CauHoi) {
        this.CauHoi = CauHoi;
    }

    public int getDoKho() {
        return doKho;
    }

    public void setDoKho(int doKho) {
        this.doKho = doKho;
    }

    public String getTenBai() {
        return tenBai;
    }

    public void setTenBai(String tenBai) {
        this.tenBai = tenBai;
    }

    public String getDapAn1() {
        return DapAn1;
    }

    public void setDapAn1(String DapAn1) {
        this.DapAn1 = DapAn1;
    }

    public String getDapAn2() {
        return DapAn2;
    }

    public void setDapAn2(String DapAn2) {
        this.DapAn2 = DapAn2;
    }

    public String getDapAn3() {
        return DapAn3;
    }

    public void setDapAn3(String DapAn3) {
        this.DapAn3 = DapAn3;
    }

    public String getDapAn4() {
        return DapAn4;
    }

    public void setDapAn4(String DapAn4) {
        this.DapAn4 = DapAn4;
    }

    public String getDapAnDung() {
        return DapAnDung;
    }

    public void setDapAnDung(String DapAnDung) {
        this.DapAnDung = DapAnDung;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
    
}
