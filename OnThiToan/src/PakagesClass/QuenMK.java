
package PakagesClass;


public class QuenMK {
    private String maND,hoten,matkhau,email;
    private boolean vaitro;

    public String getMaND() {
        return maND;
    }

    public QuenMK(String maND, String hoten, String matkhau, String email, boolean vaitro) {
        this.maND = maND;
        this.hoten = hoten;
        this.matkhau = matkhau;
        this.email = email;
        this.vaitro = vaitro;
    }

    public QuenMK() {
    }

    public void setMaND(String maND) {
        this.maND = maND;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVaitro() {
        return vaitro;
    }

    public void setVaitro(boolean vaitro) {
        this.vaitro = vaitro;
    }
}
