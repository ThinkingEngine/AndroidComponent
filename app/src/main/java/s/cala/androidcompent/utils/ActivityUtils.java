package s.cala.androidcompent.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * package name:s.cala.androidcompent.utils
 * create:cala
 * date:2019/1/8
 * commits:Activity utils
 */
public class ActivityUtils {

    /**
     * 跳转到指定Activity。
     * */
    public static void startActivity(Context context, Activity activity){
        context.startActivity(new Intent(context,activity.getClass()));
    }

    /**
     * 跳转到指定页面(携带参数)
     *
     *  val bundle = Bundle()
     *  bundle.putString("id", id)
     *  startActivity(bundle, TestActivity())
     *
     *  接收参数：
     *  val id: String = intent.getStringExtra("id")
     */
    public static void startActivity(Context context, Activity activity, Bundle bundle){
        Intent intent = new Intent(context,activity.getClass());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
