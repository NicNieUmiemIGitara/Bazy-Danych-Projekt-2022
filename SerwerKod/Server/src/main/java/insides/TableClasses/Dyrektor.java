package insides.TableClasses;

import java.io.Serializable;

public class Dyrektor implements Serializable {
    int id_dyrektora;
    String login;
    String haslo;
    String imie;
    String nazwisko;
    int adresy_id_adresu;

    public int getId_dyrektora() {
        return id_dyrektora;
    }

    public void setId_dyrektora(int id_dyrektora) {
        this.id_dyrektora = id_dyrektora;
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

    public int getAdresy_id_adresu() {
        return adresy_id_adresu;
    }

    public void setAdresy_id_adresu(int adresy_id_adresu) {
        this.adresy_id_adresu = adresy_id_adresu;
    }
}
