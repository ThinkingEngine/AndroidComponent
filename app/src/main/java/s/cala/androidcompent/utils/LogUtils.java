package s.cala.androidcompent.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;


import s.cala.androidcompent.BuildConfig;

/**
 * package name:s.cala.androidcompent.utils
 * create:cala
 * date:2019/2/8
 * commits:log 工具类
 */
public class LogUtils {

    private static String mClassName;
    private static String mTAG;

    /**
     * 设置 mTAG ，如果没有清空，所有log都会使用该TAG
     *
     * @param tag
     */
    public static void setTag(String tag) {
        mTAG = tag;
    }

    /**
     * 清空tag
     */
    public static void clearTag() {
        mTAG = null;
    }

    public static void e(Throwable throwable) {
        if (isDebugable()) {
            throwable = new Throwable(throwable);
            String logMsg = LogUtils.createLog(throwable, throwable.getMessage());
            if (!TextUtils.isEmpty(mTAG)) {
                mClassName = mTAG;
            }
            Log.e(mClassName, logMsg);
        }
    }

    public static void i(String msg) {
        if (isDebugable()) {

            Throwable throwable = new Throwable();
            String logMsg = LogUtils.createLog(throwable, msg);
            if (!TextUtils.isEmpty(mTAG)) {
                mClassName = mTAG;
            }
            Log.i(mClassName, logMsg);
        }
    }

    public static void v(String msg) {
        if (isDebugable()) {
            Throwable throwable = new Throwable();
            String logMsg = LogUtils.createLog(throwable, msg);
            if (!TextUtils.isEmpty(mTAG)) {
                mClassName = mTAG;
            }
            Log.v(mClassName, logMsg);
        }
    }

    public static void w(String msg) {
        if (isDebugable()) {
            Throwable throwable = new Throwable();
            String logMsg = LogUtils.createLog(throwable, msg);
            if (!TextUtils.isEmpty(mTAG)) {
                mClassName = mTAG;
            }
            Log.w(mClassName, logMsg);
        }
    }

    private static boolean isDebugable() {
        return BuildConfig.DEBUG;
    }

    private static String createLog(Throwable throwable, String msg) {
        if (throwable != null) {
            throwable.printStackTrace();
            StackTraceElement sElements = throwable.getStackTrace()[1];//方法栈
            String mFileName = sElements.getFileName();
            String mMethodName = sElements.getMethodName();
            int mLineNumber = sElements.getLineNumber();
            int index = mFileName.indexOf(".");
            mClassName = mFileName.substring(0, index);
            return createLog(mFileName, mMethodName, mLineNumber, msg);

        }
        return "The string is null";
    }

    @NonNull
    public static String createLog(String className, String methodName, int lineNumber, String log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(methodName);
        buffer.append("(").append(className).append(":").append(lineNumber).append(")");
        buffer.append("---> ").append(log);
        return buffer.toString();
    }
}
