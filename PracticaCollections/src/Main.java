import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import Excepcions.CustomExcepcions;
import Productes.*;

public class Main {

    /**
     * Funció principal que gestiona el menú d'interacció amb l'usuari.
     * Mostra un conjunt d'opcions perquè l'usuari pugui afegir productes al carret, passar per caixa,
     * veure el carret de compra i gestionar les compres i el magatzem.
     * @param args Arguments de la línia de comandament (no s'utilitzen en aquest cas).
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean cj = true;
        Vista.msg("BENVINGUT AL SAPAMERCAT");
        try {
            while (cj) {
                try{
                    Vista.titol("INICI");
                    Vista.msg("1) Introduir producte\n2) Passar per caixa\n3) Mostrar carret de compra\n4) Gestió Magatzem i Compres\n0) Acabar");
                    int a = scan.nextInt();
                    if (a < 0 || a > 4) throw new InputMismatchException("El número introduit no es correcte (No existeixen accions amb aquest numero)");
                    scan.nextLine(); // Consume el salto de línea pendent
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
                } catch (InputMismatchException e) {
                    Vista.msg(e.getMessage());
                }
            }
        } catch (InputMismatchException | FileNotFoundException e) {
            Vista.msg(e.getMessage());
        } catch (IOException e) {
            Vista.msg(e.getMessage());
        }
    }

    /**
     * Funció per obtenir dades de l'usuari per afegir un producte.
     * Demana el nom del producte, el preu i el codi de barres i retorna les dades.
     * @param titol Títol que es mostra abans de demanar les dades.
     * @return Llista amb les dades del producte (nom, preu, codi de barres).
     * @throws CustomExcepcions.NegatiuException Si el preu o el codi de barres són negatius.
     */
    private static ArrayList<String> obtenirDades(String titol) throws CustomExcepcions.NegatiuException {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> dd = new ArrayList<>();
        Vista.msg(titol);
        Vista.msg("Nom producte:");
        dd.add(scan.nextLine());
        Vista.msg("Preu: ");
        float preu = scan.nextFloat();
        Model.checkNegatiu(preu);
        dd.add(String.valueOf(preu));
        scan.nextLine(); // Consume el salto de línea pendent
        Vista.msg("Codi de barres: ");
        int codi = scan.nextInt();
        Model.checkNegatiu(codi);
        dd.add(String.valueOf(codi));
        scan.nextLine(); // Consume el salto de línea pendent
        return dd;
    }

    /**
     * Funció per esperar que l'usuari premi 'enter' per continuar l'execució del programa.
     */
    private static void cont(){
        Scanner scan = new Scanner(System.in);
        Vista.msg("Pulsa 'enter' per continuar");
        scan.nextLine();
    }

    /**
     * Funció que permet afegir un producte de tipus alimentació al carret.
     * Demana les dades específiques d'alimentació, incloent la data de caducitat, i les afegeix al carret.
     */
    private static void afegirAlimentacio() {
        Scanner scan = new Scanner(System.in);
        try {
            ArrayList<String> a0 = new ArrayList<>();
            a0.addAll(obtenirDades("Afegir alimentació"));
            Vista.msg("Data caducitat (YYYY-MM-DD): ");
            LocalDate d = LocalDate.parse(scan.nextLine());
            Model.checkDataCaducitat(d);
            Alimentacio aliment = new Alimentacio(a0.get(0), Float.parseFloat(a0.get(1)), Integer.parseInt(a0.get(2)), d);
            Model.afegirAlCarret(aliment);
        } catch (CustomExcepcions.DataCaducitatException e) {
            Vista.msg(e.getMessage());
        } catch (Exception e) {
            Vista.msg(e.getMessage());
        }
    }

    /**
     * Funció que permet afegir un producte de tipus tèxtil al carret.
     * Demana les dades específiques del tèxtil, incloent la composició, i les afegeix al carret.
     */
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

    /**
     * Funció que permet afegir un producte d'electrònica al carret.
     * Demana les dades específiques d'electrònica, incloent els dies de garantia, i les afegeix al carret.
     */
    private static void afegirElectronica(){
        Scanner scan = new Scanner(System.in);
        try{
            ArrayList<String> a2 = new ArrayList<>();
            a2.addAll(obtenirDades("Afegir electrònica"));
            Vista.msg("Dies de garantia: ");
            int g2 = scan.nextInt();
            scan.nextLine();
            Electronica aliment2 = new Electronica(a2.get(0), Float.parseFloat(a2.get(1)), Integer.parseInt(a2.get(2)), g2);
            Model.afegirAlCarret(aliment2);
        } catch (Exception e) {
            Vista.msg(e.getMessage());
        }
    }

    /**
     * Funció que permet introduir un nou producte seleccionant el tipus (alimentació, tèxtil o electrònica).
     * Aquesta funció redirigeix l'usuari a la funció corresponent per afegir el producte seleccionat.
     */
    private static void introduirProducte(){
        Scanner scan = new Scanner(System.in);
        Vista.titol("PRODUCTE");
        Vista.msg("1) Alimentació\n2) Tèxtil\n3) Electrònica\n0) Tornar");
        int b = scan.nextInt();
        if (b < 0 || b > 3) throw new InputMismatchException("El número introduit no es correcte (No existeixen accions amb aquest numero)");
        scan.nextLine(); // Consume el salto de línea pendent
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

    /**
     * Funció que gestiona les opcions relacionades amb la gestió del magatzem i les compres realitzades.
     * Permet veure productes per caducitat, consultar tiquets de compra, veure la composició tèxtil dels productes,
     * i buscar un producte per codi de barres.
     */
    private static void gestioMagatzemICompres(){
        Scanner scan = new Scanner(System.in);
        Vista.titol("MAGATZEM i COMPRES");
        Vista.msg("1) Caducitat\n2) Tiquets de compra\n3) Composiciò tèxtil\n4) Buscar nom per codi de barres\n0) Tornar");
        int f = scan.nextInt();
        if (f < 0 || f > 4)
            throw new InputMismatchException("El número introduit no es correcte (No existeixen accions amb aquest numero)");
        scan.nextLine(); // Consume el salto de línea pendent
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

