package insides.SpecifiedClasses.Teacher;

import insides.SpecifiedClasses.Marks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class TeacherMarks {
    Vector<Marks> marks;

    public TeacherMarks() {
        marks = new Vector<>();
    }

    @Override
    public String toString() {
        return "TeacherMarks{" +
                "marks=" + marks +
                '}';
    }

    public int setMarks(Connection connection, int teacherId, int classId) throws SQLException {
        Vector<Integer> tmp;
        Marks tmpMark;
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(
                "SELECT uczniowie.nazwisko || ' ' || uczniowie.imie AS \"Naziwsko i Imie\",  LISTAGG(oceny.ocena, ' ') AS \"Ocena\"\n" +
                        "FROM klasy\n" +
                        "JOIN uczniowie ON klasy.id_klasy = uczniowie.klasy_id_klasy\n" +
                        "JOIN oceny ON uczniowie.id_ucznia = oceny.uczniowie_id_ucznia\n" +
                        "JOIN przedmioty ON przedmioty.id_przedmiotu = oceny.przedmioty_id_przedmiotu\n" +
                        "WHERE oceny.nauczyciele_id_nauczyciela = " + teacherId + " --ZMIENNA ID_NAUCZYCIELA*\n" +
                        "AND klasy.id_klasy = " + classId + " --ZMIENNA ID_KLASY*\n" +
                        "GROUP BY uczniowie.nazwisko, uczniowie.imie")) {

            while (resultSet.next()) {
                tmpMark = new Marks();
                tmp = new Vector<>();
                tmpMark.setInfo(resultSet.getString(1));
                String str = resultSet.getString(2);
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) <= '5' && str.charAt(i) >= '1') {
                        tmp.add((int) str.charAt(i) - 48);
                    }
                }
                tmpMark.setMarks(tmp);
                marks.add(tmpMark);
            }
        }
        return 0;
    }

    public int addMark(Connection connection, int mark, String description, int studentId, int teacherId, int subjectId) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "INSERT INTO oceny(id_oceny, ocena, opis, uczniowie_id_ucznia, nauczyciele_id_nauczyciela, przedmioty_id_przedmiotu)\n" +
                            "VALUES ((SELECT (MAX(id_oceny+1)) FROM oceny), " + mark + ", " + description + ", " + studentId + ", " + teacherId + ", " + subjectId + ") -- 5 ZMIENNYCH ocena, opis, id_uczen, id_nauczyciel, id_przedmiot");

        }
        return 0;
    }

    public int deleteMarks(Connection connection, int markId) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "DELETE FROM oceny WHERE id_oceny = " + markId + " --ZMIENNA ID_OCEN");

        }
        return 0;
    }
}
