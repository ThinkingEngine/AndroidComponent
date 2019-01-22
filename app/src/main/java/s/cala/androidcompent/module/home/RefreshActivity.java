package s.cala.androidcompent.module.home;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import s.cala.androidcompent.R;
import s.cala.androidcompent.adapters.TestRefreshAdapter;
import s.cala.androidcompent.base.BaseRefreshActivity;

/**
 * package name:s.cala.androidcompent.model.home
 * create:cala
 * date:2019/1/16
 * commits:
 */
public class RefreshActivity extends BaseRefreshActivity {

    List<String> datas = new ArrayList<>();

    @Override
    protected void initViews() {

    }

    @Override
    protected void getData(int page) {
        datas.add("new");

        fillData(datas);
    }

    @Override
    protected BaseQuickAdapter getAdapter() {


        datas.add("1111");
        datas.add("2222");
        datas.add("3333");
        datas.add("4444");
        datas.add("5555");
        TestRefreshAdapter adapter = new TestRefreshAdapter(R.layout.item_layout, datas);
        return adapter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_refresh_layout;
    }
}
