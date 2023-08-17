package excel_to_message;

public class Utils {
    public static void check(final boolean condition, final String message){
        if (not(condition))
            throw new XLSXReadWriter.XLSXReadWriterException(message);
    }

    public static boolean not(final boolean condition) {
        return !condition;
    }

}
