/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import koneksi.koneksi;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Yudha
 */
public class Project {

    /**
     * @param args the command line arguments
     */
    private Connection conn = new koneksi().connect();
    String[] data;
    int head = 0;

    int no = 0;

    public Project(int batas) {
        data = new String[batas];
    }

    public void enqueue(String inputan) {
        if (head < data.length) {
            data[head] = inputan;
            head++;
        } else {
            System.out.println("data telah penuh");
        }
    }

    public String dequeue() {
        String tmp = data[0];
        for (int i = 0; i < data.length - 1; i++) {
            data[i] = data[i + 1];
        }
        head--;
        if (head >= 0) {
            data[head] = null;
            no++;
        } else {
            no = 0;
            head = 0;
            System.out.println("Data tidak ada");
            System.exit(0);
            return "Queue is Empty";
        }
        return "No.00" + no + ", Nama : " + tmp;
    }
    static String sql;

    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Scanner sc = new Scanner(System.in);
            Scanner input = new Scanner(System.in);
            String nama = null, alamat = "", nopol = "", tipe_motor = "", hubungan, kmb = null, idb, nama_barang, id_barang = null, stock, KPB1, KPB2, KPB3, KPB4, servis, ganti_oli, ganti_part, turun_mesin, claim, servis_advisor = "", nama_mekanik = "";
            int no_hp = 0, km = 0, pilihan_pekerjaan, pilih_admin, pilih_adv, nh, stokk, hargaa;

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bengkel", "root", "");
            Statement stmt = conn.createStatement();

            Project Cust = new Project(5);

            String data, steruk = null, ida, na, ala;
            String data_lagi = "";
            Date date = new Date();
            SimpleDateFormat waktu = new SimpleDateFormat("hh.mm.ss");
            SimpleDateFormat tanggal = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            String time = waktu.format(c.getTime());
            String tgl = tanggal.format(c.getTime());
            String pekerjaan[] = new String[9];
            pekerjaan[0] = "KPB 1";
            pekerjaan[1] = "KPB 2";
            pekerjaan[2] = "KPB 3";
            pekerjaan[3] = "KPB 4";
            pekerjaan[4] = "Servis";
            pekerjaan[5] = "Ganti Oli";
            pekerjaan[6] = "Ganti Part";
            pekerjaan[7] = "Turun Mesin";
            pekerjaan[8] = "Claim";

            int harga[] = new int[9];
            harga[0] = 0;
            harga[1] = 0;
            harga[2] = 0;
            harga[3] = 0;
            harga[4] = 100000;
            harga[5] = 50000;
            harga[6] = 30000;
            harga[7] = 500000;
            harga[8] = 0;

