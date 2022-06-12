package SpecifiedClasses;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ClassDayOfTheWeek {
    Vector<String> timeFrames;
    Vector<String> subjects;

    public ClassDayOfTheWeek() {
        timeFrames = new Vector<>();
        subjects = new Vector<>();
        timeFrames.setSize(5);
        subjects.setSize(5);
    }

    @Override
    public String toString() {
        return "\nClassDayOfTheWeek{" +
                "timeFrames=" + timeFrames +
                ", subjects=" + subjects +
                '}';
    }

    public int setDay(Connection connection, int dayId, int classId) throws SQLException {
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(
                "SELECT\n" +
                        "CASE lekcje.godz_lekcyjna \n" +
                        "WHEN 1 THEN '8-10'\n" +
                        "WHEN 2 THEN '10-12'\n" +
                        "WHEN 3 THEN '12-14'\n" +
                        "WHEN 4 THEN '14-16'\n" +
                        "WHEN 5 THEN '16-18'\n" +
                        "END AS \" \",\n" +
                        "NVL(TO_CHAR((SELECT przedmioty.nazwa FROM przedmioty \n" +
                        "WHERE przedmioty.id_przedmiotu = lekcje.przedmioty_id_przedmiotu AND TO_CHAR(lekcje.data, 'D') = " + dayId + ")), ' ') AS PON\n" +
                        "FROM przedmioty, lekcje\n" +
                        "WHERE przedmioty.id_przedmiotu = lekcje.przedmioty_id_przedmiotu\n" +
                        "AND TO_CHAR(lekcje.data, 'D') = " + dayId + "\n" +
                        "AND lekcje.klasy_id_klasy = " + classId)) {
            while (resultSet.next()) {
                //System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
                if (resultSet.getString(1).equals("8-10")) {
                    //System.out.println(resultSet.getString(1));
                    timeFrames.set(0, resultSet.getString(1));
                    subjects.set(0, resultSet.getString(2));

                } else if (resultSet.getString(1).equals("10-12")) {
                    //System.out.println(resultSet.getString(1));
                    timeFrames.set(1, resultSet.getString(1));
                    subjects.set(1, resultSet.getString(2));
                } else if (resultSet.getString(1).equals("12-14")) {
                    //System.out.println(resultSet.getString(1));
                    timeFrames.set(2, resultSet.getString(1));
                    subjects.set(2, resultSet.getString(2));
                } else if (resultSet.getString(1).equals("14-16")) {
                    //System.out.println(resultSet.getString(1));
                    timeFrames.set(3, resultSet.getString(1));
                    subjects.set(3, resultSet.getString(2));
                } else if (resultSet.getString(1).equals("16-18")) {
                    //System.out.println(resultSet.getString(1));
                    timeFrames.set(4, resultSet.getString(1));
                    subjects.set(4, resultSet.getString(2));
                }
            }
        }
        return 0;
    }

    public Vector<String> getTimeFrames() {
        return timeFrames;
    }

    public Vector<String> getSubjects() {
        return subjects;
    }
}
