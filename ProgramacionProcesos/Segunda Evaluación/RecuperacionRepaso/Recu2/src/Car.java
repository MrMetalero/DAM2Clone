import java.util.concurrent.Semaphore;

public class Car implements Runnable {

    private int id;
    private Semaphore parkingLot;
    private Handler handler;

    public Car(int id, Semaphore parkingLot, Handler handler){
        this.id = id;
        this.parkingLot = parkingLot;
        this.handler = handler;
    }


    @Override
    public void run() {
        try {

            handler.log("Car " + id + "is looking for parking...");
            parkingLot.acquire(); // Espera a que se libere el semáforo para actuar
            handler.log("Car " + id + " has parked.");

            Thread.sleep((long) (Math.random() * 5000));   

            handler.log("Car " + id + " is leaving.");
            parkingLot.release();

        } catch (InterruptedException e) {
            e.printStackTrace();

        }
   

    }
    

    

}
