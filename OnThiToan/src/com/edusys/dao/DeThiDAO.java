/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import PakagesClass.BaiThiChiTiet;
import PakagesClass.DeThi;
import PakagesClass.ThongTinBaiThi;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author taola
 */
public class DeThiDAO extends EduSysDAO<DeThi, Integer> {

    String INSERT = "INSERT INTO ChiTiet_BaiThi(ID_MaND,ID_BaiThi,SoCauDung,SoCauSai,Diem)VALUES(?,?,?,?,?)";
    String DELETE = "DELETE FROM ChiTiet_BaiThi WHERE ID_BaiThi=?";
    String SelectByID = "SELECT TOP 1 ID_BaiThi FROM Bai_Thi WHERE ID_CauHoi=? AND MaDe=? AND DoKho=?";
    
    public int selectByIds(Integer id, String made, int dokho) {
        int id_bt = 0;
        try {
            ResultSet rs = Helper.JdbcHelper.query(SelectByID, id, made, dokho);
            while (rs.next()) {
                id_bt = rs.getInt(1);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return id_bt;
    }

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
        Helper.JdbcHelper.update(DELETE, key);
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

    public ArrayList<DeThi> selectDeThi(String made, int dokho) {
        String sql = "SELECT Bai_Thi.ID_CauHoi, Role_ID,CauHoi,DapAn1,DapAn2,DapAn3,DapAn4,DapAnDung FROM Cau_Hoi \n"
                + "join Bai_Thi on Cau_Hoi.ID_CauHoi=Bai_Thi.ID_CauHoi WHERE MaDe=? AND Bai_Thi.DoKho=?";
        ArrayList<DeThi> list = selectBySql(sql, made, dokho);

        return list;
    }

    public int selectSoCau(String made, int dokho) {
        String sql = "SElECT COUNT(ID_CauHoi) FROM Bai_Thi WHERE MaDe = ? AND DoKho=?";
        int soCau = 0;
        try {
            ResultSet rs = Helper.JdbcHelper.query(sql, made, dokho);
            while (rs.next()) {
                soCau = rs.getInt(1);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return soCau;
    }

    public int selectSoCauDe(String made, int dokho) {
        String sql = "SElECT COUNT(Cau_Hoi.DoKho) FROM Bai_Thi JOIN Cau_Hoi ON Bai_Thi.ID_CauHoi=Cau_Hoi.ID_CauHoi \n"
                + "	WHERE Cau_Hoi.DoKho=1 AND MaDe = ? AND Bai_Thi.DoKho=?";
        int soCau = 0;
        try {
            ResultSet rs = Helper.JdbcHelper.query(sql, made, dokho);
            while (rs.next()) {
                soCau = rs.getInt(1);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return soCau;
    }

    public int selectSoCauTB(String made, int dokho) {
        String sql = "SElECT COUNT(Cau_Hoi.DoKho) FROM Bai_Thi JOIN Cau_Hoi ON Bai_Thi.ID_CauHoi=Cau_Hoi.ID_CauHoi \n"
                + "	WHERE Cau_Hoi.DoKho=2 AND MaDe = ? AND Bai_Thi.DoKho=?";
        int soCau = 0;
        try {
            ResultSet rs = Helper.JdbcHelper.query(sql, made, dokho);
            while (rs.next()) {
                soCau = rs.getInt(1);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return soCau;
    }

    public int selectSoCauKho(String made, int dokho) {
        String sql = "SElECT COUNT(Cau_Hoi.DoKho) FROM Bai_Thi JOIN Cau_Hoi ON Bai_Thi.ID_CauHoi=Cau_Hoi.ID_CauHoi \n"
                + "	WHERE Cau_Hoi.DoKho=3 AND MaDe = ? AND Bai_Thi.DoKho=?";
        int soCau = 0;
        try {
            ResultSet rs = Helper.JdbcHelper.query(sql, made, dokho);
            while (rs.next()) {
                soCau = rs.getInt(1);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return soCau;
    }

    public void inserts(BaiThiChiTiet bt) {
        Helper.JdbcHelper.update(INSERT, bt.getID_MaND(), bt.getID_BaiThi(), bt.getSoCauDung(),
                bt.getSoCauSai(), bt.getDiem());
    }

    // select baithichitiet
    public ArrayList<BaiThiChiTiet> sqlBaiThiChiTiet(String sql, Object... args) {
        ArrayList<BaiThiChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = Helper.JdbcHelper.query(sql, args);
            while (rs.next()) {
                BaiThiChiTiet dt = new BaiThiChiTiet();
                dt.setID_BaiThiCT(rs.getInt("ID_BaiThiCT"));
                dt.setID_MaND(rs.getInt("ID_MaND"));
                dt.setID_BaiThi(rs.getInt("ID_BaiThi"));
                dt.setSoCauDung(rs.getInt("SoCauDung"));
                dt.setSoCauSai(rs.getInt("SoCauSai"));
                dt.setDiem(rs.getFloat("Diem"));
                dt.setNgayThi(rs.getDate("NgayThi"));
                list.add(dt);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return list;
    }

    public BaiThiChiTiet selectNewID_BaiThiCT() {
        String sql = "SELECT TOP 1 * FROM ChiTiet_BaiThi ORDER BY ID_BaiThiCT DESC";
        ArrayList<BaiThiChiTiet> list = sqlBaiThiChiTiet(sql);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public void insertTTBaiThi(ThongTinBaiThi bt) {
        String InsertTTBaiThi = "INSERT INTO ThongTin_BaiThi(ID_CauHoi,ID_BaiThiCT,MaDe,DoKho,DapAnChon) "
                + "VALUES(?,?,?,?,?)";
        Helper.JdbcHelper.update(InsertTTBaiThi, bt.getID_CauHoi(), bt.getID_BaiThiCT(),
                bt.getMaDe(), bt.getDoKho(), bt.getDapAnChon());
    }

    public ArrayList<BaiThiChiTiet> selectALLBaiThiCT() {
        String sql = "SELECT*FROM ChiTiet_BaiThi";
        ArrayList<BaiThiChiTiet> list = sqlBaiThiChiTiet(sql);
        return list;
    }
    public ArrayList<BaiThiChiTiet> selectBaiThiCTByID(String made,int dokho,int id) {
        String SelectByTT = "SELECT ID_BaiThiCT,ID_MaND,ChiTiet_BaiThi.ID_BaiThi,SoCauDung,SoCauSai,Diem,NgayThi \n"
            + "FROM ChiTiet_BaiThi JOIN Bai_Thi ON ChiTiet_BaiThi.ID_BaiThi = Bai_Thi.ID_BaiThi\n"
            + "WHERE MaDe=? AND DoKho=? AND ID_MaND=?";
        ArrayList<BaiThiChiTiet> list = sqlBaiThiChiTiet(SelectByTT,made,dokho,id);
        return list;
    }
    public ArrayList<BaiThiChiTiet> selectBaiThiCTByNgayThi(String date,int id) {
        
        String SelectByTT = "SELECT ID_BaiThiCT,ID_MaND,ChiTiet_BaiThi.ID_BaiThi,SoCauDung,SoCauSai,Diem,NgayThi \n"
            + "FROM ChiTiet_BaiThi JOIN Bai_Thi ON ChiTiet_BaiThi.ID_BaiThi = Bai_Thi.ID_BaiThi\n"
            + "WHERE NgayThi = ? AND ID_MaND=?";
        ArrayList<BaiThiChiTiet> list = sqlBaiThiChiTiet(SelectByTT,date,id);
        return list;
    }
}
