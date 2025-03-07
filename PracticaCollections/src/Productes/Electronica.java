package Productes;

public class Electronica extends Productes{
    int diesGarantia = 0;
    public Electronica(String nom, Float preu, int codibarres, int diesGarantia) {
        super(nom, preu, codibarres);
        this.diesGarantia = diesGarantia;
        this.preu = (float) (preu + preu*(diesGarantia/365)*0.1);
    }

    public int getDiesGarantia() {
        return diesGarantia;
    }

    public void setDiesGarantia(int diesGarantia) {
        this.diesGarantia = diesGarantia;
    }

    @Override
    public String toString() {
        return "Electronica{" +
                "nom: '" + nom + '\'' +
                ", preu: " + preu +
                ", codibarres: " + codibarres +
                ", diesGarantia: " + diesGarantia;
    }
}
