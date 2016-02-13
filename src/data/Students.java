package data;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Students extends Manager {
    
    public Students() throws IOException {
        initTable("students");
    }
    public void add(String lName, String fName, String mName){
        String quary = "INSERT INTO students (lName, fName, mName) values ('" + lName + "', '" + fName + "', '" + mName +"');";
        try {
            dataSource.exe(quary);
        } catch (SQLException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        }
        initTable("students");
    }


    public String getLNameOnId(String id){

        String result = null;
        for (String[] table1 : table) {
            if (table1[0].equals(id)) {
                result = table1[3];
            }
        }
        return result;
    }


    

    public String getIdOnLastName(String lName){

        String result = null;
        for (String[] student : table) {
            if (student[1].equals(lName) ) {
                result = student[0];
            }
        }
        return result;
    }


    public String[] getLastNames(){
        String[] result = new String[table.length];
        for (int i = 0; i < table.length; i++){
            result[i] = table[i][1];
        }
        return result;
    }





}
