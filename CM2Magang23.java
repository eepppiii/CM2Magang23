import java.util.ArrayList;
import java.util.Scanner;

public class CM2Magang23 {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Object[]> dataMagang = new ArrayList<>(); // Array untuk data mahasiswa
    // Menu Pilihan
    static String[] Data = { "Tambah Data Magang",
            "Tampilan Semua Pendaftaran Magang",
            "Cari Pendaftaran Berdasarkan Program Studi",
            "Hitung Jumlah Pendaftaran Untuk Setiap Status",
            "Update Status Berdasarkan NIM", 
            "Keluar" };

    public static void main(String[] args) {
        pendafMagang();
    }

    public static void pendafMagang() {
        System.out.println("== Sistem Pendaftaran Magang Mahasiswa ==");
        for (int i = 0; i < Data.length; i++) {
            System.out.println((i + 1) + ". " + Data[i]);
        }
        System.out.print("Pilih Menu (1-5) :");
        int menu = sc.nextInt();
        switch (menu) {
            case 1:
                tambahMagang();
                break;
            case 2:
                semuaMagang();
                break;
            case 3:
                cariMagang();
                break;
            case 4:
                hitungMagang();
                break;
            case 5:
                updateStatus(); 
                break;
            case 6:
                keluar();
                break;
            default:
                System.out.println("Input Eror");
                break;
        }
    }

    // Buat Fungsi TambahData
    public static void tambahMagang() {

        sc.nextLine();
        Object[] siswa = new Object[6];

        System.out.print("Nama Mahasiswa : ");
        siswa[0] = sc.nextLine();

        System.out.print("NIM : ");
        siswa[1] = sc.nextInt();

        sc.nextLine();

        System.out.print("Program Studi : ");
        siswa[2] = sc.nextLine();

        System.out.print("Tempat Magang : ");
        siswa[3] = sc.nextLine();

        System.out.print("Semester Pengambilan Magang (6 atau 7) : ");
        siswa[4] = sc.nextInt();

        while (true) { // untuk memastikan semester 6 atau 7
            int semester = (int) siswa[4];
            if (semester == 6 || semester == 7) { // jika ya akan break atau lanjut
                break;
            }
            // jika tidak akan di minta untuk mengimput ulang
            System.out.println("Input tidak valid! Semester harus 6 atau 7.");
            System.out.print("Masukkan lagi Semester Pengambilan Magang (6 atau 7): ");
            siswa[4] = sc.nextInt();
        }
        sc.nextLine();

        System.out.print("Status Magang (Diterima/Menunggu/Ditolak) : ");
        siswa[5] = sc.nextLine();

        while (true) {
            String magang = (String) siswa[5];
            if (magang.equalsIgnoreCase("Diterima") ||
                    magang.equalsIgnoreCase("Menunggu") ||
                    magang.equalsIgnoreCase("Ditolak")) {
                break;
            }
            System.out.println("Input tidak valid!.");
            System.out.print("Masukkan Magang Dengan Benar : ");
            siswa[5] = sc.nextInt();
        }

        boolean lengkap = true;
        for (Object s : siswa) {
            if (s == null || s.toString().trim().isEmpty()) {
                lengkap = false;
                break;
            }
        }

        if (lengkap) { // jika di isi dengan lengkap
            dataMagang.add(siswa);
            System.out.println("Data pendaftaran magang berhasil ditambahkan. total :" + dataMagang.size());
            pendafMagang();

        } else { // jika tidak di isi dengan lengkap
            System.out.println("Data Tidak Berhasil Coba ulang");
            pendafMagang();
        }
    }

    // Buat Fungsi Menampilkan Semua Data
    public static void semuaMagang() {
        System.out.println("\n== Semua Data Pendaftaran ==");

        if (dataMagang.isEmpty()) {
            System.out.println("Belum ada data tersimpan!");
        } else {

            System.out.println("\nNo | Nama | NIM | Program Studi | Tempat Magang | Semester | Status");
            System.out.println("-----------------------------------------------------------------------");

            for (int i = 0; i < dataMagang.size(); i++) {
                Object[] s = dataMagang.get(i);

                System.out.println((i + 1) + " | " + s[0] + " | " + s[1] + " | " + s[2] + " | " + s[3] + " | " + s[4]
                        + " | " + s[5]);
            }

        }

        pendafMagang();
    }

