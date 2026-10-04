/*
 Gonzalez Alvarado Alexa Michelle
 Leon Gamez Fernando
 Camacho Guerra Jacobo
*/
import javax.swing.SwingUtilities;

public class Main {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaCola ventana = new VentanaCola();
            ventana.setVisible(true);
        });
    }
}