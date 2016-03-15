/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aamir
 */
public class Settings {
    HashMap<String, String> settings = null;
    public Settings(){
        readSettings();
    }
    
    final void readSettings() {
        String filePath = ".\\etc\\properties.ini";
        FileInputStream fileInputStream = null;
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
        } catch (FileNotFoundException e) {
            try {
                new File("property.ini").createNewFile();
                System.out.println("createNewFile");
            } catch (IOException ex) {
                Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (InvalidPropertiesFormatException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public String getUserDB(){
        return settings.get("userName");
    }
    public String getPassDB(){
        return settings.get("password");
    }
    public String getURLDB(){
        return settings.get("url");
    }
}
