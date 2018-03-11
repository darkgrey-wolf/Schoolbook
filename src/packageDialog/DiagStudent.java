
package packageDialog;

import java.sql.*;
import packageCore.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import packageExceptions.NoSubjectException;

public class DiagStudent extends javax.swing.JDialog {
    Connection connection;
    boolean bIsNewRecord;
    int iStudentId;
    /**
     * Creates new form diagStudentRecord
     * @param parent
     * @param modal
     * @param con
     * @param isNew
     * @param id
     * @throws java.sql.SQLException
     */
    public DiagStudent(java.awt.Frame parent, boolean modal,Connection con,boolean isNew, int id) throws SQLException {
        super(parent, modal);
        connection = con;
        bIsNewRecord = isNew;
        iStudentId = id;
        initComponents();
        this.setLocationRelativeTo(parent);
        setUpComboBox();
        if(bIsNewRecord){
            setUpRecordNew();
        }
        else if(!bIsNewRecord){
            getRecord();
        }
        this.setVisible(true);
    }
    private void setUpRecordNew() throws SQLException{
        PreparedStatement ps1,ps2;
        ResultSet rs1,rs2;
        String query1,query2;
        Integer gl = 7;
        DefaultTableModel mod;
        for(int k = 0;gl<=10;gl++,k++){
            query1 = "SELECT subjectName FROM table_sublist_";
            query2 = "SELECT COUNT(*) FROM table_sublist_";
            query1 += gl.toString();
            query2 += gl.toString();
            ps1 = connection.prepareStatement(query1);
            ps2 = connection.prepareStatement(query2);
            ps1.executeQuery();
            ps2.executeQuery();
            rs1 = ps1.getResultSet();
            rs2 = ps2.getResultSet();
            mod = getModel(gl);
            while(rs1.next()){
               mod.addRow(new Object[]{rs1.getString("subjectName"),0.0,0.0,0.0,0.0,0.0});
            }
        }  
    }
    private DefaultTableModel getModel(int gradeLevel){
        DefaultTableModel md;
        switch(gradeLevel){
            case 7: md = (DefaultTableModel) tableG7.getModel(); break;
            case 8: md = (DefaultTableModel) tableG8.getModel(); break;
            case 9: md = (DefaultTableModel) tableG9.getModel(); break;
            case 10: md = (DefaultTableModel) tableG10.getModel(); break;
            default: md = (DefaultTableModel) tableG7.getModel(); break;
        }
        return md;
    }
    private void getRecord() throws SQLException{
        //Q: does getresultset overrides previous results?
        PreparedStatement ps1,ps2,ps3,ps4;
        ResultSet rs1,rs2,rs3;
        String query1,query2,query3;
        DefaultTableModel mod;
        GradeRecords records;
        Subject subTemp;
        ArrayList<Subject> subjects;
        Integer gl = 7;
        for(int k=0;gl<=10;gl++,k++){
            ///
            subjects = new ArrayList<>();
            query1 = "SELECT * FROM table_"+gl+" WHERE studentId=" + iStudentId;
            ps1 = connection.prepareStatement(query1);
            ps1.executeQuery();
            rs1 = ps1.getResultSet();
            while(rs1.next()){
                for(int l=1;l<=10;l++){
                   subTemp = new Subject(l,gl,rs1.getString("sub_"+l));
                   subjects.add(subTemp); 
                }
            }
            records = new GradeRecords(subjects,gl);
            records.updateModel(getModel(gl));
        }
        query2 = "SELECT gradeLevel FROM table_student WHERE id = " + iStudentId;
        ps2 = connection.prepareStatement(query2);
        ps2.executeQuery();
        rs2 = ps2.getResultSet();
        while(rs2.next()){
            inComboGradeLevel.setSelectedIndex(rs2.getInt("gradeLevel")-7);
        }
        query2 = "SELECT gradeLevel FROM table_student WHERE id =?";
        ps4 = connection.prepareStatement(query2);
        ps4.setInt(1,iStudentId);
        ps4.executeQuery();
        rs2 = ps4.getResultSet();
        rs2.next();
        query3 = "SELECT sectionName FROM table_sec WHERE sectionId = "
                 + "(SELECT sectionId FROM table_" + rs2.getInt("gradeLevel") 
                 +" WHERE studentId = ?)";
        ps3 = connection.prepareStatement(query3);
        ps3.setInt(1,iStudentId);
        ps3.executeQuery();
        rs3 = ps3.getResultSet();
        while(rs3.next()){
            inComboSections.setSelectedItem(rs3.getString("sectionName"));
        }
        
    }
    private void setUpComboBox(){
        String query1 = "SELECT sectionName FROM table_sec WHERE gradeLevel = 7";
        PreparedStatement ps;
        ResultSet rs;
        String sample;
        try {
            ps = connection.prepareStatement(query1);
            ps.executeQuery();
            rs = ps.getResultSet();
            inComboSections.removeAllItems();
            while(rs.next()){
                sample = rs.getString("sectionName");
                inComboSections.addItem(sample);
            }
        } catch (SQLException ex) {
            System.out.println("Error at DiagStudent " + ex);
            //Logger.getLogger(DiagStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    panelCover = new javax.swing.JPanel();
    jPanel1 = new javax.swing.JPanel();
    inComboGradeLevel = new javax.swing.JComboBox();
    inComboSections = new javax.swing.JComboBox();
    buttonOk = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();
    jTabbedPane1 = new javax.swing.JTabbedPane();
    jPanel2 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    tableG7 = new javax.swing.JTable();
    jPanel3 = new javax.swing.JPanel();
    jScrollPane2 = new javax.swing.JScrollPane();
    tableG8 = new javax.swing.JTable();
    jPanel4 = new javax.swing.JPanel();
    jScrollPane3 = new javax.swing.JScrollPane();
    tableG9 = new javax.swing.JTable();
    jPanel5 = new javax.swing.JPanel();
    jScrollPane4 = new javax.swing.JScrollPane();
    tableG10 = new javax.swing.JTable();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    setTitle("Student Academic Record");

    panelCover.setBackground(new java.awt.Color(204, 204, 255));

    jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    inComboGradeLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Grade 7", "Grade 8", "Grade 9", "Grade 10" }));
    inComboGradeLevel.setBorder(null);
    inComboGradeLevel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        inComboGradeLevelActionPerformed(evt);
      }
    });

    inComboSections.setBorder(null);

    buttonOk.setText("OK");
    buttonOk.setBorder(null);
    buttonOk.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonOkActionPerformed(evt);
      }
    });

    jLabel1.setText("Current Status:");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(32, 32, 32)
        .addComponent(inComboGradeLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(inComboSections, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(inComboGradeLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(inComboSections, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jLabel1))
            .addGap(0, 0, Short.MAX_VALUE))
          .addComponent(buttonOk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );

    jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

    jPanel2.setBackground(new java.awt.Color(255, 255, 255));

    tableG7.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Subject Name", "1st Quarter", "2nd Quarter", "3rd Quarter", "4th Quarter", "Final Rating"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
      };
      boolean[] canEdit = new boolean [] {
        true, true, true, true, true, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    tableG7.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
    tableG7.getTableHeader().setReorderingAllowed(false);
    jScrollPane1.setViewportView(tableG7);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
        .addContainerGap())
    );

    jTabbedPane1.addTab("Grade 7", jPanel2);

    tableG8.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Subject Name", "1st Quarter", "2nd Quarter", "3rd Quarter", "4th Quarter", "Final Rating"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
      };
      boolean[] canEdit = new boolean [] {
        true, true, true, true, true, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    tableG8.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
    tableG8.getTableHeader().setReorderingAllowed(false);
    jScrollPane2.setViewportView(tableG8);

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel3Layout.setVerticalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
        .addContainerGap())
    );

    jTabbedPane1.addTab("Grade 8", jPanel3);

    tableG9.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Subject Name", "1st Quarter", "2nd Quarter", "3rd Quarter", "4th Quarter", "Final Rating"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
      };
      boolean[] canEdit = new boolean [] {
        true, true, true, true, true, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    tableG9.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
    tableG9.getTableHeader().setReorderingAllowed(false);
    jScrollPane3.setViewportView(tableG9);

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
      jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel4Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel4Layout.setVerticalGroup(
      jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel4Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
        .addContainerGap())
    );

    jTabbedPane1.addTab("Grade 9", jPanel4);

    tableG10.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Subject Name", "1st Quarter", "2nd Quarter", "3rd Quarter", "4th Quarter", "Final Rating"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
      };
      boolean[] canEdit = new boolean [] {
        true, true, true, true, true, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    tableG10.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
    tableG10.getTableHeader().setReorderingAllowed(false);
    jScrollPane4.setViewportView(tableG10);

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel5Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel5Layout.setVerticalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel5Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
        .addContainerGap())
    );

    jTabbedPane1.addTab("Grade 10", jPanel5);

    javax.swing.GroupLayout panelCoverLayout = new javax.swing.GroupLayout(panelCover);
    panelCover.setLayout(panelCoverLayout);
    panelCoverLayout.setHorizontalGroup(
      panelCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelCoverLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    panelCoverLayout.setVerticalGroup(
      panelCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelCoverLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(jTabbedPane1)
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(panelCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(panelCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void buttonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkActionPerformed
       PreparedStatement ps1,ps2;
       ResultSet rs1;
       String query1;
       Integer gl;
       DefaultTableModel md;
       GradeRecords records;
       int iSecId = 0;
       //Update section and grade level
       int gradeLevel = inComboGradeLevel.getSelectedIndex()+7;
       String secName = (String) inComboSections.getSelectedItem();
       try {
           query1 = "SELECT sectionId FROM table_sec WHERE sectionName=? AND gradeLevel=?";
           ps1 = connection.prepareStatement(query1);
           ps1.setString(1, secName);
           ps1.setInt(2,gradeLevel);
           ps1.executeQuery(); //request for sectionId from sectionName
           rs1 = ps1.getResultSet();
           if(rs1.next()){
               iSecId = rs1.getInt("sectionId");
           }
           
           query1 = "UPDATE table_student SET gradeLevel=?,sectionId=? WHERE id=?";
           ps1 = connection.prepareStatement(query1);
           ps1.setInt(1,gradeLevel);
           ps1.setInt(2,iSecId);
           ps1.setInt(3,iStudentId);
           ps1.executeUpdate(); //update student grade level
           
        //UPdate For each table in grade level
           if(bIsNewRecord){ //New and old records requires different queries
               for(gl=7;gl<=10;gl++){ //where 10 is the number of subjects
                   query1 = "INSERT INTO table_" + gl + " "
                   + "(studentId,gradeLevel,sectionId,sub_1,sub_2,sub_3,sub_4,sub_5,sub_6,sub_7,sub_8,sub_9,sub_10) "
                   + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                   ps2 = connection.prepareStatement(query1);
                   ps2.setInt(1,iStudentId);
                   ps2.setInt(2,gradeLevel);
                   if(gl==gradeLevel){
                     //only update sectionId on appropriate gradeLevel
                     ps2.setInt(3,iSecId);
                   }
                   else {
                     ps2.setInt(3,0);
                   }
                   md = getModel(gl);
                   records = new GradeRecords(md,gl);
                   for(int k=1;k<=10;k++){
                     ps2.setString(k+3,records.getSubject(k).toString());  
                   }
                   ps2.executeUpdate();
               }
           }
           else if(!bIsNewRecord){
               for(gl=7;gl<=10;gl++){
                   query1 = "UPDATE table_" + gl + " "
                   + "SET gradeLevel=?,sub_1=?,sub_2=?,sub_3=?,sub_4=?,sub_5=?,sub_6=?,sub_7=?,sub_8=?,sub_9=?,sub_10=? "
                   + "WHERE studentId=?";
                   ps2 = connection.prepareStatement(query1);
                   ps2.setInt(1,gradeLevel);
                   ps2.setInt(12,iStudentId);
                   md = getModel(gl);
                   records = new GradeRecords(md,gl);
                   for(int k=1;k<=10;k++){ //where 10 is the number of subjects
                     ps2.setString(k+1,records.getSubject(k).toString());  
                   }
                   ps2.executeUpdate();
                   //update sectionId on table_n where n is the current grade level
                   if(gl==gradeLevel){
                     query1 = "UPDATE table_"+gl+" SET sectionId=? WHERE studentId=?";
                     ps2 = connection.prepareStatement(query1);
                     ps2.setInt(1,iSecId);
                     ps2.setInt(2,iStudentId);
                     ps2.executeUpdate();
                   }
               }
           }
           JOptionPane.showMessageDialog(this,"Success!","Status",JOptionPane.INFORMATION_MESSAGE);
           this.dispose();
       } catch(SQLException | NoSubjectException e){
           System.out.println("Error: " + e);
           JOptionPane.showMessageDialog(this,"Failed!","Status",JOptionPane.ERROR_MESSAGE);
           this.dispose();
       }
    }//GEN-LAST:event_buttonOkActionPerformed

    private void inComboGradeLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inComboGradeLevelActionPerformed
        String query1 = "SELECT sectionName FROM table_sec WHERE gradeLevel = ?";
        PreparedStatement ps;
        ResultSet rs;
        String sample;
        try {
            ps = connection.prepareStatement(query1);
            ps.setInt(1,inComboGradeLevel.getSelectedIndex()+7);
            ps.executeQuery();
            rs = ps.getResultSet();
            inComboSections.removeAllItems();
            while(rs.next()){
                sample = rs.getString("sectionName");
                inComboSections.addItem(sample);
            }
        } catch (SQLException ex) {
            System.out.println("Error at DiagStudent " + ex);
            //Logger.getLogger(DiagStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_inComboGradeLevelActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonOk;
  private javax.swing.JComboBox inComboGradeLevel;
  private javax.swing.JComboBox inComboSections;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JPanel jPanel4;
  private javax.swing.JPanel jPanel5;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JScrollPane jScrollPane3;
  private javax.swing.JScrollPane jScrollPane4;
  private javax.swing.JTabbedPane jTabbedPane1;
  private javax.swing.JPanel panelCover;
  private javax.swing.JTable tableG10;
  private javax.swing.JTable tableG7;
  private javax.swing.JTable tableG8;
  private javax.swing.JTable tableG9;
  // End of variables declaration//GEN-END:variables
}
