import java.util.Scanner;

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

                            break;
                        case 2:

                            break;
                        case 3:

                            break;
                    }
            }
        }


    }
}