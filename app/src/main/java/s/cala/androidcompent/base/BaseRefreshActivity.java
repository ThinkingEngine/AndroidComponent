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

import butterknife.BindView;
import s.cala.androidcompent.R;
import s.cala.androidcompent.utils.NetworkUtils;

/**
 * package name:s.cala.androidcompent.base
 * create:cala
 * date:2019/1/14
 * commits:对RecyclerView刷新的封装
 */
abstract class BaseRefreshActivity<T> extends BaseActivity implements OnLoadmoreListener {

    private int AUTO_REFRESH_DELAY_TIME = 180;
    protected int DEFAULT_LIMIT = 15;
    protected int FIRST_PAGE = 1;

    protected int currentPage = FIRST_PAGE;

    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.classicsHeader)
    ClassicsHeader classicsHeader;

    private BaseQuickAdapter<T> adapter;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);


    abstract void initViews();

    abstract void getData(int page);

    abstract BaseQuickAdapter<T> getAdapter();

    @Override
    protected void initView() {
        initViews();
        initRecyclerView();
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

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {

    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(initAdapter());
    }

    private BaseQuickAdapter<T> initAdapter() {
        adapter = getAdapter();
        smartRefreshLayout.setOnLoadmoreListener(this);
        return adapter;
    }

    private void initRefresh() {
        classicsHeader.setEnableLastTime(false);

        if(NetworkUtils.isNetConnected(this)){
           smartRefreshLayout.autoRefresh(AUTO_REFRESH_DELAY_TIME);
        }else{
            adapter.setEmptyView(getEmptyView("网络未连接"));
        }

        smartRefreshLayout.setOnRefreshListener(refreshlayout -> {
            smartRefreshLayout.setLoadmoreFinished(false);
            currentPage = FIRST_PAGE;
            getData(FIRST_PAGE);
        });
    }

    private void setData(@Nullable List<T> data, String emptyStr) {

        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadmore();

        if (data == null || data.isEmpty()) {
            if (currentPage == FIRST_PAGE) {
                adapter.getData().clear();
                adapter.notifyDataSetChanged();
                if (!NetworkUtils.isNetConnected(this)) {
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

    /**
     * 加载空页面
     *
     * @param emptyStr 空状态提示文字
     */
    private View getEmptyView(String emptyStr) {
        View emptyView = LayoutInflater.from(this).inflate(R.layout.list_empty_layout, null);
        TextView tv = emptyView.findViewById(R.id.empty_contents);
        if (emptyStr.isEmpty()) {
            tv.setText("没有数据");
        } else {
            tv.setText(emptyStr);
        }
        return emptyView;
    }
}
