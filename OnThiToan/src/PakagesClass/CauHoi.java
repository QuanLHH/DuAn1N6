
package PakagesClass;

import java.util.Date;

public class CauHoi {
    private int ID_CauHoi;
    private boolean Role_ID;
    private String CauHoi;
    private int doKho;
    private String tenBai;
    private String DapAn;
    private Date ngayTao;

    public CauHoi() {
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

    public String getTenBai() {
        return tenBai;
    }

    public void setTenBai(String tenBai) {
        this.tenBai = tenBai;
    }

    public CauHoi(int ID_CauHoi, boolean Role_ID, String CauHoi, int doKho, String tenBai, String DapAn, Date ngayTao) {
        this.ID_CauHoi = ID_CauHoi;
        this.Role_ID = Role_ID;
        this.CauHoi = CauHoi;
        this.doKho = doKho;
        this.tenBai = tenBai;
        this.DapAn = DapAn;
        this.ngayTao = ngayTao;
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
