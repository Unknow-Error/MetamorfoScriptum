package concurrencia;

public class Buffer {
    /*
    Clase para crear un Buffer siguiendo un esquema productor-consumidores con el objetivo
    de que el programa pueda ejecutar las operaciones y métodos de forma concurrente.
     */
    private final Runnable[] tareas;
    private int inicio = 0;
    private int ultimo = 0;

    public Buffer(int tamanio){
        tareas = new Runnable[tamanio + 1];
    }

    private int siguiente(int i){
        return (i + 1) % tareas.length;
    }

    private boolean estaVacio(){
        return inicio == ultimo;
    }

    private boolean estaLleno(){
        return siguiente(inicio) == ultimo;
    }

    public synchronized void escribirBuffer(Runnable unaTarea){
        while(estaLleno()){
            try {
                wait();
            } catch (InterruptedException e) {
                return;
            }
        }
        tareas[inicio] = unaTarea;
        inicio = siguiente(inicio);
        notifyAll();
    }

    public synchronized Runnable leerBuffer(){
        while(estaVacio()){
            try{
                wait();
            } catch (InterruptedException e){
                return null;
            }
        }
        Runnable resultado = tareas[ultimo];
        ultimo = siguiente(ultimo);
        notifyAll();
        return resultado;
    }
}
