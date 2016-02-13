package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSource {
    HashMap<String, String> settings;
    String userName;
    String password;
    String url;
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    public DataSource() {
        try {
            initSettings();
        } catch (IOException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        userName = settings.get("userName");
        password = settings.get("password");
        url = settings.get("url");
    }
    void initSettings() throws IOException{
        String filePath = "D:\\properties.ini";
        FileInputStream fileInputStream = null;
        //FileOutputStream fileOutputStream = null;
        Properties properties = new Properties();
        settings = new HashMap<String, String>();
        try {
            fileInputStream = new FileInputStream(filePath);
            properties.load(fileInputStream);
            Enumeration enumeration = properties.keys();
            while (enumeration.hasMoreElements()){
                String key = enumeration.nextElement().toString();
                settings.put(key, properties.getProperty(key));
            }
            //fileOutputStream = new FileOutputStream(filePath);
            //properties.store(fileOutputStream, "mod");
        } catch (FileNotFoundException e) {
            new File("property.ini").createNewFile();
            System.out.println("createNewFile");
            e.getMessage();
        } catch (InvalidPropertiesFormatException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } finally {
            //fileOutputStream.close();
            fileInputStream.close();
        }
    }
    public boolean test(){
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
        finally {
            closeCon();
        }
    }

    public String[][] getTab(String t){
        String[][] arr = null;
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);   
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from " + t + ";");
            rs.last();
            int m = rs.getRow();
            int n = rs.getMetaData().getColumnCount();
            rs.beforeFirst();
            arr = new String[m][n];
            int i = 0;
            while (rs.next()){
                for (int j = 1; j <= n; j++){
                    arr[i][--j] = rs.getString(++j);
                }
                i++;
            }          
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally {closeCon();}
        return arr;
    }

    public void exe(String q) throws SQLException{
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            stmt = conn.createStatement();          
            stmt.executeUpdate(q);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            closeCon();
        }       
    }
    
    void closeCon(){
        if (conn != null)
            {
                try
                {
                    conn.close ();
                }
                catch (Exception ex) { }
            }
    }
    public boolean isAvailableShedule(String teacherLoadsId, String sheduleDate){
        boolean result = false;
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);   
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT id from shedule where teacherLoadId in (" + teacherLoadsId + ") and date ='" + sheduleDate + "';");
            if (rs.next()){
                result = true;
            }
            else{
                result = false;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally {closeCon();}
        return result;
    }
}
