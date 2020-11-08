package id.radikz.mvvm_news.adapter;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.radikz.mvvm_news.R;
import id.radikz.mvvm_news.model.Article;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<Article> articleArrayList;

    public ArticleAdapter(List<Article> articleArrayList) {
        this.articleArrayList = articleArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_article,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(articleArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    private void expand(boolean activate, TextView textView){
        if (activate) {
            textView.setMaxLines(4);
            textView.setEllipsize(TextUtils.TruncateAt.END);
        }
        else{
            textView.setSingleLine(false);
            textView.setEllipsize(null);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView articleTitle;
        private TextView articlePublishedAt;
        private TextView articleAuthor;
        private TextView articleDescription;
        private boolean expandable = false;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            articleTitle = itemView.findViewById(R.id.article_title);
            articleAuthor = itemView.findViewById(R.id.article_author);
            articlePublishedAt = itemView.findViewById(R.id.article_publishedat);
            articleDescription = itemView.findViewById(R.id.article_description);

            itemView.setOnClickListener(v -> {
                expand(expandable, articleDescription);
                expandable = !expandable;
            });

        }

        public void bind(Article article) {
            articleTitle.setText(article.getTitle());
            articlePublishedAt.setText(article.getPublishedAt());
            articleAuthor.setText("by: " + article.getAuthor());
            articleDescription.setText(article.getDescription());

        }
    }
}
