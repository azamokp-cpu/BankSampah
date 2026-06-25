package bank.sampah;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * Kelas koneksi database MySQL
 * Database : db_BankSampah_231011403009
 * Dev      : Zam zam okupa
 */
public class KoneksiDB {

    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DB_NAME = "db_BankSampah_231011403009";
    private static final String USER = "root";
    private static final String PASS = "";

    private static final String URL =
            "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME
            + "?useSSL=false&serverTimezone=Asia/Jakarta&allowPublicKeyRetrieval=true";

    private static Connection conn;

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASS);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Koneksi database gagal!\n" + e.getMessage(),
                    "Error Koneksi",
                    JOptionPane.ERROR_MESSAGE);
        }
        return conn;
    }
}
