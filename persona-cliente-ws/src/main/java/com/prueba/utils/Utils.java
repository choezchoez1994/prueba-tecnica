package com.prueba.utils;

import java.util.Collection;

public class Utils {

    public static boolean isEmpty(Collection l) {
        if (l == null) {
            return true;
        } else return l.size() == 0;
    }

    public static boolean isNotEmpty(Collection l) {
        return !Utils.isEmpty(l);
    }
}
