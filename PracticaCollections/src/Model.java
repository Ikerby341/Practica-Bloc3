import Excepcions.CustomExcepcions;
import Productes.*;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

public class Model {
    private static Queue<Productes> carret = new ArrayDeque<>();
    private static Queue<String> tiquets = new ArrayDeque<>();

    protected static void afegirAlCarret(Productes p) throws CustomExcepcions.LimitProductesException {
        if (carret.size() >= 100) {
            throw new CustomExcepcions.LimitProductesException("S'ha superat el límit de productes al carret!");
        }
        carret.add(p);
    }

    protected static String crearTiquet() throws IOException {
        try {
            if (carret.isEmpty()) return "El carret de la compra està buit!";
            String barras = "------------------------------------";
            String finals = barras + "\nSAPAMERCAT\n" + barras + "\nData: " + LocalDate.now() + "\n" + barras;
            Float preufinal = 0F;
            DecimalFormat df = new DecimalFormat("#.00");

            try {
                Map<Integer, Integer> quantitats = getObjectQuantities(carret);
                Map<Integer, Productes> productesUnics = getUniqueProducts(carret);

                Map<String, Integer> groupedProducts = new LinkedHashMap<>();
                Map<String, String> productNames = new LinkedHashMap<>();
                Map<String, Float> productPrices = new LinkedHashMap<>();

                for (Productes p : carret) {
                    String key = p.getCodibarres() + "-" + p.getPreu();
                    groupedProducts.put(key, groupedProducts.getOrDefault(key, 0) + 1);
                    productNames.put(key, p.getNom());
                    productPrices.put(key, p.getPreu());
                }

                for (Map.Entry<String, Integer> entry : groupedProducts.entrySet()) {
                    String[] parts = entry.getKey().split("-");
                    int codibarres = Integer.parseInt(parts[0]);
                    float preu = Float.parseFloat(parts[1]);
                    String nom = productNames.get(entry.getKey());
                    int quantitat = entry.getValue();
                    finals += "\n" + nom + "     " + quantitat + " " + df.format(preu) + " " + df.format(preu * quantitat);
                    preufinal += preu * quantitat;
                }

            } catch (IOException e) {
                return "Error: Fallada d'entrada/sortida!";
            }

            finals += "\n" + barras + "\nTotal: " + df.format(preufinal) + " €";
            tiquets.add(finals);
            File tiquetsF = new File("./tiquets.txt");
            if (tiquetsF.createNewFile() || tiquetsF.exists()) {
                try (FileWriter writer = new FileWriter(tiquetsF)) {
                    writer.write(finals + "\n\n");
                }
            } else {
                return "Error: No es pot crear l arxiu tiquets.txt!";
            }
            while (!carret.isEmpty()) carret.remove();
            return finals;
        } catch (FileNotFoundException e) {
            return "Error: Fitxer no trobat!";
        }
    }

    private static Map<Integer, Integer> getObjectQuantities(Queue<Productes> carret) throws FileNotFoundException, IOException {
        Map<Integer, Integer> quantitats = new HashMap<>();
        for (Productes p : carret) {
            int codibarres = p.getCodibarres();
            quantitats.put(codibarres, quantitats.getOrDefault(codibarres, 0) + 1);
        }
        return quantitats;
    }

    private static Map<Integer, Productes> getUniqueProducts(Queue<Productes> carret) throws FileNotFoundException, IOException {
        Map<Integer, Productes> productesUnics = new HashMap<>();
        for (Productes p : carret) {
            productesUnics.put(p.getCodibarres(), p);
        }
        return productesUnics;
    }

    protected static String carretCompra() throws FileNotFoundException, IOException {
        if (carret.isEmpty()) return "El carret de la compra esta buit!";
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

    protected static String numTiquets() {
        if (tiquets.size() == 1) {
            return "Actualment hi ha " + tiquets.size() + " tiquet, vols veure-ho? (s) Si";
        }
        return "Actualment hi han " + tiquets.size() + " tiquets, vols veurel's tots? (s) Si";
    }

    protected static String mostrarTotsTiquets() {
        Queue<String> tiquetsTemp = new ArrayDeque<>();
        tiquetsTemp.addAll(tiquets);
        String fin = "";
        for (int i = 0; i < tiquets.size(); i++) {
            fin += "\n" + tiquetsTemp.peek();
            tiquetsTemp.remove();
        }
        return fin;
    }

    protected static String mostrarProductesPerCaducitat() {
        if (carret.isEmpty()) return "El carret de la compra esta buit!";
        StringBuilder resultado = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.00");

        List<Alimentacio> aliments = new ArrayList<>();
        for (Productes p : carret) {
            if (p instanceof Alimentacio) {
                aliments.add((Alimentacio) p);
            }
        }

        aliments.sort(Comparator.comparing(Alimentacio::getDataCaducitat));

        for (Alimentacio a : aliments) {
            resultado.append(a.getNom()).append(" - ")
                    .append("Caducitat: ").append(a.getDataCaducitat()).append(" - ")
                    .append("Preu: ").append(df.format(a.getPreu())).append(" €\n");
        }

        return resultado.toString();
    }

    protected static String mostrarTextilsPerComposicio() {
        if (carret.isEmpty()) return "El carret de la compra esta buit!";
        StringBuilder resultado = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.00");

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

        textils.sort(Textil.COMPARATOR);

        for (Textil t : textils) {
            resultado.append(t.getNom()).append(" - ")
                    .append("Composició Tèxtil: ").append(t.getCompText()).append(" - ")
                    .append("Preu: ").append(df.format(t.getPreu())).append(" €\n");
        }

        return resultado.toString();
    }

    protected static String buscarNomProductePerCodi(int codiBarres) {
        Optional<Productes> producte = carret.stream()
                .filter(p -> p.getCodibarres() == codiBarres)
                .findFirst();
        return producte.map(Productes::getNom)
                .orElse("Producte no trobat");
    }

    protected static void checkNegatiu(float value) throws CustomExcepcions.NegatiuException {
        if (value < 0) {
            throw new CustomExcepcions.NegatiuException("El valor no pot ser negatiu!");
        }
    }

    protected static void checkDataCaducitat(LocalDate data) throws CustomExcepcions.DataCaducitatException {
        if (data.isBefore(LocalDate.now())) {
            throw new CustomExcepcions.DataCaducitatException("La data de caducitat és anterior a la data actual!");
        }
    }
}
