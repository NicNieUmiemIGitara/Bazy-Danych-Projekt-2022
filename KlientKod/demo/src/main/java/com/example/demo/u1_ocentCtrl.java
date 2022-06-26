package com.example.demo;

import insides.SpecifiedClasses.Marks;
import insides.SpecifiedClasses.Student.StudentMarks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static com.example.demo.Main.*;

public class u1_ocentCtrl {

    public Button obecnoscButton;
    public Label nameLabelNaucz;
    public Label surnameLabelNaucz;
    public Button ocenyButton;
    public Button sprawdzianyButton;
    public Button lekcjeButton;
    public ImageView logoutIcon;

    public Label errorLabel;
    public Label schoolName;
    public Label classLabel;

    public TableView<UczenOceny> ocenyUczenTable;
    public TableColumn<UczenOceny, String> przedmiotColumn;
    public TableColumn<UczenOceny, String> ocenyColumn;

    public void initialize() throws IOException, ClassNotFoundException {
        nameLabelNaucz.setText(Main.uczniowie.getImie());
        surnameLabelNaucz.setText(Main.uczniowie.getNazwisko());
        classLabel.setText(Main.klasa.getRok() + Main.klasa.getNazwa());
        przedmiotColumn.setCellValueFactory(new PropertyValueFactory<UczenOceny, String>("przedmiot"));
        ocenyColumn.setCellValueFactory(new PropertyValueFactory<UczenOceny, String>("oceny"));

        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientIn = new ObjectInputStream(socketClient.getInputStream());
        clientOut.writeObject(220);
        clientOut.writeObject(uczniowie.getId_ucznia());
        StudentMarks studentMarks = (StudentMarks) clientIn.readObject();
        ObservableList<UczenOceny> om = FXCollections.observableArrayList();
        for (Marks m :
                studentMarks.getMarks()) {
            om.add(new UczenOceny(m.getInfo(), String.valueOf(m.getMarks())));
        }
        ocenyUczenTable.setItems(om);
        System.out.println(om);
    }

    public void obecnoscListener(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("u1-obecnosc.fxml");
    }

    public void ocenyListener(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("u1-oceny.fxml");
    }

    public void sprawdzianyListener(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("u1-sprawdziany.fxml");
    }

    public void planListener(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("u1-lekcje.fxml");
    }

    public void logoutListener(MouseEvent mouseEvent) throws IOException {
        Main m = new Main();
        m.changeScene("logowanie.fxml");
    }


}
