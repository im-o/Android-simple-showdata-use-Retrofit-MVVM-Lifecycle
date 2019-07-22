package com.stimednp.simpleretrofitapi;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Model.Movie;
import Rest.ApiClient;
import Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rivaldy on 7/22/2019.
 */

public class MainViewModel extends ViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();
    private static final String API_KEY = "8b904530a7aced766995fa063ed27355";
    private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();

    void setListMovies(final Context context){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesResponse> call = apiInterface.getMovieList(API_KEY);
        final ArrayList<Movie> movieItems = new ArrayList<>();
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                List<Movie> movies = response.body().getResults();
                Log.d(TAG, "Number of movies rcv : "+movies.size());
                Toast.makeText(context, "Number of movies rcv : "+movies.size(), Toast.LENGTH_SHORT).show();

                try {
                    for (int i=0; i<movies.size(); i++){
                        Log.d(TAG, "ini response : "+ movies.get(i).getTitle());
                        movieItems.add(movies.get(i));
                    }
                    listMovies.postValue(movieItems);
                }catch (Exception e){
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d(TAG, "ERROR : "+t.getMessage());
            }
        });
    }
    LiveData<ArrayList<Movie>> getListMovies(){
        return listMovies;
    }
}
