package s.cala.androidcompent.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import s.cala.androidcompent.constant.STATUS;

/**
 * package name:s.cala.androidcompent.utils
 * create:cala
 * date:2019/1/14
 * commits:网络工具（网络状态监测,,,）
 */
public class NetworkUtils {

    public static int getNetworkState(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) {
                return STATUS.NETWORK_WIFI;
            } else if (networkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) {
                return STATUS.NETWORK_MOBILE;
            }
        } else {
            return STATUS.NETWORK_NONE;
        }

        return STATUS.NETWORK_NONE;
    }

    /**
     * 网络是否连接
     */
    public static boolean isNetConnected(Context context) {
        return getNetworkState(context) != STATUS.NETWORK_NONE;
    }
}
