package concurrencia;

public class Trabajador extends Thread{

    private final Buffer buffer;
    private final ContadorTrabajadores contadorTrabajadores;

    public Trabajador(Buffer buffer, ContadorTrabajadores contadorTrabajadores){
        this.buffer = buffer;
        this.contadorTrabajadores = contadorTrabajadores;
    }

    @Override
    public void run() {
        try{
            contadorTrabajadores.incrementar();
            while (true) {
                Runnable tarea = buffer.leerBuffer();
                tarea.run();
            }
        } catch (PoisonException e) {
            contadorTrabajadores.decrementar();
        }
    }
}
