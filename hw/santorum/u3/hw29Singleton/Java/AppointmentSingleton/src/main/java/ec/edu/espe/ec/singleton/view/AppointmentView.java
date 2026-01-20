
package ec.edu.espe.ec.singleton.view;


import ec.edu.espe.singleton.controller.AppointmentController;
import ec.edu.espe.singleton.model.Appointment;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Thais Santorum
 */



public class AppointmentView extends JFrame {

    private JTextField txtAppointmentId;
    private JTextField txtPatientId;
    private JTextField txtPatientEmail;
    private JTextField txtDate;
    private JTextField txtTime;

    private AppointmentController controller;

    public AppointmentView() {
        controller = new AppointmentController();
        initUI();
    }

    private void initUI() {
        setTitle("Clinic Appointment System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        txtAppointmentId = new JTextField();
        txtPatientId = new JTextField();
        txtPatientEmail = new JTextField();
        txtDate = new JTextField();
        txtTime = new JTextField();

        JButton btnSave = new JButton("Register Appointment");

        add(new JLabel("Appointment ID:"));
        add(txtAppointmentId);
        add(new JLabel("Patient ID:"));
        add(txtPatientId);
        add(new JLabel("Patient Email:"));
        add(txtPatientEmail);
        add(new JLabel("Date (YYYY-MM-DD):"));
        add(txtDate);
        add(new JLabel("Time (HH:MM):"));
        add(txtTime);
        add(new JLabel());
        add(btnSave);

        btnSave.addActionListener(e -> saveAppointment());

        setVisible(true);
    }

    private void saveAppointment() {
        Appointment appointment = new Appointment(
                txtAppointmentId.getText(),
                txtPatientId.getText(),
                txtPatientEmail.getText(),
                txtDate.getText(),
                txtTime.getText()
        );

        controller.registerAppointment(appointment);
        JOptionPane.showMessageDialog(this, "Appointment registered and email sent.");
    }

    public static void main(String[] args) {
        new AppointmentView();
    }
}
