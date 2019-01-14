package s.cala.androidcompent.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import s.cala.androidcompent.manager.ActivityManager;
import s.cala.androidcompent.utils.ActivityUtils;
import s.cala.androidcompent.utils.ToastUtils;

/**
 * package name:s.cala.androidcompent.base
 * create:cala
 * date:2019/1/8
 * commits:base activity
 */
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        //默认设置竖屏显示。
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //将本Activity加入Activity队列。
        ActivityManager.getAppManager().addActivity(this);
        //绑定butterKnife
        ButterKnife.bind(this);

        initView();
        getData();
    }

    //获取Activity的布局界面
    protected abstract int getLayoutId();

    //初始化view
    protected abstract void initView();

    //获取数据
    public abstract void getData();


    //跳转到指定的Activity。
    public void startActivity(Activity activity){
        ActivityUtils.startActivity(this,activity);
    }

    /**
     * 跳转到指定页面(携带参数)
     *
     *
     * val bundle = Bundle()
     * bundle.putString("id", id)
     * startActivity(bundle, TestActivity())
     *
     *
     * 接收参数：
     * val id: String = intent.getStringExtra("id")
     */
    public void startActivity(Activity activity,Bundle bundle){
        ActivityUtils.startActivity(this,activity,bundle);
    }

    //短Toast。
    public void showShortToast(String contents){
        ToastUtils.showShortToast(this,contents);
    }

    //长Toast。
    public void showLongToast(String contents){
        ToastUtils.showLongToast(this,contents);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getAppManager().finishActivity(this);
    }
}
