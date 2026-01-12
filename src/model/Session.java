package model;

import structure.*;
import util.FileService;
import java.util.*;

public class Session {

    private int capacity;
    private String host;
    private String subject;
    private boolean isPrivate;
    private String location;
    private String description;
    private String time;
    private String joinCode;

    private JoinedList joinedList;
    private WaitlistQueue waitlist;

    private boolean suppressSave = false; // for loading from file

    public Session(int capacity, String host, String subject, boolean isPrivate,
                   String location, String description, String time) {
        this.capacity = capacity;
        this.host = host;
        this.subject = subject;
        this.isPrivate = isPrivate;
        this.location = location;
        this.description = description;
        this.time = time;
        this.joinCode = UUID.randomUUID().toString().substring(0, 6);

        this.joinedList = new JoinedList();
        this.waitlist = new WaitlistQueue();
    }

    // ===== JOIN SESSION =====
    public boolean join(Student s) {
        if (joinedList.contains(s) || waitlist.contains(s)) return false;

        if (joinedList.size() < capacity) {
            joinedList.add(s);
        } else {
            waitlist.enqueue(s);
        }

        saveIfNeeded();
        return true;
    }

    // ===== LEAVE SESSION =====
    public boolean leaveSession(Student s) {
        boolean removed = joinedList.remove(s);
        if (!removed) return false;

        // Promote first student from waitlist if available
        if (!waitlist.isEmpty()) {
            Student next = waitlist.dequeue();
            joinedList.add(next);
        }

        saveIfNeeded();
        return true;
    }

    // ===== INTERNAL USE =====
    public void addFromFile(Student s) {
        if (joinedList.size() < capacity) {
            joinedList.add(s);
        } else {
            waitlist.enqueue(s);
        }
    }

    // ===== AUTOMATIC SAVE =====
    private void saveIfNeeded() {
        if (!suppressSave) {
            // Save ALL sessions from UniSystem to file
            // This assumes UniSystem.getInstance().getSessionManager().getSessions() exists
            FileService.saveSessions(UniSystem.getInstance().getSessionManager().getAllSessions());
        }
    }

    // ===== GETTERS =====
    public int getCapacity() { return capacity; }
    public String getHost() { return host; }
    public String getSubject() { return subject; }
    public boolean isPrivate() { return isPrivate; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public String getTime() { return time; }
    public String getJoinCode() { return joinCode; }

    public int getCurrentJoined() { return joinedList.size(); }
    public List<Student> getJoinedListStudents() { return joinedList.getAll(); }
    
    public List<Student> getWaitlistStudents() {
        return new ArrayList<>(waitlist.getAll()); // convert Queue -> List
    }


    public List<String> getStudentNames() {
        List<String> names = new ArrayList<>();
        for (Student s : joinedList.getAll()) names.add(s.getUsername());
        return names;
    }

    public List<String> getWaitlistNames() {
        List<String> names = new ArrayList<>();
        for (Student s : waitlist.getAll()) names.add(s.getUsername());
        return names;
    }

    // ===== LOADING/FILE CONTROL =====
    public void setSuppressSave(boolean suppress) { this.suppressSave = suppress; }
}
