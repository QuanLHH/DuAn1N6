
package PakagesClass;

import java.util.Date;

public class CauHoi {
    private int ID_CauHoi, ID_BT;
    private String CauHoi;
    private int doKho;
    private Date ngayTao;

    public CauHoi() {
    }

    public CauHoi(int ID_CauHoi, int ID_BT, String CauHoi, int doKho, Date ngayTao) {
        this.ID_CauHoi = ID_CauHoi;
        this.ID_BT = ID_BT;
        this.CauHoi = CauHoi;
        this.doKho = doKho;
        this.ngayTao = ngayTao;
    }

    public int getID_CauHoi() {
        return ID_CauHoi;
    }

    public void setID_CauHoi(int ID_CauHoi) {
        this.ID_CauHoi = ID_CauHoi;
    }

    public int getID_BT() {
        return ID_BT;
    }

    public void setID_BT(int ID_BT) {
        this.ID_BT = ID_BT;
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
