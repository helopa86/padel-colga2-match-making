package com.colga2.padel.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.colga2.padel.model.Match;
import com.colga2.padel.model.Pair;
import com.colga2.padel.model.Player;

public class PadelUtils {

    /**
     * Construye una pareja a partir de dos jugadores, siempre los ordena
     * @param playerA jugador A
     * @param playerB jugador B
     * @return devuelve la pareja
     */
    public static Pair buildOrderedPair(String playerA, String playerB){
        List<String> pair = Arrays.asList(playerA, playerB);
        Collections.sort(pair);
        return new Pair(new Player(pair.get(0)), new Player(pair.get(1)));
    }

    /**
     * Construye un partido a través de las parejas, siempre ordena las parejas.
     * @param pairA pareja A
     * @param pairB pareja B
     * @return devuelve el partido
     */

    public static Match buildOrderedMatch(Pair pairA, Pair pairB){
        if(pairA.getId().charAt(0) < pairB.getId().charAt(0)){           //Por defecto la pareja local será aquella que tenga el orden alfabetico
            return new Match(pairA, pairB);
        }else if(pairA.getId().charAt(0) > pairB.getId().charAt(0)){
            return new Match(pairB,pairA);
        }else{
            if(pairA.getId().charAt(1) < pairB.getId().charAt(1)){ //Si el primer caracter coincide miramos el segundo, por ejemplo He Hu
                return new Match(pairA, pairB);
            }else{
                return new Match(pairB,pairA);
            }
        }
    }
    public static boolean isPlayerInMatch(Player player, Match candidateMatch){ //Mira a ver si un jugador está dentro del partido a elegir
        return candidateMatch.getLocalPair().getPlayerA().equals(player) ||
                candidateMatch.getLocalPair().getPlayerB().equals(player) ||
                candidateMatch.getVisitorPair().getPlayerA().equals(player) ||
                candidateMatch.getVisitorPair().getPlayerB().equals(player);
    }
}
