package concurrencia;

public class HilosConcurrentes {
    private Buffer buffer;
    private final Trabajador[] trabajadores;
    private int numTrabajadores;
    private final ContadorTrabajadores contadorTrabajadores;

    public HilosConcurrentes(int tamanioBuffer, int numTrabajadores, ContadorTrabajadores contadorTrabajadores){
        this.buffer = new Buffer(tamanioBuffer);
        this.numTrabajadores = numTrabajadores;
        this.trabajadores = new Trabajador[numTrabajadores];
        this.contadorTrabajadores = contadorTrabajadores;

        for(int i = 0; i < numTrabajadores; i++){
            this.trabajadores[i] = new Trabajador(buffer, contadorTrabajadores);
            trabajadores[i].start();
        }
    }

    public int getNumTrabajadores(){
        return numTrabajadores;
    }

    public void empezar(Runnable tarea){
        //Acá iría un serialize() si es necesario
        buffer.escribirBuffer(tarea);
    }

    public void detener(){
        for (int i = 0; i < trabajadores.length; i++){
            empezar(new PoisonPill());
        }
    }
}
