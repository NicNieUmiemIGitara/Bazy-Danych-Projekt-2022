package com.example.demo;

import insides.SpecifiedClasses.ClassDayOfTheWeek;
import insides.SpecifiedClasses.TeacherDayOfTheWeek;
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

public class n1_lekcjeCtrl {
    public Label nameLabelNaucz;
    public Label surnameLabelNaucz;
    public Button obecnoscButton;
    public Button ocenyButton;
    public Button sprawdzianyButton;
    public Button planButton;
    public ImageView logoutIcon;
    public ChoiceBox subjectBox;
    public TableView planLekcjiTable;
    public TableColumn pon;
    public TableColumn wt;
    public TableColumn sr;
    public TableColumn czw;
    public TableColumn pt;
    public Label errorLabel;
    public Label schoolName;
    public ChoiceBox classBox;
    public DatePicker data;
    public Button planKlasy;
    public Button planNauczyciela;

    public void initialize() {
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

    public void planKlasyListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        errorLabel.setText("");
        LocalDate localDate = data.getValue();
        if (localDate == null) {
            errorLabel.setText("Nie wybrałeś daty");
            return;
        }
        LocalDate localDate1 = data.getValue().minusDays(localDate.getDayOfWeek().getValue() - 1);
        LocalDate localDate2 = data.getValue().plusDays(7 - localDate.getDayOfWeek().getValue());


        if (classBox.getSelectionModel().getSelectedIndex() < 0) {
            errorLabel.setText("Nie wybrałeś klasy");
            return;
        }
        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(141);
        clientIn = new ObjectInputStream(socketClient.getInputStream());
        clientOut.writeObject(klasyVector.get(
                classBox.getSelectionModel().getSelectedIndex()).getId_klasy());
        clientOut.writeObject(localDate1);
        clientOut.writeObject(localDate2);
        Vector<ClassDayOfTheWeek> days = (Vector<ClassDayOfTheWeek>) clientIn.readObject();
        ObservableList<UczenObecnoscSprawdzianyLekcje> od = FXCollections.observableArrayList();
        System.out.println(od);
        for (int i = 0; i < 5; i++) {

            od.add(new UczenObecnoscSprawdzianyLekcje(
                    days.get(0).getSubjects().get(i),
                    days.get(1).getSubjects().get(i),
                    days.get(2).getSubjects().get(i),
                    days.get(3).getSubjects().get(i),
                    days.get(4).getSubjects().get(i)));

        }
        System.out.println(od);
        planLekcjiTable.refresh();
        planLekcjiTable.setItems(od);
        System.out.println(od);
    }

    public void planNauczycielaListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        errorLabel.setText("");
        LocalDate localDate = data.getValue();
        if (localDate == null) {
            errorLabel.setText("Nie wybrałeś daty");
            return;
        }
        LocalDate localDate1 = data.getValue().minusDays(localDate.getDayOfWeek().getValue() - 1);
        LocalDate localDate2 = data.getValue().plusDays(7 - localDate.getDayOfWeek().getValue());

        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(142);
        clientIn = new ObjectInputStream(socketClient.getInputStream());

        clientOut.writeObject(nauczyciele.getId_nauczyciela());
        clientOut.writeObject(localDate1);
        clientOut.writeObject(localDate2);
        Vector<TeacherDayOfTheWeek> days = (Vector<TeacherDayOfTheWeek>) clientIn.readObject();
        ObservableList<UczenObecnoscSprawdzianyLekcje> od = FXCollections.observableArrayList();
        System.out.println(od);
        for (int i = 0; i < 5; i++) {

            od.add(new UczenObecnoscSprawdzianyLekcje(
                    days.get(0).getSubjects().get(i),
                    days.get(1).getSubjects().get(i),
                    days.get(2).getSubjects().get(i),
                    days.get(3).getSubjects().get(i),
                    days.get(4).getSubjects().get(i)));

        }
        System.out.println(od);
        planLekcjiTable.refresh();
        planLekcjiTable.setItems(od);
        System.out.println(od);
    }
}
