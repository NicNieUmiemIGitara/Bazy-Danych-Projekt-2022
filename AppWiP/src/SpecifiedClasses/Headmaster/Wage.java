package SpecifiedClasses.Headmaster;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Wage {
    public int updateWage(Connection connection,double wage,int teacherId) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "UPDATE nauczyciele \n" +
                            "SET pensja = "+wage+" --ZMIENNA nowa pensja\n" +
                            "WHERE id_nauczyciela = "+teacherId+" --ZMIENNA id_nauczyciela");

        }
        return 0;
    }
}
