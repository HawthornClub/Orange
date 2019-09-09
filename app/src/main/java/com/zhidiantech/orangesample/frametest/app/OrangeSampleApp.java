package com.zhidiantech.orangesample.frametest.app;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.zhidiantech.orangeframe.easyhttp.http.OfHttpUtil;
import com.zhidiantech.orangeframe.imageloader.GlideImageLoaderStrategy;
import com.zhidiantech.orangeframe.imageloader.ImageLoader;
import com.zhidiantech.orangeframe.support.ui.OrangeFrameActivity;
import com.zhidiantech.orangeframe.support.progress.ICustomProgress;
import com.zhidiantech.orangeframe.support.progress.IProgressSetting;
import com.zhidiantech.orangeframe.support.ui.OrangeFrameFragment;
import com.zhidiantech.orangesample.BuildConfig;
import com.zhidiantech.orangesample.R;
import com.zhidiantech.orangesample.frametest.flowsence.uibs.SampleUIFragment;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/1/2 下午6:14
 * Changes (from 2019/1/2)
 * -----------------------------------------------------------------
 */
public class OrangeSampleApp extends TinkerApplication {
    //bugly默认构造器
    public OrangeSampleApp(){
        super(ShareConstants.TINKER_ENABLE_ALL
                ,"com.zhidiantech.orangesample.frametest.app.OrangeApplicationLike"
                ,"com.tencent.tinker.loader.TinkerLoader"
                , false);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Activity 与 Fragment
        OrangeFrameActivity.initLocalProgress(new IProgressSetting() {
            @Override
            public ICustomProgress initPageCustomProgress() {
                return new OrangeSamplePorgress("正在加载页面...");
            }

            @Override
            public ICustomProgress initIoCustomProgress() {
                return new OrangeSamplePorgress("模式接口请求中...");
            }
        });
        SampleUIFragment.initLocalProgress(new IProgressSetting() {
            @Override
            public ICustomProgress initPageCustomProgress() {
                return null;
            }

            @Override
            public ICustomProgress initIoCustomProgress() {
                return null;
            }
        });

        OrangeFrameActivity.initHttpErrorView(R.layout.net_error);
        OrangeFrameFragment.initHttpErrorView(R.layout.net_error);

        //网络组件初始化
        OfHttpUtil.init(this);
        OfHttpUtil.getInstance().startConfig().setBaseUrl("http://www.xx.cn")
                .setLog(BuildConfig.DEBUG)
                .setConnectTimeout(10000);

        //ImageLoader初始化
        ImageLoader.setLoadImgStrategy(new GlideImageLoaderStrategy());
    }
}
