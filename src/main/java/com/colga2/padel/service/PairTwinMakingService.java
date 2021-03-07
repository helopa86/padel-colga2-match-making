package com.colga2.padel.service;

import com.colga2.padel.model.Pair;
import com.colga2.padel.model.Player;

/**
 * Conversión de jugadores a posibles parejas
 */
public class PairTwinMakingService extends AbstractTwinMakingService<Player, Pair> {

    @Override
    protected Pair instanceOutput(Player inputA, Player inputB) {
        return new Pair(inputA, inputB);
    }

    @Override
    protected boolean areRepeated(Player inputA, Player inputB) {
        return false;
    }
}
