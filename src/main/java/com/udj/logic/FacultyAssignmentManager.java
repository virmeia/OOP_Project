package com.udj.logic;

import java.io.*;
import java.util.*;

public class FacultyAssignmentManager {
    
    private static final String ASSIGNMENTS_FILE = "data/faculty_assignments.txt";
    
    static {
        ensureDataDirectory();
    }
    
    private static void ensureDataDirectory() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }
    
    /**
     * Assign a course to faculty
     * Format: FacultyID|CourseCode|CourseName|Program|Units|Schedule
     */
    public static boolean assignCourse(String facultyId, String courseCode, 
                                      String courseName, String program, 
                                      String units, String schedule) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ASSIGNMENTS_FILE, true))) {
            String line = String.join("|", facultyId, courseCode, courseName, program, units, schedule);
            writer.write(line);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Get all assignments for a specific faculty
     */
    public static List<String[]> getAssignmentsByFaculty(String facultyId) {
        List<String[]> assignments = new ArrayList<>();
        File file = new File(ASSIGNMENTS_FILE);
        
        if (!file.exists()) {
            return assignments;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 6 && parts[0].equals(facultyId)) {
                    // Return: CourseCode, CourseName, Program, Units, Schedule
                    assignments.add(new String[]{parts[1], parts[2], parts[3], parts[4], parts[5]});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return assignments;
    }
    
    /**
     * Get all assignments
     */
    public static List<String[]> getAllAssignments() {
        List<String[]> assignments = new ArrayList<>();
        File file = new File(ASSIGNMENTS_FILE);
        
        if (!file.exists()) {
            return assignments;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    assignments.add(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return assignments;
    }
    
    /**
     * Check for schedule conflicts
     */
    public static boolean hasScheduleConflict(String facultyId, String days, 
                                             String startTime, String endTime) {
        List<String[]> assignments = getAssignmentsByFaculty(facultyId);
        
        for (String[] assignment : assignments) {
            String schedule = assignment[4]; // Schedule format: "MWF 09:00-10:30 Room101"
            
            String[] scheduleParts = schedule.split(" ");
            if (scheduleParts.length < 2) continue;
            
            String existingDays = scheduleParts[0];
            String existingTimes = scheduleParts[1];
            
            // Check if days overlap
            if (daysOverlap(days, existingDays)) {
                // Check if times overlap
                String[] existingTimeRange = existingTimes.split("-");
                if (existingTimeRange.length == 2) {
                    if (timesOverlap(startTime, endTime, existingTimeRange[0], existingTimeRange[1])) {
                        return true; // Conflict found
                    }
                }
            }
        }
        
        return false; // No conflict
    }
    
    /**
     * Check if two day strings have overlapping days
     */
    private static boolean daysOverlap(String days1, String days2) {
        for (char c : days1.toCharArray()) {
            if (days2.indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Check if two time ranges overlap
     */
    private static boolean timesOverlap(String start1, String end1, String start2, String end2) {
        try {
            int s1 = timeToMinutes(start1);
            int e1 = timeToMinutes(end1);
            int s2 = timeToMinutes(start2);
            int e2 = timeToMinutes(end2);
            
            // Check for overlap: (start1 < end2) AND (end1 > start2)
            return (s1 < e2) && (e1 > s2);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Convert time string (HH:MM) to minutes since midnight
     */
    private static int timeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
    
    /**
     * Remove an assignment
     */
    public static boolean removeAssignment(String facultyId, String courseCode) {
        List<String[]> allAssignments = getAllAssignments();
        boolean removed = allAssignments.removeIf(assignment -> 
            assignment[0].equals(facultyId) && assignment[1].equals(courseCode)
        );
        
        if (removed) {
            return writeAllAssignments(allAssignments);
        }
        
        return false;
    }
    
    /**
     * Write all assignments to file
     */
    private static boolean writeAllAssignments(List<String[]> assignments) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ASSIGNMENTS_FILE))) {
            for (String[] assignment : assignments) {
                String line = String.join("|", assignment);
                writer.write(line);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}