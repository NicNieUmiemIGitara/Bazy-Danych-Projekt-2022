package insides.SpecifiedClasses.Headmaster;

import insides.TableClasses.Sala;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class RoomList {
    Vector<Sala> list;

    public RoomList() {
        list = new Vector<>();
    }

    @Override
    public String toString() {
        return "RoomList{" +
                "list=" + list +
                '}';
    }

    public Vector<Sala> getList() {
        return list;
    }

    public int setList(Connection connection) throws SQLException {
        Sala s;
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(
                "SELECT *\n" +
                        "FROM sala\n" +
                        "ORDER BY id_sala")) {
            while (resultSet.next()) {
                s = new Sala();
                s.setId_sala(resultSet.getInt(1));
                s.setNr_sali(resultSet.getInt(2));
                list.add(s);
            }
            //System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            return 0;
        }
    }
}