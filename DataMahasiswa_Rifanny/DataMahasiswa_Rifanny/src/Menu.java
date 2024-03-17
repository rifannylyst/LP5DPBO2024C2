import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Menu extends JFrame{
    public static void main(String[] args) {
        // buat object window
        Menu window = new Menu();

        // atur ukuran window
        window.setSize(480, 560);
        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);
        // isi window
        window.setContentPane(window.mainPanel);
        // ubah warna background
        window.getContentPane().setBackground(Color.white);
        // tampilkan window
        window.setVisible(true);
        // agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;

    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTextField emailField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JLabel emailLabel;
    private JLabel usiaLabel;
    private JSpinner ageSpinner;

    // constructor
    public Menu() {
        // inisialisasi listMahasiswa
        listMahasiswa = new ArrayList<>();

        // isi listMahasiswa
        populateList();

        // isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] jenisKelaminData = {"", "Laki-laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));

        // sembunyikan button delete
        deleteButton.setVisible(true);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    deleteData();
                }
            }
        });
        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saat tombol
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();
                if (selectedIndex >= 0 && selectedIndex < listMahasiswa.size()) {
                    // simpan value textfield dan combo box
                    String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
                    String selectedNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
                    String selectedJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 3).toString();
                    String selectedEmail = mahasiswaTable.getModel().getValueAt(selectedIndex, 4).toString();
                    int selectedAge = (int) mahasiswaTable.getModel().getValueAt(selectedIndex, 5);

                    // ubah isi textfield dan combo box
                    nimField.setText(selectedNim);
                    namaField.setText(selectedNama);
                    jenisKelaminComboBox.setSelectedItem(selectedJenisKelamin);
                    // Pastikan emailField tidak null sebelum mengatur nilai
                    if (emailField != null) {
                        emailField.setText(selectedEmail);
                    }
                    ageSpinner.setValue(selectedAge);

                    // ubah button "Add" menjadi "Update"
                    addUpdateButton.setText("Update");
                    // tampilkan button delete
                    deleteButton.setVisible(true);
                }
            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] column = {"No", "NIM", "Nama", "Jenis Kelamin", "Email", "Usia"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel temp = new DefaultTableModel(null, column);

        // isi tabel dengan listMahasiswa
        for (int i = 0; i < listMahasiswa.size(); i++) {
            Object[] row = new Object[column.length];
            row[0] = i + 1;
            row[1] = listMahasiswa.get(i).getNim();
            row[2] = listMahasiswa.get(i).getNama();
            row[3] = listMahasiswa.get(i).getJenisKelamin();
            row[4] = listMahasiswa.get(i).getEmail();
            row[5] = listMahasiswa.get(i).getUsia();


            temp.addRow(row);
        }

        return temp;
    }

    public void insertData() {
        // ambil value dari textfield dan combobox
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String email = emailField.getText();
        int usia = (int) ageSpinner.getValue();

        // tambahkan data ke dalam list
        listMahasiswa.add(new Mahasiswa(nim, nama, jenisKelamin, email, usia));

        // update tabel
        DefaultTableModel model = (DefaultTableModel) mahasiswaTable.getModel();
        model.addRow(new Object[]{model.getRowCount() + 1, nim, nama, jenisKelamin, email, usia});

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Insert Berhasil!");
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan!");
    }

    public void updateData() {
        if (selectedIndex >= 0) {
            // ambil data dari form
            String nim = nimField.getText();
            String nama = namaField.getText();
            String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
            String email = emailField.getText();
            int usia = (int) ageSpinner.getValue();

            // ubah data mahasiswa di list
            Mahasiswa mahasiswaToUpdate = listMahasiswa.get(selectedIndex);
            mahasiswaToUpdate.setNim(nim);
            mahasiswaToUpdate.setNama(nama);
            mahasiswaToUpdate.setJenisKelamin(jenisKelamin);
            mahasiswaToUpdate.setEmail(email);
            mahasiswaToUpdate.setUsia(usia);

            // update tabel
            DefaultTableModel model = (DefaultTableModel) mahasiswaTable.getModel();
            model.setValueAt(nim, selectedIndex, 1);
            model.setValueAt(nama, selectedIndex, 2);
            model.setValueAt(jenisKelamin, selectedIndex, 3);
            model.setValueAt(email, selectedIndex, 4);
            model.setValueAt(usia, selectedIndex, 5);

            // bersihkan form
            clearForm();

            // feedback
            System.out.println("Update Berhasil!");
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah!");
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris yang akan diupdate!");
        }
    }


    public void deleteData() {
        // Membuat dialog konfirmasi sebelum menghapus data
        int dialogResult = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            // hapus data dari list
            listMahasiswa.remove(selectedIndex);

            // update tabel
            DefaultTableModel model = (DefaultTableModel) mahasiswaTable.getModel();
            model.removeRow(selectedIndex);

            // bersihkan form
            clearForm();

            // feedback
            System.out.println("Delete Berhasil!");
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus!");
        }
    }

    public void clearForm() {
        // kosongkan semua texfield dan combo box
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedItem("");
        emailField.setText("");
        ageSpinner.setValue(0);

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");
        // sembunyikan button delete
        deleteButton.setVisible(true);
        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }

    private void populateList() {
        listMahasiswa.add(new Mahasiswa("2203999", "Amelia Zalfa Julianti", "Perempuan", "amelia@gmail.com", 20));
        listMahasiswa.add(new Mahasiswa("2202292", "Muhammad Iqbal Fadhilah", "Laki-laki", "iqbal@gmail.com", 22));
        listMahasiswa.add(new Mahasiswa("2202346", "Muhammad Rifky Afandi", "Laki-laki", "rifky@gmail.com", 25));
        listMahasiswa.add(new Mahasiswa("2210239", "Muhammad Hanif Abdillah", "Laki-laki", "hanif@gmail.com", 24));
        listMahasiswa.add(new Mahasiswa("2202046", "Nurainun", "Perempuan", "ainun@gmail.com", 22));
        listMahasiswa.add(new Mahasiswa("2205101", "Kelvin Julian Putra", "Laki-laki", "kelvin@gmail.com", 21));
        listMahasiswa.add(new Mahasiswa("2200163", "Rifanny Lysara Annastasya", "Perempuan", "rifanny@gmail.com", 20));
        listMahasiswa.add(new Mahasiswa("2202869", "Revana Faliha Salma", "Perempuan", "revana@gmail.com", 21));
        listMahasiswa.add(new Mahasiswa("2209489", "Rakha Dhifiargo Hariadi", "Laki-laki", "rakha@gmail.com", 24));
        listMahasiswa.add(new Mahasiswa("2203142", "Roshan Syalwan Nurilham", "Laki-laki", "roshan@gmail.com", 23));
        listMahasiswa.add(new Mahasiswa("2200311", "Raden Rahman Ismail", "Laki-laki", "rais@gmail.com", 22));
        listMahasiswa.add(new Mahasiswa("2200978", "Ratu Syahirah Khairunnisa", "Perempuan", "ratu@gmail.com", 20));
        listMahasiswa.add(new Mahasiswa("2204509", "Muhammad Fahreza Fauzan", "Laki-laki", "fahreza@gmail.com", 21));
        listMahasiswa.add(new Mahasiswa("2205027", "Muhammad Rizki Revandi", "Laki-laki", "raven@gmail.com", 25));
        listMahasiswa.add(new Mahasiswa("2203484", "Arya Aydin Margono", "Laki-laki", "arya@gmail.com", 23));
        listMahasiswa.add(new Mahasiswa("2200481", "Marvel Ravindra Dioputra", "Laki-laki", "marvel@gmail.com", 24));
        listMahasiswa.add(new Mahasiswa("2209889", "Muhammad Fadlul Hafiizh", "Laki-laki", "hafiizh@gmail.com", 21));
        listMahasiswa.add(new Mahasiswa("2206697", "Rifa Sania", "Perempuan", "rifa@gmail.com", 22));
        listMahasiswa.add(new Mahasiswa("2207260", "Imam Chalish Rafidhul Haque", "Laki-laki", "imam@gmail.com", 24));
        listMahasiswa.add(new Mahasiswa("2204343", "Meiva Labibah Putri", "Perempuan", "meiva@gmail.com", 23));
    }
}