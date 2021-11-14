
package PakagesClass;

import java.util.Date;

public class NguoiDung {
    private int ID_MaND;
    private String hoTen,gioiTinh,SDT;
    private Date ngaySinh;
    private String Email;

    public NguoiDung() {
    }

    public NguoiDung(int ID_MaND, String hoTen, String gioiTinh, String SDT, Date ngaySinh, String Email) {
        this.ID_MaND = ID_MaND;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.SDT = SDT;
        this.ngaySinh = ngaySinh;
        this.Email = Email;
    }

    public int getID_MaND() {
        return ID_MaND;
    }

    public void setID_MaND(int ID_MaND) {
        this.ID_MaND = ID_MaND;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
}
