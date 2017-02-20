package androidnewsreader.test.com.androidnewsreader.Utils.Image;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by bharanicharan.ms on 2/19/2017.
 */

public class ImageLoaderImpl implements IImageLoader {

    private Context mContext;


    ImageLoaderImpl(Context context) {
        this.mContext = context;
    }


    @Override
    public void loadImage(ImageView root, String imageUrl) {

        Picasso.with(mContext).load(imageUrl).noFade().into(root);
    }
}
