package app;
import structure.*;
import model.*;
public class App  {
    public static void main(String[] args) {
        UniSystem u = new UniSystem();
        u.addStudent(new Student("Fahmi",19));
        System.out.println("Im a dummy");
        System.out.println(u.getStudent());
    }
}