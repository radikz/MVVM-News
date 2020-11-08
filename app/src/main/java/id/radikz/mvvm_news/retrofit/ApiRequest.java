package id.radikz.mvvm_news.retrofit;

import id.radikz.mvvm_news.model.ArticleResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {
    @GET("v2/everything")
    Call<ArticleResponse> getArticles(
            @Query("q") String query,
            @Query("apiKey") String apiKey
    );

    @GET("search/users")
    Call<ArticleResponse> getUserList(@Query("q") String filter);
}
