package s.cala.androidcompent.base;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import s.cala.androidcompent.R;

/**
 * package name:s.cala.androidcompent.base
 * create:cala
 * date:2019/1/14
 * commits:封装底部弹出框
 */
abstract class BaseBottomDialog {

    private Dialog dialog;

    public BaseBottomDialog(Context context) {
        dialog = new Dialog(context, R.style.bottomDialog);
        View contentView = LayoutInflater.from(context).inflate(getLayoutId(), null);
        dialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = context.getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        Objects.requireNonNull(dialog.getWindow()).setGravity(Gravity.BOTTOM);
        dialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        initView(contentView);
    }

    public abstract int getLayoutId();

    public abstract void initView(View view);

    /**
     * 显示弹框
     */
    public BaseBottomDialog showDialog() {
        if (dialog != null) {
            dialog.show();
        }
        return this;
    }

    /**
     * 隐藏弹框
     */
    public BaseBottomDialog dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
        return this;
    }
}
