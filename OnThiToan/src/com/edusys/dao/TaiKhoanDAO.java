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
<<<<<<< Updated upstream
=======
    String SELECT_BY_ID = "SELECT*FROM Tai_Khoan WHERE TenTaiKhoan=?";
>>>>>>> Stashed changes

    @Override
    public void insert(TaiKhoan tk) {
        Helper.JdbcHelper.update(INSERT, tk.getTenTaiKhoan(), tk.getMatKhau(), tk.getMKCap2(), tk.getVaiTro(), tk.getID_MaND());

    }

    @Override
    public void update(TaiKhoan tk) {
        Helper.JdbcHelper.update(UPDATE, tk.getMatKhau(), tk.getMKCap2(), tk.getVaiTro(), tk.getID_MaND(), tk.getTenTaiKhoan());
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

<<<<<<< Updated upstream
=======
    public void doiMK(TaiKhoan nv) {
        String UPDATE = "UPDATE Tai_Khoan SET MatKhau =? WHERE TenTaiKhoan=?";
        try {
            JdbcHelper.update(UPDATE, nv.getMatKhau(), nv.getTenTaiKhoan());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

>>>>>>> Stashed changes
    @Override
    public ArrayList<TaiKhoan> selectALL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

<<<<<<< Updated upstream
=======
    public ArrayList<TaiKhoan> selectFrom(String sql, Object... args) {
        ArrayList<TaiKhoan> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.query(sql, args);
                while (rs.next()) {
                    list.add(readFromResultSet(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();      //đóng kết nối từ resultSet
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
        return list;
    }

>>>>>>> Stashed changes
    @Override
    public TaiKhoan selectById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ArrayList<TaiKhoan> selectBySql(String sql, Object... args) {
<<<<<<< Updated upstream
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

=======
        ArrayList<TaiKhoan> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
                tk.setMatKhau(rs.getString("MatKhau"));
                tk.setMKCap2(rs.getString("MKCap2"));
                tk.setVaiTro(rs.getBoolean("VaiTro"));
                tk.setID_MaND(rs.getInt("ID_MaND"));
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    private TaiKhoan readFromResultSet(ResultSet rs) throws SQLException {
        TaiKhoan model = new TaiKhoan();
        model.setID_MaND(rs.getInt("ID_MaND"));
        model.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
        model.setMatKhau(rs.getString("MatKhau"));
        model.setMKCap2(rs.getString("MKCap2"));
        model.setVaiTro(rs.getBoolean("VaiTro"));
        return model;
    }
>>>>>>> Stashed changes
}
