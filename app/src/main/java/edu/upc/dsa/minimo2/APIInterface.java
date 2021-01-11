package edu.upc.dsa.minimo2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface
{
    @GET("{user}")
    Call<User> getUserInfo(@Path("user") String user);

    @GET ("{user}/repos")
    Call<List<User>> getRepos(@Path("user") String user);

}
