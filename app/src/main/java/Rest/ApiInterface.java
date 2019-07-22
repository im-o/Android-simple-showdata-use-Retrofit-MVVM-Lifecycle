package Rest;

import com.stimednp.simpleretrofitapi.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rivaldy on 7/21/2019.
 */

public interface ApiInterface {
    @GET("discover/movie")
    Call<MoviesResponse> getMovieList(@Query("api_key") String apiKey);
}
