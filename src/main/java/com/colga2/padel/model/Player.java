package com.colga2.padel.model;

import java.util.Objects;

public class Player {

    private String name; //Nombre del jugador
    private Integer timesPlayedInaRow; //Contabiliza las partidos seguidos jugados por un jugador

    public Player(String name) {
        this.name = name;
        timesPlayedInaRow=0;
    }

    public String getName() {
        return name;
    }

    public Integer getTimesPlayedInaRow() {
        return timesPlayedInaRow;
    }

    public void play(){
        timesPlayedInaRow++;
    } //Al jugar se suma un partido seguido mas jugando

    public void rest(){
        timesPlayedInaRow = 0;
    } //Al descansar se pone el contador de partidos seguidos a cero

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
