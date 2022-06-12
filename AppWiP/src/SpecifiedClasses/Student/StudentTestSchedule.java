package SpecifiedClasses.Student;

import SpecifiedClasses.StudentTestPerDay;
import SpecifiedClasses.TeacherTestsPerDay;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

public class StudentTestSchedule {
    Vector<StudentTestPerDay> days;
    public  StudentTestSchedule(){
        days=new Vector<>();
        days.setSize(5);
    }

    @Override
    public String toString() {
        return "TeacherTestSchedule{" +
                "days=" + days +
                '}';
    }

    public int setSchedule(Connection connection, int classId, Date date1, Date date2) throws SQLException {
        StudentTestPerDay day;
        for (int i = 0; i < 5; i++) {
            day=new StudentTestPerDay();
            day.setDay(connection,i+1,classId,date1,date2);
            days.set(i,day);
        }


        return 0;
    }
}


