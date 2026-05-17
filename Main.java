import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("=== Aplikasi Contoh Main.java ===");
			System.out.println("Pilih operasi:");
			System.out.println("1. Halo dunia");
			System.out.println("2. Penjumlahan dua angka");
			System.out.println("0. Keluar");
			System.out.print("Masukkan pilihan: ");

			int pilihan = -1;
			if (in.hasNextInt()) {
				pilihan = in.nextInt();
			}

			switch (pilihan) {
				case 1 -> System.out.println("Halo, dunia!");
				case 2 -> {
                                    System.out.print("Masukkan angka pertama: ");
                                    double a = in.nextDouble();
                                    System.out.print("Masukkan angka kedua: ");
                                    double b = in.nextDouble();
                                    System.out.println("Hasil: " + (a + b));
                        }
				case 0 -> System.out.println("Keluar. Sampai jumpa!");
				default -> System.out.println("Pilihan tidak valid.");
			}
		}
	}
}
