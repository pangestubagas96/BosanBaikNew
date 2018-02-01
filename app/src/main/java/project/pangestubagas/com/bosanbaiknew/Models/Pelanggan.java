package project.pangestubagas.com.bosanbaiknew.Models;

/**
 * Created by GJH IT-ASUS on 29/01/2018.
 */

public class Pelanggan {
    String idPelanggan,namaPelanggan,alamatPelanggan,noHp;

    public Pelanggan(String idPelanggan, String namaPelanggan, String alamatPelanggan, String noHp) {
        this.idPelanggan = idPelanggan;
        this.namaPelanggan = namaPelanggan;
        this.alamatPelanggan = alamatPelanggan;
        this.noHp = noHp;
    }

    public Pelanggan() {
    }

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(String idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getAlamatPelanggan() {
        return alamatPelanggan;
    }

    public void setAlamatPelanggan(String alamatPelanggan) {
        this.alamatPelanggan = alamatPelanggan;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
}
