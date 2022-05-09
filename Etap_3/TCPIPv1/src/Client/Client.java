package Client;

import TableClasses.Uczniowie;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

//Przykład na podstawie dodania ucznia
public class Client {
    public static void main(String[] args) throws IOException {
        Uczniowie uczen;
        String tempString;
        Scanner sc = new Scanner(System.in);
        int idCounter = 1, instructionId = 1, requestsSent=2;

        try (Socket socket = new Socket("localhost", 42069)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
                for (int i = 0; i < requestsSent; i++) {
                    if (i == requestsSent-1) {
                        instructionId = 2;
                    }
                    uczen = new Uczniowie();
                    System.out.println("Podaj imie ucznia");
                    tempString = sc.nextLine();
                    uczen.setImie(tempString);
                    //System.out.println(uczen.getImie());
                    System.out.println("Podaj nazwisko ucznia");
                    tempString = sc.nextLine();
                    uczen.setNazwisko(tempString);
                    System.out.println("Wpisz login ucznia");
                    tempString = sc.nextLine();
                    uczen.setLogin(tempString);
                    System.out.println("Wpisz haslo ucznia");
                    tempString = sc.nextLine();
                    uczen.setHaslo(tempString);
                    uczen.setId_ucznia(idCounter);
                    idCounter++;
                    oos.writeObject(instructionId);
                    oos.writeObject(uczen);
                }
            }
        }
    }
}

