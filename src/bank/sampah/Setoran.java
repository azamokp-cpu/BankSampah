package bank.sampah;

/**
 * Model data Setoran
 * Dev : Zam zam okupa
 */
public class Setoran {

    private int no;
    private String anggota;
    private String jenisSampah;
    private double berat;
    private double saldo;

    public Setoran() {
    }

    public Setoran(int no, String anggota, String jenisSampah, double berat, double saldo) {
        this.no = no;
        this.anggota = anggota;
        this.jenisSampah = jenisSampah;
        this.berat = berat;
        this.saldo = saldo;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getAnggota() {
        return anggota;
    }

    public void setAnggota(String anggota) {
        this.anggota = anggota;
    }

    public String getJenisSampah() {
        return jenisSampah;
    }

    public void setJenisSampah(String jenisSampah) {
        this.jenisSampah = jenisSampah;
    }

    public double getBerat() {
        return berat;
    }

    public void setBerat(double berat) {
        this.berat = berat;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
