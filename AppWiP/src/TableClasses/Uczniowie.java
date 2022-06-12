package TableClasses;

import java.io.Serializable;

public class Uczniowie implements Serializable {
    int id_ucznia;
    String login;
    String haslo;
    String imie;
    String nazwisko;
    int klasy_id_klasy;
    int adresy_id_adresu;

    public int getKlasy_id_klasy() {
        return klasy_id_klasy;
    }

    public void setKlasy_id_klasy(int klasy_id_klasy) {
        this.klasy_id_klasy = klasy_id_klasy;
    }

    public int getAdresy_id_adresu() {
        return adresy_id_adresu;
    }

    public void setAdresy_id_adresu(int adresy_id_adresu) {
        this.adresy_id_adresu = adresy_id_adresu;
    }

    public int getId_ucznia() {
        return id_ucznia;
    }

    public void setId_ucznia(int id_ucznia) {
        this.id_ucznia = id_ucznia;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
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

    @Override
    public String toString() {
        return "\nUczniowie{" +
                "id_ucznia=" + id_ucznia +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", klasy_id_klasy=" + klasy_id_klasy +
                ", adresy_id_adresu=" + adresy_id_adresu +
                '}';
    }
}
