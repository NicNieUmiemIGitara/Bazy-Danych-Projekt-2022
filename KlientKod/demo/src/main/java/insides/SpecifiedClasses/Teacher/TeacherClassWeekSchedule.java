package insides.SpecifiedClasses.Teacher;

import insides.SpecifiedClasses.ClassDayOfTheWeek;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

public class TeacherClassWeekSchedule {
    Vector<ClassDayOfTheWeek> days;

    public TeacherClassWeekSchedule() {
        days = new Vector<>();
        days.setSize(5);
    }

    @Override
    public String toString() {
        return "TeacherClassWeekSchedule{" +
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
