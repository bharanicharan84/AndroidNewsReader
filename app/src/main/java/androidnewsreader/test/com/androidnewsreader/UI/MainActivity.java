package androidnewsreader.test.com.androidnewsreader.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import androidnewsreader.test.com.androidnewsreader.Model.Articles;
import androidnewsreader.test.com.androidnewsreader.R;

public class MainActivity extends AppCompatActivity implements NewsArticlesFragment.IItemClickCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            NewsArticlesFragment newsFragment = new NewsArticlesFragment();
            addFragment(R.id.articles_container, newsFragment);
        }
    }

    void navigateFragments(int container, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(container, fragment);
        transaction.commit();
    }

    void addFragment(int container, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment);
        transaction.commit();
    }

    @Override
    public void onItemClicked(Articles article) {
        NewsArticleDetailsFragment newsDetailsFragment = new NewsArticleDetailsFragment();
        Bundle b = new Bundle();
        b.putParcelable("ArticleDetails", article);
        newsDetailsFragment.setArguments(b);
        navigateFragments(R.id.articles_container, newsDetailsFragment);
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}
