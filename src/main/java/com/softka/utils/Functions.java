package com.softka.utils;

import com.softka.model.Player;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**En esta clase vamos a tener toda la logica de la implementacion de la programacion reactiva
 * devolviendo toda la informacion en un mono
 */
public class Functions {

    //Obtenemos la informacion de todos los jugadores
    List<Player> lista = CsvReader.getPlayers();

    //Filtramos los jugadores mayores a 35 años ordenados segun su edad
    public Mono<List<Player>> filtrarPorEdad(int edad){
        Flux<Player> flux = Flux.fromStream(lista.parallelStream()).cache();
        return flux.filter(player -> player.getAge() > edad)
                .sort(Comparator.comparing(Player::getAge))
                .collectList();
    }

    //Filtrar los jugadores por equipo y ordenados por orden alfabetico
    public Mono<List<Player>> filtrarPorEquipo(String equipo){
        Flux<Player> flux = Flux.fromStream(lista.parallelStream()).cache();
        return flux.filter(player -> player.getClub().equals(equipo))
                .sort(Comparator.comparing(Player::getName))
                .collectList();
    }

    //Creacion de la lista de nacionalidades y de los rankings por nacion
    //como en esta parte solo nos pedian visualizar los resultados, no se devolvera un mono
    public void rankginSegunNacionalidad(){
        //Obtenemos la lista de las nacionalidades
        List<String> paises = new ArrayList<>();
        Flux<Player> flux = Flux.fromIterable(lista);

        //hallamos en tiempo real las nacionalidades que existen
        flux.map(Player::getNational)
                .distinct()
                .collectSortedList()
                .subscribe(paises::addAll);

        //calculamos cuantos jugadores hay por nacion y mostramos la informacion
        paises.forEach(pais -> flux
                .filter(player -> player.getNational().equals(pais))
                .reduce(0,(a,c) -> a+1)
                .subscribe(x -> System.out.println("jugadores de " + pais + " = " + x)));
    }
}
