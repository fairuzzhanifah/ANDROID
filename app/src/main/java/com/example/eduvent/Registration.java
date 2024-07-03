package com.example.eduvent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Registration extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.p_header2));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.p_btua2));

        TextView buttonSelectFile1 = findViewById(R.id.buttonSelectFile1);
        TextView buttonSelectFile3 = findViewById(R.id.buttonSelectFile3);
        TextView buttonSelectFile2 = findViewById(R.id.buttonSelectFile2);

        buttonSelectFile1.setOnClickListener(v -> openFilePicker());
        buttonSelectFile2.setOnClickListener(v -> openFilePicker());
        buttonSelectFile3.setOnClickListener(v -> openFilePicker());

        LinearLayout llBack = findViewById(R.id.back);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, HomeMahasiswa.class);
                startActivity(intent);
            }
        });

        LinearLayout llHome = findViewById(R.id.home);
        llHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, HomeMahasiswa.class);
                startActivity(intent);
            }
        });

        LinearLayout llProfile = findViewById(R.id.profile);
        llProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, Profile.class);
                startActivity(intent);
            }
        });

    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // You can change this to specific type like "image/*" for images
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a file"), PICK_FILE_REQUEST);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                String path = uri.getPath();
                Toast.makeText(this, "File selected: " + path, Toast.LENGTH_LONG).show();
                // You can add your own logic to handle the file here
            }
        }
    }
}