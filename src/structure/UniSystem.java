package structure;
import services.*;

public class UniSystem {
    //Data members
    private static UniSystem instance;
    private SessionManager sessionManager;
    private AuthService authService;  // Add this

    private UniSystem() {
        sessionManager = new SessionManager();
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

    public AuthService getAuthService() {  // Add this getter
        return authService;
    }
}