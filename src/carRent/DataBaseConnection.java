package carRent;
import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
    public static void main(String[] args) {
        // Veritabanı bağlantı bilgileri
       

        try {
            // Bağlantıyı oluştur
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/uyebilgi	", "root", "yeniGucluSifre");
            System.out.println("MySQL veritabanına başarıyla bağlanıldı!");

            // Bağlantıyı kapat
           // connection.close();
            //System.out.println("Bağlantı kapatıldı.");
        } catch (Exception e) {
            e.printStackTrace(); // Hataları yazdır
        }
    }

	public static Connection getUyekayitdb() {
		// TODO Auto-generated method stub
		return null;
	}
}
