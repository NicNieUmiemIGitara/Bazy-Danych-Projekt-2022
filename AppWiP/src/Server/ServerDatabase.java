package Server;

import SpecifiedClasses.Headmaster.*;
import TableClasses.Adresy;
import TableClasses.Nauczyciele;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServerDatabase {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        String host = "localhost";
        int port = 1521;
        String database = "xe";
        String user = "c##databaseadmin";
        String password = "admin";

        // 1.
        String url = "jdbc:oracle:thin:" + user + "/" + password + "@" + host + ":" + port + ":" + database;
        Connection connection = DriverManager.getConnection(url);

        Wage test1=new Wage();
        //test1.updateWage(connection,1300,9);

        AddPeople test2 = new AddPeople();
        Adresy a=new Adresy();
        a.setMiejscowosc("1");
        a.setNr_domu(1);
        a.setUlica("1");
        test2.addAddress(connection,a);
        a=new Adresy();
        a.setUlica("2");
        a.setMiejscowosc("2");
        a.setNr_domu(2);
        test2.addAddress(connection,a);

        Nauczyciele n=new Nauczyciele();


        System.out.println(test2);


        Date date;
        date = new Date(2022 - 1900, 4 - 1, 1);
        System.out.println(date);
        //test2.chechl(connection,1,28,1);
        //test2.addMark(connection,5,"\'dasdaw\'",1,2,1);
        //test2.deleteMarks(connection,15);
        /*
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(
                "SELECT\n" +
                        "CASE lekcje.godz_lekcyjna \n" +
                        "WHEN 1 THEN '8-10'\n" +
                        "WHEN 2 THEN '10-12'\n" +
                        "WHEN 3 THEN '12-14'\n" +
                        "WHEN 4 THEN '14-16'\n" +
                        "WHEN 5 THEN '16-18'\n" +
                        "END AS \" \",\n" +
                        "NVL(TO_CHAR(przedmioty.nazwa), ' ') AS WT\n" +
                        "FROM przedmioty\n" +
                        "LEFT JOIN lekcje ON przedmioty.id_przedmiotu = lekcje.przedmioty_id_przedmiotu\n" +
                        "JOIN nauczyciele ON nauczyciele.id_nauczyciela = lekcje.nauczyciele_id_nauczyciela\n" +
                        "WHERE TO_CHAR(lekcje.data, 'D') = 1\n" +
                        "AND nauczyciele.id_nauczyciela = 2")) {
            while (resultSet.next())
                System.out.println(resultSet.getString( 1) + " " + resultSet.getString(2));
        }*/
    }
}
