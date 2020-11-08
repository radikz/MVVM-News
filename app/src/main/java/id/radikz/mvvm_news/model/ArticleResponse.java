package id.radikz.mvvm_news.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleResponse {
    @SerializedName("articles")
    @Expose
    private List<Article> items = null;

    public List<Article> getItems() {
        return items;
    }

    public void setItems(List<Article> items) {
        this.items = items;
    }
}
