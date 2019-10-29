package com.kabanov.generating_tool;

/**
 * @author Kabanov Alexey
 */
public class StringsGenerator {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";

    public String generateRandomString(int size) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++) {
            int index = (int) (ALPHA_NUMERIC_STRING.length() * Math.random());
            sb.append(ALPHA_NUMERIC_STRING.charAt(index));
        }

        return sb.toString();
    }
}
