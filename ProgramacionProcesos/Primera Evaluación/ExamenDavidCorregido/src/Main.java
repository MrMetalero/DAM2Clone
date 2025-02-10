import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        final int NUM_PLAYERS = 6;
        Thread[] hilos = new Thread[NUM_PLAYERS];
        CyclicBarrier cb = new CyclicBarrier(NUM_PLAYERS+1);
        Handler handler = new Handler(cb);
        ArrayList<Player> players = new ArrayList<>();
        
        for (int i = 0; i < NUM_PLAYERS; i++){
           players.add(new Player("Jugador " + i, handler, cb));
        }
        handler.players = players;

        for (int i = 0; i < NUM_PLAYERS; i++){
            hilos[i] = new Thread(players.get(i));
        }

        Thread hiloReloj = new Thread(handler);
        hiloReloj.start();

        for (int i = 0; i < NUM_PLAYERS; i++){
            hilos[i].start();
        }
    }
}