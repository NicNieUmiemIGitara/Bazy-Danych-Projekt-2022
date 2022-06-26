package insides.TableClasses;

import java.io.Serializable;

public class Przedmioty implements Serializable {
    int id_przedmiotu;
    String nazwa;

    @Override
    public String toString() {
        return "Przedmioty{" +
                "id_przedmiotu=" + id_przedmiotu +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }

    public int getId_przedmiotu() {
        return id_przedmiotu;
    }

    public void setId_przedmiotu(int id_przedmiotu) {
        this.id_przedmiotu = id_przedmiotu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
