package project.pangestubagas.com.bosanbaiknew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import project.pangestubagas.com.bosanbaiknew.Adapter.PakaianAdapterClient;
import project.pangestubagas.com.bosanbaiknew.Models.Pakaian;

public class MenuPakaianClient extends AppCompatActivity {
    private PakaianAdapterClient mAdapter;
    private List<Pakaian> mMenuPakaian = new ArrayList<>();
    private DatabaseReference databaseBosanBaik;
    private Button btnLogout;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pakaian_client);

        firebaseAuth = FirebaseAuth.getInstance();
        Intent i = getIntent();
        final String email = i.getStringExtra("email");

        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(),Login.class));
        }


        final RecyclerView rvMenuClient = (RecyclerView) findViewById(R.id.rvmenuclient);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvMenuClient.setLayoutManager(mLayoutManager);
        databaseBosanBaik = FirebaseDatabase.getInstance().getReference("pakaian");
//        databaseBosanBaik.child("pakaian");
        databaseBosanBaik.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mMenuPakaian = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Pakaian pakaian = noteDataSnapshot.getValue(Pakaian.class);
                    pakaian.setId_pakaian(noteDataSnapshot.getKey());
                    mMenuPakaian.add(pakaian);
                }
                mAdapter = new PakaianAdapterClient(MenuPakaianClient.this, mMenuPakaian,email);
                rvMenuClient.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnLogout = findViewById(R.id.btnKembaliClient);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DrawerActivity.class));
            }
        });
    }
}
