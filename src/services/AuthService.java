package services;

import model.User;
import java.util.LinkedList;

public class AuthService {

    private LinkedList<User> users = new LinkedList<>();
    private User currentUser;

    public AuthService() {
        // ===== Default & dummy users =====
        users.add(new User("System Admin", "admin@uni.edu", "admin", "admin123", "admin"));
        users.add(new User("Dr John Lecturer", "john@uni.edu", "lecturer1", "lect123", "lecturer"));
        users.add(new User("Alice Student", "alice@uni.edu", "student1", "stud123", "student"));
        users.add(new User("Guest User", "guest@uni.edu", "guest1", "guest123", "guest"));
        users.add(new User("Muhammad Fahmi", "fahmi@gmail.com", "fahmi", "123", "student"));
    }

    // ===== SIGNUP =====
    public boolean signup(String fullName, String email,String username, String password, String role) {

        if (fullName == null || email == null ||username == null || password == null) return false;

        fullName = fullName.trim();
        email = email.trim();
        username = username.trim();
        password = password.trim();

        if (fullName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) return false;

        if (userExists(username)) return false;

        users.add(new User(fullName, email, username, password, role));
        return true;
    }

    // ===== LOGIN =====
    public boolean login(String username, String password) {

        if (username == null || password == null) return false;

        for (User u : users) {
            if (u.getUsername().equals(username)
                    && u.getPassword().equals(password)) {

                currentUser = u;
                return true;
            }
        }
        return false;
    }

    // ===== LOGOUT =====
    public void logout() {
        currentUser = null;
    }


    // ===== USER CHANGES OWN ROLE (GUI) =====
    public boolean changeCurrentUserRole(String newRole) {

        if (currentUser == null) return false;
        if (newRole == null || newRole.trim().isEmpty()) return false;

        currentUser.setRole(newRole.trim());
        return true;
    }

    // ===== ADMIN CHANGES OTHER USER ROLE =====
    public boolean changeUserRole(String username, String newRole) {

        if (currentUser == null) return false;
        if (!currentUser.getRole().equalsIgnoreCase("admin")) return false;

        for (User u : users) {
            if (u.getUsername().equals(username)) {
                u.setRole(newRole.trim());
                return true;
            }
        }
        return false;
    }

    // ===== HELPERS =====
    private boolean userExists(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) return true;
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public LinkedList<User> getAllUsers() {
        return users;
    }
}
