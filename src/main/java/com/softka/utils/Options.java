package com.softka.utils;

import com.softka.model.Player;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Para poder evidenciar la correcta implementacion de los temas vistos,
 * se implementara esta clase que lo que hara es imprimir en consola los resultados
 * de las filtraciones, permitiendo asi tener una mejor vista de los resultados
 */
public class Options {

    //Obtenemos las funciones que contienen toda la logica
    Functions funciones = new Functions();

    //imprimimos los jugadores del equipo que deseemos
    public void jugadoresMayoresA(int edad){
        Mono<List<Player>> jugadores = funciones.filtrarPorEdad(edad);
        jugadores.subscribe(System.out::println);
    }

    //imprimimos los jugadores mayores a la respectiva edad
    public void jugadoresDel(String equipo){
        Mono<List<Player>> jugadores = funciones.filtrarPorEquipo(equipo);
        jugadores.subscribe(System.out::println);
    }

    //imprimimos el ranking de cada nacionalidad
    public void rankings(){
        funciones.rankginSegunNacionalidad();
    }
}
