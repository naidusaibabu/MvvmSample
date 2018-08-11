package com.example.sai.newapp.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sai.newapp.R;
import com.example.sai.newapp.service.model.Project;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private List<Project> projectList;

    class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView projId;
        private TextView projName;

        private ViewHolder(View v) {
            super(v);
            projId = v.findViewById(R.id.projIdTV);
            projName = v.findViewById(R.id.projNameTV);
        }
    }

    public ProjectAdapter(List<Project> projects) {
        this.projectList = projects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.projId.setText(projectList.get(position).getId() + "");
        holder.projName.setText(projectList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return projectList == null ? 0 : projectList.size();
    }


}
