package com.colga2.padel.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.colga2.padel.exception.PadelMatchingException;
import static com.colga2.padel.util.Constants.VALID_NAMES;

public class NamesInputValidatorService implements InputValidatorService {

    /**
     *
     * @param args lista de nombres separadas por espacios
     * @return lista de nombres ordenada alfabéticamente
     */
    @Override
    public List<String> checkNames(String[] args) {
        if(args.length < 5 || args.length > 6){ //Solo admitimos 5 o 6 jugadores
            throw new PadelMatchingException("El numero de jugadores no es correcto, tienen que introducirse entre 5 y 6 incluidos");
        }
        for (String name:args) {
            if(!VALID_NAMES.contains(name)){  //Tienen que ser nombres válidos, (solo los colga2 team)
                throw new PadelMatchingException("El nombre no es valido");
            }
        }

        List<String> names = new ArrayList<>(Arrays.asList(args));  //convierte el array a lista
        List<String> upperCaseNames = names.stream().map(String::toUpperCase).collect(Collectors.toList());
        if(upperCaseNames.size() != new HashSet<>(upperCaseNames).size()){ //Comprueba que no haya ningún nombre repetido
            throw new PadelMatchingException("Todos los jugadores deben de tener nombres distintos");
        }
        Collections.sort(names); //reordena alfabeticamente los nombres
        return names; //Los devuelve
    }
}
