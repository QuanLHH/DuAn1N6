
package PakagesClass;

public class BaiThiChiTiet {
    private int ID_MaND,ID_BaiThi,SoCauDung,SoCauSai;
    private float Diem;

    public BaiThiChiTiet() {
    }

    public BaiThiChiTiet(int ID_MaND, int ID_BaiThi, int SoCauDung, int SoCauSai, float Diem) {
        this.ID_MaND = ID_MaND;
        this.ID_BaiThi = ID_BaiThi;
        this.SoCauDung = SoCauDung;
        this.SoCauSai = SoCauSai;
        this.Diem = Diem;
    }

    public int getID_MaND() {
        return ID_MaND;
    }

    public void setID_MaND(int ID_MaND) {
        this.ID_MaND = ID_MaND;
    }

    public int getID_BaiThi() {
        return ID_BaiThi;
    }

    public void setID_BaiThi(int ID_BaiThi) {
        this.ID_BaiThi = ID_BaiThi;
    }

    public int getSoCauDung() {
        return SoCauDung;
    }

    public void setSoCauDung(int SoCauDung) {
        this.SoCauDung = SoCauDung;
    }

    public int getSoCauSai() {
        return SoCauSai;
    }

    public void setSoCauSai(int SoCauSai) {
        this.SoCauSai = SoCauSai;
    }

    public float getDiem() {
        return Diem;
    }

    public void setDiem(float Diem) {
        this.Diem = Diem;
    }
    
}
