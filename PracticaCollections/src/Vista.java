public class Vista {
    /**
     * Funció senzilla que ens permet mostrar un missatge per consola passant-li un string amb el missatge que volem mostrar
     * @param txt Missatge que volem mostrar per consola en format String
     */
    protected static void msg(String txt){
        System.out.println(txt);
    }

    /**
     * Funció que ens serveix per mostrar per consola títols d'apartats, com per exemple "INICI"
     * @param txt Nom del títol en format String el qual es mostrarà per consola
     */
    protected static void titol(String txt){
        String a = "";
        for (int i = 0; i < (txt.length() + 7); i++) {
            a += "-";
        }
        System.out.println(a + "\n-- " + txt + " ---\n" + a);
    }
}
