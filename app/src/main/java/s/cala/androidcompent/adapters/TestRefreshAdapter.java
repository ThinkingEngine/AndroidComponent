package s.cala.androidcompent.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import s.cala.androidcompent.R;


/**
 * package name:s.cala.androidcompent.adapters
 * create:cala
 * date:2019/1/16
 * commits:
 */
public class TestRefreshAdapter extends BaseQuickAdapter<String> {

    public TestRefreshAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.test_text,s);
    }
}
