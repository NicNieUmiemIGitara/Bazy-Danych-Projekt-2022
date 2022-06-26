package insides.TableClasses;

import java.io.Serializable;

public class Klasy implements Serializable {
    int id_klasy;
    int rok;
    String nazwa;

    public int getId_klasy() {
        return id_klasy;
    }

    public void setId_klasy(int id_klasy) {
        this.id_klasy = id_klasy;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public String toString() {
        return "Klasy{" +
                "id_klasy=" + id_klasy +
                ", rok=" + rok +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }
}
