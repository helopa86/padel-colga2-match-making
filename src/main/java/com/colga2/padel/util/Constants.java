package com.colga2.padel.util;

import java.util.Arrays;
import java.util.List;

public final class Constants {

    public static final String NAME_SEPARATOR="-";
    public static final String PAIR_SEPARATOR="#";
    public static final List<String> VALID_NAMES = Arrays.asList("Andres", "Garcho", "Hector", "Huertas", "Jorge", "Lax");

    private Constants(){
        throw new IllegalArgumentException("Cannot instance constants class");
    }
}
