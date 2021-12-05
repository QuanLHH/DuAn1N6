package com.edusys.dao;

import PakagesClass.BaiThiChiTiet;
import PakagesClass.ThongTinBaiThi;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ThongTinBaiThiDAO extends EduSysDAO<ThongTinBaiThi, Integer> {

    String SelectALL = "SELECT*FROM ThongTin_BaiThi";
    String SelectByTT = "SELECT * FROM ThongTin_BaiThi \n"
            + "join ChiTiet_BaiThi \n"
            + "	on ThongTin_BaiThi.ID_BaiThiCT=ChiTiet_BaiThi.ID_BaiThiCT \n"
            + "join Cau_Hoi\n"
            + "	on ThongTin_BaiThi.ID_CauHoi = Cau_Hoi.ID_CauHoi\n"
            + "WHERE MaDe=? AND ThongTin_BaiThi. DoKho=? AND ID_MaND=?";

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
                dt.setID_TTBaiThi(rs.getInt(1));
                dt.setID_CauHoi(rs.getInt(2));
                dt.setID_BaiThiCT(rs.getInt(3));
                dt.setMaDe(rs.getString(4));
                dt.setDoKho(rs.getInt(5));
                dt.setDapAnChon(rs.getString(6));
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
