package androidnewsreader.test.com.androidnewsreader.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidnewsreader.test.com.androidnewsreader.Model.Articles;
import androidnewsreader.test.com.androidnewsreader.R;
import androidnewsreader.test.com.androidnewsreader.Utils.Image.ImageLoaderFactory;

/**
 * Created by bharanicharan.ms on 2/19/2017.
 */

public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.ViewHolder> {

    private List<Articles> articlesList;
    private Context mContext;
    private IAdapterCallback adapterCallback;

    public ArticlesListAdapter(List<Articles> articles, IAdapterCallback callback) {
        this.articlesList = articles;
        this.adapterCallback = callback;
    }


    @Override
    public ArticlesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.articles_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        mContext = parent.getContext();

        return vh;
    }

    @Override
    public void onBindViewHolder(ArticlesListAdapter.ViewHolder holder, final int position) {

        final Articles article = articlesList.get(position);
        holder.articleTitle.setText(article.getArticleTitle());
        ImageLoaderFactory.getInstance(mContext).loadImage(holder.articleThumbnail, article.getArticleThumbnail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterCallback.onListItemClicked(article);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.articlesList.size();
    }

    public interface IAdapterCallback {
        void onListItemClicked(Articles article);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView articleTitle;
        public ImageView articleThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            articleTitle = (TextView) itemView.findViewById(R.id.article_title);
            articleThumbnail = (ImageView) itemView.findViewById(R.id.articles_thumbnail);
        }
    }
}
