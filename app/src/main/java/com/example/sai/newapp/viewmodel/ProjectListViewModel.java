package com.example.sai.newapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.sai.newapp.service.model.Project;
import com.example.sai.newapp.service.repo.ProjectRepo;

import java.util.List;

public class ProjectListViewModel extends AndroidViewModel {
    private final LiveData<List<Project>> projectListObservable;

    public ProjectListViewModel(@NonNull Application application) {
        super(application);
        this.projectListObservable = ProjectRepo.getInstance().getProjectList("naidusaibabu");
    }

    public LiveData<List<Project>> getProjectListObservable() {
        return projectListObservable;
    }
}
