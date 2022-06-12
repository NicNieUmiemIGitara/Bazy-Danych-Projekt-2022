package TableClasses;

public class Frekwencja {
    int id_frekwencji;
    char obecnosc;
    int uczniowie_id_ucznia;
    int lekcje_id_lekcji;

    public int getId_frekwencji() {
        return id_frekwencji;
    }

    public void setId_frekwencji(int id_frekwencji) {
        this.id_frekwencji = id_frekwencji;
    }

    public char getObecnosc() {
        return obecnosc;
    }

    public void setObecnosc(char obecnosc) {
        this.obecnosc = obecnosc;
    }

    public int getUczniowie_id_ucznia() {
        return uczniowie_id_ucznia;
    }

    public void setUczniowie_id_ucznia(int uczniowie_id_ucznia) {
        this.uczniowie_id_ucznia = uczniowie_id_ucznia;
    }

    public int getLekcje_id_lekcji() {
        return lekcje_id_lekcji;
    }

    public void setLekcje_id_lekcji(int lekcje_id_lekcji) {
        this.lekcje_id_lekcji = lekcje_id_lekcji;
    }
}
