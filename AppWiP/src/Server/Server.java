package Server;

import TableClasses.Nauczyciele;
import TableClasses.Uczniowie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int instructionID;
        Uczniowie uczen;
        Nauczyciele nauczyciel;
        ServerSocket server = new ServerSocket(42069);
        //Socket socket = server.accept();
        //ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        for (; ; ) {
            Socket connectionSocket=server.accept();
            ObjectInputStream serverIn = new ObjectInputStream(connectionSocket.getInputStream());
            ObjectOutputStream serverOut = new ObjectOutputStream(connectionSocket.getOutputStream());
            instructionID = (int) serverIn.readObject();
            if (instructionID == 1) {
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
            }
        }

    }
}
