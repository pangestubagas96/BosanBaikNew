package project.pangestubagas.com.bosanbaiknew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import project.pangestubagas.com.bosanbaiknew.Adapter.PakaianAdapter;
import project.pangestubagas.com.bosanbaiknew.Models.Pakaian;

public class MenuActivity extends AppCompatActivity {
    Button btnTambah;
    Button btnKembali;
    private PakaianAdapter mAdapter;
    private List<Pakaian> mDaftarPakaian = new ArrayList<>();
    DatabaseReference databaseBosanBaik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final RecyclerView recyclerView = findViewById(R.id.rvpakaian);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        databaseBosanBaik = FirebaseDatabase.getInstance().getReference();
        databaseBosanBaik.child("pakaian").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mDaftarPakaian = new ArrayList<>();
                for (DataSnapshot noteDataSnapShot : dataSnapshot.getChildren()){
                    Pakaian pakaian = noteDataSnapShot.getValue(Pakaian.class);
                    pakaian.setId_pakaian(noteDataSnapShot.getKey());
                    mDaftarPakaian.add(pakaian);
                }
                mAdapter = new PakaianAdapter(MenuActivity.this,mDaftarPakaian);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+""+databaseError.getMessage());
            }
        });
        btnTambah = findViewById(R.id.btnTambahData);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),TambahDataAdmin.class));
            }
        });
        btnKembali = findViewById(R.id.btnKembaliAdmin);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AdminActivity.class));
            }
        });
    }
}
