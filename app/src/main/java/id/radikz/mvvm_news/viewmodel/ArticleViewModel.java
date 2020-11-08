package id.radikz.mvvm_news.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import id.radikz.mvvm_news.model.ArticleResponse;
import id.radikz.mvvm_news.repository.ArticleRepository;

public class ArticleViewModel extends AndroidViewModel {
    private ArticleRepository articleRepository;
    private MutableLiveData<ArticleResponse> articleResponseLiveData;

    public ArticleViewModel(@NonNull Application application, String query) {
        super(application);

        articleRepository = new ArticleRepository();
        articleResponseLiveData = articleRepository.getArticles(query);
    }

    public LiveData<ArticleResponse> getArticleResponseLiveData() {

        return articleResponseLiveData;
    }

    public void search(String query){
        articleResponseLiveData = articleRepository.getArticles(query);
    }
}
