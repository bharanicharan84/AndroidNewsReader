package androidnewsreader.test.com.androidnewsreader.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidnewsreader.test.com.androidnewsreader.Model.Articles;
import androidnewsreader.test.com.androidnewsreader.R;
import androidnewsreader.test.com.androidnewsreader.Utils.Image.ImageLoaderFactory;

/**
 * Created by bharanicharan.ms on 2/19/2017.
 */

public class NewsArticleDetailsFragment extends Fragment {

    private ImageView imgThumbnail;
    private TextView newsTitle, newsId, newsDesc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.news_articles_details, container, false);
        newsTitle = (TextView) view.findViewById(R.id.news_article_title);
        newsId = (TextView) view.findViewById(R.id.news_article_id);
        newsDesc = (TextView) view.findViewById(R.id.news_description);
        imgThumbnail = (ImageView) view.findViewById(R.id.img_article_thumbnail);
        Articles articleDetails = getArguments().getParcelable("ArticleDetails");

        newsId.setText(articleDetails.getArticleID());
        newsDesc.setText(articleDetails.getArticleDesc());
        newsTitle.setText(articleDetails.getArticleTitle());
        ImageLoaderFactory.getInstance(getActivity()).loadImage(imgThumbnail, articleDetails.getArticleThumbnail());

        return view;
    }

}
