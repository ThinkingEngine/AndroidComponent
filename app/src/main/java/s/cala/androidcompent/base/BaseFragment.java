package s.cala.androidcompent.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import s.cala.androidcompent.utils.ActivityUtils;
import s.cala.androidcompent.utils.ToastUtils;

/**
 * package name:s.cala.androidcompent.base
 * create:cala
 * date:2019/1/11
 * commits:base fragment
 */
abstract class BaseFragment extends Fragment {

    protected Context context = getContext();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        getData();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    /**
     * 获取当前layoutId
     */
    public abstract int getLayoutId();

    /**
     * 初始化控件
     */
    public abstract void initView(View view);

    /**
     * 获取数据
     */
    public abstract void getData();

    /**
     * 跳转到指定页面
     */
    public void startActivity(BaseActivity activity) {
        ActivityUtils.startActivity(context,activity);
    }

    /**
     * 跳转到指定页面(携带参数)
     * <p>
     * val bundle = Bundle()
     * bundle.putString("id", id)
     * startActivity(bundle, TestActivity())
     * <p>
     * 接收参数：
     * val id: String = intent.getStringExtra("id")
     */
    public void startActivity(BaseActivity activity, Bundle bundle) {
        ActivityUtils.startActivity(context, activity, bundle);
    }

    /**
     * Toast短提示
     */
    public void showShortToast(String content) {
        if (content != null) {
            ToastUtils.showShortToast(context, content);
        }
    }

    /**
     * Toast长提示
     */
    public void showLongToast(String content) {
        if (content != null) {
            ToastUtils.showLongToast(context, content);
        }
    }

    
}
