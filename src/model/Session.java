package model;

import java.util.LinkedList;
import services.*;

public class Session {
    //Data members
    private int capacity;
    private String code;
    private String host;
    private String subject;
    private LinkedList<Student> students;

    //Constructor
    public Session(int capacity, String host, String subject) {
        this.capacity = capacity;
        this.host = host;
        this.subject = subject;
        this.students = new LinkedList<>();
        this.code = CodeGenerator.generateCode(6); // e.g., ABC123
    }

    //Adding Student
    public boolean addStudent(Student s) {
        if (students.size() >= capacity) {
            return false;
        }
        students.add(s);
        return true;
    }
    //return student from the list?
    public LinkedList<Student> getStudents() {
        return students;
    }

    public String getCode() {
        return code;
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
