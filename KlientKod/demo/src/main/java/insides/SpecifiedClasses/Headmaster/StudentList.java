package insides.SpecifiedClasses.Headmaster;


import insides.TableClasses.Uczniowie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class StudentList {
    Vector<Uczniowie> list;

    public StudentList() {
        list = new Vector<>();
    }

    @Override
    public String toString() {
        return "TeacherList{" +
                "list=" + list +
                '}';
    }

    public Vector<Uczniowie> getList() {
        return list;
    }

    public int setList(Connection connection) throws SQLException {
        Uczniowie n;
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(
                "SELECT *\n" +
                        "FROM uczniowie\n" +
                        "ORDER BY id_ucznia")) {
            while (resultSet.next()) {
                n = new Uczniowie();
                n.setId_ucznia(resultSet.getInt(1));
                n.setLogin(resultSet.getString(2));
                n.setHaslo(resultSet.getString(3));
                n.setImie(resultSet.getString(4));
                n.setNazwisko(resultSet.getString(5));
                n.setKlasy_id_klasy(resultSet.getInt(6));
                n.setAdresy_id_adresu(resultSet.getInt(7));
                list.add(n);
            }
            //System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            return 0;
        }
    }
}
