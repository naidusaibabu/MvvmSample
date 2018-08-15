package com.example.sai.newapp.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.module.GlideModule;
import com.example.sai.newapp.R;
import com.example.sai.newapp.service.model.Project;
import com.example.sai.newapp.view.adapter.ProjectAdapter;
import com.example.sai.newapp.viewmodel.ProjectListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProjectListActivity extends AppCompatActivity {
    private List<Project> projectList = new ArrayList<>();
    private RecyclerView projListRV;
    private LinearLayoutManager layoutManager;
    private ProjectAdapter mAdapter;
    private ProjectListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        projListRV = findViewById(R.id.projListRV);
        layoutManager = new LinearLayoutManager(ProjectListActivity.this);
        projListRV.setLayoutManager(layoutManager);
        viewModel = ViewModelProviders.of(this).get(ProjectListViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        observeViewModel(viewModel);
    }

    private void observeViewModel(ProjectListViewModel projectListViewModel) {
        projectListViewModel.getProjectListObservable().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                if (projects != null && projects.size() > 0) {
                    projectList.addAll(projects);
                    runOnUiThread(returnValue);
                }
            }
        });
    }

    private Runnable returnValue = new Runnable() {
        @Override
        public void run() {
            mAdapter = new ProjectAdapter(projectList);
            projListRV.setAdapter(mAdapter);
        }
    };
}
