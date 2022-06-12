package com.example.demo;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static com.example.demo.HelloApplication.*;
import static com.example.demo.HelloApplication.clientIn;
import static com.example.demo.HelloApplication.socketClient;

public class LoginContrl {

    public TextField username;
    public PasswordField password;
    public Button LoginButton;
    public Label wrongLogin;

    public void LoginButtonListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(0);
        clientOut.writeObject(username.getText().toString());
        clientOut.writeObject(password.getText().toString());
        clientIn = new ObjectInputStream(socketClient.getInputStream());
        int tmp= (int) clientIn.readObject();
        wrongLogin.setText(String.valueOf(tmp));
    }
}
