package structure;

import java.util.LinkedList;
import java.util.Queue;
import model.Student;

/**
 * ADT: Waitlist Queue
 * Data Structure: Queue (FIFO)
 */
public class WaitlistQueue {

    private Queue<Student> queue;

    public WaitlistQueue() {
        queue = new LinkedList<>();
    }

    // ENQUEUE
    public void enqueue(Student s) {
        queue.offer(s);
    }

    // DEQUEUE
    public Student dequeue() {
        return queue.poll();
    }

    // REMOVE specific student
    public boolean remove(Student s) {
        return queue.remove(s);
    }

    // SEARCH
    public boolean contains(Student s) {
        return queue.contains(s);
    }

    // SIZE
    public int size() {
        return queue.size();
    }

    // EMPTY CHECK
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // TRAVERSAL
    public Queue<Student> getAll() {
        return new LinkedList<>(queue);
    }
}
