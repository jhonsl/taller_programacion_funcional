package com.softka.utils;

import com.opencsv.CSVReader;
import com.softka.model.Player;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Se crea un lector de csv que permitira crear objetos a partir de la informacion suministrada
 * en dicho archivo
 */
public class CsvReader {

    //establecemos que cada salto de linea hay una siguiente lecura
    public static final char SEPARATOR='\n';

    public static List<Player> getPlayers(){
        CSVReader reader = null;
        List<Player> lista = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader("src/main/resources/data.csv"),SEPARATOR);
            List<String[]> registers = reader.readAll();

            //a partir de cada linea, creamos un array que va a contener todos los atributos de cada jugador
            registers = registers.stream().map(x -> Arrays.toString(x)
                                                .replace("[    ","")
                                                .split(",")).collect(Collectors.toList());

            //convertimos cada linea en un objeto tipo player, casteando los respectivos valores
            registers.forEach(strings -> lista.add(new Player(
                    Integer.parseInt(strings[0]),
                    strings[1],
                    Integer.parseInt(Optional.of(strings[2]).filter(h -> !h.isBlank()).orElse("0")),
                    strings[3],
                    strings[4],
                    Integer.parseInt(strings[5]),
                    Integer.parseInt(strings[6]),
                    strings[7]
            )));

            //cerramos el flujo y devolvemos la lista
            reader.close();
            return lista;

        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
