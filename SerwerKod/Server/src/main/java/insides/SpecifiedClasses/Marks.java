package insides.SpecifiedClasses;

import java.io.Serializable;
import java.util.Vector;

public class Marks implements Serializable {
    int studentId;
    int markId;
    String info;
    Vector<Integer> marks;
    Integer mark;
    String opis;

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

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
                ", mark=" + mark +
                ", opis='" + opis + '\'' +
                "}";
    }
}
