package Productes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Alimentacio extends Productes{
    LocalDate dataCaducitat;

    public Alimentacio(String nom, Float preu, int codibarres, LocalDate dataCaducitat) {
        super(nom, preu, codibarres);
        this.dataCaducitat = dataCaducitat;
        LocalDate dataActual = LocalDate.now();
        long diasRestantes = ChronoUnit.DAYS.between(dataActual, dataCaducitat);
        this.preu = (float) (preu - preu * (1.0 / (diasRestantes + 1)) + (preu * 0.1));
    }

    public LocalDate getDataCaducitat() {
        return dataCaducitat;
    }

    public void setDataCaducitat(LocalDate dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }

    @Override
    public String toString() {
        return "Alimentacio: " +
                "nom: '" + nom + '\'' +
                ", preu: " + preu +
                ", codi de barres: " + codibarres +
                ", data de caducitat: " + dataCaducitat;
    }
}


