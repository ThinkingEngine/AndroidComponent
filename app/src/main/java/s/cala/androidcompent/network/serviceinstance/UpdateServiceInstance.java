package s.cala.androidcompent.network.serviceinstance;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;

import io.reactivex.Observable;
import io.reactivex.observers.DefaultObserver;
import okhttp3.ResponseBody;
import s.cala.androidcompent.constant.STATUS;
import s.cala.androidcompent.network.RetrofitClient;
import s.cala.androidcompent.network.constant.BaseAddress;
import s.cala.androidcompent.network.servicesinterface.DownLoadCallBack;
import s.cala.androidcompent.protocol.updates.ApkUpdateData;
import s.cala.androidcompent.utils.PreferenceUtil;

/**
 * package name:s.cala.androidcompent.network.serviceinstance
 * create:cala
 * date:2019/1/21
 * commits:
 */
public class UpdateServiceInstance {

    private static UpdateServiceInstance updateServiceInstance = null;

    private UpdateServiceInstance() {

    }

    public static UpdateServiceInstance getInstance() {
        if (updateServiceInstance == null) {
            synchronized (UpdateServiceInstance.class) {
                if (updateServiceInstance == null) {
                    updateServiceInstance = new UpdateServiceInstance();
                }
            }
        }

        return updateServiceInstance;
    }

    public Observable<ApkUpdateData> getUpdateInfo() {
        return RetrofitClient.getInstan().getUpdateService().getUpdateInfo();
    }

    public void downloadAPK(final long range, final String fileName, final DownLoadCallBack downLoadCallBack) {

        File file = new File(STATUS.APP_ROOT_PATH + STATUS.DOWNLOAD_DIR, fileName);

        String totalLength = "-";
        if (file.exists()) {
            totalLength += file.length();
        }

        RetrofitClient
                .getInstan()
                .getUpdateService()
                .downloadAPK("bytes=" + Long.toString(range) + totalLength)
                .subscribe(new DefaultObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        RandomAccessFile randomAccessFile = null;
                        InputStream inputStream = null;
                        long total = range;
                        long responseLength = 0;
                        try {
                            byte[] buf = new byte[2048];
                            int len = 0;
                            responseLength = responseBody.contentLength();
                            inputStream = responseBody.byteStream();
                            String filePath = STATUS.APP_ROOT_PATH + STATUS.DOWNLOAD_DIR;
                            File file1 = new File(filePath, fileName);
                            File dir = new File(filePath);

                            if (!dir.exists()) {
                                dir.mkdir();
                            }
                            randomAccessFile = new RandomAccessFile(file1, "rwd");
                            if (range == 0) {
                                randomAccessFile.setLength(responseLength);
                            }
                            randomAccessFile.seek(range);

                            int progress = 0;
                            int lastProgress = 0;

                            while ((len = inputStream.read(buf)) != -1) {
                                randomAccessFile.write(buf, 0, len);
                                total += len;
                                lastProgress = progress;
                                progress = (int) (total * 100 / randomAccessFile.length());
                                if (progress > 0 && progress != lastProgress) {
                                    downLoadCallBack.onProgress(progress);
                                }
                            }
                            downLoadCallBack.onComplete();
                        } catch (Exception e) {
                            Log.e("RetrofitClient", e.getMessage());
                            downLoadCallBack.onError(e.getMessage());
                            e.printStackTrace();
                        } finally {
                            try {
                                PreferenceUtil.setPrefLong(BaseAddress.UPDATE_URL, total);
                                if (randomAccessFile != null) {
                                    randomAccessFile.close();
                                }
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        downLoadCallBack.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
