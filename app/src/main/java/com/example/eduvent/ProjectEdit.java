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
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ProjectEdit extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private int projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_project_edit);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.p_header2));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.p_btua2));

        dbHelper = new DatabaseHelper(this);

        // Retrieve the project from the singleton
        Project project = DataHolder.getInstance().getProject();
        if (project == null) {
            Toast.makeText(this, "Failed to load project data", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        projectId = project.getId();
        String projectName = project.getProjectName();
        String lecturerName = project.getLecturerName();
        String description = project.getDescription();
        byte[] imageBytes = project.getImage();

        // Find views and set data
        TextView tvProjectName1 = findViewById(R.id.pjName1);
        TextView tvProjectName2 = findViewById(R.id.pjName2);
        TextView tvLecture = findViewById(R.id.pjLecture);
        TextView tvDescription = findViewById(R.id.tvDescription);
        ImageView ivImage = findViewById(R.id.imageView);
        ImageView ivDelete = findViewById(R.id.hapus); // Delete button

        LinearLayout llBack = findViewById(R.id.back);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProjectEdit.this, LecturesProject.class);
                startActivity(intent);
            }
        });

        LinearLayout llHome = findViewById(R.id.home);
        llHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProjectEdit.this, LecturesProject.class);
                startActivity(intent);
            }
        });

        tvProjectName1.setText(projectName);
        tvProjectName2.setText(projectName);
        tvDescription.setText(description);
        tvLecture.setText(lecturerName);

        if (imageBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            ivImage.setImageBitmap(bitmap);
        }

        // Set up delete button click listener
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProject(projectId);
            }
        });
    }

    private void deleteProject(int projectId) {
        if (projectId != -1) {
            boolean isDeleted = dbHelper.deleteProject(projectId);
            if (isDeleted) {
                Toast.makeText(this, "Project deleted successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProjectEdit.this, LecturesProject.class);
                startActivity(intent);
                finish(); // Close the current activity
            } else {
                Toast.makeText(this, "Failed to delete project", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid project ID", Toast.LENGTH_SHORT).show();
        }
    }
}
