package services;

import model.*;
import java.util.LinkedList;
import java.util.Iterator;

public class SessionManager {
    private LinkedList<Session> sessions = new LinkedList<>();

    // Add a new session
    public void createSession(int capacity, String host, String subject) {
        Session newSession = new Session(capacity, host, subject);
        sessions.add(newSession);
        System.out.println("Session created: " + newSession.getCode() + " - " + subject);
    }

    // Remove a session by session code
    public boolean removeSession(String code) {
        Iterator<Session> iterator = sessions.iterator();
        while (iterator.hasNext()) {
            Session session = iterator.next();
            if (session.getCode().equals(code)) {
                iterator.remove();
                System.out.println("Session removed: " + code);
                return true;
            }
        }
        System.out.println("Session not found: " + code);
        return false;
    }

    // Remove a session by Session object
    public boolean removeSession(Session sessionToRemove) {
        if (sessions.remove(sessionToRemove)) {
            System.out.println("Session removed: " + sessionToRemove.getCode());
            return true;
        }
        System.out.println("Session not found in list");
        return false;
    }

    // List all sessions
    public LinkedList<Session> getSessions() {
        return sessions;
    }

    // Find/search for a session by code
    public Session findSession(String code) {
        for (Session session : sessions) {
            if (session.getCode().equals(code)) {
                return session;
            }
        }
        return null;
    }

    // Check if session exists by code
    public boolean sessionExists(String code) {
        return findSession(code) != null;
    }

    // Get session count
    public int getSessionCount() {
        return sessions.size();
    }

    // Get sessions by host
    public LinkedList<Session> getSessionsByHost(String host) {
        LinkedList<Session> hostSessions = new LinkedList<>();
        for (Session session : sessions) {
            if (session.getHost().equals(host)) {
                hostSessions.add(session);
            }
        }
        return hostSessions;
    }

    // Print all sessions (for debugging/display)
    public void printAllSessions() {
        System.out.println("\n=== All Sessions (" + sessions.size() + ") ===");
        if (sessions.isEmpty()) {
            System.out.println("No sessions available.");
            return;
        }
        
        for (Session session : sessions) {
            System.out.println("Code: " + session.getCode());
            System.out.println("Subject: " + session.getSubject());
            System.out.println("Host: " + session.getHost());
            System.out.println("Capacity: " + session.getCapacity());
            System.out.println("Students enrolled: " + session.getStudents().size());
            System.out.println("---");
        }
    }
}