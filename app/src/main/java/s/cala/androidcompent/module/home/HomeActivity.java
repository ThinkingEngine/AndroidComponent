package s.cala.androidcompent.module.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import s.cala.androidcompent.R;
import s.cala.androidcompent.base.BaseActivity;
import s.cala.androidcompent.module.fragments.TitlePageFragment;

/**
 * package name:s.cala.androidcompent.model.home
 * create:cala
 * date:2019/1/13
 * commits:view pages
 */
public class HomeActivity extends BaseActivity {

    @BindView(R.id.rbA)
    RadioButton rbA;

    @BindView(R.id.rbB)
    RadioButton rbB;

    @BindView(R.id.rbC)
    RadioButton rbC;

    @BindView(R.id.radioGroupMain)
    RadioGroup radioGroup;

    private Fragment cuurentFragment;

    private Fragment titleAFragment;
    private Fragment titleBFragment;
    private Fragment titleCFragment;

    private final FragmentManager manager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;

    private long exitTime = 0L;

    //测试

    @Override
    protected int getLayoutId() {
        return R.layout.view_page_layout;
    }

    @Override
    protected void initView() {


        titleAFragment = new TitlePageFragment();
        titleBFragment = new TitlePageFragment();
        titleCFragment = new TitlePageFragment();


        //默认选择首页
        rbA.setChecked(true);
        replaceFragment(titleAFragment);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rbA:
                    replaceFragment(titleAFragment);
                    break;
                case R.id.rbB:
                    replaceFragment(titleBFragment);
                    break;
                case R.id.rbC:
                    replaceFragment(titleCFragment);
                    break;
                default:
                    replaceFragment(titleAFragment);
            }
        });

    }

    @Override
    public void getData() {

    }

    /**
     * 连按两次后退键退出程序
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                showShortToast("再点一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                return super.onKeyDown(keyCode, event);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    //
    private void replaceFragment(Fragment fragment) {

        fragmentTransaction = manager.beginTransaction();
        if (cuurentFragment == null) {
            fragmentTransaction.add(R.id.layout_main_content, fragment).commit();
        } else {
            if (!fragment.isAdded()) {
                fragmentTransaction.hide(cuurentFragment).add(R.id.layout_main_content, fragment).commit();
            } else {
                fragmentTransaction.hide(cuurentFragment).show(fragment).commit();
            }
        }
        cuurentFragment = fragment;
    }
}
