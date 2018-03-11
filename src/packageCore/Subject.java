/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageCore;

import java.sql.*;
import java.util.StringTokenizer;

/**
 *
 * @author Retaliation
 */
public class Subject {
    private String strSubName;
    private int iSubId;
    private int iGradeLevel;
    private double dGrade1;
    private double dGrade2;
    private double dGrade3;
    private double dGrade4;
    private double dGradeAve;
    public Subject(String name,int gl,double g1, double g2, double g3, double g4){
        strSubName = name;
        dGrade1 = g1;
        dGrade2 = g2;
        dGrade3 = g3;
        dGrade4 = g4;
        dGradeAve=(g1+g2+g3+g4)/4;
        iGradeLevel = gl;
        retrieveDbId();
    }
    public Subject(int id,int gl,String in){
        StringTokenizer st = new StringTokenizer(in,";");
        while(st.hasMoreTokens()){
            dGrade1 = new Double(st.nextToken());
            dGrade2 = new Double(st.nextToken());
            dGrade3 = new Double(st.nextToken());
            dGrade4 = new Double(st.nextToken());
            dGradeAve = new Double(st.nextToken());
        }
        iGradeLevel = gl;
        iSubId = id;
        retrieveDbName();
    }
    public void setName(String in){
        strSubName = in;
    }
    public void set1(double g){
        dGrade1 = g;
    }
    public void set2(double g){
        dGrade2 = g;
    }
    public void set3(double g){
        dGrade3 = g;
    }
    public void set4(double g){
        dGrade3 = g;
    }
    public double get1(){
        return dGrade1;
    }
    public double get2(){
        return dGrade2;
    }
    public double get3(){
        return dGrade3;
    }
    public double get4(){
        return dGrade4;
    }
    public double getAve(){
        return dGradeAve;
    }
    public double getId(){
        return iSubId;
    }
    public String getName(){
        return strSubName;
    }
    public final void retrieveDbId(){
        PreparedStatement ps;
        ResultSet rs;
        Connector connector;
        String query;
        try {
            connector = new Connector();
            query = "SELECT id FROM table_sublist_"+iGradeLevel+" WHERE subjectName=?";
            ps = connector.getConnection().prepareStatement(query);
            ps.setString(1,strSubName);
            ps.executeQuery();
            rs = ps.getResultSet();
            while(rs.next()){
                iSubId = rs.getInt("id");
            }
        } catch(SQLException | ClassNotFoundException e1){
            System.out.println("Error: " + e1.getMessage());
        }
    }
    public final void retrieveDbName(){
        PreparedStatement ps;
        ResultSet rs;
        Connector connector;
        String query;
        try {
            connector = new Connector();
            query = "SELECT subjectName FROM table_sublist_"+iGradeLevel+" WHERE id=?";
            ps = connector.getConnection().prepareStatement(query);
            ps.setInt(1,iSubId);
            ps.executeQuery();
            rs = ps.getResultSet();
            while(rs.next()){
                strSubName = rs.getString("subjectName");
            }
        } catch(SQLException | ClassNotFoundException e1){
            System.out.println("Error: " + e1.getMessage());
        }
    }
    @Override
    public String toString(){
        Double d1 = dGrade1;
        Double d2 = dGrade2;
        Double d3 = dGrade3;
        Double d4 = dGrade4;
        Double d5 = dGradeAve;
        return d1+";"+d2+";"+d3+";"+d4+";"+d5;
    }
}
