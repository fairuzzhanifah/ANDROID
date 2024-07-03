package com.example.eduvent;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {
    EditText edtUsername, edtPaswd, edtCPaswd, edtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        edtEmail = findViewById(R.id.Emailedt);
        edtUsername = findViewById(R.id.Usernameedt);
        edtPaswd = findViewById(R.id.Passwordedt);
        edtCPaswd = findViewById(R.id.CPasswordedt);

        LinearLayout btReg = findViewById(R.id.btnRegister);

        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eml = edtEmail.getText().toString().trim();
                String usrnm = edtUsername.getText().toString().trim();
                String pwd = edtPaswd.getText().toString().trim();
                String cpwd = edtCPaswd.getText().toString().trim();

                if (TextUtils.isEmpty(eml)) {
                    edtEmail.setError("Email tidak boleh kosong!");
                } else if (TextUtils.isEmpty(usrnm)) {
                    edtUsername.setError("Username tidak boleh kosong!");
                } else if (TextUtils.isEmpty(pwd)) {
                    edtPaswd.setError("Password tidak boleh kosong!");
                } else if (TextUtils.isEmpty(cpwd)) {
                    edtCPaswd.setError("Harap Confirmasi Password Anda!");
                } else if (!pwd.equals(cpwd)) {
                    edtCPaswd.setError("Password dan konfirmasi password tidak cocok!");
                } else {
                    Intent intent = new Intent(Register.this, HomeMahasiswa.class);
                    startActivity(intent);
                }
            }
        });

    }
}