/*
NOTE: next step: student rankings
*/
package packageFrames;
import javax.swing.JFrame;
import packageCore.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import packageDialog.*;

public class FrameTeacher extends javax.swing.JFrame {
    private final Core cycle;
    private final PersonInfo piBasicInfo;
    
    public FrameTeacher(Core cycle, PersonInfo basicInfo) {
        this.cycle=cycle;
        this.piBasicInfo=basicInfo;
        initComponents();
        setUpCbAdvSec();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
   final void setUpCbAdvSec(){
       Connection con;
       PreparedStatement ps1;
       ResultSet rs1;
       String q1;
       String secName;
       int iSecId;
        try {
            con = new Connector().getConnection();
            q1 = "SELECT sectionId,sectionName FROM table_sec WHERE adviserid = ?";
            ps1 = con.prepareStatement(q1);
            ps1.setInt(1,piBasicInfo.getId());
            ps1.executeQuery();
            rs1 = ps1.getResultSet();
            while(rs1.next()){
                secName = rs1.getString("sectionName");
                iSecId = rs1.getInt("sectionId");
                cbAdvSec.addItem(iSecId + ":" + secName);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error at setUpCbAdvSec: " + ex.getMessage());
        }
   } 
   private void getAdvisedSec(int id,String name){
       Connection con;
       PreparedStatement ps1,ps2;
       ResultSet rs1,rs2;
       String q1,q2;
       PersonInfo piInfo;
       DefaultTableModel dm;
       Parser ip = new Parser();
       boolean bPermit;
        try {
            con = new Connector().getConnection();
            q1 = "SELECT id,gradeLevel FROM table_student WHERE sectionId = ?";
                ps1 = con.prepareStatement(q1);
                ps1.setInt(1,id);
                ps1.executeQuery();
                rs1 = ps1.getResultSet();
                while(rs1.next()){
                    piInfo = new PersonInfo();
                    piInfo.fetchData(rs1.getInt("id"),"Student", con);
                    q2 = "SELECT permission FROM table_student WHERE id = ?";
                    ps2 = con.prepareStatement(q2);
                    ps2.setInt(1,piInfo.getId());
                    ps2.executeQuery();
                    rs2 = ps2.getResultSet();
                    rs2.next();
                    bPermit = rs2.getBoolean("permission");
                    q2 = "SELECT genAve FROM table_" + rs1.getInt("gradeLevel") + " WHERE studentId=?";
                    ps2 = con.prepareStatement(q2);
                    ps2.setInt(1,piInfo.getId());
                    ps2.executeQuery();
                    rs2 = ps2.getResultSet();
                    rs2.next();
                    dm = (DefaultTableModel) tbAdvisedSec.getModel();
                    dm.addRow(new Object[]{ip.getString(piInfo.getId(),6),
                                           piInfo.getName(),
                                           rs2.getDouble("genAve"),
                                           bPermit});
                }
                calcGenAve();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error at getAdvisedSec: " + ex.getMessage());
        }
   }
   private void calcGenAve(){
     DefaultTableModel dm = (DefaultTableModel) tbAdvisedSec.getModel();
     Connection con;
     PreparedStatement ps1;
     ResultSet rs1;
     ResultSetMetaData rsmd;
     ArrayList<Subject> subjects;
     GradeRecords gr;
     Subject stemp;
     String q1;
     Parser p = new Parser();
     int id;
     int ig;
     int isubs;
     int isuccess = 0;
     for(int k=0;k<dm.getRowCount();k++){
       subjects = new ArrayList<>();
       id = p.getInt((String)dm.getValueAt(k,0));
       ig = getGradeLevel(id);
       try {
         con = new Connector().getConnection();
         q1 = "SELECT * FROM table_"+ig+" WHERE studentId=? LIMIT 1";
         ps1 = con.prepareStatement(q1);
         ps1.setInt(1,id);
         ps1.executeQuery();
         rs1 = ps1.getResultSet();
         rsmd = rs1.getMetaData();
         isubs = rsmd.getColumnCount()-4;
         if(rs1.next()){
           for(int l=1;l<=isubs;l++){
             stemp = new Subject(l,ig,rs1.getString("sub_"+l));
             subjects.add(stemp);
           }
           gr = new GradeRecords(subjects,ig);
           dm.setValueAt(gr.getGenAve(),k,2);
           q1 = "UPDATE table_"+ig+" SET genAve=? WHERE studentId=?";
           ps1 = con.prepareStatement(q1);
           ps1.setDouble(1,gr.getGenAve());
           ps1.setInt(2,id);
           if(ps1.executeUpdate()>0){
             isuccess++;
           }
         }
         else {
           System.out.println("No resultset at:" + q1);
         }
         
       } catch (ClassNotFoundException ec) {
         System.out.println(ec.getMessage());
         JOptionPane.showMessageDialog(this,"Connector Error");
       } catch (SQLException es) {
         System.out.println(es.getMessage());
         JOptionPane.showMessageDialog(this,"Query Error");
       } 
     }
     JOptionPane.showMessageDialog(this,
          isuccess+" out of "+dm.getRowCount()+" students successfully updated their general average",
          "",JOptionPane.INFORMATION_MESSAGE);
   }
   private int getGradeLevel(int id){
     Connection con;
     PreparedStatement ps;
     ResultSet rs;
     String q1;
     
      try {
        con = new Connector().getConnection();
        q1 = "SELECT gradeLevel FROM table_student WHERE id=? LIMIT 1";
        ps = con.prepareStatement(q1);
        ps.setInt(1, id);
        ps.executeQuery();
        rs = ps.getResultSet();
        if(rs.next()){
          return rs.getInt("gradeLevel");
        }
        else {
          JOptionPane.showMessageDialog(this,"Student's grade level not found!");
        }
      } catch (ClassNotFoundException ec) {
        JOptionPane.showMessageDialog(this,"Connector error.");
        System.out.println(ec.getMessage());
      } catch (SQLException es) {
        JOptionPane.showMessageDialog(this,"Query error");
        System.out.println(es.getMessage());
      }
     
     
     return 0;
   }
     
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    panelStudentCover = new javax.swing.JPanel();
    jPanel5 = new javax.swing.JPanel();
    jPanel2 = new javax.swing.JPanel();
    lbTitle = new javax.swing.JLabel();
    txfInfoName = new javax.swing.JTextField();
    txfInfoId = new javax.swing.JTextField();
    txfInfoGender = new javax.swing.JTextField();
    txfInfoAge = new javax.swing.JTextField();
    txfInfoPhone = new javax.swing.JTextField();
    buttonLogOut = new javax.swing.JButton();
    btnChangePassword = new javax.swing.JButton();
    jPanel1 = new javax.swing.JPanel();
    tpnAdvisedSec = new javax.swing.JTabbedPane();
    pnAdvised1 = new javax.swing.JPanel();
    jScrollPane2 = new javax.swing.JScrollPane();
    tbAdvisedSec = new javax.swing.JTable();
    btnUpPermit = new javax.swing.JButton();
    btnMLS = new javax.swing.JButton();
    cbAdvSec = new javax.swing.JComboBox();
    btnRanks = new javax.swing.JButton();
    btnUpGen = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("packageResourceBundles/bundleTextsEn"); // NOI18N
    setTitle(bundle.getString("textTeacherFrameTitle")); // NOI18N
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowOpened(java.awt.event.WindowEvent evt) {
        formWindowOpened(evt);
      }
    });

