import java.util.ArrayList;

public class CompruebaGanadores {
    
    //Objeto compartido que comprueba cual es el mayor numero de los obtenidos
    public static synchronized void comprobarGanador(ArrayList<Jugador> jugadores) {

        //Jugador en el que guardaremos el jugador ganador de cada ronda
        Jugador ganador = null;

        //Guardaremos el tiempo mayor
        int tiempoMayor = 0;

        //Recorremos por cada jugador en jugadores que es el arrayJugadores
        for(Jugador jugadorActual : jugadores){

            //Pasamos el tiempo del jugador actual a segundos
            int tiempoSegundos = jugadorActual.tiempo / 1000;

            //Solo lo cuenta si es menor o igual a 10
            if (tiempoSegundos <= 10) {

                //Comprueba si el tiempo es mayor al que tiene guardado
                if (tiempoSegundos > tiempoMayor) {
                    
                    //Guardamos como nuevo tiempo mayor el del jugadorActual
                    tiempoMayor = tiempoSegundos;
                    
                    //Guardamos ese jugador como el ganador
                    ganador = jugadorActual;

                }

            }

        }

        //Al acabar la comprobacion de los 6 jugadores
        //Mostramos el nombre del jugador ganador y el numero con el que ha ganado
        System.out.println(ganador.nombre + " ha ganado la ronda con " + (ganador.tiempo/1000) + " segundos");
        
        //Aumentamos su contador de victorias
        ganador.victorias++;

    }

}


/*  ESTA FUNCIONA
import java.util.ArrayList;

public class CompruebaGanadores {
    
    public static synchronized void comprobarGanador(ArrayList<Jugador> jugadores) {
        Jugador ganador = null;
        int maxTiempoValido = 0;  // En segundos

        for(Jugador jugadorActual : jugadores) {
            int tiempoSegundos = jugadorActual.tiempo / 1000;
            
            // Verificamos si no se pasó de 10 segundos
            if (tiempoSegundos <= 10) {
                // Comparamos contra el máximo actual
                if (tiempoSegundos > maxTiempoValido) {
                    maxTiempoValido = tiempoSegundos;
                    ganador = jugadorActual;
                }
            }
        }

        if (ganador != null) {
            System.out.println(ganador.nombre + " ha ganado la ronda con " 
                             + (ganador.tiempo/1000) + " segundos");
            ganador.victorias++;
        } else {
            System.out.println("Ningún jugador válido esta ronda!");
        }
    }
}
*/