import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// ==========================================
// 1. CLASS PRODUK (Menerapkan Encapsulation)
// ==========================================
class Produk {
    private final String idProduk;
    private final String nama;
    private int harga;
    private int stok;

    // Constructor
    public Produk(String idProduk, String nama, int harga, int stok) {
        this.idProduk = idProduk;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    // Getter dan Setter untuk mengakses data private
    public String getIdProduk() { return idProduk; }
    public String getNama() { return nama; }
    public int getHarga() { return harga; }
    public int getStok() { return stok; }

    public void setHarga(int harga) { this.harga = harga; }
    public void setStok(int stok) { this.stok = stok; }
}

// ==========================================
// 2. CLASS SISTEM INVENTARIS
// ==========================================
class SistemInventaris {
    private final HashMap<String, Produk> produkDb;
    private final HashMap<String, String> adminAkun;

    public SistemInventaris() {
        produkDb = new HashMap<>();
        adminAkun = new HashMap<>();
        adminAkun.put("admin", "rahasia123"); // Akun default
    }

    // Fitur: Otentikasi
    public boolean login(String username, String password) {
        return adminAkun.containsKey(username) && adminAkun.get(username).equals(password);
    }

    // Fitur 1: Tambah Produk
    public String tambahProduk(String idProduk, String nama, int harga, int stok) {
        if (harga < 0 || stok < 0) {
            return "GAGAL: Harga atau stok tidak boleh negatif!";
        }
        if (produkDb.containsKey(idProduk)) {
            return "GAGAL: ID Produk (" + idProduk + ") sudah terdaftar di sistem!";
        }
        produkDb.put(idProduk, new Produk(idProduk, nama, harga, stok));
        return "SUKSES: Produk berhasil ditambahkan ke dalam database.";
    }

    // Fitur 2: Ubah Produk
    public String ubahProduk(String idProduk, int hargaBaru, int stokBaru) {
        if (hargaBaru < 0 || stokBaru < 0) {
            return "GAGAL: Harga atau stok tidak boleh negatif!";
        }
        if (produkDb.containsKey(idProduk)) {
            Produk p = produkDb.get(idProduk);
            p.setHarga(hargaBaru);
            p.setStok(stokBaru);
            return "SUKSES: Data produk (" + idProduk + ") berhasil diperbarui.";
        }
        return "GAGAL: Produk dengan ID tersebut tidak ditemukan.";
    }

    // Fitur 3: Hapus Produk
    public String hapusProduk(String idProduk) {
        if (produkDb.containsKey(idProduk)) {
            produkDb.remove(idProduk);
            return "SUKSES: Produk berhasil dihapus dari sistem.";
        }
        return "GAGAL: Produk dengan ID tersebut tidak ditemukan.";
    }

    // Fitur 4: Cari Produk
    public void cariProduk(String kataKunci) {
        boolean ditemukan = false;
        System.out.println("---------------------------------------------------");
        for (Map.Entry<String, Produk> entry : produkDb.entrySet()) {
            String idProd = entry.getKey();
            Produk prod = entry.getValue();
            
            if (prod.getNama().toLowerCase().contains(kataKunci.toLowerCase()) || 
                idProd.toLowerCase().equals(kataKunci.toLowerCase())) {
                System.out.printf("=> DITEMUKAN | ID: %-5s | Nama: %-15s | Harga: Rp%-8d | Stok: %d\n", 
                                  idProd, prod.getNama(), prod.getHarga(), prod.getStok());
                ditemukan = true;
            }
        }
        System.out.println("---------------------------------------------------");
        if (!ditemukan) {
            System.out.println("=> MAAF: Produk yang Anda cari tidak ditemukan.");
        }
    }

