package insides.SpecifiedClasses.Student;

import insides.SpecifiedClasses.ClassDayOfTheWeek;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

public class StudentClassWeekSchedule implements Serializable {
    Vector<ClassDayOfTheWeek> days;

    public StudentClassWeekSchedule() {
        days = new Vector<>();
        days.setSize(5);
    }

    @Override
    public String toString() {
        return "StudentClassWeekSchedule{" +
                "days=" + days +
                '}';
    }

    public int setSchedule(Connection connection, int classId, Date date1, Date date2) throws SQLException {
        ClassDayOfTheWeek day;
        for (int i = 0; i < 5; i++) {
            day = new ClassDayOfTheWeek();
            day.setDay(connection, i + 1, classId, date1, date2);
            days.set(i, day);
        }


        return 0;
    }

    public Vector<ClassDayOfTheWeek> getDays() {
        return days;
    }
}

