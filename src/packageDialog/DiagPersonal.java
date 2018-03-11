
package packageDialog;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import packageCore.*;
import packageExceptions.DbUpdateFailedException;

/**
 *
 * @author Retaliation
 */
public class DiagPersonal extends javax.swing.JDialog {
  PersonInfo piManaged;
  Calendar c;
  FormHandler fh;
  /**
   * Creates new form DiagPersonal
   * @param parent
   * @param modal
   * @param p
   */
  public DiagPersonal(java.awt.Frame parent, boolean modal,PersonInfo p) {
    super(parent, modal);
    piManaged = p;
    c = Calendar.getInstance();
    c.setTime(p.getBirthDate());
    initComponents();
    this.setLocationRelativeTo(parent);
    //Date d = p.getBirthDate();
    fh = new FormHandler(txfNameF,txfNameL,txfNameM,txfPhone,txfDate,txfYear,cbGender,
                          cbMonth,cbType);
  }
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    pnCover = new javax.swing.JPanel();
    pn1 = new javax.swing.JPanel();
    txfNameF = new javax.swing.JTextField();
    txfNameL = new javax.swing.JTextField();
    txfNameM = new javax.swing.JTextField();
    cbGender = new javax.swing.JComboBox();
    txfPhone = new javax.swing.JTextField();
    txfYear = new javax.swing.JTextField();
    cbMonth = new javax.swing.JComboBox();
    txfDate = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    cbType = new javax.swing.JComboBox();
    btnSubmit = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Personal Information");

    pnCover.setBackground(new java.awt.Color(204, 255, 204));

    pn1.setBackground(new java.awt.Color(204, 204, 255));
    pn1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

    txfNameF.setText(piManaged.getFirstName());
    txfNameF.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    txfNameL.setText(piManaged.getLastName());
    txfNameL.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    txfNameM.setText(piManaged.getMiddleName());
    txfNameM.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    cbGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));
    cbGender.setSelectedItem(piManaged.getGender());
    cbGender.setBorder(null);
    cbGender.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    txfPhone.setText(piManaged.getPhone());
    txfPhone.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    txfYear.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txfYear.setText(""+c.get(Calendar.YEAR)
    );
    txfYear.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    cbMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
    cbMonth.setSelectedIndex(c.get(Calendar.MONTH)
    );
    cbMonth.setBorder(null);
    cbMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    txfDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txfDate.setText(""+c.get(Calendar.DAY_OF_MONTH));
    txfDate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    jLabel1.setText("First Name:");

    jLabel2.setText("Last Name:");

    jLabel3.setText("Middle Name:");

    jLabel4.setText("Phone Number:");

    jLabel5.setText("Birth Date:");

    jLabel6.setText("Type:");

    cbType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Student", "Teacher", "Admin" }));
    cbType.setBorder(null);
    cbType.setEnabled(false);

    javax.swing.GroupLayout pn1Layout = new javax.swing.GroupLayout(pn1);
    pn1.setLayout(pn1Layout);
    pn1Layout.setHorizontalGroup(
      pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(pn1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel1)
          .addComponent(jLabel2)
          .addComponent(jLabel3)
          .addComponent(jLabel4)
          .addComponent(jLabel5)
          .addComponent(jLabel6))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
            .addGroup(pn1Layout.createSequentialGroup()
              .addComponent(txfYear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(txfDate, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(txfPhone)
            .addComponent(txfNameM)
            .addComponent(txfNameL)
            .addComponent(txfNameF)
            .addComponent(cbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    pn1Layout.setVerticalGroup(
      pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(pn1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(txfNameF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel1))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(txfNameL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel2))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(txfNameM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel3))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(txfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel4))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(txfDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel5)
          .addComponent(txfYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel6)
          .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    btnSubmit.setText("Submit");
    btnSubmit.setBorder(null);
    btnSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnSubmit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnSubmitActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout pnCoverLayout = new javax.swing.GroupLayout(pnCover);
    pnCover.setLayout(pnCoverLayout);
    pnCoverLayout.setHorizontalGroup(
      pnCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(pnCoverLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(pn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCoverLayout.createSequentialGroup()
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );
    pnCoverLayout.setVerticalGroup(
      pnCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(pnCoverLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(pn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(pnCover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(pnCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
    Connection con;
    if(fh.isValid()){
      //get date;
      Calendar calTemp = Calendar.getInstance();
      c.set(new Integer(txfYear.getText()),cbMonth.getSelectedIndex(),new Integer(txfDate.getText()));
      Date d = c.getTime();
      piManaged.setData( txfNameF.getText(),
                         txfNameM.getText(),
                         txfNameL.getText(),
                         piManaged.getId(),
                         (String)cbGender.getSelectedItem(),
                         txfPhone.getText(),
                         "Student",
                         d);
      try {
        con = new Connector().getConnection();
        piManaged.updateDb(con);
        JOptionPane.showMessageDialog(this,"Update successfull!"
                + " Log-in again to see changes.","",JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
      } catch (ClassNotFoundException | SQLException ex) {
        JOptionPane.showMessageDialog(this,"Network Error","",JOptionPane.ERROR_MESSAGE);
        System.out.println("Error at btnSubmit: "+ex.getMessage());
      } catch (DbUpdateFailedException ex) {
        JOptionPane.showMessageDialog(this,"Update failed!","Oops!",JOptionPane.ERROR_MESSAGE);
        System.out.println(ex.getMessage());
      }
    }
    else {
      JOptionPane.showMessageDialog(this,fh.getMsg(),"Input Error",JOptionPane.ERROR_MESSAGE);
    }
  }//GEN-LAST:event_btnSubmitActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnSubmit;
  private javax.swing.JComboBox cbGender;
  private javax.swing.JComboBox cbMonth;
  private javax.swing.JComboBox cbType;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JPanel pn1;
  private javax.swing.JPanel pnCover;
  private javax.swing.JTextField txfDate;
  private javax.swing.JTextField txfNameF;
  private javax.swing.JTextField txfNameL;
  private javax.swing.JTextField txfNameM;
  private javax.swing.JTextField txfPhone;
  private javax.swing.JTextField txfYear;
  // End of variables declaration//GEN-END:variables
}
