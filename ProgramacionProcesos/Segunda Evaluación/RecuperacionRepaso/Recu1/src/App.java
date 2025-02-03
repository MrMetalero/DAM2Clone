import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws Exception {

        

        

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<Void> task1 = () -> {
            ejecutarProceso(new ProcessBuilder("ping","gogle.com","-c", "3"));
                        return null;
                    };
            
        Callable<Void> task2 = () -> {
            ejecutarProceso(new ProcessBuilder("echo", "Proceso completado"));
            return null;
        };

        executor.invokeAll(List.of(task1,task2));
        executor.shutdown();
        


    }
        
            
    private static void ejecutarProceso(ProcessBuilder processBuilder) {
        try{
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }catch(Exception e){

        }

        

    }
}
