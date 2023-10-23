package com.elsevier.bts.regional.ecom.framework.utilities;

public class JavaUtil {

    public static String convertToSingular(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        if (word.endsWith("s")) {
            return word.substring(0, word.length() - 1);
        } else {
            return word;
        }

    }
}
