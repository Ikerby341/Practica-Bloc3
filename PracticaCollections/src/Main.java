import Productes.Productes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Productes.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean cj = true;
        while(cj){
            Vista.msg("BENVINGUT AL SAPAMERCAT");
            Vista.titol("INICI");
            Vista.msg("1) Introduir producte\n2) Passar per caixa\n3) Mostrar carret de compra\n0) Acabar");
            int a = scan.nextInt();
            switch(a){
                case 0:
                    cj = false;
                    break;
                case 1:
                    Vista.titol("PRODUCTE");
                    Vista.msg("1) Alimentació\n2) Tèxtil\n3) Electrònica\n0) Tornar");
                    int b = scan.nextInt();
                    switch(b){
                        case 0:
                            break;
                        case 1:
                            Vista.msg("Afegir alimentació");
                            Vista.msg("Nom producte:");
                            String n = scan.next();
                            Vista.msg("Preu: ");
                            Float p = scan.nextFloat();
                            Vista.msg("Data caducitat: ");
                            LocalDate d = LocalDate.parse(scan.next());
                            Vista.msg("Codi de barres: ");
                            int c = scan.nextInt();
                            Alimentacio aliment = new Alimentacio(n,p,c,d);
                            Model.afegirAlCarret(aliment);
                            break;
                        case 2:
                            Vista.msg("Afegir tèxtil");
                            Vista.msg("Nom producte:");
                            String n1 = scan.next();
                            Vista.msg("Preu: ");
                            Float p1 = scan.nextFloat();
                            Vista.msg("Composició tèxtil: ");
                            String ct1 = scan.next();
                            Vista.msg("Codi de barres: ");
                            int c1 = scan.nextInt();
                            Textil aliment1 = new Textil(n1,p1,c1,ct1);
                            Model.afegirAlCarret(aliment1);
                            break;
                        case 3:
                            Vista.msg("Afegir tèxtil");
                            Vista.msg("Nom producte:");
                            String n2 = scan.next();
                            Vista.msg("Preu: ");
                            Float p2 = scan.nextFloat();
                            Vista.msg("Garantia (dies): ");
                            String g2 = scan.next();
                            Vista.msg("Codi de barres: ");
                            int c2 = scan.nextInt();
                            Textil aliment2 = new Textil(n2,p2,c2,g2);
                            Model.afegirAlCarret(aliment2);
                            break;
                    }
            }
        }


    }
}