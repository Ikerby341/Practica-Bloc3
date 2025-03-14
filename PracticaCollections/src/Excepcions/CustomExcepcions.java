package Excepcions;

public class CustomExcepcions {

    public static class LimitProductesException extends Exception {
        public LimitProductesException(String message) {
            super(message);
        }
    }

    public static class DataCaducitatException extends Exception {
        public DataCaducitatException(String message) {
            super(message);
        }
    }

    public static class NegatiuException extends Exception {
        public NegatiuException(String message) {
            super(message);
        }
    }

    public static class LimitCaracteresException extends Exception {
        public LimitCaracteresException(String message) {
            super(message);
        }
    }

    public static class EnumFailException extends Exception {
        public EnumFailException(String message) {
            super(message);
        }
    }
}
