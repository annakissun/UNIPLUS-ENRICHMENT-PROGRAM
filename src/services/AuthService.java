package services;

import model.User;
import java.util.LinkedList;
import java.util.Scanner;

public class AuthService {
    private Scanner scan = new Scanner(System.in);
    private LinkedList<User> users = new LinkedList<>(); // using LinkedList
    private boolean isLoggedIn = false;
    private User currentUser = null;

    public AuthService() {
        // Default users
        users.add(new User("admin", "admin123", "admin"));
        users.add(new User("lecturer", "lecturer123", "lecturer"));
        users.add(new User("student", "student123", "student"));
    }

    // ===== SIGNUP =====
    public boolean signup(String username, String password, String role) {
        // Check if username exists
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                System.out.println("Username already exists!");
                return false;
            }
        }

        User newUser = new User(username, password, role);
        users.add(newUser);
        System.out.println("Account created: " + username);
        return true;
    }

    // ===== LOGIN =====
    public boolean login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                isLoggedIn = true;
                currentUser = u;
                System.out.println("Welcome, " + username + "!");
                return true;
            }
        }
        System.out.println("Login failed!");
        return false;
    }

    // ===== LOGOUT =====
    public void logout() {
        if (isLoggedIn) {
            System.out.println("Goodbye, " + currentUser.getUsername());
            isLoggedIn = false;
            currentUser = null;
        }
    }

    // ===== RESET PASSWORD =====
    public boolean resetPassword() {
        if (!isLoggedIn) {
            System.out.println("Please login first!");
            return false;
        }

        System.out.print("Current password: ");
        String currentPass = scan.nextLine();

        if (!currentUser.getPassword().equals(currentPass)) {
            System.out.println("Wrong password!");
            return false;
        }

        System.out.print("New password: ");
        String newPass = scan.nextLine();
        currentUser.setPassword(newPass);
        System.out.println("Password updated!");
        return true;
    }

    // ===== UPDATE ACCOUNT =====
    public boolean updateAccount(String newUsername, String newRole) {
        if (!isLoggedIn) {
            System.out.println("Please login first!");
            return false;
        }

        String oldUsername = currentUser.getUsername();

        // Update username if provided
        if (newUsername != null && !newUsername.isEmpty()) {
            for (User u : users) {
                if (u.getUsername().equals(newUsername) && !newUsername.equals(oldUsername)) {
                    System.out.println("Username '" + newUsername + "' is already taken!");
                    return false;
                }
            }
            currentUser.setUsername(newUsername);
            System.out.println("Username changed to: " + newUsername);
        }

        // Update role if provided
        if (newRole != null && !newRole.isEmpty()) {
            currentUser.setRole(newRole);
            System.out.println("Role changed to: " + newRole);
        }

        return true;
    }

    // ===== HELPER METHODS =====
    public boolean isLoggedIn() { return isLoggedIn; }
    public User getCurrentUser() { return currentUser; }
    public String getCurrentUsername() { return currentUser != null ? currentUser.getUsername() : ""; }
    public String getCurrentRole() { return currentUser != null ? currentUser.getRole() : "none"; }

    public void showAllUsers() {
        System.out.println("\n=== All Users ===");
        for (User user : users) {
            System.out.println("- " + user.getUsername() + " (" + user.getRole() + ")");
        }
    }
}
