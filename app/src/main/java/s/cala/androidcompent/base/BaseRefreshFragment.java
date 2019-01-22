package s.cala.androidcompent.base;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.List;

import s.cala.androidcompent.R;
import s.cala.androidcompent.utils.NetworkUtils;

/**
 * package name:s.cala.androidcompent.base
 * create:cala
 * date:2019/1/16
 * commits:
 */
abstract class BaseRefreshFragment<T> extends BaseFragment implements OnLoadmoreListener {

    private int AUTO_REFRESH_DELAY_TIME = 180;
    protected int DEFAULT_LIMIT = 15;
    protected int FIRST_PAGE = 1;
    protected int currentPage = FIRST_PAGE;

    protected SmartRefreshLayout smartRefreshLayout;
    protected RecyclerView recyclerView;
    protected ClassicsHeader classicsHeader;

    protected BaseQuickAdapter<T> adapter = null;


    abstract void initViews(View view);
    abstract void getDatas(int page);
    abstract BaseQuickAdapter<T> getAdapter();

    @Override
    public void initView(View view) {

        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        classicsHeader = view.findViewById(R.id.classicsHeader);

        initViews(view);
        initRecyclerview();
        initRefresh();
    }

    @Override
    public void getData() {

    }

    /**
     * 填充列表数据
     *
     * @param data 列表数据
     */
    protected void fillData(@Nullable List<T> data) {
        setData(data, "");
    }

    /**
     * 填充列表数据
     *
     * @param data 列表数据
     * @param
     */
    protected void fillData(@Nullable List<T> data, String emtpyStr) {
        setData(data, emtpyStr);
    }

    private void initRecyclerview(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        BaseQuickAdapter<T> mAdapter = initAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    private BaseQuickAdapter<T> initAdapter(){
        adapter = getAdapter();
        smartRefreshLayout.setOnLoadmoreListener(this);
        return adapter;
    }

    private void initRefresh(){
        classicsHeader.setEnableLastTime(false);

        if(NetworkUtils.isNetConnected(getContext())){
            smartRefreshLayout.autoRefresh(AUTO_REFRESH_DELAY_TIME);
        }else{
            adapter.setEmptyView(getEmptyView("网络未连接"));
        }

        smartRefreshLayout.setOnRefreshListener(refreshlayout -> {
            smartRefreshLayout.setLoadmoreFinished(false);
            currentPage = FIRST_PAGE;
            getDatas(FIRST_PAGE);
        });
    }

    private void setData(@Nullable List<T> data, String emptyStr) {

        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadmore();

        if (data == null || data.isEmpty()) {
            if (currentPage == FIRST_PAGE) {
                adapter.getData().clear();
                adapter.notifyDataSetChanged();
                if (!NetworkUtils.isNetConnected(getContext())) {
                    adapter.setEmptyView(getEmptyView("网络未连接"));
                } else {
                    adapter.setEmptyView(getEmptyView(emptyStr));
                }
            } else {
                smartRefreshLayout.setLoadmoreFinished(true);
            }
            return;
        }

        if (currentPage == FIRST_PAGE) {
            adapter.setNewData(data);
        } else {
            adapter.addData(data);
        }

        if (data.size() < DEFAULT_LIMIT) {
            smartRefreshLayout.setLoadmoreFinished(true);
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        currentPage++;
        getDatas(currentPage);
    }

    /**
     * 加载空页面
     *
     * @param emptyStr 空状态提示文字
     */
    private View getEmptyView(String emptyStr) {
        View emptyView = LayoutInflater.from(getContext()).inflate(R.layout.list_empty_layout, null);
        TextView tv = emptyView.findViewById(R.id.empty_contents);
        if (emptyStr.isEmpty()) {
            tv.setText("没有数据");
        } else {
            tv.setText(emptyStr);
        }
        return emptyView;
    }
}
