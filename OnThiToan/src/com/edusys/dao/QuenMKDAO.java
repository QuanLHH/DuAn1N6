/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;


import Helper.JdbcHelper;
import PakagesClass.QuenMK;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QuenMKDAO extends EduSysDAO<QuenMK, String> {

   String INSERT = "INSERT INTO Nguoi_Dung (HoTen,GioiTinh,SDT,NgaySinh,Email) VALUES (?,?,?,?,?)";
    String UPDATE = "UPDATE Nguoi_Dung SET HoTen=?,GioiTinh=?,SDT=?,NgaySinh=?,Email=? WHERE ID_MaND=?";
    String SelectMaxID = "SELECT * FROM Nguoi_Dung WHERE ID_MaND= (SELECT MAX(ID_MaND) FROM Nguoi_Dung )";

    @Override
    public void insert(QuenMK entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(QuenMK entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<QuenMK> selectALL() {
       String sql = "SELECT*FROM Nguoi_Dung join Tai_Khoan on Nguoi_Dung.ID_MaND = Tai_Khoan.ID_MaND";
        return selectBySql(sql);
    }

    @Override
    public QuenMK selectById(String key) {
       String sql = "SELECT*FROM Nguoi_Dung join Tai_Khoan on Nguoi_Dung.ID_MaND = Tai_Khoan.ID_MaND\n"
                + "where Nguoi_Dung.ID_MaND like ?";
        ArrayList<QuenMK> list = selectBySql(sql,key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected ArrayList<QuenMK> selectBySql(String sql, Object... args) {
        ArrayList<QuenMK> list = new ArrayList<>();
        try{
            ResultSet rs = Helper.JdbcHelper.query(sql, args);
            while(rs.next()){
                QuenMK nd = new QuenMK();
                nd.setMaND(rs.getString("maND"));
                nd.setEmail(rs.getString("email"));
                nd.setHoten(rs.getString("hoten"));
                nd.setMatkhau(rs.getString("matkhau"));
                nd.setVaitro(rs.getBoolean("vaitro"));
                list.add(nd);
            }
            rs.getStatement().getConnection().close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public void updateResetPass(QuenMK entity) {
        String UPDATE_MK_SQL = "UPDATE dbo.Tai_Khoan SET MatKhau = ? WHERE TenTaiKhoan Like ?";
        JdbcHelper.update(UPDATE_MK_SQL, entity.getMatkhau(), entity.getHoten());
    }
}
