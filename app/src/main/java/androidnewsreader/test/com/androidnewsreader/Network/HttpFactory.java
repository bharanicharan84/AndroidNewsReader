package androidnewsreader.test.com.androidnewsreader.Network;

/**
 * Created by bharanicharan.ms on 2/18/2017.
 */

public class HttpFactory {

    public static IHttpRequestHelper getInstance() {
        return new HttpRequestImpl();
    }
}
