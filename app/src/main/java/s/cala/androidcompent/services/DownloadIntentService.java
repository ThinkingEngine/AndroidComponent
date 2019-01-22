package s.cala.androidcompent.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import java.io.File;

import s.cala.androidcompent.R;
import s.cala.androidcompent.constant.STATUS;
import s.cala.androidcompent.network.constant.BaseAddress;
import s.cala.androidcompent.network.serviceinstance.UpdateServiceInstance;
import s.cala.androidcompent.network.servicesinterface.DownLoadCallBack;
import s.cala.androidcompent.utils.PreferenceUtil;

/**
 * package name:s.cala.androidcompent.services
 * create:cala
 * date:2019/1/21
 * commits:下载apk服务
 */
public class DownloadIntentService extends IntentService {

    private static final String TAG = "DownloadIntentService";
    private NotificationManager notificationManager;
    private String downloadFilename;
    private Notification notification;


    public DownloadIntentService() {
        super("InitializeService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        downloadFilename = intent.getExtras().getString("download_name");
        final int downloadID = intent.getExtras().getInt("download_id");

        final File file = new File(STATUS.APP_ROOT_PATH + STATUS.DOWNLOAD_DIR + downloadFilename);
        long range = 0;
        int progress = 0;

        if (file.exists()) {
            range = PreferenceUtil.getPrefInt(BaseAddress.UPDATE_URL, 0);
            progress = (int) (range * 100 / file.length());
            if (range == file.length()) {
                installAPP(file);
                return;
            }
        }

        Log.e(TAG, "range = " + range);

        final RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notify_download_layout);
        remoteViews.setProgressBar(R.id.pb_progress, 100, progress, false);
        remoteViews.setTextViewText(R.id.tv_progress, "已下载" + progress + "%");

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContent(remoteViews)
                .setTicker("正在下载")
                .setSmallIcon(R.mipmap.ic_launcher);
        notification = builder.build();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(downloadID, notification);

        UpdateServiceInstance updateServiceInstance = UpdateServiceInstance.getInstance();
        updateServiceInstance.downloadAPK(range, downloadFilename, new DownLoadCallBack() {
            @Override
            public void onProgress(int progress) {
                remoteViews.setProgressBar(R.id.pb_progress, 100, progress, false);
                remoteViews.setTextViewText(R.id.tv_progress, "已下载" + progress + "%");
                notificationManager.notify(downloadID, notification);
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "下载完成");
                notificationManager.cancel(downloadID);
                installAPP(file);
            }

            @Override
            public void onError(String msg) {
                notificationManager.cancel(downloadID);
                Log.e(TAG, "下载发生错误---" + msg);
            }
        });

    }

    private void installAPP(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }
}
