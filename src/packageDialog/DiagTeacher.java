/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageDialog;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import packageCore.LoadHandler;

/**
 *
 * @author Retaliation
 */
public class DiagTeacher extends javax.swing.JDialog {
    private final boolean bIsNew;
    private final int iTeacherId;
    private Connection connection;

    /**
     * Creates new form DiagTeacher
     * @param parent
     * @param modal
     * @param con
     * @param isNew
     * @param id
     */
    public DiagTeacher(java.awt.Frame parent, boolean modal,Connection con,boolean isNew, int id) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        this.iTeacherId = id;
        this.bIsNew=isNew;
        this.connection=con;
        if(bIsNew){
            //GET ALL SECTIONS AND SUBJECTS WITH NO ASSIGNED TEACHERS
            getAllSec(0);
            getAllSubs(0); //0 means subject with no assigned teacher
        }
        else {
            // GET ALL SECTIONS AND SUBJECTS WITH NO ASSIGNED TEACHERS AND CHECK 
            //THE ONES OWNED BY THIS TEACHER
            getAllSec(iTeacherId);
            getAllSubs(iTeacherId);
        }
        this.setVisible(true);
    }
    private boolean getSec(int id){
        PreparedStatement ps;
        ResultSet rs;
        String query1;
        DefaultTableModel md;
        try {
            query1 = "SELECT sectionId,sectionName,gradeLevel,adviserId FROM table_sec";
            ps = connection.prepareStatement(query1);
            ps.executeQuery();
            rs = ps.getResultSet();
            md = (DefaultTableModel) tbSelAdv.getModel();
            while(rs.next()){
                if(rs.getInt("adviserId")!=id) continue;
                md.addRow(new Object[]{rs.getInt("sectionId"),
                          rs.getString("sectionName"),
                          rs.getInt("gradeLevel"),
                          false});
            }
            return true;
        } catch (SQLException ex) {
            System.out.println("Error at getSec: " + ex.getMessage());
            return false;
        }
    }
    private boolean getAllSec(int  id){
        PreparedStatement ps;
        ResultSet rs;
        String query1;
        DefaultTableModel md;
        try {
            query1 = "SELECT sectionId,sectionName,gradeLevel,adviserId FROM table_sec";
            ps = connection.prepareStatement(query1);
            ps.executeQuery();
            rs = ps.getResultSet();
            md = (DefaultTableModel) tbSelAdv.getModel();
            while(rs.next()){
                if(rs.getInt("adviserId")==id|rs.getInt("adviserId")==0){
                   md.addRow(new Object[]{rs.getInt("sectionId"),
                          rs.getString("sectionName"),
                          rs.getInt("gradeLevel"),
                          rs.getInt("adviserId")!=0}); //if equal to 0, uncheck
                }
            }
            return true;
        } catch (SQLException ex) {
            System.out.println("Error at getSec: " + ex.getMessage());
            return false;
        }

    }
    private boolean getAllSubs(int id){
        LoadHandler lh = new LoadHandler(id,connection);
        DefaultTableModel md = (DefaultTableModel) tbSelSub.getModel();
        if(lh.retrieveAllAvailable()){
            lh.updateModel(md);
            return true;
        }
        else {
            JOptionPane.showMessageDialog(this,"Something went wrong.","Oops!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    private void setSec() throws SQLException{
        PreparedStatement ps;
        DefaultTableModel dm = (DefaultTableModel) tbSelAdv.getModel();
        String query;
        //int iSecId;
        for(int k=0;k<dm.getRowCount();k++){
            if((boolean)dm.getValueAt(k,3)){ //if check replace with teacher id
                query = "UPDATE table_sec SET adviserId=? WHERE sectionId=?";
                ps = connection.prepareStatement(query);
                ps.setInt(1,iTeacherId);
                ps.setInt(2,(int)dm.getValueAt(k,0));
                if(ps.executeUpdate()==0){
                    JOptionPane.showMessageDialog(this,"Setting section failed!","Oops!",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(this,"Sections successfully updated!","Success!",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else {
                query = "UPDATE table_sec SET adviserId=? WHERE sectionId=?";
                ps = connection.prepareStatement(query);
                ps.setInt(1,0);
                ps.setInt(2,(int)dm.getValueAt(k,0));
                ps.executeUpdate();
            }
        }
    }
    private void setSubs(){
        LoadHandler lh = new LoadHandler(iTeacherId,connection);
        DefaultTableModel  dm = (DefaultTableModel) tbSelSub.getModel();
        if(lh.toDb(dm)){
            JOptionPane.showMessageDialog(this,"Teacher classlist udated!","Success",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,"Failed to upload table.","Oops!",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnAdOpt = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSelAdv = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        pnTeachOpt = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSelSub = new javax.swing.JTable();
        btnOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 255));

        pnAdOpt.setBackground(new java.awt.Color(255, 255, 255));

        tbSelAdv.setAutoCreateRowSorter(true);
        tbSelAdv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Section Id", "Section", "Grade Level", "Select"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
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
        jScrollPane3.setViewportView(tbSelAdv);

        jLabel1.setText("Note: It is advised to choose only one section.");

        javax.swing.GroupLayout pnAdOptLayout = new javax.swing.GroupLayout(pnAdOpt);
        pnAdOpt.setLayout(pnAdOptLayout);
        pnAdOptLayout.setHorizontalGroup(
            pnAdOptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAdOptLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnAdOptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                    .addGroup(pnAdOptLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnAdOptLayout.setVerticalGroup(
            pnAdOptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAdOptLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Adviser Options", pnAdOpt);

        pnTeachOpt.setBackground(new java.awt.Color(255, 255, 255));

        tbSelSub.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Section Id", "Section", "Grade Level", "Subject Name", "Subject Id", "Select"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbSelSub);

        javax.swing.GroupLayout pnTeachOptLayout = new javax.swing.GroupLayout(pnTeachOpt);
        pnTeachOpt.setLayout(pnTeachOptLayout);
        pnTeachOptLayout.setHorizontalGroup(
            pnTeachOptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTeachOptLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnTeachOptLayout.setVerticalGroup(
            pnTeachOptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTeachOptLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Teacher Options", pnTeachOpt);

        btnOk.setText("OK");
        btnOk.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOk.setFocusable(false);
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        try {
            setSec();
            setSubs();
            this.dispose();
        } catch (SQLException ex) {
            System.out.println("Error at DiagTeacher at buttonOk: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnOkActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel pnAdOpt;
    private javax.swing.JPanel pnTeachOpt;
    private javax.swing.JTable tbSelAdv;
    private javax.swing.JTable tbSelSub;
    // End of variables declaration//GEN-END:variables
}
