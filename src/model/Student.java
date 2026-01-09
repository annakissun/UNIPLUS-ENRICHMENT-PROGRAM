package model;

/**
 * Student class.
 * Extends User with student-specific information.
 */
public class Student extends User {

    // ===== DATA MEMBERS =====
    private String name;
    private int age;
    private int matrixNum;

    // ===== CONSTRUCTORS =====

    /**
     * No-argument constructor.
     */
    public Student() {
        super();
    }

    /**
     * Full constructor for Student.
     */
    public Student(String fullName, String email, String username, String password, String role, String name, int age, int matrixNum) {
    super(fullName, email, username, password, role);
    this.name = name;
    this.age = age;
    this.matrixNum = matrixNum;
}


    // ===== SETTERS =====

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMatrixNum(int matrixNum) {
        this.matrixNum = matrixNum;
    }

    // ===== GETTERS =====

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getMatrixNum() {
        return matrixNum;
    }

    // ===== UTILITY =====

    @Override
    public String toString() {
        return "\nName          : " + name +
               "\nAge           : " + age +
               "\nMatrix Number : " + matrixNum;
    }
}
