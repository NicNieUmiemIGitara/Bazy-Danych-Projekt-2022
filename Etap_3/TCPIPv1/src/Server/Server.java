package Server;

import TableClasses.Uczniowie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int tmp;
        Uczniowie uczen;

        try (ServerSocket server = new ServerSocket(42069);
             Socket socket = server.accept();
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            for (; ; ) {
                tmp = (int) ois.readObject();
                if (tmp == 1) {
                    //System.out.println("DUPA "+tmp);
                    uczen = (Uczniowie) ois.readObject();
                    System.out.println("ID wyslanego ucznia: " + uczen.getId_ucznia());
                    System.out.println("Imie ucznia wyslanego na serwer: " + uczen.getImie());
                    System.out.println("Nazwisko ucznia wyslanego na serwer: " + uczen.getNazwisko());
                    System.out.println("Login ucznia wyslanego na serwer: " + uczen.getLogin());
                    System.out.println("Haslo ucznia wyslanego na serwer: " + uczen.getHaslo());
                } else {
                    System.out.println("Id instrukcji zostalo zmienione z jeden na 2 wiec uczniowie wyslani na serwer nie zostali odczytani.");
                    ois.readObject();
                    break;
                }
            }
        }
    }
}
