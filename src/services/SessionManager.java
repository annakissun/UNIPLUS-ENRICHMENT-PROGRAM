package services;

import model.*;

import java.util.LinkedList;

/**
 * Represents a session (class/lecture/group activity).
 */
public class SessionManager {

    // ===== DATA MEMBERS =====
    private int capacity;
    private String host;
    private String subject;
    private String location;
    private String joinCode; // null if public
    private LinkedList<Session> sessions = new LinkedList<>();

    public SessionManager(){}

    // ===== CONSTRUCTOR =====


    // ===== BASIC GETTERS =====
    public int getCapacity() {
        return capacity;
    }

    public String getHost() {
        return host;
    }

    public String getSubject() {
        return subject;
    }

    public String getLocation() {
        return location;
    }

    

    // ===== PRIVATE / PUBLIC =====
    public boolean isPrivate() {
        return joinCode != null;
    }

    public String getJoinCode() {
        return joinCode;
    }

   

    // ===== TO STRING =====
    

    public void createSession(int capacity, String host, String subject, boolean isPrivate, String location) {
        Session session = new Session(capacity, host, subject, null, null, location, isPrivate);
        sessions.add(session);
    }

    public void printAllSess(){
        System.out.println(sessions);
    }

}
