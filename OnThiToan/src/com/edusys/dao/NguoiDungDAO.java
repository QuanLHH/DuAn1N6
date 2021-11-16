/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import PakagesClass.NguoiDung;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author taola
 */
public class NguoiDungDAO extends EduSysDAO<NguoiDung, Integer>{
    String INSERT = "INSERT INTO Nguoi_Dung (HoTen,GioiTinh,SDT,NgaySinh,Email) VALUES (?,?,?,?,?)";
    String UPDATE = "UPDATE Nguoi_Dung SET HoTen=?,GioiTinh=?,SDT=?,NgaySinh=?,Email=? WHERE ID_MaND=?";
    String SelectMaxID = "SELECT * FROM Nguoi_Dung WHERE ID_MaND= (SELECT MAX(ID_MaND) FROM Nguoi_Dung )";
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
    public NguoiDung getMaxID(){
        ArrayList<NguoiDung> list = selectBySql(SelectMaxID);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    @Override
    protected ArrayList<NguoiDung> selectBySql(String sql, Object... args) {
        ArrayList<NguoiDung> list = new ArrayList<>();
        try{
            ResultSet rs = Helper.JdbcHelper.query(sql, args);
            while(rs.next()){
                NguoiDung nd = new NguoiDung();
                nd.setID_MaND(rs.getInt(1));
                nd.setHoTen(rs.getString(2));
                nd.setGioiTinh(rs.getString(3));
                nd.setSDT(rs.getString(4));
                nd.setNgaySinh(rs.getDate(5));
                nd.setEmail(rs.getString(6));
                list.add(nd);
            }
            rs.getStatement().getConnection().close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    
}
