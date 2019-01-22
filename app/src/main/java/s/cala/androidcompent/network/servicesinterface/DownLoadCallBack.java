package s.cala.androidcompent.network.servicesinterface;

/**
 * package name:s.cala.androidcompent.network.servicesinterface
 * create:cala
 * date:2019/1/21
 * commits:下载回调接口
 */
public interface DownLoadCallBack {

    void onProgress(int progress);

    void onComplete();

    void onError(String msg);
}
