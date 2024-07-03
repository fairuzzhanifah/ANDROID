package com.example.eduvent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "projectDatabase3.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PROJECTS = "projects";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PROJECT_NAME = "project_name";
    private static final String COLUMN_LECTURER_NAME = "lecturer_name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_IMAGE = "image";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROJECTS_TABLE = "CREATE TABLE " + TABLE_PROJECTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PROJECT_NAME + " TEXT,"
                + COLUMN_LECTURER_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_IMAGE + " BLOB" + ")";
        db.execSQL(CREATE_PROJECTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECTS);
        onCreate(db);
    }

    public void addProject(Project project) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PROJECT_NAME, project.getProjectName());
        values.put(COLUMN_LECTURER_NAME, project.getLecturerName());
        values.put(COLUMN_DESCRIPTION, project.getDescription());
        values.put(COLUMN_IMAGE, project.getImage());
        db.insert(TABLE_PROJECTS, null, values);
        db.close();
    }

    public List<Project> getAllProjects() {
        List<Project> projectList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PROJECTS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Project project = new Project();
                project.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                project.setProjectName(cursor.getString(cursor.getColumnIndex(COLUMN_PROJECT_NAME)));
                project.setLecturerName(cursor.getString(cursor.getColumnIndex(COLUMN_LECTURER_NAME)));
                project.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                project.setImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE)));
                projectList.add(project);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return projectList;
    }

    public boolean deleteProject(int projectId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("projects", "id = ?", new String[]{String.valueOf(projectId)}) > 0;
    }
}
