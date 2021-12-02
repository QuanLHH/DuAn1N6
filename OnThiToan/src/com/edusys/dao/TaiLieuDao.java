/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import PakagesClass.TaiLieu;
import Helper.JdbcHelper;
import static java.awt.Event.DELETE;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author DELL
 */
public class TaiLieuDao extends EduSysDAO<TaiLieu, Integer>{
    String INSERT = "INSERT INTO Tai_Lieu(TenTaiLieu,LyThuyet) VALUES (?,?)";
    String UPDATE = "UPDATE Tai_Lieu SET TenTaiLieu=?,LyThuyet=? where ID_MaTaiLieu like ?";
    String Delete = "delete  from Tai_Lieu where ID_MaTaiLieu like ?";
    String SELECT_BY_ID = "SELECT*FROM Tai_Lieu WHERE ID_MaTaiLieu =?";
    String SELECTALL = "SELECT * FROM Tai_Lieu";
    String SELECTBYID = "SELECT ID_MaTaiLieu,TenTaiLieu,LyThuyet FROM Tai_Lieu WHERE TenTaiLieu like ?";
    @Override
    public void insert(TaiLieu tl) {
        Helper.JdbcHelper.update(INSERT, tl.getTentl(),tl.getLythuyet());
    }

    @Override
    public void update(TaiLieu tl) {
        Helper.JdbcHelper.update(UPDATE,tl.getTentl(),tl.getLythuyet(),tl.getID_MaTaiLieu());
    }

    @Override
    public void delete(Integer key) {
      Helper.JdbcHelper.update(Delete, key);
    }

    @Override
    public ArrayList<TaiLieu> selectALL() {
         ArrayList<TaiLieu> list = selectBySql(SELECTALL);
        return list;
    }

    @Override
    public TaiLieu selectById(Integer key) {
         List<TaiLieu> list = selectBySql(SELECT_BY_ID, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected ArrayList<TaiLieu> selectBySql(String sql, Object... args) {
        ArrayList<TaiLieu> list = new ArrayList<>();
        try {
            ResultSet rs = Helper.JdbcHelper.query(sql, args);
            while (rs.next()) {                
                TaiLieu tl = new TaiLieu();
                tl.setID_MaTaiLieu(rs.getInt(1));
                tl.setTentl(rs.getString(2));
                tl.setLythuyet(rs.getString(3));
                list.add(tl);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return list;
    }
    public List<TaiLieu> timkiem(String tentailieu){
        return selectBySql(SELECTBYID, "%"+tentailieu +"%");
    }
}
