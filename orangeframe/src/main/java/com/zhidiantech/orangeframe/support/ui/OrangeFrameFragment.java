package com.zhidiantech.orangeframe.support.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhidiantech.orangeframe.support.eventbus.EventBusUtils;
import com.zhidiantech.orangeframe.support.exception.OrangeFrameInitError;
import com.zhidiantech.orangeframe.support.mvp.IOrangeFrameView;
import com.zhidiantech.orangeframe.support.mvp.OrangeFramePresenter;
import com.zhidiantech.orangeframe.support.progress.ICustomProgress;
import com.zhidiantech.orangeframe.support.progress.IProgressSetting;
import com.zhidiantech.orangeframe.support.widget.ContentLayout;
import com.zhidiantech.orangeframe.support.widget.TitleBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <p>
 * -----------------------------------------------------------------
 * </p>
 *
 * <p>
 * Copyright (C) 芝点科技 wen
 * </p>
 *
 * <p>
 * -----------------------------------------------------------------
 * </p>
 *
 * <p>
 * Create: 2018/12/21 下午2:46
 * </p>
 *
 * <p>
 * Changes (from 2018/12/21)
 * </p>
 *
 *
 * <p>
 * 底层Fragment
 * </p>
 *
 * <p>
 * 构建的视图模型：StatusBar + ToolBar + ContentView
 * </p>
 *
 * <p>
 * -----------------------------------------------------------------
 * </p>
 */
public abstract class OrangeFrameFragment<P extends OrangeFramePresenter> extends Fragment implements IOrangeFrameView {
    /**
     * 通用标题栏
     */
    private TitleBar mTitleBar;

    /**
     * 主内容
     */
    private ContentLayout mContentLayout;

    /**
     * ButterKnife
     */
    private Unbinder mUnbinder;


    /**
     * 设置布局id
     *
     * @return
     */
    protected abstract @LayoutRes
    int getLayoutId();

    /**
     * 是否需要TitleBar 默认不需要
     */
    protected abstract boolean isNeedTitleBar();

    /**
     * 配置默认的TitleBar
     */
    protected void initTitleBar(TitleBar titleBar) {
    }

    /**
     * 是否需要绑定ButterKnife
     *
     * @return
     */
    protected abstract boolean isBindButterKnife();

    /**
     * 初始化Presenter 抽象方法表示每个页面至少有一个Presenter
     *
     * @return NUll:无业务 非NULL：有业务
     */
    protected abstract P initPresenter();

    /**
     * 处理初始化View
     *
     * @param rootView
     */
    protected abstract void handleCreateView(View rootView);

    /**
     * 是否需要集成EventBus
     *
     * @return 默认不集成
     */
    protected boolean isNeedEventBus() {
        return false;
    }

    //-------------多Presenter支持----------

    /**
     * 绑定额外的Presenter
     */
    protected void onAttachMoreView() {
    }

    /**
     * 解除额外的Presenter
     */
    protected void onDetachMoreView() {
    }

    /**
     * Presenter
     */
    protected P mPresenter;

    /**
     * 进度条配置器
     */
    protected static IProgressSetting mProgressSetting;
    /**
     * 页面进度条
     */
    protected ICustomProgress mPageProgress;
    /**
     * IO进度条
     */
    protected ICustomProgress mIoProgress;

    //------------------------------------


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //初始化检查
        if (getLayoutId() == 0) {
            throw new OrangeFrameInitError("Fragment布局id必须设置");
        }

