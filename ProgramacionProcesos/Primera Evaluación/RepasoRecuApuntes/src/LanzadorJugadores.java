import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class LanzadorJugadores {

    //Creamos el array de hilos
    public static Thread [] hilos;

    //Creamos el array de jugadores
    public static ArrayList<Jugador> arrayJugadores = new ArrayList<>();

    //Bariable con el numero de jugadores a crear
    private static int NUM_JUGADORES = 6;
    
    //Creamos una barrera que esperera a 6 hilos
    public static CyclicBarrier todosListos = new CyclicBarrier(
        NUM_JUGADORES,
        //Cuando todos llegan a la barrera ejecuta comprobarGanador de CompruebaGanadores pasandole el array de Jugadores
        () -> {
            CompruebaGanadores.comprobarGanador(arrayJugadores);
        }
    
    );
    
        public static void main(String[] args) throws Exception {
            
            //Creamos un array para 6 hilos
            hilos = new Thread[NUM_JUGADORES];
    
            //Hara el proceso 6 veces
            for(int i = 0; i < NUM_JUGADORES; i++){
    
                //Creamos un jugador nuevo pasandole la barrera
                Jugador jugador = new Jugador(todosListos);

                //Metemos el jugador al array de jugadores
                arrayJugadores.add(jugador);
            
                //Creamos un hilo para jugador
                Thread jThread = new Thread(jugador);

                //Le ponemos nombre al hilo de cada jugador
                jThread.setName("jugador " + i);

                //Añadimos el hilo al array de hilos
                hilos[i] = jThread;

                //Iniciamos el hilo
                jThread.start();

            }

            //Espera o comprueba que todos los hilos se cierren
            for(Thread hiloJugador : hilos){
                hiloJugador.join();
            }

            System.out.println("\nResultados finales");
            //Muestra por cada jugador del array el numero de victorias que ha tenido "RESULTADOS FINALES"
            for(Jugador jugador : arrayJugadores){
                //Mostramos el nombre del jugador y el numero de victorias
                System.out.println(jugador.nombre + ": " + jugador.victorias + " victorias");

            }

    }
}
