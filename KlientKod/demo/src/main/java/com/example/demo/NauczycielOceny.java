package com.example.demo;

public class NauczycielOceny {
    int id_ucznia;
    int id_oceny;
    String uczen;
    int ocena;
    String opis;

    public NauczycielOceny(int id_ucznia, int id_oceny, String uczen, int ocena, String opis) {
        this.id_ucznia = id_ucznia;
        this.id_oceny = id_oceny;
        this.uczen = uczen;
        this.ocena = ocena;
        this.opis = opis;
    }

    public NauczycielOceny(int id_ucznia, String uczen, int ocena, String opis) {
        this.id_ucznia = id_ucznia;
        this.uczen = uczen;
        this.ocena = ocena;
        this.opis = opis;
    }

    public NauczycielOceny(String uczen, int ocena, String opis) {
        this.uczen = uczen;
        this.ocena = ocena;
        this.opis = opis;
    }

    public int getId_oceny() {
        return id_oceny;
    }

    public void setId_oceny(int id_oceny) {
        this.id_oceny = id_oceny;
    }

    public int getId_ucznia() {
        return id_ucznia;
    }

    public void setId_ucznia(int id_ucznia) {
        this.id_ucznia = id_ucznia;
    }

    public String getUczen() {
        return uczen;
    }

    public void setUczen(String uczen) {
        this.uczen = uczen;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
