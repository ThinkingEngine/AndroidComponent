package s.cala.androidcompent.network;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * package name:s.cala.androidcompent.network
 * create:cala
 * date:2019/1/15
 * commits:管理cookie, 储存cookie的store
 */
public class NovateCookieManger implements CookieJar {
    private static final String TAG = "NovateCookieManger";
    private static Context mContext;
//    private static PersistentCookieStore cookieStore;

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return null;
    }
}
