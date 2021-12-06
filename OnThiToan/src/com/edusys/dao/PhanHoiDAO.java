/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import PakagesClass.PhanHoi;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class PhanHoiDAO extends  EduSysDAO<PhanHoi, Integer>{
    String INSERT = "INSERT INTO Phan_Hoi(HoTen, PhanHoi, DanhGia)VALUE(?,?,?)";
    @Override
    public void insert(PhanHoi ph) {
        Helper.JdbcHelper.update(INSERT, ph.getID_MaND(),ph.getDanhGia(),ph.getNhanXet());
    }

    @Override
    public void update(PhanHoi entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<PhanHoi> selectALL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PhanHoi selectById(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ArrayList<PhanHoi> selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
