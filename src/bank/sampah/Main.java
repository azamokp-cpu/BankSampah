package bank.sampah;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Entry point Aplikasi Bank Sampah
 * Dev : Zam zam okupa
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