    // Buat Fungsi Mencari Data Dengan Memasukan Prodi
    public static void cariMagang() {
        sc.nextLine();
        System.out.print("Masukkan Program Studi yang dicari: ");
        String prodi = sc.nextLine();

        boolean ada = false;

        System.out.println("\nNo | Nama | NIM | Program Studi | Tempat Magang | Semester | Status");
        System.out.println("-----------------------------------------------------------------------");

        int no = 1;

        for (Object[] s : dataMagang) {
            if (s[2].toString().equalsIgnoreCase(prodi)) {
                ada = true;
                System.out.println(
                        no + " | " + s[0] + " | " + s[1] + " | " + s[2] + " | " + s[3] + " | " + s[4] + " | " + s[5]);
                no++;
            }
        }

        if (!ada) {
            System.out.println("Tidak ada data dengan Program Studi tersebut!");
        }
        pendafMagang();
    }

    // Buat Fungsi Hitung Diterima, Menunggu Dan Ditolak
    public static void hitungMagang() {
        int diterima = 0, menunggu = 0, ditolak = 0;

        for (Object[] s : dataMagang) {
            String st = s[5].toString().toLowerCase();

            switch (st) {
                case "diterima":
                    diterima++;
                    break;
                case "menunggu":
                    menunggu++;
                    break;
                case "ditolak":
                    ditolak++;
                    break;
            }
        }
        System.out.println("Diterima : " + diterima);
        System.out.println("Menunggu : " + menunggu);
        System.out.println("Ditolak  : " + ditolak);
        int total = diterima + menunggu + ditolak;
        System.out.println("Total Pendaftar  : " + total);

        pendafMagang();
    }

    public static void keluar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Apakah Anda Yakin Keluar? (y/t)");
        String pilihan = sc.nextLine();

        if (pilihan.equalsIgnoreCase("y")) {
            System.out.print("Anda Keluar Dari Program");
            System.exit(0);
        } else {
            pendafMagang();
        }
    }

    public static void updateStatus() {
        if (dataMagang.isEmpty()) {
            System.out.println("Belum ada data tersimpan!");
            pendafMagang();
            return;
        }

        sc.nextLine(); // buang newline
        System.out.print("Masukkan NIM yang ingin diupdate: ");
        int nimCari = sc.nextInt();
        sc.nextLine();

        boolean ditemukan = false;

        for (Object[] s : dataMagang) {
            int nim = (int) s[1];
            if (nim == nimCari) {
                ditemukan = true;

                System.out.println("\n== Data Sebelum Update ==");
                System.out.println("Nama: " + s[0] + ", NIM: " + s[1] + ", Status: " + s[5]);

                String statusBaru;
                while (true) {
                    System.out.print("Masukkan Status Baru (Diterima/Menunggu/Ditolak): ");
                    statusBaru = sc.nextLine();
                    if (statusBaru.equalsIgnoreCase("Diterima") ||
                            statusBaru.equalsIgnoreCase("Menunggu") ||
                            statusBaru.equalsIgnoreCase("Ditolak")) {
                        break;
                    }
                    System.out.println("Status tidak valid. Coba lagi.");
                }

                s[5] = statusBaru;

                System.out.println("\n== Data Sesudah Update ==");
                System.out.println("Nama: " + s[0] + ", NIM: " + s[1] + ", Status: " + s[5]);

                break;
            }
        }

        if (!ditemukan) {
            System.out.println("Data dengan NIM " + nimCari + " tidak ditemukan!");
        }

        pendafMagang();
    }

}
// link Github https://github.com/eepppiii/CM2Magang23.git