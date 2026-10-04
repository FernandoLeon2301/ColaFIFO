/*
 Gonzalez Alvarado Alexa Michelle
 Leon Gamez Fernando
 Camacho Guerra Jacobo
*/

import javax.swing.*;
import java.awt.*;

public class VentanaCola extends JFrame {

    private final Cola cola;

    private final JTextField txtNumero;
    private final JTextField txtCliente;
    private final JTextField txtServicio;

    private final JTextArea areaVisualizacion;
    private final JTextArea areaLog;
    private final JLabel lblEstado;

    private final JButton btnEncolar;
    private final JButton btnDesencolar;
    private final JButton btnVerFrente;
    private final JButton btnTamano;
    private final JButton btnVaciar;

    public VentanaCola() {
        this.cola = new Cola();

        this.txtNumero = new JTextField(10);
        this.txtCliente = new JTextField(20);
        this.txtServicio = new JTextField(20);

        this.areaVisualizacion = new JTextArea(6, 60);
        this.areaLog = new JTextArea(8, 60);

        this.lblEstado = new JLabel(" ");

        this.btnEncolar = new JButton("Encolar");
        this.btnDesencolar = new JButton("Desencolar");
        this.btnVerFrente = new JButton("Ver frente");
        this.btnTamano = new JButton("Ver tamaño");
        this.btnVaciar = new JButton("Vaciar");

        configurarVentana();
        registrarEventos();
        actualizarVista();
    }

    private void configurarVentana() {
        setTitle("TDA Cola FIFO con Nodos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel norte: formulario
        JPanel panelFormulario = new JPanel(new GridLayout(0, 2, 8, 8));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registrar turno"));

        panelFormulario.add(new JLabel("Número de turno:"));
        panelFormulario.add(txtNumero);

        panelFormulario.add(new JLabel("Cliente:"));
        panelFormulario.add(txtCliente);

        panelFormulario.add(new JLabel("Servicio:"));
        panelFormulario.add(txtServicio);

        // Panel centro: visualización y log
        areaVisualizacion.setEditable(false);
        areaVisualizacion.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaVisualizacion.setLineWrap(true);
        areaVisualizacion.setWrapStyleWord(true);

        JScrollPane scrollVisualizacion = new JScrollPane(areaVisualizacion);
        scrollVisualizacion.setBorder(BorderFactory.createTitledBorder("Visualización de la cola (frente -> final)"));

        areaLog.setEditable(false);
        areaLog.setFont(new Font("Consolas", Font.PLAIN, 12));
        areaLog.setLineWrap(true);
        areaLog.setWrapStyleWord(true);

        JScrollPane scrollLog = new JScrollPane(areaLog);
        scrollLog.setBorder(BorderFactory.createTitledBorder("Registro de operaciones"));

        JPanel panelCentro = new JPanel(new GridLayout(0, 1, 8, 8));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(8, 8, 0, 8));
        panelCentro.add(scrollVisualizacion);
        panelCentro.add(scrollLog);

        // Panel sur: botones y estado
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotones.add(btnEncolar);
        panelBotones.add(btnDesencolar);
        panelBotones.add(btnVerFrente);
        panelBotones.add(btnTamano);
        panelBotones.add(btnVaciar);

        JPanel panelSur = new JPanel(new BorderLayout(8, 8));
        panelSur.setBorder(BorderFactory.createEmptyBorder(0, 8, 8, 8));
        panelSur.add(panelBotones, BorderLayout.CENTER);

        lblEstado.setBorder(BorderFactory.createEtchedBorder());
        panelSur.add(lblEstado, BorderLayout.SOUTH);

        // Envoltura para el formulario
        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.setBorder(BorderFactory.createEmptyBorder(8, 8, 0, 8));
        panelNorte.add(panelFormulario, BorderLayout.CENTER);

        add(panelNorte, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);
    }

    private void registrarEventos() {
        btnEncolar.addActionListener(e -> encolarTurno());
        btnDesencolar.addActionListener(e -> desencolarTurno());
        btnVerFrente.addActionListener(e -> mostrarFrente());
        btnTamano.addActionListener(e -> mostrarTamano());
        btnVaciar.addActionListener(e -> vaciarCola());
    }

    private void encolarTurno() {
        try {
            String textoNumero = txtNumero.getText().trim();
            String cliente = txtCliente.getText().trim();
            String servicio = txtServicio.getText().trim();

            if (textoNumero.isEmpty()) {
                throw new IllegalArgumentException("Debe ingresar el número de turno.");
            }

            if (cliente.isEmpty()) {
                throw new IllegalArgumentException("Debe ingresar el nombre del cliente.");
            }

            if (servicio.isEmpty()) {
                throw new IllegalArgumentException("Debe ingresar el servicio.");
            }

            int numero = Integer.parseInt(textoNumero);

            if (numero <= 0) {
                throw new IllegalArgumentException("El número de turno debe ser mayor que cero.");
            }

            Turno turno = new Turno(numero, cliente, servicio);

            cola.encolar(turno);

            agregarLog("Se encoló: " + turno);
            limpiarCampos();
            actualizarVista();

        } catch (NumberFormatException e) {
            mostrarError("El número de turno debe ser un entero válido.");
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    private void desencolarTurno() {
        try {
            Turno atendido = cola.desencolar();
            agregarLog("Se atendió/desencoló: " + atendido);
            actualizarVista();
        } catch (IllegalStateException e) {
            mostrarError(e.getMessage());
        }
    }

    private void mostrarFrente() {
        try {
            Turno frente = cola.verFrente();
            JOptionPane.showMessageDialog(
                    this,
                    "El turno al frente es:\n" + frente,
                    "Frente de la cola",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (IllegalStateException e) {
            mostrarError(e.getMessage());
        }
    }

    private void mostrarTamano() {
        JOptionPane.showMessageDialog(
                this,
                "La cola contiene " + cola.obtenerTamano() + " elemento(s).",
                "Tamaño de la cola",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void vaciarCola() {
        if (cola.estaVacia()) {
            mostrarError("La cola ya está vacía.");
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea vaciar completamente la cola?",
                "Confirmar vaciado",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            cola.vaciar();
            agregarLog("Se vació la cola.");
            actualizarVista();
        }
    }

    private void actualizarVista() {
        areaVisualizacion.setText(cola.representarSecuencia());

        if (cola.estaVacia()) {
            lblEstado.setText("Estado: cola vacía. Tamaño: 0.");
        } else {
            lblEstado.setText("Estado: frente = " + cola.verFrente() + " | Tamaño: " + cola.obtenerTamano());
        }

        areaVisualizacion.setCaretPosition(0);
    }

    private void agregarLog(String mensaje) {
        areaLog.append(mensaje + System.lineSeparator());
        areaLog.setCaretPosition(areaLog.getDocument().getLength());
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Validación", JOptionPane.ERROR_MESSAGE);
    }

    private void limpiarCampos() {
        txtNumero.setText("");
        txtCliente.setText("");
        txtServicio.setText("");
        txtNumero.requestFocusInWindow();
    }
}