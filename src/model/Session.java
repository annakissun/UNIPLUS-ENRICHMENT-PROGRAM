package model;

import java.util.*;

import structure.*;


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

    public Session(int capacity, String host, String subject, boolean isPrivate, String location, String description, String time) {
        this.capacity = capacity;
        this.host = host;
        this.subject = subject;
        this.isPrivate = isPrivate;
        this.location = location;
        this.description = description;
        this.time = time;
        this.joinCode = UUID.randomUUID().toString().substring(0,6);

        this.joinedList = new JoinedList();
        this.waitlist = new WaitlistQueue();
    }

    // ===== PERMISSION =====
    public boolean canEdit(String username) {
        return host.equals(username);
    }

    // ===== JOIN / LEAVE =====
    public boolean join(Student s) {
        if (joinedList.contains(s) || waitlist.contains(s)) return false;

        if (joinedList.size() < capacity) joinedList.add(s);
        else waitlist.enqueue(s);

        return true; // external code triggers FileService.saveSessions(...)
    }

    public boolean leaveSession(Student s) {
        boolean removed = joinedList.remove(s);
        if (!removed) return false;

        if (!waitlist.isEmpty()) joinedList.add(waitlist.dequeue());

        return true; // external code triggers FileService.saveSessions(...)
    }

    // ===== FILE LOAD SUPPORT =====
    public void addFromFile(Student s) {
        if (joinedList.size() < capacity) joinedList.add(s);
        else waitlist.enqueue(s);
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
    public Queue<Student> getWaitlistStudents() { return waitlist.getAll(); }

    // ===== SETTERS =====
    public void setSubject(String subject) { this.subject = subject; }
    public void setLocation(String location) { this.location = location; }
    public void setDescription(String desc) { this.description = desc; }
    public void setTime(String time) { this.time = time; }
    public void setCapacity(int cap) { if (cap >= joinedList.size()) this.capacity = cap; }
    public void setPrivate(boolean priv) { this.isPrivate = priv; }

    public String getStudentNames() {
        if (joinedList.getAll().isEmpty()) return "None";
        StringBuilder sb = new StringBuilder();
        for (Student s : joinedList.getAll()) sb.append(s.getUsername()).append(", ");
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    public String getWaitlistNames() {
        if (waitlist.getAll().isEmpty()) return "None";
        StringBuilder sb = new StringBuilder();
        for (Student s : waitlist.getAll()) sb.append(s.getUsername()).append(", ");
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }
}
