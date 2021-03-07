package com.colga2.padel.service;

import java.util.List;

@FunctionalInterface
public interface TwinMakingService<Input, Output> {
    /**
     * Convierte una lista de jugadores en una combinación de parejas
     * Convierte una lista de parejas en una combinación de partidos
     *
     * @param inputs lista de entradas, pueden ser jugadores o parejas
     * @return lista de salidas, pueden ser parejas o partidos
     */
    List<Output> getPairings(List<Input> inputs);
}
