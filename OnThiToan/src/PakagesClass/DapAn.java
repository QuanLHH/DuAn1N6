
package PakagesClass;

public class DapAn {
    private int ID_DapAn,ID_CauHoi;
    private String DapAnDung,DapAnSai1,DapAnSai2,DapAnSai3;

    public DapAn() {
    }

    public DapAn(int ID_DapAn, int ID_CauHoi, String DapAnDung, String DapAnSai1, String DapAnSai2, String DapAnSai3) {
        this.ID_DapAn = ID_DapAn;
        this.ID_CauHoi = ID_CauHoi;
        this.DapAnDung = DapAnDung;
        this.DapAnSai1 = DapAnSai1;
        this.DapAnSai2 = DapAnSai2;
        this.DapAnSai3 = DapAnSai3;
    }
    public int getID_DapAn() {
        return ID_DapAn;
    }

    public void setID_DapAn(int ID_DapAn) {
        this.ID_DapAn = ID_DapAn;
    }

    public int getID_CauHoi() {
        return ID_CauHoi;
    }

    public void setID_CauHoi(int ID_CauHoi) {
        this.ID_CauHoi = ID_CauHoi;
    }

    public String getDapAnDung() {
        return DapAnDung;
    }

    public void setDapAnDung(String DapAnDung) {
        this.DapAnDung = DapAnDung;
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
    
}
