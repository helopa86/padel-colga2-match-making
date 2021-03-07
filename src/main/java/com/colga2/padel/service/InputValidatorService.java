package com.colga2.padel.service;

import java.util.List;

@FunctionalInterface
public interface InputValidatorService {

    /**
     * Comprueba que la lista de nombres introducidos al programa es la correcta, tanto en número como en forma
     * @param args lista de nombres separadas por espacios
     * @return devuelve la lista de nombres validada y ordenada alfabéticamente
     */

    List<String> checkNames(String[] args);
}
