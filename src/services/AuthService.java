package services;

import model.User;
import java.util.HashMap;
import java.util.Scanner;

public class AuthService {
    private Scanner scan = new Scanner(System.in);
    private HashMap<String, User> users = new HashMap<>();
    private boolean isLoggedIn = false;
    private User currentUser = null;
    
    public AuthService() {
        // Default users
        users.put("admin", new User("admin", "admin123", "admin"));
        users.put("lecturer", new User("lecturer", "lecturer123", "lecturer"));
        users.put("student", new User("student", "student123", "student"));
    }
    
    // ========== ALL YOUR NEEDED METHODS ==========
    
    // 1. SIGNUP/REGISTER
    public boolean signup(String username, String password, String role) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists!");
            return false;
        }
        User newUser = new User(username, password, role);
        users.put(username, newUser);
        System.out.println("Account created: " + username);
        return true;
    }
    
    // 2. LOGIN
    public boolean login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            isLoggedIn = true;
            currentUser = user;
            System.out.println("Welcome, " + username + "!");
            return true;
        }
        System.out.println("Login failed!");
        return false;
    }
    
    // 3. LOGOUT
    public void logout() {
        if (isLoggedIn) {
            System.out.println("Goodbye, " + currentUser.getUsername());
            isLoggedIn = false;
            currentUser = null;
        }
    }
    
    // 4. RESET PASSWORD
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
    
    // 5. UPDATE ACCOUNT - Keep only ONE version (the better one)
    public boolean updateAccount(String newUsername, String newRole) {
        if (!isLoggedIn) {
            System.out.println("Please login first!");
            return false;
        }
        
        String oldUsername = currentUser.getUsername();
        
        // Update username if provided
        if (newUsername != null && !newUsername.isEmpty()) {
            // Check if new username is already taken
            if (users.containsKey(newUsername) && !newUsername.equals(oldUsername)) {
                System.out.println("Username '" + newUsername + "' is already taken!");
                return false;
            }
            
            // Remove old entry, add new one
            users.remove(oldUsername);
            currentUser.setUsername(newUsername);
            users.put(newUsername, currentUser);
            System.out.println("Username changed to: " + newUsername);
        }
        
        // Update role if provided
        if (newRole != null && !newRole.isEmpty()) {
            currentUser.setRole(newRole);
            System.out.println("Role changed to: " + newRole);
        }
        
        return true;
    }
    
    // ========== HELPER METHODS ==========
    
    public boolean isLoggedIn() { return isLoggedIn; }
    public User getCurrentUser() { return currentUser; }
    public String getCurrentUsername() { 
        return currentUser != null ? currentUser.getUsername() : ""; 
    }
    public String getCurrentRole() { 
        return currentUser != null ? currentUser.getRole() : "none"; 
    }
    public User getUser(String username) { return users.get(username); }
    
    public void showAllUsers() {
        System.out.println("\n=== All Users ===");
        for (User user : users.values()) {
            System.out.println("- " + user.getUsername() + " (" + user.getRole() + ")");
        }
    }
}