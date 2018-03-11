/*
Notes
Passwords should not be put on Strings
*/

package packageFrames;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import packageCore.*;
import packageDialog.DiagSettings;
public class FrameIdLog extends javax.swing.JFrame {
    //Custom variables
    private ResourceBundle idLogBundle;
    private final ConsoleSelector selector;
    private final Core core;
    private boolean bPassed;
    private String strConType;
    private String strVerifiedId;
    private PersonInfo piBasicInfo;
    
    /**
     * Creates new form frameIdLog
     * @param selector
     * @param core
     */
    public FrameIdLog(ConsoleSelector selector, Core core) {
       initComponents();
       this.setLocationRelativeTo(null);//Center of screen
       this.setVisible(true);
       bPassed = false;
       this.selector=selector;
       this.core = core;
    }
    public void verify(String id, String type){
        Connection con;
        PreparedStatement ps1;
        ResultSet rs1;
        String q1;
        Parser ip = new Parser();
        String t = type.toLowerCase();
        try {
          con = new Connector().getConnection();
          q1 = "SELECT EXISTS(SELECT * FROM table_"+t+" WHERE id = '"+ip.getInt(id)+"')";
          ps1 = con.prepareStatement(q1);
          ps1.executeQuery();
          rs1 = ps1.getResultSet();
          rs1.next();
          if(rs1.getBoolean(1)){
            piBasicInfo = new PersonInfo();
            piBasicInfo.fetchData(ip.getInt(id),type,con);
            JOptionPane.showMessageDialog(this,
            "Account found and verified!","Status",JOptionPane.INFORMATION_MESSAGE);
            labelIdLogStatus.setText("Your Name: " + piBasicInfo.getName());
            switch (type) {
                case "Student":
                    idLogButtonOk.setEnabled(true);
                    break;
                case "Teacher":
                case "Admin":
                    inputPassword.setEditable(true);
                    idLogButtonOk.setEnabled(true);
                    break;
            }
          }
          else {
              JOptionPane.showMessageDialog(this,
              "Sorry, that account was not found.","Not Found",JOptionPane.INFORMATION_MESSAGE);
              idLogButtonOk.setEnabled(false);
              inputPassword.setEditable(false);
              labelIdLogStatus.setText("Status: ");
          }
        }
        catch(NumberFormatException|NullPointerException e){
            JOptionPane.showMessageDialog(this,
            "Please check your input","Oops!",JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }catch(SQLException | ClassNotFoundException ex){
          JOptionPane.showMessageDialog(this,
            "Network error. Please inform administrators","Oops!",JOptionPane.ERROR_MESSAGE);
          System.out.println(ex.getMessage());
        }
    } 
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    panelIdLogCover = new javax.swing.JPanel();
    textFieldIdLog = new javax.swing.JTextField();
    idLogButtonCancel = new javax.swing.JButton();
    idLogCombo = new javax.swing.JComboBox();
    labelIdLog1 = new javax.swing.JLabel();
    idLogButtonOk = new javax.swing.JButton();
    labelIdLogPassword = new javax.swing.JLabel();
    labelIdLogStatus = new javax.swing.JLabel();
    labelIdNum = new javax.swing.JLabel();
    buttonVerify = new javax.swing.JButton();
    inputPassword = new javax.swing.JPasswordField();
    btnConfig = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Log In");
    setResizable(false);

    panelIdLogCover.setBackground(new java.awt.Color(204, 204, 255));

    textFieldIdLog.setColumns(30);
    textFieldIdLog.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
    textFieldIdLog.setToolTipText("");
    textFieldIdLog.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    textFieldIdLog.setCaretColor(new java.awt.Color(153, 153, 153));
    textFieldIdLog.setMinimumSize(new java.awt.Dimension(6, 15));

    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("packageResourceBundles/bundleTextsEn"); // NOI18N
    idLogButtonCancel.setText(bundle.getString("idLogButtonCancel")); // NOI18N
    idLogButtonCancel.setBorder(null);
    idLogButtonCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    idLogButtonCancel.setFocusable(false);
    idLogButtonCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        idLogButtonCancelActionPerformed(evt);
      }
    });

    idLogCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Student", "Teacher", "Admin" }));
    idLogCombo.setBorder(null);
    idLogCombo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    idLogCombo.setFocusable(false);
    idLogCombo.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(java.awt.event.ItemEvent evt) {
        idLogComboItemStateChanged(evt);
      }
    });

    labelIdLog1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    labelIdLog1.setForeground(new java.awt.Color(0, 102, 0));
    labelIdLog1.setText(bundle.getString("idLogLabelAcType")); // NOI18N

    idLogButtonOk.setText(bundle.getString("idLogButtonOK")); // NOI18N
    idLogButtonOk.setBorder(null);
    idLogButtonOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    idLogButtonOk.setEnabled(false);
    idLogButtonOk.setFocusable(false);
    idLogButtonOk.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        idLogButtonOkActionPerformed(evt);
      }
    });

    labelIdLogPassword.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    labelIdLogPassword.setForeground(new java.awt.Color(0, 102, 0));
    labelIdLogPassword.setText(bundle.getString("idLogLabelInID")); // NOI18N

    labelIdLogStatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    labelIdLogStatus.setForeground(new java.awt.Color(0, 102, 0));
    labelIdLogStatus.setText("Status: ");

    labelIdNum.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    labelIdNum.setForeground(new java.awt.Color(0, 102, 0));
    labelIdNum.setText("ID Number:");

    buttonVerify.setText("Verify");
    buttonVerify.setBorder(null);
    buttonVerify.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    buttonVerify.setFocusable(false);
    buttonVerify.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonVerifyActionPerformed(evt);
      }
    });

    inputPassword.setEditable(false);
    inputPassword.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
    inputPassword.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    btnConfig.setBackground(new java.awt.Color(204, 204, 255));
    btnConfig.setText("...");
    btnConfig.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnConfigActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panelIdLogCoverLayout = new javax.swing.GroupLayout(panelIdLogCover);
    panelIdLogCover.setLayout(panelIdLogCoverLayout);
    panelIdLogCoverLayout.setHorizontalGroup(
      panelIdLogCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelIdLogCoverLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelIdLogCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelIdLogCoverLayout.createSequentialGroup()
            .addComponent(btnConfig)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(idLogButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(idLogButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(12, 12, 12))
          .addGroup(panelIdLogCoverLayout.createSequentialGroup()
            .addGroup(panelIdLogCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(labelIdLogStatus)
              .addGroup(panelIdLogCoverLayout.createSequentialGroup()
                .addGroup(panelIdLogCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(labelIdLog1)
                  .addComponent(labelIdNum)
                  .addComponent(labelIdLogPassword))
                .addGap(26, 26, 26)
                .addGroup(panelIdLogCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(textFieldIdLog, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addGroup(panelIdLogCoverLayout.createSequentialGroup()
                    .addComponent(idLogCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(buttonVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
    );
    panelIdLogCoverLayout.setVerticalGroup(
      panelIdLogCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelIdLogCoverLayout.createSequentialGroup()
        .addGap(18, 18, 18)
        .addGroup(panelIdLogCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(textFieldIdLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(labelIdNum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addGroup(panelIdLogCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(buttonVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(idLogCombo)
          .addComponent(labelIdLog1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelIdLogStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelIdLogCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelIdLogPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(67, 67, 67)
        .addGroup(panelIdLogCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(panelIdLogCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(idLogButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(idLogButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(btnConfig))
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(panelIdLogCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(panelIdLogCover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 0, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void idLogButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idLogButtonCancelActionPerformed
        core.setRepeat(false);
        this.dispose();
    }//GEN-LAST:event_idLogButtonCancelActionPerformed

    private void idLogButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idLogButtonOkActionPerformed

        Connection con;
        PreparedStatement ps1;
        ResultSet rs1;
        char[] csInput,csPassword;
        csInput = inputPassword.getPassword();
        String userInput = new String(csInput); //change this later
        String tablename = "table_"+ (piBasicInfo.getType()).toLowerCase();
        String querry = "SELECT password FROM " + tablename + " WHERE id = '" + piBasicInfo.getId() +"'";
        try {
          con = new Connector().getConnection();
          if((piBasicInfo.getType()).equalsIgnoreCase("Student")){
              bPassed=true;
          }
          else {
            ps1 = con.prepareStatement(querry);
            ps1.executeQuery();
            rs1 = ps1.getResultSet();
            rs1.next();
            if(userInput.equals(rs1.getString(1))){
                  bPassed = true;
            }
            else {
                 JOptionPane.showMessageDialog(this,"Sorry, wrong password. ","Password Invalid",JOptionPane.ERROR_MESSAGE);
                 labelIdLogStatus.setText("Status: Account valid. Password invalid.");
            } 
          }
          if(bPassed){
              selector.startConsole(piBasicInfo);
              this.dispose();
          }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this,"Network error, please see admnistrators.","Oops!",JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_idLogButtonOkActionPerformed

    private void buttonVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerifyActionPerformed
      verify(textFieldIdLog.getText(),(String) idLogCombo.getSelectedItem());
    }//GEN-LAST:event_buttonVerifyActionPerformed

    private void idLogComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_idLogComboItemStateChanged
        String type = (String) idLogCombo.getSelectedItem();
        if(type.equals("Student")){
            inputPassword.setEditable(false);
        }
    }//GEN-LAST:event_idLogComboItemStateChanged

  private void btnConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigActionPerformed
    String st;
    JPasswordField jp = new JPasswordField();
    JOptionPane.showMessageDialog(this, jp);
    st = new String(jp.getPassword());
    try {
      if(!st.isEmpty()&&st.equals("admin")){
       DiagSettings ds = new DiagSettings(this,true);
       ds.setVisible(true);
      }
    }catch(NullPointerException e){
      // do nothing       
    }
  }//GEN-LAST:event_btnConfigActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnConfig;
  private javax.swing.JButton buttonVerify;
  private javax.swing.JButton idLogButtonCancel;
  private javax.swing.JButton idLogButtonOk;
  private javax.swing.JComboBox idLogCombo;
  private javax.swing.JPasswordField inputPassword;
  private javax.swing.JLabel labelIdLog1;
  private javax.swing.JLabel labelIdLogPassword;
  private javax.swing.JLabel labelIdLogStatus;
  private javax.swing.JLabel labelIdNum;
  protected javax.swing.JPanel panelIdLogCover;
  private javax.swing.JTextField textFieldIdLog;
  // End of variables declaration//GEN-END:variables

//CUSTOM SIDE
    


}


