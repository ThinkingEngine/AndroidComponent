package s.cala.androidcompent;


import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import s.cala.androidcompent.base.BaseActivity;
import s.cala.androidcompent.model.home.HomeActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_test)
    TextView textView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new HomeActivity());
            }
        });
    }

    @Override
    public void getData() {

    }
}