    panelStudentCover.setBackground(new java.awt.Color(255, 255, 255));

    jPanel5.setBackground(new java.awt.Color(0, 204, 0));
    jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jPanel2.setBackground(new java.awt.Color(0, 153, 0));
    jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    lbTitle.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
    lbTitle.setForeground(new java.awt.Color(255, 255, 255));
    lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lbTitle.setText("Schoolbook Teacher");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(lbTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
    );

    txfInfoName.setEditable(false);
    txfInfoName.setBackground(new java.awt.Color(204, 255, 204));
    txfInfoName.setText("Name: " + piBasicInfo.getFullName());
    txfInfoName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    txfInfoName.setMargin(new java.awt.Insets(2, 4, 2, 2));

    txfInfoId.setEditable(false);
    txfInfoId.setBackground(new java.awt.Color(204, 255, 204));
    txfInfoId.setText("ID: " + piBasicInfo.getIdString(6)
    );
    txfInfoId.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    txfInfoId.setMargin(new java.awt.Insets(2, 4, 2, 2));

    txfInfoGender.setEditable(false);
    txfInfoGender.setBackground(new java.awt.Color(204, 255, 204));
    txfInfoGender.setText("Gender: " + piBasicInfo.getGender());
    txfInfoGender.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    txfInfoGender.setMargin(new java.awt.Insets(2, 4, 2, 2));

    txfInfoAge.setEditable(false);
    txfInfoAge.setBackground(new java.awt.Color(204, 255, 204));
    txfInfoAge.setText("Age: " + piBasicInfo.getAge());
    txfInfoAge.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    txfInfoAge.setMargin(new java.awt.Insets(2, 4, 2, 2));

    txfInfoPhone.setEditable(false);
    txfInfoPhone.setBackground(new java.awt.Color(204, 255, 204));
    txfInfoPhone.setText("Phone: " + piBasicInfo.getPhone());
    txfInfoPhone.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    txfInfoPhone.setMargin(new java.awt.Insets(2, 4, 2, 2));

