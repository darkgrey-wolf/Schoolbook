/*
 Handles teacher's load of subjects
ArrayList is actualy slow compared to others like HashList and TreeSet
*/
package packageCore;
import java.sql.*;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.table.DefaultTableModel;
import packageExceptions.*;
public class LoadHandler {
    private int iTeacherId;
    private Connection connection;
    private ArrayList<Load> loads;
    public LoadHandler(int id, Connection con){
        this.iTeacherId=id;
        this.connection=con;
        loads = new ArrayList<>();
    }
    //Retrieve all subjects not handled by any teacher
    //including those that are already handled by this teacher
    public boolean retrieveAllAvailable(){
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        String query1;
        Load loadTemp;
        int iCount;
        int iId;
        try {
            query1 = "SELECT * FROM table_sec";
            ps = connection.prepareStatement(query1);
            ps.executeQuery();
            rs = ps.getResultSet();
            rsmd=rs.getMetaData();
            iCount = rsmd.getColumnCount()-4;
            while(rs.next()){
                for(int k=1;k<=iCount;k++){ // where k denotes subject id;
                    iId = rs.getInt(rsmd.getColumnName(k+4));
                    if(iId==iTeacherId|iId==0){
                        loadTemp = new Load(rs.getInt("sectionId"),
                                        rs.getString("sectionName"),
                                        k,
                                        rs.getInt("gradeLevel"),
                                        connection);
                        loadTemp.setTeacher(iId);
                        loads.add(loadTemp);
                    }
                    
                }
            }
            return true;
        } catch(SQLException e){
            System.out.println("Error at LoadHandler at retrieve: " + e.getMessage());
            return false;
        }
    }
    //Retrieve only those that are already handled by this teacher
    public boolean retrieveOnly(){
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        String query1;
        Load loadTemp;
        int iCount;
        int iId;
        try {
            query1 = "SELECT * FROM table_sec";
            ps = connection.prepareStatement(query1);
            ps.executeQuery();
            rs = ps.getResultSet();
            rsmd=rs.getMetaData();
            iCount = rsmd.getColumnCount()-4;
            while(rs.next()){
                for(int k=1;k<=iCount;k++){ // where k denotes subject id;
                    iId = rs.getInt(rsmd.getColumnName(k+4));
                    if(iId==iTeacherId){
                        loadTemp = new Load(rs.getInt("sectionId"),
                                        rs.getString("sectionName"),
                                        k,
                                        rs.getInt("gradeLevel"),
                                        connection);
                        loadTemp.setTeacher(iId);
                        loads.add(loadTemp);
                    }
                    
                }
            }
            return true;
        } catch(SQLException e){
            System.out.println("Error at LoadHandler at retrieveOnly: " + e.getMessage());
            return false;
        }
    }
    public boolean toDb(DefaultTableModel dm){
        Load temp;
        for(int k=0;k<dm.getRowCount();k++){
            temp = new Load((int)dm.getValueAt(k,0),
                        (String)dm.getValueAt(k,1),
                        (int) dm.getValueAt(k,4),
                        (int) dm.getValueAt(k,2),
                         connection);
            if((boolean) dm.getValueAt(k, 5)){
                temp.setTeacher(iTeacherId);
            }
            else {
                temp.setTeacher(0);
            }
            loads.add(temp);
        }
        ListIterator li = loads.listIterator();
        try {
            while(li.hasNext()){
               temp = (Load) li.next();
               temp.toDb(connection);
            }
            return true;
        }catch(SQLException e){
            System.out.println("Error at toDb: " + e.getMessage());
            return false;
        }
    }
    public boolean updateModel(DefaultTableModel md){
        ListIterator li = loads.listIterator();
        Load loadTemp;
        while(li.hasNext()){
            loadTemp = (Load) li.next();
            md.addRow(loadTemp.toRowData()); //Not selected if id==0
        }
        return true;
    }
    public String[] getSubListStrings(){
        ListIterator li = loads.listIterator();
        String[] stSubAr = new String[loads.size()];
        Load temp;
        for(int k=0;li.hasNext();k++){
            temp = (Load) li.next();
            stSubAr[k] = temp.getSecName()+ ":"+temp.getSubName();
        }
        
        return stSubAr;
    }
    public Load getLoad(String in){
        Load load = new Load();
        ListIterator li = loads.listIterator();
        while(li.hasNext()){
            load = (Load) li.next();
            if(load.isThis(in)){
                return load;
            }
        }
        return load;
    }
}
