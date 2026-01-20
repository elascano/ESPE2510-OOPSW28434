package ec.edu.espe.alertsystemperday.view;

/**
 *
 * @author Paulo Ramos
 */

import ec.edu.espe.alertsystemperday.controller.TaskController;
import ec.edu.espe.alertsystemperday.model.Task;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class MainView extends JFrame {

    private TaskController controller;
    private DefaultListModel<String> listModel;

    public MainView(TaskController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        setTitle("AlertSystemPerDay");
        setSize(450, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);

        JPanel top = new JPanel(new GridLayout(3,2));
        JTextField txtName = new JTextField();
        JTextField txtDate = new JTextField("2026-01-31");
        JTextField txtDays = new JTextField();

        JButton btnAdd = new JButton("Add Task");
        JButton btnConfig = new JButton("Update Alert Days");

        top.add(new JLabel("Task Name:"));
        top.add(txtName);
        top.add(new JLabel("Due Date (YYYY-MM-DD):"));
        top.add(txtDate);
        top.add(new JLabel("Alert Days:"));
        top.add(txtDays);

        JPanel buttons = new JPanel();
        buttons.add(btnAdd);
        buttons.add(btnConfig);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        btnAdd.addActionListener(e ->
                controller.addTask(txtName.getText(), LocalDate.parse(txtDate.getText()))
        );

        btnConfig.addActionListener(e ->
                controller.updateAlertDays(Integer.parseInt(txtDays.getText()))
        );

        setVisible(true);
    }

    public void showTasks(List<Task> tasks, int alertDays) {
        listModel.clear();
        for (Task t : tasks) {
            listModel.addElement(
                    t.getName() + " - Due: " + t.getDueDate() +
                    " (" + t.getDaysRemaining() + " days left)"
            );
        }
    }

    public void showAlert(String name, long days) {
        JOptionPane.showMessageDialog(this,
                "⚠ Task \"" + name + "\" expires in " + days + " day(s)");
    }
}
