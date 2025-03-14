import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import Productes.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean cj = true;
        Vista.msg("BENVINGUT AL SAPAMERCAT");
        try {
            while (cj) {
                Vista.titol("INICI");
                Vista.msg("1) Introduir producte\n2) Passar per caixa\n3) Mostrar carret de compra\n4) Gestió Magatzem i Compres\n0) Acabar");
                int a = scan.nextInt();
                if (a < 0 || a > 4) throw new InputMismatchException("El número introduit no es correcte (No existeixen accions amb aquest numero)");
                scan.nextLine(); // Consume el salto de línea pendiente
                switch (a) {
                    case 0:
                        cj = false;
                        break;
                    case 1:
                        introduirProducte();
                        break;
                    case 2:
                        Vista.msg(Model.crearTiquet());
                        cont();
                        break;
                    case 3:
                        Vista.msg(Model.carretCompra());
                        cont();
                        break;
                    case 4:
                        gestioMagatzemICompres();
                        break;
                }
            }
        } catch (InputMismatchException | FileNotFoundException e) {
            Vista.msg(e.getMessage());
        } catch (IOException e) {
            Vista.msg(e.getMessage());
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

    private static void cont(){
        Scanner scan = new Scanner(System.in);
        Vista.msg("Pulsa 'enter' per continuar");
        scan.nextLine();
    }

    private static void afegirAlimentacio(){
        Scanner scan = new Scanner(System.in);
        try {
            ArrayList<String> a0 = new ArrayList<>();
            a0.addAll(obtenirDades("Afegir alimentació"));
            Vista.msg("Data caducitat (YYYY-MM-DD): ");
            LocalDate d = LocalDate.parse(scan.nextLine());
            Alimentacio aliment = new Alimentacio(a0.get(0), Float.parseFloat(a0.get(1)), Integer.parseInt(a0.get(2)), d);
            Model.afegirAlCarret(aliment);
        } catch (Exception e) {
            Vista.msg(e.getMessage());
        }
    }

    private static void afegirTextil(){
        Scanner scan = new Scanner(System.in);
        try {
            ArrayList<String> a1 = new ArrayList<>();
            a1.addAll(obtenirDades("Afegir tèxtil"));
            Vista.msg("Composició tèxtil: ");
            String ct1 = scan.nextLine();
            Textil aliment1 = new Textil(a1.get(0), Float.parseFloat(a1.get(1)), Integer.parseInt(a1.get(2)), ct1);
            Model.afegirAlCarret(aliment1);
        } catch (Exception e) {
            Vista.msg(e.getMessage());
        }
    }

    private static void afegirElectronica(){
        Scanner scan = new Scanner(System.in);
        try{
            ArrayList<String> a2 = new ArrayList<>();
            a2.addAll(obtenirDades("Afegir electrònica"));
            Vista.msg("Dies de garantía: ");
            int g2 = scan.nextInt();
            scan.nextLine();
            Electronica aliment2 = new Electronica(a2.get(0), Float.parseFloat(a2.get(1)), Integer.parseInt(a2.get(2)), g2);
            Model.afegirAlCarret(aliment2);
        } catch (Exception e) {
            Vista.msg(e.getMessage());
        }
    }

    private static void introduirProducte(){
        Scanner scan = new Scanner(System.in);
        Vista.titol("PRODUCTE");
        Vista.msg("1) Alimentació\n2) Tèxtil\n3) Electrònica\n0) Tornar");
        int b = scan.nextInt();
        if (b < 0 || b > 3) throw new InputMismatchException("El número introduit no es correcte (No existeixen accions amb aquest numero)");
        scan.nextLine(); // Consume el salto de línea pendiente
        switch (b) {
            case 0:
                break;
            case 1:
                afegirAlimentacio();
                break;
            case 2:
                afegirTextil();
                break;
            case 3:
                afegirElectronica();
                break;
        }
    }

    private static void gestioMagatzemICompres(){
        Scanner scan = new Scanner(System.in);
        Vista.titol("MAGATZEM i COMPRES");
        Vista.msg("1) Caducitat\n2) Tiquets de compra\n3) Composiciò tèxtil\n4) Buscar nom per codi de barres\n0) Tornar");
        int f = scan.nextInt();
        if (f < 0 || f > 4)
            throw new InputMismatchException("El número introduit no es correcte (No existeixen accions amb aquest numero)");
        scan.nextLine(); // Consume el salto de línea pendiente
        switch (f) {
            case 0:
                break;
            case 1:
                Vista.msg(Model.mostrarProductesPerCaducitat());
                cont();
                break;
            case 2:
                Vista.msg(Model.numTiquets());
                if (scan.nextLine().equals("s")) {
                    Vista.msg(Model.mostrarTotsTiquets());
                }
                cont();
                break;
            case 3:
                Vista.msg(Model.mostrarTextilsPerComposicio());
                cont();
                break;
            case 4:
                Vista.msg("Digues el codi per aconseguir el Nom");
                Vista.msg(Model.buscarNomProductePerCodi(scan.nextInt()));
                scan.nextLine();
                cont();
                break;
        }
    }
}
