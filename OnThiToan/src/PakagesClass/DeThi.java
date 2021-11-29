
package PakagesClass;

public class DeThi {
    private int ID_CauHoi;
    private boolean Role_ID;
    private String CauHoi,DapAn1,DapAn2,DapAn3,DapAn4,DapAnDung;

    public DeThi() {
    }

    public DeThi(int ID_CauHoi, boolean Role_ID, String CauHoi, String DapAn1, String DapAn2, String DapAn3, String DapAn4, String DapAnDung) {
        this.ID_CauHoi = ID_CauHoi;
        this.Role_ID = Role_ID;
        this.CauHoi = CauHoi;
        this.DapAn1 = DapAn1;
        this.DapAn2 = DapAn2;
        this.DapAn3 = DapAn3;
        this.DapAn4 = DapAn4;
        this.DapAnDung = DapAnDung;
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

    
}
