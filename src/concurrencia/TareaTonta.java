package concurrencia;

public class TareaTonta extends Tarea{
    /*
    Clase de ejemplo para probar que funciona el Buffer y los Trabajadores.
     */
    private final String msg;

    public TareaTonta(String msg){
        this.msg = msg;
    }

    @Override
    public void run(){
        System.out.println(msg);
    }
}
