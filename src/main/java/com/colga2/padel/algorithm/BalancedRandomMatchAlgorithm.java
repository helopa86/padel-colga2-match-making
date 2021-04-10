package com.colga2.padel.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.colga2.padel.exception.PadelMatchingException;
import com.colga2.padel.model.Match;
import com.colga2.padel.model.Pair;
import com.colga2.padel.model.Player;
import com.colga2.padel.util.PadelUtils;

public class BalancedRandomMatchAlgorithm implements RandomMatchAlgorithm {

    private List<Player> players; //Jugadores cargados desde los argumentos de entrada al programa.
    private List<Pair> pairs; //Parejas cargadas con la combinación de jugadores

    public BalancedRandomMatchAlgorithm(List<Player> players, List<Pair> pairs) {
        this.players = players;
        this.pairs = pairs;
    }

    /**
     *
     * @param firstMatch es el primer partido que aparecerá en la lista de emparejamientos
     * @param matches total de partidos resultado de la combinación de los jugadores, es la lista de partidos que vamos a reordenar
     * @return partidos reordenados cumpliendo los criterios
     */
    @Override
    public List<Match> shuffle(Match firstMatch, List<Match> matches) {
        int totalMatches = matches.size();                          //Número total de partidos
        List<Match> reorderedMatches = new ArrayList<>();           //Partidos reordenados, es la salida de la función
        reorderedMatches.add(firstMatch);                           //Se añade el primer partido a la reordenación

        randomizeMatches(firstMatch,matches);                       //se elimina el partido añadido de la lista original y se reordenan todos los partidos de forma aleatoria
        firstMatch.play();                                          //Jugamos el primer partido

        while(reorderedMatches.size() != totalMatches) {            //mientras la lista de partidos reordenados no se corresponda con el tamaño de la lista de partidos original...
            Match lastPlayedMatch = nextCandidateMatch(matches);    //Buscamos el mejor partido candidato para ser el siguiente
            matches.remove(lastPlayedMatch);                        //Eliminamos ese partido de la lista original
            reorderedMatches.add(lastPlayedMatch);                  //Lo añadimos a la lista reordenada

            lastPlayedMatch.play();                                 //Jugamos el partido
            players.stream().filter(player ->
                    !PadelUtils.isPlayerInMatch(player, lastPlayedMatch))
                    .forEach(Player::rest);                         //Los jugadores que NO han jugado el último partido descansan
            pairs.stream().filter(pair ->
                    !PadelUtils.isPairInMatch(pair,lastPlayedMatch))
                    .forEach(Pair::rest);                           //Las parejas que no han jugado el partido descansan
        }
        return reorderedMatches;  //Devuelve la lista resultante de los partidos ordenados
    }

    /**
     *
     * @param newPlayers  nuevos jugadores que no jugaron el último partido y que les toca entrar
     * @param candidateMatch partido candidato
     * @return si están los jugadores que no jugaron el anterior partido en el posible partido candidato a ser elegido el siguiente
     */
    private boolean areNewPlayersInMatch(List<Player> newPlayers, Match candidateMatch){
        return newPlayers.stream()
                .allMatch(player ->
                        PadelUtils.isPlayerInMatch(player, candidateMatch));
    }

    private Match nextCandidateMatch(List<Match> matches){
        List<Player> newPlayers = players.stream().filter(player ->
                player.getTimesPlayedInaRow() == 0).collect(Collectors.toList());                   //Escogemos a los jugadores que NO han jugado el último partido

        List<Match> candidateMatchList = matches.stream()                                           //De entre todos los partidos candidatos...
                .filter(match -> areNewPlayersInMatch(newPlayers, match))                           //Filtramos todos aquellos partidos que tengan a los jugadores que no jugaron el último partido
                .sorted(Comparator.comparingInt(Match::getTotalTimesPlayedInaRow))                  //Se reordenan los partidos colocando al principio los de aquellos jugadores que hayan jugado MENOS partidos seguidos
                .collect(Collectors.toList());

        if(candidateMatchList.size() == 0){                                                         //Si no pueden entrar los jugadores que no han jugado el último partido...
            return matches.stream().min(Comparator.comparingInt(Match::getTotalTimesPlayedInaRow)). //Se escoge que jueguen aquellos jugadores que llevan menos partidos seguidos jugados...
                    orElseThrow(() -> new PadelMatchingException("No se obtuvo ningún candidato"));
        }
        return candidateMatchList.stream()
                .filter(match -> !match.getLocalPair().getLastPlayed() && !match.getVisitorPair().getLastPlayed())
                .findFirst()                                                                        //Intentamos que no juegue la misma pareja que jugó en el anterior partido
                .orElse(candidateMatchList.get(0));                                                 //Si no hay mas remedio elegimos el primer partido
    }


    private void randomizeMatches(Match firstMatch, List<Match> matches){
        if(!matches.remove(firstMatch)){                                                                      //Elimina el primer partido
            throw new PadelMatchingException("No se pudo eliminar el partido '"+firstMatch.toString()+"'");   //Si no puede excepción
        }
        Collections.shuffle(matches);                                                                         //Se reorganizan aleatoriamente todos los partidos
    }


}
