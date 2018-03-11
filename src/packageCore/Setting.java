
package packageCore;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Setting {
  public final static int DRIVER = 0;
  public final static int DOMAIN = 1;
  public final static int PORT = 2;
  public final static int DBNAME = 3;
  public final static int DBUSERNAME = 4;
  public final static int DBPASSWORD = 5;
  private ArrayList<String> conSettings;
  
  public Setting(){
    conSettings = new ArrayList<>();
    try {
      FileReader fin = new FileReader("conf.txt");
      Scanner scan = new Scanner(fin);
      if(scan.hasNextLine()&&scan.nextLine().equals("Connection")){
        while(scan.hasNextLine()){
          conSettings.add(scan.nextLine());
        }
        int dif = 6 - conSettings.size();
        for(int k=0;k<dif;k++){
          conSettings.add("CORRUPTED");
        }
       // System.out.println("Con: " + conSettings.size());
      }
      else {
        for(int k=0;k<6;k++){
          conSettings.add("CORRUPTED");
        }
      }
      //System.out.println("Con: " + conSettings.size());
    } catch (FileNotFoundException ex) {
      System.out.println("File not found. Using default settings.");
      for(int k=0;k<6;k++){
        conSettings.add("NOT SET");
      }
      conSettings.set(DRIVER,"com.mysql.jdbc.Driver");
      conSettings.set(DOMAIN,"//localhost");
      conSettings.set(PORT,"3306");
      conSettings.set(DBNAME,"db_Schoolbook");
      conSettings.set(DBUSERNAME,"root");
      conSettings.set(DBPASSWORD,"pathofLight");
      //System.out.println("Con: " + conSettings.size());
      try {
        FileWriter fout = new FileWriter("conf.txt");
        fout.write("Connection\n");
        fout.write(conSettings.get(DRIVER)+"\n");
        fout.write(conSettings.get(DOMAIN)+"\n");
        fout.write(conSettings.get(PORT)+"\n");
        fout.write(conSettings.get(DBNAME)+"\n");
        fout.write(conSettings.get(DBUSERNAME)+"\n");
        fout.write(conSettings.get(DBPASSWORD)+"\n");
        fout.close();
      } catch (IOException ex1) {
        System.out.println("File writing failed." + ex1.getMessage());
      }
    }
    //System.out.println("Con: " + conSettings.size());
  }
  //sample getConnection arguments
    // ---- jdbc:mysql://localhost:3306/db_Schoolbook?useSSL=false,
    // ---- root
    // ---- pathofLight
  public String getServer(boolean b){
    //----> return //localhost:3306/db_Schoolbook?useSSL=false;
    String stSSL;
    if(b){
      stSSL = "true";
    }
    else {
      stSSL = "false";
    }
    return conSettings.get(DOMAIN)+":"+conSettings.get(PORT)+"/"+conSettings.get(DBNAME)+"?useSSL="+stSSL;
    //return stUrl + ":" + stPort + "/" + stDbName +"?useSSL="+stSSL;
  }
  public String getDriver(){
    // ---> return com.mysql.jdbc.Driver
    return conSettings.get(DRIVER);
  }
  public String getDbName(){
    return conSettings.get(DBNAME);
  }
  public String getDomain(){
    // ---> return //localhost
    return conSettings.get(DOMAIN);
  }
  public String getPort(){
    // ---> return 3306
    return conSettings.get(PORT);
  }
  public String getDbUser(){
    // ---> return db_DBName
    return conSettings.get(DBUSERNAME);
  }
  public String getDbPassword(){
    // ---> return 
    return conSettings.get(DBPASSWORD);
  }
  public void setDriver(String in){
    conSettings.set(DRIVER, in);
  }
  public void setDomain(String in){
    conSettings.set(DOMAIN, in);
  }
  public void setPort(String in){
    conSettings.set(PORT, in);
  }
  public void setDbName(String in){
    conSettings.set(DBNAME, in);
  }
  public void setDbUserName(String in){
    conSettings.set(DBUSERNAME, in);
  }
  public void setDbPassword(String in){
    conSettings.set(DBPASSWORD, in);
  }
  public final void toFileCon(){
    try {
      FileWriter fout = new FileWriter("conf.txt");
      fout.write("Connection\n");
      ListIterator li = conSettings.listIterator();
      while(li.hasNext()){
        fout.write((String)li.next() + "\n");
      }
      fout.close();
    } catch (IOException ex) {
     System.out.println("Error at toFileCon: " + ex.getMessage());
    }
  }
}
