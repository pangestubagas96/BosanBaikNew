package project.pangestubagas.com.bosanbaiknew.Models;

/**
 * Created by GJH IT-ASUS on 16/01/2018.
 */

public class Pakaian {
    String id_pakaian,namaPakaian,kategori,Harga,Deskripsi,ukuran;

    public Pakaian(String id_pakaian, String namaPakaian, String kategori, String harga, String deskripsi, String ukuran) {
        this.id_pakaian = id_pakaian;
        this.namaPakaian = namaPakaian;
        this.kategori = kategori;
        Harga = harga;
        Deskripsi = deskripsi;
        this.ukuran = ukuran;
    }

    public Pakaian() {

    }

    public String getId_pakaian() {
        return id_pakaian;
    }

    public void setId_pakaian(String id_pakaian) {
        this.id_pakaian = id_pakaian;
    }

    public String getNamaPakaian() {
        return namaPakaian;
    }

    public void setNamaPakaian(String namaPakaian) {
        this.namaPakaian = namaPakaian;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }
}
