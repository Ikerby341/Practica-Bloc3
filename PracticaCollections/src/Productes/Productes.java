package Productes;

public class Productes {
    String nom = "";
    Float preu = 0F;
    int codibarres = 0;

    public Productes(String nom, Float preu, int codibarres) {
        this.nom = nom;
        this.preu = preu;
        this.codibarres = codibarres;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getPreu() {
        return preu;
    }

    public void setPreu(Float preu) {
        this.preu = preu;
    }

    public int getCodibarres() {
        return codibarres;
    }

    public void setCodibarres(int codibarres) {
        this.codibarres = codibarres;
    }

    @Override
    public String toString() {
        return "Productes:" +
                "nom:'" + nom + '\'' +
                ", preu: " + preu +
                ", codibarres: " + codibarres;
    }
}
