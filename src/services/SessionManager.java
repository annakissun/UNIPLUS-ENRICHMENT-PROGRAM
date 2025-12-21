package services;

import model.*;
import java.util.LinkedList;

public class SessionManager {

    private LinkedList<Session> sessions = new LinkedList<>();

    public void createSession(int capacity, String host, String subject,LinkedList<Student> sl) {
        sessions.add(new Session(capacity,host,subject,sl = new LinkedList<>()));
    }

    public LinkedList<Session> getSessions() {
        return sessions;
    }
}
