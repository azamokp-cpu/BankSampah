# Aplikasi Bank Sampah (Java Swing - NetBeans)
**Developer:** Zam zam okupa

## Deskripsi
Aplikasi desktop CRUD data **Setoran** Bank Sampah, dibuat dengan Java Swing
dan database MySQL. Project ini sudah dalam format project NetBeans (Ant-based),
jadi bisa langsung dibuka via **File > Open Project** di NetBeans.

## Struktur Tabel: `setoran`
| Kolom        | Tipe          | Keterangan       |
|--------------|---------------|-------------------|
| no           | INT (PK, AI)  | Nomor urut        |
| anggota      | VARCHAR(100)  | Nama anggota      |
| jenis_sampah | VARCHAR(50)   | Jenis sampah      |
| berat        | DOUBLE(10,2)  | Berat (kg)        |
| saldo        | DOUBLE(12,2)  | Saldo (Rp)        |

## Nama Database
```
db_BankSampah_231011403009
```
File export SQL tersedia di: `db_BankSampah_231011403009.sql`
Import file ini ke phpMyAdmin / MySQL Workbench / via terminal:
```
mysql -u root -p < db_BankSampah_231011403009.sql
```

## Langkah Setup di NetBeans
1. **Import database**
   - Buka phpMyAdmin / MySQL client.
   - Import file `db_BankSampah_231011403009.sql`.
2. **Download MySQL Connector/J**
   - Download file `mysql-connector-j-8.x.x.jar` dari:
     https://dev.mysql.com/downloads/connector/j/
   - Simpan ke folder `lib/` pada project ini dengan nama `mysql-connector-j.jar`
     (atau sesuaikan referensi di `nbproject/project.properties`,
     property `file.reference.mysql-connector-j.jar`).
3. **Buka project**
   - NetBeans > File > Open Project > pilih folder `BankSampah`.
   - Klik kanan project > Properties > Libraries, pastikan
     `mysql-connector-j.jar` sudah ditambahkan sebagai Compile-time Library
     (otomatis terbaca jika sudah di folder `lib/`).
4. **Sesuaikan koneksi**
   - Edit `src/bank/sampah/KoneksiDB.java` jika username/password
     MySQL Anda berbeda dari default (`root` / tanpa password).
5. **Run project** (Shift+F6 / klik kanan project > Run).

## Struktur Project
```
BankSampah/
├── build.xml
├── manifest.mf
├── db_BankSampah_231011403009.sql
├── lib/                         <- letakkan mysql-connector-j.jar di sini
├── nbproject/
│   ├── project.xml
│   └── project.properties
└── src/bank/sampah/
    ├── Main.java                <- entry point
    ├── MainFrame.java           <- form utama (CRUD UI)
    ├── KoneksiDB.java           <- koneksi database
    ├── Setoran.java             <- model data
    └── SetoranDAO.java          <- proses CRUD ke MySQL
```

## Fitur
- Tambah data setoran
- Lihat semua data (tabel)
- Cari data (berdasarkan nama anggota / jenis sampah)
- Update data
- Hapus data

---
Dibuat oleh **Zam zam okupa**.
