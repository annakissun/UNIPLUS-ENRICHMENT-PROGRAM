package model;

import java.util.*;
import services.CodeGenerator;
import util.AlertShow;

public class Session {

    private int capacity;
    private String host;
    private String subject;
    private String location;
    private String description;
    private String time;
    private String joinCode; // null if public

    private LinkedList<Student> students = new LinkedList<>();
    private Queue<Student> waitQueue = new LinkedList<>();

    public Session(int capacity,String host,String subject,boolean isPrivate,String location,String description,String time) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be > 0");
        if (host == null || host.trim().isEmpty()) throw new IllegalArgumentException("Host required");
        if (subject == null || subject.trim().isEmpty()) throw new IllegalArgumentException("Subject required");

        this.capacity = capacity;
        this.host = host;
        this.subject = subject;
        this.location = location;
        this.description = description;
        this.time = time;
        this.joinCode = isPrivate ? CodeGenerator.generateCode(6) : null;
    }

    // ===== ENROLLMENT =====
    public boolean addStudent(Student student) {
        if (students.contains(student) || waitQueue.contains(student)) {
            AlertShow.showInfo("Can't Join", "Already in session or waitlist");
            return false;
        }
        if (students.size() >= capacity) {
            waitQueue.add(student);
            AlertShow.showInfo("Waitlist", "Session full, added to waitlist");
            saveState();
            return false;
        }
        students.add(student);
        saveState();
        return true;
    }

    public boolean removeStudent(Student student) {
        boolean removed = students.remove(student);
        if (removed && !waitQueue.isEmpty()) {
            students.add(waitQueue.poll());
        }
        boolean removedFromWaitlist = waitQueue.remove(student);
        saveState();
        return removed || removedFromWaitlist;
    }

    // ===== SAVE STATE =====
    private void saveState() {
        // Save after any change
        util.FileService.saveSessions(structure.UniSystem.getInstance().getSessionManager().getSessions());
    }

    // ===== GETTERS =====
    public int getCapacity() { return capacity; }
    public String getHost() { return host; }
    public String getSubject() { return subject; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public String getTime() { return time; }
    public String getJoinCode() { return joinCode; }
    public boolean isPrivate() { return joinCode != null; }
    public LinkedList<Student> getStudents() { return new LinkedList<>(students); }
    public LinkedList<Student> getWaitlist() { return new LinkedList<>(waitQueue); }

    // ===== USERNAME LISTS FOR FILE =====
    public List<String> getStudentNames() {
        List<String> usernames = new ArrayList<>();
        for (Student s : students) usernames.add(s.getUsername());
        return usernames;
    }

    public List<String> getWaitlistNames() {
        List<String> usernames = new ArrayList<>();
        for (Student s : waitQueue) usernames.add(s.getUsername());
        return usernames;
    }

    // Returns the number of currently joined students
    public int getCurrentJoined() {
        return students.size();
    }

    public int getWaitlistCount() {
        return waitQueue.size();
    }

    // ===== ADD FROM FILE =====
    public void addJoined(Student s) { students.add(s); }
    public void addWaitlist(Student s) { waitQueue.add(s); }
}
