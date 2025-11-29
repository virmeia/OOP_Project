package com.udj.gui;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
//import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminDB extends javax.swing.JFrame {
    private static final String STUDENTS_FILE = "/data/StudentsData.txt";
    private static final String FACULTY_FILE = "/data/FacultysData.txt";
    private static final String COURSES_FILE = "/data/Courses.txt";
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminDB.class.getName());
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public AdminDB() {
       initComponents();

       updateDashboardData();

       cardLayout = new CardLayout();
       contentPanel = new JPanel(cardLayout);
       contentPanel.setOpaque(false);
       contentPanel.setBackground(new Color(244, 232, 250));
       contentPanel.setBounds(0, 0, 1000, 600);

       contentPanel.add(new StudentManagement(this), "STUDENT_MANAGEMENT");
       contentPanel.add(new FacultyManagement(this), "FACULTY_MANAGEMENT");
       // Add these when you create the panels
       // contentPanel.add(new CourseManagement(this), "COURSES_AND_CURRICULUM");
       // contentPanel.add(new SystemManagement(this), "SYSTEM_AND_ACCOUNT");


       BGPanel.add(contentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));
       contentPanel.setVisible(false);
       JLayeredPane layeredPane = getLayeredPane();
       layeredPane.add(contentPanel, JLayeredPane.PALETTE_LAYER);
       
       contentPanel.setVisible(false);
       hideAllHighlights();
            
    }
    
    private int countLinesInFile(String resourcePath) {
        int lineCount = 0;
        
        // 1. Get the file as an InputStream from the ClassLoader
        try (java.io.InputStream is = AdminDB.class.getResourceAsStream(resourcePath)) {
            
            if (is == null) {
                logger.severe("Resource not found in classpath: " + resourcePath);
                return 0;
            }

            // 2. Read the InputStream line by line using an InputStreamReader
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                while (reader.readLine() != null) {
                    lineCount++;
                }
            }
        } catch (IOException e) {
            // This catch block handles reading errors after the resource is successfully found
            logger.severe("Error reading resource " + resourcePath + ": " + e.getMessage());
            return 0;
        }
        return lineCount;
    }

    private void updateDashboardData() {
        int studentCount = countLinesInFile(STUDENTS_FILE);
        int facultyCount = countLinesInFile(FACULTY_FILE); // <-- This is the file line count
        int courseCount = countLinesInFile(COURSES_FILE);
        
        TotalStudents.setText(String.valueOf(studentCount));
        TotalFaculty.setText(String.valueOf(facultyCount));
        TotalCourses.setText(String.valueOf(courseCount));
    }
    

    
     public void showPanel(String name) {
        setMainNavigationVisible(false);
        contentPanel.setVisible(true);
        
        TotalStudents.setVisible(false); 
        TotalFaculty.setVisible(false);
        TotalCourses.setVisible(false);
        
        cardLayout.show(contentPanel, name);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    public void showDashboard() {
        contentPanel.setVisible(false);
        setMainNavigationVisible(true);
        hideAllHighlights();
        
        TotalStudents.setVisible(true);
        TotalFaculty.setVisible(true);
        TotalCourses.setVisible(true);
        
        updateDashboardData();
    }
    
    private void setMainNavigationVisible(boolean visible) {
        StudentC.setVisible(visible);
        FacultyC.setVisible(visible);
        CoursesnCurriC.setVisible(visible);
        SystemC.setVisible(visible);
        if (!visible) {
            hideAllHighlights();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BGPanel = new javax.swing.JPanel();
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
        TotalStudents = new javax.swing.JTextField();
        TotalFaculty = new javax.swing.JTextField();
        TotalCourses = new javax.swing.JTextField();
        AdminBG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BGPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DashboardC.setBorderPainted(false);
        DashboardC.setContentAreaFilled(false);
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
        BGPanel.add(DashboardC, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 160, 40));

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
        BGPanel.add(StudentC, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 164, 57));

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
        BGPanel.add(FacultyC, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 157, 37));

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
        BGPanel.add(CoursesnCurriC, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, 160, 40));

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
        BGPanel.add(SystemC, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 470, 160, 40));

        DashboardC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DashboardAdminC1.png"))); // NOI18N
        DashboardC1.setBorderPainted(false);
        DashboardC1.setContentAreaFilled(false);
        BGPanel.add(DashboardC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 227, 160, 40));

        StudentC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/StudentAdminC1.png"))); // NOI18N
        StudentC1.setBorderPainted(false);
        StudentC1.setContentAreaFilled(false);
        StudentC1.setFocusable(false);
        BGPanel.add(StudentC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 280, 180, -1));

        FacultyC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FacultyAdminC1.png"))); // NOI18N
        FacultyC1.setBorder(null);
        FacultyC1.setBorderPainted(false);
        FacultyC1.setContentAreaFilled(false);
        FacultyC1.setFocusable(false);
        BGPanel.add(FacultyC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 347, 163, 46));

        CoursesnCurriC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/CourseAdminC1.png"))); // NOI18N
        CoursesnCurriC1.setBorderPainted(false);
        CoursesnCurriC1.setContentAreaFilled(false);
        BGPanel.add(CoursesnCurriC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 409, 170, 40));

        SystemC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SystemAdminC1.png"))); // NOI18N
        SystemC1.setBorderPainted(false);
        SystemC1.setContentAreaFilled(false);
        BGPanel.add(SystemC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 471, 160, 40));

        TotalStudents.setEditable(false);
        TotalStudents.setBackground(new java.awt.Color(185, 154, 202));
        TotalStudents.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TotalStudents.setForeground(new java.awt.Color(51, 0, 51));
        TotalStudents.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotalStudents.setBorder(null);
        TotalStudents.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BGPanel.add(TotalStudents, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, 53, 18));

        TotalFaculty.setBackground(new java.awt.Color(185, 154, 202));
        TotalFaculty.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TotalFaculty.setForeground(new java.awt.Color(51, 0, 51));
        TotalFaculty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotalFaculty.setBorder(null);
        TotalFaculty.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TotalFaculty.setEnabled(false);
        TotalFaculty.setSelectionColor(new java.awt.Color(51, 0, 51));
        TotalFaculty.addActionListener(this::TotalFacultyActionPerformed);
        BGPanel.add(TotalFaculty, new org.netbeans.lib.awtextra.AbsoluteConstraints(805, 415, 53, 18));

        TotalCourses.setBackground(new java.awt.Color(185, 154, 202));
        TotalCourses.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TotalCourses.setForeground(new java.awt.Color(51, 0, 51));
        TotalCourses.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotalCourses.setBorder(null);
        TotalCourses.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TotalCourses.setEnabled(false);
        BGPanel.add(TotalCourses, new org.netbeans.lib.awtextra.AbsoluteConstraints(593, 415, 53, 18));

        AdminBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dashboard.png"))); // NOI18N
        BGPanel.add(AdminBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        getContentPane().add(BGPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StudentCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StudentCMouseClicked
        showPanel("STUDENT_MANAGEMENT");
    }//GEN-LAST:event_StudentCMouseClicked

    private void StudentCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StudentCMouseEntered
        StudentC1.setVisible(true);
    }//GEN-LAST:event_StudentCMouseEntered

    private void StudentCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StudentCMouseExited
       StudentC1.setVisible(false);
    }//GEN-LAST:event_StudentCMouseExited

    private void FacultyCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FacultyCMouseClicked
        showPanel("FACULTY_MANAGEMENT");
    }//GEN-LAST:event_FacultyCMouseClicked

    private void FacultyCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FacultyCMouseEntered
        FacultyC1.setVisible(true);
    }//GEN-LAST:event_FacultyCMouseEntered

    private void FacultyCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FacultyCMouseExited
        FacultyC1.setVisible(false);
    }//GEN-LAST:event_FacultyCMouseExited

    private void CoursesnCurriCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CoursesnCurriCMouseClicked
        showPanel("COURSES_AND_CURRICULUM");
    }//GEN-LAST:event_CoursesnCurriCMouseClicked

    private void CoursesnCurriCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CoursesnCurriCMouseEntered
        CoursesnCurriC1.setVisible(true);
    }//GEN-LAST:event_CoursesnCurriCMouseEntered

    private void CoursesnCurriCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CoursesnCurriCMouseExited
        CoursesnCurriC1.setVisible(false);
    }//GEN-LAST:event_CoursesnCurriCMouseExited

    private void SystemCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SystemCMouseClicked
        showPanel("SYSTEM_AND_ACCOUNT");
    }//GEN-LAST:event_SystemCMouseClicked

    private void SystemCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SystemCMouseEntered
        SystemC1.setVisible(true);
    }//GEN-LAST:event_SystemCMouseEntered

    private void SystemCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SystemCMouseExited
        SystemC1.setVisible(false);
    }//GEN-LAST:event_SystemCMouseExited

    private void DashboardCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardCMouseClicked
        
    }//GEN-LAST:event_DashboardCMouseClicked

    private void DashboardCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardCMouseEntered
        DashboardC1.setVisible(true);
    }//GEN-LAST:event_DashboardCMouseEntered

    private void DashboardCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardCMouseExited
        DashboardC1.setVisible(false);
    }//GEN-LAST:event_DashboardCMouseExited

    private void TotalFacultyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalFacultyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalFacultyActionPerformed

    private void hideAllHighlights() {
        StudentC1.setVisible(false);
        FacultyC1.setVisible(false);
        CoursesnCurriC1.setVisible(false);
        SystemC1.setVisible(false);
    }
    
    private boolean isCurrentPanel(String panelName) {
        Component[] components = contentPanel.getComponents();
        for (Component comp : components) {
            if (comp.isVisible()) {
                if (panelName.equals("STUDENT_MANAGEMENT") && comp instanceof StudentManagement) {
                    return true;
                }
                if (panelName.equals("FACULTY_MANAGEMENT") && comp instanceof FacultyManagement) {
                    return true;
                }
                /*if (panelName.equals("COURSES_AND_CURRICULUM") && comp instanceof CoursesnCurriculum) {
                    return true;
                }
                if (panelName.equals("SYSTEM_AND_ACCOUNT") && comp instanceof SystemnAccount) {
                    return true;
                }*/
            }
        }
        return false;
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new AdminDB().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AdminBG;
    private javax.swing.JPanel BGPanel;
    private javax.swing.JButton CoursesnCurriC;
    private javax.swing.JButton CoursesnCurriC1;
    private javax.swing.JButton DashboardC;
    private javax.swing.JButton DashboardC1;
    private javax.swing.JButton FacultyC;
    private javax.swing.JButton FacultyC1;
    private javax.swing.JButton StudentC;
    private javax.swing.JButton StudentC1;
    private javax.swing.JButton SystemC;
    private javax.swing.JButton SystemC1;
    private javax.swing.JTextField TotalCourses;
    private javax.swing.JTextField TotalFaculty;
    private javax.swing.JTextField TotalStudents;
    // End of variables declaration//GEN-END:variables
}
