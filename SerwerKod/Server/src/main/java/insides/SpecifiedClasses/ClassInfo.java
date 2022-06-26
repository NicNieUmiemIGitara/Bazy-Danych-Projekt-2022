package insides.SpecifiedClasses;

import insides.TableClasses.Klasy;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClassInfo implements Serializable {
    Klasy klasa = new Klasy();

    public int setKlasa(Connection connection, int id_klasy) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * \n" +
                            "FROM klasy\n" +
                            "WHERE klasy.id_klasy = " + id_klasy);
            if (resultSet.next()) {

                klasa.setId_klasy(resultSet.getInt(1));
                klasa.setRok(resultSet.getInt(2));
                klasa.setNazwa(resultSet.getString(3));
                return 1;//zalogowano jako nauczyciel
            }
            return 0;
        }

    }

    public Klasy getKlasa() {
        return klasa;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "klasa=" + klasa +
                '}';
    }
}