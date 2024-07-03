package com.example.eduvent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeMahasiswa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_mahasiswa);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.p_header2));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.p_btua2));

        LinearLayout llProfile = findViewById(R.id.MProfile);
        LinearLayout llBack = findViewById(R.id.MBack);
        ImageView llHistory = findViewById(R.id.history);
        llHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeMahasiswa.this, History.class);
                startActivity(intent);
            }
        });

        llProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeMahasiswa.this, Profile.class);
                startActivity(intent);
            }
        });

        LinearLayout llDProject = findViewById(R.id.llProject);
//        LinearLayout llDCandidatess = findViewById(R.id.llDCandidates);

        llDProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeMahasiswa.this, LecturesProjectMhs.class);
                startActivity(intent);
            }
        });

    }
}