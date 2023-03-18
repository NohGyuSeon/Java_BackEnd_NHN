package day01.third;

public class Student {
    private int studentNo;
    private String name;

    public Student(int studentNo, String name, String department) {
        this.studentNo = studentNo;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.valueOf(studentNo) + "번 " + name;
    }

}
