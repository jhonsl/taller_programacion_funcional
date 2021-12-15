package com.example.demo;

import com.softka.model.Player;
import com.softka.utils.CsvReader;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class CSVUtilTest {

    @Test
    void converterData(){
        List<Player> lista = CsvReader.getPlayers();
        assert lista.size() == 18207;
    }

    @Test
    void filtrarJugadoresMayoresA34(){
        List<Player> lista = CsvReader.getPlayers();

        Flux<Player> flux = Flux.fromStream(lista.parallelStream()).cache();
        Mono<List<Player>> mono = flux
                .filter(player -> player.getAge() > 34)
                .collectList();

        assert mono.block().size() == 489;
    }
}
