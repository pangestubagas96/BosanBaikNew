package project.pangestubagas.com.bosanbaiknew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import project.pangestubagas.com.bosanbaiknew.Models.Cart;

public class DetailPakaianClient extends AppCompatActivity {

    TextView txtNama,txtKategori,txtHarga,txtDeskripsi,txtJumlah;
    Spinner ukuran;
    Button btnCart;
    DatabaseReference databaseBosanBaik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pakaian_client);

        txtNama = findViewById(R.id.txtNamaPakaianCL);
        txtKategori = findViewById(R.id.txtKategoriCL);
        txtHarga = findViewById(R.id.txtHargaCL);
        txtDeskripsi = findViewById(R.id.txtDeskripsiCL);
        txtJumlah = findViewById(R.id.txtJumlahCL);

        ukuran = findViewById(R.id.spinnerUkuranCL);

        btnCart = findViewById(R.id.btnCart);

        databaseBosanBaik = FirebaseDatabase.getInstance().getReference("Cart");

        Intent i = getIntent();
        final String idPakaian = i.getStringExtra("idPakaian");
        final String namaPakaian = i.getStringExtra("namaPakaian");
        final String kategori = i.getStringExtra("kategori");
        final String harga = i.getStringExtra("harga");
        final String deskripsi = i.getStringExtra("deskripsi");
        final String ukuran = i.getStringExtra("ukuran");
        final String email = i.getStringExtra("email");

        txtNama.setText(namaPakaian);
        txtHarga.setText(harga);
        txtDeskripsi.setText(deskripsi);
        txtKategori.setText(kategori);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hartot = Integer.parseInt(harga) * Integer.parseInt(txtJumlah.getText().toString());
                String id = databaseBosanBaik.push().getKey();
                Cart cart = new Cart(id,email,namaPakaian,String.valueOf(hartot),txtJumlah.getText().toString());
                databaseBosanBaik.child(id).setValue(cart);
                Toast.makeText(DetailPakaianClient.this, "Tambah Cart Berhasil", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),CartActivity.class);
                i.putExtra("idPakaian",idPakaian);
                i.putExtra("namaPakaian",namaPakaian);
                i.putExtra("jumlah",txtJumlah.getText().toString());
                startActivity(i);
            }
        });

    }

}
