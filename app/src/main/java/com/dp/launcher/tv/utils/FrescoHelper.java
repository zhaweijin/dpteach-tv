package com.dp.launcher.tv.utils;

import android.net.Uri;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import androidx.annotation.DrawableRes;

/**
 * Created by carter on 7/9/17.
 */

public class FrescoHelper {


    /**
     * uri对应的图片在指定宽高在simpleDraweeView上显示
     *
     * @param simpleDraweeView
     * @param uri
     * @return
     */
    public static void setImage(SimpleDraweeView simpleDraweeView, Uri uri) {

        ImageRequest request = ImageRequest.fromUri(uri);
        setImage(simpleDraweeView, request);
    }

    /**
     * uri对应的图片在指定宽高在simpleDraweeView上显示，options主要是用来设置大小，Fresco会找到最适应的图片大小
     *
     * @param simpleDraweeView
     * @param uri
     * @param options
     */
    public static void setImage(SimpleDraweeView simpleDraweeView, Uri uri, ResizeOptions options) {

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(options)
                .setAutoRotateEnabled(true)
                .build();
        setImage(simpleDraweeView, request);
    }

    public static void setImage(SimpleDraweeView simpleDraweeView, Uri uri, ResizeOptions options, int errorIcon) {

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(options)
                .setAutoRotateEnabled(true)
                .build();
        setImage(simpleDraweeView, request);
    }


    /**
     * 将ImageRequest请求返回的图片显示在simpleDraweeView上
     *
     * @param simpleDraweeView
     * @param request
     */
    public static void setImage(SimpleDraweeView simpleDraweeView, ImageRequest request) {

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setTapToRetryEnabled(true)
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);
        simpleDraweeView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
    }


    /**
     * 给simpleDraweeView设置res下的资源图片
     *
     * @param simpleDraweeView
     * @param resId            图片对应的resId
     */
    public static void setImageResource(SimpleDraweeView simpleDraweeView, @DrawableRes int resId) {

        ImageRequestBuilder imageRequest = ImageRequestBuilder.newBuilderWithResourceId(resId);
        simpleDraweeView.setImageURI(imageRequest.getSourceUri());
    }


    /**
     * 给photo的图片以半径为radius的圆角显示
     *
     * @param photoView
     * @param radius
     */
    public static void setCornersRadius(SimpleDraweeView photoView, int radius) {

        GenericDraweeHierarchy hierarchy = photoView.getHierarchy();
        RoundingParams roundingParams = hierarchy.getRoundingParams();
        if (roundingParams == null) {

            roundingParams = new RoundingParams();
        }
        roundingParams.setCornersRadius(radius);
        hierarchy.setRoundingParams(roundingParams);
    }



    /**
     * 以高斯模糊显示。
     *
     * @param draweeView View。
     * @param url        url.
     * @param iterations 迭代次数，越大越魔化。
     * @param blurRadius 模糊图半径，必须大于0，越大越模糊。
     */
    public static void showUrlBlur(SimpleDraweeView draweeView, String url, int iterations, int blurRadius) {
        try {
            Uri uri = Uri.parse(url);
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setPostprocessor(new IterativeBoxBlurPostProcessor(iterations, blurRadius))
                    .build();
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setOldController(draweeView.getController())
                    .setImageRequest(request)
                    .build();
            draweeView.setController(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
