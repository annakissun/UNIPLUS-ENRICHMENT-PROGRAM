package model;

public class User {

    private String fullName;
    private String email;
    private String username;
    private String password; // should be HASHED (handled in AuthService)
    private String role;     // "admin", "lecturer", "student"

    public User(String fullName, String email,
                String username, String password, String role) {

        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // ===== Getters =====
    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // ===== Setters =====
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // password must already be hashed
    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // ===== Utility =====
    public boolean isAdmin() {
        return "admin".equalsIgnoreCase(role);
    }

    @Override
    public String toString() {
        return fullName + " [" + username + "] (" + role + ")";
    }
}
