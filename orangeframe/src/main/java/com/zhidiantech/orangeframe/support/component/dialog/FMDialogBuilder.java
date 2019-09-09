package com.zhidiantech.orangeframe.support.component.dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/3/25 下午6:03
 * Changes (from 2019/3/25)
 * -----------------------------------------------------------------
 */
public class FMDialogBuilder extends DialogBuilder {

    private FMDialog mFmDialog=new FMDialog();
    private Bundle mBundle=new Bundle();
    @Override
    public DialogBuilder setLayoutRes(int layoutRes) {
        mBundle.putInt(DialogPower.LAYOUTRES,layoutRes);
        return this;
    }

    @Override
    public DialogBuilder setWidthPercent(float widthP) {
        mBundle.putFloat(DialogPower.WIDTHP,widthP);
        return this;
    }

    @Override
    public DialogBuilder setHeightPercent(float heightP) {
        mBundle.putFloat(DialogPower.HEIGHTP,heightP);
        return this;
    }

    @Override
    public DialogBuilder setIsCancel(boolean outCancel) {
        mBundle.putBoolean(DialogPower.OUTCANCEL,outCancel);
        return this;
    }

    @Override
    public DialogBuilder setGravity(int gravity) {
        mBundle.putInt(DialogPower.GRAVITY,gravity);
        return this;
    }

    @Override
    public DialogBuilder setAnimType(int animType) {
        mBundle.putInt(DialogPower.ANIM,animType);
        return this;
    }

    @Override
    public DialogBuilder setAnimRes(int AnimRes) {
        mBundle.putInt(DialogPower.ANIMRES,AnimRes);
        return this;
    }

    @Override
    public DialogBuilder setSaveState(boolean saveState) {
        mBundle.putBoolean(DialogPower.SAVESTATE,saveState);
        return this;
    }

    public DialogBuilder setFragmentManager(FragmentManager fragemtManager){
        mFmDialog.setFragmentManager(fragemtManager);
        return this;
    }


    @Override
    public FMDialog createDialog() {
        mFmDialog.setArguments(mBundle);
        return mFmDialog;
    }

}
