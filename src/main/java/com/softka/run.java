package com.softka;

import com.softka.utils.Options;

/**
 * Ademas de los test, se implemento esta parte en la cual se podra probar
 * cada una de las filtraciones posibles
 */
public class run {

    public static void main(String[] args) {
        //Importamos las opciones disponibles que seran 3
        Options opciones = new Options();

        /**
         * Filtramos los jugadores mayores a la edad que deseemos, en este
         * caso seran los mayores a 34
         */
        opciones.jugadoresMayoresA(34);

        /**
         * Filtramos los jugadores del equipo que deseemos, teniendo en cuenta
         * que se debe de colocar el nombre del equipo tal cual esta en el archivo csv
         */
        opciones.jugadoresDel("FC Barcelona");

        //Visualisamos el ranking de las naciones
        System.out.println("Rankings");
        opciones.rankings();
    }

}
