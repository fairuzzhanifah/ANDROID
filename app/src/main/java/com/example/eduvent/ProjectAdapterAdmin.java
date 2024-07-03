package com.example.eduvent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProjectAdapterAdmin extends RecyclerView.Adapter<ProjectAdapterAdmin.ProjectViewHolder> {

    private Context context;
    private List<Project> projectList;

    public ProjectAdapterAdmin(Context context, List<Project> projectList) {
        this.context = context;
        this.projectList = projectList;}

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Project project = projectList.get(position);

        holder.projectName.setText(project.getProjectName());
        holder.lecturerName.setText(project.getLecturerName());
        holder.description.setText(project.getDescription());

        Bitmap bitmap = BitmapFactory.decodeByteArray(project.getImage(), 0, project.getImage().length);
        holder.imageView.setImageBitmap(bitmap);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProjectEdit.class);
                DataHolder.getInstance().setProject(project);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {

        public TextView projectName;
        public TextView lecturerName;
        public TextView description;
        public ImageView imageView;

        public ProjectViewHolder(View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.projectName);
            lecturerName = itemView.findViewById(R.id.lecturerName);
            description = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
