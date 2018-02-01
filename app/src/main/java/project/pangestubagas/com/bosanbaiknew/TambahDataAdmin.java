package project.pangestubagas.com.bosanbaiknew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import project.pangestubagas.com.bosanbaiknew.Models.Pakaian;

public class TambahDataAdmin extends AppCompatActivity {

    EditText namaPakaian,harga,deskripsi;
    Spinner kategori,ukuran;
    Button btnSimpan,btnKembali;

    DatabaseReference databaseBosanBaik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_admin);

        databaseBosanBaik = FirebaseDatabase.getInstance().getReference("pakaian");

        namaPakaian = (EditText) findViewById(R.id.editTextNama);
        harga = (EditText) findViewById(R.id.editTextHarga);
        deskripsi = (EditText) findViewById(R.id.EditTextDeskripsi);

        kategori = (Spinner) findViewById(R.id.spinnerKat);
        ukuran = (Spinner) findViewById(R.id.spinnerukuran);

        btnSimpan = (Button) findViewById(R.id.btnSimanData);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDataPakaian();
            }
        });

        btnKembali = (Button) findViewById(R.id.btnKembaliTmbah);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AdminActivity.class));
            }
        });
    }

    public void addDataPakaian(){
        if (!TextUtils.isEmpty(namaPakaian.toString())&&!TextUtils.isEmpty(harga.toString())&&!TextUtils.isEmpty(deskripsi.toString())){
            String id = databaseBosanBaik.push().getKey();
            Pakaian pakaian = new Pakaian(id,namaPakaian.getText().toString(),kategori.getSelectedItem().toString(),harga.getText().toString(),deskripsi.getText().toString(),ukuran.getSelectedItem().toString());
            databaseBosanBaik.child(id).setValue(pakaian);
            Toast.makeText(this, "Tambah Data Berhasil", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplication(),MenuActivity.class);
            startActivity(i);
        }  else {
            Toast.makeText(this, "Semua Data Harus diisi", Toast.LENGTH_SHORT).show();
        }
    }
}
