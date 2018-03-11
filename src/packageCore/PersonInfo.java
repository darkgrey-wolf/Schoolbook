package packageCore;
import java.sql.*;
import java.util.*;
import java.util.Date;
import packageExceptions.DbUpdateFailedException;
import packageExceptions.DuplicateAccountException;
public class PersonInfo {
    public final static String NONE = "NONE";
    protected int iId;
    protected String strNameF;
    protected String strNameM;
    protected String strNameL;
    protected String strGender;
    protected String strPhone;
    protected String strType;
    protected Date dateBirth;
    protected boolean bDataSet;
    public PersonInfo(){
      iId = 0;
      bDataSet=false;
    }
    public PersonInfo(String nameF, String nameM,String nameL, int id, String g, String phone, String type, Date bd){
        strNameF = nameF; 
        strNameM = nameM; 
        strNameL = nameL; 
        iId = id;
        strGender = g; 
        strPhone = phone; 
        strType = type; 
        dateBirth = bd;
        bDataSet=true;
    }
    public void setData(String nameF, String nameM, String nameL, int id, String gender, String phone, String type, Date bd){
        strNameF=nameF;
        strNameM=nameM;
        strNameL=nameL;
        iId=id;
        strGender=gender;
        strPhone=phone;
        strType=type;
        dateBirth=bd;
        bDataSet=true;
    }
    public boolean fetchData(int i, String type, Connection connection) throws SQLException{
        PreparedStatement pst;
        ResultSet rs;
        String query1 = "SELECT EXISTS(SELECT * FROM table_"+type.toLowerCase() + " "
                + "WHERE id='" + i + "')";
        String query2 = "SELECT nameF,nameM,nameL,gender,birthDate,phoneNumber FROM "
                + "table_"+type.toLowerCase() + " WHERE id='" + i + "'";
        Parser ip = new Parser();
        pst = connection.prepareStatement(query1);
        //pst.setInt(1,i);
        pst.executeQuery();
        rs = pst.getResultSet();
        rs.next();
        if(rs.getBoolean(1)){
            iId=i;
            pst = connection.prepareStatement(query2);
            //pst.setInt(1, i);
            pst.executeQuery();
            rs = pst.getResultSet();
            while(rs.next()){
                strNameF = rs.getString("nameF");
                strNameM = rs.getString("nameM");
                strNameL = rs.getString("nameL");
                strGender = rs.getString("gender");
                dateBirth = rs.getDate("birthDate");
                strPhone = rs.getString("phoneNumber");
                strType = type;  
            }
            bDataSet=true;
            return true;
        }
        else {
            bDataSet=false;
            return false;
        }
    }
    public boolean delAllPerson(String type){
        Connection con;
        try {
            con = new Connector().getConnection();
            switch(type){
                case "Student": return delAllStudent(con);
                case "Teacher": return delAllTeacher(con);
                case "All": return delAllStudent(con)&delAllTeacher(con);
                default: return false;
            }
        } catch(ClassNotFoundException|SQLException e){
            System.out.println("Error at delPerson " + e.getMessage());
            return false;
        }
    }
    private boolean delAllStudent(Connection con) throws SQLException{
        PreparedStatement ps;
        String query1 = "TRUNCATE table_student";
        String query2;
        ps = con.prepareStatement(query1);
        ps.executeUpdate();
        for(int k=7;k<=10;k++){
            query2 = "TRUNCATE table_";
            query2 = query2+k;
            ps = con.prepareStatement(query2);
            ps.executeUpdate();
        }
        return true;
    }
    private boolean delAllTeacher(Connection con) throws SQLException{
        PreparedStatement ps;
        String query1 = "TRUNCATE table_teacher";
        ps = con.prepareStatement(query1);
        ps.executeUpdate();
        String query2 = "UPDATE table_sec SET adviserId=0";
        for(int k=1;k<=10;k++){
            query2 += ",tr_" + k + "='0'";
        }
        ps = con.prepareStatement(query2);
        ps.executeUpdate();
        
        return true;
    }
    public void setId(int i){
        iId = i;
    }
    public String getFullName(){
        //Format: LastName, FirstName, MiddleInitial(s)
        return strNameL + ", " + strNameF + ", " + strNameM;
    }
    public String getName(){
        return strNameL + ", " + strNameF + ", " + middleInitial(strNameM);
    }
    public String getFirstName(){
        return strNameF;
    }
    public String getMiddleName(){
        return strNameM;
    }
    public String getLastName(){
        return strNameL;
    }
    public java.sql.Date getBirthDateSQL(){
        GregorianCalendar c = new GregorianCalendar();
        java.sql.Date d;
        try {
            c.setTime(dateBirth);
            d = new java.sql.Date(c.getTimeInMillis()); 
            return d;
        } catch(NullPointerException e){
            c.setTime(new Date());
            d = new java.sql.Date(c.getTimeInMillis());
            return d;
        }
    }
    public Date getBirthDate(){
        return dateBirth;
    }
    public Calendar getBirthCalendar(){
        //Useful in extracting months and dates
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(dateBirth);
            return c;
        } catch(NullPointerException e){
            c.setTime(new Date());
            return c;
        }
    }
    public int getId(){
        return iId;
    }
    public String getIdString(int len){
        Parser ip = new Parser();
        return ip.getString(iId, len);
    }
    public String getGender(){
        if(strGender.equalsIgnoreCase(NONE)) return "Unknown";
        return strGender;
    }
    public String getPhone(){
        try {
            if(strPhone.equalsIgnoreCase(NONE)) return "Not Available";
            return strPhone;
        } catch(NullPointerException e){
            return "Not Available";
        } 
    }
    public String getAge(){
        Date dateCurrent = new Date();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal2.setTime(dateCurrent);
        cal1.setTime(dateBirth);
        try {
            Integer iWYearGap = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
            //If birthdate not yet passed, minus 1 on year gap
            if(cal1.get(Calendar.DAY_OF_YEAR)>cal2.get(Calendar.DAY_OF_YEAR)){
                --iWYearGap;
                return iWYearGap.toString();
            }
            else {
                return iWYearGap.toString();
            }
        }
        catch(NumberFormatException e){
            System.out.println("Error at getAge() at PersonInfo " + e);
            return "N/A";
        }
    }
    public String getType(){
        if(strType.isEmpty()|strType.equalsIgnoreCase(NONE)) return NONE;
        return strType;
    }
    public String middleInitial(String in){
        //Turns Middle Name into Middile Initials
        try {
            StringTokenizer tk = new StringTokenizer(in," ");
            String temp;
            ArrayList<Character> chars = new ArrayList<>();
            char[] feed;
            while(tk.hasMoreTokens()){
                temp = tk.nextToken();
                temp = temp.toUpperCase();
                chars.add(temp.charAt(0));
                chars.add('.');
                chars.add(' ');
            }
            chars.remove(chars.size()-1);
            feed = new char[chars.size()];
            for(int k=0;k<feed.length;k++){
                feed[k] = chars.get(k);
            }
            return new String(feed);
        } catch(NullPointerException e){
            return "???";
        }
        
    }
    public void toDb(Connection connection) throws DuplicateAccountException, SQLException{
        Statement st;
        PreparedStatement pst;
        ResultSet rs;
        String query1 = "SELECT EXISTS(SELECT * FROM table_"+strType.toLowerCase()+" "
                + "WHERE nameF='" + strNameF + "' AND nameM='" +strNameM + "' "
                + "AND nameL='" +strNameL+ "' )";
        String query2 = "INSERT INTO table_" + strType.toLowerCase() + " "
        + "(nameF,nameM,nameL,gender,birthDate,phoneNumber,type) "
        + "VALUES (?,?,?,?,?,?,?)";
        String query3 = "SELECT id FROM table_" + strType.toLowerCase() + ""
                + " WHERE nameF='" +strNameF+ "' AND nameM='" + strNameM + "'"
                + " AND nameL='" + strNameL + "'";
        st = connection.createStatement();
        st.executeQuery(query1);
        rs = st.getResultSet(); rs.next();
        if(!rs.getBoolean(1)){
            pst = connection.prepareStatement(query2);
            pst.setString(1,strNameF);
            pst.setString(2,strNameM);
            pst.setString(3,strNameL);
            pst.setString(4,strGender);
            pst.setDate(5,getBirthDateSQL());
            pst.setString(6,strPhone);
            pst.setString(7,strType);
            pst.executeUpdate();
            st.executeQuery(query3);
            rs = st.getResultSet(); rs.next();
            iId = rs.getInt("id");
        }
        else{
            throw new DuplicateAccountException();
        }
}
    public void updateDb(Connection connection) throws SQLException, DbUpdateFailedException{
        Statement st;
        PreparedStatement pst;
        String query2 = "UPDATE table_" +strType.toLowerCase() + " "
        + " SET nameF = ? , nameM = ? , nameL = ? , gender = ? , birthDate = ? , phoneNumber = ?"
        + " WHERE id = ?";
        pst = connection.prepareStatement(query2);
        pst.setString(1,strNameF);
        pst.setString(2,strNameM);
        pst.setString(3,strNameL);
        pst.setString(4,strGender);
        pst.setDate(5,this.getBirthDateSQL());
        pst.setString(6,strPhone);
        pst.setInt(7,iId);
        Integer n = pst.executeUpdate();
        if(n<=0) throw new DbUpdateFailedException();
    }
}
