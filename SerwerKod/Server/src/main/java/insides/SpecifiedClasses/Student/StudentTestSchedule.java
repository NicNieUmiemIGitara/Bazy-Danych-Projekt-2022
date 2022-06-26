package insides.SpecifiedClasses.Student;

import insides.SpecifiedClasses.StudentTestPerDay;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class StudentTestSchedule implements Serializable {
    Vector<StudentTestPerDay> days;

    public StudentTestSchedule() {
        days = new Vector<>();
        days.setSize(5);
    }

    @Override
    public String toString() {
        return "TeacherTestSchedule{" +
                "days=" + days +
                '}';
    }

    public Vector<StudentTestPerDay> getDays() {
        return days;
    }

    public int setSchedule(Connection connection, int classId, Date date1, Date date2) throws SQLException {
        StudentTestPerDay day;
        for (int i = 0; i < 5; i++) {
            day = new StudentTestPerDay();
            day.setDay(connection, i + 1, classId, date1, date2);
            days.set(i, day);
        }


        return 0;
    }

    public int addTest(Connection connection, String description, int godz, int id_klasy, Date date) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "INSERT INTO sprawdziany(id_sprawdzianu, opis)\n" +
                            "VALUES ((SELECT (MAX(id_sprawdzianu+1)) FROM sprawdziany), '" + description + "')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "UPDATE lekcje\n" +
                            "SET sprawdziany_id_sprawdzianu = (SELECT (MAX(id_sprawdzianu)) FROM sprawdziany)\n" +
                            "WHERE lekcje.godz_lekcyjna = " + godz + "\n" +
                            "AND lekcje.klasy_id_klasy = " + id_klasy + "\n" +
                            "AND lekcje.data = TO_DATE('" + date + "','YYYY-MM-DD')");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int deleteTest(Connection connection, int godz, int id_klasy, Date date) {

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "UPDATE lekcje\n" +
                            "SET sprawdziany_id_sprawdzianu = NULL\n" +
                            "WHERE lekcje.godz_lekcyjna = " + godz + "\n" +
                            "AND lekcje.klasy_id_klasy = " + id_klasy + "\n" +
                            "AND lekcje.data = TO_DATE('" + date + "','YYYY-MM-DD')");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}


