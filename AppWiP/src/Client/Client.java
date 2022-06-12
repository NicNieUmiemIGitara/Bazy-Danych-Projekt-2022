package Client;

import TableClasses.Nauczyciele;
import TableClasses.Uczniowie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

//Przykład na podstawie dodania ucznia
public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Uczniowie uczen;
        Nauczyciele nauczyciel;
        String tempString;
        Scanner sc = new Scanner(System.in);
        int idCounter = 1, instructionId = 1, requestsSent = 2;
        Socket socketClient = new Socket("127.0.0.1", 42069);
        //ObjectOutputStream oos = new ObjectOutputStream(socketClient.getOutputStream());

        for (;;) {
            ObjectOutputStream clientOut = new ObjectOutputStream(socketClient.getOutputStream());
            ObjectInputStream clientIn=new ObjectInputStream(socketClient.getInputStream());
            if (1 == requestsSent - 1) {
                instructionId = 2;
                nauczyciel = new Nauczyciele();
                nauczyciel.setId_nauczyciela(1);
                nauczyciel.setImie("Dariusz");
                nauczyciel.setNazwisko("Bananas");
                nauczyciel.setHaslo("w dupe trzaslo lolololololo");
                nauczyciel.setLogin("loginmalpadżimejlkropkakom");
                nauczyciel.setPensja(-15);
                clientOut.writeObject(instructionId);
                clientOut.writeObject(nauczyciel);
                break;
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
            clientOut.writeObject(instructionId);
            clientOut.writeObject(uczen);
            String test=(String)clientIn.readObject();
            System.out.println(test);

        }
    }
}



