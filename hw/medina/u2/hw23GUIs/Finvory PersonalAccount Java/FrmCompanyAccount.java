package ec.edu.espe.contacts.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FrmCompanyAccount extends JFrame {

    private JTextField txtName, txtRuc, txtPhone, txtEmail, txtUsername;
    private JPasswordField txtPassword;
    private JTextArea txtAddress;

    public FrmCompanyAccount() {
        setTitle("Finvory - Company Management");
        setSize(480, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(44, 62, 80));
        pnlHeader.setPreferredSize(new Dimension(500, 100));
        pnlHeader.setLayout(new BoxLayout(pnlHeader, BoxLayout.Y_AXIS));

        JLabel lblTitle = new JLabel("Finvory");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("Create Company Account");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitle.setForeground(new Color(189, 195, 199));
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlHeader.add(Box.createVerticalStrut(20));
        pnlHeader.add(lblTitle);
        pnlHeader.add(Box.createVerticalStrut(5));
        pnlHeader.add(lblSubtitle);
        
        add(pnlHeader, BorderLayout.NORTH);

        JPanel pnlForm = new JPanel();
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        Font labelFont = new Font("Segoe UI", Font.BOLD, 12);
        Color labelColor = new Color(52, 73, 94);

        int row = 0;

        addLabel(pnlForm, "Company Name:", labelFont, labelColor, gbc, row++);
        txtName = new JTextField();
        addInput(pnlForm, txtName, gbc, row++);

        addLabel(pnlForm, "RUC:", labelFont, labelColor, gbc, row++);
        txtRuc = new JTextField();
        addInput(pnlForm, txtRuc, gbc, row++);

        addLabel(pnlForm, "Address:", labelFont, labelColor, gbc, row++);
        txtAddress = new JTextArea(3, 20);
        txtAddress.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtAddress.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtAddress.setLineWrap(true);
        txtAddress.setWrapStyleWord(true);
        JScrollPane scrollAddress = new JScrollPane(txtAddress);
        scrollAddress.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.insets = new Insets(0, 40, 0, 40);
        pnlForm.add(scrollAddress, gbc);

        addLabel(pnlForm, "Phone:", labelFont, labelColor, gbc, row++);
        txtPhone = new JTextField();
        addInput(pnlForm, txtPhone, gbc, row++);

        addLabel(pnlForm, "Email:", labelFont, labelColor, gbc, row++);
        txtEmail = new JTextField();
        addInput(pnlForm, txtEmail, gbc, row++);

        addLabel(pnlForm, "Username:", labelFont, labelColor, gbc, row++);
        txtUsername = new JTextField();
        addInput(pnlForm, txtUsername, gbc, row++);

        addLabel(pnlForm, "Password:", labelFont, labelColor, gbc, row++);
        txtPassword = new JPasswordField();
        addInput(pnlForm, txtPassword, gbc, row++);

        JButton btnSave = new JButton("CREATE ACCOUNT");
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSave.setBackground(new Color(39, 174, 96));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnSave.setBorderPainted(false);
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.insets = new Insets(30, 40, 20, 40);
        gbc.ipady = 15;
        pnlForm.add(btnSave, gbc);

        add(pnlForm, BorderLayout.CENTER);
    }

    private void addLabel(JPanel panel, String text, Font font, Color color, GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.ipady = 0;
        gbc.insets = new Insets(15, 40, 5, 40);
        panel.add(label, gbc);
    }

    private void addInput(JPanel panel, JComponent field, GridBagConstraints gbc, int row) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY), 
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.ipady = 10;
        gbc.insets = new Insets(0, 40, 0, 40);
        panel.add(field, gbc);
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }

        java.awt.EventQueue.invokeLater(() -> {
            new FrmCompanyAccount().setVisible(true);
        });
    }
}