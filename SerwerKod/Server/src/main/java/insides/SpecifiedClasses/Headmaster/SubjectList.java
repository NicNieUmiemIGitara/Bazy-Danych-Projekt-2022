package insides.SpecifiedClasses.Headmaster;

import insides.TableClasses.Przedmioty;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class SubjectList implements Serializable {
    Vector<Przedmioty> list;

    public SubjectList() {
        list = new Vector<>();
    }

    @Override
    public String toString() {
        return "SubjectList{" +
                "list=" + list +
                '}';
    }

    public Vector<Przedmioty> getList() {
        return list;
    }

    public int setList(Connection connection) throws SQLException {
        Przedmioty p;
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(
                "SELECT *\n" +
                        "FROM przedmioty\n" +
                        "ORDER BY id_przedmiotu")) {
            while (resultSet.next()) {
                p = new Przedmioty();
                p.setId_przedmiotu(resultSet.getInt(1));
                p.setNazwa(resultSet.getString(2));

                list.add(p);
            }
            //System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            return 0;
        }
    }
}
