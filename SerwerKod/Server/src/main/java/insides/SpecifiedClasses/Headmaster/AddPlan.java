package insides.SpecifiedClasses.Headmaster;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class AddPlan {
    public int addPlan(Connection connection, int godz, LocalDate localDate,
                       int przedmioty_id_przedmiotu, int nauczyciele_id_nauczyciela,
                       int klasy_id_klasy, int sala_id_sala
    ) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "INSERT INTO lekcje(id_lekcji, godz_lekcyjna, data, przedmioty_id_przedmiotu, nauczyciele_id_nauczyciela, sprawdziany_id_sprawdzianu, klasy_id_klasy, sala_id_sala) \n" +
                            "VALUES ((SELECT (MAX(id_lekcji+1)) FROM lekcje), " +
                            "" + godz + ", " +
                            "TO_DATE('" + localDate + "','YYYY-MM-DD'), " +
                            "" + przedmioty_id_przedmiotu + ", " +
                            "" + nauczyciele_id_nauczyciela + ", " +
                            "NULL, " +
                            "" + klasy_id_klasy + ", " +
                            "" + sala_id_sala + ")  --ZMIENNE godzina data przedmiot nauczyciel klasa");

        }
        return 0;
    }
}
