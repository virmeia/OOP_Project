package com.udj.gui;

import com.udj.gui.components.FActionCellEditor;
import com.udj.gui.components.ActionCellRenderer;
import com.udj.gui.components.CustomComboBox;
import com.udj.gui.components.CustomScrollBarUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JScrollBar;
import javax.swing.border.LineBorder;
import com.udj.logic.FacultyManager;
import javax.swing.table.DefaultTableModel;

public class FacultyManagement extends javax.swing.JPanel {

    private static final String PLACEHOLDER_TEXT = "Search Name or ID";
    private AdminDB parentFrame;
    
    public FacultyManagement(AdminDB parentFrame) {
        this.parentFrame = parentFrame;
        initComponents();

        
        styleTable();
        styleScrollBar();
        FacultyScrollPane.setBorder(null);
        FacultyScrollPane.getViewport().setOpaque(true);
        FacultyScrollPane.getViewport().setBackground(Color.decode("#F4E8FA")); 
        FacultyScrollPane.setBorder(BorderFactory.createEmptyBorder());
        CustomComboBox.applyStyle(CollegeCB);
        CollegeCB.setBorder(new LineBorder(new Color(0x8750A6), 1, true));
        loadFacultyData();
        setupActionColumn(); 
        
        DashboardC1.setVisible(false);
        StudentC1.setVisible(false);
        
        Search.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) { loadFacultyData(); }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) { loadFacultyData(); }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) { loadFacultyData(); }
        });
    }
    
    private void setupActionColumn() {
        FacultyTable.getColumnModel().getColumn(5).setCellRenderer(new ActionCellRenderer());
        FacultyTable.getColumnModel().getColumn(5).setCellEditor(new FActionCellEditor(FacultyTable, this));
        FacultyTable.setRowHeight(30);
    }
    
    private void loadFacultyData() {
        DefaultTableModel model = (DefaultTableModel) FacultyTable.getModel();
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
        if (searchText.equals(PLACEHOLDER_TEXT)) {
            searchText = "";
        }
        searchText = searchText.toLowerCase();
        
        // TODO: Replace this with actual FacultyManager call when available
        java.util.List<String[]> faculty = FacultyManager.getAllFaculty();
        
        for (String[] fac : faculty) {
            String id = fac[0];
            String name = fac[1];
            String facultyCollege = fac[4]; 

            boolean matchesCollege = "ALL".equals(targetCollegeName) || facultyCollege.equals(targetCollegeName);
            boolean matchesSearch = searchText.isEmpty() || 
                                    id.toLowerCase().contains(searchText) || 
                                    name.toLowerCase().contains(searchText);

            if (matchesCollege && matchesSearch) {
                Object[] row = {
                    fac[0],  // Faculty ID
                    fac[1],  // Name
                    fac[2],  // Department
                    fac[3],  // Employment Type
                    fac[4],  // College
                    "⋮"      // Actions
                };
                model.addRow(row);
            }
        }   
    }
    
    public void refreshTable() {
        loadFacultyData();
        FacultyTable.revalidate();
        FacultyTable.repaint();
    }
    
    private void styleScrollBar() {
        JScrollBar verticalBar = FacultyScrollPane.getVerticalScrollBar();
        verticalBar.setUI(new CustomScrollBarUI());
        verticalBar.setPreferredSize(new Dimension(5, Integer.MAX_VALUE));

        JScrollBar horizontalBar = FacultyScrollPane.getHorizontalScrollBar();
        horizontalBar.setUI(new CustomScrollBarUI());
        horizontalBar.setPreferredSize(new Dimension(Integer.MAX_VALUE, 10));
    }
    
    private void styleTable() {
        FacultyTable.getTableHeader().setOpaque(false);
        FacultyTable.getTableHeader().setBackground(Color.decode("#8f6da0")); 
        FacultyTable.getTableHeader().setForeground(Color.WHITE);
        FacultyTable.getTableHeader().setFont(new java.awt.Font("Arial", Font.BOLD, 12));
        FacultyTable.setBackground(Color.decode("#F4E8FA"));
        FacultyTable.setForeground(Color.decode("#2E1448"));
        FacultyTable.setFont(new java.awt.Font("Arial", Font.PLAIN, 12));
        FacultyTable.setSelectionBackground(Color.decode("#C8A8D8"));
        FacultyTable.setSelectionForeground(Color.decode("#2E1448"));
        FacultyTable.setShowGrid(true);
        FacultyTable.setGridColor(new Color(140, 104, 160, 25)); 
        FacultyTable.setIntercellSpacing(new java.awt.Dimension(4, 1));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FacultyScrollPane = new javax.swing.JScrollPane();
        FacultyTable = new javax.swing.JTable();
        CollegeCB = new javax.swing.JComboBox<>();
        Search = new javax.swing.JTextField();
        AssignFaculty = new javax.swing.JButton();
        AddFaculty = new javax.swing.JButton();
        DashboardC = new javax.swing.JButton();
        StudentC = new javax.swing.JButton();
        FacultyC = new javax.swing.JButton();
        CoursesnCurriC = new javax.swing.JButton();
        SystemC = new javax.swing.JButton();
        DashboardC1 = new javax.swing.JButton();
        StudentC1 = new javax.swing.JButton();
        FacultyC1 = new javax.swing.JButton();
        CoursesnCurriC1 = new javax.swing.JButton();
        SystemC1 = new javax.swing.JButton();
        FacultyBG = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FacultyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Faculty ID", "Name", "Department", "Employment Type", "College", "Actions"
            }
        ));
        FacultyScrollPane.setViewportView(FacultyTable);

        add(FacultyScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 216, 696, 344));

        CollegeCB.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        CollegeCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL", "CLA", "COE", "COS", " " }));
        CollegeCB.addActionListener(this::CollegeCBActionPerformed);
        add(CollegeCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 70, 18));

        Search.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        Search.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Search.setText("Search Name or ID");
        Search.setBorder(null);
        Search.setCaretColor(new java.awt.Color(51, 0, 51));
        Search.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        Search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                SearchFocusLost(evt);
            }
        });
        Search.addActionListener(this::SearchActionPerformed);
        add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 103, 130, 17));

        AssignFaculty.setBorderPainted(false);
        AssignFaculty.setContentAreaFilled(false);
        AssignFaculty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AssignFacultyMouseClicked(evt);
            }
        });
        add(AssignFaculty, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 170, 95, 24));

        AddFaculty.setBorderPainted(false);
        AddFaculty.setContentAreaFilled(false);
        AddFaculty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddFacultyMouseClicked(evt);
            }
        });
        add(AddFaculty, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 170, 80, 24));

        DashboardC.setBorderPainted(false);
        DashboardC.setContentAreaFilled(false);
        DashboardC.setFocusPainted(false);
        DashboardC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DashboardCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DashboardCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DashboardCMouseExited(evt);
            }
        });
        add(DashboardC, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 162, 37));

        StudentC.setBorderPainted(false);
        StudentC.setContentAreaFilled(false);
        StudentC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StudentCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                StudentCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                StudentCMouseExited(evt);
            }
        });
        add(StudentC, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 160, 40));

        FacultyC.setBorderPainted(false);
        FacultyC.setContentAreaFilled(false);
        FacultyC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FacultyCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                FacultyCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                FacultyCMouseExited(evt);
            }
        });
        add(FacultyC, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 160, 40));

        CoursesnCurriC.setBorderPainted(false);
        CoursesnCurriC.setContentAreaFilled(false);
        CoursesnCurriC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CoursesnCurriCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CoursesnCurriCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CoursesnCurriCMouseExited(evt);
            }
        });
        add(CoursesnCurriC, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, 160, 40));

        SystemC.setBorderPainted(false);
        SystemC.setContentAreaFilled(false);
        SystemC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SystemCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SystemCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SystemCMouseExited(evt);
            }
        });
        add(SystemC, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 470, 160, 40));

        DashboardC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DashboardAdminC1.png"))); // NOI18N
        DashboardC1.setBorderPainted(false);
        DashboardC1.setContentAreaFilled(false);
        add(DashboardC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 162, 37));

        StudentC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/StudentAdminC1.png"))); // NOI18N
        StudentC1.setBorderPainted(false);
        StudentC1.setContentAreaFilled(false);
        add(StudentC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 290, 162, 37));

        FacultyC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FacultyAdminC1.png"))); // NOI18N
        FacultyC1.setBorderPainted(false);
        FacultyC1.setContentAreaFilled(false);
        add(FacultyC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 351, 160, 40));

        CoursesnCurriC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/CourseAdminC1.png"))); // NOI18N
        CoursesnCurriC1.setBorderPainted(false);
        CoursesnCurriC1.setContentAreaFilled(false);
        add(CoursesnCurriC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 409, 160, 40));

        SystemC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SystemAdminC1.png"))); // NOI18N
        SystemC1.setBorderPainted(false);
        SystemC1.setContentAreaFilled(false);
        add(SystemC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 472, 160, 38));

        FacultyBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FMBG.png"))); // NOI18N
        add(FacultyBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void CollegeCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CollegeCBActionPerformed
        loadFacultyData();
    }//GEN-LAST:event_CollegeCBActionPerformed

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
        loadFacultyData();  
    }//GEN-LAST:event_SearchActionPerformed

    private void DashboardCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardCMouseClicked
        parentFrame.showDashboard();
    }//GEN-LAST:event_DashboardCMouseClicked

    private void DashboardCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardCMouseEntered
        DashboardC1.setVisible(true);
        DashboardC1.repaint();
    }//GEN-LAST:event_DashboardCMouseEntered

    private void DashboardCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardCMouseExited
        DashboardC1.setVisible(false);
    }//GEN-LAST:event_DashboardCMouseExited

    private void StudentCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StudentCMouseClicked
        parentFrame.showPanel("STUDENT_MANAGEMENT");
    }//GEN-LAST:event_StudentCMouseClicked

    private void StudentCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StudentCMouseEntered
        StudentC1.setVisible(true);
        StudentC1.repaint();
    }//GEN-LAST:event_StudentCMouseEntered

    private void StudentCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StudentCMouseExited
        StudentC1.setVisible(false);
    }//GEN-LAST:event_StudentCMouseExited

    private void AddFacultyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddFacultyMouseClicked
        java.awt.EventQueue.invokeLater(() -> {
            AddFaculty dialog = new AddFaculty(parentFrame, true, this);
            dialog.setLocationRelativeTo(parentFrame);
            dialog.setVisible(true);
        });
    }//GEN-LAST:event_AddFacultyMouseClicked

    private void AssignFacultyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AssignFacultyMouseClicked
        java.awt.EventQueue.invokeLater(() -> {
            AssignFaculty dialog = new AssignFaculty(parentFrame, true, this);
            dialog.setLocationRelativeTo(parentFrame);
            dialog.setVisible(true);
        });
    }//GEN-LAST:event_AssignFacultyMouseClicked

    private void FacultyCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FacultyCMouseClicked
        parentFrame.showPanel("FACULTY_MANAGEMENT");
        refreshTable();
    }//GEN-LAST:event_FacultyCMouseClicked

    private void FacultyCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FacultyCMouseEntered
        FacultyC1.setVisible(true);
        FacultyC1.repaint();
    }//GEN-LAST:event_FacultyCMouseEntered

    private void FacultyCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FacultyCMouseExited
        FacultyC1.setVisible(false);
    }//GEN-LAST:event_FacultyCMouseExited

    private void CoursesnCurriCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CoursesnCurriCMouseClicked
        parentFrame.showPanel("COURSES_AND_CURRICULUM");
    }//GEN-LAST:event_CoursesnCurriCMouseClicked

    private void CoursesnCurriCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CoursesnCurriCMouseEntered
        CoursesnCurriC1.setVisible(true);
        CoursesnCurriC1.repaint();
    }//GEN-LAST:event_CoursesnCurriCMouseEntered

    private void CoursesnCurriCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CoursesnCurriCMouseExited
        CoursesnCurriC1.setVisible(false);
    }//GEN-LAST:event_CoursesnCurriCMouseExited

    private void SystemCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SystemCMouseClicked
        parentFrame.showPanel("SYSTEM_AND_ACCOUNT");
    }//GEN-LAST:event_SystemCMouseClicked

    private void SystemCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SystemCMouseEntered
        SystemC1.setVisible(true);
        SystemC1.repaint();
    }//GEN-LAST:event_SystemCMouseEntered

    private void SystemCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SystemCMouseExited
        SystemC1.setVisible(false);
    }//GEN-LAST:event_SystemCMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddFaculty;
    private javax.swing.JButton AssignFaculty;
    private javax.swing.JComboBox<String> CollegeCB;
    private javax.swing.JButton CoursesnCurriC;
    private javax.swing.JButton CoursesnCurriC1;
    private javax.swing.JButton DashboardC;
    private javax.swing.JButton DashboardC1;
    private javax.swing.JLabel FacultyBG;
    private javax.swing.JButton FacultyC;
    private javax.swing.JButton FacultyC1;
    private javax.swing.JScrollPane FacultyScrollPane;
    private javax.swing.JTable FacultyTable;
    private javax.swing.JTextField Search;
    private javax.swing.JButton StudentC;
    private javax.swing.JButton StudentC1;
    private javax.swing.JButton SystemC;
    private javax.swing.JButton SystemC1;
    // End of variables declaration//GEN-END:variables
}
