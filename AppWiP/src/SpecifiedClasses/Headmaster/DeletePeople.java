package SpecifiedClasses.Headmaster;

import TableClasses.Nauczyciele;
import TableClasses.Uczniowie;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeletePeople {
    public int deleteTeacher(Connection connection, Nauczyciele n) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "DELETE FROM wiadomosci WHERE wiadmosci_nauczyciele_fk = " + n.getId_nauczyciela() + " --ZMIENNA ID_NAUCZYCIELA\n");
            statement.executeUpdate(
                    "UPDATE oceny SET nauczyciele_id_nauczyciela = NULL " +
                            "WHERE nauczyciele_id_nauczyciela = " + n.getId_nauczyciela() + " --ZMIENNA ID_NAUCZYCIELA\n");
            statement.executeUpdate(
                    "UPDATE lekcje SET nauczyciele_id_nauczyciela = NULL " +
                            "WHERE nauczyciele_id_nauczyciela = " + n.getId_nauczyciela() + " --ZMIENNA ID_NAUCZYCIELA\n");
            statement.executeUpdate("DELETE FROM nauczyciele WHERE id_nauczyciela = " + n.getId_nauczyciela() + " --ZMIENNA ID_NAUCZYCIELA");
        }
        return 0;
    }
    public int deleteStudent(Connection connection, Uczniowie u) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "DELETE FROM wiadomosci WHERE uczniowie_id_ucznia = "+u.getId_ucznia()+" --ZMIENNA ID_UCZNIA");
            statement.executeUpdate(
                    "DELETE FROM oceny WHERE uczniowie_id_ucznia = "+u.getId_ucznia()+" --ZMIENNA ID_UCZNIA");
            statement.executeUpdate(
                    "DELETE FROM frekwencja WHERE uczniowie_id_ucznia = "+u.getId_ucznia()+" --ZMIENNA ID_UCZNIA");
            statement.executeUpdate("DELETE FROM uczniowie WHERE id_ucznia = "+u.getId_ucznia()+" --ZMIENNA ID_UCZNIA");
        }
        return 0;
    }
}
