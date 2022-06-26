package com.example.demo;

import insides.TableClasses.Dyrektor;
import insides.TableClasses.Klasy;
import insides.TableClasses.Nauczyciele;
import insides.TableClasses.Uczniowie;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import static com.example.demo.Main.*;

public class LoginCtrl {

    public TextField username;
    public TextField password;
    public Button LoginButton;
    public Label wrongLogin;
    public Label schoolName;

    public void LoginButtonListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        checkLogin();

    }

    public void checkLogin() throws IOException, ClassNotFoundException {
        wrongLogin.setText("");
        Main m = new Main();
        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(0);
        clientOut.writeObject(username.getText().toString());
        clientOut.writeObject(password.getText().toString());
        clientIn = new ObjectInputStream(socketClient.getInputStream());
        int tmp = (int) clientIn.readObject();
        if (tmp == 1) {
            nauczyciele = (Nauczyciele) clientIn.readObject();
            klasyVector = (Vector<Klasy>) clientIn.readObject();
            m.changeScene("n1-menu.fxml");
            //loggedInUser = (Nauczyciele) clientIn.readObject();
        } else if (tmp == 2) {
            uczniowie = (Uczniowie) clientIn.readObject();
            clientOut.writeObject(uczniowie.getKlasy_id_klasy());
            klasa = (Klasy) clientIn.readObject();
            m.changeScene("u1-menu.fxml");
            //loggedInUser = clientIn.readObject();
        } else if (tmp == 3) {
            dyrektor = (Dyrektor) clientIn.readObject();
            m.changeScene("d1-menu.fxml");
            //loggedInUser = clientIn.readObject();
        } else {
            wrongLogin.setText("Incorrect username or password");
        }
        //wrongLogin.setText(String.valueOf(tmp));

    }
}
