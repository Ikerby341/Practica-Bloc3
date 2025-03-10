import Productes.Productes;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Model {
    private static Queue<Productes> carret = new ArrayDeque<>();

    protected static void afegirAlCarret(Productes p) {
        carret.add(p);
    }

    protected static String crearTiquet() {
        String barras = "------------------------------------";
        String finals = barras + "\nSAPAMERCAT\n" + barras + "\nData: " + LocalDate.now() + "\n" + barras;
        Float preufinal = 0F;
        DecimalFormat df = new DecimalFormat("#.00");

        Map<Integer, Integer> quantitats = getObjectQuantities(carret);
        Map<Integer, Productes> productesUnics = getUniqueProducts(carret);

        for (Map.Entry<Integer, Productes> entrada : productesUnics.entrySet()) {
            Productes p = entrada.getValue();
            int quantitat = quantitats.get(entrada.getKey());
            finals += "\n" + p.getNom() + "     " + quantitat + " " + df.format(p.getPreu()) + " " + df.format(p.getPreu() * quantitat);
            preufinal += p.getPreu() * quantitat;
        }

        finals += "\n" + barras + "\nTotal: " + df.format(preufinal) + " €";
        return finals;
    }

    protected static String carretCompra(){
        String finali = "Carret";
        Map<Integer, Integer> quantitats = getObjectQuantities(carret);
        Map<Integer, Productes> productesUnics = getUniqueProducts(carret);

        for (Map.Entry<Integer, Productes> entrada : productesUnics.entrySet()) {
            Productes p = entrada.getValue();
            int quantitat = quantitats.get(entrada.getKey());
            finali += "\n" + p.getNom() + " -> " + quantitat;
        }
        return finali;
    }

    private static Map<Integer, Integer> getObjectQuantities(Queue<Productes> carret) {
        Map<Integer, Integer> quantitats = new HashMap<>();
        for (Productes p : carret) {
            int codibarres = p.getCodibarres();
            quantitats.put(codibarres, quantitats.getOrDefault(codibarres, 0) + 1);
        }
        return quantitats;
    }

    private static Map<Integer, Productes> getUniqueProducts(Queue<Productes> carret) {
        Map<Integer, Productes> productesUnics = new HashMap<>();
        for (Productes p : carret) {
            productesUnics.put(p.getCodibarres(), p);
        }
        return productesUnics;
    }
}
