package s.cala.androidcompent.network;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * package name:s.cala.androidcompent.network
 * create:cala
 * date:2019/1/15
 * commits:创建具体Retrofit，和调度分发请求
 */
public class RetrofitClient {

    private static final int DEFAULT_TIMEOUT = 5;

    private OkHttpClient okHttpClient;

    public static String baseUrl = BaseAddress.SERVICE_BASE_URL;

    private static Context mContext;

    private static RetrofitClient sNewInstance;

    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient(mContext);
    }

    public static RetrofitClient getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return SingletonHolder.INSTANCE;
    }

    public static RetrofitClient getInstance(Context context, String url) {
        if (context != null) {
            mContext = context;
        }
        sNewInstance = new RetrofitClient(context, url);
        return sNewInstance;
    }

    private RetrofitClient(Context context) {
        this(context, null);
    }

    private RetrofitClient(Context context, String url){
        if(TextUtils.isEmpty(url)){
            url = baseUrl;
        }

        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .addInterceptor(new BaseInterceptor())
                .connectTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }
}
