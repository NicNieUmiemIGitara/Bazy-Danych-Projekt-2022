package insides.TableClasses;

import java.io.Serializable;

public class Sala implements Serializable {
    int id_sala;
    int nr_sali;

    @Override
    public String toString() {
        return "Sala{" +
                "id_sala=" + id_sala +
                ", nr_sali=" + nr_sali +
                '}';
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public int getNr_sali() {
        return nr_sali;
    }

    public void setNr_sali(int nr_sali) {
        this.nr_sali = nr_sali;
    }
}
