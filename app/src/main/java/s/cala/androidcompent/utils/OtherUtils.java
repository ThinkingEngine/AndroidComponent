package s.cala.androidcompent.utils;

import static s.cala.androidcompent.base.BaseApplication.appContext;

/**
 * package name:s.cala.androidcompent.utils
 * create:cala
 * date:2019/1/8
 * commits:一般工具
 */
public class OtherUtils {

    //
    public static int px2dip(int px) {
        final float scale = appContext.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int dip2px(int dp) {
        final float density = appContext.getResources().getDisplayMetrics().density;

        return (int) (dp * density + 0.5);
    }
}
