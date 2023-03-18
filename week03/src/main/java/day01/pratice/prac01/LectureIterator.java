package day01.pratice.prac01;

import java.util.Iterator;

public class LectureIterator<T> implements Iterator<T> {
    Lecture lecture;
    int index = 0;

    public LectureIterator(Lecture lecture) {
        this.lecture = lecture;
    }

    @Override
    public boolean hasNext() {
        if (index >= lecture.students.length) {
            return false;
        }
        return (lecture.students[index] != null);
    }

    @Override
    public T next() {
        return (T) lecture.students[index++];
    }


}