    // Fitur Tambahan: Tampilkan Semua
    public void tampilkanSemua() {
        System.out.println("---------------------------------------------------");
        if (produkDb.isEmpty()) {
            System.out.println("=> Database masih kosong. Belum ada data produk.");
        } else {
            System.out.printf("%-10s | %-15s | %-10s | %-10s\n", "ID PRODUK", "NAMA PRODUK", "HARGA", "STOK");
            System.out.println("---------------------------------------------------");
            for (Map.Entry<String, Produk> entry : produkDb.entrySet()) {
                Produk prod = entry.getValue();
                System.out.printf("%-10s | %-15s | Rp%-8d | %d\n", 
                                  entry.getKey(), prod.getNama(), prod.getHarga(), prod.getStok());
            }
        }
        System.out.println("---------------------------------------------------");
    }
}

// ==========================================
// 3. MAIN CLASS (Antarmuka CLI Terpadu)
// ==========================================
@SuppressWarnings("unused")
class Main {
    public static void main(String[] args) {
        SistemInventaris sistem = new SistemInventaris();
        try (Scanner input = new Scanner(System.in)) {

            // --- DATA AWAL (DUMMY) ---
            sistem.tambahProduk("P01", "Buku Tulis", 5000, 100);
            sistem.tambahProduk("P02", "Pena Hitam", 3000, 50);
            sistem.tambahProduk("P03", "Penghapus", 2000, 80);
            sistem.tambahProduk("P04", "Spidol Warna", 7000, 40);
            sistem.tambahProduk("P05", "Buku Gambar", 15000, 35);
            sistem.tambahProduk("P06", "Kertas A4", 12000, 60);
            sistem.tambahProduk("P07", "Stapler", 25000, 25);
            sistem.tambahProduk("P08", "Penggaris", 5000, 70);
            sistem.tambahProduk("P09", "Lem Kertas", 8000, 45);
            sistem.tambahProduk("P10", "Tipe-X", 10000, 30);
            
            System.out.println("=========================================");
            System.out.println("   APLIKASI MANAJEMEN INVENTARIS TOKO    ");
            System.out.println("=========================================");

            // --- PROSES LOGIN ---
            boolean isLogin = false;
            while (!isLogin) {
                System.out.println("\n--- SILAKAN LOGIN ADMIN ---");
                System.out.print("Username : ");
                String user = input.nextLine();
                System.out.print("Password : ");
                String pass = input.nextLine();

                if (sistem.login(user, pass)) {
                    System.out.println("=> BERHASIL LOGIN! Selamat datang, " + user + ".");
                    isLogin = true;
                } else {
                    System.out.println("=> GAGAL LOGIN! Username atau Password salah.");
                }
            }

            // --- MENU UTAMA ---
            boolean isRunning = true;
            while (isRunning) {
                System.out.println("\n=== MENU UTAMA ===");
                System.out.println("1. Tampilkan Semua Produk");
                System.out.println("2. Tambah Produk Baru");
                System.out.println("3. Ubah Harga/Stok Produk");
                System.out.println("4. Hapus Produk");
                System.out.println("5. Cari Produk");
                System.out.println("6. Keluar / Logout");
                System.out.print("Pilih menu (1-6): ");
                
                String pilihan = input.nextLine();

                switch (pilihan) {
                    case "1" -> {
                        System.out.println("\n[1] DAFTAR SELURUH PRODUK");
                        sistem.tampilkanSemua();
                    }
                    case "2" -> {
                        System.out.println("\n[2] TAMBAH PRODUK BARU");
                        try {
                            System.out.print("Masukkan ID Produk : ");
                            String idP = input.nextLine();
                            System.out.print("Masukkan Nama Produk: ");
                            String namaP = input.nextLine();

                            System.out.print("Masukkan Harga (Angka): ");
                            int hargaP = Integer.parseInt(input.nextLine()); // Exception handling built-in via Try

                            System.out.print("Masukkan Stok (Angka) : ");
                            int stokP = Integer.parseInt(input.nextLine());

                            String hasil = sistem.tambahProduk(idP, namaP, hargaP, stokP);
                            System.out.println("=> " + hasil);
                        } catch (NumberFormatException e) {
                            System.out.println("=> ERROR: Harga dan Stok HARUS diisi dengan angka bulat!");
                        }
                    }
                    case "3" -> {
                        System.out.println("\n[3] UBAH DATA PRODUK");
                        try {
                            System.out.print("Masukkan ID Produk yang akan diubah: ");
                            String idUbah = input.nextLine();
                            System.out.print("Masukkan Harga Baru (Angka): ");
                            int hargaBaru = Integer.parseInt(input.nextLine());
                            System.out.print("Masukkan Stok Baru (Angka) : ");
                            int stokBaru = Integer.parseInt(input.nextLine());

                            String hasilUbah = sistem.ubahProduk(idUbah, hargaBaru, stokBaru);
                            System.out.println("=> " + hasilUbah);
                        } catch (NumberFormatException e) {
                            System.out.println("=> ERROR: Harga dan Stok HARUS diisi dengan angka bulat!");
                        }
                    }
                    case "4" -> {
                        System.out.println("\n[4] HAPUS PRODUK");
                        System.out.print("Masukkan ID Produk yang akan dihapus: ");
                        String idHapus = input.nextLine();
                        String hasilHapus = sistem.hapusProduk(idHapus);
                        System.out.println("=> " + hasilHapus);
                    }
                    case "5" -> {
                        System.out.println("\n[5] CARI PRODUK");
                        System.out.print("Ketik ID atau Nama barang yang dicari: ");
                        String keyword = input.nextLine();
                        sistem.cariProduk(keyword);
                    }
                    case "6" -> {
                        System.out.println("\nKeluar dari sistem. Terima kasih telah menggunakan aplikasi ini!");
                        isRunning = false;
                    }
                    default -> System.out.println("\n=> ERROR: Pilihan tidak valid, silakan ketik angka 1 sampai 6.");
                }
            }
        }
    }
}