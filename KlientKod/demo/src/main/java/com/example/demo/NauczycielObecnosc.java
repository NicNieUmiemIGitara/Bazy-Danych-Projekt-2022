package com.example.demo;

public class NauczycielObecnosc {
    int id;
    String imie;
    String nazwisko;
    boolean obecnosc;

    public NauczycielObecnosc(int id, String imie, String nazwisko, boolean obecnosc) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.obecnosc = obecnosc;
    }

    @Override
    public String toString() {
        return "NauczycielObecnosc{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", obecnosc=" + obecnosc +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public boolean isObecnosc() {
        return obecnosc;
    }

    public void setObecnosc(boolean obecnosc) {
        this.obecnosc = obecnosc;
    }
}
