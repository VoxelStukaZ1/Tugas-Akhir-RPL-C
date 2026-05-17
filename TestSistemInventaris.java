import java.util.ArrayList;
import java.util.List;

public class TestSistemInventaris {
    public static void main(String[] args) {
        SistemInventaris sistem = new SistemInventaris();
        List<String> log = new ArrayList<>();

        log.add(check("Login valid", sistem.login("admin", "rahasia123"), true));
        log.add(check("Login invalid", sistem.login("admin", "salah"), false));

        log.add(check("Tambah produk baru", sistem.tambahProduk("P11", "Pensil", 2500, 30).startsWith("SUKSES"), true));
        log.add(check("Tambah produk duplikat", sistem.tambahProduk("P11", "Pensil", 2500, 30).startsWith("GAGAL"), true));
        log.add(check("Tambah produk negatif", sistem.tambahProduk("P12", "Penghapus", -1000, 10).startsWith("GAGAL"), true));

        log.add(check("Ubah produk valid", sistem.ubahProduk("P11", 3000, 25).startsWith("SUKSES"), true));
        log.add(check("Ubah produk tidak ditemukan", sistem.ubahProduk("P99", 3000, 25).startsWith("GAGAL"), true));
        log.add(check("Ubah produk negatif", sistem.ubahProduk("P11", 3000, -5).startsWith("GAGAL"), true));

        log.add(check("Cari produk ada sebelum hapus", containsResult(sistem, "P11"), true));
        log.add(check("Hapus produk valid", sistem.hapusProduk("P11").startsWith("SUKSES"), true));
        log.add(check("Cari produk tidak ada setelah hapus", containsResult(sistem, "P11"), false));
        log.add(check("Hapus produk tidak ditemukan", sistem.hapusProduk("P99").startsWith("GAGAL"), true));

        log.add(check("Cari produk tidak ada", containsResult(sistem, "XYZ"), false));

        System.out.println("\n=== Hasil Uji Unit ===");
        int passed = 0;
        for (String entry : log) {
            System.out.println(entry);
            if (entry.contains("PASS")) passed++;
        }
        System.out.println("\nTotal: " + passed + "/" + log.size() + " test case lulus.");
    }

    private static String check(String name, boolean actual, boolean expected) {
        return name + ": " + (actual == expected ? "PASS" : "FAIL") + " (expected=" + expected + ", actual=" + actual + ")";
    }

    private static boolean containsResult(SistemInventaris sistem, String keyword) {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream oldOut = System.out;
        try {
            System.setOut(new java.io.PrintStream(baos));
            sistem.cariProduk(keyword);
            System.out.flush();
            String output = baos.toString();
            return output.contains("DITEMUKAN");
        } finally {
            System.setOut(oldOut);
        }
    }
}
