package s.cala.androidcompent;


import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import s.cala.androidcompent.base.BaseActivity;
import s.cala.androidcompent.module.home.HomeActivity;
import s.cala.androidcompent.module.home.RefreshActivity;
import s.cala.androidcompent.module.settings.SettingActivity;

public class MainActivity extends BaseActivity {

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
}
