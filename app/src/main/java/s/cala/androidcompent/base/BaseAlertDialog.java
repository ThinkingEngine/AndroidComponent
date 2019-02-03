package s.cala.androidcompent.base;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import s.cala.androidcompent.R;

/**
 * package name:s.cala.androidcompent.base
 * create:cala
 * date:2019/1/24
 * commits:自定义AlertDialog基类
 */
public abstract class BaseAlertDialog extends AlertDialog {
    private AlertDialog alertDialog;

    protected BaseAlertDialog(Context context) {
        super(context);
        AlertDialog.Builder builder = new Builder(context);
        View view = LayoutInflater.from(context).inflate(getLayoutId(),null);
        alertDialog = builder.create();
        alertDialog.setView(view);
        initViews(view);

    }

    public abstract int getLayoutId();

    public abstract void initViews(View view);

    /**
     * 显示AlertDialog对话框
     * */
    public BaseAlertDialog showAlertDialog(){
        if(alertDialog != null){
            alertDialog.show();
        }

        return this;
    }

    /**
     * 隐藏AlertDialog对话框
     * */
    public BaseAlertDialog hideAlertDialog(){
        if(alertDialog != null){
            alertDialog.hide();
        }
        return this;
    }
}
