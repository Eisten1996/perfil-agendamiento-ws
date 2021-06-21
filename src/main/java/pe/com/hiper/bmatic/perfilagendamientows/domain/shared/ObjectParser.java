package pe.com.hiper.bmatic.perfilagendamientows.domain.shared;


public class ObjectParser {


    public static String toStringWithoutTrim(final Object o) {
        return o == null ? null : o.toString();
    }

    public static String toString(final Object o) {
        return o == null ? null : o.toString().trim();
    }

    public static int toInt(final Object o) {
        return o == null ? 0 : Integer.valueOf(o.toString());
    }

    public static boolean toBoolean(final Object o) {
        return o == null ? false : o.toString().equals("1");
    }

    public static float toFloat(final Object o) {
        return o == null ? 0 : Float.valueOf(o.toString());
    }

    public static double toDouble(final Object o) {
        return o == null ? 0 : Double.valueOf(o.toString());
    }

}
