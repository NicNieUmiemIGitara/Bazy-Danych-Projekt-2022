package insides.SpecifiedClasses.Student;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

public class StudentFrequency implements Serializable {
    Vector<DayFreq> days;

    public StudentFrequency() {
        days = new Vector<>();
        days.setSize(5);
    }

    @Override
    public String toString() {
        return "StudentFrequency{" +
                "days=" + days +
                '}';
    }

    public Vector<DayFreq> getDays() {
        return days;
    }

    public int setSchedule(Connection connection, int studentId, Date date1, Date date2) throws SQLException {
        DayFreq day;
        for (int i = 0; i < 5; i++) {
            day = new DayFreq();
            day.setDay(connection, i + 1, studentId, date1, date2);
            days.set(i, day);
        }


        return 0;
    }
}
