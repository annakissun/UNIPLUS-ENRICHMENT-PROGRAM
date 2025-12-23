package services;

import model.*;
import java.util.LinkedList;

public class SessionManager {

    private LinkedList<Session> sessions = new LinkedList<>();

    //Add a new session
    public void createSession(int capacity, String host, String subject) {
        sessions.add(new Session(capacity, host, subject));
    }

    //Remove a session
    public void removeSession() {
        if (sessions.equals("code entered")) { // not implemented yet
            sessions.remove();
        }
    }
    //List all sessions?
    public LinkedList<Session> getSessions() {
        return sessions;
    }

    //Find/search for a session (by name or code)
    public boolean findSession (String code) {
        for (Session session : sessions) {
            if (session.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

}
