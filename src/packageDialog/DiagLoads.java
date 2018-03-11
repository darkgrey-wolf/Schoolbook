
package packageDialog;
import packageCore.*;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class DiagLoads extends javax.swing.JDialog {
    private LoadHandler lhSubjects;
    private Load loadSel;
    private boolean bHasPrev;
    private Load lLastSel;
    /**
     * Creates new form DiagLoads
     * @param parent
     * @param modal
     * @param teacherId
     */
    public DiagLoads(java.awt.Frame parent, boolean modal, int teacherId) {
        super(parent, modal);
        initComponents();
        try {
            lhSubjects = new LoadHandler(teacherId,new Connector().getConnection());
            setUpLoadSel();
            bHasPrev = false;
            lLastSel = null;
        } catch (ClassNotFoundException | SQLException ex) {
           System.out.println("Network Error at DiagLoads constructor: " + ex.getMessage());
        }
        
    }
    

    
    private void setUpLoadSel(){
        String[] stSubAr;
        if(lhSubjects.retrieveOnly()){
            stSubAr = lhSubjects.getSubListStrings();
            //stLoadSelAr = new String[stSubAr.length+1];
            //stLoadSelAr[0] = "Select Load";
            for (String stSubAr1 : stSubAr) {
                cbLoadSel.addItem(stSubAr1);
            }
            //cbLoadSel = new JComboBox(stLoadSelAr);
        } else {
            JOptionPane.showMessageDialog(this,"Failed to retrieve your subjects.","Sorry!",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void setUpTable(){
        Connection con;
        PreparedStatement ps1;
        ResultSet rs1;
        String q1;
        int[] iStudentIdAr;
        DefaultTableModel dm = (DefaultTableModel) tbClassList.getModel();
        dm.getDataVector().removeAllElements();
        dm.fireTableDataChanged();
        Subject s;
        PersonInfo p;
        try {
            //get all students that belong to the section of the subject
            con = new Connector().getConnection();
            iStudentIdAr = loadSel.getStudentIds(con);
            //for each student gather required data
            for(int k=0;k<iStudentIdAr.length;k++){
                //get Student name
                p = new PersonInfo();
                p.fetchData(iStudentIdAr[k],"Student", con);
                //Get grades
                q1 = "SELECT sub_" + loadSel.getSubId() + " FROM table_" + loadSel.getGdLev() + " "
                    + "WHERE studentId = ? LIMIT 1";
                ps1 = con.prepareStatement(q1);
                ps1.setInt(1,iStudentIdAr[k]);
                ps1.executeQuery();
                rs1 = ps1.getResultSet();
                if(rs1.next()){
                    s = new Subject(loadSel.getSubId(),loadSel.getGdLev(),rs1.getString("sub_"+loadSel.getSubId()));
                    dm.addRow(new Object[]{p.getId(),p.getName(),loadSel.getGdLev(),loadSel.getSecName(),
                                       s.get1(),
                                       s.get2(),
                                       s.get3(),
                                       s.get4(),
                                       s.getAve()});
                }
                else {
                    System.out.println("Query failure: " + q1);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Network Error at setUpTable: " + ex.getMessage());
        }
    }
    private void calcFinal(DefaultTableModel dm){
        double  dTotal;
        double dFinal;
        for(int k=0;k<dm.getRowCount();k++){
            dTotal = 0;
            for(int l=0;l<4;l++){
                dTotal += (double) dm.getValueAt(k,l+4);
            }
            dFinal = dTotal/4;
            dm.setValueAt(dFinal,k,8);
            dm.fireTableCellUpdated(k,8);
        }
    }
    private void toDbTable(Load load) throws ClassNotFoundException, SQLException{
        Connection con = new Connector().getConnection();
        PreparedStatement ps1;
        ResultSet rs1;
        String q1;
        Subject s;
        int iGl;
        
        for(int k=0;k<tbClassList.getRowCount();k++){
            s = new Subject(load.getSubName(),
                            load.getGdLev(),
                            (double)tbClassList.getValueAt(k,4),
                            (double)tbClassList.getValueAt(k, 5),
                            (double)tbClassList.getValueAt(k,6),
                            (double)tbClassList.getValueAt(k,7));
            q1 = "UPDATE table_" + load.getGdLev() + " SET sub_" + load.getSubId() + " = ? WHERE studentId = ?";
            ps1 = con.prepareStatement(q1);
            ps1.setString(1,s.toString());
            ps1.setInt(2,(int)tbClassList.getValueAt(k,0));
            ps1.executeUpdate();
        }
    }
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    tpnLoad = new javax.swing.JTabbedPane();
    jPanel1 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    tbClassList = new javax.swing.JTable();
    btnOk = new javax.swing.JButton();
    cbLoadSel = new javax.swing.JComboBox();
    btnCalc = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    setPreferredSize(new java.awt.Dimension(1200, 500));
    setResizable(false);
    setType(java.awt.Window.Type.POPUP);

    jPanel1.setBackground(new java.awt.Color(255, 255, 255));

    tbClassList.setAutoCreateRowSorter(true);
    tbClassList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    tbClassList.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Student Id", "Student Name", "Grade Level", "Section", "1st Quarter", "2nd Quarter", "3rd Quarter", "4th Quarter", "Final Rating"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
      };
      boolean[] canEdit = new boolean [] {
        false, false, false, false, true, true, true, true, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    tbClassList.setSelectionBackground(new java.awt.Color(204, 204, 255));
    tbClassList.getTableHeader().setReorderingAllowed(false);
    jScrollPane1.setViewportView(tbClassList);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
        .addContainerGap())
    );

    tpnLoad.addTab("Load: Section, Subject Name", jPanel1);

    btnOk.setText("OK");
    btnOk.setBorder(null);
    btnOk.setFocusable(false);
    btnOk.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnOkActionPerformed(evt);
      }
    });

    cbLoadSel.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
    cbLoadSel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Load" }));
    cbLoadSel.setBorder(null);
    cbLoadSel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    cbLoadSel.setFocusable(false);
    cbLoadSel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cbLoadSelActionPerformed(evt);
      }
    });

    btnCalc.setText("Calculate Final Ratings");
    btnCalc.setBorder(null);
    btnCalc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnCalc.setFocusable(false);
    btnCalc.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnCalcActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(tpnLoad, javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
            .addGap(12, 12, 12)
            .addComponent(cbLoadSel, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
            .addComponent(btnCalc, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(cbLoadSel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(tpnLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(56, 56, 56)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btnCalc, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dm = (DefaultTableModel) tbClassList.getModel();
        calcFinal(dm);
        try {
            toDbTable(loadSel);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Network Error at toDbTable: " + ex.getMessage());
        }
        this.dispose();
    }//GEN-LAST:event_btnOkActionPerformed

    private void cbLoadSelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLoadSelActionPerformed
      DefaultTableModel dm = (DefaultTableModel) tbClassList.getModel();
      Load load;  
        switch((String)cbLoadSel.getSelectedItem()){
          case "Select Load": //do nothing 
            dm.getDataVector().removeAllElements();
            dm.fireTableDataChanged();
            loadSel = null;
            lLastSel = null;
            bHasPrev = false;
            break;
          default:
            if(!bHasPrev){ // first run
              loadSel = lhSubjects.getLoad((String)cbLoadSel.getSelectedItem());
              tpnLoad.setTitleAt(0,"Section: " + loadSel.getSecName() + ", Subject: " + loadSel.getSubName());
              setUpTable();
              bHasPrev = true;
              lLastSel = loadSel;
            }
            else { //If table has previous data...
              calcFinal(dm);
              try {
                toDbTable(lLastSel);
                loadSel = lhSubjects.getLoad((String)cbLoadSel.getSelectedItem());
                tpnLoad.setTitleAt(0,"Section: " + loadSel.getSecName() + ", Subject: " + loadSel.getSubName());
                setUpTable();
                bHasPrev = true;
                lLastSel = loadSel;
              } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("Network Error at toDbTable: " + ex.getMessage());
              }
            }  
            break;
        }
    }//GEN-LAST:event_cbLoadSelActionPerformed

    private void btnCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dm = (DefaultTableModel) tbClassList.getModel();
        calcFinal(dm);
    }//GEN-LAST:event_btnCalcActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnCalc;
  private javax.swing.JButton btnOk;
  private javax.swing.JComboBox cbLoadSel;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable tbClassList;
  private javax.swing.JTabbedPane tpnLoad;
  // End of variables declaration//GEN-END:variables
}
