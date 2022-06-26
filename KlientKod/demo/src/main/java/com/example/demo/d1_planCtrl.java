package com.example.demo;

import insides.TableClasses.Klasy;
import insides.TableClasses.Nauczyciele;
import insides.TableClasses.Przedmioty;
import insides.TableClasses.Sala;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Vector;

import static com.example.demo.Main.*;

public class d1_planCtrl {

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
    public ChoiceBox pon1;
    public ChoiceBox pon2;
    public ChoiceBox pon3;
    public ChoiceBox pon4;
    public ChoiceBox pon5;
    public ChoiceBox wt1;
    public ChoiceBox wt2;
    public ChoiceBox wt3;
    public ChoiceBox wt4;
    public ChoiceBox wt5;
    public ChoiceBox sr1;
    public ChoiceBox sr2;
    public ChoiceBox sr3;
    public ChoiceBox sr4;
    public ChoiceBox sr5;
    public ChoiceBox czw1;
    public ChoiceBox czw2;
    public ChoiceBox czw3;
    public ChoiceBox czw4;
    public ChoiceBox czw5;
    public ChoiceBox pt1;
    public ChoiceBox pt2;
    public ChoiceBox pt3;
    public ChoiceBox pt4;
    public ChoiceBox pt5;
    public ChoiceBox klasa;
    public ChoiceBox matematyka;
    public ChoiceBox fizyka;
    public ChoiceBox jPolski;
    public ChoiceBox jAngielski;
    public ChoiceBox wf;
    public ChoiceBox religia;
    public ChoiceBox przyroda;
    public ChoiceBox chemia;
    public DatePicker date;
    public Button zapiszButton;
    public ChoiceBox sala;
    ObservableList<String> listaPrzedmiotow;
    ObservableList<String> listaNauczycieli;
    ObservableList<String> listaKlas;
    ObservableList<String> listaSal;

    Vector<Nauczyciele> nL;
    Vector<Klasy> kL;
    Vector<Przedmioty> pL;
    Vector<Sala> sL;

