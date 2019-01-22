package s.cala.androidcompent.constant;

import android.os.Environment;

import s.cala.androidcompent.base.BaseApplication;

/**
 * package name:s.cala.androidcompent.constant
 * create:cala
 * date:2019/1/14
 * commits:一般状态量
 */
public class STATUS {

    public static final int NETWORK_NONE = -1;
    public static final int NETWORK_MOBILE = 0;
    public static final int NETWORK_WIFI = 1;


    /**
     * 当前版本与app更新处的版本对比结果
     * <p>
     * DIFF_EQUAL 两个版本号相等
     * DIFF_MIN
     */
    public enum VersionComp {
        NO_UPDATE, NEED_UPDATE, ERROR
    }


    /**
     * 更新apk时，下载的apk的存储位置。
     */
    public final static String APP_ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public final static String DOWNLOAD_DIR = "/download";
}
