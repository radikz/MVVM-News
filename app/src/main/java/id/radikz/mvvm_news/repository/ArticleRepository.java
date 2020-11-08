package id.radikz.mvvm_news.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import id.radikz.mvvm_news.model.ArticleResponse;
import id.radikz.mvvm_news.retrofit.ApiRequest;
import id.radikz.mvvm_news.retrofit.RetrofitRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {
    private static final String TAG = ArticleRepository.class.getSimpleName();
    private static final String APIKEY = "055958b5360849dcab44a9792d6146f9";
    private ApiRequest apiRequest;

    public ArticleRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);

    }

    public MutableLiveData<ArticleResponse> getArticles(String query) {
        Log.d(TAG, "onResponse response:: " + "run query");
        final MutableLiveData<ArticleResponse> data = new MutableLiveData<>();
        apiRequest.getArticles(query, APIKEY)
                .enqueue(new Callback<ArticleResponse>() {
                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);
                        if (response.body() != null) {
                            data.setValue(response.body());
                            Log.d(TAG, "articles total result " + response.body().getItems());
//                            Log.d(TAG, "articles size:: " + response.body().getItems().g);
//                            Log.d(TAG, "articles title pos 0:: " + response.body().getArticles().get(0).getTitle());
                        }
                    }
                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                    }
                });
        return data;
    }
}
