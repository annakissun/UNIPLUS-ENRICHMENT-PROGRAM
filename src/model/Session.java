package model;

import java.util.LinkedList;

public class Session {

    private int capacity;
    private String code;
    private String host;
    private String subject;
    private LinkedList<Student> students;

    public Session(int capacity, String host, String subject) {
        this.capacity = capacity;
        this.host = host;
        this.subject = subject;
        this.students = new LinkedList<>();
    }

    public boolean addStudent(Student s) {
        if (students.size() >= capacity) {
            return false;
        }
        students.add(s);
        return true;
    }

    public LinkedList<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "Session{" +
                "capacity=" + capacity +
                ", host='" + host + '\'' +
                ", subject='" + subject + '\'' +
                ", students=" + students +
                '}';
    }
}
