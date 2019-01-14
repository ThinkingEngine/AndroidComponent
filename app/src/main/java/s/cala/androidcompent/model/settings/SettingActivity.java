package s.cala.androidcompent.model.settings;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.allen.library.SuperTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import s.cala.androidcompent.R;
import s.cala.androidcompent.base.BaseActivity;
import s.cala.androidcompent.manager.ActivityManager;
import s.cala.androidcompent.widget.AppTitlebar;

/**
 * package name:s.cala.androidcompent.model.settings
 * create:cala
 * date:2019/1/14
 * commits:通用设置模型
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.at_setting)
    AppTitlebar appTitlebar;
    @BindView(R.id.stv_setting_alert)
    SuperTextView stvSettingAlert;
    @BindView(R.id.stv_wechat_alert)
    SuperTextView stvWechatAlert;
    @BindView(R.id.stv_aotu_get_red)
    SuperTextView stvAotuGetRed;
    @BindView(R.id.stv_set_gretting)
    SuperTextView stvSetGretting;
    @BindView(R.id.stv_change_id)
    SuperTextView stvChangeId;
    @BindView(R.id.stv_offline)
    SuperTextView stvOffline;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_layout;
    }

    @Override
    protected void initView() {

        appTitlebar.setTitle("设置");
        appTitlebar.setFinishClickListener(() -> finish());

        stvSettingAlert.setOnClickListener(v -> showShortToast("设置提醒"));

        stvWechatAlert.setOnClickListener(v -> showShortToast("微信通知"));

        stvAotuGetRed.setOnClickListener(v -> showShortToast("自动接收直直红包"));

        stvSetGretting.setOnClickListener(v -> showShortToast("设置打招呼语"));

        stvChangeId.setOnClickListener(v -> showShortToast("切换身份"));


        stvOffline.setOnClickListener(v -> {
            showLogOutDialog();
        });
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

    /**
     * 退出登录提示
     */
    private void showLogOutDialog() {
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        builder.setTitle("退出提示");
        builder.setMessage("是否退出应用?");
        builder.setNegativeButton("暂不", null);
        builder.setPositiveButton("退出", (dialog, which) -> {
            ActivityManager.getAppManager().finishAllActivity();
        });
        alertDialog = builder.create();
        alertDialog.show();
    }
}
