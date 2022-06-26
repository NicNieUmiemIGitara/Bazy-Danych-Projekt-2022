package com.example.demo;

import insides.SpecifiedClasses.Student.DayFreq;
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
import java.sql.Date;
import java.time.LocalDate;
import java.util.Vector;

import static com.example.demo.Main.*;

public class u1_obecnoscCtrl {
    public Button obecnoscButton;
    public Label nameLabelNaucz;
    public Label surnameLabelNaucz;
    public Button ocenyButton;
    public Button sprawdzianyButton;
    public Button lekcjeButton;
    public ImageView logoutIcon;
    public TableView<UczenObecnoscSprawdzianyLekcje> uczenObecnoscTableView;
    public TableColumn<UczenObecnoscSprawdzianyLekcje, String> pon;
    public TableColumn<UczenObecnoscSprawdzianyLekcje, String> wt;
    public TableColumn<UczenObecnoscSprawdzianyLekcje, String> sr;
    public TableColumn<UczenObecnoscSprawdzianyLekcje, String> czw;
    public TableColumn<UczenObecnoscSprawdzianyLekcje, String> pt;
    public Label errorLabel;
    public Label schoolName;
    public Label classLabel;
    public DatePicker date;
    public Button refreash;

    public void initialize() {
        nameLabelNaucz.setText(Main.uczniowie.getImie());
        surnameLabelNaucz.setText(Main.uczniowie.getNazwisko());
        classLabel.setText(Main.klasa.getRok() + Main.klasa.getNazwa());
        pon.setCellValueFactory(new PropertyValueFactory<UczenObecnoscSprawdzianyLekcje, String>("pon"));
        wt.setCellValueFactory(new PropertyValueFactory<UczenObecnoscSprawdzianyLekcje, String>("wt"));
        sr.setCellValueFactory(new PropertyValueFactory<UczenObecnoscSprawdzianyLekcje, String>("sr"));
        czw.setCellValueFactory(new PropertyValueFactory<UczenObecnoscSprawdzianyLekcje, String>("czw"));
        pt.setCellValueFactory(new PropertyValueFactory<UczenObecnoscSprawdzianyLekcje, String>("pt"));
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

    public void refreashListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        LocalDate localDate = date.getValue();
        System.out.println(localDate.getDayOfWeek().getValue());
        LocalDate localDate1 = date.getValue().minusDays(localDate.getDayOfWeek().getValue() - 1);
        LocalDate localDate2 = date.getValue().plusDays(7 - localDate.getDayOfWeek().getValue());
        Date d = Date.valueOf(localDate);
        System.out.println(d);
        System.out.println(localDate1);
        System.out.println(localDate2);

        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(210);
        clientIn = new ObjectInputStream(socketClient.getInputStream());
        clientOut.writeObject(uczniowie.getId_ucznia());
        clientOut.writeObject(localDate1);
        clientOut.writeObject(localDate2);
        Vector<DayFreq> days = (Vector<DayFreq>) clientIn.readObject();
        ObservableList<UczenObecnoscSprawdzianyLekcje> od = FXCollections.observableArrayList();
        for (int i = 0; i < 5; i++) {

            od.add(new UczenObecnoscSprawdzianyLekcje(
                    days.get(0).getSubjects().get(i),
                    days.get(1).getSubjects().get(i),
                    days.get(2).getSubjects().get(i),
                    days.get(3).getSubjects().get(i),
                    days.get(4).getSubjects().get(i)));

        }
        uczenObecnoscTableView.refresh();
        uczenObecnoscTableView.setItems(od);
        System.out.println(od);

        //System.out.println(days);
        //Instant instant=Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        //java.util.Date d= Date.from(instant);
        //System.out.println(d);
    }
}
