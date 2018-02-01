package project.pangestubagas.com.bosanbaiknew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import project.pangestubagas.com.bosanbaiknew.Models.Pelanggan;

public class InsertData extends AppCompatActivity {
    TextView txtNama,txtAlamat,txtNoHp;
    Button btnTambahPelanggan;
    DatabaseReference databaseBosanBaik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        txtNama = findViewById(R.id.ETNama);
        txtAlamat = findViewById(R.id.ETAlamat);
        txtNoHp = findViewById(R.id.ETNoHp);

        Intent i = getIntent();
        final ArrayList<String> idCart = i.getStringArrayListExtra("idCart");
        final ArrayList<String> namaPakaian = i.getStringArrayListExtra("namaPakaian");
        final ArrayList<String> harga = i.getStringArrayListExtra("harga");
        final ArrayList<String> jumlah = i.getStringArrayListExtra("jumlah");

        databaseBosanBaik = FirebaseDatabase.getInstance().getReference("pelanggan");

        btnTambahPelanggan = findViewById(R.id.btnTambahDataPelanggan);
        btnTambahPelanggan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(txtNama.toString())&&!TextUtils.isEmpty(txtAlamat.toString())&&!TextUtils.isEmpty(txtNoHp.toString())){
                    String id = databaseBosanBaik.push().getKey();
                    Pelanggan pelanggan = new Pelanggan(id,txtNama.getText().toString(),txtAlamat.getText().toString(),txtNoHp.getText().toString());
                    databaseBosanBaik.child(id).setValue(pelanggan);
                    Intent i = new Intent(getApplicationContext(),KonfirmasiPembayaran.class);
                    i.putExtra("idCart",idCart);
                    i.putExtra("namaPakaian",namaPakaian);
                    i.putExtra("harga",harga);
                    i.putExtra("jumlah",jumlah);
                    i.putExtra("namaPelanggan",txtNama.getText().toString());
                    startActivity(i);
                }
                else {
                    Toast.makeText(InsertData.this, "Semua Data Harus Diisi", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
