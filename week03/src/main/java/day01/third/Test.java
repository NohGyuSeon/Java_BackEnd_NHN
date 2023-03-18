package day01.third;

public class Test {
    public static void main(String[] args) {
        Lecture lecture = new Lecture(5);
        lecture.add(new Student(1, "Cellin", "CS"));
        lecture.add(new Student(2, "VEspoer", "MSE"));
        lecture.add(new Student(3, "Ploman", "PHY"));
        lecture.add(new Student(4, "Veslpeoer", "Electronic"));
        lecture.add(new Student(5, "roChaawln", "English"));

        for (Student s : lecture) {
            System.out.println(s);
        }
    }
}
