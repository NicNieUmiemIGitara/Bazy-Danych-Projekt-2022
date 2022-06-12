package TableClasses;

import java.io.Serializable;

public class Nauczyciele implements Serializable {
    int id_nauczyciela;
    String login;
    String haslo;
    String imie;
    String nazwisko;
    double pensja;
    int adresy_id_adresu;

    public int getAdresy_id_adresu() {
        return adresy_id_adresu;
    }

    public void setAdresy_id_adresu(int adresy_id_adresu) {
        this.adresy_id_adresu = adresy_id_adresu;
    }

    public int getId_nauczyciela() {
        return id_nauczyciela;
    }

    public void setId_nauczyciela(int id_nauczyciela) {
        this.id_nauczyciela = id_nauczyciela;
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

    public double getPensja() {
        return pensja;
    }

    public void setPensja(double pensja) {
        this.pensja = pensja;
    }

    @Override
    public String toString() {
        return "\nNauczyciele{" +
                "id_nauczyciela=" + id_nauczyciela +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pensja=" + pensja +
                '}';
    }
}
