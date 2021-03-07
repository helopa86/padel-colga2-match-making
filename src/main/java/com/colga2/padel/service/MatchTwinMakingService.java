package com.colga2.padel.service;

import com.colga2.padel.model.Match;
import com.colga2.padel.model.Pair;

/**
 * Conversión de parejas a partidos
 */
public class MatchTwinMakingService extends AbstractTwinMakingService<Pair, Match> {

    @Override
    protected Match instanceOutput(Pair inputA, Pair inputB) {
        return new Match(inputA, inputB);
    }

    @Override
    protected boolean areRepeated(Pair pairA, Pair pairB) {
        String playerAA = pairA.getPlayerA().getName();
        String playerAB = pairA.getPlayerB().getName();
        String playerBA = pairB.getPlayerA().getName();
        String playerBB = pairB.getPlayerB().getName();
        return playerAA.equals(playerBA) || playerAA.equals(playerBB) ||
                playerAB.equals(playerBA) || playerAB.equals(playerBB);

    }
}
