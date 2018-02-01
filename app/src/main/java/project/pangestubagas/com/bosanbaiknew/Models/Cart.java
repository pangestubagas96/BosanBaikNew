package project.pangestubagas.com.bosanbaiknew.Models;

/**
 * Created by GJH IT-ASUS on 27/01/2018.
 */

public class Cart {
    String idCart,user,namaPakaian,harga,jumlah;

    public Cart(String idCart, String user, String namaPakaian, String harga, String jumlah) {
        this.idCart = idCart;
        this.user = user;
        this.namaPakaian = namaPakaian;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    public Cart() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNamaPakaian() {
        return namaPakaian;
    }

    public void setNamaPakaian(String namaPakaian) {
        this.namaPakaian = namaPakaian;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getIdCart() {
        return idCart;
    }

    public void setIdCart(String idCart) {
        this.idCart = idCart;
    }
}
