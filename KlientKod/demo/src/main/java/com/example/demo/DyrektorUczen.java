package com.example.demo;

public class DyrektorUczen {
    int id_ucznia;
    String login;
    String haslo;
    String imieNazwisko;
    int adresy_id_adresu;

    public DyrektorUczen(int id_ucznia, String login, String haslo, String imieNazwisko, int adresy_id_adresu) {
        this.id_ucznia = id_ucznia;
        this.login = login;
        this.haslo = haslo;
        this.imieNazwisko = imieNazwisko;
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

    public String getImieNazwisko() {
        return imieNazwisko;
    }

    public void setImieNazwisko(String imieNazwisko) {
        this.imieNazwisko = imieNazwisko;
    }

    public int getAdresy_id_adresu() {
        return adresy_id_adresu;
    }

    public void setAdresy_id_adresu(int adresy_id_adresu) {
        this.adresy_id_adresu = adresy_id_adresu;
    }

}
