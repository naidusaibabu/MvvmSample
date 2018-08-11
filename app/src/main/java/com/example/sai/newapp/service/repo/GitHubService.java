package com.example.sai.newapp.service.repo;

import com.example.sai.newapp.service.model.Project;
import com.example.sai.newapp.util.ServiceConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET(ServiceConstants.GET_REPOS)
    Call<List<Project>> getProjectList(@Path("user") String user);

}
