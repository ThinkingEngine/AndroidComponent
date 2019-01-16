package s.cala.androidcompent.network;

import static s.cala.androidcompent.network.BaseAddress.ACCOUNT_BASE_URL;
import static s.cala.androidcompent.network.BaseAddress.SERVICE_BASE_URL;

/**
 * package name:s.cala.androidcompent.network
 * create:cala
 * date:2019/1/15
 * commits:基础API地址
 */
public class APIAdress {
    /* 用户协议 */
    public static final String USER_AGREEMENT = SERVICE_BASE_URL + "";

    /* 获取用户信息 */
    public static final String USER_INFO_URL = ACCOUNT_BASE_URL + "";

    /* 发送和账号相关的短信验证码 */
    public static final String SEND_ACCOUNT_CAPTCHA = ACCOUNT_BASE_URL + "";
}
