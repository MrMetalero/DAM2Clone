import java.util.concurrent.CyclicBarrier;

public class Jugador implements Runnable {
    
    //Creamos una barrera
    public CyclicBarrier todosListos;

    //Nombre del jugador
    public String nombre;

    //Creamos un contador que ira sumando hasta llegar al numero de rondas requeridas
    public int rondas = 0;

    //Tiempo que sacara del relojCompartido
    public int tiempo = 0;

    //Numero de victorias
    public int victorias = 0;

    //Constructor de usuarios a los que se le iguala la barrera de Jugadores a la del Lanzador
    public Jugador(CyclicBarrier todosListos){
        
        this.todosListos = todosListos;

    }

    @Override
    public void run() {

            //Guardamos como nombre el nombre del hilo
            nombre = Thread.currentThread().getName();
 
            //Hacemos 10 rondas
            for(int i = 0; i < 10; i++){

                try {
                
                    //Guardamos en tiempo el numero generado
                    tiempo = RelojCompartido.generarTiempo();
    
                    //Mostramos el nombre del hilo en ejecucion y el tiempo que va a dormir pasandolo a segundos (tiempo*1000)
                    System.out.println(nombre + "duerme: " + (tiempo/1000) + "segundos");
    
                    //El hilo duerme esos segundos
                    Thread.sleep(tiempo);
    
                    //Indicamos que la barrera espere a los 6 hilos antes de continuar
                    todosListos.await();
    
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            

    }

}
