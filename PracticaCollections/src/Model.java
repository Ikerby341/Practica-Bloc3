import Productes.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;


public class Model {
    private static Queue<Productes> carret = new ArrayDeque<>();
    private static Queue<String> tiquets = new ArrayDeque<>();
    protected static void afegirAlCarret(Productes p) {
        carret.add(p);
    }

    protected static String crearTiquet() {
        if(carret.isEmpty()) return "El carret de la compra esta buit!";
        String barras = "------------------------------------";
        String finals = barras + "\nSAPAMERCAT\n" + barras + "\nData: " + LocalDate.now() + "\n" + barras;
        Float preufinal = 0F;
        DecimalFormat df = new DecimalFormat("#.00");

        Map<Integer, Integer> quantitats = getObjectQuantities(carret);
        Map<Integer, Productes> productesUnics = getUniqueProducts(carret);

        // Crear una lista ordenada de productos únicos
        List<Productes> sortedProducts = new ArrayList<>(productesUnics.values());
        Collections.sort(sortedProducts);

        for (Productes p : sortedProducts) {
            int quantitat = quantitats.get(p.getCodibarres());
            finals += "\n" + p.getNom() + "     " + quantitat + " " + df.format(p.getPreu()) + " " + df.format(p.getPreu() * quantitat);
            preufinal += p.getPreu() * quantitat;
        }

        finals += "\n" + barras + "\nTotal: " + df.format(preufinal) + " €";
        tiquets.add(finals);
        while(!carret.isEmpty()) carret.remove();
        return finals;
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

    protected static String carretCompra(){
        if(carret.isEmpty()) return "El carret de la compra esta buit!";
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

    protected static String numTiquets (){
        if(tiquets.size() == 1){
            return "Actualment hi ha " + tiquets.size() + " tiquet, vols veure-ho? (s) Si";
        }
        return "Actualment hi han " + tiquets.size() + " tiquets, vols veurel's tots? (s) Si";
    }

    protected static String mostrarTotsTiquets(){
        Queue<String> tiquetsTemp = new ArrayDeque<>();
        tiquetsTemp.addAll(tiquets);
        String fin = "";
        for(int i = 0; i < tiquets.size(); i++){
            fin += "\n" + tiquetsTemp.peek();
            tiquetsTemp.remove();
        }
        return fin;
    }

    public static String mostrarProductesPerCaducitat() {
        if(carret.isEmpty()) return "El carret de la compra esta buit!";
        StringBuilder resultado = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.00");

        // Crear una lista de productos de alimentación
        List<Alimentacio> aliments = new ArrayList<>();
        for (Productes p : carret) {
            if (p instanceof Alimentacio) {
                aliments.add((Alimentacio) p);
            }
        }

        // Ordenar los productos de alimentación por fecha de caducidad
        aliments.sort(Comparator.comparing(Alimentacio::getDataCaducitat));

        // Añadir los productos ordenados al resultado
        for (Alimentacio a : aliments) {
            resultado.append(a.getNom()).append(" - ")
                    .append("Caducitat: ").append(a.getDataCaducitat()).append(" - ")
                    .append("Preu: ").append(df.format(a.getPreu())).append(" €\n");
        }

        return resultado.toString();
    }

    public static String mostrarTextilsPerComposicio() {
        if(carret.isEmpty()) return "El carret de la compra esta buit!";
        StringBuilder resultado = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.00");

        // Crear una lista de productos de tèxtil
        List<Textil> textils = new ArrayList<>();
        Set<Integer> codisUnics = new HashSet<>();

        for (Productes p : carret) {
            if (p instanceof Textil) {
                Textil t = (Textil) p;
                if (codisUnics.add(t.getCodibarres())) {
                    textils.add(t);
                }
            }
        }

        // Ordenar los productos de tèxtil por composició tèxtil
        textils.sort(Textil.COMPARATOR);

        // Añadir los productos ordenados al resultado
        for (Textil t : textils) {
            resultado.append(t.getNom()).append(" - ")
                    .append("Composició Tèxtil: ").append(t.getCompText()).append(" - ")
                    .append("Preu: ").append(df.format(t.getPreu())).append(" €\n");
        }

        return resultado.toString();
    }
}
