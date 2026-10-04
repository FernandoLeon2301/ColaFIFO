/*
 Gonzalez Alvarado Alexa Michelle
 Leon Gamez Fernando
 Camacho Guerra Jacobo
*/

public class PruebaCola {

    public static void main(String[] args) {
        Cola cola = new Cola();

        verificar(cola.estaVacia(), "La cola inicia vacía.");

        cola.encolar(new Turno(1, "Ana", "Caja"));
        verificar(cola.obtenerTamano() == 1, "Al encolar el primer elemento, el tamaño es 1.");
        verificar(cola.verificarExtremosDeUnSoloElemento(), "Con un elemento, frente y ultimo apuntan al mismo nodo.");

        cola.encolar(new Turno(2, "Luis", "Pago"));
        cola.encolar(new Turno(3, "Maria", "Asesoria"));
        verificar(cola.obtenerTamano() == 3, "Después de tres inserciones, el tamaño es 3.");

        Turno primero = cola.desencolar();
        verificar(primero.getNumero() == 1, "El primer elemento desencolado es el primero que ingresó (FIFO).");
        verificar(cola.verFrente().getNumero() == 2, "Tras desencolar, el nuevo frente es el segundo elemento.");
        verificar(cola.obtenerTamano() == 2, "El tamaño disminuye al desencolar.");

        cola.vaciar();
        verificar(cola.estaVacia(), "Después de vaciar, la cola queda vacía.");

        System.out.println("Pruebas básicas completadas correctamente.");
    }

    private static void verificar(boolean condicion, String mensaje) {
        if (!condicion) {
            throw new AssertionError("FALLÓ: " + mensaje);
        }
        System.out.println("OK: " + mensaje);
    }
}