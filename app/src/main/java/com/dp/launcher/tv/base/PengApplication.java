package com.dp.launcher.tv.base;

import android.app.Application;

import com.dp.launcher.tv.utils.ImagePipelineConfigUtils;
import com.facebook.drawee.backends.pipeline.Fresco;

import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;

public class PengApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this, ImagePipelineConfigUtils.getDefaultImagePipelineConfig(this));
        AutoSizeConfig.getInstance().setCustomFragment(true);
        AutoSize.initCompatMultiProcess(this);
    }
}
