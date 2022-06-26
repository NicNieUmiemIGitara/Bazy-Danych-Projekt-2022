package com.example.demo;

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

public class d1_uczen_usunCtrl {
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
    public TableView<DyrektorUczen> uczenUsunTableView;
    public TableColumn<DyrektorUczen, Integer> idUczenUsunColumn;
    public TableColumn<DyrektorUczen, String> imieNazwiskoUczenUsun;
    public TextArea idTextFArea;
    public TextArea nazwiskoTextFArea;
    public Button usunButton;

    public void initialize() throws IOException, ClassNotFoundException {
        nameLabelNaucz.setText(Main.dyrektor.getImie());
        surnameLabelNaucz.setText(Main.dyrektor.getNazwisko());
        idUczenUsunColumn.setCellValueFactory(new PropertyValueFactory<DyrektorUczen, Integer>("id_ucznia"));
        imieNazwiskoUczenUsun.setCellValueFactory(new PropertyValueFactory<DyrektorUczen, String>("imieNazwisko"));

        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(320);
        clientIn = new ObjectInputStream(socketClient.getInputStream());
        Vector<Uczniowie> tL = (Vector<Uczniowie>) clientIn.readObject();

        ObservableList<DyrektorUczen> ot = FXCollections.observableArrayList();
        for (Uczniowie n :
                tL) {
            ot.add(new DyrektorUczen(n.getId_ucznia(),
                    n.getLogin(),
                    n.getHaslo(),
                    n.getImie() + " " + n.getNazwisko(),
                    n.getAdresy_id_adresu()));
        }
        uczenUsunTableView.setItems(ot);

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


    public void usunButtonListener(ActionEvent actionEvent) throws IOException {
        socketClient = new Socket("127.0.0.1", 42069);
        clientOut = new ObjectOutputStream(socketClient.getOutputStream());
        clientOut.writeObject(321);
        clientIn = new ObjectInputStream(socketClient.getInputStream());
        clientOut.writeObject(Integer.valueOf(idTextFArea.getText()));
        uczenUsunTableView.getItems().remove(
                uczenUsunTableView.getSelectionModel().getSelectedItem());


        uczenUsunTableView.refresh();
    }

    public void tableChoiceListener(MouseEvent mouseEvent) {
        idTextFArea.setText(String.valueOf(Integer.valueOf(uczenUsunTableView.getSelectionModel().getSelectedItem().getId_ucznia())));
        nazwiskoTextFArea.setText(uczenUsunTableView.getSelectionModel().getSelectedItem().getImieNazwisko());
    }
}

