package com.example.eduvent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        databaseHelper = new DatabaseHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);

        if (isFirstRun) {
//            insertDefaultProject();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstRun", false);
            editor.apply();
        }

        LinearLayout llStart = findViewById(R.id.start);

        llStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void insertDefaultProject() {
        String projectName = "Project Name";
        String lecturerName = "Lecture";
        String description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";

        Project project = new Project();
        project.setProjectName(projectName);
        project.setLecturerName(lecturerName);
        project.setDescription(description);
//        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//        Bitmap bitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.maingambar);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        byte[] imageInByte = baos.toByteArray();
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maingambar);
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] imageBytes = stream.toByteArray();
//        project.setImage(imageInByte);

        databaseHelper.addProject(project);
    }
}
