package s.cala.androidcompent.network.servicesinterface;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import s.cala.androidcompent.network.constant.BaseAddress;
import s.cala.androidcompent.protocol.updates.ApkUpdateData;

/**
 * package name:s.cala.androidcompent.network.servicesinterface
 * create:cala
 * date:2019/1/21
 * commits:获取apk升级信息接口
 */
public interface UpdateServiceInterface {

    @GET(BaseAddress.UPDATE_URL)
    public Observable<ApkUpdateData> getUpdateInfo();

    @Streaming
    @GET(BaseAddress.UPDATE_URL)
    Observable<ResponseBody> downloadAPK(@Header("Range") String range);
}
