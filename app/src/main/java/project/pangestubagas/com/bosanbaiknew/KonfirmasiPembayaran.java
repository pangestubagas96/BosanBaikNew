package project.pangestubagas.com.bosanbaiknew;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import project.pangestubagas.com.bosanbaiknew.Adapter.KonfirmasiAdapter;
import project.pangestubagas.com.bosanbaiknew.Models.Cart;

public class KonfirmasiPembayaran extends AppCompatActivity {
    TextView txtNamaPelanggan,txtHarga;
    private KonfirmasiAdapter mKonfirmasiAdapter;
    private List<Cart> mDaftarCart;
    Button btnKonfirmasi;
    DatabaseReference DatabaseBosanBaik;
    ArrayList<String> idCart = new ArrayList<>();
    ArrayList<String> namaPakaian = new ArrayList<>();
    ArrayList<String> jumlah = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pembayaran);

        final RecyclerView recyclerView = findViewById(R.id.rvKonfirm);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        txtNamaPelanggan = findViewById(R.id.pelanggankonfirm);
        txtHarga = findViewById(R.id.hartot);

        Intent i = getIntent();
        final String namaPelanggan = i.getStringExtra("namaPelanggan");
        final String harga = i.getStringExtra("harga");

        txtNamaPelanggan.setText(namaPelanggan);
        txtHarga.setText(harga);

        DatabaseBosanBaik = FirebaseDatabase.getInstance().getReference();
        DatabaseBosanBaik.child("Cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mDaftarCart = new ArrayList<>();
                for (DataSnapshot noteDataSnapShot : dataSnapshot.getChildren()){
                    Cart cart = noteDataSnapShot.getValue(Cart.class);
                    cart.setIdCart(noteDataSnapShot.getKey());
                    mDaftarCart.add(cart);
                    idCart.add(cart.getIdCart());
                    namaPakaian.add(cart.getNamaPakaian());
                    jumlah.add(cart.getJumlah());
                }
                mKonfirmasiAdapter = new KonfirmasiAdapter(KonfirmasiPembayaran.this,mDaftarCart);
                recyclerView.setAdapter(mKonfirmasiAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+""+databaseError.getMessage());
            }
        });

        btnKonfirmasi = findViewById(R.id.btnOK);
        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),OKKonfirm.class));
            }
        });
    }
}
