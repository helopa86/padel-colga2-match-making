package com.colga2.padel.exception;

/**
 * Excepción, por si algo va mal... se para el programa
 */
public class PadelMatchingException extends RuntimeException {

    public PadelMatchingException(String s) {
        super(s);
    }
}
