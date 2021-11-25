/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import PakagesClass.BaiThi;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author taola
 */
public class BaiThiDAO extends EduSysDAO<BaiThi, Integer> {

    String INSERT = "INSERT INTO Bai_Thi(MaDe,DoKho,ID_CauHoi)VALUES(?,?,?)";
    String UPDATE = "UPDATE Bai_Thi SET MaDe=?,DoKho=?,ID_CauHoi=? WHERE ID_BaiThi=?";
    String DELETE = "DELETE FROM Bai_Thi WHERE ID_BaiThi=?";
    String SELECTALL = "SELECT * FROM Bai_Thi";
    String SELECTMUCDOCAU = "SELECT Cau_Hoi.ID_CauHoi,Cau_Hoi.DoKho FROM Cau_Hoi inner join Bai_Thi  on Cau_Hoi.ID_CauHoi = Bai_Thi.ID_CauHoi";

    @Override
    public void insert(BaiThi bt) {
        Helper.JdbcHelper.update(INSERT, bt.getMaDe(), bt.getDoKho(), bt.getID_CauHoi());
    }

    @Override
    public void update(BaiThi bt) {
        Helper.JdbcHelper.update(UPDATE, bt.getMaDe(), bt.getDoKho(), bt.getID_CauHoi(), bt.getID_BaiThi());
    }

    @Override
    public void delete(Integer key) {
        Helper.JdbcHelper.update(DELETE, key);
    }

    @Override
    public ArrayList<BaiThi> selectALL() {
        ArrayList<BaiThi> list = selectBySql(SELECTALL);
        return list;
    }

    @Override
    public BaiThi selectById(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ArrayList<BaiThi> selectBySql(String sql, Object... args) {
        ArrayList<BaiThi> list = new ArrayList<>();
        try {
            ResultSet rs = Helper.JdbcHelper.query(sql, args);
            while (rs.next()) {
                BaiThi bt = new BaiThi();
                bt.setID_BaiThi(rs.getInt(1));
                bt.setDoKho(rs.getInt(2));
                bt.setMaDe(rs.getInt(3));
                bt.setID_CauHoi(rs.getInt(4));
                list.add(bt);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return list;
    }

    //lấy row ms nhất
    public BaiThi selectNew() {
        String SELECTNEW = "SELECT TOP 1 * FROM Bai_Thi ORDER BY ID_BaiThi DESC";
        ArrayList<BaiThi> list = selectBySql(SELECTNEW);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    

    // Lấy danh sách theo mã ID_BT
    public ArrayList<BaiThi> selectDoKho() {
        ArrayList<BaiThi> list = new ArrayList<>();
        try {
            int i = 0;
            String sql = "SELECT DoKho FROM Bai_Thi GROUP BY DoKho";
            ResultSet rs = Helper.JdbcHelper.query(sql);
            while (rs.next()) {
                BaiThi bt = new BaiThi();
                bt.setDoKho(rs.getInt(1));
                list.add(bt);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
