# LP5DPBO2024C2

### 
Saya Rifanny Lysara Annastasya [2200163] mengerjakan LP1 dalam mata kuliah
Desain dan Pemrograman Berorientasi Objek (DPBO) untuk keberkahanNya maka saya tidak
melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin. 
###

# Desain Program
1. Kelas Mahasiswa:
Kelas ini merepresentasikan entitas mahasiswa dengan properti seperti nim, nama, jenis kelamin, email, dan usia. Kelas Mahasiswa memiliki konstruktor untuk menginisialisasi objek Mahasiswa dan beberapa metode getter dan setter untuk mendapatkan dan mengatur nilai properti.

2. Kelas Menu:
Kelas ini merupakan antarmuka pengguna grafis (GUI) yang dibangun menggunakan Java Swing. Ini berfungsi sebagai antarmuka untuk mengelola data mahasiswa. Beberapa komponen GUI yang digunakan termasuk JTextField, JTable, JButton, JComboBox, JLabel, dan JSpinner.

- Metode `main` adalah titik masuk program yang membuat instance dari kelas Menu dan menampilkannya.
- Konstruktor `Menu` menginisialisasi komponen GUI dan mengatur pengaturan awal. Ini juga menambahkan action listener ke tombol dan tabel untuk menangani interaksi pengguna.
- Metode `setTable` digunakan untuk mengatur data mahasiswa ke dalam model tabel.
- Metode-metode `insertData`, `updateData`, dan `deleteData` digunakan untuk menambahkan, memperbarui, dan menghapus data mahasiswa dari daftar dan model tabel sesuai dengan input pengguna.
- Metode `clearForm` digunakan untuk menghapus isi dari input field dan mengatur kembali status tombol.
- Metode `populateList` digunakan untuk menginisialisasi daftar mahasiswa dengan beberapa entri dummy.

# Penjelasan Alur
1. **Inisialisasi GUI**:
   - Atribut `mainPanel` digunakan untuk menampung semua komponen GUI yang dibangun.
   - Atribut-atribut seperti `nimField`, `namaField`, `emailField`, `jenisKelaminComboBox`, dan `ageSpinner` digunakan untuk memasukkan data mahasiswa baru atau memperbarui data yang sudah ada.
   - `mahasiswaTable` digunakan untuk menampilkan data mahasiswa dalam tabel.
   - Tombol-tombol seperti `addUpdateButton`, `cancelButton`, dan `deleteButton` digunakan untuk menambah, memperbarui, dan menghapus data mahasiswa.

2. **Populasi Data Awal**:
   - Atribut `listMahasiswa` digunakan untuk menyimpan daftar mahasiswa yang diinisialisasi dengan beberapa entri dummy menggunakan metode `populateList`.

3. **Penanganan Aksi Tombol**:
   - Setiap tombol di GUI memiliki action listener yang menangani tindakan pengguna. Aksi dari tombol "Add/Update", "Delete", dan "Cancel" ditangani oleh metode-metode `insertData`, `updateData`, `deleteData`, dan `clearForm`.

4. **Interaksi dengan Tabel**:
   - Saat pengguna memilih baris di tabel, atribut `selectedIndex` disetel untuk menyimpan indeks baris yang dipilih. Ini memungkinkan program untuk mengetahui baris mana yang sedang dipilih untuk pengeditan atau penghapusan.

5. **Manipulasi Data Mahasiswa**:
   - Atribut `listMahasiswa` digunakan untuk menyimpan dan mengelola data mahasiswa. Metode-metode seperti `insertData`, `updateData`, dan `deleteData` digunakan untuk menambahkan, memperbarui, dan menghapus data dari daftar ini.

6. **Pemberitahuan**:
   - Saat operasi tambah, perbarui, atau hapus berhasil dilakukan, pesan notifikasi akan muncul menggunakan `JOptionPane` memberi tahu pengguna bahwa operasi tersebut berhasil dilakukan.

7. **Pemeliharaan Tampilan Tabel**:
   - Setiap kali data mahasiswa diperbarui, model tabel diperbarui menggunakan metode `setTable` untuk mencerminkan data terbaru.

# Record

https://github.com/rifannylyst/LP5DPBO2024C2/assets/147616851/45cd4359-abd4-47f1-8540-bce8415c3ab8

