package com.gojek.pl.main;

/**
 * some basic utility methods
 *
 * @author tckb
 */
public abstract class Utils {

    public static void checkNotNull(Object data, String name) {
        if (data == null) { fail(name + " not null");}
    }

    public static void checkNotEmpty(Object data, String name) {
        if (data.toString().isEmpty()) { fail(name + " not empty");}
    }

    public static void checkMandatory(Object data, String name) {
        checkNotNull(data, name);
        checkNotEmpty(data, name);
    }


    private static void fail(final String condition) {
        throw new IllegalArgumentException("precondition " + condition + " - failed!");
    }

}
