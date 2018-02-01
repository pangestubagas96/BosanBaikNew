package project.pangestubagas.com.bosanbaiknew;

import android.content.Intent;
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

import project.pangestubagas.com.bosanbaiknew.Adapter.CartAdapter;
import project.pangestubagas.com.bosanbaiknew.Models.Cart;

public class CartActivity extends AppCompatActivity {
    Button btnLanjutShopping,btnConfirmPembayaran;
    TextView TxtHarga;
    private CartAdapter mCartAdapter;
    private List<Cart> mDaftarCart = new ArrayList<>();
    DatabaseReference databaseBosanBaik;
    ArrayList<String> harga = new ArrayList<String>();
    ArrayList<String> jumlah = new ArrayList<String>();
    ArrayList<String> namaPakaian = new ArrayList<String>();
    ArrayList<String> idCart = new ArrayList<String>();
    int hartot = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        final RecyclerView recyclerView = findViewById(R.id.rvCart);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        TxtHarga = findViewById(R.id.txtTotal);

        databaseBosanBaik = FirebaseDatabase.getInstance().getReference();
        databaseBosanBaik.child("Cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mDaftarCart = new ArrayList<>();
                for (DataSnapshot noteDataSnapShot : dataSnapshot.getChildren()){
                    Cart cart = noteDataSnapShot.getValue(Cart.class);
                    cart.setIdCart(noteDataSnapShot.getKey());
                    mDaftarCart.add(cart);
                    harga.add(cart.getHarga());
                    namaPakaian.add(cart.getNamaPakaian());
                    jumlah.add(cart.getJumlah());
                    idCart.add(cart.getIdCart());

                }
                mCartAdapter = new CartAdapter(CartActivity.this,mDaftarCart);
                recyclerView.setAdapter(mCartAdapter);

                for(int o = 0; o< harga.size();o++){
                    hartot = Integer.parseInt(harga.get(o)) + hartot;
                }
                TxtHarga.setText(String.valueOf(hartot));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+""+databaseError.getMessage());
            }
        });
        btnLanjutShopping = findViewById(R.id.btnLanjutShopping);
        btnLanjutShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MenuPakaianClient.class));
            }
        });
        btnConfirmPembayaran = findViewById(R.id.LanjutPembayaran);
        btnConfirmPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),InsertData.class);
                i.putExtra("idCart",idCart);
                i.putExtra("harga",harga);
                i.putExtra("namaPakaian",namaPakaian);
                i.putExtra("jumlah",jumlah);
                startActivity(i);
            }
        });
    }
}
