/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import PakagesClass.NguoiDung;
import java.util.ArrayList;

/**
 *
 * @author taola
 */
public class NguoiDungDAO extends EduSysDAO<NguoiDung, Integer>{
    String INSERT = "INSERT INTO Nguoi_Dung (HoTen,GioiTinh,SDT,NgaySinh,Email) VALUES (?,?,?,?,?)";
    String UPDATE = "UPDATE Nguoi_Dung SET HoTen=?,GioiTinh=?,SDT=?,NgaySinh=?,Email=? WHERE ID_MaND=?";
    @Override
    public void insert(NguoiDung nd) {
        Helper.JdbcHelper.update(INSERT, nd.getHoTen(),nd.getGioiTinh(),nd.getSDT(),nd.getNgaySinh(),nd.getEmail());
    }

    @Override
    public void update(NguoiDung entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<NguoiDung> selectALL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NguoiDung selectById(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ArrayList<NguoiDung> selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
