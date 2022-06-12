package insides.TableClasses;

public class Wiadomosci {
    int id_wiadomosci;
    String tresc;
    int nauczyciele_id_nauczyciela;
    int uczniowie_id_ucznia;

    public int getId_wiadomosci() {
        return id_wiadomosci;
    }

    public void setId_wiadomosci(int id_wiadomosci) {
        this.id_wiadomosci = id_wiadomosci;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public int getNauczyciele_id_nauczyciela() {
        return nauczyciele_id_nauczyciela;
    }

    public void setNauczyciele_id_nauczyciela(int nauczyciele_id_nauczyciela) {
        this.nauczyciele_id_nauczyciela = nauczyciele_id_nauczyciela;
    }

    public int getUczniowie_id_ucznia() {
        return uczniowie_id_ucznia;
    }

    public void setUczniowie_id_ucznia(int uczniowie_id_ucznia) {
        this.uczniowie_id_ucznia = uczniowie_id_ucznia;
    }
}
