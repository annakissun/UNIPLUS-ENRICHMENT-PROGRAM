package structure;

import services.*;
import util.FileService;
import model.Session;
import model.SessionList;

/**
 * Singleton for system-wide access
 */
public class UniSystem {

    private static UniSystem instance;

    private AuthService authService;
    private SessionManager sessionManager;

    private UniSystem() {
        authService = new AuthService();       
        sessionManager = new SessionManager(); // empty manager

        // Load sessions safely
        SessionList loadedSessions = FileService.loadSessions(authService);
        for (Session s : loadedSessions.getAllSessions()) {
            sessionManager.addSession(s); // add to manager
        }
    }

    public static UniSystem getInstance() {
        if (instance == null) {
            instance = new UniSystem();
        }
        return instance;
    }

    public AuthService getAuthService() {
        return authService;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
