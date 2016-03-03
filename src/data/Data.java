/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aamir
 */
public class Data {
    Students students;
    Skips skips;
    public Data() {
        try {
            this.students = new Students();
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.skips = new Skips();
    }
    public String[] getLastNames(){
        return students.getLastNames();
    }
    public void saveSkips(ArrayList<HashMap<String, String>> toSave, String date){
        skips.add(toSave, date);
    }
    public String getIdOnLastName(String lastName){
        return students.getIdOnLastName(lastName);
    }
    public String getLastDate(){
        return skips.getLastDate();
    }
    public String[][] getSkips(String idStudent){
        return skips.getSkips(idStudent);
    }
}
