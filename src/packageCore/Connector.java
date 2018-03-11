/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageCore;
import java.sql.*;
public class Connector {
    Connection connection;
    //ResourceBundle rbTexts;
    Setting sets;
    public Connector() throws ClassNotFoundException, SQLException{
      sets = new Setting();
        //rbTexts = ResourceBundle.getBundle("packageResourceBundles/bundleTextsEn");
        Class.forName(sets.getDriver());
        connection = DriverManager.getConnection(
                "jdbc:mysql:" + sets.getServer(false),
                sets.getDbUser(),
                sets.getDbPassword());
    }
    //sample getConnection arguments
    // ---- jdbc:mysql://localhost:3306/db_Schoolbook?useSSL=false,
    // ---- root
    // ---- pathofLight
    public Connection getConnection(){
        return connection;
    }
    public void closeConnecotr() throws SQLException{
        connection.close();
    }
}
