package s.cala.androidcompent.module.fragments;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import s.cala.androidcompent.R;
import s.cala.androidcompent.base.BaseFragment;

/**
 * package name:s.cala.androidcompent.model.fragments
 * create:cala
 * date:2019/1/14
 * commits:title fragment
 */
public class TitlePageFragment extends BaseFragment {

    @BindView(R.id.tv_text)
    TextView tv;

    @Override
    public int getLayoutId() {
        return R.layout.title_page_layout;
    }

    @Override
    public void initView(View view) {
        tv.setText("test");
    }

    @Override
    public void getData() {

    }
}
