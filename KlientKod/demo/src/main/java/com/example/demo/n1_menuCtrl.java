package com.example.demo;

import insides.TableClasses.Klasy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static com.example.demo.Main.klasyVector;
import static com.example.demo.Main.nauczyciele;

public class n1_menuCtrl {
    public Button planButton;
    public Button obecnoscButton;
    public Button ocenyButton;
    public Button sprawdzianyButton;
    public ImageView logoutIcon;
    public Label nameLabelNaucz;
    public Label surnameLabelNaucz;
    public ChoiceBox subjectBox;
    public Label errorLabel;
    public Label schoolName;
    public ChoiceBox classBox;


    public void initialize() {
        nameLabelNaucz.setText(nauczyciele.getImie());
        surnameLabelNaucz.setText(nauczyciele.getNazwisko());

        ObservableList<String> ok = FXCollections.observableArrayList();
        for (Klasy k :
                klasyVector) {
            ok.add(k.getRok() + " " + k.getNazwa());
        }
        classBox.setItems(ok);
    }

    public void obecnoscListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        Main m = new Main();
        m.changeScene("n1-obecnosc.fxml");
    }

    public void ocenyListener(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("n1-oceny.fxml");
    }

    public void sprawdzianyListener(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("n1-sprawdziany.fxml");
    }

    public void planListener(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("n1-lekcje.fxml");
    }

    public void logoutListener(MouseEvent mouseEvent) throws IOException {
        Main m = new Main();
        m.changeScene("logowanie.fxml");
    }

    public void dataListener(ActionEvent actionEvent) {
    }

    public void dodajButtonListener(ActionEvent actionEvent) {
    }

    public void usunButtonListener(ActionEvent actionEvent) {
    }
}
