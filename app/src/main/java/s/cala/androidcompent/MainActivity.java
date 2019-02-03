package s.cala.androidcompent;


import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import s.cala.androidcompent.base.BaseActivity;
import s.cala.androidcompent.constant.STATUS;
import s.cala.androidcompent.module.home.HomeActivity;
import s.cala.androidcompent.module.home.RefreshActivity;
import s.cala.androidcompent.module.settings.SettingActivity;
import s.cala.androidcompent.network.serviceinstance.UpdateServiceInstance;
import s.cala.androidcompent.protocol.updates.ApkUpdateData;
import s.cala.androidcompent.utils.SystemUtils;

public class MainActivity extends BaseActivity {

    private String TAG = "s.cala.androidcompent.MainActivity";
    private float exitTime = 0l;

    @BindView(R.id.tv_test)
    TextView textView;
    @BindView(R.id.tv_setting)
    TextView setting;
    @BindView(R.id.tv_refresh_activity)
    TextView tvRefreshActivity;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        textView.setOnClickListener(v -> startActivity(new HomeActivity()));
        setting.setOnClickListener(v -> startActivity(new SettingActivity()));
        tvRefreshActivity.setOnClickListener(v -> startActivity(new RefreshActivity()));
    }

    @Override
    public void getData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if(System.currentTimeMillis() - exitTime > 2000){
                showShortToast("再点击一次退出程序");
                exitTime = System.currentTimeMillis();
            }else{
                finish();
                return super.onKeyDown(keyCode, event);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void checkUpdate() {

//        String version = PreferenceUtil.getPrefString("VERSION", "");
        try {
            final String currentVersion = SystemUtils.getVersionName(this);

            UpdateServiceInstance
                    .getInstance()
                    .getUpdateInfo()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(Schedulers.io())
                    .subscribe(new DefaultObserver<ApkUpdateData>() {
                        @Override
                        public void onNext(ApkUpdateData apkUpdateData) {
                            if (apkUpdateData == null) {
                                showShortToast("获取更新信息异常");
                                return;
                            }
                            if (!currentVersion.isEmpty()) {
                                STATUS.VersionComp vc = SystemUtils.compareVersion(currentVersion, apkUpdateData.getVersion_name());
                                switch (vc) {
                                    case NO_UPDATE:
                                        Log.e(TAG, "当前为最小版本");
                                        break;
                                    case ERROR:
                                        Log.e(TAG, "版本比较状态异常");
                                        break;
                                    case NEED_UPDATE:
                                        Log.e(TAG, "进行升级");
                                        break;
                                    default:
                                        Log.e(TAG, "检测完成");
                                }
                            } else {
                                showShortToast("获取app版本信息异常");
                                return;
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            showShortToast("未能获取更新信息:" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
