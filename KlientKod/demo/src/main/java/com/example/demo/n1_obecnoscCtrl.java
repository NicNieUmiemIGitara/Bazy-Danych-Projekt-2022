package com.example.demo;

import insides.SpecifiedClasses.Teacher.TeacherFrequency;
import insides.TableClasses.Klasy;
import insides.TableClasses.Uczniowie;
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

public class n1_obecnoscCtrl {
    public Label surnameLabelNaucz;
    public Label nameLabelNaucz;
    public Button obecnoscButton;
    public Button ocenyButton;
    public Button sprawdzianyButton;
    public Button planButton;
    public ImageView logoutIcon;
    public ChoiceBox subjectBox;
    public Label errorLabel;
    public Label schoolName;
    public TableView<NauczycielObecnosc> obecnoscTable;
    public TableColumn<NauczycielObecnosc, String> idColumn;
    public TableColumn<NauczycielObecnosc, String> nameColumn;
    public TableColumn<NauczycielObecnosc, String> secondNameColumn;
    public TableColumn<NauczycielObecnosc, String> obecnoscColumn;
    public Button dodajObecnoscButton;
    public ChoiceBox classBox;
    public TableColumn uczenObecnoscColumn;
    public Button potwierdzButton;
    public Button odnowButton;
    public ChoiceBox godzinaBox;
    public DatePicker data;
    Vector<Uczniowie> uList;
    Vector<Integer> hList;

    public void initialize() throws IOException, ClassNotFoundException {
        nameLabelNaucz.setText(nauczyciele.getImie());
        surnameLabelNaucz.setText(nauczyciele.getNazwisko());
        ObservableList<String> ok = FXCollections.observableArrayList();
        for (Klasy k :
                klasyVector) {
            ok.add(k.getRok() + " " + k.getNazwa());
        }
        classBox.setItems(ok);


        idColumn.setCellValueFactory(new PropertyValueFactory<NauczycielObecnosc, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<NauczycielObecnosc, String>("imie"));
        secondNameColumn.setCellValueFactory(new PropertyValueFactory<NauczycielObecnosc, String>("nazwisko"));
        obecnoscColumn.setCellValueFactory(new PropertyValueFactory<NauczycielObecnosc, String>("obecnosc"));


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

    public void obecnoscButtonListener(ActionEvent actionEvent) {
        errorLabel.setText("");
        if (godzinaBox.getSelectionModel().getSelectedIndex() == -1) {
            errorLabel.setText("Nie wybrałeś godziny");
            return;
        }
        if (classBox.getSelectionModel().getSelectedIndex() == -1) {
            errorLabel.setText("Nie wybrałeś klasy");
            return;
        }
        int index = obecnoscTable.getSelectionModel().getSelectedIndex();
        ObservableList<NauczycielObecnosc> tmp = obecnoscTable.getItems();
        tmp.get(obecnoscTable.getSelectionModel().getSelectedIndex()).setObecnosc(!tmp.get(obecnoscTable.getSelectionModel().getSelectedIndex()).isObecnosc());
        //obecnoscTable.getItems().clear();
        obecnoscTable.refresh();
        obecnoscTable.setItems(tmp);
    }

    public void potwierdzButtonListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        errorLabel.setText("");
        if (godzinaBox.getSelectionModel().getSelectedIndex() == -1) {
            errorLabel.setText("Nie wybrałeś godziny");
            return;
        }
        for (NauczycielObecnosc n :
                obecnoscTable.getItems()) {
            n.getId();
            hList.get(godzinaBox.getSelectionModel().getSelectedIndex());
            int present;
            if (n.isObecnosc()) {
                present = 1;
            } else {
                present = 0;
            }
            socketClient = new Socket("127.0.0.1", 42069);
            clientOut = new ObjectOutputStream(socketClient.getOutputStream());
            clientOut.writeObject(112);
            clientIn = new ObjectInputStream(socketClient.getInputStream());
            clientOut.writeObject(present);
            clientOut.writeObject(n.getId());
            clientOut.writeObject(hList.get(godzinaBox.getSelectionModel().getSelectedIndex()));

        }
        odnowCheck();

    }

    public void odnowButtonListnener(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        odnowCheck();

    }

    public void odnowCheck() throws IOException, ClassNotFoundException {
        errorLabel.setText("");
        if (data.getValue() == null) {
            errorLabel.setText("Nie wybrałeś daty");
            return;
        }
        if (classBox.getSelectionModel().getSelectedIndex() == -1) {
            errorLabel.setText("Nie wybrałeś klasy");
            return;
        }
        godzinaBox.getItems().clear();
        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(110);
        clientIn = new ObjectInputStream(socketClient.getInputStream());
        clientOut.writeObject(classBox.getSelectionModel().getSelectedIndex() + 1);
        TeacherFrequency t = (TeacherFrequency) clientIn.readObject();


        uList = t.retList();
        ObservableList<NauczycielObecnosc> ou = FXCollections.observableArrayList();
        for (Uczniowie u :
                uList) {
            ou.add(new NauczycielObecnosc(u.getId_ucznia(), u.getImie(), u.getNazwisko(), false));
        }

        obecnoscTable.setItems(ou);
        obecnoscTable.getSelectionModel().getSelectedItem();
        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(111);
        clientIn = new ObjectInputStream(socketClient.getInputStream());
        clientOut.writeObject(classBox.getSelectionModel().getSelectedIndex() + 1);
        clientOut.writeObject(nauczyciele.getId_nauczyciela());
        clientOut.writeObject(data.getValue());
        hList = (Vector<Integer>) clientIn.readObject();

        ObservableList<String> ol = FXCollections.observableArrayList();
        if (hList.get(0) > 0) {
            ol.add("8-10");
        }
        if (hList.get(1) > 0) {
            ol.add("10-12");
        }
        if (hList.get(2) > 0) {
            ol.add("12-14");
        }
        if (hList.get(3) > 0) {
            ol.add("14-16");
        }
        if (hList.get(4) > 0) {
            ol.add("16-18");
        }

        for (int i = 4; i >= 0; i--) {
            if (hList.get(i) < 0) {
                hList.remove(i);
            }
        }
        godzinaBox.setItems(ol);

        System.out.println(hList);

    }
}
