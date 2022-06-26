package insides.SpecifiedClasses.Teacher;

import insides.TableClasses.Uczniowie;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

public class TeacherFrequency implements Serializable {
    Vector<Uczniowie> list;

    @Override
    public String toString() {
        return "TeacherFrequency{" +
                "list=" + list +
                '}';
    }

    public int setList(Connection connection, int classId) throws SQLException {
        list = new Vector<>();
        Uczniowie u;
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(
                "SELECT uczniowie.id_ucznia, uczniowie.nazwisko , uczniowie.imie\n" +
                        "FROM klasy, uczniowie\n" +
                        "WHERE klasy.id_klasy = uczniowie.klasy_id_klasy\n" +
                        "AND klasy.id_klasy = " + classId + " ----ZMIENNA ID_KLASY\n" +
                        "ORDER BY uczniowie.id_ucznia")) {
            while (resultSet.next()) {
                u = new Uczniowie();
                u.setId_ucznia(Integer.valueOf(resultSet.getString(1)));
                u.setNazwisko(resultSet.getString(2));
                u.setImie(resultSet.getString(3));
                list.add(u);
            }
            return 0;
        }
    }

    public Vector<Uczniowie> retList() {
        return list;
    }

    public int chechl(Connection connection, int present, int studentId, int lessonId) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "INSERT INTO frekwencja(id_frekwencji, obecnosc, uczniowie_id_ucznia, lekcje_id_lekcji)\n" +
                            "VALUES ((SELECT (MAX(id_frekwencji+1)) FROM frekwencja), " + present + ", " + studentId + ", " + lessonId + ") --3 ZMIENNE obecny/nieobecny (1/0), id_uczen, id_lekcja");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getLessonId(Connection connection, int id_klasa, int godz, int id_nauczyciela, Date date) {
        int lessonId = -1;
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(
                "SELECT id_lekcji, godz_lekcyjna, data, przedmioty_id_przedmiotu, nauczyciele_id_nauczyciela, sprawdziany_id_sprawdzianu, klasy_id_klasy, sala_id_sala\n" +
                        "FROM lekcje \n" +
                        "WHERE lekcje.godz_lekcyjna = " + godz + " \n" +
                        "AND lekcje.klasy_id_klasy = " + id_klasa + "\n" +
                        "AND lekcje.nauczyciele_id_nauczyciela = " + id_nauczyciela + "\n" +
                        "AND lekcje.data = TO_DATE('" + date.toString() + "','YYYY-MM-DD')")) {
            while (resultSet.next()) {
                lessonId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessonId;

    }
}