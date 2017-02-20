package androidnewsreader.test.com.androidnewsreader.UI;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import androidnewsreader.test.com.androidnewsreader.Adapter.ArticlesListAdapter;
import androidnewsreader.test.com.androidnewsreader.Model.Articles;
import androidnewsreader.test.com.androidnewsreader.Network.HttpFactory;
import androidnewsreader.test.com.androidnewsreader.Parser.ArticleParser;
import androidnewsreader.test.com.androidnewsreader.R;

/**
 * Created by bharanicharan.ms on 2/19/2017.
 */

public class NewsArticlesFragment extends Fragment implements View.OnClickListener, ArticlesListAdapter.IAdapterCallback {

    private Button btnTopStories, btnBusiness, btnEntertainment;
    private List<Articles> articlesList;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ArticlesListAdapter myAdapter;
    private IItemClickCallBack mCallBack;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IItemClickCallBack) {
            mCallBack = (IItemClickCallBack) context;
        } else {
            throw new RuntimeException("Hosting context must implement IItemClickCallBack");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_articles, container, false);

        btnTopStories = (Button) view.findViewById(R.id.btn_top_stories);
        btnBusiness = (Button) view.findViewById(R.id.btn_business);
        btnEntertainment = (Button) view.findViewById(R.id.btn_entertainment);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.articles_list);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        btnBusiness.setOnClickListener(this);
        btnEntertainment.setOnClickListener(this);
        btnTopStories.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_top_stories:
                new FetchNewsTask().execute(getString(R.string.articles_top_stories));
                break;

            case R.id.btn_business:
                new FetchNewsTask().execute(getString(R.string.articles_business));
                break;

            case R.id.btn_entertainment:
                new FetchNewsTask().execute(getString(R.string.articles_entertainment));
                break;
        }
    }

    @Override
    public void onListItemClicked(Articles article) {
        mCallBack.onItemClicked(article);
    }

    public interface IItemClickCallBack {
        void onItemClicked(Articles article);
    }

    class FetchNewsTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            return HttpFactory.getInstance().sendGet(url[0]);
        }

        @Override
        protected void onPostExecute(String sResponse) {

            ArticleParser articleParser = new ArticleParser(sResponse);
            articlesList = articleParser.build();

            myAdapter = new ArticlesListAdapter(articlesList, NewsArticlesFragment.this);
            mRecyclerView.setAdapter(myAdapter);
        }
    }
}
