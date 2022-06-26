package com.example.demo;

import insides.SpecifiedClasses.Marks;
import insides.SpecifiedClasses.Teacher.TeacherMarks;
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

public class n1_ocenyCtrl {
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

    public ChoiceBox classBox;
    public TableColumn<NauczycielOceny, String> uczenOcenyColumn;
    public TableColumn<NauczycielOceny, String> ocenaColumn;
    public TableColumn<NauczycielOceny, String> opisColumn;
    public TableView<NauczycielOceny> ocenyTable;
    public TextField opisTextField;
    public Button dodajButton;
    public Button usunButton;
    public ChoiceBox ocenaChoiceBox;
    public ChoiceBox uczenChoiceBox;
    public Button odnowButton;
    Vector<Uczniowie> uL;

    public void initialize() throws IOException, ClassNotFoundException {
        nameLabelNaucz.setText(nauczyciele.getImie());
        surnameLabelNaucz.setText(nauczyciele.getNazwisko());
        ObservableList<String> ok = FXCollections.observableArrayList();
        for (Klasy k :
                klasyVector) {
            ok.add(k.getRok() + " " + k.getNazwa());
        }
        classBox.setItems(ok);
        subjectBox.getItems().add("Matematyka");
        subjectBox.getItems().add("Jezyk polski");
        subjectBox.getItems().add("Jezyk angielski");
        subjectBox.getItems().add("Przyroda");
        subjectBox.getItems().add("Fizyka");
        subjectBox.getItems().add("Chemia");
        subjectBox.getItems().add("Wychowanie fizyczne");
        subjectBox.getItems().add("Religia");

        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(120);
        clientIn = new ObjectInputStream(socketClient.getInputStream());
        clientOut.writeObject(nauczyciele.getId_nauczyciela());
        clientOut.writeObject(1);
        TeacherMarks t = (TeacherMarks) clientIn.readObject();
        //System.out.println(t);

        Vector<Marks> mList = t.getMarks();
        uczenOcenyColumn.setCellValueFactory(new PropertyValueFactory<NauczycielOceny, String>("uczen"));
        ocenaColumn.setCellValueFactory(new PropertyValueFactory<NauczycielOceny, String>("ocena"));
        opisColumn.setCellValueFactory(new PropertyValueFactory<NauczycielOceny, String>("opis"));
        ObservableList om = FXCollections.observableArrayList();
        for (Marks u :
                mList) {
            om.add(new NauczycielOceny(u.getStudentId(), u.getMarkId(), u.getInfo(), u.getMark(), u.getOpis()));
        }
        uL = (Vector<Uczniowie>) clientIn.readObject();
        ObservableList<String> ou = FXCollections.observableArrayList();
        for (Uczniowie u :
                uL) {
            ou.add(u.getImie() + " " + u.getNazwisko());
        }
        uczenChoiceBox.setItems(ou);

        ocenaChoiceBox.getItems().add(1);
        ocenaChoiceBox.getItems().add(2);
        ocenaChoiceBox.getItems().add(3);
        ocenaChoiceBox.getItems().add(4);
        ocenaChoiceBox.getItems().add(5);
        ocenaChoiceBox.getItems().add(6);
        ocenyTable.setItems(om);
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

    public void dodajButtonListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        errorLabel.setText("");
        if (ocenaChoiceBox.getSelectionModel().getSelectedIndex() < 0) {
            errorLabel.setText("Nie wybraleś oceny");
            return;
        }
        if (uczenChoiceBox.getSelectionModel().getSelectedIndex() < 0) {
            errorLabel.setText("Nie wybrałeś ucznia");
            return;
        }
        if (classBox.getSelectionModel().getSelectedIndex() < 0) {
            errorLabel.setText("Nie wybrałeś klasy");
            return;
        }
        if (subjectBox.getSelectionModel().getSelectedIndex() < 0) {
            errorLabel.setText("Nie wybrałeś przedmiotu");
        }
        System.out.println(uL);

        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(121);
        clientIn = new ObjectInputStream(socketClient.getInputStream());
        clientOut.writeObject(ocenaChoiceBox.getSelectionModel().getSelectedIndex() + 1);
        clientOut.writeObject(opisTextField.getText());
        clientOut.writeObject(uL.get(uczenChoiceBox.getSelectionModel().getSelectedIndex()).getId_ucznia());
        System.out.println(uL.get(uczenChoiceBox.getSelectionModel().getSelectedIndex()).getId_ucznia());
        clientOut.writeObject(nauczyciele.getId_nauczyciela());
        clientOut.writeObject(subjectBox.getSelectionModel().getSelectedIndex() + 1);
        odnowTablice();

    }

    public void usunButtonListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(122);
        clientIn = new ObjectInputStream(socketClient.getInputStream());

        clientOut.writeObject(ocenyTable.getSelectionModel().getSelectedItem().getId_oceny());
        odnowTablice();

    }



    public void odnowTablice() throws IOException, ClassNotFoundException {
        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(120);
        clientIn = new ObjectInputStream(socketClient.getInputStream());
        clientOut.writeObject(nauczyciele.getId_nauczyciela());
        clientOut.writeObject(classBox.getSelectionModel().getSelectedIndex() + 1);
        TeacherMarks t = (TeacherMarks) clientIn.readObject();
        //System.out.println(t);

        Vector<Marks> mList = t.getMarks();
        uczenOcenyColumn.setCellValueFactory(new PropertyValueFactory<NauczycielOceny, String>("uczen"));
        ocenaColumn.setCellValueFactory(new PropertyValueFactory<NauczycielOceny, String>("ocena"));
        opisColumn.setCellValueFactory(new PropertyValueFactory<NauczycielOceny, String>("opis"));
        ObservableList om = FXCollections.observableArrayList();
        for (Marks u :
                mList) {
            om.add(new NauczycielOceny(u.getStudentId(), u.getMarkId(), u.getInfo(), u.getMark(), u.getOpis()));
        }
        uL = (Vector<Uczniowie>) clientIn.readObject();
        ObservableList<String> ou = FXCollections.observableArrayList();
        for (Uczniowie u :
                uL) {
            ou.add(u.getImie() + " " + u.getNazwisko());
        }
        uczenChoiceBox.setItems(ou);

        ocenyTable.setItems(om);
    }


    public void odnowButtonLIstener(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        odnowTablice();
    }
}

