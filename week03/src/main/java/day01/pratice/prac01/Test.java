package day01.pratice.prac01;

public class Test {

    public static void main(String[] args) {
        Lecture lecture = new Lecture(5);
        lecture.add(new Student(1, "NGS", "GOAT"));
        lecture.add(new Student(2, "LJE", "G"));
        lecture.add(new Student(3, "NWS", "O"));
        lecture.add(new Student(4, "NYH", "A"));
        lecture.add(new Student(5, "NSI", "T"));

        for (Student s : lecture) {
            System.out.println(s);
        }
    }
}
