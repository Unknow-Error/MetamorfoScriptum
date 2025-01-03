package concurrencia;

public class PoisonPill extends Tarea{
    @Override
    public void run(){
        throw new PoisonException("Deteniendo el trabajador");
    }
}
