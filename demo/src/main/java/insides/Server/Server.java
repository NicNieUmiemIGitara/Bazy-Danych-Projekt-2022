package insides.Server;

import insides.SpecifiedClasses.LoginProcess;
import insides.TableClasses.Dyrektor;
import insides.TableClasses.Nauczyciele;
import insides.TableClasses.Uczniowie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        int userType;// 1 - nauczyciel :::::::: 2 - uczen :::::::: 3 - dyrektor
        int instructionID;
        Uczniowie uczen=new Uczniowie();
        Nauczyciele nauczyciel=new Nauczyciele();
        Dyrektor dyrektor=new Dyrektor();
        ServerSocket server = new ServerSocket(42069);
        //Socket socket = server.accept();
        //ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        String host = "localhost";
        int port = 1521;
        String database = "xe";
        String user = "c##databaseadmin";
        String password = "admin";

        String url = "jdbc:oracle:thin:" + user + "/" + password + "@" + host + ":" + port + ":" + database;
        Connection connection = DriverManager.getConnection(url);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Socket connectionSocket = server.accept();
        //ObjectInputStream serverIn = new ObjectInputStream(connectionSocket.getInputStream());
        //ObjectOutputStream serverOut = new ObjectOutputStream(connectionSocket.getOutputStream());
        for (; ; ) {
            Socket connectionSocket = server.accept();
            ObjectInputStream serverIn = new ObjectInputStream(connectionSocket.getInputStream());
            ObjectOutputStream serverOut = new ObjectOutputStream(connectionSocket.getOutputStream());
            instructionID = (int) serverIn.readObject();
            if (instructionID == 0) {
                LoginProcess l = new LoginProcess();
                serverOut.writeObject(l.login(connection, (String) serverIn.readObject(), (String) serverIn.readObject(), uczen, nauczyciel, dyrektor));
                //l.login(connection, (String) serverIn.readObject(), (String) serverIn.readObject(), uczen, nauczyciel, dyrektor);
            }
            /*if (instructionID == 1) {
                //System.out.println("DUPA "+instructionID);
                uczen = (Uczniowie) serverIn.readObject();
                System.out.println("ID wyslanego ucznia: " + uczen.getId_ucznia());
                System.out.println("Imie ucznia wyslanego na serwer: " + uczen.getImie());
                System.out.println("Nazwisko ucznia wyslanego na serwer: " + uczen.getNazwisko());
                System.out.println("Login ucznia wyslanego na serwer: " + uczen.getLogin());
                System.out.println("Haslo ucznia wyslanego na serwer: " + uczen.getHaslo());
                serverOut.writeObject("\n\n\n\n\n\nNapis");
            } else if (instructionID == 2) {
                nauczyciel = (Nauczyciele) serverIn.readObject();
                System.out.println("\n" + nauczyciel);
                break;
            }*/
        }

    }
}
