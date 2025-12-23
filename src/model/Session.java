package model;

import java.util.LinkedList;
import java.time.LocalDateTime;
import services.CodeGenerator;

public class Session {
    // Essential data members
    private int capacity;
    private String code;
    private String host;
    private String subject;
    private LinkedList<Student> students;
    private LocalDateTime creationTime;
    private String location;
    
    // Optional: session timing
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Constructor with required fields
    public Session(int capacity, String host, String subject) {
        this.capacity = capacity;
        this.host = host;
        this.subject = subject;
        this.students = new LinkedList<>();
        this.code = CodeGenerator.generateCode(6); // Auto-generated code
        this.creationTime = LocalDateTime.now(); // Auto-set creation time
    }

    // Full constructor with optional fields
    public Session(int capacity, String host, String subject, 
                   LocalDateTime startTime, LocalDateTime endTime,
                   String location) {
        this.capacity = capacity;
        this.host = host;
        this.subject = subject;
        this.students = new LinkedList<>();
        this.code = CodeGenerator.generateCode(6);
        this.creationTime = LocalDateTime.now();
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    // ========== BUSINESS LOGIC METHODS ==========
    
    /**
     * Add a student to the session
     * Returns false if session is at capacity or student already enrolled
     */
    public boolean addStudent(Student student) {
        if (students.size() >= capacity) {
            return false; // Session is full
        }
        if (students.contains(student)) {
            return false; // Student already enrolled
        }
        return students.add(student);
    }
    
    /**
     * Remove a student from the session
     */
    public boolean removeStudent(Student student) {
        return students.remove(student);
    }
    
    /**
     * Check if student is enrolled in this session
     */
    public boolean hasStudent(Student student) {
        return students.contains(student);
    }
    
    /**
     * Get current number of enrolled students
     */
    public int getEnrollmentCount() {
        return students.size();
    }

    // ========== GETTERS ==========
    public int getCapacity() { return capacity; }
    public String getCode() { return code; }
    public String getHost() { return host; }
    public String getSubject() { return subject; }
    public LinkedList<Student> getStudents() { return students; }
    public LocalDateTime getCreationTime() { return creationTime; }
    public String getLocation() { return location; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    
    // Calculated getters for GUI
    public int getAvailableSeats() {
        return capacity - students.size();
    }
    
    public boolean isFull() {
        return students.size() >= capacity;
    }

    // ========== SETTERS ==========
    public void setCapacity(int capacity) { 
        // Prevent setting capacity lower than current enrollment
        if (capacity >= students.size()) {
            this.capacity = capacity;
        }
    }
    
    public void setHost(String host) { this.host = host; }
    public void setSubject(String subject) { this.subject = subject; }
    public void setLocation(String location) { this.location = location; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    
    // Note: No setters for code and creationTime - these are immutable
    
    // Simple toString for debugging
    @Override
    public String toString() {
        return code + " - " + subject + " (" + students.size() + "/" + capacity + ")";
    }
}