/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.strategycrud.view;

import ec.edu.espe.strategycrud.controller.EventController;
import ec.edu.espe.strategycrud.model.Event;
import ec.edu.espe.strategycrud.controller.StorageStrategyFactory;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Mateo Cevallos
 */
public class FrmCalendar extends javax.swing.JFrame {

    private EventController controller;

    // Componentes GUI
    private JComboBox<String> cmbStorageType;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtDate;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnRead;
    private JButton btnClear;
    private JTextArea txtResults;
    private JTable tblEvents;

    public FrmCalendar() {
        initComponents();
        setupController();
    }

    private void initComponents() {
        // Configuración básica del frame
        setTitle("Sistema Calendar MVC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel superior para selección de almacenamiento
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Tipo de Almacenamiento:"));

        cmbStorageType = new JComboBox<>(new String[]{"JSON", "CSV", "MongoDB"});
        cmbStorageType.addActionListener(e -> changeStorageStrategy());
        topPanel.add(cmbStorageType);

        // Panel central con formulario y resultados
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Formulario para eventos
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("ID:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        txtId = new JTextField(15);
        centerPanel.add(txtId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        txtName = new JTextField(15);
        centerPanel.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(new JLabel("Fecha (YYYY-MM-DD):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        txtDate = new JTextField(15);
        centerPanel.add(txtDate, gbc);

        // Panel de botones CRUD
        JPanel buttonPanel = new JPanel(new FlowLayout());

        btnAdd = new JButton("Agregar");
        btnAdd.addActionListener(e -> addEvent());
        buttonPanel.add(btnAdd);

        btnUpdate = new JButton("Actualizar");
        btnUpdate.addActionListener(e -> updateEvent());
        buttonPanel.add(btnUpdate);

        btnDelete = new JButton("Eliminar");
        btnDelete.addActionListener(e -> deleteEvent());
        buttonPanel.add(btnDelete);

        btnRead = new JButton("Buscar");
        btnRead.addActionListener(e -> readEvent());
        buttonPanel.add(btnRead);

        btnClear = new JButton("Limpiar");
        btnClear.addActionListener(e -> clearForm());
        buttonPanel.add(btnClear);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        centerPanel.add(buttonPanel, gbc);

        // Área de resultados
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        txtResults = new JTextArea(10, 30);
        txtResults.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResults);
        centerPanel.add(scrollPane, gbc);

        // Agregar paneles al frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void setupController() {
        // Inicializar con estrategia por defecto (JSON)
        controller = new EventController(
                StorageStrategyFactory.createStrategy(
                        StorageStrategyFactory.StorageType.JSON
                )
        );
    }

    private void changeStorageStrategy() {
        String selected = (String) cmbStorageType.getSelectedItem();
        StorageStrategyFactory.StorageType type;

        switch (selected) {
            case "CSV":
                type = StorageStrategyFactory.StorageType.CSV;
                break;
            case "MongoDB":
                type = StorageStrategyFactory.StorageType.MONGODB;
                break;
            default:
                type = StorageStrategyFactory.StorageType.JSON;
        }

        controller.setStorageStrategy(
                StorageStrategyFactory.createStrategy(type)
        );
        txtResults.setText("Estrategia cambiada a: " + selected);
    }

    private void addEvent() {
        try {
            Event event = createEventFromForm();
            if (event != null) {
                boolean success = controller.addEvent(event);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Evento agregado exitosamente!");
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar evento",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error de Validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateEvent() {
        try {
            Event event = createEventFromForm();
            if (event != null) {
                boolean success = controller.updateEvent(event);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Evento actualizado exitosamente!");
                } else {
                    JOptionPane.showMessageDialog(this, "Evento no encontrado",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error de Validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteEvent() {
        String id = txtId.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID para eliminar",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar el evento con ID: " + id + "?",
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = controller.deleteEvent(id);
            if (success) {
                JOptionPane.showMessageDialog(this, "Evento eliminado exitosamente!");
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Evento no encontrado",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void readEvent() {
        String id = txtId.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID para buscar",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Event event = controller.readEvent(id);
        if (event != null) {
            txtName.setText(event.getName());
            txtDate.setText(event.getDate());
            txtResults.setText("Evento encontrado:\n" + event.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Evento no encontrado",
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private Event createEventFromForm() {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String date = txtDate.getText().trim();

        if (id.isEmpty() || name.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son requeridos",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        // Validar formato de fecha (simple)
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this,
                    "Formato de fecha inválido. Use YYYY-MM-DD",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return new Event(id, name, date);
    }

    private void clearForm() {
        txtId.setText("");
        txtName.setText("");
        txtDate.setText("");
        txtResults.setText("");
        txtId.requestFocus();
    }

    public static void main(String[] args) {
        // Establecer Look and Feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            new FrmCalendar().setVisible(true);
        });
    }
}
