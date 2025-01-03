package concurrencia;

public class ContadorTrabajadores {
    private int trabajadoresActivos = 0;

    public synchronized void incrementar(){
        trabajadoresActivos++;
    }

    public synchronized void decrementar(){
        trabajadoresActivos--;
        if (trabajadoresActivos == 0){
            notifyAll(); //Despierta al main si todos los trabajadores terminaron.
        }
    }

    public synchronized void trabajoTerminado(){
        while (trabajadoresActivos > 0){
            try{
                wait();
            } catch (InterruptedException e){
                return;
            } //Bloquea hasta que no haya Trabajadores activos.
        }
    }
}
