package model;

import java.util.LinkedList;
import java.time.LocalDateTime;
import java.util.Queue;
import services.CodeGenerator;

/**
 * Represents a session (class/lecture/group activity) in the system.
 * Handles student enrollment, waitlisting, and session details.
 */
public class Session {

    // ===== DATA MEMBERS =====
    private int capacity; // Maximum number of students allowed in this session
    private String host; // Name/username of the staff hosting this session
    private String subject;  // Subject/title of this session
    private String code; // Private join code if the session is private (otherwise null)
    private String location;  // Physical or virtual location of the session

    // List of students currently enrolled in the session
    private LinkedList<Student> students;

    // Queue for students waiting to join if the session is full
    private Queue<Student> waitQueue;

    // Session creation and optional timing
    private LocalDateTime creationTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // ===== CONSTRUCTORS =====

    // Constructor with required fields
    public Session(int capacity, String host, String subject) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be > 0");
        if (host == null || host.trim().isEmpty()) throw new IllegalArgumentException("Host required");
        if (subject == null || subject.trim().isEmpty()) throw new IllegalArgumentException("Subject required");

        this.capacity = capacity;
        this.host = host;
        this.subject = subject;
        this.students = new LinkedList<>();
        this.waitQueue = new LinkedList<>();
        this.code = CodeGenerator.generateCode(6);
        this.creationTime = LocalDateTime.now();
    }

    // Full constructor with optional fields
    public Session(int capacity, String host, String subject,
                   LocalDateTime startTime, LocalDateTime endTime,
                   String location, boolean isPrivate) {

        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be > 0");
        if (host == null || host.trim().isEmpty()) throw new IllegalArgumentException("Host required");
        if (subject == null || subject.trim().isEmpty()) throw new IllegalArgumentException("Subject required");

        this.capacity = capacity;
        this.host = host;
        this.subject = subject;
        this.students = new LinkedList<>();
        this.waitQueue = new LinkedList<>();
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.code = isPrivate ? CodeGenerator.generateCode(6) : null;
        this.creationTime = LocalDateTime.now();
    }

    // ========== BUSINESS LOGIC METHODS ==========

    /**
     * Add a student to the session.
     * @return true if enrolled, false if waitlisted or rejected
     */
    public boolean addStudent(Student student) {
        if (student == null) return false;
        if (students.contains(student) || waitQueue.contains(student)) return false;

        if (students.size() >= capacity) {
            waitQueue.add(student);
            return false;
        }

        students.add(student);
        return true;
    }

    /**
     * Remove a student from enrolled list or waitlist.
     */
    public boolean removeStudent(Student student) {
        if (student == null) return false;

        boolean removedFromStudents = students.remove(student);
        boolean removedFromWaitlist = waitQueue.remove(student);

        if (removedFromStudents && !waitQueue.isEmpty()) {
            students.add(waitQueue.poll());
        }

        return removedFromStudents || removedFromWaitlist;
    }

    // ===== QUERY METHODS =====

    public boolean isEnrolled(Student student) {
        return students.contains(student);
    }

    public boolean isWaitlisted(Student student) {
        return waitQueue.contains(student);
    }

    public int getEnrollmentCount() {
        return students.size();
    }

    // ===== GETTERS =====

    public int getCapacity() { return capacity; }
    public String getHost() { return host; }
    public String getSubject() { return subject; }
    public String getCode() { return code; }
    public String getLocation() { return location; }
    public LocalDateTime getCreationTime() { return creationTime; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }

    /**
     * Safe copy of enrolled students.
     */
    public LinkedList<Student> getStudents() {
        return new LinkedList<>(students);
    }

    /**
     * Safe copy of waitlist.
     */
    public LinkedList<Student> getWaitlist() {
        return new LinkedList<>(waitQueue);
    }

    // ===== SETTERS =====

    public void setCapacity(int capacity) {
        if (capacity >= students.size()) {
            this.capacity = capacity;
        }
    }

    public void setHost(String host) { this.host = host; }
    public void setSubject(String subject) { this.subject = subject; }
    public void setLocation(String location) { this.location = location; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    // ===== CALCULATED =====

    public boolean isFull() {
        return students.size() >= capacity;
    }

    public int getAvailableSeats() {
        return capacity - students.size();
    }

    public boolean isPrivate() {
        return code != null;
    }

    public String getJoinCode() {
        return code;
    }

    // ===== TO STRING =====

    @Override
    public String toString() {
        return subject + " [" + students.size() + "/" + capacity + "]"
                + (isPrivate() ? " (Private)" : " (Public)");
    }
}
