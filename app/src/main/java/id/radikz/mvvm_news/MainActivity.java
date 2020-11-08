package id.radikz.mvvm_news;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.radikz.mvvm_news.adapter.ArticleAdapter;
import id.radikz.mvvm_news.model.Article;
import id.radikz.mvvm_news.viewmodel.ArticleViewModel;
import id.radikz.mvvm_news.viewmodel.MyViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private ArticleAdapter adapter;
    ArticleViewModel articleViewModel;

    ListView listView;
    EditText editUser;
    private RecyclerView recyclerView;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_item);
        editUser = findViewById(R.id.edit_user);

        recyclerView =  findViewById(R.id.recycler_user_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        final String query = "android";

        articleViewModel = ViewModelProviders.of(this, new MyViewModelFactory(this.getApplication(), query)).get(ArticleViewModel.class);

        editUser.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                searchUser(editUser.getText().toString());
                Log.d("text", editUser.getText().toString());
                handled = true;
            }
            return handled;
        });


        initialization(query);

    }

    private void searchUser(String query){
        articleViewModel.search(editUser.getText().toString().trim());
        adapter.notifyDataSetChanged();
        initialization(query);
    }

    private void initialization(String query) {
        loading = ProgressDialog.show(this, null, "harap tunggu...", true, true);

        // View Model
//        articleViewModel = ViewModelProviders.of(this, new MyViewModelFactory(this.getApplication(), query)).get(ArticleViewModel.class);
        getMovieArticles(query);
    }



    private void getMovieArticles(String query) {

        articleViewModel.getArticleResponseLiveData().observe(this, articleResponse -> {
            if (articleResponse != null) {
                loading.dismiss();
                List<Article> articles = articleResponse.getItems();
                // adapter
                adapter = new ArticleAdapter(articles);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}