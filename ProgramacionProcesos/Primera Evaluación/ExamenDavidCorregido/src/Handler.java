import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Handler implements Runnable {
    ArrayList<Player> players;
    private CyclicBarrier cb;
    public int roundNum = 1;

    public Handler(CyclicBarrier cb) {
        this.cb = cb;
    }

    @Override
    public void run() {
        try {
            while (roundNum < 11) {
                System.out.println("Ronda " + roundNum + ":");
                cb.await();
                cb.await();
                calcularGanador();
                roundNum++;
                cb.await();
            }
            System.out.println("Se ha terminado el juego");
            System.out.println("Lista de ganadores");
            for (Player player : players) {
                System.out.println(player.name + " ha ganado " + 
                				player.rondasGanadas + " rondas");
            }
            Thread.currentThread().join();

        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    private void calcularGanador() {
        Player winner = null;
        Double bestTime = 0.0;
        for (Player player : players) {
            if (player.time > bestTime 
            		&& player.time <= 10.00) {
            	winner = player;
                bestTime = winner.time;
            }
        }
        for (Player player : players) {
            if (winner.name.equals(player.name)) {
                player.rondasGanadas++;
            }
        }
        if (bestTime == 0.00)
            System.out.println("Todos se han pasado, nadie gana!");
        else
            System.out.println("El ganador de la ronda es " + winner.name + " con un tiempo de " + winner.time + " segundos!!");
    }

    public synchronized double calcularTiempo() {
        double tiempo;
        Random rnd = new Random();
        tiempo = (double) (rnd.nextInt(700) + 500) / 100;
        return tiempo;
    }
}
