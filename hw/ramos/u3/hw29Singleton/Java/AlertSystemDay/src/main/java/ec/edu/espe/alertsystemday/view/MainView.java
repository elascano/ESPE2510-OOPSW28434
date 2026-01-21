package ec.edu.espe.alertsystemday.view;

/**
 *
 * @author Paulo Ramos
 */
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import ec.edu.espe.alertsystemday.controller.MainController;

public class MainView extends JFrame {

    private JTextField taskNameField;
    private JTextField taskDateField;
    private JTextField alertDaysField;
    private JTextArea taskListArea;

    private MainController controller;

    public MainView(MainController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("AlertSystemPerDay");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));

        taskNameField = new JTextField();
        taskDateField = new JTextField("YYYY-MM-DD");
        alertDaysField = new JTextField();

        JButton addTaskBtn = new JButton("Add Task");
        JButton updateAlertBtn = new JButton("Update Days");

        inputPanel.add(new JLabel("Task Name:"));
        inputPanel.add(taskNameField);
        inputPanel.add(new JLabel("Deadline:"));
        inputPanel.add(taskDateField);
        inputPanel.add(new JLabel("Days in advance:"));
        inputPanel.add(alertDaysField);
        inputPanel.add(addTaskBtn);
        inputPanel.add(updateAlertBtn);

        taskListArea = new JTextArea();
        taskListArea.setEditable(false);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(taskListArea), BorderLayout.CENTER);

        addTaskBtn.addActionListener(e
                -> controller.addTask(
                        taskNameField.getText(),
                        taskDateField.getText()
                )
        );

        updateAlertBtn.addActionListener(e
                -> controller.updateAlertDays(alertDaysField.getText())
        );
    }

    public void updateTaskList(String text) {
        taskListArea.setText(text);
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message, "ALERT", JOptionPane.WARNING_MESSAGE);
    }
}
