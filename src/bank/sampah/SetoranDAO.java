package bank.sampah;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Data Access Object - operasi CRUD tabel setoran
 * Dev : Zam zam okupa
 */
public class SetoranDAO {

    // ====== CREATE ======
    public boolean tambah(Setoran s) {
        String sql = "INSERT INTO setoran (anggota, jenis_sampah, berat, saldo) VALUES (?,?,?,?)";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getAnggota());
            ps.setString(2, s.getJenisSampah());
            ps.setDouble(3, s.getBerat());
            ps.setDouble(4, s.getSaldo());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menambah data!\n" + e.getMessage());
            return false;
        }
    }

    // ====== READ (ALL) ======
    public List<Setoran> tampilkanSemua() {
        List<Setoran> list = new ArrayList<>();
        String sql = "SELECT * FROM setoran ORDER BY no ASC";
        try (Connection conn = KoneksiDB.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Setoran s = new Setoran(
                        rs.getInt("no"),
                        rs.getString("anggota"),
                        rs.getString("jenis_sampah"),
                        rs.getDouble("berat"),
                        rs.getDouble("saldo")
                );
                list.add(s);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan data!\n" + e.getMessage());
        }
        return list;
    }

    // ====== READ (SEARCH) ======
    public List<Setoran> cari(String keyword) {
        List<Setoran> list = new ArrayList<>();
        String sql = "SELECT * FROM setoran WHERE anggota LIKE ? OR jenis_sampah LIKE ? ORDER BY no ASC";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Setoran s = new Setoran(
                        rs.getInt("no"),
                        rs.getString("anggota"),
                        rs.getString("jenis_sampah"),
                        rs.getDouble("berat"),
                        rs.getDouble("saldo")
                );
                list.add(s);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mencari data!\n" + e.getMessage());
        }
        return list;
    }

    // ====== UPDATE ======
    public boolean update(Setoran s) {
        String sql = "UPDATE setoran SET anggota=?, jenis_sampah=?, berat=?, saldo=? WHERE no=?";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getAnggota());
            ps.setString(2, s.getJenisSampah());
            ps.setDouble(3, s.getBerat());
            ps.setDouble(4, s.getSaldo());
            ps.setInt(5, s.getNo());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengubah data!\n" + e.getMessage());
            return false;
        }
    }

    // ====== DELETE ======
    public boolean hapus(int no) {
        String sql = "DELETE FROM setoran WHERE no=?";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, no);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus data!\n" + e.getMessage());
            return false;
        }
    }
}
