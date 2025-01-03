package concurrencia;

public class PoisonException extends RuntimeException{
    /*
    Excepción lanzada con el objetivo de matar un Trabajador mediante el buffer.
     */
    public PoisonException (String mensaje){
        super(mensaje);
    }
}
