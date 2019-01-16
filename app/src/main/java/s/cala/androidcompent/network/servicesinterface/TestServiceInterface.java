package s.cala.androidcompent.network.servicesinterface;

import io.reactivex.Observable;
import okhttp3.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import s.cala.androidcompent.network.constant.APIAdress;

/**
 * package name:s.cala.androidcompent.network.servicesinterface
 * create:cala
 * date:2019/1/16
 * commits:测试用
 */
public interface TestServiceInterface {


    //测试用例
    @GET(APIAdress.USER_AGREEMENT)
    @Headers("Authorization:true")
    public Observable<Response> testAPI();
}
