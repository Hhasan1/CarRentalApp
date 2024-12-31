package carRent;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.sql.*;


	public class RegisterPage extends JFrame {

	    private JTextField tfKullaniciAdi, tfSifre, tfTelefon, tfTc;
	    private JButton btnKayitOl,btnGeri;

	    
	    public RegisterPage() {
	        try {
	            setTitle("Üye Ol");
	            setSize(400, 300);
	            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            setLocationRelativeTo(null);

	            JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));

	            JLabel lbKullaniciAdi = new JLabel("Kullanıcı Adı:");
	            tfKullaniciAdi = new JTextField();

	            JLabel lbSifre = new JLabel("Şifre:");
	            tfSifre = new JTextField();

	            JLabel lblTelefon = new JLabel("Telefon:");
	            tfTelefon = new JTextField();

	            JLabel lblTc = new JLabel("TC Kimlik No:");
	            tfTc = new JTextField();

	            btnKayitOl = new JButton("Kayıt Ol");
	            btnGeri = new JButton("Geri");

	            panel.add(lbKullaniciAdi);
	            panel.add(tfKullaniciAdi);
	            panel.add(lbSifre);
	            panel.add(tfSifre);
	            panel.add(lblTelefon);
	            panel.add(tfTelefon);
	            panel.add(lblTc);
	            panel.add(tfTc);
	            panel.add(btnKayitOl);
	            panel.add(btnGeri);

	            add(panel, BorderLayout.CENTER);

	            btnGeri.addActionListener(e -> {
	                new LoginFrame();
	                dispose();
	            });

	            btnKayitOl.addActionListener(e -> {
	                String kullaniciadi = tfKullaniciAdi.getText();
	                String sifre = tfSifre.getText();
	                String telefon = tfTelefon.getText();
	                String tc = tfTc.getText();

	                if (kullaniciadi.isEmpty() || sifre.isEmpty() || telefon.isEmpty() || tc.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun!");
	                } else if (!telefon.matches("\\d{11}")) {
	                    JOptionPane.showMessageDialog(null, "Telefon numarası geçerli değil.");
	                } else if (!tc.matches("\\d{11}")) {
	                    JOptionPane.showMessageDialog(null, "TC Kimlik No 11 haneli olmalıdır.");
	                } else {
	                    uyeKaydet(kullaniciadi, sifre, telefon, tc);
	                }
	            });

	            setVisible(true);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "RegisterPage oluşturulurken hata oluştu: " + ex.getMessage());
	        }
	    
	        
	        
	        btnGeri.addActionListener(e -> {
	            new LoginFrame(); 
	            dispose(); 
	          
		        
	        });
	        
	       
	        btnKayitOl.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                
	                String kullaniciadi = tfKullaniciAdi.getText();
	                String sifre = tfSifre.getText();
	                String telefon = tfTelefon.getText();
	                String tc = tfTc.getText();

	                
	                if (kullaniciadi.isEmpty() || sifre.isEmpty() || telefon.isEmpty() || tc.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun!");
	                } else {
	                    
	                    if (!telefon.matches("\\d{11}")) {
	                        JOptionPane.showMessageDialog(null, "Telefon numarası geçerli değil.");
	                        return;
	                    }
	                    if (!tc.matches("\\d{11}")) {
	                        JOptionPane.showMessageDialog(null, "TC Kimlik No geçerli değil.");
	                        return;
	                    }

	                   
	                    uyeKaydet(kullaniciadi, sifre, telefon, tc);
	                }
	            }
	        });
	    }
	    public class DatabaseConnection {
	        public static Connection getUyekayitdb() throws SQLException {
	            String url = "jdbc:mysql://localhost:3306/uyebilgi";
	            String username = "root"; // Veritabanı kullanıcı adı
	            String password = "yeniGucluSifre";     // Veritabanı şifresi

	            return DriverManager.getConnection(url, username, password);
	        }
	    }

	    // Veritabanına üye bilgilerini kaydetme
	    public void uyeKaydet(String kullaniciadi, String sifre, String telefon, String tc) {
	        String sql = "INSERT INTO uyebilgi.uyeform (kullaniciadi, sifre, telefon, tc) VALUES (?, ?, ?, ?)";
	        
	        try (Connection connection = DatabaseConnection.getUyekayitdb();
	             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	            preparedStatement.setString(1, kullaniciadi);
	            preparedStatement.setString(2, sifre);
	            preparedStatement.setString(3, telefon);
	            preparedStatement.setString(4, tc);

	            int rowsAffected = preparedStatement.executeUpdate();

	            if (rowsAffected > 0) {
	                JOptionPane.showMessageDialog(null, "Kayıt başarılı!");
	                // Alanları temizle
	                clearFields();
	            } else {
	                JOptionPane.showMessageDialog(null, "Kayıt başarısız.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e.getMessage());
	        }
	    }

	    // Alanları temizleme
	    public void clearFields() {
	        tfKullaniciAdi.setText("");
	        tfSifre.setText("");
	        tfTelefon.setText("");
	        tfTc.setText("");
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            RegisterPage frame = new RegisterPage();
	            frame.UyeOlPaneli();
	            frame.setVisible(true);
	        });
	    }

		private void UyeOlPaneli() {
			// TODO Auto-generated method stub
			
		}
	}


	
    
