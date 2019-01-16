package s.cala.androidcompent.utils;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * package name:s.cala.androidcompent.utils
 * create:cala
 * date:2019/1/15
 * commits:压缩工具
 */
public class ZipUtils {

    public static void unzipFile(File zipFile, String destination) throws IOException {

        FileInputStream fileInputStream = null;

        BufferedInputStream bufferedInputStream = null;

        ZipInputStream zipInputStream = null;

        try {
            fileInputStream = new FileInputStream(zipFile);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            zipInputStream = new ZipInputStream(bufferedInputStream);
            ZipEntry entry;

            File destinationFolder = new File(destination);
            if (destinationFolder.exists()) {
                deleteDirectory(destination);
            }

            destinationFolder.mkdir();

            byte[] buffer = new byte[1024];
            while ((entry = zipInputStream.getNextEntry()) != null) {
                String fileName = entry.getName();
                File file = new File(destinationFolder, fileName);
                if (entry.isDirectory()) {
                    file.mkdir();
                } else {
                    File parent = file.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdir();
                    }

                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    try {
                        int numBytesRead;
                        while ((numBytesRead = zipInputStream.read(buffer)) != -1) {
                            fileOutputStream.write(buffer, 0, numBytesRead);
                        }
                    } finally {
                        fileOutputStream.close();
                    }
                }

                long time = entry.getTime();
                if (time > 0) {
                    file.setLastModified(time);
                }
            }
        } finally {
            try {
                if (zipInputStream != null) {
                    zipInputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //删除指定目录
    public static boolean deleteDirectory(String dir) {
        //如果dir不以文件分隔符，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }

        File file = new File(dir);

        //如果dir对应的文件不存在，或者不是一个目录，退出
        if (!file.exists() || !file.isDirectory()) {
            Log.e("ZipUtils_tag", "删除目录失败:" + dir + "不是一个目录");
            return false;
        }

        boolean flag = true;

        //删除文件夹下的所有文件(包括子目录)
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }

        if (!flag) {
            Log.e("ZipUtils_tag", "删除目录失败");
            return false;
        }

        if (file.delete()) {
            Log.e("ZipUtils_tag", "删除目录:" + dir + "成功");
            return true;
        } else {
            Log.e("ZipUtils_tag", "删除目录:" + dir + "失败");
            return false;
        }
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            boolean succeedDelete = file.delete();
            if (succeedDelete) {
                Log.e("ZipUtils_tag", "删除单个文件" + fileName + "成功");
                return true;
            } else {
                Log.e("ZipUtils_tag", "删除单个文件" + fileName + "失败");
                return true;
            }
        } else {
            Log.e("ZipUtils_tag", "删除单个文件" + fileName + "失败!");
            return false;
        }
    }
}
