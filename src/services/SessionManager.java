package services;

import model.*;
import java.util.LinkedList;

/**
 * Manages all sessions in the system.
 */
public class SessionManager {

    // ===== DATA MEMBERS =====
    private LinkedList<Session> sessions = new LinkedList<>();
    private Session currentSession;

    // ===== CONSTRUCTOR =====
    public SessionManager() {}

    // ===== SESSION MANAGEMENT =====

    public Session createSession(int capacity, String host, String subject, boolean isPrivate, String location, String desc, String time) {
        Session session = new Session(capacity, host, subject, isPrivate, location, desc, time);
        sessions.add(session);
        return session;
    }

    public void removeSession(Session session) {
        sessions.remove(session);

        if (session == currentSession) {
            currentSession = null;
        }

        System.out.println("Session removed");
    }

    // ===== CURRENT SESSION =====

    /**
     * Sets the currently active session (selected/joined).
     */
    public void setCurrentSession(Session session) {
        this.currentSession = session;
    }

    /**
     * Returns the currently active session.
     * Returns null if no session is selected.
     */
    public Session getCurrentSession() {
        return currentSession;
    }

    // ===== GETTERS =====

    public LinkedList<Session> getSessions() {
        return sessions;
    }

    // ===== DEBUG =====
    public void printAllSess() {
        System.out.println(sessions);
    }
}
