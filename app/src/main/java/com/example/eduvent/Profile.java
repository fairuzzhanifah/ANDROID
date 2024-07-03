package com.example.eduvent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Profile extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText editName, editEmail, editPhone;
    private LinearLayout btnSave;
    private LinearLayout btnLogout;
    private ImageView imageProfile;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.p_header2));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.p_btua2));


        editName = findViewById(R.id.Emailedt);
        editEmail = findViewById(R.id.Usernameedt);
        editPhone = findViewById(R.id.Passwordedt);
        btnSave = findViewById(R.id.update);
        imageProfile = findViewById(R.id.imageProfile);

        btnLogout = findViewById(R.id.logout);


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(Profile.this, Login.class);
                startActivity(intent);
                finish();
            }
        });


        loadProfile();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });

        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        ImageView llHistory = findViewById(R.id.history);
        llHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, History.class);
                startActivity(intent);
            }
        });

        LinearLayout llHome = findViewById(R.id.home);
        llHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
                String userLevel = sharedPreferences.getString("userLevel", "");
                if (userLevel.equals("mahasiswa")) {
                    Intent intent = new Intent(Profile.this, HomeMahasiswa.class);
                    startActivity(intent);
                } else if (userLevel.equals("dosen")) {
                    Intent intent = new Intent(Profile.this, HomeDosen.class);
                    startActivity(intent);
                } else if (userLevel.equals("lectures")) {
                    Intent intent = new Intent(Profile.this, LecturesProject.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        String userLevel = sharedPreferences.getString("userLevel", "");

        if (userLevel.equals("mahasiswa")) {
            Intent intent = new Intent(Profile.this, HomeMahasiswa.class);
            startActivity(intent);
        } else if (userLevel.equals("dosen")) {
            Intent intent = new Intent(Profile.this, HomeDosen.class);
            startActivity(intent);
        } else if (userLevel.equals("lectures")) {
            Intent intent = new Intent(Profile.this, LecturesProject.class);
            startActivity(intent);
        } else {
            super.onBackPressed();
        }
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageProfile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadProfile() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String email = sharedPreferences.getString("email", "");
        String phone = sharedPreferences.getString("phone", "");

        editName.setText(name);
        editEmail.setText(email);
        editPhone.setText(phone);

        String imagePath = sharedPreferences.getString("imagePath", "");
        if (!imagePath.equals("")) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageProfile.setImageBitmap(bitmap);
        }
    }

    private void saveProfile() {
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();
        String phone = editPhone.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("phone", phone);

        boolean isImageSaved = false;
        if (imageUri != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                String path = saveToInternalStorage(bitmap);
                editor.putString("imagePath", path);
                isImageSaved = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        boolean isSaved = editor.commit();

        if (isSaved) {
            if (isImageSaved) {
                Toast.makeText(Profile.this, "Profile and Image saved successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Profile.this, "Profile saved successfully", Toast.LENGTH_SHORT).show();
            }
            recreate();  // Refresh activity
        } else {
            Toast.makeText(Profile.this, "Failed to save profile", Toast.LENGTH_SHORT).show();
        }
    }

    private String saveToInternalStorage(Bitmap bitmap) throws IOException {
        Context context = getApplicationContext();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        stream.close();

        String fileName = "profile_image.png";
        FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        fos.write(byteArray);
        fos.close();

        return context.getFileStreamPath(fileName).getAbsolutePath();
    }
}
