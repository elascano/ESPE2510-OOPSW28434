package ec.edu.espe.parkingcontrolsystem.view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingGUI extends JFrame {

    private JTextField txtPlaca;
    private JTextField txtHoraEntrada;
    private JTextField txtHoraSalida;
    private JComboBox<String> cbEspacio;
    private JTextArea txtRegistro;

    public ParkingGUI() {
        setTitle("Parking Control System - Entrada y Salida");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null); // Absolute layout para simplicidad
        panel.setBackground(new Color(240, 240, 240));

        JLabel lblTitulo = new JLabel("REGISTRO DE VEHÍCULOS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(120, 20, 300, 30);
        panel.add(lblTitulo);

        // ======= PLACA =======
        JLabel lblPlaca = new JLabel("Placa:");
        lblPlaca.setFont(new Font("Arial", Font.PLAIN, 16));
        lblPlaca.setBounds(70, 90, 150, 30);
        panel.add(lblPlaca);

        txtPlaca = new JTextField();
        txtPlaca.setFont(new Font("Arial", Font.PLAIN, 16));
        txtPlaca.setBounds(200, 90, 200, 35);
        panel.add(txtPlaca);

        // ======= HORA ENTRADA =======
        JLabel lblHoraEntrada = new JLabel("Hora Entrada:");
        lblHoraEntrada.setFont(new Font("Arial", Font.PLAIN, 16));
        lblHoraEntrada.setBounds(70, 150, 150, 30);
        panel.add(lblHoraEntrada);

        txtHoraEntrada = new JTextField();
        txtHoraEntrada.setFont(new Font("Arial", Font.PLAIN, 16));
        txtHoraEntrada.setBounds(200, 150, 200, 35);
        txtHoraEntrada.setEditable(false);
        panel.add(txtHoraEntrada);

        // ======= HORA SALIDA =======
        JLabel lblHoraSalida = new JLabel("Hora Salida:");
        lblHoraSalida.setFont(new Font("Arial", Font.PLAIN, 16));
        lblHoraSalida.setBounds(70, 210, 150, 30);
        panel.add(lblHoraSalida);

        txtHoraSalida = new JTextField();
        txtHoraSalida.setFont(new Font("Arial", Font.PLAIN, 16));
        txtHoraSalida.setBounds(200, 210, 200, 35);
        txtHoraSalida.setEditable(false);
        panel.add(txtHoraSalida);

        // ======= ESPACIO =======
        JLabel lblEspacio = new JLabel("Espacio:");
        lblEspacio.setFont(new Font("Arial", Font.PLAIN, 16));
        lblEspacio.setBounds(70, 270, 150, 30);
        panel.add(lblEspacio);

        cbEspacio = new JComboBox<>(new String[]{"A1", "A2", "A3", "B1", "B2"});
        cbEspacio.setFont(new Font("Arial", Font.PLAIN, 16));
        cbEspacio.setBounds(200, 270, 200, 35);
        panel.add(cbEspacio);

        // ======= BOTÓN REGISTRAR ENTRADA =======
        JButton btnEntrada = new JButton("Registrar Entrada");
        btnEntrada.setFont(new Font("Arial", Font.BOLD, 15));
        btnEntrada.setBounds(70, 330, 160, 40);
        panel.add(btnEntrada);

        btnEntrada.addActionListener(e -> registrarEntrada());

        // ======= BOTÓN REGISTRAR SALIDA =======
        JButton btnSalida = new JButton("Registrar Salida");
        btnSalida.setFont(new Font("Arial", Font.BOLD, 15));
        btnSalida.setBounds(250, 330, 160, 40);
        panel.add(btnSalida);

        btnSalida.addActionListener(e -> registrarSalida());

        // ======= REGISTRO =======
        JLabel lblRegistro = new JLabel("Registro:");
        lblRegistro.setFont(new Font("Arial", Font.BOLD, 16));
        lblRegistro.setBounds(70, 390, 150, 30);
        panel.add(lblRegistro);

        txtRegistro = new JTextArea();
        txtRegistro.setFont(new Font("Arial", Font.PLAIN, 14));
        txtRegistro.setEditable(false);

        JScrollPane scroll = new JScrollPane(txtRegistro);
        scroll.setBounds(70, 430, 340, 120);
        panel.add(scroll);

        add(panel);
    }

    // ========================================================
    // MÉTODO PARA OBTENER LA HORA ACTUAL
    // ========================================================
    private String getCurrentTime() {
        return LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
    }

    // ========================================================
    // REGISTRAR ENTRADA
    // ========================================================
    private void registrarEntrada() {
        String placa = txtPlaca.getText().trim();

        if (placa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese una placa.");
            return;
        }

        txtHoraEntrada.setText(getCurrentTime());

        String registro = "Entrada → Placa: " + placa +
                " | Hora: " + txtHoraEntrada.getText() +
                " | Espacio: " + cbEspacio.getSelectedItem() + "\n";

        txtRegistro.append(registro);
    }

    // ========================================================
    // REGISTRAR SALIDA
    // ========================================================
    private void registrarSalida() {
        String placa = txtPlaca.getText().trim();

        if (placa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese una placa.");
            return;
        }

        txtHoraSalida.setText(getCurrentTime());

        String registro = "Salida  → Placa: " + placa +
                " | Hora: " + txtHoraSalida.getText() + "\n";

        txtRegistro.append(registro);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ParkingGUI().setVisible(true);
        });
    }
}
