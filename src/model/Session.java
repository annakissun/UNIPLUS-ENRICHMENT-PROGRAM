package model;

import java.util.LinkedList;
import java.util.Queue;
import services.CodeGenerator;
import util.AlertShow;

/**
 * Represents a session (class, lecture, or group activity).
 * Handles enrollment, waitlisting, and session metadata.
 */
public class Session {

    // ===== DATA MEMBERS =====
    private int capacity;
    private String host;
    private String subject;
    private String location;
    private String description;
    private String time;

    private String joinCode; // null if public

    private LinkedList<Student> students = new LinkedList<>();
    private Queue<Student> waitQueue = new LinkedList<>();

    // ===== CONSTRUCTOR =====
    public Session(int capacity,String host,String subject,boolean isPrivate,String location,String description,String time) {

        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be greater than 0");
        if (host == null || host.trim().isEmpty())
            throw new IllegalArgumentException("Host is required");
        if (subject == null || subject.trim().isEmpty())
            throw new IllegalArgumentException("Subject is required");

        this.capacity = capacity;
        this.host = host;
        this.subject = subject;
        this.location = location;
        this.description = description;
        this.time = time;
        this.joinCode = isPrivate ? CodeGenerator.generateCode(6) : null;
    }

    // ===== ENROLLMENT LOGIC =====
    public boolean addStudent(User user) {
        // Must be a Student
        if (!(user instanceof Student)) {
            System.out.println("Not a student, can't join");
            return false;
        }

        Student student = (Student) user;

        // Already enrolled or in waitlist
        if (students.contains(student) || waitQueue.contains(student)) {
            System.out.println(student.getName() + " is already in the session or waitlist");
            AlertShow.showInfo("Can't Join Session", "You're already in the session or waitlist queue");
            return false;
        }

        // Session full → add to waitlist
        if (students.size() >= capacity) {
            waitQueue.add(student);
            AlertShow.showInfo("Added to waiting queue", "The session is full for now please wait someone to leave");
            System.out.println(student.getName() + " added to waitlist");
            return false;
        }

        // Add student to session
        students.add(student);
        System.out.println(student.getName() + " successfully joined session");
        return true;
    }


    public boolean removeStudent(User user) {
        // Must be a Student
        if (!(user instanceof Student)) {
            System.out.println("Not a student, can't be removed");
            return false;
        }
        Student student = (Student) user;
        // Try to remove from enrolled students
        boolean removed = students.remove(student);
        // If removed and waitlist not empty, move first waitlisted student to session
        if (removed && !waitQueue.isEmpty()) {
            students.add(waitQueue.poll());
        }
        // Also try removing from waitlist (in case they were only in waitlist)
        boolean removedFromWaitlist = waitQueue.remove(student);
        return removed || removedFromWaitlist;
    }


    // ===== QUERY METHODS =====
    public boolean isFull() {
        return students.size() >= capacity;
    }

    public int getCurrentJoined() {
        return students.size();
    }

    public boolean isPrivate() {
        return joinCode != null;
    }

    // ===== GETTERS =====
    public int getCapacity() { return capacity; }
    public String getHost() { return host; }
    public String getSubject() { return subject; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public String getTime() { return time; }
    public String getJoinCode() { return joinCode; }

    public LinkedList<Student> getStudents() {
        return new LinkedList<>(students);
    }

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
    public void setDescription(String description) { this.description = description; }
    public void setTime(String time) { this.time = time; }

    public boolean hasJoined(Student s) {
        return students.contains(s);
    }


    // ===== TO STRING =====
    @Override
public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("===== SESSION DETAILS =====\n");
        sb.append("Subject      : ").append(subject).append("\n");
        sb.append("Host         : ").append(host).append("\n");
        sb.append("Location     : ").append(location).append("\n");
        sb.append("Time         : ").append(time).append("\n");
        sb.append("Description  : ").append(description).append("\n");
        sb.append("Capacity     : ").append(students.size())
        .append("/").append(capacity).append("\n");
        sb.append("Available    : ").append(getCurrentJoined()).append("\n");
        sb.append("Type         : ").append(isPrivate() ? "Private" : "Public").append("\n");

        if (isPrivate()) {
            sb.append("Join Code    : ").append(joinCode).append("\n");
        }

        sb.append("Students     : ");
        if (students.isEmpty()) {
            sb.append("None\n");
        } else {
            sb.append("\n");
            for (Student s : students) {
                sb.append("  - ").append(s).append("\n");
            }
        }

        sb.append("Waitlist     : ");
        if (waitQueue.isEmpty()) {
            sb.append("Empty\n");
        } else {
            sb.append("\n");
            for (Student s : waitQueue) {
                sb.append("  - ").append(s).append("\n");
            }
        }

        sb.append("===========================\n");
        return sb.toString();
    }

    // inside Session.java
    public String getStudentNames() {
        if (students.isEmpty()) return "None";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < students.size(); i++) {
            sb.append(students.get(i).getName());
            if (i < students.size() - 1) sb.append(", "); // add comma between names
        }
        return sb.toString();
    }

    // Get all waitlisted student names
    public String getWaitlistNames() {
        if (waitQueue.isEmpty()) return "None";

        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Student s : waitQueue) {
            sb.append(s.getName());
            i++;
            if (i < waitQueue.size()) sb.append(", ");
        }
        return sb.toString();
    }

}
