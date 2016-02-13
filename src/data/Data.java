/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author aamir
 */
public class Data {
    Students students;
    Skips skips;
    public Data() throws IOException {
        this.students = new Students();
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
}
