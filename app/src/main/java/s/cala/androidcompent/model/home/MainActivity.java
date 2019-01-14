package s.cala.androidcompent.model.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;

import butterknife.BindView;
import s.cala.androidcompent.R;
import s.cala.androidcompent.base.BaseActivity;

/**
 * package name:s.cala.androidcompent.model.home
 * create:cala
 * date:2019/1/13
 * commits:view pages
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.rbA)
    RadioButton rbA;

    @BindView(R.id.rbB)
    RadioButton rbB;

    @BindView(R.id.rbC)
    RadioButton rbC;

    private Fragment cuurentFragment;

    private FragmentTransaction fragmentTransaction;


    @Override
    protected int getLayoutId() {
        return R.layout.view_page_layout;
    }

    @Override
    protected void initView() {

        rbA.setChecked(true);
    }

    @Override
    public void getData() {

    }

    private void replaceFragment(Fragment fragment){

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
    }
}
