package insides.TableClasses;

import java.io.Serializable;

public class Adresy implements Serializable {
    int id_adresu;
    String ulica;
    int nr_domu;
    String miejscowosc;

    public int getId_adresu() {
        return id_adresu;
    }

    public void setId_adresu(int id_adresu) {
        this.id_adresu = id_adresu;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getNr_domu() {
        return nr_domu;
    }

    public void setNr_domu(int nr_domu) {
        this.nr_domu = nr_domu;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    @Override
    public String toString() {
        return "Adresy{" +
                "id_adresu=" + id_adresu +
                ", ulica='" + ulica + '\'' +
                ", nr_domu=" + nr_domu +
                ", miejscowosc='" + miejscowosc + '\'' +
                '}';
    }
}
