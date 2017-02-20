package androidnewsreader.test.com.androidnewsreader.Parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidnewsreader.test.com.androidnewsreader.Model.Articles;

/**
 * Created by bharanicharan.ms on 2/19/2017.
 */

public class ArticleParser {

    String sJsonResponse;
    List<Articles> articlesList;

    public ArticleParser(String sResponse) {
        this.sJsonResponse = sResponse;
    }


    public List<Articles> build() {
        articlesList = new ArrayList<>();
        Articles articleObject;

        try {
            JSONObject jsonResponeObject = new JSONObject(sJsonResponse);
            JSONArray jsonArticles = jsonResponeObject.getJSONArray("Items");
            for (int i = 0; i < jsonArticles.length(); i++) {
                JSONObject itemObject = jsonArticles.getJSONObject(i);
                String mId = itemObject.getString("Id");
                String mTitle = itemObject.getString("Title");
                String mDesc = itemObject.getString("Description");
                String mThumbnail = itemObject.getString("ThumbnailUrl");
                articleObject = new Articles(mId, mTitle, mDesc, mThumbnail);
                articleObject.setArticleID(mId);
                articleObject.setArticleTitle(mTitle);
                articleObject.setArticleDesc(mDesc);
                articleObject.setArticleThumbnail(mThumbnail);
                articlesList.add(articleObject);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return articlesList;
    }
}
