package services;

import model.Student;
import java.util.LinkedList;

public class StudentManager {

    //LL to store student data
    private LinkedList<Student> students = new LinkedList<>();

    //Add a new student
    public void addStudent(Student s) {
        for (Student student : students) {
            if (student.getName().equals(s.getName())) {
                System.out.println("student already in system");  //later will filter by matrix number not name
                break;  //exit loop then add the student if not
            }
        }
        students.add(s);
    }

    //Remove a student
    public void removeStudent(Student s) {
        for (Student student : students) {
            if (student.getName().equals(s.getName())) {
                System.out.println("student already in system");  //later will filter by matrix number not name
                break;  //exit loop then add the student if not
            }
        }
        students.remove(s);
    }

    //List all students
    public LinkedList<Student> getStudents() {
        return students;
    }

    //Find/search for a student (by name, ID, etc.)
    public boolean findStudent(Student s) {
        for (Student student : students) {
            if (student.getName().equals(s.getName())) {
                System.out.println("student already in system");  //later will filter by matrix number not name
                return true;  //exit loop then add the student if not
            }
        }
        return false;  //it should return whatever info about student i guess but idk wtf to implement rn student not exist in system
    }

    //Update student information (if you have more attributes like age, grade, etc.)
    public void updateStudent(Student s){  //should be based on matrix number since we wanna know which student to update right?
        for (Student item : students) {
            item.setAge(item.getAge());  //will connect to gui later
            item.setName(item.getName());  //same
        }
    }

    //Return student data as String (for console or GUI display)
    //Possibly handle validation (like no empty names)

}
