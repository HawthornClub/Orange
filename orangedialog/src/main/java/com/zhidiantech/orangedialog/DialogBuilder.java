package com.zhidiantech.orangedialog;

import android.support.annotation.AnimRes;
import android.support.annotation.LayoutRes;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/3/25 下午6:03
 * Changes (from 2019/3/25)
 * -----------------------------------------------------------------
 */
public abstract class DialogBuilder<T extends DialogPower> {

    public abstract DialogBuilder setLayoutRes(@LayoutRes int layoutRes);

    public abstract DialogBuilder setWidthPercent(float widthP);

    public abstract DialogBuilder setHeightPercent(float heightP);

    public abstract DialogBuilder setIsCancel(boolean outCancel);

    public abstract DialogBuilder setGravity(int gravity);

    public abstract DialogBuilder setAnimType(int animType);

    public abstract DialogBuilder setAnimRes(@AnimRes int AnimRes);

    public abstract DialogBuilder setSaveState(boolean saveState);

    public abstract T createDialog();

}
