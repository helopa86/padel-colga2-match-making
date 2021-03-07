package com.colga2.padel.algorithm;

import java.util.List;

import com.colga2.padel.model.Match;

@FunctionalInterface
public interface RandomMatchAlgorithm {

    /**
     * Reordena los partidos para cumplir con las reglas de entrada y salida de los jugadores
     *
     * @param firstMatch es el primer partido que aparecerá en la lista de emparejamientos
     * @param matches total de partidos resultado de la combinación de los jugadores, es la lista de partidos que vamos a reordenar
     * @return partidos reordenados cumpliendo los criterios
     */
    List<Match> shuffle(Match firstMatch, List<Match> matches);
}