    buttonLogOut.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
    buttonLogOut.setForeground(new java.awt.Color(0, 102, 0));
    buttonLogOut.setText("Log Out");
    buttonLogOut.setBorder(null);
    buttonLogOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    buttonLogOut.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonLogOutActionPerformed(evt);
      }
    });

    btnChangePassword.setText("Change Password");
    btnChangePassword.setBorder(null);
    btnChangePassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnChangePassword.setFocusable(false);
    btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnChangePasswordActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel5Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(txfInfoName)
          .addComponent(txfInfoId)
          .addGroup(jPanel5Layout.createSequentialGroup()
            .addComponent(txfInfoGender, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txfInfoAge))
          .addGroup(jPanel5Layout.createSequentialGroup()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(btnChangePassword, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
              .addComponent(buttonLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(0, 0, Short.MAX_VALUE))
          .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(txfInfoPhone))
        .addContainerGap())
    );
    jPanel5Layout.setVerticalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel5Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(45, 45, 45)
        .addComponent(txfInfoName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(txfInfoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(txfInfoGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(txfInfoAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(txfInfoPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(btnChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
        .addComponent(buttonLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    jPanel1.setBackground(new java.awt.Color(204, 255, 204));
    jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    pnAdvised1.setBackground(new java.awt.Color(255, 255, 255));

    tbAdvisedSec.setAutoCreateRowSorter(true);
    tbAdvisedSec.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    tbAdvisedSec.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Student Id", "Student Name", "General Average", "Permission"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Boolean.class
      };
      boolean[] canEdit = new boolean [] {
        false, false, false, true
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    tbAdvisedSec.getTableHeader().setReorderingAllowed(false);
    jScrollPane2.setViewportView(tbAdvisedSec);

    javax.swing.GroupLayout pnAdvised1Layout = new javax.swing.GroupLayout(pnAdvised1);
    pnAdvised1.setLayout(pnAdvised1Layout);
    pnAdvised1Layout.setHorizontalGroup(
      pnAdvised1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(pnAdvised1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
        .addContainerGap())
    );
    pnAdvised1Layout.setVerticalGroup(
      pnAdvised1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(pnAdvised1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
        .addContainerGap())
    );

    tpnAdvisedSec.addTab("Advised: ", pnAdvised1);

    btnUpPermit.setText("Update Permissions");
    btnUpPermit.setBorder(null);
    btnUpPermit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnUpPermit.setEnabled(false);
    btnUpPermit.setPreferredSize(new java.awt.Dimension(114, 40));
    btnUpPermit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnUpPermitActionPerformed(evt);
      }
    });

    btnMLS.setText("Manage Subjects");
    btnMLS.setBorder(null);
    btnMLS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnMLS.setPreferredSize(new java.awt.Dimension(95, 40));
    btnMLS.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnMLSActionPerformed(evt);
      }
    });

    cbAdvSec.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Advised Section" }));
    cbAdvSec.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cbAdvSecActionPerformed(evt);
      }
    });

    btnRanks.setText("Rankings");
    btnRanks.setBorder(null);
    btnRanks.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnRanks.setPreferredSize(new java.awt.Dimension(53, 40));
    btnRanks.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnRanksActionPerformed(evt);
      }
    });

    btnUpGen.setText("Update General Average");
    btnUpGen.setBorder(null);
    btnUpGen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnUpGen.setPreferredSize(new java.awt.Dimension(134, 40));
    btnUpGen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnUpGenActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
              .addComponent(btnUpGen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
              .addComponent(btnUpPermit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(cbAdvSec, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMLS, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRanks, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
              .addComponent(tpnAdvisedSec))
            .addContainerGap())))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(cbAdvSec, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btnMLS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btnRanks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(tpnAdvisedSec, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(btnUpPermit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(btnUpGen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout panelStudentCoverLayout = new javax.swing.GroupLayout(panelStudentCover);
    panelStudentCover.setLayout(panelStudentCoverLayout);
    panelStudentCoverLayout.setHorizontalGroup(
      panelStudentCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelStudentCoverLayout.createSequentialGroup()
        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    panelStudentCoverLayout.setVerticalGroup(
      panelStudentCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(panelStudentCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(panelStudentCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void buttonLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogOutActionPerformed
        cycle.setRepeat(true);
        this.dispose();
    }//GEN-LAST:event_buttonLogOutActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void btnMLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMLSActionPerformed
        //call diagLoads parameters: teacher id
        DiagLoads dl = new DiagLoads(this,true,piBasicInfo.getId());
        dl.setVisible(true);
    }//GEN-LAST:event_btnMLSActionPerformed

    private void cbAdvSecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAdvSecActionPerformed
        Parser p = new Parser();
        String in = (String) cbAdvSec.getSelectedItem();
        DefaultTableModel dm = (DefaultTableModel) tbAdvisedSec.getModel();
        dm.getDataVector().removeAllElements();
        dm.fireTableDataChanged();
        switch(in){
            case "Select Advised Section":
                btnUpPermit.setEnabled(false);
                break;
            default:
                Object[] o = p.parse1((String)cbAdvSec.getSelectedItem());
                getAdvisedSec((int)o[0],(String)o[1]);
                btnUpPermit.setEnabled(true);
                break;
        }
    }//GEN-LAST:event_cbAdvSecActionPerformed

    private void btnUpPermitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpPermitActionPerformed
        DefaultTableModel dm = (DefaultTableModel) tbAdvisedSec.getModel();
        PreparedStatement ps1;
        String q1;
        Parser p = new Parser();
        try {
            Connection con = new Connector().getConnection();
            for(int k=0;k<dm.getRowCount();k++){
                q1 = "UPDATE table_student SET permission=? WHERE id=?";
                ps1 = con.prepareStatement(q1);
                ps1.setBoolean(1,(boolean)dm.getValueAt(k,3));
                ps1.setInt(2, p.getInt((String)dm.getValueAt(k,0)));
                ps1.executeUpdate();
            }
            JOptionPane.showMessageDialog(this,"Permission updated.","Status",JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Network Error at button Update: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnUpPermitActionPerformed

    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePasswordActionPerformed
      Connection con;
      PreparedStatement ps1;
      ResultSet rs1;
      String q1;
      String stInput;
      String stPass;
      try {
        con = new Connector().getConnection();
        stInput = JOptionPane.showInputDialog(this,"Enter old password.");
        if(!stInput.isEmpty()){
          q1 = "SELECT password FROM table_teacher WHERE id = ? LIMIT 1";
          ps1 = con.prepareStatement(q1);
          ps1.setInt(1,piBasicInfo.getId());
          ps1.executeQuery();
          rs1 = ps1.getResultSet();
          if(rs1.next()){
            if(stInput.equals(rs1.getString("password"))){
              stPass = JOptionPane.showInputDialog(this,"Enter new password.");
              if(!stPass.isEmpty()){
                q1 = "UPDATE table_teacher SET password = ? WHERE id=?";
                ps1 = con.prepareStatement(q1);
                ps1.setString(1, stPass);
                ps1.setInt(2,piBasicInfo.getId());
                String msg;
                if(ps1.executeUpdate()>0){
                  msg = "Password changed!";
                }
                else {
                  msg = "Sorry, password not changed.";
                }
                JOptionPane.showMessageDialog(this,msg);
              }
              else{
                  JOptionPane.showMessageDialog(this,"Empty password is not encouraged.");
              }
            } else {
                JOptionPane.showMessageDialog(this,"Sorry, password did not match.");
            }
          }
            else {
                throw new SQLException("No password fetched.");
            }
        } 
        else {
          JOptionPane.showMessageDialog(this,"No input","",JOptionPane.INFORMATION_MESSAGE);
        }

      } catch (ClassNotFoundException | SQLException ex) {
          JOptionPane.showMessageDialog(this,"Network Error","Sorry",JOptionPane.ERROR_MESSAGE);
          System.out.println("Error at btnChangePassword: " + ex.getMessage());
      } catch (NullPointerException e){
          JOptionPane.showMessageDialog(this,"Cancelled","",JOptionPane.INFORMATION_MESSAGE);
      }
    }//GEN-LAST:event_btnChangePasswordActionPerformed

  private void btnUpGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpGenActionPerformed
    calcGenAve();
  }//GEN-LAST:event_btnUpGenActionPerformed

  private void btnRanksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRanksActionPerformed
    DiagRanks dr = new DiagRanks(this,true,piBasicInfo.getId());
    dr.setVisible(true);
  }//GEN-LAST:event_btnRanksActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnChangePassword;
  private javax.swing.JButton btnMLS;
  private javax.swing.JButton btnRanks;
  private javax.swing.JButton btnUpGen;
  private javax.swing.JButton btnUpPermit;
  private javax.swing.JButton buttonLogOut;
  private javax.swing.JComboBox cbAdvSec;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel5;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JLabel lbTitle;
  private javax.swing.JPanel panelStudentCover;
  private javax.swing.JPanel pnAdvised1;
  private javax.swing.JTable tbAdvisedSec;
  private javax.swing.JTabbedPane tpnAdvisedSec;
  private javax.swing.JTextField txfInfoAge;
  private javax.swing.JTextField txfInfoGender;
  private javax.swing.JTextField txfInfoId;
  private javax.swing.JTextField txfInfoName;
  private javax.swing.JTextField txfInfoPhone;
  // End of variables declaration//GEN-END:variables
}
