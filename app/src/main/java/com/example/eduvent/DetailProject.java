package com.example.eduvent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailProject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_project);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.p_header2));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.p_btua2));

//        Intent intent = getIntent();
//        String projectName = intent.getStringExtra("PROJECT_NAME");
//        String lecturerName = intent.getStringExtra("LECTURER_NAME");
//        String description = intent.getStringExtra("DESCRIPTION");
//        byte[] imageBytes = intent.getByteArrayExtra("IMAGE");

        // Find views and set data
        TextView tvProjectName = findViewById(R.id.tvProjectName);
        TextView tvDescription = findViewById(R.id.tvDescription);
        ImageView ivImage = findViewById(R.id.ivImage);

        LinearLayout llAplay = findViewById(R.id.Apply);
        llAplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailProject.this, Registration.class);
                startActivity(intent);
            }
        });

        LinearLayout llBack = findViewById(R.id.back);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailProject.this, HomeMahasiswa.class);
                startActivity(intent);
            }
        });

        LinearLayout llHome = findViewById(R.id.home);
        llHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailProject.this, HomeMahasiswa.class);
                startActivity(intent);
            }
        });

        LinearLayout llProfile = findViewById(R.id.profile);
        llProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailProject.this, Profile.class);
                startActivity(intent);
            }
        });

//        tvProjectName.setText(projectName);
//        tvDescription.setText(description);
//
//        if (imageBytes != null) {
//            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//            ivImage.setImageBitmap(bitmap);
//        }
    }
}