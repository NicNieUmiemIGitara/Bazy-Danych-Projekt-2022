package insides.SpecifiedClasses.Headmaster;

import insides.TableClasses.Adresy;
import insides.TableClasses.Nauczyciele;
import insides.TableClasses.Uczniowie;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddPeople {
    public int addTeacher(Connection connection, Nauczyciele n) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "INSERT INTO nauczyciele(id_nauczyciela, login, haslo, imie, nazwisko, pensja, adresy_id_adresu)\n" +
                            "VALUES ((SELECT (MAX(id_nauczyciela+1)) FROM nauczyciele), '" + n.getLogin() + "', " +
                            "'" + n.getHaslo() + "', '" + n.getImie() + "', '" + n.getNazwisko() + "'" +
                            ", " + n.getPensja() + "," + n.getAdresy_id_adresu());

        }
        return 0;
    }

    public int addStudent(Connection connection, Uczniowie u) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "INSERT INTO uczniowie(id_ucznia, login, haslo, imie, nazwisko, klasy_id_klasy, adresy_id_adresu)\n" +
                            "VALUES ((SELECT (MAX(id_ucznia+1)) FROM uczniowie), '" + u.getLogin() + "', " +
                            "'" + u.getHaslo() + "', '" + u.getImie() + "', '" + u.getNazwisko() + "'" +
                            ", " + u.getKlasy_id_klasy() + "," + u.getAdresy_id_adresu());

        }
        return 0;
    }

    public int addAddress(Connection connection, Adresy a) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "INSERT INTO adresy(id_adresu, ulica, nr_domu, miejscowosc)\n" +
                            "VALUES ((SELECT (MAX(id_adresu+1)) FROM adresy), '" + a.getUlica() + "'" +
                            ", " + a.getNr_domu() + ", " +
                            "'" + a.getMiejscowosc() + "') --ZMIENNE ADRESU\n");

        }
        return 0;
    }
}
