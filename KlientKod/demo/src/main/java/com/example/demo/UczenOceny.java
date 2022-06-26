package com.example.demo;

public class UczenOceny {
    String przedmiot;
    String oceny;

    public UczenOceny(String przedmiot, String oceny) {
        this.przedmiot = przedmiot;
        this.oceny = oceny;
    }

    @Override
    public String toString() {
        return "UczenOceny{" +
                "przedmiot='" + przedmiot + '\'' +
                ", oceny='" + oceny + '\'' +
                '}';
    }

    public String getPrzedmiot() {
        return przedmiot;
    }

    public void setPrzedmiot(String przedmiot) {
        this.przedmiot = przedmiot;
    }

    public String getOceny() {
        return oceny;
    }

    public void setOceny(String oceny) {
        this.oceny = oceny;
    }
}
