package s.cala.androidcompent.utils;

import android.content.Context;

/**
 * package name:s.cala.androidcompent.utils
 * create:cala
 * date:2019/1/10
 * commits:Toast utils
 */
public class ToastUtils {

    public static void showShortToast(Context context, String content) {
        if (null != context && null != content && content.trim().length() != 0)
            ToastInstance.getInstance().showShortToast(context, content);
    }

    public static void showShortToast(Context context, int resId) {
        if (null != context && resId != -1)
            ToastInstance.getInstance().showShortToast(context, resId);
    }

    public static void showShortToast(Context context, int resId,
                                      Object... args) {
        if (null != context) {
            String content = context.getString(resId, args);
            ToastInstance.getInstance().showShortToast(context, content);
        }
    }

    public static void showLongToast(Context context, String content) {
        if (null != context && null != content && content.trim().length() != 0)
            ToastInstance.getInstance().showLongToast(context, content);
    }

    public static void showLongToast(Context context, int resId) {
        if (null != context && resId != -1)
            ToastInstance.getInstance().showLongToast(context, resId);
    }

    public static void showLongToast(Context context, int resId, Object... args) {
        if (null != context) {
            String content = context.getString(resId, args);
            ToastInstance.getInstance().showLongToast(context, content);
        }
    }
}
