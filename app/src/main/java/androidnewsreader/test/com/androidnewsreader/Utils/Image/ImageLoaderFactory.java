package androidnewsreader.test.com.androidnewsreader.Utils.Image;

import android.content.Context;

/**
 * Created by bharanicharan.ms on 2/19/2017.
 */

public class ImageLoaderFactory {

    private static IImageLoader INSTANCE;
    private static Context mContext;


    public synchronized static IImageLoader getInstance(Context context) {

        mContext = context;

        if (INSTANCE == null) {
            INSTANCE = new ImageLoaderImpl(mContext);
        }
        return INSTANCE;
    }
}
