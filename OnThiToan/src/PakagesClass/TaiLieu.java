/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PakagesClass;

/**
 *
 * @author taola
 */
public class TaiLieu {
    private String tentl,linkvideo,lythuyet;
    private int ID_MaTaiLieu;

    public TaiLieu() {
    }

    public TaiLieu(String tentl, String linkvideo, String lythuyet, int ID_MaTaiLieu) {
        this.tentl = tentl;
        this.linkvideo = linkvideo;
        this.lythuyet = lythuyet;
        this.ID_MaTaiLieu = ID_MaTaiLieu;
    }

    public String getTentl() {
        return tentl;
    }

    public void setTentl(String tentl) {
        this.tentl = tentl;
    }

    public String getLinkvideo() {
        return linkvideo;
    }

    public void setLinkvideo(String linkvideo) {
        this.linkvideo = linkvideo;
    }

    public String getLythuyet() {
        return lythuyet;
    }

    public void setLythuyet(String lythuyet) {
        this.lythuyet = lythuyet;
    }

    public int getID_MaTaiLieu() {
        return ID_MaTaiLieu;
    }

    public void setID_MaTaiLieu(int ID_MaTaiLieu) {
        this.ID_MaTaiLieu = ID_MaTaiLieu;
    }

    @Override
    public String toString() {
        return "TaiLieu{" + "tentl=" + tentl + ", linkvideo=" + linkvideo + ", lythuyet=" + lythuyet + ", ID_MaTaiLieu=" + ID_MaTaiLieu + '}';
    }
    
    
}
