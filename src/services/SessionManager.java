package services;

import model.*;
import java.util.LinkedList;
import java.util.Iterator;

public class SessionManager {
    private LinkedList<Session> sessions = new LinkedList<>();

    // Add a new session
    public void createSession(int capacity, String host, String subject, boolean isPrivate, String location) {
        // Create session properly
        Session newSession = new Session(capacity, host, subject, location, isPrivate);
        sessions.add(newSession);

        if (newSession.isPrivate()) {
            System.out.println("Private session created: " + newSession.getJoinCode() + " - " + subject);
        } else {
            System.out.println("Public session created: " + subject);
        }
    }

    // Remove a session by session code (public sessions only)
    public boolean removeSession(String code) {
        if (code == null) return false; // public sessions have a code

        Iterator<Session> iterator = sessions.iterator();
        while (iterator.hasNext()) {
            Session session = iterator.next();
            if (!session.isPrivate() && code.equals(session.getJoinCode())) {
                iterator.remove();
                System.out.println("Session removed: " + code);
                return true;
            }
        }
        System.out.println("Public session not found: " + code);
        return false;
    }

    // Remove a session by Session object (works for any session)
    public boolean removeSession(Session sessionToRemove) {
        if (sessions.remove(sessionToRemove)) {
            String code = sessionToRemove.isPrivate() ? sessionToRemove.getJoinCode() : "no code";
            System.out.println("Session removed: " + code);
            return true;
        }
        System.out.println("Session not found in list");
        return false;
    }

    // List all sessions
    public LinkedList<Session> getSessions() {
        return sessions;
    }

    // Find/search for a session by code (only public sessions)
    public Session findSession(String code) {
        if (code == null) return null;

        for (Session session : sessions) {
            if (!session.isPrivate() && code.equals(session.getJoinCode())) {
                return session;
            }
        }
        return null;
    }

    // Check if session exists by code (public only)
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
            if (!session.isPrivate()) {
                System.out.println("Code: " + session.getJoinCode());
            } else {
                System.out.println("Private session");
            }
            System.out.println("Subject: " + session.getSubject());
            System.out.println("Host: " + session.getHost());
            System.out.println("Capacity: " + session.getCapacity());
            System.out.println("Students enrolled: " + session.getStudents().size());
            System.out.println("---");
        }
    }
}
