# **📦 Sistem Manajemen Inventaris Toko (CLI)**

Proyek ini adalah aplikasi berbasis **Command Line Interface (CLI)** menggunakan bahasa pemrograman **Java**. Aplikasi ini dibuat untuk memenuhi Tugas Akhir mata kuliah **Rekayasa Perangkat Lunak C** di Universitas Brawijaya.

Sistem ini menerapkan prinsip **Object-Oriented Programming (OOP)** dengan arsitektur monolitik dan menyimpan data secara *in-memory* memanfaatkan struktur data HashMap.

## **✨ Fitur Utama**

Sistem ini memiliki fitur CRUD (Create, Read, Update, Delete) dasar dengan penanganan sesi (*login/logout*):

1. **🔐 Otentikasi Admin:** Wajib login dengan kredensial default sebelum mengakses sistem.  
2. **➕ Tambah Produk:** Menyimpan entitas produk baru dengan validasi (pencegahan duplikasi ID dan penolakan nilai harga/stok negatif).  
3. **📋 Tampilkan Semua:** Merender seluruh data produk yang tersimpan dalam format tabular yang rapi.  
4. **✏️ Ubah Produk:** Memperbarui data harga dan stok dari produk yang sudah terdaftar.  
5. **🗑️ Hapus Produk:** Menghapus data produk secara permanen dari *memory* sistem berdasarkan ID.  
6. **🔍 Cari Produk:** Fitur pencarian fleksibel (*case-insensitive*) berdasarkan ID atau Nama produk.  
7. **🛡️ Error Handling:** Mampu menangani *exception* jika pengguna memasukkan huruf pada isian yang seharusnya angka (mencegah aplikasi *crash*).

## **📂 Struktur File Kode (Source Code)**

Proyek ini dipecah menjadi beberapa file secara modular untuk memenuhi prinsip enkapsulasi pada OOP:

* **Produk.java**  
  Kelas POJO (*Plain Old Java Object*) yang merepresentasikan entitas data produk. Berisi atribut idProduk, nama, harga, dan stok beserta metode *Getter* & *Setter*\-nya.  
* **SistemInventaris.java**  
  Kelas *Backend* yang memuat seluruh logika bisnis aplikasi. Kelas ini bertanggung jawab atas manipulasi data di dalam HashMap (Otentikasi, Tambah, Ubah, Hapus, Cari, dan Tampil).  
* **Tugas Final.java** (Berisi Kelas Main)  
  Kelas utama (*Entry Point*) yang berfungsi sebagai antarmuka konsol (CLI). Menggunakan Scanner untuk menangkap *input* pengguna dan mengeksekusi perulangan menu utama.  
* **TestSistemInventaris.java**  
  File otomasi *Unit Testing* (White Box Testing) yang menguji fungsionalitas logika di kelas SistemInventaris dengan berbagai skenario jalur (*path*) berdasarkan *Control Flow Graph* (CFG).

## **🚀 Cara Menjalankan Aplikasi**

Pastikan komputer Anda sudah terinstal **Java Development Kit (JDK)** (minimal Java 8).

1. Buka Terminal (Command Prompt / PowerShell / Bash).  
2. Arahkan direktori (menggunakan perintah cd) ke folder tempat file proyek ini disimpan.  
3. Kompilasi semua file Java dengan perintah:  
   javac \*.java

4. Jalankan aplikasi utama (Main Class):  
   java Main

5. **Gunakan kredensial berikut untuk Login:**  
   * **Username:** admin  
   * **Password:** rahasia123

## **🧪 Cara Menjalankan Unit Testing**

Proyek ini dilengkapi dengan skrip otomatisasi pengujian unit. Untuk menguji sistem dan melihat hasil eksekusi tes (*Test Case*):

1. Pastikan Anda sudah mengompilasi kode pada langkah sebelumnya (javac \*.java).  
2. Jalankan kelas *testing* dengan perintah:  
   java TestSistemInventaris

3. Terminal akan menampilkan hasil *log* pengujian (Pass/Fail) untuk skenario uji input valid, invalid, duplikat, dan pencarian basis data.