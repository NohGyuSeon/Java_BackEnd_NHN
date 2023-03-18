package day01.third;

import java.util.Iterator;

public class Lecture implements Iterable<Student> {
    Student[] students;
    int index = 0;
    public Lecture(int size) {
        this.students = new Student[size];
    }

    public void add(Student student) {
        if (index > students.length) {
            System.out.println("FULL!");
            return;
        }
        students[index++] = student;
    }

    @Override
    public Iterator<Student> iterator() {
        return new LectureIterator<>(this);
    }
}
