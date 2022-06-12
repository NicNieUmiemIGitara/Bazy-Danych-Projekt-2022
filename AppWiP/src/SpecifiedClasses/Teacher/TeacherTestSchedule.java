package SpecifiedClasses.Teacher;

import SpecifiedClasses.TeacherTestsPerDay;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

public class TeacherTestSchedule {
    Vector<TeacherTestsPerDay> days;
    public  TeacherTestSchedule(){
        days=new Vector<>();
        days.setSize(5);
    }

    @Override
    public String toString() {
        return "TeacherTestSchedule{" +
                "days=" + days +
                '}';
    }

    public int setSchedule(Connection connection, int teacherId, int classId, Date date1, Date date2) throws SQLException {
        TeacherTestsPerDay day;
        for (int i = 0; i < 5; i++) {
            day=new TeacherTestsPerDay();
            day.setDay(connection,i+1, teacherId,classId,date1,date2);
            days.set(i,day);
        }


        return 0;
    }
}
