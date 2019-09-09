package com.zhidiantech.orangesample.frametest;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhidiantech.orangeframe.support.component.PushDialog;
import com.zhidiantech.orangeframe.support.component.dialog.DialogBuilder;
import com.zhidiantech.orangeframe.support.component.dialog.DialogPower;
import com.zhidiantech.orangeframe.support.component.dialog.FMDialog;
import com.zhidiantech.orangeframe.support.component.dialog.FMDialogBuilder;
import com.zhidiantech.orangeframe.support.ui.OrangeFrameActivity;
import com.zhidiantech.orangeframe.support.mvp.OrangeFramePresenter;
import com.zhidiantech.orangeframe.support.widget.TitleBar;
import com.zhidiantech.orangesample.R;
import com.zhidiantech.orangesample.frametest.flowsence.morebs.SampleMoreActivity;
import com.zhidiantech.orangesample.frametest.flowsence.singlebs.SampleSingleBsActivity;

import butterknife.BindView;


public class MainActivity extends OrangeFrameActivity implements View.OnClickListener {
    @BindView(R.id.bt_open_1)
    Button mOpen1;
    @BindView(R.id.bt_open_2)
    Button mOpen2;

    @BindView(R.id.bt_open_3)
    Button mOpen3;
    private boolean isTitlebarColorChange;

    @BindView(R.id.bt_open_single)
    Button mOpenSingle;

    Button mNoDataClose;

    @BindView(R.id.bt_open_push_dialog)
    Button mOpenPushDialog;  //弹窗
    PushDialog mPushDialog;

    @BindView(R.id.bt_open_push_dialog_update)
    Button mUpdateDialog;
    @Override
    protected boolean isNeedTitleBar() {
        return true;
    }

    @Override
    protected void initTitleBar(TitleBar titleBar) {
        super.initTitleBar(titleBar);
        //2.toolbar自定义样式示例
        titleBar.setTitle("Orange体验调试面板")
                .setTitleBarBackground(getResources().getColor(R.color.white))
                .setRightIconDrawable(R.drawable.icon_mall)
                .setRightIconSize(20, 20)
                .setRightIconMarginRight(30)
                .setIconClick(new TitleBar.TitleBarListener() {
                    @Override
                    public void onLeftIconClick() {
                        Toast.makeText(MainActivity.this, "返回", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRightIconTvClick() {
                        Toast.makeText(MainActivity.this, "右边按钮", Toast.LENGTH_SHORT).show();
                        showProgressPage();
                    }
                });
    }

    @Override
    protected boolean isBindButterKnife() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected OrangeFramePresenter initPresenter() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initEvent();
        setNoDataContent();

    }

    private void initEvent() {
        mOpen1.setOnClickListener(this);
        mOpen2.setOnClickListener(this);
        mOpen3.setOnClickListener(this);
        mOpenSingle.setOnClickListener(this);
        mOpenPushDialog.setOnClickListener(this);
        mUpdateDialog.setOnClickListener(this);
    }

    private void setNoDataContent() {
        initOtherView("无数据页面", R.layout.no_data);
        View view = getContentByKey("无数据页面");
        mNoDataClose = view.findViewById(R.id.bt_close);
        mNoDataClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCoreContentView();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_open_1:
                showOtherView("无数据页面");
                break;
            case R.id.bt_open_2:
                Intent intent = new Intent(MainActivity.this, SampleMoreActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_open_3:
                if (isTitlebarColorChange) {
                    getTitleBar().setTitleBarBackground(getResources().getColor(R.color.white));
                    isTitlebarColorChange = false;
                } else {
                    getTitleBar().setTitleBarBackground(getResources().getColor(R.color.colorPrimary));
                    isTitlebarColorChange = true;
                }
                break;
            case R.id.bt_open_single:
                Intent intentSingle = new Intent(MainActivity.this, SampleSingleBsActivity.class);
                startActivity(intentSingle);
                break;
            case R.id.bt_open_push_dialog:
                if (mPushDialog == null) {
                    mPushDialog=PushDialog.createDialog(R.layout.dialog_sample,1.0f,0.5f,true,PushDialog.GRAVITY_CENTER);
                    mPushDialog.setViewLisenter(new PushDialog.ViewListener() {
                        @Override
                        public void handleUI(View view) {

                        }
                    });
                }
                mPushDialog.showDialog(getSupportFragmentManager());
                break;
            case R.id.bt_open_push_dialog_update:
                FMDialogBuilder fmDialogBuilder=new FMDialogBuilder();
                fmDialogBuilder.setFragmentManager(getSupportFragmentManager())
                        .setAnimRes(0)
                        .setGravity(FMDialog.GRAVITY_TOP)
                        .setHeightPercent(0.5f)
                        .setWidthPercent(1f)
                        .setIsCancel(true)
                        .setLayoutRes(R.layout.dialog_sample)
                        .setSaveState(false);
                FMDialog fmDialog=fmDialogBuilder.createDialog();
                fmDialog.setUIHandler(new DialogPower.UIHandler() {
                    @Override
                    public void handleUI(View viewLayout) {

                    }
                });
                fmDialog.showDialog();
                break;

            default:
                break;
        }
    }
}
