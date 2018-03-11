
package packageCore;

import java.sql.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
*
*@author Retaliaiton
*/
public class Load {
    //private Connection connection;
    private String stSubName;
    private String stSecName;
    private int iSecId;
    private int iSubId;
    private int iGdLev;
    private int iTrId;
    public Load(){
        stSubName = "NONE";
        stSecName = "NONE";
        iSecId = 0;
        iSubId = 0;
        iGdLev = 0;
        iTrId = 0;
    }
    public Load(int iSec,String secName,int id,int grade,Connection con){
        this.iSecId=iSec;
        this.stSecName=secName;
        this.iSubId=id;
        this.iGdLev=grade;
        this.iTrId=0;
        try {
            //retrieve name from database;
            retrieveName(con);
        } catch (SQLException ex) {
            System.out.println("Error at Load: " + ex.getMessage());
            stSubName = "Not Found";
        }
    }
    private void retrieveName(Connection connection) throws SQLException{
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT subjectName FROM table_sublist_" + iGdLev
                      +" WHERE id = ?";
        ps = connection.prepareStatement(query);
        ps.setInt(1,iSubId);
        ps.executeQuery();
        rs = ps.getResultSet();
        if(rs.next()){
            stSubName = rs.getString("subjectName");
        }
        else {
            stSubName = "Not Found";
        }
        
    }
    public void setTeacher(int id){
        this.iTrId=id;
    }
    public boolean toDb(Connection connection) throws SQLException{
        PreparedStatement ps;
        String query = "UPDATE table_sec SET tr_" + iSubId + " = ? WHERE sectionId = ?";
        ps = connection.prepareStatement(query);
        ps.setInt(1,iTrId);
        ps.setInt(2,iSecId);
        return ps.executeUpdate()!=0;
    }
    public Object[] toRowData(){
        return new Object[]{iSecId,stSecName,iGdLev,stSubName,iSubId,iTrId!=0};
    }
    public String getSubName(){
        return stSubName;
    }
    public String getSecName(){
        return stSecName;
    }
    public int getSubId(){
        return iSubId;
    }
    public int getSecId(){
        return iSecId;
    }
    public int getGdLev(){
        return iGdLev;
    }
    public boolean isThis(String in){
        StringTokenizer tk = new StringTokenizer(in,":");
        int iCount = 0;
        String stTemp1 = "none";
        String stTemp2 = "none";
        while(tk.hasMoreTokens()){
            if(iCount>1){
                return false;
            }
            stTemp1 = tk.nextToken();
            stTemp2 = tk.nextToken();
            iCount++;
        }
        return stTemp1.equals(stSecName)&stTemp2.equals(stSubName);
    }
    public int[] getStudentIds(Connection con){
        int[] iIdAr = new int[]{0};
        ArrayList<Integer> iIds = new ArrayList<>();
        //ArrayList<Integer> iIds = new ArrayList<>();
        int iCount;
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        String query = "SELECT id FROM table_student WHERE sectionid = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1,iSecId);
            ps.executeQuery();
            rs = ps.getResultSet();
            //iCount = rs.getFetchSize();
            while(rs.next()){
                iIds.add(rs.getInt("id"));
            }
            iIdAr = new int[iIds.size()];
            for(int k=0;k<iIds.size();k++){
                iIdAr[k] = iIds.get(k);
            }
            return iIdAr;
        } catch (SQLException ex) {
            System.out.println("Error at getStudentIds: " + ex.getMessage());
            return iIdAr;
        }
        
    }
}
