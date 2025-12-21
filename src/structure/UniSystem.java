package structure;
import model.*;
import java.util.LinkedList;

public class UniSystem {
    //Data members
    private LinkedList<Student> sl;
    private LinkedList<Session> sessions;

    public void createSession (Session s) {
        sessions.add(s);
    }

    public void addStudent(Student s) {
        sl.add(s);
    }
    public LinkedList<Student> getStudents() {
        return sl;
    }

}