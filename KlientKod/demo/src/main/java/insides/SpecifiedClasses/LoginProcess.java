package insides.SpecifiedClasses;

import insides.TableClasses.Dyrektor;
import insides.TableClasses.Nauczyciele;
import insides.TableClasses.Uczniowie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginProcess {
    public int login(Connection connection, String username, String password,
                     Uczniowie uczniowie,
                     Nauczyciele nauczyciele,
                     Dyrektor dyrektor) throws SQLException {

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * \n" +
                            "FROM nauczyciele\n" +
                            "WHERE nauczyciele.login = '" + username + "'\n" +
                            "AND nauczyciele.haslo = '" + password + "'");
            if (resultSet.next()) {

                nauczyciele.setId_nauczyciela(resultSet.getInt(1));
                nauczyciele.setImie(resultSet.getString(4));
                nauczyciele.setNazwisko(resultSet.getString(5));
                return 1;//zalogowano jako nauczyciel
            }
            resultSet = statement.executeQuery(
                    "SELECT * \n" +
                            "FROM uczniowie\n" +
                            "WHERE uczniowie.login = '" + username + "'\n" +
                            "AND uczniowie.haslo = '" + password + "'");
            if (resultSet.next()) {

                uczniowie.setId_ucznia(resultSet.getInt(1));
                uczniowie.setImie(resultSet.getString(4));
                uczniowie.setNazwisko(resultSet.getString(5));
                uczniowie.setKlasy_id_klasy(resultSet.getInt(6));
                return 2;//zalogowano jako uczen
            }
            resultSet = statement.executeQuery(
                    "SELECT * \n" +
                            "FROM dyrektor\n" +
                            "WHERE dyrektor.login = '" + username + "'\n" +
                            "AND dyrektor.haslo = '" + password + "'");
            if (resultSet.next()) {

                dyrektor.setId_dyrektora(resultSet.getInt(1));
                dyrektor.setImie(resultSet.getString(4));
                dyrektor.setNazwisko(resultSet.getString(5));
                return 3;//zalogowano jako dyrektor
            }

            return 0;//nie ma takiej osoby
        }
    }
}