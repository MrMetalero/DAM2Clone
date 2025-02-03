public class Handler {
    public synchronized void log(String message){
        System.out.println(message);
    }
}
