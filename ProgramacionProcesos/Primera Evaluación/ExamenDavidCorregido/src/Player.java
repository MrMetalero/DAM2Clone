import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Player implements Runnable{
    String name;
    Double time;
    int rondasGanadas = 0;
    private Handler handler;
    private CyclicBarrier barrera;

    public Player(String name, Handler clock, CyclicBarrier barrier) {
        this.name = name;
        this.handler = clock;
        this.barrera = barrier;
    }

    @Override
    public void run() {
        try {
            while (handler.roundNum < 11){
                barrera.await();
                play();
                barrera.await();
                barrera.await();
            }
            Thread.currentThread().join();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    private void play(){
        time = handler.calcularTiempo();
        try {
            Thread.sleep((long) (time*1000));
            System.out.println(name + " esperando...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
