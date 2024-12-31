package carRent;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class RentalFrame extends JFrame {
    private JComboBox<String> carModelCombo;
    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;
    private JLabel resultLabel;
    private JButton btnGeri;
    
    public RentalFrame() {
        setTitle("Araba Kiralama");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel carModelLabel = new JLabel("Araba Modeli:");
        carModelCombo = new JComboBox<>(new String[]{
        											 "Mercedes E200 - 2500 TL/Gün	", 
        											 "Fiat Egea	- 500 TL/Gün",
        											  "Renault Clio - 550 TL/Gün",
        											  "Hyundai i20 - 520 TL/Gün",
        											  "Opel Corsa -	530 TL/Gün",
        											  "Peugeot 208 - 560 TL/GÜn",
        											  "Volkswagen Golf - 750 TL/Gün",
        											  "Toyota Corolla - 800 TL/Gün",
        											  "Honda Civic - 820 TL/Gün",
        											 
        											  "Ford Focus - 780 TL/Gün",
        											  "Skoda Octavia - 790 TL/Gün",
        											  "Audi A3 - 1.200 TL/Gün",
        											  "BMW 1 Serisi	- 1.300 TL/Gün",
        											  "Mercedes-Benz A180 - 1.400 TL/Gün",
        											  "Volkswagen Passat - 1.250 TL/Gün",
        											  "Peugeot 508 - 1.270 TL/Gün",
        											  "BMW 3 Serisi - 2.000 TL/Gün",
        											  
        											  "Mercedes-Benz C200 - 2.200 TL/Gün",
        											  
        											  "Audi A6 - 2.300 TL/Gün",
        											  "Tesla Model 3 - 2.500 TL/Gün",
        											  "Range Rover Evoque - 3.000 TL/Gün",
        											  
        											 
        											 
        											
        											 
        
        											});

        JLabel startDateLabel = new JLabel("Alış Tarihi:");
        startDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd");
        startDateSpinner.setEditor(startDateEditor);

        JLabel endDateLabel = new JLabel("Teslim Tarihi:");
        endDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd");
        endDateSpinner.setEditor(endDateEditor);

        JButton calculateButton = new JButton("Hesapla");
        resultLabel = new JLabel("Sonuç: ", SwingConstants.CENTER);
        
        JButton backButton = new JButton("Geri");
        backButton.addActionListener(e -> {
        	new LoginFrame(); // LoginFrame tekrar açılır
            dispose();
        });
        btnGeri = new JButton("Geri");
        calculateButton.addActionListener(e -> calculateRental());

       
        panel.add(carModelLabel);
        panel.add(carModelCombo);
        panel.add(startDateLabel);
        panel.add(startDateSpinner);
        panel.add(endDateLabel);
        panel.add(endDateSpinner);
        panel.add(new JLabel(""));
        panel.add(calculateButton);
        panel.add(new JLabel(""));
        panel.add(resultLabel);
        panel.add(backButton);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
        
		btnGeri.addActionListener(e -> {
            new LoginFrame(); // LoginFrame tekrar açılır
            dispose(); // RegisterPage kapanır
        });
    }
  
    
    private void calculateRental() {
        try {
            String selectedCar = (String) carModelCombo.getSelectedItem();
            int dailyRate = 0;
            if (selectedCar != null) {
                if (selectedCar.contains("Mercedes E200")) dailyRate = 2500;
                else if (selectedCar.contains("Fiat Egea")) dailyRate = 500;
                else if (selectedCar.contains("Renault Clio")) dailyRate = 550;
                else if (selectedCar.contains("Hyundai i20")) dailyRate = 520;
                else if (selectedCar.contains("Opel Corsa")) dailyRate = 530;
                else if (selectedCar.contains("Peugeot 208")) dailyRate = 560;
                else if (selectedCar.contains("Volkswagen Golf")) dailyRate = 750;
                else if (selectedCar.contains("Toyota Corolla")) dailyRate = 800;
                else if (selectedCar.contains("Honda Civic")) dailyRate = 820;
                
                else if (selectedCar.contains("Ford Focus")) dailyRate = 780;
                else if (selectedCar.contains("Skoda Octavia")) dailyRate = 790;
                else if (selectedCar.contains("Audi A3")) dailyRate = 1200;
                else if (selectedCar.contains("BMW 1 Serisi")) dailyRate = 1300;
                else if (selectedCar.contains("Mercedes-Benz A180")) dailyRate = 1400;
                else if (selectedCar.contains("Volkswagen Passat")) dailyRate = 1250;
                else if (selectedCar.contains("Peugeot 508")) dailyRate = 1270;
                else if (selectedCar.contains("BMW 3 Serisi")) dailyRate = 2000;
                
                else if (selectedCar.contains("Mercedes-Benz C200")) dailyRate = 2200;
                else if (selectedCar.contains("Audi A6")) dailyRate = 2300;
                else if (selectedCar.contains("Tesla Model 3")) dailyRate = 2500;
                else if (selectedCar.contains("Range Rover Evoque")) dailyRate = 3000;
               
                
                
                
                
                
                
            }

            LocalDate startDate = LocalDate.parse(new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd").getFormat().format(startDateSpinner.getValue()));
            LocalDate endDate = LocalDate.parse(new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd").getFormat().format(endDateSpinner.getValue()));

            if (endDate.isBefore(startDate)) {
                resultLabel.setText("Sonuç: Teslim tarihi alış tarihinden önce olamaz.");
                return;
            }

            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1; // Kiralama günü dahil
            int totalCost = (int) (daysBetween * dailyRate);

            resultLabel.setText("Sonuç: Toplam gün: " + daysBetween + " | Ücret: " + totalCost + " TL");
        } catch (Exception ex) {
            resultLabel.setText("Sonuç: Tarihler düzgün seçilmedi.");
        }
    }
}



