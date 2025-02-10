import java.util.Random;

public class RelojCompartido {
    
    //Creamos un random "r"
    static Random r = new Random();

    //Funcion syncronizada
    public static synchronized int generarTiempo(){

        //Sacamos un numero random del 5 al 12 y lo multiplicamos para pasarlo a milisegundos
        return r.nextInt(5, 13) * 1000;

    }

}
