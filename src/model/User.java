package model;

/**
 * Base User class.
 * Represents a system user such as Admin, Lecturer, or Student.
 */
public class User {

    // ===== DATA MEMBERS =====
    protected String fullName;
    protected String email;
    protected String username;
    protected String password; // Should be HASHED (handled in AuthService)
    protected String role;     // "admin", "lecturer", "student"

    // ===== CONSTRUCTORS =====

    /**
     * Protected no-argument constructor.
     * Used by subclasses (e.g. Student).
     */
    protected User() {}

    /**
     * Full constructor for creating a User.
     */
    public User(String fullName, String email, String username,String password, String role) {

        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // ===== GETTERS =====

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    // Password should already be hashed
    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // ===== SETTERS =====

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Password must already be hashed
    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // ===== UTILITY METHODS =====

    /**
     * Checks if the user has admin role.
     */
    public boolean isAdmin() {
        return "admin".equalsIgnoreCase(role);
    }

    @Override
    public String toString() {
        return fullName + " [" + username + "] (" + role + ")";
    }
}
