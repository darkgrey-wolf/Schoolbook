package packageFrames;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import packageCore.*;
import packageDialog.DiagPersonal;
public class FrameStudent extends javax.swing.JFrame {

    private final Core cycle;
    private final PersonInfo piBasicInfo;
    private int iGl;
    private String strSecName;
    private String strAdv;

    public FrameStudent(Core cycle, PersonInfo basicInfo) {
        this.piBasicInfo=basicInfo;
        this.cycle=cycle;
        fetchBasicAcad();
        initComponents();
        fetchGrades();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public final void fetchBasicAcad(){
        iGl = 0;
        strSecName = "Not Found";
        strAdv = "Not Found";
        String query1;
        Connector connector;
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        //Retrieve grade level
        try {
            connector = new Connector();
            con = connector.getConnection();
            query1 = "SELECT gradeLevel FROM table_student WHERE id=?";
            ps = con.prepareStatement(query1);
            ps.setInt(1,piBasicInfo.getId());
            ps.executeQuery();
            rs = ps.getResultSet();
            if(rs.next()){
                iGl = rs.getInt("gradeLevel");
            }
            //retrieve sectionname
            query1 = "SELECT sectionName from table_sec where sectionId = "
                   + "(SELECT sectionId from table_student where id = ?)";
            ps = con.prepareStatement(query1);
            ps.setInt(1,piBasicInfo.getId());
            ps.executeQuery();
            rs = ps.getResultSet();
            if(rs.next()){
                strSecName = rs.getString("sectionName");
            }
            //retrieve adviser
            query1 = "SELECT id FROM table_teacher WHERE id = "
            + "(SELECT adviserId FROM table_sec WHERE sectionId = "
            + "(SELECT sectionId FROM table_student WHERE id = ?) )";
            ps = con.prepareStatement(query1);
            ps.setInt(1,piBasicInfo.getId());
            ps.executeQuery();
            rs = ps.getResultSet();
            if(rs.next()){
                PersonInfo teacher = new PersonInfo();
                teacher.fetchData(rs.getInt("id"),"Teacher",con);
                strAdv = teacher.getName();
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Error at fetchBasicAcad: " + ex.getMessage());
        }
    }
    public final void fetchGrades(){
        DefaultTableModel md;
        GradeRecords records;
        ArrayList<Subject> subjects;
        Subject temp;
        String query1;
        Connector connector;
        Connection connection;
        PreparedStatement ps;
        ResultSet rs;
        try {
            connection = new Connector().getConnection();
            for(int k=7;k<=10;k++){
                subjects = new ArrayList<>();
                md = getModel(k);
                //retrieve all subjects from table_n
                query1 = "SELECT * FROM table_" + k + " WHERE studentId=?";
                ps = connection.prepareStatement(query1);
                ps.setInt(1,piBasicInfo.getId());
                ps.executeQuery();
                rs = ps.getResultSet();
                if(rs.next()){
                    for(int l=1;l<=10;l++){
                        temp = new Subject(l,k,rs.getString("sub_"+l));
                        subjects.add(temp);
                    }
                }
                records = new GradeRecords(subjects,k);
                records.updateModel(md);
                String stG = "General Average: ";
                switch(k){
                    case 7: txfAve7.setText(stG+Double.toString(records.getGenAve()));
                        break;
                    case 8: txfAve8.setText(stG+Double.toString(records.getGenAve()));
                        break;
                    case 9: txfAve9.setText(stG+Double.toString(records.getGenAve()));
                        break;
                    case 10: txfAve10.setText(stG+Double.toString(records.getGenAve()));
                        break;
                }
            }
        }catch(ClassNotFoundException|SQLException e){
            System.out.println("Error at fetchGrades: " + e.getMessage());
        } 
    }
    public final DefaultTableModel getModel(int gradeLevel){
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
    
    
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    panelStudentCover = new javax.swing.JPanel();
    jPanel9 = new javax.swing.JPanel();
    textName = new javax.swing.JTextField();
    textID = new javax.swing.JTextField();
    textGender = new javax.swing.JTextField();
    textAge = new javax.swing.JTextField();
    textPhone = new javax.swing.JTextField();
    buttonLogOut = new javax.swing.JButton();
    txfInfoGl = new javax.swing.JTextField();
    txfInfoSec = new javax.swing.JTextField();
    txfInoProf = new javax.swing.JTextField();
    btnPI = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();
    jTabbedPane1 = new javax.swing.JTabbedPane();
    jPanel1 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    tableG7 = new javax.swing.JTable();
    txfAve7 = new javax.swing.JTextField();
    jPanel3 = new javax.swing.JPanel();
    jScrollPane2 = new javax.swing.JScrollPane();
    tableG8 = new javax.swing.JTable();
    txfAve8 = new javax.swing.JTextField();
    jPanel4 = new javax.swing.JPanel();
    jScrollPane3 = new javax.swing.JScrollPane();
    tableG9 = new javax.swing.JTable();
    txfAve9 = new javax.swing.JTextField();
    jPanel5 = new javax.swing.JPanel();
    jScrollPane4 = new javax.swing.JScrollPane();
    tableG10 = new javax.swing.JTable();
    txfAve10 = new javax.swing.JTextField();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("packageResourceBundles/bundleTextsEn"); // NOI18N
    setTitle(bundle.getString("textStudentFrameTitle")); // NOI18N
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowOpened(java.awt.event.WindowEvent evt) {
        formWindowOpened(evt);
      }
    });

    panelStudentCover.setBackground(new java.awt.Color(204, 204, 255));

    jPanel9.setBackground(new java.awt.Color(153, 153, 255));
    jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    textName.setEditable(false);
    textName.setBackground(new java.awt.Color(204, 204, 255));
    textName.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    textName.setText("Name: " + piBasicInfo.getFullName());
    textName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    textName.setMargin(new java.awt.Insets(2, 4, 2, 2));

    textID.setEditable(false);
    textID.setBackground(new java.awt.Color(204, 204, 255));
    textID.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    textID.setText("ID: " + piBasicInfo.getIdString(6)
    );
    textID.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    textID.setMargin(new java.awt.Insets(2, 4, 2, 2));

    textGender.setEditable(false);
    textGender.setBackground(new java.awt.Color(204, 204, 255));
    textGender.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    textGender.setText("Gender: " + piBasicInfo.getGender());
    textGender.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    textGender.setMargin(new java.awt.Insets(2, 4, 2, 2));

    textAge.setEditable(false);
    textAge.setBackground(new java.awt.Color(204, 204, 255));
    textAge.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    textAge.setText("Age: " + piBasicInfo.getAge());
    textAge.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    textAge.setMargin(new java.awt.Insets(2, 4, 2, 2));

    textPhone.setEditable(false);
    textPhone.setBackground(new java.awt.Color(204, 204, 255));
    textPhone.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    textPhone.setText("Phone: " + piBasicInfo.getPhone());
    textPhone.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    buttonLogOut.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
    buttonLogOut.setForeground(new java.awt.Color(0, 51, 102));
    buttonLogOut.setText("Log Out");
    buttonLogOut.setBorder(null);
    buttonLogOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    buttonLogOut.setFocusable(false);
    buttonLogOut.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonLogOutActionPerformed(evt);
      }
    });

    txfInfoGl.setEditable(false);
    txfInfoGl.setBackground(new java.awt.Color(204, 204, 255));
    txfInfoGl.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    txfInfoGl.setText("Grade Level: " + iGl);
    txfInfoGl.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    txfInfoSec.setEditable(false);
    txfInfoSec.setBackground(new java.awt.Color(204, 204, 255));
    txfInfoSec.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    txfInfoSec.setText("Section Name: " + strSecName);
    txfInfoSec.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    txfInoProf.setEditable(false);
    txfInoProf.setBackground(new java.awt.Color(204, 204, 255));
    txfInoProf.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    txfInoProf.setText("Adviser: " + strAdv);
    txfInoProf.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    txfInoProf.setFocusable(false);

    btnPI.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
    btnPI.setText("Edit Personal Info");
    btnPI.setToolTipText("You must have permission from your adviser");
    btnPI.setBorder(null);
    btnPI.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnPI.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnPIActionPerformed(evt);
      }
    });

    jLabel1.setBackground(new java.awt.Color(0, 51, 153));
    jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText("Schoolbook Student");
    jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    jLabel1.setOpaque(true);

    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
    jPanel9.setLayout(jPanel9Layout);
    jPanel9Layout.setHorizontalGroup(
      jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(txfInoProf, javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(textName, javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(textID, javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(txfInfoSec, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addComponent(txfInfoGl, javax.swing.GroupLayout.Alignment.LEADING))
              .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(buttonLogOut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPI, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)))
            .addGap(0, 0, Short.MAX_VALUE))
          .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
          .addComponent(textPhone, javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
            .addComponent(textGender, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(textAge)))
        .addContainerGap())
    );
    jPanel9Layout.setVerticalGroup(
      jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel9Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(30, 30, 30)
        .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(textID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(textGender, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(textAge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(textPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(txfInfoGl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(txfInfoSec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(txfInoProf, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(btnPI, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(buttonLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    jPanel1.setBackground(new java.awt.Color(255, 255, 255));

    tableG7.setAutoCreateRowSorter(true);
    tableG7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
        false, false, false, false, false, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    tableG7.setSelectionBackground(new java.awt.Color(204, 204, 204));
    tableG7.getTableHeader().setReorderingAllowed(false);
    jScrollPane1.setViewportView(tableG7);

    txfAve7.setEditable(false);
    txfAve7.setBackground(new java.awt.Color(204, 204, 255));
    txfAve7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    txfAve7.setText("General Average:");
    txfAve7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(txfAve7, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(txfAve7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    jTabbedPane1.addTab("Grade 7", jPanel1);

    jPanel3.setBackground(new java.awt.Color(255, 255, 255));

    tableG8.setAutoCreateRowSorter(true);
    tableG8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
        false, false, false, false, false, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    tableG8.setSelectionBackground(new java.awt.Color(204, 204, 204));
    tableG8.getTableHeader().setReorderingAllowed(false);
    jScrollPane2.setViewportView(tableG8);

    txfAve8.setEditable(false);
    txfAve8.setBackground(new java.awt.Color(204, 204, 255));
    txfAve8.setText("General Average:");
    txfAve8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
          .addGroup(jPanel3Layout.createSequentialGroup()
            .addComponent(txfAve8, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    jPanel3Layout.setVerticalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(txfAve8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    jTabbedPane1.addTab("Grade 8", jPanel3);

    jPanel4.setBackground(new java.awt.Color(255, 255, 255));

    tableG9.setAutoCreateRowSorter(true);
    tableG9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
        false, false, false, false, false, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    tableG9.setSelectionBackground(new java.awt.Color(204, 204, 204));
    tableG9.getTableHeader().setReorderingAllowed(false);
    jScrollPane3.setViewportView(tableG9);

    txfAve9.setEditable(false);
    txfAve9.setBackground(new java.awt.Color(204, 204, 255));
    txfAve9.setText("General Average:");
    txfAve9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
      jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel4Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
          .addGroup(jPanel4Layout.createSequentialGroup()
            .addComponent(txfAve9, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    jPanel4Layout.setVerticalGroup(
      jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel4Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(txfAve9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    jTabbedPane1.addTab("Grade 9", jPanel4);

    jPanel5.setBackground(new java.awt.Color(255, 255, 255));

    tableG10.setAutoCreateRowSorter(true);
    tableG10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
        false, false, false, false, false, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    tableG10.setSelectionBackground(new java.awt.Color(204, 204, 204));
    tableG10.getTableHeader().setReorderingAllowed(false);
    jScrollPane4.setViewportView(tableG10);

    txfAve10.setEditable(false);
    txfAve10.setBackground(new java.awt.Color(204, 204, 255));
    txfAve10.setText("General Average:");
    txfAve10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel5Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
          .addGroup(jPanel5Layout.createSequentialGroup()
            .addComponent(txfAve10, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    jPanel5Layout.setVerticalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel5Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(txfAve10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    jTabbedPane1.addTab("Grade 10", jPanel5);

    javax.swing.GroupLayout panelStudentCoverLayout = new javax.swing.GroupLayout(panelStudentCover);
    panelStudentCover.setLayout(panelStudentCoverLayout);
    panelStudentCoverLayout.setHorizontalGroup(
      panelStudentCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelStudentCoverLayout.createSequentialGroup()
        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jTabbedPane1))
    );
    panelStudentCoverLayout.setVerticalGroup(
      panelStudentCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(jTabbedPane1)
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

  private void btnPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPIActionPerformed
    Connection con;
    PreparedStatement ps1;
    ResultSet rs1;
    String q1;
    DiagPersonal dp;
    
      try {
        con = new Connector().getConnection();
        q1 = "SELECT permission FROM table_student WHERE id=?";
        ps1 = con.prepareStatement(q1);
        ps1.setInt(1,piBasicInfo.getId());
        ps1.executeQuery();
        rs1 = ps1.getResultSet();
        if(rs1.next()){
          if(rs1.getBoolean("permission")){
            dp = new DiagPersonal(this,true,piBasicInfo);
            dp.setVisible(true);
          }
          else {
            JOptionPane.showMessageDialog(this,
            "Ask permission to your adviser to edit your personal information",
            "Sorry",
            JOptionPane.INFORMATION_MESSAGE);
          }
        }
        else {
          System.out.println("No result at btnPIActionPerformed.");
        }
      } catch (ClassNotFoundException | SQLException ex) {
        JOptionPane.showMessageDialog(this,"Network Error.");
        System.out.println("AT btnPIActionPerformed: "+ex.getMessage());
      }
  }//GEN-LAST:event_btnPIActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnPI;
  private javax.swing.JButton buttonLogOut;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JPanel jPanel4;
  private javax.swing.JPanel jPanel5;
  private javax.swing.JPanel jPanel9;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JScrollPane jScrollPane3;
  private javax.swing.JScrollPane jScrollPane4;
  private javax.swing.JTabbedPane jTabbedPane1;
  private javax.swing.JPanel panelStudentCover;
  private javax.swing.JTable tableG10;
  private javax.swing.JTable tableG7;
  private javax.swing.JTable tableG8;
  private javax.swing.JTable tableG9;
  private javax.swing.JTextField textAge;
  private javax.swing.JTextField textGender;
  private javax.swing.JTextField textID;
  private javax.swing.JTextField textName;
  private javax.swing.JTextField textPhone;
  private javax.swing.JTextField txfAve10;
  private javax.swing.JTextField txfAve7;
  private javax.swing.JTextField txfAve8;
  private javax.swing.JTextField txfAve9;
  private javax.swing.JTextField txfInfoGl;
  private javax.swing.JTextField txfInfoSec;
  private javax.swing.JTextField txfInoProf;
  // End of variables declaration//GEN-END:variables
}
