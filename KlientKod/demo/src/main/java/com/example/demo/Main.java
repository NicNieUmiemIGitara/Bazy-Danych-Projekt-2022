package com.example.demo;

import insides.TableClasses.Dyrektor;
import insides.TableClasses.Klasy;
import insides.TableClasses.Nauczyciele;
import insides.TableClasses.Uczniowie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class Main extends Application {
    public static Nauczyciele nauczyciele;
    public static Uczniowie uczniowie;
    public static Dyrektor dyrektor;
    public static Klasy klasa;
    public static Vector<Klasy> klasyVector;
    public static Socket socketClient;
    public static ObjectOutputStream clientOut;
    public static ObjectInputStream clientIn;
    private static Stage stg;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        //socketClient = new Socket("127.0.0.1", 42069);
        //clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        //clientIn = new ObjectInputStream(socketClient.getInputStream());
        stg = stage;
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("logowanie.fxml"));
        //Scene scene = new Scene(fxmlLoader, 600, 400);
        stage.setTitle("School App");
        stage.setScene(new Scene(fxmlLoader, 750, 500));
        stg.setResizable(false);
        stage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
        //stg.sizeToScene();
    }
}