package TableClasses;

public class Oceny {
    int id_oceny;
    int ocena;
    String opis;
    int uczniowie_id_ucznia;
    int nauczyciele_id_nauczyciela;
    int przedmioty_id_przedmiotu;

    public int getId_oceny() {
        return id_oceny;
    }

    public void setId_oceny(int id_oceny) {
        this.id_oceny = id_oceny;
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

    public int getUczniowie_id_ucznia() {
        return uczniowie_id_ucznia;
    }

    public void setUczniowie_id_ucznia(int uczniowie_id_ucznia) {
        this.uczniowie_id_ucznia = uczniowie_id_ucznia;
    }

    public int getNauczyciele_id_nauczyciela() {
        return nauczyciele_id_nauczyciela;
    }

    public void setNauczyciele_id_nauczyciela(int nauczyciele_id_nauczyciela) {
        this.nauczyciele_id_nauczyciela = nauczyciele_id_nauczyciela;
    }

    public int getPrzedmioty_id_przedmiotu() {
        return przedmioty_id_przedmiotu;
    }

    public void setPrzedmioty_id_przedmiotu(int przedmioty_id_przedmiotu) {
        this.przedmioty_id_przedmiotu = przedmioty_id_przedmiotu;
    }
}
