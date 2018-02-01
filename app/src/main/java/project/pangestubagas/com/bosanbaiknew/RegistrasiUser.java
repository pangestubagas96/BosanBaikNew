package project.pangestubagas.com.bosanbaiknew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrasiUser extends AppCompatActivity {
    EditText txtEmail,txtPass;
    Button btnReg,txtLogin;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi_user);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        txtEmail = findViewById(R.id.txtRegEmail);
        txtPass = findViewById(R.id.txtRegPassword);

        btnReg = findViewById(R.id.BtnReg);
        txtLogin = findViewById(R.id.btnTxtLogin);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });
    }

    private void registerUser() {
        if (TextUtils.isEmpty(txtEmail.getText().toString())){
            Toast.makeText(this, "Masukkan Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(txtPass.getText().toString())){
            Toast.makeText(this, "Masukkan Password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(),txtPass.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    txtEmail.setText("");
                    txtPass.setText("");
                    Toast.makeText(RegistrasiUser.this, "Register Sukses", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Login.class));
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(RegistrasiUser.this, "Registasi Gagal, Silakan Coba Lagi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
