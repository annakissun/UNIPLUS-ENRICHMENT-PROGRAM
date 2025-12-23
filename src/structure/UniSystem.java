package structure;
import model.*;
import services.*;

import java.util.LinkedList;

public class UniSystem {
    //Data members
    private static UniSystem instance;
    private SessionManager sessionManager;
    private StudentManager studentManager;
    private AuthService authService;  // Add this

    private UniSystem() {
        sessionManager = new SessionManager();
        studentManager = new StudentManager();
        authService = new AuthService();  // Initialize it
    }

    // Singleton method
    public static UniSystem getInstance() {
        if (instance == null) instance = new UniSystem();
        return instance;
    }

    // Getters for all services
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public StudentManager getStudentManager() {
        return studentManager;
    }

    public AuthService getAuthService() {  // Add this getter
        return authService;
    }
}