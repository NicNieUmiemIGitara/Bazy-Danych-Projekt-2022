package com.example.demo;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class u1_menuCtrl {
    public Button obecnoscButton;
    public Label nameLabelNaucz;
    public Label surnameLabelNaucz;
    public Button ocenyButton;
    public Button sprawdzianyButton;
    public Button lekcjeButton;
    public ImageView logoutIcon;
    public TableView planLekcjiTable;
    public TableColumn pon;
    public TableColumn wt;
    public TableColumn sr;
    public TableColumn czw;
    public TableColumn pt;
    public Label errorLabel;
    public Label schoolName;
    public Label classLabel;
    public DatePicker date;


    public void initialize() throws IOException {
        nameLabelNaucz.setText(Main.uczniowie.getImie());
        surnameLabelNaucz.setText(Main.uczniowie.getNazwisko());
        classLabel.setText(Main.klasa.getRok() + Main.klasa.getNazwa());

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
