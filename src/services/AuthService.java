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
        users.add(new Student("Muhammad Fahmi", "fahmi@gmail.com", "fahmi", "123", "student", "fahmi", 20, 202441064));
        users.add(new Student("Khalid bin Amin", "khalid@gmail.com", "khalid", "123", "student", "khalid", 20, 20144890));
        users.add(new Student("Iman Omar", "iman@gmail.com", "iman", "123", "student", "iman", 20, 608016510));
        users.add(new Student("Alice Tan", "alice@gmail.com", "alice", "123", "student", "alice", 20, 202341001));
        users.add(new Student("Bob Lee", "bob@gmail.com", "bob", "123", "student", "bob", 20, 202341002));
        users.add(new Student("Charlie Lim", "charlie@gmail.com", "charlie", "123", "student", "charlie", 20, 202341003));
        users.add(new Student("David Ng", "david@gmail.com", "david", "123", "student", "david", 20, 202341004));
        users.add(new Student("Ethan Chong", "ethan@gmail.com", "ethan", "123", "student", "ethan", 20, 202341005));
        users.add(new Student("Farah Ali", "farah@gmail.com", "farah", "123", "student", "farah", 20, 202341006));
        users.add(new Student("Grace Wong", "grace@gmail.com", "grace", "123", "student", "grace", 20, 202341007));
        users.add(new Student("Hadi Hassan", "hadi@gmail.com", "hadi", "123", "student", "hadi", 20, 202341008));
        users.add(new Student("Irene Lim", "irene@gmail.com", "irene", "123", "student", "irene", 20, 202341009));
        users.add(new Student("Jason Tan", "jason@gmail.com", "jason", "123", "student", "jason", 20, 202341010));
        users.add(new Student("Kelly Ng", "kelly@gmail.com", "kelly", "123", "student", "kelly", 20, 202341011));
        users.add(new Student("Lina Omar", "lina@gmail.com", "lina", "123", "student", "lina", 20, 202341012));

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

    // NEW METHOD
    public Student findStudentByUsername(String username) {
        if (username == null) return null;

        for (User u : users) {
            if (u instanceof Student && u.getUsername().equals(username)) {
                return (Student) u;
            }
        }
        return null;
    }
}
