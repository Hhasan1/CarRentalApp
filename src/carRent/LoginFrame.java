package carRent;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class LoginFrame extends JFrame {
    private JTextField kullaniciAdiField;
    private JPasswordField sifreField;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/uyebilgi";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "yeniGucluSifre";

    public LoginFrame() {
        setTitle("HK Araba Kiralama");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));

        kullaniciAdiField = new JTextField(15);
        sifreField = new JPasswordField(15);

        JButton loginButton = new JButton("Üye Girişi");
        JButton registerButton = new JButton("Üye Ol");

        panel.add(new JLabel("Kullanıcı Adı:"));
        panel.add(kullaniciAdiField);
        panel.add(new JLabel("Şifre:"));
        panel.add(sifreField);
        panel.add(loginButton);
        panel.add(registerButton);

        loginButton.addActionListener(e -> {
            if (verifyLogin(kullaniciAdiField.getText(), new String(sifreField.getPassword()))) {
                new RentalFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Geçersiz kullanıcı adı veya şifre", "Giriş Hatası", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> {
            new RegisterPage(); // RegisterPage açılır
            dispose(); // LoginFrame kapanır
        });

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private boolean verifyLogin(String kullaniciAdi, String sifre) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM uyeform WHERE kullaniciadi = ? AND sifre = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, kullaniciAdi);
                statement.setString(2, sifre);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}

