import java.util.HashMap;
import java.util.Map;

class SistemInventaris {
    private final HashMap<String, Produk> produkDb;
    private final HashMap<String, String> adminAkun;

    public SistemInventaris() {
        produkDb = new HashMap<>();
        adminAkun = new HashMap<>();
        adminAkun.put("admin", "rahasia123");
    }

    public boolean login(String username, String password) {
        return adminAkun.containsKey(username) && adminAkun.get(username).equals(password);
    }

    public String tambahProduk(String idProduk, String nama, int harga, int stok) {
        if (harga < 0 || stok < 0) {
            return "GAGAL: Harga atau stok tidak boleh negatif!";
        }
        if (produkDb.containsKey(idProduk)) {
            return "GAGAL: ID Produk (" + idProduk + ") sudah terdaftar di sistem!";
        }
        Produk produk = new Produk(nama, harga, stok);
        produkDb.put(idProduk, produk);
        return "SUKSES: Produk berhasil ditambahkan ke dalam database.";
    }

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

    public String hapusProduk(String idProduk) {
        if (produkDb.containsKey(idProduk)) {
            produkDb.remove(idProduk);
            return "SUKSES: Produk berhasil dihapus dari sistem.";
        }
        return "GAGAL: Produk dengan ID tersebut tidak ditemukan.";
    }

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
