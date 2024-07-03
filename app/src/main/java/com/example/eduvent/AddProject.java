package com.example.eduvent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddProject extends AppCompatActivity {

    private EditText pjName, ltName, pjDes;
    private ImageView imageView;
    private LinearLayout btnSubmit;
    private DatabaseHelper dbHelper;
    private TextView pjFoto;
    private byte[] imageBytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.p_header2));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.p_btua2));

        pjName = findViewById(R.id.pjName);
        ltName = findViewById(R.id.ltName);
        pjDes = findViewById(R.id.pjDes);
        pjFoto = findViewById(R.id.pjFoto);
        imageView = findViewById(R.id.imageView);
        btnSubmit = findViewById(R.id.btnSubmit);

        dbHelper = new DatabaseHelper(this);
        pjFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle image selection
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
            }
        });

        pjFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle image selection
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String projectName = pjName.getText().toString().trim();
                String lecturerName = ltName.getText().toString().trim();
                String description = pjDes.getText().toString().trim();

                Project project = new Project();
                project.setProjectName(projectName);
                project.setLecturerName(lecturerName);
                project.setDescription(description);
                project.setImage(imageBytes);

                dbHelper.addProject(project);

                Toast.makeText(AddProject.this, "Project Added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddProject.this, LecturesProject.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                imageBytes = stream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
