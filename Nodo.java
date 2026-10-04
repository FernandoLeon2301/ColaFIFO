/*
 Gonzalez Alvarado Alexa Michelle
 Leon Gamez Fernando
 Camacho Guerra Jacobo
*/

public class Nodo {

    private Turno dato;
    private Nodo siguiente;

    public Nodo(Turno dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Turno getDato() {
        return dato;
    }

    public void setDato(Turno dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}