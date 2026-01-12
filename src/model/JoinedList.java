package model;

import java.util.LinkedList;
import java.util.List;

/**
 * ADT: Joined List
 * Data Structure: LinkedList
 */
public class JoinedList {

    private LinkedList<Student> list;

    public JoinedList() {
        list = new LinkedList<>();
    }

    // INSERT
    public void add(Student s) {
        list.add(s);
    }

    // DELETE
    public boolean remove(Student s) {
        return list.remove(s);
    }

    // SEARCH
    public boolean contains(Student s) {
        return list.contains(s);
    }

    // SIZE
    public int size() {
        return list.size();
    }

    // TRAVERSAL
    public List<Student> getAll() {
        return new LinkedList<>(list);
    }
}
