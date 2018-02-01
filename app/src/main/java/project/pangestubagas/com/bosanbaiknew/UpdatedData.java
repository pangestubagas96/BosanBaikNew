package project.pangestubagas.com.bosanbaiknew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import project.pangestubagas.com.bosanbaiknew.Models.Pakaian;

public class UpdatedData extends AppCompatActivity {
    EditText txtNamaUpdate;
    Spinner txtKategoriUpdate, txtUkuranUpdate;
    EditText txtHargaUpdate;
    EditText txtDeskripsiUpdate;
    Button btnUpdate;
    DatabaseReference databaseBosanBaik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updated_data);

        Intent i = getIntent();
        final String idPakaian = i.getStringExtra("idPakaian");
        final String namaPakaian = i.getStringExtra("namaPakaian");
        final String kategori = i.getStringExtra("kategori");
        final String harga = i.getStringExtra("harga");
        final String deskripsi = i.getStringExtra("deskripsi");
        final String ukuran = i.getStringExtra("ukuran");

        txtNamaUpdate = (EditText) findViewById(R.id.UpdateNama);
        txtKategoriUpdate = (Spinner) findViewById(R.id.spinnerKatUpdate);
        txtHargaUpdate = (EditText) findViewById(R.id.UpdateHarga);
        txtDeskripsiUpdate = (EditText) findViewById(R.id.UpdateDeskripsi);
        txtUkuranUpdate = (Spinner) findViewById(R.id.spinnerukuranUpdate);

        btnUpdate = (Button) findViewById(R.id.btnSimanDataUpdate);

        txtNamaUpdate.setText(namaPakaian);
        txtHargaUpdate.setText(harga);
        txtDeskripsiUpdate.setText(deskripsi);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseBosanBaik = FirebaseDatabase.getInstance().getReference("pakaian").child(idPakaian);
                Pakaian menuPakaian = new Pakaian(idPakaian,txtNamaUpdate.getText().toString(),txtKategoriUpdate.getSelectedItem().toString(),txtHargaUpdate.getText().toString(),txtDeskripsiUpdate.getText().toString(),txtUkuranUpdate.getSelectedItem().toString());
                databaseBosanBaik.setValue(menuPakaian);
                Toast.makeText(UpdatedData.this, "Update Data Berhasil", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(i);
            }
        });
    }
}
