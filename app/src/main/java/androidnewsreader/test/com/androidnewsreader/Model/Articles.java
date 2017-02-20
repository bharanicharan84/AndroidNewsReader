package androidnewsreader.test.com.androidnewsreader.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bharanicharan.ms on 2/19/2017.
 */

public class Articles implements Parcelable {

    public static final Creator<Articles> CREATOR = new Creator<Articles>() {
        @Override
        public Articles createFromParcel(Parcel in) {
            return new Articles(in);
        }

        @Override
        public Articles[] newArray(int size) {
            return new Articles[size];
        }
    };

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleDesc() {
        return articleDesc;
    }

    public void setArticleDesc(String articleDesc) {
        this.articleDesc = articleDesc;
    }

    public String getArticleThumbnail() {
        return articleThumbnail;
    }

    public void setArticleThumbnail(String articleThumbnail) {
        this.articleThumbnail = articleThumbnail;
    }

    public String articleID;
    public String articleTitle;
    public String articleDesc;
    public String articleThumbnail;

    public Articles(String articleID, String articleTitle, String articleDesc, String articleThumbnail) {
        this.articleID = articleID;
        this.articleTitle = articleTitle;
        this.articleDesc = articleDesc;
        this.articleThumbnail = articleThumbnail;
    }

    protected Articles(Parcel in) {
        String[] data = new String[4];
        in.readStringArray(data);

        this.articleID = data[0];
        this.articleTitle = data[1];
        this.articleDesc = data[2];
        this.articleThumbnail = data[3];
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.articleID, this.articleTitle, this.articleDesc, this.articleThumbnail});
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
