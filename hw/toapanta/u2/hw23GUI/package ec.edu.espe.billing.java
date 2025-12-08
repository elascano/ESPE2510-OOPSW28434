package ec.edu.espe.billing.view;

import javax.swing.JOptionPane;
// Asegúrate de que las variables de tus componentes sean correctas
// private javax.swing.JTextField txtCedula;
// private javax.swing.JTextField txtNombre;
// ... (otras variables declaradas por el editor)

public class FrmBilling extends javax.swing.JFrame {

    // ... (Código del constructor y initComponents) ...

    /**
     * Este método contiene la lógica para capturar los datos de la factura
     * y simular el guardado.
     * Es el ActionListener del botón "Guardar Factura".
     */
    private void btnGuardarFacturaActionPerformed(java.awt.event.ActionEvent evt) {
        
        // 1. Obtener los valores de los campos de texto
        String cedula = txtCedula.getText();
        String nombre = txtNombre.getText();
        String direccion = txtDireccion.getText();
        String costoStr = txtCosto.getText();
        
        // 2. Validación básica de campos (opcional pero recomendado)
        if (cedula.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || costoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                    "Por favor, complete todos los campos de la factura.", 
                    "Error de Validación", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // 3. Convertir el costo a un tipo numérico (ej. float o double)
        float costo;
        try {
            costo = Float.parseFloat(costoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                    "El costo debe ser un valor numérico válido.", 
                    "Error de Formato", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 4. Procesar o mostrar los datos (simulación de guardado)
        
        String mensaje = String.format(
            "FACTURA GENERADA:\n" +
            "  Cédula: %s\n" +
            "  Nombre: %s\n" +
            "  Dirección: %s\n" +
            "  Costo Total: $%.2f",
            cedula, nombre, direccion, costo
        );
        
        System.out.println("--- Datos de Factura ---");
        System.out.println(mensaje);
        System.out.println("------------------------");
        
        // 5. Notificar al usuario y limpiar campos (opcional)
        JOptionPane.showMessageDialog(this, 
                "Factura generada y guardada con éxito.", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
                
        // Limpiar los campos después de guardar
        txtCedula.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtCosto.setText("");
    }
    
    // ... (El resto del código generado por el IDE, incluyendo main) ...