package services;

import model.*;
import util.FileService;
import java.util.LinkedList;

public class SessionManager {

    private LinkedList<Session> sessions;
    private Session currentSession;

    public SessionManager() {
        sessions = new LinkedList<>(); // start empty
    }

    public Session createSession(int capacity, String host, String subject,
                                 boolean isPrivate, String location,
                                 String desc, String time) {
        Session session = new Session(capacity, host, subject, isPrivate, location, desc, time);
        sessions.add(session);
        FileService.saveSessions(sessions); // save immediately
        return session;
    }

    public void addSession(Session s) {
        sessions.add(s);
        FileService.saveSessions(sessions);
    }

    public void removeSession(Session s) {
        sessions.remove(s);
        if (s == currentSession) currentSession = null;
        FileService.saveSessions(sessions);
    }

    public LinkedList<Session> getSessions() {
        return sessions;
    }

    public void setCurrentSession(Session s) {
        currentSession = s;
    }

    public Session getCurrentSession() {
        return currentSession;
    }
}
