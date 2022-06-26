package insides.SpecifiedClasses.Headmaster;

import insides.TableClasses.Nauczyciele;
import insides.TableClasses.Uczniowie;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeletePeople {
    public int deleteTeacher(Connection connection, Nauczyciele n) {
        try (Statement statement = connection.createStatement()) {
            try {
                statement.executeUpdate(
                        "DELETE FROM wiadomosci WHERE wiadmosci_nauczyciele_fk = " + n.getId_nauczyciela() + " --ZMIENNA ID_NAUCZYCIELA\n");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.executeUpdate(
                        "UPDATE oceny SET nauczyciele_id_nauczyciela = NULL " +
                                "WHERE nauczyciele_id_nauczyciela = " + n.getId_nauczyciela() + " --ZMIENNA ID_NAUCZYCIELA\n");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.executeUpdate(
                        "UPDATE lekcje SET nauczyciele_id_nauczyciela = NULL " +
                                "WHERE nauczyciele_id_nauczyciela = " + n.getId_nauczyciela() + " --ZMIENNA ID_NAUCZYCIELA\n");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.executeUpdate("DELETE FROM nauczyciele WHERE id_nauczyciela = " + n.getId_nauczyciela() + " --ZMIENNA ID_NAUCZYCIELA");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteStudent(Connection connection, Uczniowie u) {
        try (Statement statement = connection.createStatement()) {
            try {
                statement.executeUpdate(
                        "DELETE FROM wiadomosci WHERE uczniowie_id_ucznia = " + u.getId_ucznia() + " --ZMIENNA ID_UCZNIA");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.executeUpdate(
                        "DELETE FROM oceny WHERE uczniowie_id_ucznia = " + u.getId_ucznia() + " --ZMIENNA ID_UCZNIA");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.executeUpdate(
                        "DELETE FROM frekwencja WHERE uczniowie_id_ucznia = " + u.getId_ucznia() + " --ZMIENNA ID_UCZNIA");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.executeUpdate("DELETE FROM uczniowie WHERE id_ucznia = " + u.getId_ucznia() + " --ZMIENNA ID_UCZNIA");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
