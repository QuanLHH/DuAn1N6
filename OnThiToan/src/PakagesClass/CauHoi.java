
package PakagesClass;

import java.util.Date;

public class CauHoi {
    private int ID_CauHoi;
    private boolean Role_ID=true;
    private String CauHoi;
    private int doKho;
    private String tenBai;
    private String DapAn,DapAnSai1,DapAnSai2,DapAnSai3;
    private Date ngayTao;

    public CauHoi() {
    }

    public CauHoi(int ID_CauHoi, String CauHoi, int doKho, String tenBai, String DapAn, String DapAnSai1, String DapAnSai2, String DapAnSai3, Date ngayTao) {
        this.ID_CauHoi = ID_CauHoi;
        this.CauHoi = CauHoi;
        this.doKho = doKho;
        this.tenBai = tenBai;
        this.DapAn = DapAn;
        this.DapAnSai1 = DapAnSai1;
        this.DapAnSai2 = DapAnSai2;
        this.DapAnSai3 = DapAnSai3;
        this.ngayTao = ngayTao;
    }

    public boolean getRole_ID() {
        return Role_ID;
    }

    public void setRole_ID(boolean Role_ID) {
        this.Role_ID = Role_ID;
    }

    public String getDapAn() {
        return DapAn;
    }

    public void setDapAn(String DapAn) {
        this.DapAn = DapAn;
    }

    public String getDapAnSai1() {
        return DapAnSai1;
    }

    public void setDapAnSai1(String DapAnSai1) {
        this.DapAnSai1 = DapAnSai1;
    }

    public String getDapAnSai2() {
        return DapAnSai2;
    }

    public void setDapAnSai2(String DapAnSai2) {
        this.DapAnSai2 = DapAnSai2;
    }

    public String getDapAnSai3() {
        return DapAnSai3;
    }

    public void setDapAnSai3(String DapAnSai3) {
        this.DapAnSai3 = DapAnSai3;
    }

    public String getTenBai() {
        return tenBai;
    }

    public void setTenBai(String tenBai) {
        this.tenBai = tenBai;
    }

    public int getID_CauHoi() {
        return ID_CauHoi;
    }

    public void setID_CauHoi(int ID_CauHoi) {
        this.ID_CauHoi = ID_CauHoi;
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

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
    
}
