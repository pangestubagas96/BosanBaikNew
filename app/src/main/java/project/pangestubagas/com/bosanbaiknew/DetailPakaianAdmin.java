package project.pangestubagas.com.bosanbaiknew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailPakaianAdmin extends AppCompatActivity {

    TextView txtNamaPakaian, txtKategori, txtHarga, txtDeskripsi, txtUkuran;
    Button btnDel, btnUpdate;
    DatabaseReference databaseBosanBaik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pakaian_admin);

        txtNamaPakaian = (TextView) findViewById(R.id.TxtDetailNama);
        txtKategori = (TextView) findViewById(R.id.TxtDetailKategori);
        txtHarga = (TextView) findViewById(R.id.txtDetailHarga);
        txtDeskripsi = (TextView) findViewById(R.id.txtDetailDeskrips);
        txtUkuran = (TextView) findViewById(R.id.txtDetailUkuran);

        btnUpdate = (Button) findViewById(R.id.btnUpdateUpdate);
        btnDel = (Button) findViewById(R.id.btnDelUpdate);

        Intent i = getIntent();
        final String idPakaian = i.getStringExtra("idPakaian");
        final String namaPakaian = i.getStringExtra("namaPakaian");
        final String kategori = i.getStringExtra("kategori");
        final String harga = i.getStringExtra("harga");
        final String deskripsi = i.getStringExtra("deskripsi");
        final String ukuran = i.getStringExtra("ukuran");

        txtNamaPakaian.setText(namaPakaian);
        txtHarga.setText(harga);
        txtDeskripsi.setText(deskripsi);
        txtKategori.setText(kategori);
        txtUkuran.setText(ukuran);

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseBosanBaik = FirebaseDatabase.getInstance().getReference("pakaian").child(idPakaian);
                databaseBosanBaik.removeValue();
                Toast.makeText(DetailPakaianAdmin.this, "Data Pakaian Telah Dihapus", Toast.LENGTH_SHORT).show();
                Intent p = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(p);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),UpdatedData.class);
                i.putExtra("idPakaian",idPakaian);
                i.putExtra("namaPakaian",namaPakaian);
                i.putExtra("kategori",kategori);
                i.putExtra("harga",harga);
                i.putExtra("deskripsi",deskripsi);
                i.putExtra("ukuran",ukuran);
                startActivity(i);
            }
        });

    }
}
