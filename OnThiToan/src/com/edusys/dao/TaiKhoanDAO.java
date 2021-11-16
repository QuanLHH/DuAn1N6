/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import PakagesClass.TaiKhoan;
import java.util.ArrayList;

public class TaiKhoanDAO extends EduSysDAO<TaiKhoan, String> {

    String INSERT = "INSERT INTO Tai_Khoan (TenTaiKhoan,MatKhau,MKCap2,VaiTro,ID_MaND) VALUES (?,?,?,?,?)";
    String UPDATE = "UPDATE Tai_Khoan SET MatKhau=?,MKCap2=?,VaiTro=?,ID_MaND=? WHERE TenTaiKhoan=?";

    @Override
    public void insert(TaiKhoan tk) {
        Helper.JdbcHelper.update(INSERT, tk.getTenTaiKhoan(),tk.getMatKhau(),tk.getMKCap2(),tk.getVaiTro(),tk.getID_MaND());

    }

    @Override
    public void update(TaiKhoan tk) {
        Helper.JdbcHelper.update(UPDATE,tk.getMatKhau(),tk.getMKCap2(),tk.getVaiTro(),tk.getID_MaND(),tk.getTenTaiKhoan());
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<TaiKhoan> selectALL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TaiKhoan selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ArrayList<TaiKhoan> selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
