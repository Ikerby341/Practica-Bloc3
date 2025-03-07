package Productes;

public class Textil extends Productes {
    String compText = "";

    public Textil(String nom, Float preu, int codibarres, String compText) {
        super(nom, preu, codibarres);
        this.compText = compText;
    }

    public String getCompText() {
        return compText;
    }

    public void setCompText(String compText) {
        this.compText = compText;
    }

    @Override
    public String toString() {
        return "Textil{" +
                "nom: '" + nom + '\'' +
                ", preu: " + preu +
                ", codibarres: " + codibarres +
                ", compText: '" + compText + '\'';
    }
}
