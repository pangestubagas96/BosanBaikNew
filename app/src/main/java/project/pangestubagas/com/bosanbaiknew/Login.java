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

public class Login extends AppCompatActivity {

    Button buttonSignIn, btnRegClick;
    EditText loginEmail;
    EditText loginPass;


    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }

        loginEmail = (EditText) findViewById(R.id.LoginEmail);
        loginPass = (EditText) findViewById(R.id.LoginPass);

        btnRegClick = findViewById(R.id.btnRegClick);
        btnRegClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegistrasiUser.class));
            }
        });

        buttonSignIn = (Button) findViewById(R.id.btnLogin);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }
    public void loginUser(){
        if (TextUtils.isEmpty(loginEmail.getText().toString())){
            Toast.makeText(this, "Enter your Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(loginPass.getText().toString())){
            Toast.makeText(this, "Enter your Password", Toast.LENGTH_SHORT).show();
            return;
        }

        final String email = loginEmail.getText().toString();

        progressDialog.setMessage("Login Process....");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(loginEmail.getText().toString(),loginPass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            finish();
                            if (loginEmail.getText().toString().equals("admin@gmail.com")){
                                startActivity(new Intent(getApplicationContext(),AdminActivity.class));
                            }
                            else {
                                Intent i = new Intent(getApplicationContext(),DrawerActivity.class);
                                i.putExtra("email",email);
                                startActivity(i);
                            }
                            Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(Login.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