        View rootView = View.inflate(getContext(), getLayoutId(), null);
        //是否需要butterknife
        if (isBindButterKnife()) {
            mUnbinder = ButterKnife.bind(this, rootView);
        }
        //线性布局
        LinearLayout parentView = new LinearLayout(getContext());
        parentView.setOrientation(LinearLayout.VERTICAL);
        parentView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        //content
        mContentLayout = new ContentLayout(getActivity());
        mContentLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                , LinearLayout.LayoutParams.MATCH_PARENT));
        mContentLayout.setContentView(getLayoutId());

        //Toolbar
        if (isNeedTitleBar()) {
            mTitleBar = new TitleBar(getActivity());
        }
        if (isNeedTitleBar()) {
            parentView.addView(mTitleBar);
            initTitleBar(mTitleBar);
        }
        parentView.addView(mContentLayout);

        //设置Presenter
        setPresenter();
        //是否设置了全局的网络错误页面
        if (mHttpErrorRes != 0) {
            initOtherView(mHttpErrorKey, mHttpErrorRes);
        }
        handleCreateView(rootView);
        return parentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //注册EventBus
        if (isNeedEventBus()) {
            EventBusUtils.register(this);
        }
    }

    /**
     * 设置MVP Presenter
     */
    private void setPresenter() {
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.onAttachView(this);
        }
        onAttachMoreView();
    }

    /**
     * 创建进度框
     */
    private void createProgress() {
        if (mProgressSetting != null) {
            mPageProgress = mProgressSetting.initPageCustomProgress();
            mIoProgress = mProgressSetting.initIoCustomProgress();
            mPageProgress.createUI(this.getActivity());
            mIoProgress.createUI(this.getActivity());
        }
    }


    /**
     * 显示指定内容
     *
     * @param layoutKey 指定的key
     */
    protected void showOtherView(String layoutKey) {
        mContentLayout.showOtherView(layoutKey);
    }

    /**
     * 显示核心的业务页面
     */
    protected void showCoreContentView() {
        mContentLayout.showCoreContentView();
    }

    /**
     * 单页面添加
     * 添加指定的资源文件
     *
     * @param layoutKey 资源文件的名称
     * @param layoutRes 指定的资源文件
     */
    protected void initOtherView(final String layoutKey, int layoutRes) {
        mContentLayout.initOtherView(layoutKey, layoutRes);
    }

    /**
     * 多页面添加
     * key - layoutRes 一一对应
     *
     * @param layoutKeys key数组
     * @param layoutRes  layoutRes可变序列
     */
    protected void initOtherViews(String[] layoutKeys, int... layoutRes) {
        mContentLayout.initOtherViews(layoutKeys, layoutRes);
    }

    /**
     * 获取指定key的View
     *
     * @param key 指定的key
     * @return 根据获取的View findViewById 找到子控件
     */
    public View getContentByKey(String key) {
        return mContentLayout.getContentByKey(key);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        this.mUnbinder = null;

        if (mPresenter != null) {
            mPresenter.onDetachView();
        }

        if (isNeedEventBus()){
            EventBusUtils.unregister(this);
        }
        onDetachMoreView();
        mPresenter = null;
    }

    //---------------加载进度条配置----------------


    /**
     * 此方法用于页面中直接调用，处理非网络的IO耗时需求
     * 显示加载对话框
     */
    protected void showProgressPage() {
        mPageProgress.showUI();
    }

    /**
     * 此方法用于页面中直接调用，处理非网络的IO耗时需求
     * 隐藏加载对话框
     */
    protected void hideProgressPage() {
        mPageProgress.hideUI();
    }

    @Override
    public void showProgressIo() {
        mIoProgress.showUI();
    }

    @Override
    public void hideProgressIo() {
        mIoProgress.hideUI();
    }

    //--------------------------------------------

    //------------------配置方法-------------------

    /**
     * 配置全局的加载框
     *
     * @param progress
     */
    public static void initLocalProgress(IProgressSetting progress) {
        mProgressSetting = progress;
    }

    /**
     * 子类单独配置个性化的页面加载框
     *
     * @param pageProgress
     */
    protected void setPageProgress(ICustomProgress pageProgress) {
        mPageProgress = pageProgress;
    }

    /**
     * 子类单独配置个性化的IO加载框
     *
     * @param pageProgress
     */
    protected void setIoProgress(ICustomProgress pageProgress) {
        mIoProgress = pageProgress;
    }

    /**
     * 全局网络错误页key
     */
    private String mHttpErrorKey = "HttpErrorView";
    /**
     * 网络错误页资源id
     */
    private static int mHttpErrorRes = 0;

    /**
     * 初始化网络请求错误页面
     *
     * @param layoutRes 页面资源
     */
    public static void initHttpErrorView(@LayoutRes int layoutRes) {
        mHttpErrorRes = layoutRes;
    }

    /**
     * 显示网络错误页面
     */
    protected void showHttpErrorView() {
        if (mHttpErrorRes == 0) {
            throw new OrangeFrameInitError("请在App中调用initHttpErrorView初始化网络错误视图");
        }
        mContentLayout.showOtherView(mHttpErrorKey);
    }

    /**
     * 获取网络错误页面
     */
    protected View getHttpErrorView() {
        if (mHttpErrorRes == 0) {
            throw new OrangeFrameInitError("请在App中调用initHttpErrorView初始化网络错误视图");
        }
        return mContentLayout.getContentByKey(mHttpErrorKey);
    }

}
