/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import Helper.JdbcHelper;
import PakagesClass.CauHoi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taola
 */
public class CauHoiDAO extends EduSysDAO<CauHoi, Integer> {

    String INSERT = "INSERT INTO Cau_Hoi(Role_ID,CauHoi,DoKho,TenBai,DapAn1,DapAn2,DapAn3,DapAn4,DapAnDung)"
            + "VALUES (?,?,?,?,?,?,?,?,?)";
    String UPDATE = "UPDATE Cau_Hoi SET Role_ID=?,CauHoi=?,DoKho=?,TenBai=?,DapAn1=?,DapAn2=?,DapAn3=?,DapAn4=?,DapAnDung=?"
            + " WHERE ID_CauHoi=?";
    String DELETE = "DELETE FROM Cau_Hoi WHERE ID_CauHoi=?";
    String SELECTALL = "SELECT * FROM Cau_Hoi";
    String SELECTBYID = "SELECT * FROM Cau_Hoi WHERE ID_CauHoi=?";

    @Override
    public void insert(CauHoi ch) {
        Helper.JdbcHelper.update(INSERT, ch.getRole_ID(), ch.getCauHoi(), ch.getDoKho(), ch.getTenBai(),
                ch.getDapAn1(), ch.getDapAn2(), ch.getDapAn3(), ch.getDapAn4(), ch.getDapAnDung());
    }

    @Override
    public void update(CauHoi ch) {
        Helper.JdbcHelper.update(UPDATE, ch.getRole_ID(), ch.getCauHoi(), ch.getDoKho(), ch.getTenBai(),
                ch.getDapAn1(), ch.getDapAn2(), ch.getDapAn3(), ch.getDapAn4(), ch.getDapAnDung(), ch.getID_CauHoi());

    }

    @Override
    public void delete(Integer key) {
        Helper.JdbcHelper.update(DELETE, key);

    }

    @Override
    public ArrayList<CauHoi> selectALL() {
        ArrayList<CauHoi> list = selectBySql(SELECTALL);
        return list;
    }

    @Override
    public CauHoi selectById(Integer key) {
        ArrayList<CauHoi> list = selectBySql(SELECTBYID, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected ArrayList<CauHoi> selectBySql(String sql, Object... args) {
        ArrayList<CauHoi> list = new ArrayList<>();
        try {
            ResultSet rs = Helper.JdbcHelper.query(sql, args);
            while (rs.next()) {
                CauHoi ch = new CauHoi();
                ch.setID_CauHoi(rs.getInt(1));
                ch.setRole_ID(rs.getBoolean(2));
                ch.setCauHoi(rs.getString(3));
                ch.setDoKho(rs.getInt(4));
                ch.setTenBai(rs.getString(5));
                ch.setDapAn1(rs.getString(6));
                ch.setDapAn2(rs.getString(7));
                ch.setDapAn3(rs.getString(8));
                ch.setDapAn4(rs.getString(9));
                ch.setDapAnDung(rs.getString(10));
                ch.setNgayTao(rs.getDate(11));
                list.add(ch);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }
    //vietanh.

    // Lấy row mới nhất của table
    public CauHoi selectIDNew() {
        String SELECTIDNEW = "SELECT TOP 1 * FROM Cau_Hoi ORDER BY ID_CauHoi DESC ";
        ArrayList<CauHoi> list = selectBySql(SELECTIDNEW);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    // Lấy danh sách theo mã ID_BT// viet anh
    public ArrayList<CauHoi> selectDoKho() {
        ArrayList<CauHoi> list = new ArrayList<>();
        try {
            String sql = "SELECT DoKho FROM Cau_Hoi GROUP BY DoKho";
            ResultSet rs = Helper.JdbcHelper.query(sql);
            while (rs.next()) {
                CauHoi ch = new CauHoi();
                ch.setDoKho(rs.getInt(1));
                list.add(ch);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // viet anh
    public ArrayList<CauHoi> selectRole_ID() {
        ArrayList<CauHoi> list = new ArrayList<>();
        try {
            String sql = "SELECT Role_ID FROM Cau_Hoi GROUP BY Role_ID";
            ResultSet rs = Helper.JdbcHelper.query(sql);
            while (rs.next()) {
                CauHoi ch = new CauHoi();
                ch.setRole_ID(rs.getBoolean(1));
                list.add(ch);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // select với tên bài và thể loại
    public ArrayList<CauHoi> selectByTenBai(String chuoi) {
        String SelectByRole_ID = "SELECT*FROM Cau_Hoi WHERE TenBai LIKE ?";
        ArrayList<CauHoi> list = selectBySql(SelectByRole_ID,chuoi);
        return list;
    }
    // lấy danh sách với thể loại 
    public ArrayList<CauHoi> selectByTheLoai(int id) {
        String SelectByRole_ID = "SELECT*FROM Cau_Hoi WHERE Role_ID=?";
        ArrayList<CauHoi> list = selectBySql(SelectByRole_ID,id);
        return list;
    }
        //truong

    // dinh truong

    public ArrayList<CauHoi> selecttenbai() {
        ArrayList<CauHoi> list = new ArrayList<>();
        try {
            String sql = "SELECT TenBai FROM Cau_Hoi WHERE TenBai!='' GROUP BY TenBai";
            ResultSet rs = Helper.JdbcHelper.query(sql);
            while (rs.next()) {
                CauHoi ch = new CauHoi();
                ch.setTenBai(rs.getString(1));
                list.add(ch);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<CauHoi> selectfill(String tenbai, int dokho) {
        ArrayList<CauHoi> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Cau_Hoi WHERE TenBai =? AND DoKho = ?";
            ResultSet rs = Helper.JdbcHelper.query(sql, tenbai, dokho);
            while (rs.next()) {
                CauHoi ch = new CauHoi();
                ch.setID_CauHoi(rs.getInt(1));
                ch.setRole_ID(rs.getBoolean(2));
                ch.setCauHoi(rs.getString(3));
                ch.setDoKho(rs.getInt(4));
                ch.setTenBai(rs.getString(5));
                list.add(ch);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String dapan(int id) {
        String dap = null;
        try {
            String sql = "select DapAnDung from Cau_Hoi where ID_CauHoi = ?";
            ResultSet rs = Helper.JdbcHelper.query(sql, id);
            while (rs.next()) {
                dap = rs.getString("DapAnDung");
            }
            rs.getStatement().getConnection().close();

            return dap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public int idcauhoi(String tenbai, int mucdo) {
        try {
            int a = 0;
            String sql = "select ID_CauHoi from Cau_Hoi where DoKho = ? and TenBai = ?";
            ResultSet rs = Helper.JdbcHelper.query(sql, mucdo, tenbai);
            while (rs.next()) {
                a = rs.getInt("ID_CauHoi");
            }
            rs.getStatement().getConnection().close();
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(CauHoiDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }
    // huy hoang
    public ArrayList<CauHoi> selectID() {
        ArrayList<CauHoi> list = new ArrayList<>();
        try {
            int i = 0;
            String sql = "SELECT ID_CauHoi FROM Cau_Hoi";
            ResultSet rs = Helper.JdbcHelper.query(sql);
            while (rs.next()) {
                CauHoi ch = new CauHoi();
                ch.setID_CauHoi(rs.getInt(1));
                list.add(ch);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
