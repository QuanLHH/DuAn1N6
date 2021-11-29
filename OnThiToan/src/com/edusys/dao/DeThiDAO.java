/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import PakagesClass.DeThi;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author taola
 */
public class DeThiDAO extends EduSysDAO<DeThi, Integer>{

    @Override
    public void insert(DeThi entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(DeThi entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<DeThi> selectALL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DeThi selectById(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ArrayList<DeThi> selectBySql(String sql, Object... args) {
        ArrayList<DeThi> list = new ArrayList<>();
        try {
            ResultSet rs = Helper.JdbcHelper.query(sql, args);
            while (rs.next()) {
                DeThi dt = new DeThi();
                dt.setID_CauHoi(rs.getInt(1));
                dt.setRole_ID(rs.getBoolean(2));
                dt.setCauHoi(rs.getString(3));
                dt.setDapAn1(rs.getString(4));
                dt.setDapAn2(rs.getString(5));
                dt.setDapAn3(rs.getString(6));
                dt.setDapAn4(rs.getString(7));
                dt.setDapAnDung(rs.getString(8));
                list.add(dt);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return list;
    }
     public ArrayList<DeThi> selectDeThi(String made,int dokho) {
         String sql = "SELECT Bai_Thi.ID_CauHoi, Role_ID,CauHoi,DapAn1,DapAn2,DapAn3,DapAn4,DapAnDung FROM Cau_Hoi \n"
                    + "join Bai_Thi on Cau_Hoi.ID_CauHoi=Bai_Thi.ID_CauHoi WHERE MaDe=? AND Bai_Thi.DoKho=?";
        ArrayList<DeThi> list =selectBySql(sql,made,dokho);
        
        return list;
    }
}
