/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import PakagesClass.DapAn;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author taola
 */
public class DapAnDAO extends EduSysDAO<DapAn, Integer> {

    String INSERT = "INSERT INTO Dap_An(ID_CauHoi, DapAnDung,DapAnSai1,DapAnSai2,DapAnSai3)VALUES(?,?,?,?,?)";
    String UPDATE = "UPDATE Dap_An SET ID_CauHoi=?, DapAnDung=?,DapAnSai1=?,DapAnSai2=?,DapAnSai3=? WHERE ID_DapAn=?";
    String DELETE = "DELETE FROM Dap_An WHERE ID_DapAn = ?";
    
    @Override
    public void insert(DapAn da) {
        Helper.JdbcHelper.update(INSERT, da.getID_CauHoi(),da.getDapAnDung(),da.getDapAnSai1(),da.getDapAnSai2(),da.getDapAnSai3());
    }

    @Override
    public void update(DapAn entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<DapAn> selectALL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DapAn selectById(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ArrayList<DapAn> selectBySql(String sql, Object... args) {
        ArrayList<DapAn> list = new ArrayList<>();
        try{
            ResultSet rs = Helper.JdbcHelper.query(sql, args);
            while(rs.next()){
                DapAn da = new DapAn();
                da.setID_DapAn(rs.getInt(1));
                da.setID_CauHoi(rs.getInt(2));
                da.setDapAnDung(rs.getString(3));
                da.setDapAnSai1(rs.getString(4));
                da.setDapAnSai2(rs.getString(5));
                da.setDapAnSai3(rs.getString(6));
                da.setDapAnSai3(rs.getString(6));
                list.add(da);
            }
            rs.getStatement().getConnection().close();  
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

}
