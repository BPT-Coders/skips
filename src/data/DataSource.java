package data;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSource {    
    Connection conn;
    Statement stmt;

    public DataSource() {
        Settings settings = new Settings();
        String userName = settings.getUserDB();
        String password = settings.getPassDB();
        String url = settings.getURLDB();
        openCon(userName, password, url);
    }
    
    final void openCon(String user, String pass, String url){
        try {
            try {
                Class.forName ("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = DriverManager.getConnection (url, user, pass);
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[][] getTab(String q){
        String[][] arr = null;
        try
        {       
            ResultSet rs = stmt.executeQuery(q);
            rs.last();
            int m = rs.getRow();//Количество строк
            int n = rs.getMetaData().getColumnCount();//Количество столбцов
            arr = new String[m][n];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()){
                for (int j = 1; j <= n; j++){
                    arr[i++][--j] = rs.getString(++j);
                }
            }          
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return arr;
    }  

    public void exe(String q) throws SQLException{
        try
        {                
            stmt.executeUpdate(q);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }    
    }
       
}