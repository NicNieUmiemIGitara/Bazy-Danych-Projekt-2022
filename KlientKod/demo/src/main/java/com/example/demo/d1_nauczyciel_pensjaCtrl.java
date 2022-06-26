package com.example.demo;

import insides.TableClasses.Nauczyciele;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import static com.example.demo.Main.*;

public class d1_nauczyciel_pensjaCtrl {
    public Label errorLabel;
    public Label schoolName;
    public ImageView logoutIcon;
    public Button planButton;
    public Button pensjaButton;
    public MenuItem usunNauczyciel;
    public MenuItem dodajNauczyciel;
    public MenuItem usunUczen;
    public MenuItem dodajUczen;
    public Label surnameLabelNaucz;
    public Label nameLabelNaucz;
    public MenuButton studentMenuButton;
    public MenuButton teacherMenuButton;
    public TableView<DyrektorNayczyciel> pensjaTableView;
    public TableColumn<DyrektorNayczyciel, String> pensjaIdColumn;
    public TableColumn<DyrektorNayczyciel, String> pensjiaImieNazwiskoColumn;
    public TableColumn pensjaPensjaColumn;
    public TextField editPensjaTextField;
    public Button editButton;

    public void initialize() throws IOException, ClassNotFoundException {
        nameLabelNaucz.setText(Main.dyrektor.getImie());
        surnameLabelNaucz.setText(Main.dyrektor.getNazwisko());
        pensjaIdColumn.setCellValueFactory(new PropertyValueFactory<DyrektorNayczyciel, String>("id_nauczyciela"));
        pensjiaImieNazwiskoColumn.setCellValueFactory(new PropertyValueFactory<DyrektorNayczyciel, String>("imieNazwisko"));
        pensjaPensjaColumn.setCellValueFactory(new PropertyValueFactory<DyrektorNayczyciel, Double>("pensja"));


        System.out.println(pensjaPensjaColumn.editableProperty());

        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(350);
        clientIn = new ObjectInputStream(socketClient.getInputStream());
        Vector<Nauczyciele> tL = (Vector<Nauczyciele>) clientIn.readObject();

        ObservableList<DyrektorNayczyciel> ot = FXCollections.observableArrayList();
        for (Nauczyciele n :
                tL) {
            ot.add(new DyrektorNayczyciel(n.getId_nauczyciela(),
                    n.getLogin(),
                    n.getHaslo(),
                    n.getImie() + " " + n.getNazwisko(),
                    n.getPensja(),
                    n.getAdresy_id_adresu()));
        }
        pensjaTableView.setItems(ot);

    }

    public void dodajUczenListener(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        studentMenuButton.hide();
        m.changeScene("d1-uczen-dodaj.fxml");
    }

    public void usunUczenListener(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        studentMenuButton.hide();
        m.changeScene("d1-uczen-usun.fxml");
    }

    public void dodajNauczycielListener(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        teacherMenuButton.hide();
        m.changeScene("d1-nauczyciel-dodaj.fxml");
    }

    public void usunNauczycielListener(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        teacherMenuButton.hide();
        m.changeScene("d1-nauczyciel-usun.fxml");
    }

    public void pensjaListener(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("d1-nauczyciel-pensja.fxml");
    }

    public void planListener(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("d1-plan.fxml");
    }

    public void logoutListener(MouseEvent mouseEvent) throws IOException {
        Main m = new Main();
        m.changeScene("logowanie.fxml");
    }


    public void pensjaEditCommit(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void editButtonListener(ActionEvent actionEvent) throws IOException {
        DyrektorNayczyciel dyrektorNayczyciel = pensjaTableView.getSelectionModel().getSelectedItem();
        dyrektorNayczyciel.setPensja(Double.valueOf(editPensjaTextField.getText().toString()));

        pensjaTableView.refresh();

        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(351);
        clientIn = new ObjectInputStream(socketClient.getInputStream());
        clientOut.writeObject(dyrektorNayczyciel.getId_nauczyciela());
        clientOut.writeObject(dyrektorNayczyciel.getPensja());
        editPensjaTextField.clear();
    }
}
