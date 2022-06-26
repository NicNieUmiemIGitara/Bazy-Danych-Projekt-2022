package insides.SpecifiedClasses.Teacher;

import insides.SpecifiedClasses.TeacherDayOfTheWeek;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

public class TeacherWeekSchedule {
    Vector<TeacherDayOfTheWeek> days;

    public TeacherWeekSchedule() {
        days = new Vector<>();
        days.setSize(5);
    }

    @Override
    public String toString() {
        return "TeacherWeekSchedule{" +
                "days=" + days +
                '}';
    }

    public int setSchedule(Connection connection, int teacherId, Date date1, Date date2) throws SQLException {
        TeacherDayOfTheWeek day;
        for (int i = 0; i < 5; i++) {
            day = new TeacherDayOfTheWeek();
            day.setDay(connection, i + 1, teacherId, date1, date2);
            days.set(i, day);
        }


        return 0;
    }

    public Vector<TeacherDayOfTheWeek> getDays() {
        return days;
    }
}
