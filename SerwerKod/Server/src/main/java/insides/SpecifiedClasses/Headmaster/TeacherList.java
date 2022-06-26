package insides.SpecifiedClasses.Headmaster;

import insides.TableClasses.Nauczyciele;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class TeacherList implements Serializable {
    Vector<Nauczyciele> list;

    public TeacherList() {
        list = new Vector<>();
    }

    @Override
    public String toString() {
        return "TeacherList{" +
                "list=" + list +
                '}';
    }

    public Vector<Nauczyciele> getList() {
        return list;
    }

    public int setList(Connection connection) throws SQLException {
        Nauczyciele n;
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(
                "SELECT *\n" +
                        "FROM nauczyciele\n" +
                        "ORDER BY id_nauczyciela")) {
            while (resultSet.next()) {
                n = new Nauczyciele();
                n.setId_nauczyciela(resultSet.getInt(1));
                n.setLogin(resultSet.getString(2));
                n.setHaslo(resultSet.getString(3));
                n.setImie(resultSet.getString(4));
                n.setNazwisko(resultSet.getString(5));
                n.setPensja(resultSet.getDouble(6));
                n.setAdresy_id_adresu(resultSet.getInt(7));
                list.add(n);
            }
            //System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            return 0;
        }
    }
}