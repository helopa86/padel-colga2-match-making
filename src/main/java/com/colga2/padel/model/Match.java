package com.colga2.padel.model;

import java.util.Objects;

import com.colga2.padel.util.Constants;

public class Match {

    private String id;     //Identificador del partido está compuesto por por los identificadores de las parejas separados por una almohadilla '#'
    private Pair localPair;  //pareja local
    private Pair visitorPair; //pareja visitante

    public Match(Pair localPair, Pair visitorPair) {
        this.id = localPair.getId()+Constants.PAIR_SEPARATOR+visitorPair.getId();
        this.localPair = localPair;
        this.visitorPair = visitorPair;
    }

    public Pair getLocalPair() {
        return localPair;
    }

    public Pair getVisitorPair() {
        return visitorPair;
    }

    public void play(){ //Se juega el partido...
        if(localPair.getLocalCount().equals(visitorPair.getLocalCount())){   //Si las dos parejas han jugado las mismas veces de local..
            if(localPair.getVisitorCount() < visitorPair.getVisitorCount()){ //y la pareja local ha jugado menos veces como visitante que la pareja visitante
                swapPartners();                                              //cambiamos las parejas local por visitante
            }
        }else if(visitorPair.getLocalCount() < localPair.getLocalCount()){   //si la pareja visitante ha jugado menos veces como local que la pareja local..
            swapPartners();                                                  //cambiamos la parejas local por visitante
        }
        localPair.play(true);                                        //juega la pareja local como local
        visitorPair.play(false);                                     //juega la pareja visitante como visitante
    }

    /**
     *
     * @return devuelve la suma de todos los partidos seguidos que llevan los jugadores
     */
    public int getTotalTimesPlayedInaRow(){
        return localPair.getPlayerA().getTimesPlayedInaRow()+
                localPair.getPlayerB().getTimesPlayedInaRow()+
                visitorPair.getPlayerA().getTimesPlayedInaRow()+
                visitorPair.getPlayerB().getTimesPlayedInaRow();
    }

    /**
     * Intercambia las parejas, para equilibrar las veces que una pareja juega de local con la de visitante
     */
    private void swapPartners(){
        Pair backupLocalPair = this.localPair;
        this.localPair = this.visitorPair;
        this.visitorPair = backupLocalPair;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        return Objects.equals(id, match.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return localPair.getId()+Constants.PAIR_SEPARATOR+visitorPair.getId();
    }
}
