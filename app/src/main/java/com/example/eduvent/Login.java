package com.example.eduvent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    EditText edID, edPsswd;
    LinearLayout llLogin;
    TextView tvRegister;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        edID = findViewById(R.id.YourID);
        edPsswd = findViewById(R.id.Password);
        llLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.TextRegister);

        mContext = this;
        llLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrnm = edID.getText().toString().trim();
                String pwd = edPsswd.getText().toString().trim();

                if (TextUtils.isEmpty(usrnm)) {
                    edID.setError("ID tidak boleh kosong");
                } else if (TextUtils.isEmpty(pwd)) {
                    edPsswd.setError("Password tidak boleh kosong");
                } else {

                    SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (usrnm.equals("mahasiswa") && pwd.equals("mahasiswa")) {
                        editor.putString("userLevel", "mahasiswa");
                        editor.apply();
                        Intent intent = new Intent(Login.this, HomeMahasiswa.class);
                        startActivity(intent);
                    } else if (usrnm.equals("dosen") && pwd.equals("dosen")) {
                        editor.putString("userLevel", "dosen");
                        editor.apply();
                        Intent intent = new Intent(Login.this, HomeDosen.class);
                        startActivity(intent);
                    } else if (usrnm.equals("admin") && pwd.equals("admin")) {
                        editor.putString("userLevel", "lectures");
                        editor.apply();
                        Intent intent = new Intent(Login.this, LecturesProject.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Login failed. Please check your username and password.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

    }
}