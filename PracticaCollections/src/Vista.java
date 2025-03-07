public class Vista {
    protected static void msg(String txt){
        System.out.println(txt);
    }
    protected static void titol(String txt){
        String a = "";
        for (int i = 0; i < (txt.length() + 7); i++) {
            a += "-";
        }
        System.out.println(a + "\n-- " + txt + " ---\n" + a);
    }
}
