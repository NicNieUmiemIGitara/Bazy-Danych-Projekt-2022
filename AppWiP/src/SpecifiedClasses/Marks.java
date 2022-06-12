package SpecifiedClasses;

import java.util.Vector;

public class Marks {
    String info;
    Vector<Integer> marks;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Vector<Integer> getMarks() {
        return marks;
    }

    public void setMarks(Vector<Integer> marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "\nMarks{" +
                "info='" + info + '\'' +
                ", marks=" + marks +
                '}';
    }
}
