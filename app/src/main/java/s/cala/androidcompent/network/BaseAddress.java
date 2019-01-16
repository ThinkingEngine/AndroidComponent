package s.cala.androidcompent.network;

import s.cala.androidcompent.BuildConfig;

/**
 * package name:s.cala.androidcompent.network
 * create:cala
 * date:2019/1/15
 * commits:基础地址
 */
public class BaseAddress {

    /**
     * 账号相关基地址
     */
    private static final String DEV_BASE_URL = "";
    private static final String LIVE_BASE_URL = "";

    /**
     * 业务基地址
     */
    private static final String DEV_SERVICE_URL = "";
    private static final String LIVE_SERVICE_URL = "";

    /**
     * 账号api接口根地址
     */
    public static final String ACCOUNT_BASE_URL = BuildConfig.isDev ? DEV_BASE_URL : LIVE_BASE_URL;

    /**
     * 业务api接口根地址
     */
    public static final String SERVICE_BASE_URL = BuildConfig.isDev ? DEV_SERVICE_URL : LIVE_SERVICE_URL;
}
