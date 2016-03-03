/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author aamir
 */
public class Skips extends Manager {
    String[][] allSkips;
    public Skips(){
        initTable("skips");
        getAllSkips();
        
    }
    public void add(ArrayList<HashMap<String, String>> toSave, String date){
        String quary = "INSERT INTO skips (date, hours, idStudent) values ";
        for (HashMap skip : toSave){
            quary = quary + "('" + date + "', '" + skip.get("hours") + "', '" + skip.get("idStudent") + "'), ";
        }
        quary = quary.substring(0, quary.length() - 2) + ";";
        try {
            System.out.println(quary);
            dataSource.exe(quary);
        } catch (SQLException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        }
        initTable("skips");
        JOptionPane.showMessageDialog(null, "Данные сохранены");

    }
    public String getLastDate(){
        String quary = "SELECT MAX( DATE ) FROM  `skips`;";
        String[][] res = dataSource.getRow(quary);
        return res[0][0];
    }
    void getAllSkips(){
        allSkips = dataSource.getTab("skips WHERE MONTH( DATE ) = 02");
    }
    public String[][] getSkips(String idStudent){
        //Выборка пропусков по id студента
        int length = 0;
        for (String[] skip: allSkips){
            if (skip[3].equals(idStudent)){
                //skips[i] = skip;
                length++;
            }
        }
        int i = 0;
        String[][] skips = new String[length][4];
        for (String[] skip: allSkips){
            if (skip[3].equals(idStudent)){
                skips[i] = skip;
                i++;
            }
        }
        return skips;
    }
}
