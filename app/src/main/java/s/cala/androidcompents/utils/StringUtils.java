package s.cala.androidcompents.utils;

import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

/**
 * package name:s.cala.androidcompents.utils
 * create:cala
 * date:2019/1/8
 * commits:String utils(字符串工具)
 */
public class StringUtils {

    /**
     * 获取输入字符
     */
    public static String getText(@NotNull TextView textView) {
        return textView.getText().toString().trim();
    }

    /**
     * 每隔4位对字符串加空格
     */
    public static String addBlank(@NotNull String replace) {
        if (replace.isEmpty() || replace.length() < 4) return replace;
        String regex = "(.{4})";
        replace = replace.replaceAll(regex, "$1 ");
        return replace;
    }

    /**
     * 电话号码中间加省略号
     */
    public static String formatMobile(@NotNull String mobile) {
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }
}
