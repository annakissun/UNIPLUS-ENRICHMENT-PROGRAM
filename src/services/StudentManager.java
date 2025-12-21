package services;

import model.Student;
import java.util.LinkedList;

public class StudentManager {

    private LinkedList<Student> students = new LinkedList<>();

    public void addStudent(Student s) {
        students.add(s);
    }

    public LinkedList<Student> getStudents() {
        return students;
    }
}
