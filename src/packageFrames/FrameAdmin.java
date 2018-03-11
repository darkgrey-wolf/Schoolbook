
package packageFrames;

import java.awt.*;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import packageCore.*;
import packageDialog.*;
import packageExceptions.*;
import java.awt.Toolkit;

/**
 *
 * @author Retaliation
 */
public class FrameAdmin extends javax.swing.JFrame {
    private final Core cycle;
    private final PersonInfo piBasicInfo;
    private PersonInfo piManaged;
    private FormHandler submitForm;
    private int iMode;// 0 for create, 1 for update, 2 for delete 3 for none
    /**
     * Creates new form FrameAdmin
     * @param cycle
     * @param basicInfo
     */
    public FrameAdmin(Core cycle, PersonInfo basicInfo) {
        piBasicInfo = basicInfo;
        piManaged = new PersonInfo();
        iMode = 0;
        this.cycle=cycle;
        initComponents();
        submitForm = new FormHandler(inputNameF,inputNameM,inputNameL,inputPhone,inTextDate,inBirthYear,
                                     selectGender,inComboMonth,inComboType);
        submitForm.setEditable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    //Custom Codes
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    panelAdminCover = new javax.swing.JPanel();
    adminTabbedPane = new javax.swing.JTabbedPane();
    panelAccount = new javax.swing.JPanel();
    panelAccountsub1 = new javax.swing.JPanel();
    labelNameF = new javax.swing.JLabel();
    inputNameF = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    inputNameL = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    inputNameM = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    inputPhone = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    selectGender = new javax.swing.JComboBox();
    jLabel5 = new javax.swing.JLabel();
    inBirthYear = new javax.swing.JTextField();
    inComboMonth = new javax.swing.JComboBox();
    inTextDate = new javax.swing.JTextField();
    jLabel6 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    inComboType = new javax.swing.JComboBox();
    buttonSubmit = new javax.swing.JButton();
    buttonMR = new javax.swing.JButton();
    panelOperation = new javax.swing.JPanel();
    jLabel8 = new javax.swing.JLabel();
    inIdNumber = new javax.swing.JTextField();
    labelId = new javax.swing.JLabel();
    buttonVerify = new javax.swing.JButton();
    inComboOperation = new javax.swing.JComboBox();
    inComboType2 = new javax.swing.JComboBox();
    buttonDelete = new javax.swing.JButton();
    panelDelAll = new javax.swing.JPanel();
    labelDelAc = new javax.swing.JLabel();
    cbDelAll = new javax.swing.JComboBox();
    btnOK = new javax.swing.JButton();
    panelHeader = new javax.swing.JPanel();
    textFieldName = new javax.swing.JTextField();
    textFieldId = new javax.swing.JTextField();
    textFieldGender = new javax.swing.JTextField();
    textFieldAge = new javax.swing.JTextField();
    textFieldPhone = new javax.swing.JTextField();
    buttonLogOut = new javax.swing.JButton();
    btnChangePassword = new javax.swing.JButton();
    lbTitle = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("packageResourceBundles/bundleTextsEn"); // NOI18N
    setTitle(bundle.getString("strFrameAdminTitle")); // NOI18N
    setPreferredSize((Toolkit.getDefaultToolkit()).getScreenSize());

    panelAdminCover.setBackground(new java.awt.Color(204, 204, 204));

    adminTabbedPane.setBackground(new java.awt.Color(0, 0, 0));
    adminTabbedPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

    panelAccount.setBackground(new java.awt.Color(125, 125, 125));

    panelAccountsub1.setBackground(new java.awt.Color(192, 192, 192));
    panelAccountsub1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    labelNameF.setText("First Name:");

    inputNameF.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    jLabel1.setText("Last Name:");

    inputNameL.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    jLabel2.setText("Middle Name:");

    inputNameM.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    jLabel3.setText("Phone Number:");

    inputPhone.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    jLabel4.setText("Gender:");

    selectGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));
    selectGender.setBorder(null);
    selectGender.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    selectGender.setFocusable(false);

    jLabel5.setText("BirthDate: ");

    inBirthYear.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    inBirthYear.setText("1990");
    inBirthYear.setToolTipText(bundle.getString("strTooltipYear")); // NOI18N
    inBirthYear.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    inBirthYear.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        inBirthYearFocusGained(evt);
      }
    });

    inComboMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
    inComboMonth.setBorder(null);
    inComboMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    inTextDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    inTextDate.setText("1");
    inTextDate.setToolTipText(bundle.getString("strTooltipDate")); // NOI18N
    inTextDate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    inTextDate.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        inTextDateFocusGained(evt);
      }
    });

    jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel6.setText("Submit Account");
    jLabel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    jLabel7.setText("Type:");

    inComboType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Student", "Teacher", "Admin" }));
    inComboType.setBorder(null);

    buttonSubmit.setText("Submit");
    buttonSubmit.setBorder(null);
    buttonSubmit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonSubmitActionPerformed(evt);
      }
    });

    buttonMR.setText("Manage Records");
    buttonMR.setBorder(null);
    buttonMR.setEnabled(false);
    buttonMR.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonMRActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panelAccountsub1Layout = new javax.swing.GroupLayout(panelAccountsub1);
    panelAccountsub1.setLayout(panelAccountsub1Layout);
    panelAccountsub1Layout.setHorizontalGroup(
      panelAccountsub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAccountsub1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelAccountsub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(panelAccountsub1Layout.createSequentialGroup()
            .addGroup(panelAccountsub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(panelAccountsub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(inputPhone, javax.swing.GroupLayout.Alignment.TRAILING)
              .addGroup(panelAccountsub1Layout.createSequentialGroup()
                .addComponent(inComboType, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 281, Short.MAX_VALUE))
              .addGroup(panelAccountsub1Layout.createSequentialGroup()
                .addGroup(panelAccountsub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(panelAccountsub1Layout.createSequentialGroup()
                    .addComponent(inBirthYear, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(inComboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(inTextDate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addComponent(selectGender, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 135, Short.MAX_VALUE))))
          .addGroup(panelAccountsub1Layout.createSequentialGroup()
            .addGroup(panelAccountsub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(labelNameF)
              .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jLabel2))
            .addGap(23, 23, 23)
            .addGroup(panelAccountsub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(inputNameL, javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(inputNameM)
              .addComponent(inputNameF)))
          .addGroup(panelAccountsub1Layout.createSequentialGroup()
            .addGroup(panelAccountsub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(buttonMR, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(buttonSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    panelAccountsub1Layout.setVerticalGroup(
      panelAccountsub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAccountsub1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel6)
        .addGap(19, 19, 19)
        .addGroup(panelAccountsub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelNameF)
          .addComponent(inputNameF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelAccountsub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(inputNameL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelAccountsub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(inputNameM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(8, 8, 8)
        .addGroup(panelAccountsub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(selectGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelAccountsub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(inputPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelAccountsub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel5)
          .addComponent(inBirthYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(inComboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(inTextDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(16, 16, 16)
        .addGroup(panelAccountsub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel7)
          .addComponent(inComboType))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(buttonSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(buttonMR, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    panelOperation.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel8.setText("Operation");
    jLabel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    inIdNumber.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    inIdNumber.setEnabled(false);

    labelId.setText("ID Number: ");
    labelId.setEnabled(false);

    buttonVerify.setText("Verify");
    buttonVerify.setBorder(null);
    buttonVerify.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    buttonVerify.setEnabled(false);
    buttonVerify.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonVerifyActionPerformed(evt);
      }
    });

    inComboOperation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Operation", "New Account", "Update Account", "Delete Account" }));
    inComboOperation.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        inComboOperationActionPerformed(evt);
      }
    });

    inComboType2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Student", "Teacher", "Admin" }));
    inComboType2.setEnabled(false);

    buttonDelete.setText("Delete Account");
    buttonDelete.setBorder(null);
    buttonDelete.setEnabled(false);
    buttonDelete.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonDeleteActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panelOperationLayout = new javax.swing.GroupLayout(panelOperation);
    panelOperation.setLayout(panelOperationLayout);
    panelOperationLayout.setHorizontalGroup(
      panelOperationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelOperationLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelOperationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(panelOperationLayout.createSequentialGroup()
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(5, 5, 5))
          .addGroup(panelOperationLayout.createSequentialGroup()
            .addGroup(panelOperationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(labelId)
              .addComponent(buttonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
              .addComponent(buttonVerify, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOperationLayout.createSequentialGroup()
            .addComponent(inIdNumber)
            .addContainerGap())
          .addGroup(panelOperationLayout.createSequentialGroup()
            .addGroup(panelOperationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(inComboOperation, 0, 167, Short.MAX_VALUE)
              .addComponent(inComboType2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
    );
    panelOperationLayout.setVerticalGroup(
      panelOperationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelOperationLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(inComboOperation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(inComboType2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(11, 11, 11)
        .addComponent(labelId)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(inIdNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(buttonVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    panelDelAll.setBackground(new java.awt.Color(223, 217, 200));
    panelDelAll.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    labelDelAc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    labelDelAc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    labelDelAc.setText("Delete All Accounts");
    labelDelAc.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    labelDelAc.setPreferredSize(new java.awt.Dimension(75, 23));

    cbDelAll.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Student Accounts", "Teacher Accounts", "All Accounts" }));

    btnOK.setText("OK");
    btnOK.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnOKActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panelDelAllLayout = new javax.swing.GroupLayout(panelDelAll);
    panelDelAll.setLayout(panelDelAllLayout);
    panelDelAllLayout.setHorizontalGroup(
      panelDelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelDelAllLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelDelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelDelAc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(panelDelAllLayout.createSequentialGroup()
            .addGroup(panelDelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(cbDelAll, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(0, 73, Short.MAX_VALUE)))
        .addContainerGap())
    );
    panelDelAllLayout.setVerticalGroup(
      panelDelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelDelAllLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(labelDelAc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(cbDelAll, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(104, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout panelAccountLayout = new javax.swing.GroupLayout(panelAccount);
    panelAccount.setLayout(panelAccountLayout);
    panelAccountLayout.setHorizontalGroup(
      panelAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAccountLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(panelOperation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(panelDelAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(18, 18, 18)
        .addComponent(panelAccountsub1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );
    panelAccountLayout.setVerticalGroup(
      panelAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAccountLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(panelAccountsub1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(panelAccountLayout.createSequentialGroup()
            .addComponent(panelOperation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(panelDelAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        .addContainerGap())
    );

    adminTabbedPane.addTab("Accounts", panelAccount);

    panelHeader.setBackground(new java.awt.Color(175, 175, 175));
    panelHeader.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    textFieldName.setEditable(false);
    textFieldName.setBackground(new java.awt.Color(204, 255, 204));
    textFieldName.setText("Name: " + piBasicInfo.getFullName());
    textFieldName.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    textFieldName.setMargin(new java.awt.Insets(2, 4, 2, 2));

    textFieldId.setEditable(false);
    textFieldId.setBackground(new java.awt.Color(204, 255, 204));
    textFieldId.setText("ID: " + piBasicInfo.getIdString(6)
    );
    textFieldId.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    textFieldId.setMargin(new java.awt.Insets(2, 4, 2, 2));

    textFieldGender.setEditable(false);
    textFieldGender.setBackground(new java.awt.Color(204, 255, 204));
    textFieldGender.setText("Gender: " + piBasicInfo.getGender());
    textFieldGender.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    textFieldGender.setMargin(new java.awt.Insets(2, 4, 2, 2));

    textFieldAge.setEditable(false);
    textFieldAge.setBackground(new java.awt.Color(204, 255, 204));
    textFieldAge.setText("Age: " + piBasicInfo.getAge());
    textFieldAge.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    textFieldAge.setMargin(new java.awt.Insets(2, 4, 2, 2));

    textFieldPhone.setEditable(false);
    textFieldPhone.setBackground(new java.awt.Color(204, 255, 204));
    textFieldPhone.setText("Phone: " + piBasicInfo.getPhone());
    textFieldPhone.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    textFieldPhone.setMargin(new java.awt.Insets(2, 4, 2, 2));

    buttonLogOut.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    buttonLogOut.setText("Log Out");
    buttonLogOut.setBorder(null);
    buttonLogOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    buttonLogOut.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonLogOutActionPerformed(evt);
      }
    });

    btnChangePassword.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
    btnChangePassword.setText("Change Password");
    btnChangePassword.setBorder(null);
    btnChangePassword.setBorderPainted(false);
    btnChangePassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnChangePasswordActionPerformed(evt);
      }
    });

    lbTitle.setBackground(new java.awt.Color(75, 102, 103));
    lbTitle.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
    lbTitle.setForeground(new java.awt.Color(255, 255, 255));
    lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lbTitle.setText("Schoolbook Admin");
    lbTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    lbTitle.setOpaque(true);

    javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
    panelHeader.setLayout(panelHeaderLayout);
    panelHeaderLayout.setHorizontalGroup(
      panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelHeaderLayout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(textFieldAge, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(textFieldPhone, javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(textFieldName, javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(textFieldId)
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelHeaderLayout.createSequentialGroup()
            .addComponent(textFieldGender, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
            .addGap(95, 95, 95))
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelHeaderLayout.createSequentialGroup()
            .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
              .addComponent(buttonLogOut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(btnChangePassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    panelHeaderLayout.setVerticalGroup(
      panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelHeaderLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(textFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(textFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(textFieldGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(textFieldAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(textFieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(btnChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(buttonLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    javax.swing.GroupLayout panelAdminCoverLayout = new javax.swing.GroupLayout(panelAdminCover);
    panelAdminCover.setLayout(panelAdminCoverLayout);
    panelAdminCoverLayout.setHorizontalGroup(
      panelAdminCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAdminCoverLayout.createSequentialGroup()
        .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(adminTabbedPane))
    );
    panelAdminCoverLayout.setVerticalGroup(
      panelAdminCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(adminTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING)
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(panelAdminCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(panelAdminCover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void buttonLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogOutActionPerformed
        cycle.setRepeat(true);
        this.dispose();
    }//GEN-LAST:event_buttonLogOutActionPerformed

    private void inBirthYearFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inBirthYearFocusGained
        inBirthYear.setSelectionStart(0);
        inBirthYear.setSelectionEnd(inBirthYear.getText().length());
    }//GEN-LAST:event_inBirthYearFocusGained

    private void inTextDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inTextDateFocusGained
        inTextDate.setSelectionStart(0);
        inTextDate.setSelectionEnd(inTextDate.getText().length());
    }//GEN-LAST:event_inTextDateFocusGained

    private void buttonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSubmitActionPerformed
        //NOTE combo box starts index at 0
        PersonInfo p;
        DiagStudent dialogStudent;
        DiagTeacher dialogTeacher;
        String msg;
        boolean bHasError;
        Date dateCurrent = new Date();
        Date dateBirth;
        Calendar c = Calendar.getInstance();
        c.setTime(dateCurrent);
        bHasError = !submitForm.isValid();
        msg = submitForm.getMsg();
        if(bHasError){
           JOptionPane.showMessageDialog(this,msg,"Error",JOptionPane.ERROR_MESSAGE);
        }
        else {
            c.set(new Integer(inBirthYear.getText()),inComboMonth.getSelectedIndex(), new Integer(inTextDate.getText()));
            dateBirth = c.getTime();
            try {
                p = new PersonInfo(inputNameF.getText(),inputNameM.getText(),inputNameL.getText(),
                           0,
                          (String) selectGender.getSelectedItem(),
                           inputPhone.getText(),
                          (String) inComboType.getSelectedItem(),
                           dateBirth);
                if(iMode==0){
                   p.toDb(new Connector().getConnection());
                    switch (p.getType()) {
                        case "Student":
                            dialogStudent = new DiagStudent(this,true, new Connector().getConnection(),true,p.getId());
                            buttonMR.setEnabled(true);
                            break;
                        case "Teacher":
                            dialogTeacher = new DiagTeacher(this,true,new Connector().getConnection(),true,p.getId());
                            buttonMR.setEnabled(true);
                            break;
                    }
                   JOptionPane.showMessageDialog(this,"New "+p.getType()
                      +" account added with ID number: " + p.getIdString(6));
                }
                else if(iMode==1){
                    p.setId(new Integer(inIdNumber.getText()));
                    p.updateDb(new Connector().getConnection());
                    JOptionPane.showMessageDialog(this,"Update Success","Success",JOptionPane.INFORMATION_MESSAGE);
                }
             submitForm.resetForm();
             submitForm.setEditable(false);
             buttonMR.setEnabled(false);
             buttonSubmit.setEnabled(false);
             inComboOperation.setSelectedIndex(0);
             
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage(),"Connection Error",JOptionPane.ERROR_MESSAGE);
            } catch (DuplicateAccountException ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage(),"Duplicate Account",JOptionPane.ERROR_MESSAGE);
            } catch (DbUpdateFailedException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_buttonSubmitActionPerformed

    private void buttonVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerifyActionPerformed
        try {
            Connector connector = new Connector();
            PersonInfo p = new PersonInfo();
            boolean bExists;
            bExists=p.fetchData(new Integer(inIdNumber.getText()),
                        (String)inComboType2.getSelectedItem(),
                        new Connector().getConnection());
            
            if(bExists){
                //Need for one class for this pane...later
                piManaged = p;
                inputNameF.setText(p.getFirstName());
                inputNameL.setText(p.getLastName());
                inputNameM.setText(p.getMiddleName());
                selectGender.setSelectedItem(p.getGender());
                inputPhone.setText(p.getPhone());
                inComboType.setSelectedItem(p.getType());
                Integer year = p.getBirthCalendar().get(Calendar.YEAR);
                Integer date = p.getBirthCalendar().get(Calendar.DAY_OF_MONTH);
                Integer month = p.getBirthCalendar().get(Calendar.MONTH);
                inBirthYear.setText(year.toString());
                inTextDate.setText(date.toString());
                inComboMonth.setSelectedIndex(month);
                if(piManaged.getType().equals("Student")|piManaged.getType().equals("Teacher")){
                    buttonMR.setEnabled(true);
                }
                buttonSubmit.setEnabled(true);
                JOptionPane.showMessageDialog(this,"Account found and displayed in the text fields",
                                              "Found",JOptionPane.INFORMATION_MESSAGE);
            }      
            else{
                JOptionPane.showMessageDialog(this,"Account not found.",
                                              "Promt",JOptionPane.ERROR_MESSAGE);
            }
            if(bExists&iMode==2){
                buttonMR.setEnabled(false);
                buttonSubmit.setEnabled(false);
                buttonDelete.setEnabled(true);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FrameAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch(NumberFormatException en){
          JOptionPane.showMessageDialog(this,"Invalid Input","",JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_buttonVerifyActionPerformed

    private void buttonMRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMRActionPerformed
        try {
            switch(piManaged.getType()){
                case "Student": 
                    DiagStudent ds = new DiagStudent(this,true,new Connector().getConnection(),false,piManaged.getId());
                    break;
                case "Teacher":
                    DiagTeacher dt = new DiagTeacher(this,true,new Connector().getConnection(),false,piManaged.getId());
                    break;
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error at buttonMR " + ex);
           //Logger.getLogger(FrameAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonMRActionPerformed

    private void inComboOperationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inComboOperationActionPerformed
        boolean bActivate = false;
        submitForm.resetForm();
        piManaged = new PersonInfo();
        switch((String) inComboOperation.getSelectedItem()){
            case "Select Operation":
                buttonMR.setEnabled(false);
                buttonDelete.setEnabled(false);
                buttonSubmit.setEnabled(false);
                iMode=3;
                submitForm.setEditable(false);
                break;
            case "New Account":
                buttonMR.setEnabled(false);
                buttonDelete.setEnabled(false);
                buttonSubmit.setEnabled(true);
                submitForm.setEditable(true);
                iMode =0;
                break;
            case "Update Account":
                bActivate = true;
                buttonDelete.setEnabled(false);
                submitForm.setEditable(true);
                inComboType.setEnabled(false);
                iMode = 1;
                break;
            case "Delete Account":
                bActivate = true;
                iMode = 2;
                buttonSubmit.setEnabled(false);
                submitForm.setEditable(false);
                break;
        }
        inIdNumber.setEnabled(bActivate);
        //inComboType.setEnabled(!bActivate);
        inComboType2.setEnabled(bActivate);
        buttonVerify.setEnabled(bActivate);
        labelId.setEnabled(bActivate);
    }//GEN-LAST:event_inComboOperationActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        PreparedStatement ps;
        String query1;
        int choice = JOptionPane.showConfirmDialog(this,"Are you sure you want to delete this account?","Warning",JOptionPane.YES_NO_OPTION);
        if(choice==JOptionPane.OK_OPTION){
            try {
                Connector connector = new Connector();
                query1 = "DELETE FROM table_"+piManaged.getType().toLowerCase()+" WHERE id=?";
                ps = connector.getConnection().prepareStatement(query1);
                ps.setInt(1,piManaged.getId());
                ps.executeUpdate();
                if(piManaged.getType().equals("Student")){
                   for(int gl = 7;gl<=10;gl++){
                    query1 = "DELETE FROM table_" + gl + " WHERE studentId=?";
                    ps = connector.getConnection().prepareStatement(query1);
                    ps.setInt(1,piManaged.getId());
                    ps.executeUpdate();
                    } 
                }
                submitForm.resetForm();
                submitForm.setEditable(false);
                JOptionPane.showMessageDialog(this,"Account successfully deleted!","Status",
                                              JOptionPane.INFORMATION_MESSAGE);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FrameAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        int choice = JOptionPane.showConfirmDialog(this,"Deleted data will never be retrieved.",
                "Warning!",JOptionPane.YES_NO_OPTION);
        if(choice==JOptionPane.YES_OPTION){
            PersonInfo p = new PersonInfo();
            boolean bSuccess;
            switch(cbDelAll.getSelectedIndex()){
                case 0: bSuccess = p.delAllPerson("Student"); break;
                case 1: bSuccess = p.delAllPerson("Teacher"); break;
                case 2: bSuccess = p.delAllPerson("All"); break;
                default: bSuccess = false;     
            }
            if(bSuccess){
                JOptionPane.showMessageDialog(this,"Operation success","Success",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(this,"Operation failed","Failed",JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(this,"Operation cancelled.","Notice",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnOKActionPerformed

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
          q1 = "SELECT password FROM table_admin WHERE id = ? LIMIT 1";
          ps1 = con.prepareStatement(q1);
          ps1.setInt(1,piBasicInfo.getId());
          ps1.executeQuery();
          rs1 = ps1.getResultSet();
          if(rs1.next()){
            if(stInput.equals(rs1.getString("password"))){
              stPass = JOptionPane.showInputDialog(this,"Enter new password.");
              if(!stPass.isEmpty()){
                q1 = "UPDATE table_admin SET password=? WHERE id=?";
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

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTabbedPane adminTabbedPane;
  private javax.swing.JButton btnChangePassword;
  private javax.swing.JButton btnOK;
  private javax.swing.JButton buttonDelete;
  private javax.swing.JButton buttonLogOut;
  private javax.swing.JButton buttonMR;
  private javax.swing.JButton buttonSubmit;
  private javax.swing.JButton buttonVerify;
  private javax.swing.JComboBox cbDelAll;
  private javax.swing.JTextField inBirthYear;
  private javax.swing.JComboBox inComboMonth;
  private javax.swing.JComboBox inComboOperation;
  private javax.swing.JComboBox inComboType;
  private javax.swing.JComboBox inComboType2;
  private javax.swing.JTextField inIdNumber;
  private javax.swing.JTextField inTextDate;
  private javax.swing.JTextField inputNameF;
  private javax.swing.JTextField inputNameL;
  private javax.swing.JTextField inputNameM;
  private javax.swing.JTextField inputPhone;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel labelDelAc;
  private javax.swing.JLabel labelId;
  private javax.swing.JLabel labelNameF;
  private javax.swing.JLabel lbTitle;
  private javax.swing.JPanel panelAccount;
  private javax.swing.JPanel panelAccountsub1;
  private javax.swing.JPanel panelAdminCover;
  private javax.swing.JPanel panelDelAll;
  private javax.swing.JPanel panelHeader;
  private javax.swing.JPanel panelOperation;
  private javax.swing.JComboBox selectGender;
  private javax.swing.JTextField textFieldAge;
  private javax.swing.JTextField textFieldGender;
  private javax.swing.JTextField textFieldId;
  private javax.swing.JTextField textFieldName;
  private javax.swing.JTextField textFieldPhone;
  // End of variables declaration//GEN-END:variables
}
