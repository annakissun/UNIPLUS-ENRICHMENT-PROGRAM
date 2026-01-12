package services;

import model.Session;
import structure.SessionList;
import util.FileService;

/**
 * SessionManager manages all sessions.
 * Uses SessionList wrapper for better OOP design.
 */
public class SessionManager {

    private SessionList sessions;      // Now using SessionList wrapper
    private Session currentSession;    // Currently selected session

    public SessionManager() {
        sessions = new SessionList();  // initialize wrapper
    }

    /**
     * Create a new session and add it to the session list
     */
    public Session createSession(int capacity, String host, String subject, boolean isPrivate, String location, String desc, String time) {
        Session session = new Session(capacity, host, subject, isPrivate, location, desc, time);
        sessions.addSession(session);   // use wrapper method
        FileService.saveSessions(sessions.getAllSessions()); // save to file
        return session;
    }

    /**
     * Add an existing session (e.g., loaded from file)
     */
    public void addSession(Session s) {
        sessions.addSession(s);
        FileService.saveSessions(sessions.getAllSessions());
    }

    /**
     * Remove a session
     */
    public void removeSession(Session s) {
        sessions.removeSession(s);
        if (s == currentSession) currentSession = null;
        FileService.saveSessions(sessions.getAllSessions());
    }

    /**
     * Find a session by its join code
     */
    public Session findSessionByCode(String code) {
        return sessions.findByCode(code);
    }

    /**
     * Get all sessions as a list
     */
    public java.util.List<Session> getAllSessions() {
        return sessions.getAllSessions();
    }

    /**
     * Get the number of sessions
     */
    public int getSessionCount() {
        return sessions.size();
    }

    /**
     * Set or get the currently active session
     */
    public void setCurrentSession(Session s) {
        currentSession = s;
    }

    public Session getCurrentSession() {
        return currentSession;
    }
}
