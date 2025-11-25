package com.udj.gui;

import com.udj.gui.components.ActionCellEditor;
import com.udj.gui.components.ActionCellRenderer;
import com.udj.gui.components.CustomComboBox;
import com.udj.gui.components.CustomScrollBarUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JScrollBar;
import javax.swing.border.LineBorder;
import com.udj.logic.StudentManager;
import javax.swing.table.DefaultTableModel;

public class StudentManagement extends javax.swing.JPanel {
    
    private static final String PLACEHOLDER_TEXT = "Search Name or ID";

    public StudentManagement() {
        initComponents();
        styleTable();
        styleScrollBar();
        StudentScrollPane.setBorder(null);
        StudentScrollPane.getViewport().setOpaque(true);
        StudentScrollPane.getViewport().setBackground(Color.decode("#F4E8FA")); 
        StudentScrollPane.setBorder(BorderFactory.createEmptyBorder());
        CustomComboBox.applyStyle(CollegeCB);
        CollegeCB.setBorder(new LineBorder(new Color(0x8750A6), 1, true));
        loadStudentData();
        setupActionColumn(); 
        Search.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) { loadStudentData(); }
        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) { loadStudentData(); }
        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) { loadStudentData(); }
});
    }
    
    private void setupActionColumn() {
        StudentTable.getColumnModel().getColumn(6).setCellRenderer(new ActionCellRenderer());
        StudentTable.getColumnModel().getColumn(6).setCellEditor(new ActionCellEditor(StudentTable, this));
        StudentTable.setRowHeight(30);
    }
    
     private void loadStudentData() {
        DefaultTableModel model = (DefaultTableModel) StudentTable.getModel();
        model.setRowCount(0); 
        
        String selectedFilter = (String) CollegeCB.getSelectedItem();
        String targetCollegeName = "";
        if (selectedFilter != null) {
            targetCollegeName = switch (selectedFilter) {
                case "COS" -> "College of Science";
                case "COE" -> "College of Engineering";
                case "CLA" -> "College of Liberal Arts";
                default -> "ALL";
            };
        }
        
        String searchText = Search.getText().trim();
        if (searchText.equals("Search Name or ID")) {
            searchText = "";
        }
        searchText = searchText.toLowerCase();
        
        java.util.List<String[]> students = StudentManager.getAllStudents();
        
        for (String[] student : students) {
            String id = student[0];
            String name = student[1];
            String studentCollege = student[4];

            boolean matchesCollege = "ALL".equals(targetCollegeName) || studentCollege.equals(targetCollegeName);
            boolean matchesSearch = searchText.isEmpty() || 
                                    id.toLowerCase().contains(searchText) || 
                                    name.toLowerCase().contains(searchText);

            if (matchesCollege && matchesSearch) {
                Object[] row = {
                    student[0], 
                    student[1], 
                    student[2], 
                    student[5], 
                    student[3], 
                    student[6], 
                    "⋮"         
                };
                model.addRow(row);
            }
        }   
    }
    
    public void refreshTable() {
        loadStudentData();
        StudentTable.revalidate();
        StudentTable.repaint();
    }
    
    private void styleScrollBar() {
        JScrollBar verticalBar = StudentScrollPane.getVerticalScrollBar();
        verticalBar.setUI(new CustomScrollBarUI());
        verticalBar.setPreferredSize(new Dimension(5, Integer.MAX_VALUE));

        JScrollBar horizontalBar = StudentScrollPane.getHorizontalScrollBar();
        horizontalBar.setUI(new CustomScrollBarUI());
        horizontalBar.setPreferredSize(new Dimension(Integer.MAX_VALUE, 10));
    }
    
    private void styleTable() {
        StudentTable.getTableHeader().setOpaque(false);
        StudentTable.getTableHeader().setBackground(Color.decode("#8f6da0")); 
        StudentTable.getTableHeader().setForeground(Color.WHITE);
        StudentTable.getTableHeader().setFont(new java.awt.Font("Arial", Font.BOLD, 12));
        StudentTable.setBackground(Color.decode("#F4E8FA"));
        StudentTable.setForeground(Color.decode("#2E1448"));
        StudentTable.setFont(new java.awt.Font("Arial", Font.PLAIN, 12));
        StudentTable.setSelectionBackground(Color.decode("#C8A8D8"));
        StudentTable.setSelectionForeground(Color.decode("#2E1448"));
        StudentTable.setShowGrid(true);
        StudentTable.setGridColor(new Color(140, 104, 160, 25)); 
        StudentTable.setIntercellSpacing(new java.awt.Dimension(4, 1));
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        StudentScrollPane = new javax.swing.JScrollPane();
        StudentTable = new javax.swing.JTable();
        CollegeCB = new javax.swing.JComboBox<>();
        Search = new javax.swing.JTextField();
        AddStudentC = new javax.swing.JLabel();
        AutoPromoteC = new javax.swing.JLabel();
        StudentMBG = new com.udj.gui.components.ButtonLabelBG();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        StudentScrollPane.setBackground(new java.awt.Color(238, 221, 242));
        StudentScrollPane.setBorder(null);
        StudentScrollPane.setColumnHeaderView(null);

        StudentTable.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        StudentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Program", "Year", "Department", "Status", "Actions"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        StudentTable.setGridColor(new java.awt.Color(143, 109, 160));
        StudentTable.setOpaque(false);
        StudentTable.setShowGrid(false);
        StudentTable.getTableHeader().setResizingAllowed(false);
        StudentTable.getTableHeader().setReorderingAllowed(false);
        StudentScrollPane.setViewportView(StudentTable);
        if (StudentTable.getColumnModel().getColumnCount() > 0) {
            StudentTable.getColumnModel().getColumn(0).setResizable(false);
            StudentTable.getColumnModel().getColumn(0).setPreferredWidth(40);
            StudentTable.getColumnModel().getColumn(1).setResizable(false);
            StudentTable.getColumnModel().getColumn(1).setPreferredWidth(90);
            StudentTable.getColumnModel().getColumn(2).setResizable(false);
            StudentTable.getColumnModel().getColumn(3).setResizable(false);
            StudentTable.getColumnModel().getColumn(3).setPreferredWidth(25);
            StudentTable.getColumnModel().getColumn(4).setResizable(false);
            StudentTable.getColumnModel().getColumn(5).setResizable(false);
            StudentTable.getColumnModel().getColumn(5).setPreferredWidth(25);
            StudentTable.getColumnModel().getColumn(6).setResizable(false);
            StudentTable.getColumnModel().getColumn(6).setPreferredWidth(45);
        }

        add(StudentScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 216, 697, 344));

        CollegeCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL", "COS", "COE", "CLA" }));
        CollegeCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CollegeCBActionPerformed(evt);
            }
        });
        add(CollegeCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 66, 20));

        Search.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Search.setForeground(new java.awt.Color(102, 102, 102));
        Search.setText("Search Name or ID"); // NOI18N
        Search.setBorder(null);
        Search.setPreferredSize(new java.awt.Dimension(180, 25));
        Search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                SearchFocusLost(evt);
            }
        });
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(793, 103, 130, 17));

        AddStudentC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddStudentC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddStudentCMouseClicked(evt);
            }
        });
        add(AddStudentC, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 170, 70, 22));

        AutoPromoteC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AutoPromoteC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AutoPromoteCMouseClicked(evt);
            }
        });
        add(AutoPromoteC, new org.netbeans.lib.awtextra.AbsoluteConstraints(773, 170, 86, 22));

        StudentMBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SMBG.png"))); // NOI18N
        add(StudentMBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void SearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SearchFocusGained
        if (Search.getText().equals(PLACEHOLDER_TEXT)) {
            Search.setText("");
            Search.setForeground(java.awt.Color.BLACK);
        }
    }//GEN-LAST:event_SearchFocusGained

    private void SearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SearchFocusLost
        if (Search.getText().trim().isEmpty()) {
            Search.setText(PLACEHOLDER_TEXT);
            Search.setForeground(java.awt.Color.GRAY);
        }
    }//GEN-LAST:event_SearchFocusLost

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        loadStudentData();
    }//GEN-LAST:event_SearchActionPerformed

    private void CollegeCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CollegeCBActionPerformed
        loadStudentData();
    }//GEN-LAST:event_CollegeCBActionPerformed

    private void AddStudentCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddStudentCMouseClicked
        javax.swing.JFrame parentFrame = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
        java.awt.EventQueue.invokeLater(() -> {
            AddStudent dialog = new AddStudent(parentFrame, true, this);
            dialog.setLocationRelativeTo(parentFrame);
            dialog.setVisible(true);
        });
    }//GEN-LAST:event_AddStudentCMouseClicked

    private void AutoPromoteCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AutoPromoteCMouseClicked

    }//GEN-LAST:event_AutoPromoteCMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddStudentC;
    private javax.swing.JLabel AutoPromoteC;
    private javax.swing.JComboBox<String> CollegeCB;
    private javax.swing.JTextField Search;
    private javax.swing.JLabel StudentMBG;
    private javax.swing.JScrollPane StudentScrollPane;
    private javax.swing.JTable StudentTable;
    // End of variables declaration//GEN-END:variables
}
