package com.colga2.padel.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstracción para obtener la lista total de parejas y la lista total de partidos
 * @param <Input>
 * @param <Output>
 */
public abstract class AbstractTwinMakingService<Input, Output> implements TwinMakingService<Input,Output>{

    protected abstract Output instanceOutput(Input inputA, Input inputB);
    protected abstract boolean areRepeated(Input inputA, Input inputB);

    /**
     * Recorre la lista de entradas con dos bucles, el segundo bucle
     * va un slot adelantado al primero para ir realizando combinaciones de esa lista de entradas
     * @param inputs lista de entradas, pueden ser jugadores o parejas
     * @return lista de salidas
     */
    @Override
    public List<Output> getPairings(List<Input> inputs) {
        List<Output> outputs = new ArrayList<>();
        for (int i = 0; i < inputs.size() - 1; i++) {
            for (int j = i + 1; j < inputs.size(); j++) {
                if(!areRepeated(inputs.get(i), inputs.get(j))){                     //Si la combinación de la entrada 1 con la entrada 2 ya está repetida descartamos su agregación a la lista de salidas...
                    outputs.add(instanceOutput(inputs.get(i), inputs.get(j)));
                }
            }
        }
        return outputs;
    }
}
