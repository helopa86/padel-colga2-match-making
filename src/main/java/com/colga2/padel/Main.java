package com.colga2.padel;

import java.util.List;
import java.util.stream.Collectors;

import com.colga2.padel.algorithm.BalancedRandomMatchAlgorithm;
import com.colga2.padel.model.Match;
import com.colga2.padel.model.Pair;
import com.colga2.padel.model.Player;
import com.colga2.padel.service.InputValidatorService;
import com.colga2.padel.service.MatchTwinMakingService;
import com.colga2.padel.service.NamesInputValidatorService;
import com.colga2.padel.service.PairTwinMakingService;
import com.colga2.padel.service.TwinMakingService;
import com.colga2.padel.util.PadelUtils;

public class Main {

    //Inicialización de servicios
    private static TwinMakingService<Player, Pair> pairMakingService = new PairTwinMakingService();  //servicio de alineación de jugadores en parejas
    private static TwinMakingService<Pair, Match> matchMakingService = new MatchTwinMakingService(); //servicio de alineación de parejas en partidos
    private static InputValidatorService inputValidatorService = new NamesInputValidatorService();   //servicio de validación de los nombres

    public static void main(String[] args) {
        List<String> names = inputValidatorService.checkNames(args);                            //Obtiene los nombres como entradas al programa
        List<Player> players = names.stream().map(Player::new).collect(Collectors.toList());    //Construye los jugadores a través de la lista de nombres
        List<Pair> pairs = pairMakingService.getPairings(players);                              //Se emparejan los jugadores con todas las posibles parejas
        List<Match> matches = matchMakingService.getPairings(pairs);                            //Se alinean las parejas en todos los posibles partidos

        List<Match> reorderedMatches = new BalancedRandomMatchAlgorithm(players)                //Se reordenan todos los posibles partidos de tal forma que cumpla con los criterios...
                .shuffle(getFirstMatch(args,matches), matches);                                 //de entrada de jugadores que no han jugado y salida de jugadores que mas han jugado
        System.out.println("Nº partidos: "+reorderedMatches.size());                            //Muestra por consola el número de partidos totales
        System.out.println(reorderedMatches);                                                   //Muestra por consola los partidos reordenados cumpliendo los criterios
    }

    private static Match getFirstMatch(String[] args, List<Match> matches){                     //Construye el primer partido que va ir fijo en la reordenación, dependiendo del orden de entrada de los nombres al programa
        Pair pairA = PadelUtils.buildOrderedPair(args[0], args[1]);                             //Basicamente corresponde al partido de PlayMatic
        Pair pairB = PadelUtils.buildOrderedPair(args[2], args[3]);
        return matches.get(matches.indexOf(PadelUtils.buildOrderedMatch(pairA, pairB)));
    }
}
