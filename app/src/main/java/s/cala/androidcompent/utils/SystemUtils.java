package s.cala.androidcompent.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import s.cala.androidcompent.constant.STATUS;

/**
 * package name:s.cala.androidcompent.utils
 * create:cala
 * date:2019/1/16
 * commits:系统检测工具
 */
public class SystemUtils {


    //获取用户的cpu型号，方便调试时
    public static String getCupName() {
        String name = getCpuName1();
        if (TextUtils.isEmpty(name)) {
            name = getCpuName2();
            if (TextUtils.isEmpty(name)) {
                name = "unknown";
            }
        }

        return name;
    }

    /**
     * 获取版本号
     *
     * @throws PackageManager.NameNotFoundException
     */
    public static String getVersionName(Context context) throws PackageManager.NameNotFoundException {
        //获取PackageManager实例
        PackageManager pm = context.getPackageManager();
        //getPackagename()获取当前类的包名，0代表获取版本信息
        PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);

        String version = info.versionName;

        return version;

    }

    /**
     * 版本号对比
     *
     * @param currentVersion 当前app的版本号
     * @param getVersion     获取到的版本号
     */
    public static STATUS.VersionComp compareVersion(String currentVersion, String getVersion) {

        if (currentVersion.endsWith(getVersion)) {
            return STATUS.VersionComp.NO_UPDATE;
        }

        String[] versionCurrent = currentVersion.split("\\.");
        String[] versionGet = getVersion.split("\\.");
        int index = 0;

        //获取最小长度值
        int minLen = Math.min(versionCurrent.length, versionGet.length);
        int diff = 0;

        //循环判断每位的大小
        while (index < minLen && (diff = Integer.parseInt(versionCurrent[index]) - Integer.parseInt(versionGet[index])) == 0) {
            index++;
        }

        if (diff == 0) {
            //如果位数不一致，比较多余位数
            for (int i = index; i < versionCurrent.length; i++) {
                if (Integer.parseInt(versionCurrent[i]) > 0) {
                    return STATUS.VersionComp.NEED_UPDATE;
                }
            }

            for (int i = 0; i < versionGet.length; i++) {
                if (Integer.parseInt(versionGet[i]) > 0) {
                    return STATUS.VersionComp.ERROR;
                }
            }

            return STATUS.VersionComp.NO_UPDATE;
        } else {
            return diff > 0 ? STATUS.VersionComp.NEED_UPDATE : STATUS.VersionComp.ERROR;
        }
    }



    private static String getCpuName1() {
        String[] abiArr;
        if (Build.VERSION.PREVIEW_SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            abiArr = Build.SUPPORTED_ABIS;
        } else {
            abiArr = new String[]{Build.CPU_ABI, Build.CPU_ABI2};
        }

        StringBuilder sb = new StringBuilder();
        for (String abi : abiArr) {
            sb.append(abi);
            sb.append(",");
        }

        return sb.toString();
    }

    private static String getCpuName2() {
        try {
            FileReader e = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(e);
            String text = br.readLine();
            String[] array = text.split(":\\s+", 2);
            e.close();
            br.close();

            return array[1];
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
