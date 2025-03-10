import Productes.Productes;

import java.util.ArrayDeque;
import java.util.Queue;

public class Model {
    private static Queue<Productes> carret = new ArrayDeque<>();
    protected static void afegirAlCarret(Productes p){
        carret.add(p);
    }
}
