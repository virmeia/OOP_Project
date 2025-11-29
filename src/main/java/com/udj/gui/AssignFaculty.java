package com.udj.gui;

import com.udj.gui.components.CustomComboBox;
import com.udj.logic.CourseManager;
import com.udj.logic.FacultyAssignmentManager;
import com.udj.logic.FacultyManager;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AssignFaculty extends javax.swing.JDialog {
    
    private FacultyManagement parentPanel;
    private String selectedFacultyId;
    private String selectedFacultyName;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AssignFaculty.class.getName());

    public AssignFaculty(java.awt.Frame parent, boolean modal, FacultyManagement facultyPanel) {
        super(parent, modal);
        this.parentPanel = facultyPanel;
        initComponents();
        CustomComboBox.applyStyle(CourseCodeCB);
        CustomComboBox.applyStyle(DaysCB);
        CustomComboBox.applyStyle(FacultyIDCB);
        loadFacultyList();
        Back1.setVisible(false);
    }
    
    private void loadFacultyList() {
        FacultyIDCB.removeAllItems();
        FacultyIDCB.addItem("- Select Faculty -");
        
        List<String[]> faculty = FacultyManager.getAllFaculty();
        for (String[] fac : faculty) {
            String display = fac[0] + " - " + fac[1]; // ID - Name
            FacultyIDCB.addItem(display);
        }
    }
    
    private void loadCoursesForFaculty() {
        CourseCodeCB.removeAllItems();
        CourseCodeCB.addItem("- Select Course -");
        
        if (selectedFacultyId == null || selectedFacultyId.isEmpty()) {
            return;
        }
        
        String[] facultyData = FacultyManager.getFaculty(selectedFacultyId);
        if (facultyData == null) return;
        
        String department = facultyData[2]; // Department
        
        List<String[]> courses = CourseManager.getCoursesByDepartment(department);
        for (String[] course : courses) {
            CourseCodeCB.addItem(course[0]); // Course Code
        }
    }
    
    private void updateCourseDetails() {
        String courseCode = (String) CourseCodeCB.getSelectedItem();
        if (courseCode == null || courseCode.equals("- Select Course -")) {
            CourseName.setText("");
            Program.setText("");
            Units.setText("");
            return;
        }
        
        String[] course = CourseManager.getCourse(courseCode);
        if (course != null) {
            CourseName.setText(course[1]); // Course Name
            Program.setText(course[2]);     // Program
            Units.setText(course[3]);       // Units
        }
    }

    private boolean validateSchedule() {
        String facultyId = selectedFacultyId;
        String days = (String) DaysCB.getSelectedItem();
        String startTime = StartTime.getText().trim();
        String endTime = EndTime.getText().trim();
        
        if (days.equals("- Select Days -") || startTime.isEmpty() || endTime.isEmpty()) {
            return true; // Will be caught by field validation
        }
        
        // Check time format (HH:MM)
        if (!startTime.matches("\\d{2}:\\d{2}") || !endTime.matches("\\d{2}:\\d{2}")) {
            JOptionPane.showMessageDialog(this,
                "Invalid time format. Use HH:MM (e.g., 09:00)",
                "Invalid Time", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Check if schedule overlaps with existing assignments
        if (FacultyAssignmentManager.hasScheduleConflict(facultyId, days, startTime, endTime)) {
            JOptionPane.showMessageDialog(this,
                "Schedule conflict detected!\nThis faculty member already has a class at this time.",
                "Schedule Conflict", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        BGPanel = new javax.swing.JPanel();
        Back = new javax.swing.JButton();
        Back1 = new javax.swing.JLabel();
        Header = new javax.swing.JLabel();
        FacultyIDCB = new javax.swing.JComboBox<>();
        CourseCodeCB = new javax.swing.JComboBox<>();
        CourseName = new javax.swing.JTextField();
        Program = new javax.swing.JTextField();
        Units = new javax.swing.JTextField();
        DaysCB = new javax.swing.JComboBox<>();
        StartTime = new javax.swing.JTextField();
        EndTime = new javax.swing.JTextField();
        Room = new javax.swing.JTextField();
        Clear = new javax.swing.JButton();
        Assign = new javax.swing.JButton();
        AssignBG = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        BGPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Back.setBorderPainted(false);
        Back.setContentAreaFilled(false);
        Back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BackMouseExited(evt);
            }
        });
        BGPanel.add(Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 10, 30, 20));

        Back1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BackIconSmall.png"))); // NOI18N
        BGPanel.add(Back1, new org.netbeans.lib.awtextra.AbsoluteConstraints(509, 6, -1, 30));

        Header.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Header.setForeground(new java.awt.Color(255, 255, 255));
        Header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Header.setText("ASSIGN FACULTY");
        BGPanel.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 40));

        FacultyIDCB.setBackground(new java.awt.Color(242, 216, 255));
        FacultyIDCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Select Faculty -", " " }));
        FacultyIDCB.addActionListener(this::FacultyIDCBActionPerformed);
        BGPanel.add(FacultyIDCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 82, 317, 21));

        CourseCodeCB.setBackground(new java.awt.Color(242, 216, 255));
        CourseCodeCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Select Course -", " " }));
        CourseCodeCB.addActionListener(this::CourseCodeCBActionPerformed);
        BGPanel.add(CourseCodeCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 110, 317, 21));

        CourseName.setBorder(null);
        BGPanel.add(CourseName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 138, 290, 18));

        Program.setBorder(null);
        BGPanel.add(Program, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 165, 290, 18));

        Units.setBorder(null);
        BGPanel.add(Units, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 193, 290, 18));

        DaysCB.setBackground(new java.awt.Color(242, 216, 255));
        DaysCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Select Days -", "MWF", "TTH", "SAT", "M", "T", "W", "TH", "F", " " }));
        BGPanel.add(DaysCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 219, 317, 21));

        StartTime.setBorder(null);
        StartTime.addActionListener(this::StartTimeActionPerformed);
        BGPanel.add(StartTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 248, 290, 18));

        EndTime.setBorder(null);
        BGPanel.add(EndTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 275, 290, 18));

        Room.setBorder(null);
        BGPanel.add(Room, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 302, 290, 18));

        Clear.setBorder(null);
        Clear.setBorderPainted(false);
        Clear.setContentAreaFilled(false);
        Clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClearMouseClicked(evt);
            }
        });
        BGPanel.add(Clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 359, 84, 24));

        Assign.setBorder(null);
        Assign.setContentAreaFilled(false);
        Assign.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AssignMouseClicked(evt);
            }
        });
        Assign.addActionListener(this::AssignActionPerformed);
        BGPanel.add(Assign, new org.netbeans.lib.awtextra.AbsoluteConstraints(427, 358, 87, 25));

        AssignBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AssignFaculty.png"))); // NOI18N
        BGPanel.add(AssignBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 400));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BGPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BGPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FacultyIDCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FacultyIDCBActionPerformed
        String selected = (String) FacultyIDCB.getSelectedItem();
        if (selected != null && !selected.equals("- Select Faculty -")) {
            String[] parts = selected.split(" - ");
            selectedFacultyId = parts[0];
            selectedFacultyName = parts[1];
            loadCoursesForFaculty();
        }
    }//GEN-LAST:event_FacultyIDCBActionPerformed

    private void CourseCodeCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CourseCodeCBActionPerformed
        updateCourseDetails();
    }//GEN-LAST:event_CourseCodeCBActionPerformed

    private void AssignMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AssignMouseClicked
        String facultyId = selectedFacultyId;
        String courseCode = (String) CourseCodeCB.getSelectedItem();
        String courseName = CourseName.getText().trim();
        String program = Program.getText().trim();
        String units = Units.getText().trim();
        String days = (String) DaysCB.getSelectedItem();
        String startTime = StartTime.getText().trim();
        String endTime = EndTime.getText().trim();
        String room = Room.getText().trim();
        
        if (facultyId == null || courseCode.equals("- Select Course -") || 
            days.equals("- Select Days -") || startTime.isEmpty() || 
            endTime.isEmpty() || room.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please complete all fields before assigning.",
                "Incomplete Information", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!validateSchedule()) {
            return;
        }
        
        String schedule = days + " " + startTime + "-" + endTime + " " + room;
        
        boolean success = FacultyAssignmentManager.assignCourse(
            facultyId, courseCode, courseName, program, units, schedule
        );
        
        if (success) {
            JOptionPane.showMessageDialog(this,
                "Course assigned successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
            if (parentPanel != null) {
                parentPanel.refreshTable();
            }
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this,
                "Failed to assign course.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_AssignMouseClicked

    private void ClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClearMouseClicked
        clearFields();
    }//GEN-LAST:event_ClearMouseClicked

    private void AssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssignActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AssignActionPerformed

    private void StartTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StartTimeActionPerformed

    private void BackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackMouseClicked
        Back1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BackMouseClicked

    private void BackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackMouseEntered
        Back1.setVisible(true);
    }//GEN-LAST:event_BackMouseEntered

    private void BackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackMouseExited
        Back1.setVisible(false);
    }//GEN-LAST:event_BackMouseExited

    private void clearFields() {
        FacultyIDCB.setSelectedIndex(0);
        CourseCodeCB.setSelectedIndex(0);
        CourseName.setText("");
        Program.setText("");
        Units.setText("");
        DaysCB.setSelectedIndex(0);
        StartTime.setText("00:00");
        EndTime.setText("00:00");
        Room.setText("");
        selectedFacultyId = null;
        selectedFacultyName = null;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Assign;
    private javax.swing.JLabel AssignBG;
    private javax.swing.JPanel BGPanel;
    private javax.swing.JButton Back;
    private javax.swing.JLabel Back1;
    private javax.swing.JButton Clear;
    private javax.swing.JComboBox<String> CourseCodeCB;
    private javax.swing.JTextField CourseName;
    private javax.swing.JComboBox<String> DaysCB;
    private javax.swing.JTextField EndTime;
    private javax.swing.JComboBox<String> FacultyIDCB;
    private javax.swing.JLabel Header;
    private javax.swing.JTextField Program;
    private javax.swing.JTextField Room;
    private javax.swing.JTextField StartTime;
    private javax.swing.JTextField Units;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
