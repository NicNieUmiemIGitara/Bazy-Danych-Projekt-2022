package insides.SpecifiedClasses.Headmaster;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

public class Lessons {
    public int addLessons(Connection connection, int godz_lekcyjna, Date data, int subjectId, int teacherId, Integer testId, int classId, int classroomId) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "INSERT INTO lekcje(id_lekcji, godz_lekcyjna, data, przedmioty_id_przedmiotu, nauczyciele_id_nauczyciela, sprawdziany_id_sprawdzianu, klasy_id_klasy, sala_id_sala)\n" +
                            "VALUES ((SELECT (MAX(id_lekcji+1)) FROM lekcje), "+godz_lekcyjna+", TO_DATE('"+data.toString() +"','YYYY-MM-DD'), "+subjectId+ ", "+teacherId+ ", "+testId+ ", "+classId+ ", " +classroomId+")");

        }
        return 0;
    }
}
