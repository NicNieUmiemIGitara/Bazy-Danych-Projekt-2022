package com.example.demo;

import insides.Server.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HelloApplication extends Application {
    public static Socket socketClient;
    public static ObjectOutputStream clientOut;
    public static ObjectInputStream clientIn;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        //socketClient = new Socket("127.0.0.1", 42069);
        //clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        //clientIn = new ObjectInputStream(socketClient.getInputStream());

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}