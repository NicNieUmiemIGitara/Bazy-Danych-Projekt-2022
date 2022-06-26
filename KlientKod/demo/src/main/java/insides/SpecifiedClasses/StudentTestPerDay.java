package insides.SpecifiedClasses;

import java.io.Serializable;
import java.sql.*;
import java.util.Vector;

public class StudentTestPerDay implements Serializable {
    Vector<String> timeFrames;
    Vector<String> subjects;

    public StudentTestPerDay() {
        timeFrames = new Vector<>();
        subjects = new Vector<>();
        timeFrames.setSize(5);
        subjects.setSize(5);
    }

    public Vector<String> getTimeFrames() {
        return timeFrames;
    }

    public void setTimeFrames(Vector<String> timeFrames) {
        this.timeFrames = timeFrames;
    }

    public Vector<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(Vector<String> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "\nStudentTestPerDay{" +
                "timeFrames=" + timeFrames +
                ", subjects=" + subjects +
                '}';
    }

    public int setDay(Connection connection, int dayId, int classId, Date date1, Date date2) throws SQLException {
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(
                "SELECT\n" +
                        "CASE lekcje.godz_lekcyjna \n" +
                        "WHEN 1 THEN '8-10'\n" +
                        "WHEN 2 THEN '10-12'\n" +
                        "WHEN 3 THEN '12-14'\n" +
                        "WHEN 4 THEN '14-16'\n" +
                        "WHEN 5 THEN '16-18'\n" +
                        "END AS \" \",\n" +
                        "NVL(TO_CHAR(sprawdziany.opis), ' ') AS WT\n" +
                        "FROM sprawdziany \n" +
                        "JOIN lekcje ON sprawdziany.id_sprawdzianu = lekcje.sprawdziany_id_sprawdzianu\n" +
                        "LEFT JOIN sprawdziany ON lekcje.sprawdziany_id_sprawdzianu = sprawdziany.id_sprawdzianu \n" +
                        //"JOIN nauczyciele ON nauczyciele.id_nauczyciela = lekcje.nauczyciele_id_nauczyciela\n" +
                        "WHERE TO_CHAR(lekcje.data, 'D') = " + dayId + "\n" +
                        //"AND nauczyciele.id_nauczyciela = " + teacherId + " --ZMIENNA ID_NAUCZYCIELA*\n" +
                        "AND lekcje.klasy_id_klasy = " + classId + " --ZMIENNA _ID_KLASY*\n" +
                        "AND lekcje.data BETWEEN TO_DATE('" + date1.toString() + "','YYYY-MM-DD') AND TO_DATE('" + date2.toString() + "','YYYY-MM-DD')" +
                        "--2 ZMIENNE - (data poniedzialku i piatku)*\n")) {
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
}
