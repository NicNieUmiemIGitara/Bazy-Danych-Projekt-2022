package insides.SpecifiedClasses.Headmaster;

import insides.TableClasses.Klasy;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ClassList implements Serializable {
    Vector<Klasy> list;

    public ClassList() {
        list = new Vector<>();
    }

    @Override
    public String toString() {
        return "ClassList{" +
                "list=" + list +
                '}';
    }

    public Vector<Klasy> getList() {
        return list;
    }

    public int setList(Connection connection) throws SQLException {
        Klasy c;
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(
                "SELECT *\n" +
                        "FROM klasy\n" +
                        "ORDER BY id_klasy")) {
            while (resultSet.next()) {
                c = new Klasy();
                c.setId_klasy(resultSet.getInt(1));
                c.setRok(Integer.parseInt(resultSet.getString(2)));
                c.setNazwa(resultSet.getString(3));
                list.add(c);
            }
            //System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            return 0;
        }
    }
}
