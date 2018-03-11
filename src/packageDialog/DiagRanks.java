/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageDialog;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import packageCore.Connector;
import packageCore.Parser;
import packageCore.PersonInfo;

/**
 *
 * @author Retaliation
 */
public class DiagRanks extends javax.swing.JDialog {
  private int id;
  /**
   * Creates new form DiagRanks
   * @param parent
   * @param modal
   * @param id
   */
  public DiagRanks(java.awt.Frame parent, boolean modal,int id) {
    super(parent, modal);
    initComponents();
    this.setLocationRelativeTo(parent);
    this.id=id;
  }
  
  private void setUpCbSec(){
    Connection con;
     PreparedStatement ps1;
     ResultSet rs1;
     String q1;
     String secName;
     int iSecId;
     cbSelect.addItem("Select");
      try {
          con = new Connector().getConnection();
          q1 = "SELECT sectionId,sectionName FROM table_sec WHERE adviserid = ?";
          ps1 = con.prepareStatement(q1);
          ps1.setInt(1,id);
          ps1.executeQuery();
          rs1 = ps1.getResultSet();
          while(rs1.next()){
              secName = rs1.getString("sectionName");
              iSecId = rs1.getInt("sectionId");
              cbSelect.addItem(iSecId + ":" + secName);
          }
      } catch (ClassNotFoundException | SQLException ex) {
          System.out.println(ex.getMessage());
        }
  }
  private int getLimit(){
    switch(cbHow.getSelectedIndex()){
      case 0: return 3;
      case 1: return 10;
      case 2: return 20;
      case 3: return -1;
      default: return -1;
    }
  }
  private void gradeLevelRank(int ig){
    Connection con;
    PreparedStatement ps1;
    ResultSet rs1,rs2;
    String q1;
    PersonInfo p = new PersonInfo();
    int limit = getLimit();
    int icn=0;
    String stSecName = "Unknown";
    DefaultTableModel dm = (DefaultTableModel) tbRanks.getModel();
    try {
      con = new Connector().getConnection(); 
      q1 = "SELECT studentId, genAve FROM table_"+ig+" WHERE gradeLevel=? ORDER BY genAve DESC";
      if(limit!=-1){
        q1 += " LIMIT "+limit;
      }
      ps1 = con.prepareStatement(q1);
      ps1.setInt(1,ig);
      ps1.executeQuery();
      rs1 = ps1.getResultSet();
      dm.getDataVector().removeAllElements();
      dm.fireTableDataChanged();
      while(rs1.next()){
        icn++;
        p.fetchData(rs1.getInt("studentId"),"Student",con);
        q1 = "SELECT sectionName FROM table_sec WHERE sectionId = "
           + "(SELECT sectionId FROM table_student WHERE id=? LIMIT 1)";
        ps1 = con.prepareStatement(q1);
        ps1.setInt(1,p.getId());
        ps1.executeQuery();
        rs2 = ps1.getResultSet();
        if(rs2.next()){
          dm.addRow(new Object[]{icn,p.getIdString(6),p.getFullName(),ig,rs2.getString("sectionName"),rs1.getDouble("genAve")});
        }
        else {
          System.out.println("Error resultset: " + ps1.toString());
        }
        dm.fireTableDataChanged();
      }
    } catch (ClassNotFoundException ec) {
      System.out.println("Connector error at gradeLevelRank:" + ec.getMessage());
    } catch (SQLException es) {
      System.out.println("Sql error at gradeLevelRank: " + es.getMessage());
    }
    
  }
  private void secRank(String in){
    //where in = "sectionId:sectionName"
    Parser p = new Parser();
    Object[] ob = p.parse1(in);
    
    int sectionId = (int) ob[0];
    int ir = 0;
    int ig = 0;
    double iGa = 0;
    String sectionName = (String)ob[1];
    
    Connection con;
    PreparedStatement ps1;
    ResultSet rs1,rs2;
    String q1;
    PersonInfo  pi = new PersonInfo();
    DefaultTableModel dm = (DefaultTableModel) tbRanks.getModel();
    dm.getDataVector().removeAllElements();
    try {
      con = new Connector().getConnection();
      //fetch grade level;
      q1 = "SELECT gradeLevel FROM table_sec WHERE sectionId=?";
      ps1 = con.prepareStatement(q1);
      ps1.setInt(1,sectionId);
      rs1 = ps1.executeQuery();
      if(rs1.next()){
        ig = rs1.getInt("gradeLevel");
      }
      else {
        throw new SQLException("Invalid gradelevel.");
      }
      rs1.close();
      q1 = "SELECT studentId,genAve FROM table_"+ig+" WHERE sectionId=? ORDER BY genAve DESC";
      ps1 = con.prepareStatement(q1);
      ps1.setInt(1,sectionId);
      rs1 = ps1.executeQuery();
      while(rs1.next()){
        ir++;
        pi.fetchData(rs1.getInt("studentId"),"Student", con);
        dm.addColumn(new Object[]{ir,pi.getId(),pi.getFullName(),ig,ob[1],rs1.getDouble("genAve")});
      }
      dm.fireTableDataChanged();
    } catch (ClassNotFoundException ec) {
      System.out.println("Error at sectionRank: " + ec.getMessage());
    } catch (SQLException es) {
      JOptionPane.showMessageDialog(this,"SQL error happened.","",JOptionPane.ERROR_MESSAGE);
      System.out.println("Network error at sectionBank: " + es.getMessage());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
  }
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    btgRankType = new javax.swing.ButtonGroup();
    pnCover = new javax.swing.JPanel();
    pnOptions = new javax.swing.JPanel();
    cbSelect = new javax.swing.JComboBox();
    btnGo = new javax.swing.JButton();
    cbHow = new javax.swing.JComboBox();
    cbWhat = new javax.swing.JComboBox();
    jScrollPane1 = new javax.swing.JScrollPane();
    tbRanks = new javax.swing.JTable();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Student Ranking");

    pnCover.setBackground(new java.awt.Color(204, 255, 204));

    pnOptions.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    cbSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Grade 7", "Grade 8", "Grade 9", "Grade 10" }));
    cbSelect.setBorder(null);
    cbSelect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    btnGo.setText("Go");
    btnGo.setBorder(null);
    btnGo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnGo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnGoActionPerformed(evt);
      }
    });

    cbHow.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Top 3", "Top 10", "Top 20", "All Students" }));
    cbHow.setBorder(null);
    cbHow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    cbWhat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "By Grade Level", "By Section" }));
    cbWhat.setBorder(null);
    cbWhat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    cbWhat.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cbWhatActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout pnOptionsLayout = new javax.swing.GroupLayout(pnOptions);
    pnOptions.setLayout(pnOptionsLayout);
    pnOptionsLayout.setHorizontalGroup(
      pnOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(pnOptionsLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(cbWhat, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(cbSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(cbHow, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(btnGo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(155, Short.MAX_VALUE))
    );
    pnOptionsLayout.setVerticalGroup(
      pnOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(pnOptionsLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(pnOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(cbSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btnGo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(cbHow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(cbWhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(37, Short.MAX_VALUE))
    );

    tbRanks.setAutoCreateRowSorter(true);
    tbRanks.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
    tbRanks.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Rank", "Student Id", "Student Name", "Grade Level", "Section", "General Average"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
      };
      boolean[] canEdit = new boolean [] {
        false, false, false, false, false, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    tbRanks.getTableHeader().setReorderingAllowed(false);
    jScrollPane1.setViewportView(tbRanks);

    javax.swing.GroupLayout pnCoverLayout = new javax.swing.GroupLayout(pnCover);
    pnCover.setLayout(pnCoverLayout);
    pnCoverLayout.setHorizontalGroup(
      pnCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(pnOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addGroup(pnCoverLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1)
        .addContainerGap())
    );
    pnCoverLayout.setVerticalGroup(
      pnCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(pnCoverLayout.createSequentialGroup()
        .addComponent(pnOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(pnCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(pnCover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 0, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void btnGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoActionPerformed
    
    if(cbSelect.getSelectedIndex()==0){
          JOptionPane.showMessageDialog(this,"Please select grade level");
    }
    else{
      switch((String)cbWhat.getSelectedItem()){
        case "By Grade Level":
          gradeLevelRank(cbSelect.getSelectedIndex()+6);
          break;
        case "By Section":
          JOptionPane.showMessageDialog(this,"Not supported yet.");
          break;
      }
    }
    
  }//GEN-LAST:event_btnGoActionPerformed

  private void cbWhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbWhatActionPerformed
     cbSelect.removeAllItems();
     //ComboBoxModel cbm = cbSelect.getModel();
    switch((String)cbWhat.getSelectedItem()){
      case "By Grade Level":
       cbSelect.addItem("Select");
       for(int k=7;k<=10;k++){
         cbSelect.addItem("Grade "+k);
       }
       break;
      case "By Section":
        setUpCbSec();
        break;
    }
  }//GEN-LAST:event_cbWhatActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.ButtonGroup btgRankType;
  private javax.swing.JButton btnGo;
  private javax.swing.JComboBox cbHow;
  private javax.swing.JComboBox cbSelect;
  private javax.swing.JComboBox cbWhat;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JPanel pnCover;
  private javax.swing.JPanel pnOptions;
  private javax.swing.JTable tbRanks;
  // End of variables declaration//GEN-END:variables
}
