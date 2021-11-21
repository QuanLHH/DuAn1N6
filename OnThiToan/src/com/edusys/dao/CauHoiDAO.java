/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import PakagesClass.CauHoi;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author taola
 */
public class CauHoiDAO extends EduSysDAO<CauHoi, Integer> {

    String INSERT = "INSERT INTO Cau_Hoi(ID_BT,CauHoi,DoKho) VALUES (?,?,?)";
    String UPDATE = "UPDATE Cau_Hoi SET ID_BT=?,CauHoi=?,DoKho=? WHERE ID_CauHoi=?";
    String DELETE = "DELETE FROM Dap_An WHERE ID_DapAn = ?\n"
            + "DELETE FROM Cau_Hoi WHERE ID_CauHoi=?";
    String SELECTALL = "SELECT * FROM Cau_Hoi";

    @Override
    public void insert(CauHoi ch) {
        Helper.JdbcHelper.update(INSERT, ch.getID_BT(), ch.getCauHoi(), ch.getDoKho());
    }

    @Override
    public void update(CauHoi entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<CauHoi> selectALL() {
        ArrayList<CauHoi> list = selectBySql(SELECTALL);
        return list;
    }

    @Override
    public CauHoi selectById(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ArrayList<CauHoi> selectBySql(String sql, Object... args) {
        ArrayList<CauHoi> list = new ArrayList<>();
        try {
            ResultSet rs = Helper.JdbcHelper.query(sql, args);
            while (rs.next()) {
                CauHoi ch = new CauHoi();
                ch.setID_CauHoi(rs.getInt(1));
                ch.setID_BT(rs.getInt(2));
                ch.setCauHoi(rs.getString(3));
                ch.setDoKho(rs.getInt(4));
                ch.setNgayTao(rs.getDate(5));
                list.add(ch);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    public CauHoi selectIDNew() {
        String SELECTIDNEW = "SELECT TOP 1 * FROM Cau_Hoi ORDER BY ID_CauHoi DESC ";
        ArrayList<CauHoi> list = selectBySql(SELECTIDNEW);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

}
