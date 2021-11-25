package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcHelper {
    static String dburl = "jdbc:sqlserver://localhost:1433;databaseName=FPoly_DuAn1";
    static String username="sa";
    static String password="songlong";
    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static {
        try{
            Class.forName(driver);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static PreparedStatement getStmt(String sql,Object...args) throws SQLException{
        Connection cn = DriverManager.getConnection(dburl,username,password);
        PreparedStatement stmt;
        if(sql.trim().startsWith("{")){
            stmt = cn.prepareCall(sql);
        }else{
            stmt = cn.prepareStatement(sql);
        }
        for(int i=0;i<args.length;i++){
            stmt.setObject(i+1, args[i]);
        }
        return stmt;
    }
    public static ResultSet query(String sql,Object...agrs) throws SQLException{
        PreparedStatement stmt = JdbcHelper.getStmt(sql, agrs);
        return stmt.executeQuery();
        
    }
    public static Object value(String sql,Object...args){
        try{
            ResultSet rs = JdbcHelper.query(sql, args);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    }
    public static int update (String sql,Object...args){
        
        try{
            PreparedStatement stmt = JdbcHelper.getStmt(sql, args);
            try{
                return stmt.executeUpdate();
            }
            finally{
                stmt.getConnection().close();
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
