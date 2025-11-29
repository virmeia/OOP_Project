package com.udj.logic;

import java.io.*;
import java.util.*;

public class CourseManager {
    
    private static final String COURSES_FILE = "data/Courses.txt";
    
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
     * Save a new course
     * Format: CourseCode|CourseName|Program|Units|Department
     */
    public static boolean saveCourse(String courseCode, String courseName, 
                                    String program, String units, String department) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COURSES_FILE, true))) {
            String line = String.join("|", courseCode, courseName, program, units, department);
            writer.write(line);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Get all courses
     */
    public static List<String[]> getAllCourses() {
        List<String[]> courses = new ArrayList<>();
        File file = new File(COURSES_FILE);
        
        if (!file.exists()) {
            return courses;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    courses.add(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return courses;
    }
    
    /**
     * Get courses by department
     */
    public static List<String[]> getCoursesByDepartment(String department) {
        List<String[]> courses = new ArrayList<>();
        
        for (String[] course : getAllCourses()) {
            if (course[4].equalsIgnoreCase(department)) {
                courses.add(course);
            }
        }
        
        return courses;
    }
    
    /**
     * Get a specific course by code
     */
    public static String[] getCourse(String courseCode) {
        for (String[] course : getAllCourses()) {
            if (course[0].equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }
    
    /**
     * Check if course code exists
     */
    public static boolean courseExists(String courseCode) {
        return getCourse(courseCode) != null;
    }
    
    /**
     * Update a course
     */
    public static boolean updateCourse(String courseCode, String courseName, 
                                      String program, String units, String department) {
        List<String[]> courses = getAllCourses();
        boolean found = false;
        
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i)[0].equalsIgnoreCase(courseCode)) {
                courses.set(i, new String[]{courseCode, courseName, program, units, department});
                found = true;
                break;
            }
        }
        
        if (!found) return false;
        
        return writeAllCourses(courses);
    }
    
    /**
     * Delete a course
     */
    public static boolean deleteCourse(String courseCode) {
        List<String[]> courses = getAllCourses();
        boolean removed = courses.removeIf(course -> course[0].equalsIgnoreCase(courseCode));
        
        if (removed) {
            return writeAllCourses(courses);
        }
        
        return false;
    }
    
    /**
     * Write all courses to file
     */
    private static boolean writeAllCourses(List<String[]> courses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COURSES_FILE))) {
            for (String[] course : courses) {
                String line = String.join("|", course);
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