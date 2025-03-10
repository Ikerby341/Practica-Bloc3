import java.time.LocalDate;
import java.util.*;
import Productes.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean cj = true;
        Vista.msg("BENVINGUT AL SAPAMERCAT");
        while (cj) {
            Vista.titol("INICI");
            Vista.msg("1) Introduir producte\n2) Passar per caixa\n3) Mostrar carret de compra\n0) Acabar");
            int a = scan.nextInt();
            scan.nextLine(); // Consume el salto de línea pendiente
            switch (a) {
                case 0:
                    cj = false;
                    break;
                case 1:
                    Vista.titol("PRODUCTE");
                    Vista.msg("1) Alimentació\n2) Tèxtil\n3) Electrònica\n0) Tornar");
                    int b = scan.nextInt();
                    scan.nextLine(); // Consume el salto de línea pendiente
                    switch (b) {
                        case 0:
                            break;
                        case 1:
                            ArrayList<String> a0 = new ArrayList<>();
                            a0.addAll(obtenirDades("Afegir alimentació"));
                            Vista.msg("Data caducitat (YYYY-MM-DD): ");
                            LocalDate d = LocalDate.parse(scan.nextLine());
                            Alimentacio aliment = new Alimentacio(a0.get(0), Float.parseFloat(a0.get(1)), Integer.parseInt(a0.get(2)), d);
                            Model.afegirAlCarret(aliment);
                            break;
                        case 2:
                            ArrayList<String> a1 = new ArrayList<>();
                            a1.addAll(obtenirDades("Afegir tèxtil"));
                            Vista.msg("Composició tèxtil: ");
                            String ct1 = scan.nextLine();
                            Textil aliment1 = new Textil(a1.get(0), Float.parseFloat(a1.get(1)), Integer.parseInt(a1.get(2)), ct1);
                            Model.afegirAlCarret(aliment1);
                            break;
                        case 3:
                            ArrayList<String> a2 = new ArrayList<>();
                            a2.addAll(obtenirDades("Afegir electrònica"));
                            Vista.msg("Dies de garantía: ");
                            int g2 = scan.nextInt();
                            scan.nextLine(); // Consume el salto de línea pendiente
                            Electronica aliment2 = new Electronica(a2.get(0), Float.parseFloat(a2.get(1)), Integer.parseInt(a2.get(2)), g2);
                            Model.afegirAlCarret(aliment2);
                            break;
                    }
                    break;
                case 2:
                    Vista.msg(Model.crearTiquet());
                    cj = false;
                    break;
                case 3:
                    Vista.msg(Model.carretCompra());
                    break;
            }
        }
    }
    private static ArrayList<String> obtenirDades(String titol){
        Scanner scan = new Scanner(System.in);
        ArrayList<String> dd = new ArrayList<>();
        Vista.msg(titol);
        Vista.msg("Nom producte:");
        dd.add(scan.nextLine());
        Vista.msg("Preu: ");
        dd.add(String.valueOf(scan.nextFloat()));
        scan.nextLine(); // Consume el salto de línea pendiente
        Vista.msg("Codi de barres: ");
        dd.add(String.valueOf(scan.nextInt()));
        scan.nextLine(); // Consume el salto de línea pendiente
        return dd;
    }
}
