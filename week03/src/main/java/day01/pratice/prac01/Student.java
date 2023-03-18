package day01.pratice.prac01;

public class Student {
    private int studentNo;
    private String name;
    private String department;

    public Student(int studentNo, String name, String department) {
        this.studentNo = studentNo;
        this.name = name;
        this.department = department;
    }

    @Override
    public String toString() {
        return studentNo + "번 " + name + " " + department;

    }

}
