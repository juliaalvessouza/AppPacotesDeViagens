package com.example.viagens.util;

public class DiasUtil {

    public static final String PLURAL = " dias";
    public static final String SINGULAR = " dia";

    public static String formatEmTexto(int qtadeDias) {
        if (qtadeDias > 1) {
            return qtadeDias + PLURAL;
        }
        return qtadeDias + SINGULAR;
    }
}
