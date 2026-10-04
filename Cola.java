/*
 Gonzalez Alvarado Alexa Michelle
 Leon Gamez Fernando
 Camacho Guerra Jacobo
*/


public class Cola {

    private Nodo frente;
    private Nodo ultimo;
    private int cantidad;

    public Cola() {
        vaciar();
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void encolar(Turno turno) {
        if (turno == null) {
            throw new IllegalArgumentException("No se puede encolar un turno nulo.");
        }

        Nodo nuevo = new Nodo(turno);

        if (estaVacia()) {
            // Primer elemento: frente y ultimo apuntan al mismo nodo.
            frente = nuevo;
            ultimo = nuevo;
        } else {
            // El nuevo nodo queda al final de la cola.
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }

        cantidad++;
    }

    public Turno desencolar() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía.");
        }

        Nodo nodoFrente = frente;
        Turno dato = nodoFrente.getDato();

        // La frente avanza al siguiente nodo.
        frente = frente.getSiguiente();

        // Se elimina la referencia del nodo retirado para ayudar al recolector de basura.
        nodoFrente.setSiguiente(null);

        // Si ya no hay elementos, ultimo también debe ser null.
        if (frente == null) {
            ultimo = null;
        }

        cantidad--;
        return dato;
    }

    public Turno verFrente() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía.");
        }
        return frente.getDato();
    }

    public int obtenerTamano() {
        return cantidad;
    }

    public void vaciar() {
        frente = null;
        ultimo = null;
        cantidad = 0;
    }

    /**
     * Método útil para verificar el requisito del primer elemento:
     * frente y ultimo deben apuntar al mismo nodo.
     */
    public boolean verificarExtremosDeUnSoloElemento() {
        return cantidad == 1 && frente != null && frente == ultimo;
    }

    /**
     * Devuelve una representación textual del estado interno de las referencias.
     * Sirve para demostrar visualmente el comportamiento de frente y ultimo.
     */
    public String obtenerEstadoPunteros() {
        if (estaVacia()) {
            return "Referencias internas: frente = null, ultimo = null";
        }

        if (frente == ultimo) {
            return "Referencias internas: frente y ultimo apuntan al mismo nodo -> [" + frente.getDato() + "]";
        }

        return "Referencias internas: frente -> [" + frente.getDato() + "] | ultimo -> [" + ultimo.getDato() + "]";
    }

    /**
     * Representa la secuencia completa de la cola desde el frente hasta el final.
     * Este método recorre los nodos manualmente, sin usar colecciones.
     */
    public String representarSecuencia() {
        StringBuilder sb = new StringBuilder();

        if (estaVacia()) {
            sb.append("FRENTE -> [SIN ELEMENTOS] <- FINAL");
        } else {
            sb.append("FRENTE -> ");

            Nodo actual = frente;

            while (actual != null) {
                sb.append("[").append(actual.getDato().toString()).append("]");

                actual = actual.getSiguiente();

                if (actual != null) {
                    sb.append(" -> ");
                }
            }

            sb.append(" <- FINAL");
        }

        sb.append(System.lineSeparator()).append(obtenerEstadoPunteros());

        return sb.toString();
    }

    // --------------------------------------------------------------------
    // Métodos opcionales con nomenclatura clásica:
    // enqueue, dequeue, front, isEmpty, size, clear.
    // Pueden ser útiles si el docente usa esos nombres.
    // --------------------------------------------------------------------

    public void enqueue(Turno turno) {
        encolar(turno);
    }

    public Turno dequeue() {
        return desencolar();
    }

    public Turno front() {
        return verFrente();
    }

    public boolean isEmpty() {
        return estaVacia();
    }

    public int size() {
        return obtenerTamano();
    }

    public void clear() {
        vaciar();
    }
}