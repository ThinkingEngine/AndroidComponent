package s.cala.androidcompent.utils;

import android.text.TextUtils;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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

    /**
     * 把字符串转换为数字
     *
     * @param value
     * @return
     */
    public static int textToInteger(String value) {
        int data = -1;
        if (!TextUtils.isEmpty(value.trim()) && TextUtils.isDigitsOnly(value)) {
            data = Integer.valueOf(value);
        }
        return data;
    }

    /**
     * 将数字转化为字符串
     *
     * @param value
     * @param defVal
     * @return
     */
    public static String numberToText(String value, String defVal) {

        if (!TextUtils.isEmpty(value.trim()) && TextUtils.isDigitsOnly(value)) {
            return value;
        }
        return defVal;
    }

    /**
     * formatType格式为yyyy-MM-dd HH:mm:ss
     * yyyy年MM月dd日 HH时mm分ss秒
     * data Date类型的时间
     */
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    /**
     * strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
     * HH时mm分ss秒，
     * strTime的时间格式必须要与formatType的时间格式相同
     */
    public static Date string2Date(String strTime, String formatType) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatType);
            TimeZone timeZone = TimeZone.getTimeZone("GMT+08:00");
            dateFormat.setTimeZone(timeZone);
            return dateFormat.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 判断字符串是否符合手机号码格式
     * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
     * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
     * 电信号段: 133,149,153,170,173,177,180,181,189
     *
     * @return 待检测的字符串
     */
    public static boolean isMobileNO(String mobileNum) {
        // "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        if (TextUtils.isEmpty(mobileNum))
            return false;
        else
            return mobileNum.matches(telRegex);
    }
}
