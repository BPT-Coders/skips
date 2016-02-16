/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;


import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import data.Data;

/**
 *
 * @author aamir
 */
public class ScrollPane extends javax.swing.JPanel {
    ArrayList<Student> students;
    Data data;
    String[] lastNames;
    int heightPane;
    int i;
    /**
     * Creates new form ScrollPane
     */
    public ScrollPane() {
        initComponents();
        data = Form.data;
        lastNames = data.getLastNames();
        students = new ArrayList<>();
        clear();
    }
    
    public void addStudent(){
        students.add(new Student(lastNames));
        Student st = students.get(students.size() - 1);
        this.add(st);
        //Отступы и размеры
        st.setBounds(10, i, 210, 45);
        i += 45;
        if (i > this.getPreferredSize().height){
            heightPane += 45;
        }
        Dimension d = new Dimension(250, heightPane);
        this.setPreferredSize(d); 
    }
    
    public void removeStudent(){
        Student st = students.get(students.size() - 1);
        this.remove(st);
        students.remove(st);
        //Отступы и размеры
        i -= 45;
        if (i < this.getPreferredSize().height){
            heightPane -= 45;
        }
        Dimension d = new Dimension(250, heightPane);
        this.setPreferredSize(d);
        this.revalidate();
        this.repaint();
    }
    
    public final void clear(){
        for(Student student : students){
            this.remove(student);
        }
        students.clear();
        this.revalidate();
        this.repaint();
        i = 10;
        heightPane = 190;

    }
    
 
    public ArrayList<HashMap<String, String>> getDataToSave(){
        ArrayList<HashMap<String, String>> toSave = new ArrayList<>();
        for(Student stupid : students){
            toSave.add(stupid.getValues());
        }
        return toSave;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(250, 190));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
