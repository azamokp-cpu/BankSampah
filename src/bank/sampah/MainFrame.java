package bank.sampah;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * Form utama Aplikasi Bank Sampah - tampilan modern hijau-putih
 * Dev : Zam zam okupa
 */
public class MainFrame extends JFrame {

    private static final Color G_DARK  = new Color(0x1a7a4a);
    private static final Color G_MID   = new Color(0x2da866);
    private static final Color G_LIGHT = new Color(0x4cd68a);
    private static final Color G_PALE  = new Color(0xe8f7ef);
    private static final Color G_MUTED = new Color(0xc5ead7);
    private static final Color WHITE   = Color.WHITE;
    private static final Color TEXT_DARK = new Color(0x0d3d25);
    private static final Color TEXT_SOFT = new Color(0x5a9e7a);

    private JTextField txtNo, txtAnggota, txtBerat, txtSaldo, txtCari;
    private JComboBox<String> cmbJenisSampah;
    private JTable table;
    private DefaultTableModel model;
    private final SetoranDAO dao = new SetoranDAO();

    public MainFrame() {
        setTitle("Aplikasi Bank Sampah");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 620);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(G_PALE);

        add(buatHeader(), BorderLayout.NORTH);
        add(buatForm(),   BorderLayout.WEST);
        add(buatTabel(),  BorderLayout.CENTER);
        add(buatFooter(), BorderLayout.SOUTH);

