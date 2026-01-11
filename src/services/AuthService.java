package services;

import model.Student;
import model.User;
import java.util.LinkedList;

public class AuthService {

    //Data members
    private LinkedList<User> users = new LinkedList<>();
    private User currentUser;

    public AuthService() {
        // ===== Default & dummy users =====
        users.add(new User("System Admin", "admin@uni.edu", "admin", "admin123", "admin"));
        users.add(new User("Dr John Lecturer", "john@uni.edu", "lecturer1", "lect123", "lecturer"));
        users.add(new User("Alice Student", "alice@uni.edu", "student1", "stud123", "student"));
        users.add(new User("Guest User", "guest@uni.edu", "guest1", "guest123", "guest"));
        users.add(new Student("Muhammad Fahmi", "fahmi@gmail.com", "fahmi", "123", "student","fahmi",20,202441064));
        users.add(new Student("khalid bin amin", "khalid@gmail", "1", "1", "Student", "khalid", 20, 20144890));
        users.add(new Student("Iman", "imangmail", "iman", "1", "Student", "iman op", 20, 608016510));
    }

    // ===== SIGNUP =====
    public boolean signup(String fullName, String email, String username, String password, String role) {

        if (fullName == null || email == null || username == null || password == null) return false;

        fullName = fullName.trim();
        email = email.trim();
        username = username.trim();
        password = password.trim();

        if (fullName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) return false;
        if (userExists(username)) return false;

        if (role.equals("Student")) {
            users.add(new Student(fullName, email, username, password, role, username, 0, 0));
        } else {
            users.add(new User(fullName, email, username, password, role));
        }

        return true;
    }

    // ===== LOGIN =====
    public boolean login(String username, String password) {

        if (username == null || password == null) return false;

        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
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
