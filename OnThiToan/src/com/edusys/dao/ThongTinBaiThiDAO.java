package com.edusys.dao;

import PakagesClass.BaiThiChiTiet;
import PakagesClass.ThongTinBaiThi;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ThongTinBaiThiDAO extends EduSysDAO<ThongTinBaiThi, Integer> {

    String SelectALL = "SELECT ID_TTBaiThi,ID_CauHoi,ThongTin_BaiThi. ID_BaiThiCT,MaDe,DoKho,"
            + "DapAnChon,DapAn,ID_MaND FROM ThongTin_BaiThi join ChiTiet_BaiThi \n"
            + "on ThongTin_BaiThi.ID_BaiThiCT=ChiTiet_BaiThi.ID_BaiThiCT WHERE ID_MaND=?";
    String SelectByTT = "SELECT * FROM ThongTin_BaiThi \n"
            + "join ChiTiet_BaiThi \n"
            + "	on ThongTin_BaiThi.ID_BaiThiCT=ChiTiet_BaiThi.ID_BaiThiCT \n"
            + "join Cau_Hoi\n"
            + "	on ThongTin_BaiThi.ID_CauHoi = Cau_Hoi.ID_CauHoi\n"
            + "WHERE ChiTiet_BaiThi. ID_BaiThiCT=? AND MaDe=? AND ThongTin_BaiThi. DoKho=? AND ID_MaND=?";

    @Override
    public void insert(ThongTinBaiThi entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ThongTinBaiThi entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ThongTinBaiThi> selectALL() {
        ArrayList<ThongTinBaiThi> list = selectBySql(SelectALL);
        return list;
    }

    public ArrayList<ThongTinBaiThi> selectAllTTBaiThi(int id) {
        ArrayList<ThongTinBaiThi> list = selectBySql(SelectALL, id);
        return list;
    }

    public ArrayList<ThongTinBaiThi> selectByTT(String made, int dokho, int id) {
        ArrayList<ThongTinBaiThi> list = selectBySql(SelectByTT, made, dokho, id);
        return list;
    }

    @Override
    public ThongTinBaiThi selectById(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ArrayList<ThongTinBaiThi> selectBySql(String sql, Object... args) {
        ArrayList<ThongTinBaiThi> list = new ArrayList<>();
        try {
            ResultSet rs = Helper.JdbcHelper.query(sql, args);
            while (rs.next()) {
                ThongTinBaiThi dt = new ThongTinBaiThi();
                dt.setID_TTBaiThi(rs.getInt("ID_TTBaiThi"));
                dt.setID_CauHoi(rs.getInt("ID_CauHoi"));
                dt.setID_BaiThiCT(rs.getInt("ID_BaiThiCT"));
                dt.setMaDe(rs.getString("MaDe"));
                dt.setDoKho(rs.getInt("DoKho"));
                dt.setDapAnChon(rs.getString("DapAnChon"));
                dt.setID_MaND(rs.getInt("ID_MaND"));
                dt.setDapAn(rs.getString("DapAn"));
                list.add(dt);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return list;
    }

    public ArrayList<Object[]> selectSQLThongTinThi(int idBT,String made, int dokho, int id) {
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = Helper.JdbcHelper.query(SelectByTT,idBT, made,dokho,id);
            while (rs.next()) {
                Object[] dt = {rs.getInt("ID_TTBaiThi"), rs.getString("CauHoi"),
                    rs.getString("DapAn"), rs.getString("DapAnDung")};
                list.add(dt);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return list;
    }
   
}