        muatData();
    }

    // ===================== HEADER =====================
    private JPanel buatHeader() {
        JPanel header = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, G_DARK, getWidth(), 0, G_LIGHT);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        header.setPreferredSize(new Dimension(0, 70));
        header.setBorder(new EmptyBorder(12, 20, 12, 20));

        JLabel lblIcon = new JLabel("\u267b");
        lblIcon.setFont(new Font("SansSerif", Font.PLAIN, 28));
        lblIcon.setForeground(WHITE);
        lblIcon.setBorder(new EmptyBorder(0, 0, 0, 12));

        JLabel lblTitle = new JLabel("Bank Sampah");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(WHITE);

        JLabel lblSub = new JLabel("Sistem Manajemen Setoran Sampah");
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblSub.setForeground(new Color(255, 255, 255, 180));

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setOpaque(false);
        textPanel.add(lblTitle);
        textPanel.add(lblSub);

        JPanel left = new JPanel(new BorderLayout());
        left.setOpaque(false);
        left.add(lblIcon, BorderLayout.WEST);
        left.add(textPanel, BorderLayout.CENTER);

        JLabel lblDev = new JLabel("Dev: Zam zam okupa", JLabel.RIGHT);
        lblDev.setFont(new Font("SansSerif", Font.ITALIC, 11));
        lblDev.setForeground(new Color(255, 255, 255, 160));

        header.add(left, BorderLayout.WEST);
        header.add(lblDev, BorderLayout.EAST);
        return header;
    }

    // ===================== FORM =====================
    private JPanel buatForm() {
        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setBackground(G_PALE);
        wrap.setBorder(new EmptyBorder(12, 12, 0, 6));
        wrap.setPreferredSize(new Dimension(290, 0));

        JPanel card = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                GradientPaint gp = new GradientPaint(0, 0, G_DARK, getWidth(), 0, G_LIGHT);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), 4, 4, 4);
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(16, 14, 14, 14));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 4, 5, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel judul = new JLabel("Form Input Setoran");
        judul.setFont(new Font("SansSerif", Font.BOLD, 13));
        judul.setForeground(G_DARK);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        card.add(judul, gbc);

        JSeparator sep = new JSeparator();
        sep.setForeground(G_MUTED);
        gbc.gridy = 1;
        card.add(sep, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.35;
        card.add(label("No"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.65;
        txtNo = styledField(); txtNo.setEditable(false);
        txtNo.setBackground(G_PALE); txtNo.setForeground(TEXT_SOFT);
        card.add(txtNo, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.35;
        card.add(label("Nama Anggota"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.65;
        txtAnggota = styledField();
        card.add(txtAnggota, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0.35;
        card.add(label("Jenis Sampah"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.65;
        cmbJenisSampah = new JComboBox<>(new String[]{"Plastik","Kertas","Logam","Kaca","Organik","Elektronik"});
        styleCombo(cmbJenisSampah);
        card.add(cmbJenisSampah, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0.35;
        card.add(label("Berat (kg)"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.65;
        txtBerat = styledField();
        card.add(txtBerat, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.weightx = 0.35;
        card.add(label("Saldo (Rp)"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.65;
        txtSaldo = styledField();
        card.add(txtSaldo, gbc);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2; gbc.insets = new Insets(14, 4, 4, 4);
        JSeparator sep2 = new JSeparator(); sep2.setForeground(G_MUTED);
        card.add(sep2, gbc);

        gbc.gridy = 8; gbc.insets = new Insets(4, 4, 4, 4);
        JPanel btnPanel = new JPanel(new GridLayout(2, 2, 6, 6));
        btnPanel.setOpaque(false);
        btnPanel.add(btnPrimary("+ Tambah",      e -> tambahData()));
        btnPanel.add(btnOutline("\u270e Update",  e -> updateData()));
        btnPanel.add(btnDanger("\u2715 Hapus",    e -> hapusData()));
        btnPanel.add(btnSecondary("\u21ba Bersihkan", e -> bersihkanForm()));
        card.add(btnPanel, gbc);

        wrap.add(card, BorderLayout.NORTH);
        return wrap;
    }

    // ===================== TABEL =====================
    private JPanel buatTabel() {
        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setBackground(G_PALE);
        wrap.setBorder(new EmptyBorder(12, 6, 0, 12));

        JPanel card = new JPanel(new BorderLayout(0, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                GradientPaint gp = new GradientPaint(0, 0, G_DARK, getWidth(), 0, G_LIGHT);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), 4, 4, 4);
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(16, 14, 14, 14));

        JPanel top = new JPanel(new BorderLayout(8, 0));
        top.setOpaque(false);

        JLabel judul = new JLabel("Data Setoran");
        judul.setFont(new Font("SansSerif", Font.BOLD, 13));
        judul.setForeground(G_DARK);

        JPanel searchPanel = new JPanel(new BorderLayout(6, 0));
        searchPanel.setOpaque(false);
        txtCari = styledField();
        txtCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { cariData(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { cariData(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { cariData(); }
        });
        JButton btnRefresh = btnOutline("\u21ba Refresh", e -> muatData());
        searchPanel.add(txtCari, BorderLayout.CENTER);
        searchPanel.add(btnRefresh, BorderLayout.EAST);

        top.add(judul, BorderLayout.WEST);
        top.add(searchPanel, BorderLayout.CENTER);
        card.add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(
            new Object[]{"No","Anggota","Jenis Sampah","Berat (kg)","Saldo (Rp)"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(model);
        table.setRowHeight(32);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(new Color(0xd1f5e5));
        table.setSelectionForeground(TEXT_DARK);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setBackground(WHITE);

        JTableHeader th = table.getTableHeader();
        th.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object val,
                    boolean sel, boolean foc, int row, int col) {
                JLabel lbl = new JLabel(val != null ? val.toString().toUpperCase() : "") {
                    @Override
                    protected void paintComponent(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        GradientPaint gp = new GradientPaint(0, 0, G_DARK, getWidth(), 0, G_MID);
                        g2.setPaint(gp);
                        g2.fillRect(0, 0, getWidth(), getHeight());
                        g2.dispose();
                        super.paintComponent(g);
                    }
                };
                lbl.setForeground(WHITE);
                lbl.setFont(new Font("SansSerif", Font.BOLD, 11));
                lbl.setBorder(new EmptyBorder(0, 10, 0, 10));
                lbl.setOpaque(false);
                return lbl;
            }
        });
        th.setPreferredSize(new Dimension(0, 36));
        th.setBorder(BorderFactory.createEmptyBorder());

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object val,
                    boolean sel, boolean foc, int row, int col) {
                super.getTableCellRendererComponent(t, val, sel, foc, row, col);
                setFont(new Font("SansSerif", Font.PLAIN, 13));
                setBorder(new EmptyBorder(0, 10, 0, 10));
                if (sel) {
                    setBackground(new Color(0xd1f5e5));
                    setForeground(TEXT_DARK);
                } else {
                    setBackground(row % 2 == 0 ? WHITE : new Color(0xf5fcf8));
                    setForeground(TEXT_DARK);
                }
                return this;
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> isiFormDariTabel());

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createLineBorder(G_MUTED, 1));
        scroll.getViewport().setBackground(WHITE);
        card.add(scroll, BorderLayout.CENTER);

        wrap.add(card, BorderLayout.CENTER);
        return wrap;
    }

    // ===================== FOOTER =====================
    private JPanel buatFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(G_PALE);
        footer.setBorder(new EmptyBorder(8, 12, 10, 12));
        JLabel lbl = new JLabel("\u00a9 Bank Sampah ZamZam Okupa \u2014 Sistem CRUD Data Setoran Sampah");
        lbl.setFont(new Font("SansSerif", Font.ITALIC, 11));
        lbl.setForeground(TEXT_SOFT);
        footer.add(lbl, BorderLayout.EAST);
        return footer;
    }

    // ===================== LOGIKA CRUD =====================
    private void muatData() {
        model.setRowCount(0);
        for (Setoran s : dao.tampilkanSemua())
            model.addRow(new Object[]{s.getNo(), s.getAnggota(), s.getJenisSampah(), s.getBerat(), s.getSaldo()});
    }

    private void cariData() {
        String kw = txtCari.getText().trim();
        model.setRowCount(0);
        List<Setoran> list = kw.isEmpty() ? dao.tampilkanSemua() : dao.cari(kw);
        for (Setoran s : list)
            model.addRow(new Object[]{s.getNo(), s.getAnggota(), s.getJenisSampah(), s.getBerat(), s.getSaldo()});
    }

    private void isiFormDariTabel() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtNo.setText(model.getValueAt(row, 0).toString());
            txtAnggota.setText(model.getValueAt(row, 1).toString());
            cmbJenisSampah.setSelectedItem(model.getValueAt(row, 2).toString());
            txtBerat.setText(model.getValueAt(row, 3).toString());
            txtSaldo.setText(model.getValueAt(row, 4).toString());
        }
    }

    private void tambahData() {
        if (!validasiInput()) return;
        Setoran s = new Setoran();
        s.setAnggota(txtAnggota.getText().trim());
        s.setJenisSampah((String) cmbJenisSampah.getSelectedItem());
        s.setBerat(Double.parseDouble(txtBerat.getText().trim()));
        s.setSaldo(Double.parseDouble(txtSaldo.getText().trim()));
        if (dao.tambah(s)) {
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            bersihkanForm(); muatData();
        }
    }

    private void updateData() {
        if (txtNo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data pada tabel terlebih dahulu!");
            return;
        }
        if (!validasiInput()) return;
        Setoran s = new Setoran();
        s.setNo(Integer.parseInt(txtNo.getText().trim()));
        s.setAnggota(txtAnggota.getText().trim());
        s.setJenisSampah((String) cmbJenisSampah.getSelectedItem());
        s.setBerat(Double.parseDouble(txtBerat.getText().trim()));
        s.setSaldo(Double.parseDouble(txtSaldo.getText().trim()));
        if (dao.update(s)) {
            JOptionPane.showMessageDialog(this, "Data berhasil diubah.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            bersihkanForm(); muatData();
        }
    }

    private void hapusData() {
        if (txtNo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data pada tabel terlebih dahulu!");
            return;
        }
        int c = JOptionPane.showConfirmDialog(this, "Hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION) {
            if (dao.hapus(Integer.parseInt(txtNo.getText().trim()))) {
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                bersihkanForm(); muatData();
            }
        }
    }

    private boolean validasiInput() {
        if (txtAnggota.getText().trim().isEmpty()
                || txtBerat.getText().trim().isEmpty()
                || txtSaldo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field wajib diisi!");
            return false;
        }
        try {
            Double.parseDouble(txtBerat.getText().trim());
            Double.parseDouble(txtSaldo.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Berat dan Saldo harus berupa angka!");
            return false;
        }
        return true;
    }

    private void bersihkanForm() {
        txtNo.setText(""); txtAnggota.setText("");
        txtBerat.setText(""); txtSaldo.setText("");
        cmbJenisSampah.setSelectedIndex(0);
        table.clearSelection();
    }

    // ===================== HELPER UI =====================
    private JLabel label(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.BOLD, 11));
        l.setForeground(new Color(0x1a6640));
        return l;
    }

    private JTextField styledField() {
        JTextField f = new JTextField();
        f.setFont(new Font("SansSerif", Font.PLAIN, 13));
        f.setForeground(TEXT_DARK);
        f.setBackground(WHITE);
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(G_MUTED, 1, true),
            new EmptyBorder(4, 8, 4, 8)
        ));
        f.setPreferredSize(new Dimension(0, 32));
        return f;
    }

    private void styleCombo(JComboBox<String> cb) {
        cb.setFont(new Font("SansSerif", Font.PLAIN, 13));
        cb.setBackground(WHITE);
        cb.setForeground(TEXT_DARK);
        cb.setPreferredSize(new Dimension(0, 32));
    }

    private JButton btnPrimary(String text, java.awt.event.ActionListener al) {
        JButton b = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, G_DARK, getWidth(), 0, G_MID);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        b.setForeground(WHITE);
        b.setFont(new Font("SansSerif", Font.BOLD, 12));
        b.setFocusPainted(false); b.setBorderPainted(false); b.setContentAreaFilled(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(0, 34));
        b.addActionListener(al);
        return b;
    }

    private JButton btnOutline(String text, java.awt.event.ActionListener al) {
        JButton b = new JButton(text);
        b.setForeground(G_DARK);
        b.setFont(new Font("SansSerif", Font.BOLD, 12));
        b.setBackground(WHITE); b.setFocusPainted(false);
        b.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(G_MUTED, 1, true),
            new EmptyBorder(4, 10, 4, 10)
        ));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(0, 34));
        b.addActionListener(al);
        return b;
    }

    private JButton btnSecondary(String text, java.awt.event.ActionListener al) {
        JButton b = new JButton(text);
        b.setForeground(TEXT_DARK);
        b.setFont(new Font("SansSerif", Font.BOLD, 12));
        b.setBackground(G_PALE); b.setFocusPainted(false);
        b.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(G_MUTED, 1, true),
            new EmptyBorder(4, 10, 4, 10)
        ));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(0, 34));
        b.addActionListener(al);
        return b;
    }

    private JButton btnDanger(String text, java.awt.event.ActionListener al) {
        JButton b = new JButton(text);
        b.setForeground(new Color(0xdc2626));
        b.setFont(new Font("SansSerif", Font.BOLD, 12));
        b.setBackground(new Color(0xfef2f2)); b.setFocusPainted(false);
        b.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xfecaca), 1, true),
            new EmptyBorder(4, 10, 4, 10)
        ));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(0, 34));
        b.addActionListener(al);
        return b;
    }
}
