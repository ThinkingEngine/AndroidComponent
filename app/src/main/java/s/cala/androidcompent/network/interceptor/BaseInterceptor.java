package s.cala.androidcompent.network.interceptor;

import java.io.IOException;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * package name:s.cala.androidcompent.network
 * create:cala
 * date:2019/1/15
 * commits:BaseInterceptor，use set okhttp call header
 */
public class BaseInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        String accessToken = request.header("Authorization");

        if (Boolean.valueOf(accessToken)) {
            newBuilder.removeHeader("Authorization");
//            newBuilder.addHeader("Authorization", UserUtil.getTokenType() + " " + UserUtil.getAccessToken());
        }

        Request newRequest = newBuilder.build();
        return chain.proceed(newRequest);
    }
}