            System.out.println("+-----------------+");
            System.out.println("| 1. Admin        |");
            System.out.println("| 2. Pendaftar    |");
            System.out.println("+-----------------+");
            System.out.println("Masukkan pilihan : ");
            int pilihan_awal = input.nextInt();
            switch (pilihan_awal) {
                case 1:
                    System.out.println("+-------------+");
                    System.out.println("| 1. advisor  |");
                    System.out.println("| 2. mekanik  |");
                    System.out.println("| 3. barang   |");
                    System.out.println("+-------------+");
                    System.out.println("Masukkan pilihan : ");
                    pilih_admin = input.nextInt();
                    switch (pilih_admin) {
                        case 1:
                            do {
                                System.out.println("+-----------+");
                                System.out.println("| 1. tambah |");
                                System.out.println("| 2. hapus  |");
                                System.out.println("| 3. ubah   |");
                                System.out.println("| 4. lihat  |");
                                System.out.println("+-----------+");
                                System.out.println("Masukkan Pilihan");
                                pilih_adv = input.nextInt();
                                switch (pilih_adv) {
                                    case 1:
                                        System.out.println("Masukkan id advisor : ");
                                        ida = sc.next();
                                        System.out.println("Masukkan nama advisor : ");
                                        na = input.next();
                                        System.out.println("Masukkan alamat : ");
                                        ala = sc.next();
                                        System.out.println("Masukkan no hp : ");
                                        nh = input.nextInt();
                                        sql = "insert into advisor(id_advisor, nama_advisor, alamat, no_hp) values ('" + ida + "','" + na + "','" + ala + "','" + nh + "')";
                                        stmt.executeUpdate(sql);
                                        System.out.println("kembali : ");
                                        kmb = sc.next();
                                        break;
                                    case 2:
                                        sql = "SELECT * from advisor";
                                        System.out.println("id advisor" + "\t" + "nama advisor" + "\t" + "alamat" + "\t" + "no hp");
                                        ResultSet ee = stmt.executeQuery(sql);
                                        while (ee.next()) {
                                            String idad = ee.getString("id_advisor");
                                            String namaad = ee.getString("nama_advisor");
                                            String alad = ee.getString("alamat");
                                            String hpad = ee.getString("no_hp");
                                            System.out.println(idad + "\t" + namaad + "\t" + alad + "\t" + hpad);
                                        }
                                        System.out.println("Masukkan id advisor : ");
                                        ida = sc.next();
                                        sql = "delete from advisor where id_advisor = '" + ida + "'";
                                        stmt.executeUpdate(sql);
                                        System.out.println("kembali : ");
                                        kmb = sc.next();
                                        break;
                                    case 3:
                                        sql = "SELECT * from advisor";
                                        System.out.println("id advisor" + "\t" + "nama advisor" + "\t" + "alamat" + "\t" + "no hp");
                                        ResultSet cc = stmt.executeQuery(sql);
                                        while (cc.next()) {
                                            String idad = cc.getString("id_advisor");
                                            String namaad = cc.getString("nama_advisor");
                                            String alad = cc.getString("alamat");
                                            String hpad = cc.getString("no_hp");
                                            System.out.println(idad + "\t" + namaad + "\t" + alad + "\t" + hpad);
                                        }
                                        System.out.println("Masukkan id advisor : ");
                                        ida = sc.next();
                                        System.out.println("Masukkan nama advisor : ");
                                        na = sc.next();
                                        System.out.println("Masukkan alamat : ");
                                        ala = sc.next();
                                        System.out.println("Masukkan no hp : ");
                                        nh = input.nextInt();
                                        sql = "update advisor set nama_advisor='" + na + "',alamat='" + ala + "',no_hp='" + nh + "' where id_advisor ='" + ida + "'";
                                        stmt.executeUpdate(sql);
                                        System.out.println("kembali : ");
                                        kmb = sc.next();
                                        break;
                                    case 4:
                                        sql = "SELECT * from advisor";
                                        System.out.println("id advisor" + "\t" + "nama advisor" + "\t" + "alamat" + "\t" + "no hp");
                                        ResultSet aa = stmt.executeQuery(sql);
                                        while (aa.next()) {
                                            String idad = aa.getString("id_advisor");
                                            String namaad = aa.getString("nama_advisor");
                                            String alad = aa.getString("alamat");
                                            String hpad = aa.getString("no_hp");
                                            System.out.println(idad + "\t" + namaad + "\t" + alad + "\t" + hpad);
                                        }
                                        System.out.println("kembali : ");
                                        kmb = sc.next();
                                }
                            } while (kmb.equalsIgnoreCase("y"));
                        case 2:
                            do{
                            System.out.println("+-----------+");
                                System.out.println("| 1. tambah |");
                                System.out.println("| 2. hapus  |");
                                System.out.println("| 3. ubah   |");
                                System.out.println("| 4. lihat  |");
                                System.out.println("+-----------+");
                                System.out.println("Masukkan Pilihan");
                                pilih_adv = input.nextInt();
                                switch (pilih_adv) {
                                    case 1:
                                        System.out.println("Masukkan id mekanik : ");
                                        ida = sc.next();
                                        System.out.println("Masukkan nama mekanik : ");
                                        na = input.next();
                                        System.out.println("Masukkan mekanik : ");
                                        ala = sc.next();
                                        System.out.println("Masukkan mekanik : ");
                                        nh = input.nextInt();
                                        sql = "insert into mekanik(id_mekanik, nama_mekanik, alamat, no_hp) values ('" + ida + "','" + na + "','" + ala + "','" + nh + "')";
                                        stmt.executeUpdate(sql);
                                        System.out.println("kembali : ");
                                        kmb = sc.next();
                                        break;
                                    case 2:
                                        sql = "SELECT * from mekanik";
                                        System.out.println("id mekanik" + "\t" + "nama mekanik" + "\t" + "alamat" + "\t" + "no hp");
                                        ResultSet ee = stmt.executeQuery(sql);
                                        while (ee.next()) {
                                            String idad = ee.getString("id_mekanik");
                                            String namaad = ee.getString("nama_mekanik");
                                            String alad = ee.getString("alamat");
                                            String hpad = ee.getString("no_hp");
                                            System.out.println(idad + "\t" + namaad + "\t" + alad + "\t" + hpad);
                                        }
                                        System.out.println("Masukkan mekanik : ");
                                        ida = sc.next();
                                        sql = "delete from mekanik where mekanik = '" + ida + "'";
                                        stmt.executeUpdate(sql);
                                        System.out.println("kembali : ");
                                        kmb = sc.next();
                                        break;
                                    case 3:
                                        sql = "SELECT * from mekanik";
                                        System.out.println("id mekanik" + "\t" + "nama mekanik" + "\t" + "alamat" + "\t" + "no hp");
                                        ResultSet cc = stmt.executeQuery(sql);
                                        while (cc.next()) {
                                            String idad = cc.getString("id_mekanik");
                                            String namaad = cc.getString("nama_mekanik");
                                            String alad = cc.getString("alamat");
                                            String hpad = cc.getString("no_hp");
                                            System.out.println(idad + "\t" + namaad + "\t" + alad + "\t" + hpad);
                                        }
                                        System.out.println("Masukkan id mekanik : ");
                                        ida = sc.next();
                                        System.out.println("Masukkan nama mekanik : ");
                                        na = sc.next();
                                        System.out.println("Masukkan alamat : ");
                                        ala = sc.next();
                                        System.out.println("Masukkan no hp : ");
                                        nh = input.nextInt();
                                        sql = "update advisor set nama_mekanik='" + na + "',alamat='" + ala + "',no_hp='" + nh + "' where id_mekanik ='" + ida + "'";
                                        stmt.executeUpdate(sql);
                                        System.out.println("kembali : ");
                                        kmb = sc.next();
                                        break;
                                    case 4:
                                        sql = "SELECT * from mekanik";
                                        System.out.println("id mekanik" + "\t" + "nama mekanik" + "\t" + "alamat" + "\t" + "no hp");
                                        ResultSet aa = stmt.executeQuery(sql);
                                        while (aa.next()) {
                                            String idad = aa.getString("id_mekanik");
                                            String namaad = aa.getString("nama_mekanik");
                                            String alad = aa.getString("alamat");
                                            String hpad = aa.getString("no_hp");
                                            System.out.println(idad + "\t" + namaad + "\t" + alad + "\t" + hpad);
                                        }
                                        System.out.println("kembali : ");
                                        kmb = sc.next();
                                }
                            } while (kmb.equalsIgnoreCase("y"));
                            break;
                        case 3:
                            System.out.println("+-----------+");
                                System.out.println("| 1. tambah |");
                                System.out.println("| 2. hapus  |");
                                System.out.println("| 3. ubah   |");
                                System.out.println("| 4. lihat  |");
                                System.out.println("+-----------+");
                                System.out.println("Masukkan Pilihan");
                                pilih_adv = input.nextInt();
                                switch (pilih_adv) {
                                    case 1:
                                        System.out.println("Masukkan barang: ");
                                        ida = sc.next();
                                        System.out.println("Masukkan nama barang: ");
                                        na = input.next();
                                        System.out.println("Masukkan stock : ");
                                        stokk = input.nextInt();
                                        System.out.println("Masukkan harga : ");
                                        hargaa = input.nextInt();
                                        sql = "insert into barang(id_barang, nama_barang, stock, harga) values ('" + ida + "','" + na + "','" + stokk + "','" + hargaa + "')";
                                        stmt.executeUpdate(sql);
                                        System.out.println("kembali : ");
                                        kmb = sc.next();
                                        break;
                                    case 2:
                                        sql = "SELECT * from barang";
                                        System.out.println("id barang" + "\t" + "nama barang" + "\t" + "stock" + "\t" + "harga");
                                        ResultSet ee = stmt.executeQuery(sql);
                                        while (ee.next()) {
                                            String idad = ee.getString("id_barang");
                                            String namaad = ee.getString("nama_barang");
                                            String alad = ee.getString("stock");
                                            String hpad = ee.getString("harga");
                                            System.out.println(idad + "\t" + namaad + "\t" + alad + "\t" + hpad);
                                        }
                                        System.out.println("Masukkan id barang: ");
                                        ida = sc.next();
                                        sql = "delete from advisor where barang= '" + ida + "'";
                                        stmt.executeUpdate(sql);
                                        System.out.println("kembali : ");
                                        kmb = sc.next();
                                        break;
                                    case 3:
                                        sql = "SELECT * from barang";
                                        System.out.println("id barang" + "\t" + "nama barang" + "\t" + "stock" + "\t" + "harga");
                                        ResultSet cc = stmt.executeQuery(sql);
                                        while (cc.next()) {
                                            String idad = cc.getString("id_barang");
                                            String namaad = cc.getString("nama_barang");
                                            String alad = cc.getString("stock");
                                            String hpad = cc.getString("harga");
                                            System.out.println(idad + "\t" + namaad + "\t" + alad + "\t" + hpad);
                                        }
                                        System.out.println("Masukkan id barang: ");
                                        ida = sc.next();
                                        System.out.println("Masukkan nama barang : ");
                                        na = sc.next();
                                        System.out.println("Masukkan stock: ");
                                        stokk = input.nextInt();
                                        System.out.println("Masukkan harga : ");
                                        hargaa = input.nextInt();
                                        sql = "update advisor set nama_barang='" + na + "',stock='" + stokk + "',harga='" + harga + "' where id_advisor ='" + ida + "'";
                                        stmt.executeUpdate(sql);
                                        System.out.println("kembali : ");
                                        kmb = sc.next();
                                        break;
                                    case 4:
                                        sql = "SELECT * from barang";
                                        System.out.println("id barang" + "\t" + "nama barang" + "\t" + "stock" + "\t" + "harga");
                                        ResultSet aa = stmt.executeQuery(sql);
                                        while (aa.next()) {
                                            String idad = aa.getString("id_barang");
                                            String namaad = aa.getString("nama_barang");
                                            String alad = aa.getString("stock");
                                            String hpad = aa.getString("harga");
                                            System.out.println(idad + "\t" + namaad + "\t" + alad + "\t" + hpad);
                                        }
                                        System.out.println("kembali : ");
                                        kmb = sc.next();
                                }
                            break;
                    }
                    break;
                case 2:

                    do {

                        System.out.print("Nama Pendaftar :");
                        data = sc.nextLine();
                        Cust.enqueue(data);
                        System.out.print("Ada data lagi ? ");
                        data_lagi = sc.nextLine();
                    } while (data_lagi.equalsIgnoreCase("Y"));

                    String a = null;
                    do {
                      
                            System.out.println("+----SELAMAT DATANG DI BENGKEL KITA----+");
                            System.out.println("|                                      |");
                            System.out.println("|    1. Service Berkala                |");
                            System.out.println("|    2. Layanan Biasa                  |");
                            System.out.println("|    3. Layanan Claim                  |");
                            System.out.println("|                                      |");
                            System.out.println("+--------------------------------------+");
                            System.out.print("Masukkan Pilihan : ");
                            int pilih = input.nextInt();
                            switch (pilih) {
                                case 1:
                                    steruk = "Service Berkala";
                                    System.out.println(Cust.dequeue());
                                    System.out.println("Tanggal Servis : ");
                                    System.out.println(tgl);
                                    System.out.print("No Polisi : ");
                                    sc.next();
                                    nopol = sc.nextLine();
                                    System.out.print("Tipe Motor : ");
                                    tipe_motor = sc.nextLine();
                                    System.out.print("KM : ");
                                    km = input.nextInt();
                                    System.out.print("Pendaftaran : ");
                                    System.out.println(time);
                                    break;

                                case 2:
                                    steruk = "Layanan Biasa";
                                    System.out.println(Cust.dequeue());
                                    System.out.println("Tanggal Servis : ");
                                    System.out.println(tgl);
                                    System.out.print("No Polisi : ");
                                    nopol = sc.nextLine();
                                    System.out.print("Tipe Motor : ");
                                    tipe_motor = sc.nextLine();
                                    System.out.print("KM : ");
                                    km = input.nextInt();
                                    System.out.print("Pendaftaran : ");
                                    System.out.println(time);
                                    break;

                                case 3:
                                    steruk = "Layanan Claim";
                                    System.out.println(Cust.dequeue());
                                    System.out.println("Tanggal Servis : ");
                                    System.out.println(tgl);
                                    System.out.print("No Polisi : ");
                                    nopol = sc.nextLine();
                                    System.out.print("Tipe Motor : ");
                                    tipe_motor = sc.nextLine();
                                    System.out.print("KM : ");
                                    km = input.nextInt();
                                    System.out.print("Pendaftaran : ");
                                    System.out.println(time);
                                    break;

                            }
                            System.out.println("+--------------------------+");
                            System.out.println("| 1. Pemilik Sepeda Motor  |");
                            System.out.println("| 2. Pembawa Sepeda Motor  |");
                            System.out.println("----------------------------");
                            System.out.println("Masukkan Pilihan : ");
                            int pemilik_pembawa = input.nextInt();
                            switch (pemilik_pembawa) {
                                case 1:
                                    System.out.println("Data Pemilik");
                                    System.out.print("Nama       : ");
                                    nama = sc.next();
                                    System.out.print("Alamat     : ");
                                    alamat = sc.next();
                                    System.out.print("No HP      : ");
                                    no_hp = input.nextInt();
                                    break;
                                case 2:
                                    System.out.println("Data Pembawa");
                                    System.out.print("Nama       : ");
                                    nama = sc.next();
                                    System.out.print("Alamat     : ");
                                    alamat = sc.next();
                                    System.out.print("No HP      : ");
                                    no_hp = input.nextInt();
                                    System.out.println("Hubungan pembawa dengan pemilik : ");
                                    hubungan = sc.next();
                                    break;
                            }
                            System.out.println("+------Menu Service-----+");
                            System.out.println("| 1. KPB 1              |");
                            System.out.println("| 2. KPB 2              |");
                            System.out.println("| 3. KPB 3              |");
                            System.out.println("| 4. KPB 4              |");
                            System.out.println("| 5. Servis             |");
                            System.out.println("| 6. Ganti Oli          |");
                            System.out.println("| 7. Ganti Part         |");
                            System.out.println("| 8. Turun Mesin        |");
                            System.out.println("| 9. Claim              |");
                            System.out.println("+-----------------------+");
                            System.out.println("Masukkan Pilihan : ");
                            pilihan_pekerjaan = input.nextInt();
                            switch (pilihan_pekerjaan) {
                                case 1:
                                    KPB1 = "KPB1";
                                    break;

                                case 2:
                                    KPB2 = "KPB2";
                                    break;

                                case 3:
                                    KPB3 = "KPB3";
                                    break;

                                case 4:
                                    KPB4 = "KPB4";
                                    break;

                                case 5:
                                    servis = "Servis";
                                    break;

                                case 6:
                                    ganti_oli = "Ganti Oli";
                                    break;

                                case 7:
                                    ganti_part = "Ganti Part";
                                    sql = "SELECT * from barang";
                                    System.out.println("id barang" + "\t" + "nama barang");
                                    ResultSet rs = stmt.executeQuery(sql);
                                    while (rs.next()) {
                                        id_barang = rs.getString("id_barang");
                                        nama_barang = rs.getString("nama_barang");
                                        System.out.println(id_barang + "\t" + nama_barang);
                                    }
                                    System.out.println("Masukkan id barang : ");
                                    idb = sc.next();
                                    sql = "SELECT * from mekanik";
                                    System.out.println("id mekanik" + "\t" + "nama mekanik");
                                    ResultSet gg = stmt.executeQuery(sql);
                                    while (gg.next()) {
                                        String id_mekanik = gg.getString("id_mekanik");
                                        String nm_mekanik = gg.getString("nama_mekanik");
                                        System.out.println(id_mekanik + "\t" + nm_mekanik);
                                    }
                                    System.out.println("nama mekanik : ");
                                    nama_mekanik = sc.next();
                                    sql = "SELECT * from advisor";
                                    System.out.println("id advisor" + "\t" + "nama advisor");
                                    ResultSet st = stmt.executeQuery(sql);
                                    while (st.next()) {
                                        String id_advisor = st.getString("id_advisor");
                                        String nm_advisor = st.getString("nama_advisor");
                                        System.out.println(id_advisor + "\t" + nm_advisor);
                                    }
                                    System.out.println("Nama Servis Advisor : ");
                                    servis_advisor = sc.next();
                                    System.out.println("+-------------Struk " +steruk+"------------+");
                                    System.out.println("Nama : " + nama);
                                    System.out.println("Alamat : " + alamat);
                                    System.out.println("No HP : " + no_hp);
                                    System.out.println("No Polisi : " + nopol);
                                    System.out.println("KM : " + km);
                                    System.out.println("Tipe Motor : " + tipe_motor);
                                    System.out.println("Service Advisor : " + servis_advisor);
                                    System.out.println("Mekanik : " + nama_mekanik);
                                    System.out.println("----------------------------------------------------");
                                    System.out.println("Pekerjaan : " + pekerjaan[pilihan_pekerjaan - 1]);
                                    System.out.println("");
                                    System.out.println("Harga Jasa: " + harga[pilihan_pekerjaan - 1]);
                                    System.out.println("Selesai : " + time);
                                    System.out.println("Tanggal : " + tgl);
                                    System.exit(0);
                                    break;

                                case 8:
                                    turun_mesin = "Turun Mesin";
                                    ;
                                    break;

                                case 9:
                                    System.out.println("Masukkan Keluhan : ");
                                    claim = sc.nextLine();
                                    break;
                            }
                            
                        

                        sql = "SELECT * from mekanik";
                        System.out.println("id mekanik" + "\t" + "nama mekanik");
                        ResultSet rs = stmt.executeQuery(sql);
                        while (rs.next()) {
                            String id_mekanik = rs.getString("id_mekanik");
                            String nm_mekanik = rs.getString("nama_mekanik");
                            System.out.println(id_mekanik + "\t" + nm_mekanik);
                        }
                        System.out.println("nama mekanik : ");
                        nama_mekanik = sc.next();
                        sql = "SELECT * from advisor";
                        System.out.println("id advisor" + "\t" + "nama advisor");
                        ResultSet st = stmt.executeQuery(sql);
                        while (st.next()) {
                            String id_advisor = st.getString("id_advisor");
                            String nm_advisor = st.getString("nama_advisor");
                            System.out.println(id_advisor + "\t" + nm_advisor);
                        }
                        System.out.println("Nama Servis Advisor : ");
                        servis_advisor = sc.next();
                        System.out.println("+-------------Struk " + steruk + "------------+");
                        System.out.println("Nama : " + nama);
                        System.out.println("Alamat : " + alamat);
                        System.out.println("No HP : " + no_hp);
                        System.out.println("No Polisi : " + nopol);
                        System.out.println("KM : " + km);
                        System.out.println("Tipe Motor : " + tipe_motor);
                        System.out.println("Service Advisor : " + servis_advisor);
                        System.out.println("Mekanik : " + nama_mekanik);
                        System.out.println("----------------------------------------------------");
                        System.out.println("Pekerjaan : " + pekerjaan[pilihan_pekerjaan - 1]);
                        System.out.println("");
                        System.out.println("Harga Jasa: " + harga[pilihan_pekerjaan - 1]);
                        System.out.println("Selesai : " + time);
                        System.out.println("Tanggal : " + tgl);
                        System.out.print("Kembali ke menu : ");
                        a = sc.next();
                    } while (a.equalsIgnoreCase("y"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
