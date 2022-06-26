package com.example.demo;

import insides.TableClasses.Adresy;
import insides.TableClasses.Uczniowie;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static com.example.demo.Main.*;

public class d1_uczen_dodajCtrl {
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
    public TextField uczenImie;
    public Button dodajButton;
    public TextField uczenNazwisko;
    public TextField uczenMiasto;
    public TextField uczenUlica;
    public TextField uczenNrDomu;
    public TextField uczenLogin;
    public TextField uczenHaslo;
    public TextField uczenKlasa;

    public void initialize() {
        nameLabelNaucz.setText(Main.dyrektor.getImie());
        surnameLabelNaucz.setText(Main.dyrektor.getNazwisko());
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

    public void dodajButtonListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        Uczniowie uczniowie = new Uczniowie();
        Adresy adresy = new Adresy();


        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(311);
        clientIn = new ObjectInputStream(socketClient.getInputStream());

        adresy.setMiejscowosc(uczenMiasto.getText());
        adresy.setNr_domu(Integer.parseInt(uczenNrDomu.getText()));
        adresy.setUlica(uczenUlica.getText());
        clientOut.writeObject(adresy);
        int id_adres = (int) clientIn.readObject();
        uczniowie.setImie(uczenImie.getText());
        uczniowie.setNazwisko(uczenNazwisko.getText());
        uczniowie.setLogin(uczenLogin.getText());
        uczniowie.setHaslo(uczenHaslo.getText());
        uczniowie.setKlasy_id_klasy(Integer.parseInt(uczenKlasa.getText()));
        uczniowie.setAdresy_id_adresu(id_adres);
        clientOut.writeObject(uczniowie);

    }
}
