package com.colga2.padel.model;

import java.util.Objects;

import com.colga2.padel.util.Constants;

public class Pair {
    private String id; //Identificador compuesto por el nombre del jugadorA-jugadorB
    private Player playerA; //Jugador A
    private Player playerB; // Jugador B
    private Integer localCount; //Veces que la pareja ha jugado como local
    private Integer visitorCount; //Veces que la pareja ha jugado como visitante

    public Pair(Player playerA, Player playerB) {
        this.id = playerA.getName()+Constants.NAME_SEPARATOR+playerB.getName();
        this.playerA = playerA;
        this.playerB = playerB;
        localCount = 0;
        visitorCount = 0;
    }

    public String getId() {
        return id;
    }

    public Player getPlayerA() {
        return playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public Integer getLocalCount() {
        return localCount;
    }

    public Integer getVisitorCount() {
        return visitorCount;
    }

    public void play(boolean asLocal){
        if(asLocal){ //juega como local
            localCount++;    //se incrementa el contador de juego local
        }else{
            visitorCount++; //se incrementa el contador de juego visitante
        }
        playerA.play();    //Juega el jugador A
        playerB.play();    //Juega el jugador B
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        return Objects.equals(id, pair.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return id;
    }
}
