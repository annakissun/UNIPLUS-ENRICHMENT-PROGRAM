package model;

import java.util.LinkedList;
import java.util.Queue;
import services.CodeGenerator;

/**
 * Represents a session (class/lecture/group activity) in the system.
 * Handles student enrollment, waitlisting, and session details.
 */
public class Session {

    // ===== DATA MEMBERS =====
    private final int capacity; // Maximum number of students allowed in this session
    private final String host; // Name/username of the staff hosting this session
    private final String subject;  // Subject/title of this session
    private final String code; // Private join code if the session is private (otherwise null)
    private final String location;  // Physical or virtual location of the session

    // List of students currently enrolled in the session
    private final LinkedList<Student> students;

    // Queue for students waiting to join if the session is full
    private final Queue<Student> waitQueue;

    // ===== CONSTRUCTOR =====
    public Session(int capacity, String host, String subject, String location, boolean isPrivate) {

        // Validate inputs
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be > 0");
        if (host == null || host.trim().isEmpty())
            throw new IllegalArgumentException("Host required");
        if (subject == null || subject.trim().isEmpty())
            throw new IllegalArgumentException("Subject required");

        // Assign values
        this.capacity = capacity;
        this.host = host;
        this.subject = subject;
        this.location = location;

        // Initialize student list and waitlist queue
        this.students = new LinkedList<>();
        this.waitQueue = new LinkedList<>();

        // Generate private join code if needed, else set to null
        this.code = isPrivate ? CodeGenerator.generateCode(6) : null;
    }

    // ===== BUSINESS LOGIC =====

    /**
     * Attempt to add a student to the session.
     * @param student Student object to add
     * @return true if student is enrolled successfully, false if waitlisted or already enrolled
     */
    public boolean addStudent(Student student) {

        if (student == null)  // Can't add null student
            return false;

        if (students.contains(student))  // Already enrolled
            return false;

        if (waitQueue.contains(student))  // Already in waitlist
            return false;

        if (students.size() >= capacity) {  // Session full → add to waitlist
            waitQueue.add(student);
            return false; // student is waitlisted
        }

        // Space available → add student to enrolled list
        students.add(student);
        return true; // successfully enrolled
    }

    /**
     * Remove a student from session or waitlist.
     * @param student Student object to remove
     * @return true if student was removed from either enrolled list or waitlist
     */
    public boolean removeStudent(Student student) {

        // Attempt to remove from enrolled list
        boolean removedFromStudents = students.remove(student);

        // Attempt to remove from waitlist queue
        boolean removedFromWaitlist = waitQueue.remove(student);

        // If removed from enrolled and there are waitlisted students, move first waitlisted student to enrolled
        if (removedFromStudents && !waitQueue.isEmpty()) {
            students.add(waitQueue.poll());
        }

        return removedFromStudents || removedFromWaitlist; // true if removed from either
    }

    // ===== QUERY METHODS =====

    /**
     * Check if student is currently enrolled in the session.
     */
    public boolean isEnrolled(Student student) {
        return students.contains(student);
    }

    /**
     * Check if student is currently waitlisted for this session.
     */
    public boolean isWaitlisted(Student student) {
        return waitQueue.contains(student);
    }

    /**
     * Check if session is full.
     */
    public boolean isFull() {
        return students.size() >= capacity;
    }

    /**
     * Get the number of available seats in the session.
     */
    public int getAvailableSeats() {
        return capacity - students.size();
    }

    // ===== SAFE GETTERS =====

    /**
     * Get a copy of the enrolled students list.
     * Returning a new LinkedList prevents external code from modifying the original list.
     */
    public LinkedList<Student> getStudents() {
        return new LinkedList<>(students);
    }

    /**
     * Get a copy of the waitlist queue as a LinkedList.
     * Returning a copy prevents external modification of the internal queue.
     */
    public LinkedList<Student> getWaitlist() {
        return new LinkedList<>(waitQueue);
    }

    // ===== BASIC GETTERS =====

    public int getCapacity() { return capacity; }
    public String getHost() { return host; }
    public String getSubject() { return subject; }
    public String getLocation() { return location; }

    /**
     * Check if session is private.
     */
    public boolean isPrivate() { return code != null; }

    /**
     * Returns the join code (only useful if private).
     */
    public String getJoinCode() {
        return code;
    }

    // ===== TO STRING =====

    @Override
    public String toString() {
        // Example: "Math [3/10] (Private)" or "History [5/15] (Public)"
        return subject + " [" + students.size() + "/" + capacity + "]"
                + (code != null ? " (Private)" : " (Public)");
    }
}
