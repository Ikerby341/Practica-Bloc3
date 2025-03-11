package Productes;

import java.util.Comparator;

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

    public int compareTo(Textil other) {
        return this.compText.compareTo(other.compText);
    }

    public static final Comparator<Textil> COMPARATOR = new Comparator<Textil>() {
        @Override
        public int compare(Textil t1, Textil t2) {
            return t1.getCompText().compareTo(t2.getCompText());
        }
    };

    @Override
    public String toString() {
        return "Textil: " +
                "nom: '" + nom + '\'' +
                ", preu: " + preu +
                ", codi de barres: " + codibarres +
                ", composició tèxtil: '" + compText + '\'';
    }
}
