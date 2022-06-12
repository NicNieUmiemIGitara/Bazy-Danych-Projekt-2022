package SpecifiedClasses.Teacher;

import TableClasses.Uczniowie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class TeacherFrequency {
    Vector<Uczniowie> list;

    @Override
    public String toString() {
        return "TeacherFrequency{" +
                "list=" + list +
                '}';
    }

    public int getList(Connection connection, int classId) throws SQLException {
        list = new Vector<>();
        Uczniowie u;
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(
                "SELECT uczniowie.id_ucznia, uczniowie.nazwisko , uczniowie.imie\n" +
                        "FROM klasy, uczniowie\n" +
                        "WHERE klasy.id_klasy = uczniowie.klasy_id_klasy\n" +
                        "AND klasy.id_klasy = " + classId + " ----ZMIENNA ID_KLASY\n" +
                        "ORDER BY uczniowie.id_ucznia")) {
            while (resultSet.next()) {
                u=new Uczniowie();
                u.setId_ucznia(Integer.valueOf(resultSet.getString(1)));
                u.setNazwisko(resultSet.getString(2));
                u.setImie(resultSet.getString(3));
                list.add(u);
            }
            return 0;
        }
    }
    public int chechl(Connection connection,int present,int studentId,int lessonId) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "INSERT INTO frekwencja(id_frekwencji, obecnosc, uczniowie_id_ucznia, lekcje_id_lekcji)\n" +
                            "VALUES ((SELECT (MAX(id_frekwencji+1)) FROM frekwencja), "+present+", "+studentId+", "+lessonId+") --3 ZMIENNE obecny/nieobecny (1/0), id_uczen, id_lekcja");

        }
        return 0;
    }
}