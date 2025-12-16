package ec.edu.espe.contactbook.view;

/**
 *
 * @author Josue Carvajal, THE ART OF PROGRAMMING, @ESPE
 */

import ec.edu.espe.contactbook.controller.ContactController;
import ec.edu.espe.contactbook.model.Contact;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ContactView extends JFrame {

    private JTextField txtId;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtBirthDate; // yyyy-MM-dd
    private JTextField txtAge;
    private JComboBox<String> comboType;
    private JRadioButton rbMale, rbFemale;
    private JList<String> listHobbies;
    private JTextArea txtComments;
    private JTable table;
    private DefaultTableModel tableModel;

    private ContactController controller;

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

    public ContactView() {
        controller = new ContactController();
        initComponents();
        setTitle("CONTACTS");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        JLabel lblTitle = new JLabel("CONTACTS", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel left = new JPanel();
        left.setLayout(null);
        left.setPreferredSize(new Dimension(420, 420));

        JLabel lblId = new JLabel("id:");
        lblId.setBounds(20, 10, 100, 20);
        left.add(lblId);
        txtId = new JTextField();
        txtId.setBounds(120, 10, 120, 22);
        left.add(txtId);

        JLabel lblFirst = new JLabel("First Name:");
        lblFirst.setBounds(20, 40, 100, 20);
        left.add(lblFirst);
        txtFirstName = new JTextField();
        txtFirstName.setBounds(120, 40, 200, 22);
        left.add(txtFirstName);

        JLabel lblLast = new JLabel("Last Name:");
        lblLast.setBounds(20, 75, 100, 20);
        left.add(lblLast);
        txtLastName = new JTextField();
        txtLastName.setBounds(120, 75, 200, 22);
        left.add(txtLastName);

        JLabel lblBirth = new JLabel("Birth Date:");
        lblBirth.setBounds(20, 110, 100, 20);
        left.add(lblBirth);
        txtBirthDate = new JTextField();
        txtBirthDate.setToolTipText("Formato: yyyy-MM-dd (ej: 1990-05-23)");
        txtBirthDate.setBounds(120, 110, 120, 22);
        left.add(txtBirthDate);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setBounds(20, 145, 100, 20);
        left.add(lblAge);
        txtAge = new JTextField();
        txtAge.setBounds(120, 145, 60, 22);
        txtAge.setEditable(false);
        left.add(txtAge);

        JLabel lblType = new JLabel("Type:");
        lblType.setBounds(20, 180, 100, 20);
        left.add(lblType);
        comboType = new JComboBox<>(new String[]{"Family", "Friend", "Job", "Unknown"});
        comboType.setBounds(120, 180, 120, 22);
        left.add(comboType);

        JLabel lblSex = new JLabel("Sex:");
        lblSex.setBounds(20, 215, 100, 20);
        left.add(lblSex);
        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        ButtonGroup bgSex = new ButtonGroup();
        bgSex.add(rbMale);
        bgSex.add(rbFemale);
        rbMale.setBounds(120, 215, 80, 20);
        rbFemale.setBounds(200, 215, 100, 20);
        left.add(rbMale);
        left.add(rbFemale);

        JLabel lblHobbies = new JLabel("Hobbies:");
        lblHobbies.setBounds(20, 250, 100, 20);
        left.add(lblHobbies);
        String[] hobbiesArr = {"Play Soccer", "Djing", "Read", "Cook", "Swim", "Sing", "Play an instrument"};
        listHobbies = new JList<>(hobbiesArr);
        listHobbies.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane spHobbies = new JScrollPane(listHobbies);
        spHobbies.setBounds(120, 250, 160, 120);
        left.add(spHobbies);

        JPanel right = new JPanel();
        right.setLayout(new BorderLayout());
        right.setPreferredSize(new Dimension(420, 420));

        JPanel commentsPanel = new JPanel(new BorderLayout());
        commentsPanel.setBorder(BorderFactory.createTitledBorder("Comments:"));
        txtComments = new JTextArea();
        JScrollPane spComments = new JScrollPane(txtComments);
        commentsPanel.add(spComments, BorderLayout.CENTER);
        commentsPanel.setPreferredSize(new Dimension(400, 240));
        right.add(commentsPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "First Name", "Last Name", "Age", "Type", "Sex"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        JScrollPane spTable = new JScrollPane(table);
        spTable.setPreferredSize(new Dimension(400, 140));
        right.add(spTable, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        JButton btnSave = new JButton("Save");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnLoadAll = new JButton("Load All");

        buttons.add(btnSave);
        buttons.add(btnUpdate);
        buttons.add(btnDelete);
        buttons.add(btnLoadAll);

        JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
        center.add(left);
        center.add(right);

        add(lblTitle, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        // eventos
        txtBirthDate.addFocusListener(new FocusAdapter() {
            @Override public void focusLost(FocusEvent e) { computeAgeFromBirth(); }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if (r >= 0) {
                    int id = Integer.parseInt(tableModel.getValueAt(r, 0).toString());
                    loadContactToForm(id);
                }
            }
        });

        btnSave.addActionListener(ev -> {
            try {
                Contact c = readContactFromForm();
                controller.saveContact(c);
                JOptionPane.showMessageDialog(this, "Guardado en MongoDB");
                loadAllToTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnUpdate.addActionListener(ev -> {
            try {
                Contact c = readContactFromForm();
                controller.updateContact(c);
                JOptionPane.showMessageDialog(this, "Actualizado en MongoDB");
                loadAllToTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnDelete.addActionListener(ev -> {
            try {
                String idText = txtId.getText().trim();
                if (idText.isEmpty()) throw new IllegalArgumentException("Ingrese un ID para eliminar.");
                int id = Integer.parseInt(idText);
                int confirm = JOptionPane.showConfirmDialog(this, "Eliminar contacto con ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.deleteById(id);
                    JOptionPane.showMessageDialog(this, "Eliminado");
                    loadAllToTable();
                    clearForm();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLoadAll.addActionListener(ev -> loadAllToTable());

        // cargar al inicio
        loadAllToTable();
    }

    private void computeAgeFromBirth() {
        String txt = txtBirthDate.getText().trim();
        if (txt.isEmpty()) {
            txtAge.setText("");
            return;
        }
        try {
            LocalDate birth = LocalDate.parse(txt, FORMAT);
            int years = java.time.Period.between(birth, LocalDate.now()).getYears();
            txtAge.setText(String.valueOf(years));
        } catch (Exception ex) {
            txtAge.setText("");
        }
    }

    private Contact readContactFromForm() {
        String idText = txtId.getText().trim();
        if (idText.isEmpty()) throw new IllegalArgumentException("ID es requerido y debe ser numérico.");
        int id = Integer.parseInt(idText);

        String first = txtFirstName.getText().trim();
        if (first.isEmpty()) throw new IllegalArgumentException("First Name requerido.");

        String last = txtLastName.getText().trim();
        if (last.isEmpty()) throw new IllegalArgumentException("Last Name requerido.");

        String birthText = txtBirthDate.getText().trim();
        if (birthText.isEmpty()) throw new IllegalArgumentException("Birth Date requerido (yyyy-MM-dd).");
        LocalDate birthDate = LocalDate.parse(birthText, FORMAT);

        String type = comboType.getSelectedItem().toString();
        String sex = rbMale.isSelected() ? "male" : rbFemale.isSelected() ? "female" : "";

        List<String> hobbies = listHobbies.getSelectedValuesList();
        String comments = txtComments.getText().trim();

        return new Contact(id, first, last, birthDate, type, sex, new ArrayList<>(hobbies), comments);
    }

    private void loadContactToForm(int id) {
        try {
            Contact c = controller.findById(id);
            if (c == null) {
                JOptionPane.showMessageDialog(this, "Contacto no encontrado con ID: " + id);
                return;
            }
            txtId.setText(String.valueOf(c.getId()));
            txtFirstName.setText(c.getFirstName());
            txtLastName.setText(c.getLastName());
            if (c.getBirthDate() != null) txtBirthDate.setText(c.getBirthDate().format(FORMAT));
            txtAge.setText(String.valueOf(c.getAge()));
            comboType.setSelectedItem(capitalize(c.getTypeOfContact()));
            if ("male".equalsIgnoreCase(c.getSex())) rbMale.setSelected(true);
            else if ("female".equalsIgnoreCase(c.getSex())) rbFemale.setSelected(true);
            else { rbMale.setSelected(false); rbFemale.setSelected(false); }

            listHobbies.clearSelection();
            List<String> hobbies = c.getHobbies();
            if (hobbies != null) {
                ListModel<String> model = listHobbies.getModel();
                for (int i = 0; i < model.getSize(); i++) {
                    if (hobbies.contains(model.getElementAt(i))) {
                        listHobbies.addSelectionInterval(i, i);
                    }
                }
            }
            txtComments.setText(c.getComments());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error cargando contacto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadAllToTable() {
        try {
            List<Contact> contacts = controller.findAll();
            tableModel.setRowCount(0);
            for (Contact c : contacts) {
                tableModel.addRow(new Object[]{
                        c.getId(),
                        c.getFirstName(),
                        c.getLastName(),
                        c.getAge(),              // edad calculada
                        c.getTypeOfContact(),
                        c.getSex()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error cargando tabla: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtId.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtBirthDate.setText("");
        txtAge.setText("");
        comboType.setSelectedIndex(0);
        rbMale.setSelected(false);
        rbFemale.setSelected(false);
        listHobbies.clearSelection();
        txtComments.setText("");
    }

    private String capitalize(String s) {
        if (s == null) return "";
        if (s.isEmpty()) return s;
        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ContactView v = new ContactView();
            v.setVisible(true);
        });
    }
}
