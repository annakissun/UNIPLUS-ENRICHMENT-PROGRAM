package structure;

import java.util.List;
import services.*;
import util.FileService;
import model.Session;

public class UniSystem {

    private static UniSystem instance;
    private SessionManager sessionManager;
    private AuthService authService;

    private UniSystem() {
        authService = new AuthService();       // initialize users first
        sessionManager = new SessionManager(); // empty first

        List<Session> loaded = FileService.loadSessions(authService);
        for (Session s : loaded) {
            sessionManager.addSession(s);
        }
    }

    public static UniSystem getInstance() {
        if (instance == null) instance = new UniSystem();
        return instance;
    }

    public SessionManager getSessionManager() { return sessionManager; }
    public AuthService getAuthService() { return authService; }
}
