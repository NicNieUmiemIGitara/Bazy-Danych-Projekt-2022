package com.example.demo;

import insides.SpecifiedClasses.TeacherTestsPerDay;
import insides.TableClasses.Klasy;
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
import java.time.LocalDate;
import java.util.Vector;

import static com.example.demo.Main.*;

public class n1_sprawdzianyCtrl {
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
    public TableView planLekcjiTable;
    public TableColumn pon;
    public TableColumn wt;
    public TableColumn sr;
    public TableColumn czw;
    public TableColumn pt;
    public DatePicker data;
    public Button dodajButton;
    public Button usunButton;
    public ChoiceBox godzinaBox;
    public ChoiceBox classBox;
    public TextField opisTextField;


    public void initialize() throws IOException, ClassNotFoundException {
        nameLabelNaucz.setText(nauczyciele.getImie());
        surnameLabelNaucz.setText(nauczyciele.getNazwisko());

        pon.setCellValueFactory(new PropertyValueFactory<UczenObecnoscSprawdzianyLekcje, String>("pon"));
        wt.setCellValueFactory(new PropertyValueFactory<UczenObecnoscSprawdzianyLekcje, String>("wt"));
        sr.setCellValueFactory(new PropertyValueFactory<UczenObecnoscSprawdzianyLekcje, String>("sr"));
        czw.setCellValueFactory(new PropertyValueFactory<UczenObecnoscSprawdzianyLekcje, String>("czw"));
        pt.setCellValueFactory(new PropertyValueFactory<UczenObecnoscSprawdzianyLekcje, String>("pt"));

        ObservableList<String> ok = FXCollections.observableArrayList();
        for (Klasy k :
                klasyVector) {
            ok.add(k.getRok() + " " + k.getNazwa());
        }
        classBox.setItems(ok);

        godzinaBox.getItems().add("8-10");
        godzinaBox.getItems().add("10-12");
        godzinaBox.getItems().add("12-14");
        godzinaBox.getItems().add("14-16");
        godzinaBox.getItems().add("16-18");


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

    public void dataListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        dataCheck();

    }


    public void dodajButtonListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        LocalDate localDate = data.getValue();
        if (localDate == null) {
            errorLabel.setText("Nie podano daty");
            return;
        }
        if (godzinaBox.getSelectionModel().getSelectedIndex() == -1) {
            errorLabel.setText("Nie podano godziny");
            return;
        }
        if (classBox.getSelectionModel().getSelectedIndex() == -1) {
            errorLabel.setText("Nie podano klasy");
            return;
        }
        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(132);
        clientIn = new ObjectInputStream(socketClient.getInputStream());
        clientOut.writeObject(localDate);
        clientOut.writeObject(godzinaBox.getSelectionModel().getSelectedIndex() + 1);
        clientOut.writeObject(opisTextField.getText());
        clientOut.writeObject(classBox.getSelectionModel().getSelectedIndex()+1);
        clientOut.writeObject(nauczyciele.getId_nauczyciela());
        dataCheck();

    }

    public void usunButtonListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        LocalDate localDate = data.getValue();
        if (localDate == null) {
            errorLabel.setText("Nie podano daty");
            return;
        }
        if (godzinaBox.getSelectionModel().getSelectedIndex() == -1) {
            errorLabel.setText("Nie podano godziny");
            return;
        }
        if (classBox.getSelectionModel().getSelectedIndex() == -1) {
            errorLabel.setText("Nie podano klasy");
            return;
        }
        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(133);
        clientIn = new ObjectInputStream(socketClient.getInputStream());
        clientOut.writeObject(localDate);
        clientOut.writeObject(godzinaBox.getSelectionModel().getSelectedIndex() + 1);
        clientOut.writeObject(classBox.getSelectionModel().getSelectedIndex()+1);
        clientOut.writeObject(nauczyciele.getId_nauczyciela());
        dataCheck();
    }

    public void dataCheck() throws IOException, ClassNotFoundException {
        LocalDate localDate = data.getValue();
        if (localDate == null) {
            errorLabel.setText("Nie wybrałeś daty");
            return;
        }
        LocalDate localDate1 = data.getValue().minusDays(localDate.getDayOfWeek().getValue() - 1);
        LocalDate localDate2 = data.getValue().plusDays(7 - localDate.getDayOfWeek().getValue());

        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(131);
        clientIn = new ObjectInputStream(socketClient.getInputStream());
        clientOut.writeObject(nauczyciele.getId_nauczyciela());
        clientOut.writeObject(localDate1);
        clientOut.writeObject(localDate2);
        Vector<TeacherTestsPerDay> days = (Vector<TeacherTestsPerDay>) clientIn.readObject();
        ObservableList<UczenObecnoscSprawdzianyLekcje> od = FXCollections.observableArrayList();
        for (int i = 0; i < 5; i++) {

            od.add(new UczenObecnoscSprawdzianyLekcje(
                    days.get(0).getSubjects().get(i),
                    days.get(1).getSubjects().get(i),
                    days.get(2).getSubjects().get(i),
                    days.get(3).getSubjects().get(i),
                    days.get(4).getSubjects().get(i)));

        }
        planLekcjiTable.refresh();
        planLekcjiTable.setItems(od);
        System.out.println(od);
    }
}
