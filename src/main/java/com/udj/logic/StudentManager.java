package com.udj.logic;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StudentManager {

    private static final String STUDENT_FILE = "src/main/resources/data/StudentsData.txt";
    
    // ADD STUDENT
    public static String generateStudentID() {
        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(2);
        int nextNumber = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length > 0 && parts[0].startsWith("UDJ-" + year)) {
                    String[] idParts = parts[0].split("-");
                    int num = Integer.parseInt(idParts[2]);

                    if (num >= nextNumber) {
                        nextNumber = num + 1;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String numberFormatted = String.format("%04d", nextNumber);
        return "UDJ-" + year + "-" + numberFormatted;
    }

    public static boolean studentIDExists(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(id + ",")) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean saveStudent(String id, String name, String program,
                                      String department, String college, String yearLevel,
                                      String status, String email, String city) {
        try {
            File file = new File(STUDENT_FILE);
            System.out.println("Trying to save to: " + file.getAbsolutePath());

            file.getParentFile().mkdirs();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(id + "," + name + "," + program + "," + department + "," +
                     college + "," + yearLevel + "," + status + "," + email + "," + city);
            bw.newLine();
            bw.close();

            System.out.println("Save successful!");
            return true;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public static java.util.List<String[]> getAllStudents() {
        java.util.List<String[]> students = new java.util.ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 9) {
                    students.add(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
    
    public static String[] getStudent(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].equals(id)) {
                    return data;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // EDIT STUDENT
    public static boolean updateStudent(String originalId, String newName, String program,
                                  String department, String college, String yearLevel,
                                  String status, String email, String city) {
        java.util.List<String> lines = new java.util.ArrayList<>();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].equals(originalId)) {
                    String newLine = originalId + "," + newName + "," + program + "," + department + "," +
                                     college + "," + yearLevel + "," + status + "," + email + "," + city;
                    lines.add(newLine);
                    found = true;
                } else {
                    lines.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (!found) return false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(STUDENT_FILE))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // DELETE STUDENT
    public static boolean deleteStudent(String id) {
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].trim().equals(id.trim())) {
                    found = true; 
                    System.out.println("DEBUG: Deleting ID: " + id);
                } else {
                    lines.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (found) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(STUDENT_FILE))) {
                for (String line : lines) {
                    bw.write(line);
                    bw.newLine();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}