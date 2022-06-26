package com.example.demo;

public class DyrektorNayczyciel {
    int id_nauczyciela;
    String login;
    String haslo;
    String imieNazwisko;
    Double pensja;
    int adresy_id_adresu;

    public DyrektorNayczyciel(int id_nauczyciela, String login, String haslo, String imieNazwisko, double pensja, int adresy_id_adresu) {
        this.id_nauczyciela = id_nauczyciela;
        this.login = login;
        this.haslo = haslo;
        this.imieNazwisko = imieNazwisko;
        this.pensja = pensja;
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

    public String getImieNazwisko() {
        return imieNazwisko;
    }

    public void setImieNazwisko(String imieNazwisko) {
        this.imieNazwisko = imieNazwisko;
    }

    public Double getPensja() {
        return pensja;
    }

    public void setPensja(double pensja) {
        this.pensja = pensja;
    }

    public int getAdresy_id_adresu() {
        return adresy_id_adresu;
    }

    public void setAdresy_id_adresu(int adresy_id_adresu) {
        this.adresy_id_adresu = adresy_id_adresu;
    }

    @Override
    public String toString() {
        return "DyrektorNayczyciel{" +
                "id_nauczyciela=" + id_nauczyciela +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", imieNazwisko='" + imieNazwisko + '\'' +
                ", pensja=" + pensja +
                ", adresy_id_adresu=" + adresy_id_adresu +
                '}';
    }
}
