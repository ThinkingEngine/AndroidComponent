package s.cala.androidcompent;


import android.widget.TextView;

import butterknife.BindView;
import s.cala.androidcompent.base.BaseActivity;
import s.cala.androidcompent.model.home.HomeActivity;
import s.cala.androidcompent.model.settings.SettingActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_test)
    TextView textView;
    @BindView(R.id.tv_setting)
    TextView setting;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        textView.setOnClickListener(v -> startActivity(new HomeActivity()));
        setting.setOnClickListener(v -> startActivity(new SettingActivity()));
    }

    @Override
    public void getData() {

    }
}
