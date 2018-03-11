/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageDialog;

import packageCore.Setting;
public class DiagSettings extends javax.swing.JDialog {
  Setting sets;
  /**
   * Creates new form DiagSettings
   * @param parent
   * @param modal
   */
  public DiagSettings(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    sets = new Setting();
    initComponents();
    txfDomain.setText(sets.getDomain());
    txfPort.setText(sets.getPort());
    txfDbName.setText(sets.getDbName());
    txfDbUser.setText(sets.getDbUser());
    tpfPassword.setText(sets.getDbPassword());
    txfDriver.setText(sets.getDriver());
    this.setLocationRelativeTo(parent);
  }


  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    pnCover = new javax.swing.JPanel();
    txfDomain = new javax.swing.JTextField();
    txfPort = new javax.swing.JTextField();
    txfDbName = new javax.swing.JTextField();
    txfDbUser = new javax.swing.JTextField();
    tpfPassword = new javax.swing.JPasswordField();
    lb1 = new javax.swing.JLabel();
    lb2 = new javax.swing.JLabel();
    lb3 = new javax.swing.JLabel();
    lb4 = new javax.swing.JLabel();
    lb5 = new javax.swing.JLabel();
    txfDriver = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    btnDefs = new javax.swing.JButton();
    btnSave = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    pnCover.setBackground(new java.awt.Color(0, 0, 0));

    txfDomain.setText("DOMAIN");

    txfPort.setText("PORT");

    txfDbName.setText("DBNAME");

    txfDbUser.setText("DBUsername");

    lb1.setForeground(new java.awt.Color(255, 255, 255));
    lb1.setText("Domain:");

    lb2.setForeground(new java.awt.Color(255, 255, 255));
    lb2.setText("Port Number:");

    lb3.setForeground(new java.awt.Color(255, 255, 255));
    lb3.setText("Database Name:");

    lb4.setForeground(new java.awt.Color(255, 255, 255));
    lb4.setText("Database User:");

    lb5.setForeground(new java.awt.Color(255, 255, 255));
    lb5.setText("Database Password:");

    txfDriver.setText("Custom Driver");

    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setText("Edit Driver");

    btnDefs.setText("Reset to Defaults");
    btnDefs.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnDefsActionPerformed(evt);
      }
    });

    btnSave.setText("Save Settings");
    btnSave.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnSaveActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout pnCoverLayout = new javax.swing.GroupLayout(pnCover);
    pnCover.setLayout(pnCoverLayout);
    pnCoverLayout.setHorizontalGroup(
      pnCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(pnCoverLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(pnCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(txfDriver, javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCoverLayout.createSequentialGroup()
            .addGroup(pnCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(lb1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(lb2)
              .addComponent(lb3)
              .addComponent(lb4)
              .addComponent(lb5))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(pnCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(txfDomain)
              .addComponent(txfPort)
              .addComponent(txfDbName)
              .addComponent(txfDbUser)
              .addComponent(tpfPassword, javax.swing.GroupLayout.Alignment.TRAILING)))
          .addGroup(pnCoverLayout.createSequentialGroup()
            .addGroup(pnCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel1)
              .addComponent(btnDefs))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
            .addComponent(btnSave)))
        .addContainerGap())
    );
    pnCoverLayout.setVerticalGroup(
      pnCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(pnCoverLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(pnCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(txfDomain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(lb1))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(pnCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(txfPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(lb2))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(pnCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(txfDbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(lb3))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(pnCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(txfDbUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(lb4))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(pnCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(tpfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(lb5))
        .addGap(25, 25, 25)
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(txfDriver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addGroup(pnCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btnDefs, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addComponent(pnCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(pnCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
    sets.setDomain(txfDomain.getText());
    sets.setPort(txfPort.getText());
    sets.setDbName(txfDbName.getText());
    sets.setDbUserName(txfDbUser.getText());
    sets.setDbPassword(new String(tpfPassword.getPassword()));
    sets.setDriver(txfDriver.getText());
    sets.toFileCon();
    this.dispose();
  }//GEN-LAST:event_btnSaveActionPerformed

  private void btnDefsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDefsActionPerformed
    sets.setDriver("com.mysql.jdbc.Driver");
    sets.setDomain("//localhost");
    sets.setPort("3306");
    sets.setDbName("db_Schoolbook");
    sets.setDbUserName("root");
    sets.setDbPassword("pathofLight");
    txfDriver.setText(sets.getDriver());
    txfDomain.setText(sets.getDomain());
    txfPort.setText(sets.getPort());
    txfDbName.setText(sets.getDbName());
    txfDbUser.setText(sets.getDbUser());
    tpfPassword.setText(sets.getDbPassword());
  }//GEN-LAST:event_btnDefsActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnDefs;
  private javax.swing.JButton btnSave;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel lb1;
  private javax.swing.JLabel lb2;
  private javax.swing.JLabel lb3;
  private javax.swing.JLabel lb4;
  private javax.swing.JLabel lb5;
  private javax.swing.JPanel pnCover;
  private javax.swing.JPasswordField tpfPassword;
  private javax.swing.JTextField txfDbName;
  private javax.swing.JTextField txfDbUser;
  private javax.swing.JTextField txfDomain;
  private javax.swing.JTextField txfDriver;
  private javax.swing.JTextField txfPort;
  // End of variables declaration//GEN-END:variables
}
