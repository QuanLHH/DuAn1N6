
package PakagesClass;

public class TaiKhoan {
    private String TenTaiKhoan,MatKhau,MKCap2;
    private boolean VaiTro;
    private int ID_MaND;

    public TaiKhoan() {
        
    }

    public TaiKhoan(String TenTaiKhoan, String MatKhau, String MKCap2, boolean VaiTro, int ID_MaND) {
        this.TenTaiKhoan = TenTaiKhoan;
        this.MatKhau = MatKhau;
        this.MKCap2 = MKCap2;
        this.VaiTro = VaiTro;
        this.ID_MaND = ID_MaND;
    }

    public String getTenTaiKhoan() {
        return TenTaiKhoan;
    }

    public void setTenTaiKhoan(String TenTaiKhoan) {
        this.TenTaiKhoan = TenTaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getMKCap2() {
        return MKCap2;
    }

    public void setMKCap2(String MKCap2) {
        this.MKCap2 = MKCap2;
    }

    public boolean getVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(boolean VaiTro) {
        this.VaiTro = VaiTro;
    }

    public int getID_MaND() {
        return ID_MaND;
    }

    public void setID_MaND(int ID_MaND) {
        this.ID_MaND = ID_MaND;
    }
    
}