    public void initialize() throws IOException, ClassNotFoundException {
        nameLabelNaucz.setText(Main.dyrektor.getImie());
        surnameLabelNaucz.setText(Main.dyrektor.getNazwisko());

        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(360);
        clientIn = new ObjectInputStream(socketClient.getInputStream());
        System.out.println("dupa");
        pL = (Vector<Przedmioty>) clientIn.readObject();
        nL = (Vector<Nauczyciele>) clientIn.readObject();
        kL = (Vector<Klasy>) clientIn.readObject();
        sL = (Vector<Sala>) clientIn.readObject();

        listaKlas = FXCollections.observableArrayList();
        listaNauczycieli = FXCollections.observableArrayList();
        listaPrzedmiotow = FXCollections.observableArrayList();
        listaSal = FXCollections.observableArrayList();
        System.out.println("dupa");
        listaPrzedmiotow.add(null);
        for (Przedmioty p :
                pL) {
            listaPrzedmiotow.add(p.getNazwa());
        }

        for (Nauczyciele n :
                nL) {
            listaNauczycieli.add(n.getImie() + " " + n.getNazwisko());
        }
        for (Klasy k :
                kL) {
            listaKlas.add(k.getRok() + " " + k.getNazwa());
        }

        for (Sala s :
                sL) {
            listaSal.add(String.valueOf(s.getNr_sali()));

        }
        pon1.setItems(listaPrzedmiotow);
        pon2.setItems(listaPrzedmiotow);
        pon3.setItems(listaPrzedmiotow);
        pon4.setItems(listaPrzedmiotow);
        pon5.setItems(listaPrzedmiotow);

        wt1.setItems(listaPrzedmiotow);
        wt2.setItems(listaPrzedmiotow);
        wt3.setItems(listaPrzedmiotow);
        wt4.setItems(listaPrzedmiotow);
        wt5.setItems(listaPrzedmiotow);

        sr1.setItems(listaPrzedmiotow);
        sr2.setItems(listaPrzedmiotow);
        sr3.setItems(listaPrzedmiotow);
        sr4.setItems(listaPrzedmiotow);
        sr5.setItems(listaPrzedmiotow);

        czw1.setItems(listaPrzedmiotow);
        czw2.setItems(listaPrzedmiotow);
        czw3.setItems(listaPrzedmiotow);
        czw4.setItems(listaPrzedmiotow);
        czw5.setItems(listaPrzedmiotow);

        pt1.setItems(listaPrzedmiotow);
        pt2.setItems(listaPrzedmiotow);
        pt3.setItems(listaPrzedmiotow);
        pt4.setItems(listaPrzedmiotow);
        pt5.setItems(listaPrzedmiotow);

        matematyka.setItems(listaNauczycieli);
        jPolski.setItems(listaNauczycieli);
        przyroda.setItems(listaNauczycieli);
        wf.setItems(listaNauczycieli);
        fizyka.setItems(listaNauczycieli);
        jAngielski.setItems(listaNauczycieli);
        chemia.setItems(listaNauczycieli);
        religia.setItems(listaNauczycieli);

        sala.setItems(listaSal);

        klasa.setItems(listaKlas);
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

    public void zapiszButtonListener(ActionEvent actionEvent) throws IOException {

        errorLabel.setText("");
        Vector<ChoiceBox> d1 = new Vector<>();
        Vector<ChoiceBox> d2 = new Vector<>();
        Vector<ChoiceBox> d3 = new Vector<>();
        Vector<ChoiceBox> d4 = new Vector<>();
        Vector<ChoiceBox> d5 = new Vector<>();

        d1.add(pon1);
        d1.add(pon2);
        d1.add(pon3);
        d1.add(pon4);
        d1.add(pon5);

        d2.add(wt1);
        d2.add(wt2);
        d2.add(wt3);
        d2.add(wt4);
        d2.add(wt5);
        d3.add(sr1);
        d3.add(sr2);
        d3.add(sr3);
        d3.add(sr4);
        d3.add(sr5);

        d4.add(czw1);
        d4.add(czw2);
        d4.add(czw3);
        d4.add(czw4);
        d4.add(czw5);

        d5.add(pt1);
        d5.add(pt2);
        d5.add(pt3);
        d5.add(pt4);
        d5.add(pt5);

        Vector<ChoiceBox> naucz = new Vector<>();
        naucz.add(matematyka);
        naucz.add(jPolski);
        naucz.add(jAngielski);
        naucz.add(przyroda);
        naucz.add(fizyka);
        naucz.add(chemia);
        naucz.add(wf);
        naucz.add(religia);

        int godz_lekcyjna;
        LocalDate localDate = date.getValue();
        if (localDate == null) {
            errorLabel.setText("Nie podano daty");
            return;
        }
        localDate = localDate.minusDays(localDate.getDayOfWeek().getValue() - 1);
        checkZapiszButton(d1, naucz, localDate);
        localDate = localDate.plusDays(1);
        checkZapiszButton(d2, naucz, localDate);
        localDate = localDate.plusDays(1);
        checkZapiszButton(d3, naucz, localDate);
        localDate = localDate.plusDays(1);
        checkZapiszButton(d4, naucz, localDate);
        localDate = localDate.plusDays(1);
        checkZapiszButton(d5, naucz, localDate);

    }

    public void checkZapiszButton(Vector<ChoiceBox> day1, Vector<ChoiceBox> naucz,
                                  LocalDate localDate) throws IOException {


        int przedmioty_id_przedmiotu;
        int nauczyciele_id_nauczyciela;
        int klasy_id_klasy;
        int sala_id_sala;


        System.out.println("Data: " + localDate);
        if (klasa.getSelectionModel().getSelectedIndex() == -1) {
            errorLabel.setText("Nie podano klasy");
            //clientOut.writeObject(-1);
            return;
        }
        klasy_id_klasy = kL.get(klasa.getSelectionModel().getSelectedIndex()).getId_klasy();
        System.out.println("Id klasy: " + klasy_id_klasy);
        if (sala.getSelectionModel().getSelectedIndex() == -1) {
            errorLabel.setText("Nie wybrano sali");
            System.out.println("Nie udane id sali: " + sala.getSelectionModel().getSelectedIndex());
            //clientOut.writeObject(-1);
            return;
        }
        sala_id_sala = sL.get(sala.getSelectionModel().getSelectedIndex()).getId_sala();

        System.out.println("Id sali: " + sala_id_sala);
        for (int i = 0; i < 5; i++) {
            System.out.println("I: " + i);
            przedmioty_id_przedmiotu = day1.get(i).getSelectionModel().getSelectedIndex();
            if (przedmioty_id_przedmiotu > 0) {
                przedmioty_id_przedmiotu = pL.get(przedmioty_id_przedmiotu - 1).getId_przedmiotu();
            } else {
                continue;
            }
            nauczyciele_id_nauczyciela = naucz.get(przedmioty_id_przedmiotu - 1)
                    .getSelectionModel().getSelectedIndex();
            if (nauczyciele_id_nauczyciela > -1) {
                nauczyciele_id_nauczyciela = nL.get(nauczyciele_id_nauczyciela).getId_nauczyciela();
            } else {
                errorLabel.setText("Nie wprowadzono nauczycieli do wymaganych przedmiotow");
                // clientOut.writeObject(-1);
                return;
            }
            socketClient = new Socket("127.0.0.1", 42069);
            clientOut = new ObjectOutputStream(socketClient.getOutputStream());
            clientIn = new ObjectInputStream(socketClient.getInputStream());
            clientOut.writeObject(361);
            clientOut.writeObject(i/*to jest godzina lekcyjna*/);
            clientOut.writeObject(localDate);
            clientOut.writeObject(przedmioty_id_przedmiotu);
            clientOut.writeObject(nauczyciele_id_nauczyciela);
            clientOut.writeObject(klasy_id_klasy);
            clientOut.writeObject(sala_id_sala);


        }

    }
}
