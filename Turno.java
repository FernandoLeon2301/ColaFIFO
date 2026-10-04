/*
 Gonzalez Alvarado Alexa Michelle
 Leon Gamez Fernando
 Camacho Guerra Jacobo
*/

public class Turno {

    private int numero;
    private String cliente;
    private String servicio;

    public Turno(int numero, String cliente, String servicio) {
        setNumero(numero);
        setCliente(cliente);
        setServicio(servicio);
    }

    public int getNumero() {
        return numero;
    }

    public String getCliente() {
        return cliente;
    }

    public String getServicio() {
        return servicio;
    }

    public void setNumero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("El número de turno debe ser mayor que cero.");
        }
        this.numero = numero;
    }

    public void setCliente(String cliente) {
        if (cliente == null || cliente.trim().isEmpty()) {
            throw new IllegalArgumentException("El cliente no puede estar vacío.");
        }
        this.cliente = cliente.trim();
    }

    public void setServicio(String servicio) {
        if (servicio == null || servicio.trim().isEmpty()) {
            throw new IllegalArgumentException("El servicio no puede estar vacío.");
        }
        this.servicio = servicio.trim();
    }

    @Override
    public String toString() {
        return "T" + numero + " | " + cliente + " | " + servicio;
    }
}