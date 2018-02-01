package project.pangestubagas.com.bosanbaiknew.Models;

/**
 * Created by GJH IT-ASUS on 30/01/2018.
 */

public class Pesanan {
    String idPesanan, nama_pelanggan, nama_pakaian, jumlahPakaian, hargaPakaian,status;

    public Pesanan(String idPesanan, String nama_pelanggan, String nama_pakaian, String jumlahPakaian, String hargaPakaian, String status) {
        this.idPesanan = idPesanan;
        this.nama_pelanggan = nama_pelanggan;
        this.nama_pakaian = nama_pakaian;
        this.jumlahPakaian = jumlahPakaian;
        this.hargaPakaian = hargaPakaian;
        this.status = status;
    }

    public Pesanan() {
    }

    public String getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(String idPesanan) {
        this.idPesanan = idPesanan;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public void setNama_pelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
    }

    public String getNama_pakaian() {
        return nama_pakaian;
    }

    public void setNama_pakaian(String nama_pakaian) {
        this.nama_pakaian = nama_pakaian;
    }

    public String getJumlahPakaian() {
        return jumlahPakaian;
    }

    public void setJumlahPakaian(String jumlahPakaian) {
        this.jumlahPakaian = jumlahPakaian;
    }

    public String getHargaPakaian() {
        return hargaPakaian;
    }

    public void setHargaPakaian(String hargaPakaian) {
        this.hargaPakaian = hargaPakaian;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
