package s.cala.androidcompent.utils;

import android.os.Build;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * package name:s.cala.androidcompent.utils
 * create:cala
 * date:2019/1/16
 * commits:系统检测工具
 */
public class SystemUtils {


    //获取用户的cpu型号，方便调试时
    public static String getCupName(){
        String name = getCpuName1();
        if(TextUtils.isEmpty(name)){
            name = getCpuName2();
            if(TextUtils.isEmpty(name)){
                name = "unknown";
            }
        }

        return name;
    }

    private static String getCpuName1(){
        String[] abiArr;
        if(Build.VERSION.PREVIEW_SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            abiArr = Build.SUPPORTED_ABIS;
        }else{
            abiArr = new String[]{Build.CPU_ABI,Build.CPU_ABI2};
        }

        StringBuilder sb = new StringBuilder();
        for(String abi:abiArr){
            sb.append(abi);
            sb.append(",");
        }

        return sb.toString();
    }

    private static String getCpuName2(){
        try {
            FileReader e = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(e);
            String text = br.readLine();
            String[] array = text.split(":\\s+",2);
            e.close();
            br.close();

            return array[1];
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
