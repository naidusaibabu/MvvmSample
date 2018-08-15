package com.example.sai.newapp.service.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.widget.Toast;

import com.example.sai.newapp.service.model.Project;
import com.example.sai.newapp.util.Constants;
import com.example.sai.newapp.util.ServiceConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectRepo {
    private GitHubService gitHubService;
    private static ProjectRepo projectRepository;
    private ProjectRepo() {
        //TODO this gitHubService instance will be injected using Dagger ...
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceConstants.GIT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gitHubService = retrofit.create(GitHubService.class);
    }

    public synchronized static ProjectRepo getInstance() {
        //TODO No need to implement this singleton , since Dagger will handle it ...
        if (projectRepository == null) {
            projectRepository = new ProjectRepo();
        }
        return projectRepository;
    }
    public LiveData<List<Project>> getProjectList(String userName){
        final MutableLiveData<List<Project>> data = new MutableLiveData<>();
        gitHubService.getProjectList(userName).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                if(response.code() == Constants.SUCCESS){
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                System.err.print("No Project found");
            }
        });
        return data;
    }
}
