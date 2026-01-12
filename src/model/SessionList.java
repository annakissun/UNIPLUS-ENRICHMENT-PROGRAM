package model;

import java.util.*;

/**
 * ADT: Session List
 * Purpose: Manage all sessions using LinkedList
 */
public class SessionList {

    private LinkedList<Session> sessions;

    public SessionList() {
        sessions = new LinkedList<>();
    }

    // INSERT operation
    public void addSession(Session s) {
        sessions.add(s);
    }

    // DELETE operation
    public boolean removeSession(Session s) {
        return sessions.remove(s);
    }

    // SEARCH operation
    public Session findByCode(String code) {
        for (Session s : sessions) {
            if (s.getJoinCode().equals(code)) {
                return s;
            }
        }
        return null;
    }

    // TRAVERSAL operation
    public List<Session> getAllSessions() {
        return sessions;
    }

    // SIZE operation
    public int size() {
        return sessions.size();
    }
}
