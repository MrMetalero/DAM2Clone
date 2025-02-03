import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class App {
    public static void main(String[] args) throws Exception {

        Semaphore parkingLot = new Semaphore(3);
        Handler handler = new Handler();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 1; i <= 10; i++) {
            executor.execute(new Car(i,parkingLot,handler));
        }






    }



}
