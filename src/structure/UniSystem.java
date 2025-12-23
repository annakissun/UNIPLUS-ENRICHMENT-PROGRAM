package structure;
import model.*;
import services.SessionManager;
import services.StudentManager;

import java.util.LinkedList;

public class UniSystem {
    //Data members
    private static UniSystem instance;
    private SessionManager sessionManager;
    private StudentManager studentManager;


    private UniSystem() {
        sessionManager = new SessionManager();
        studentManager = new StudentManager();
    }

    //Mehtod for creatung system (singleton since we dont want many system)
    public static UniSystem getInstance() {
        if (instance == null) instance = new UniSystem();
        return instance;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public StudentManager getStudentManager() {
        return studentManager;
    }

}