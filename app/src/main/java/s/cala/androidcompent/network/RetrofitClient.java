package s.cala.androidcompent.network;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import butterknife.internal.Utils;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import s.cala.androidcompent.network.constant.BaseAddress;
import s.cala.androidcompent.network.constant.BaseConstants;
import s.cala.androidcompent.network.interceptor.BaseInterceptor;
import s.cala.androidcompent.network.security.Certificate;
import s.cala.androidcompent.network.serviceinstance.TestServiceInstance;
import s.cala.androidcompent.network.servicesinterface.TestServiceInterface;
import s.cala.androidcompent.network.servicesinterface.UpdateServiceInterface;

/**
 * package name:s.cala.androidcompent.network
 * create:cala
 * date:2019/1/15
 * commits:创建具体Retrofit，和调度分发请求
 */
public class RetrofitClient {

    private Retrofit httpRetrofit = null;
    private Retrofit httpsRetrofit = null;

    public static String baseUrl = BaseAddress.SERVICE_BASE_URL;


    private static RetrofitClient sNewInstance;

    private RetrofitClient() {
        httpRetrofit = new Retrofit
                .Builder()
                .client(getHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        httpsRetrofit = new Retrofit
                .Builder()
                .client(getHttpsClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    public static RetrofitClient getInstan() {
        if (sNewInstance == null) {
            synchronized (RetrofitClient.class) {
                if (sNewInstance == null) {
                    sNewInstance = new RetrofitClient();
                }
            }
        }
        return sNewInstance;
    }


    HttpLoggingInterceptor loging = new HttpLoggingInterceptor();

    private OkHttpClient getHttpClient() {

        OkHttpClient httpClient = new OkHttpClient
                .Builder()
                .readTimeout(BaseConstants.TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(BaseConstants.CONNECT_TIME, TimeUnit.SECONDS)
                .addInterceptor(loging)
                .addInterceptor(new BaseInterceptor())
                .build();
        return httpClient;
    }

    private OkHttpClient getHttpsClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        try {
//            Certificate.setCertificates(builder,Utils.getApp().getAsserts().open());
            Certificate.setCertificates(builder, null);
//            builder.hostnameVerifier()
        } catch (Exception e) {
            e.printStackTrace();
        }

        builder.readTimeout(BaseConstants.TIME_OUT, TimeUnit.SECONDS);
        builder.connectTimeout(BaseConstants.CONNECT_TIME, TimeUnit.SECONDS);
        builder.addInterceptor(loging);
        builder.addInterceptor(new BaseInterceptor());
        return builder.build();
    }

    private <T> T createHttp(Class<T> classs) {
        return httpRetrofit.create(classs);
    }

    private <T> T createHttps(Class<T> tClass) {
        return httpsRetrofit.create(tClass);
    }


    //自定义服务部分
    //测试
    public TestServiceInterface getTest() {
        return createHttp(TestServiceInterface.class);
    }

    //更新服务
    public UpdateServiceInterface getUpdateService(){
        return createHttp(UpdateServiceInterface.class);
    }
}
