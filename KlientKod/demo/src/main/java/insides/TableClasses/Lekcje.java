package insides.TableClasses;

import java.sql.Date;

public class Lekcje {
    int id_lekcji;
    int godz_lekcyjna;
    Date data;
    int przedmioty_id_przedmiotu;
    int nauczyciele_id_nauczyciela;
    int sprawdziany_id_sprawdzianu;
    int klasy_id_klasy;
    int sala_id_sala;

    public int getId_lekcji() {
        return id_lekcji;
    }

    public void setId_lekcji(int id_lekcji) {
        this.id_lekcji = id_lekcji;
    }

    public int getGodz_lekcyjna() {
        return godz_lekcyjna;
    }

    public void setGodz_lekcyjna(int godz_lekcyjna) {
        this.godz_lekcyjna = godz_lekcyjna;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getPrzedmioty_id_przedmiotu() {
        return przedmioty_id_przedmiotu;
    }

    public void setPrzedmioty_id_przedmiotu(int przedmioty_id_przedmiotu) {
        this.przedmioty_id_przedmiotu = przedmioty_id_przedmiotu;
    }

    public int getNauczyciele_id_nauczyciela() {
        return nauczyciele_id_nauczyciela;
    }

    public void setNauczyciele_id_nauczyciela(int nauczyciele_id_nauczyciela) {
        this.nauczyciele_id_nauczyciela = nauczyciele_id_nauczyciela;
    }

    public int getSprawdziany_id_sprawdzianu() {
        return sprawdziany_id_sprawdzianu;
    }

    public void setSprawdziany_id_sprawdzianu(int sprawdziany_id_sprawdzianu) {
        this.sprawdziany_id_sprawdzianu = sprawdziany_id_sprawdzianu;
    }

    public int getKlasy_id_klasy() {
        return klasy_id_klasy;
    }

    public void setKlasy_id_klasy(int klasy_id_klasy) {
        this.klasy_id_klasy = klasy_id_klasy;
    }

    public int getSala_id_sala() {
        return sala_id_sala;
    }

    public void setSala_id_sala(int sala_id_sala) {
        this.sala_id_sala = sala_id_sala;
    }
}
