package model;
public class Student {
    //Data members
    private String name;
    private int age;

    public Student () {
    }
    public Student (String name, int age) {
        this.name = name;
        this.age = age;
    }

    //Setter
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    //Getter
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String toString () {
        return "\n Name : " + name +
        "\n Age : " + age;
    }
}