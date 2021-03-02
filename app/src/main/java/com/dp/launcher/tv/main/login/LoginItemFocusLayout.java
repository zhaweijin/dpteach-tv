package com.dp.launcher.tv.main.login;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.dp.launcher.tv.R;


public class LoginItemFocusLayout extends RelativeLayout {

    private Animation scaleSmallAnimation;
    private Animation scaleBigAnimation;

    private final static String tag = "focus_layout";

    public LoginItemFocusLayout(Context context) {
        super(context);
        //this.setOnKeyListener(onKeyListener);
    }

    public LoginItemFocusLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //this.setOnKeyListener(onKeyListener);
    }

    public LoginItemFocusLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //this.setOnKeyListener(onKeyListener);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        try {
             if (gainFocus) {
                 setTextLayoutSelected();
                 zoomOut();
             } else {
                 setTextLayoutUnSelect();
                 zoomIn();
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void zoomIn() {
        if (scaleSmallAnimation == null) {
            scaleSmallAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.login_focus_item_zoom_out);
        }
        startAnimation(scaleSmallAnimation);

    }

    private void zoomOut() {
        if (scaleBigAnimation == null) {
            scaleBigAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.login_focus_item_zoom_in);
        }
        startAnimation(scaleBigAnimation);
    }



    public void setTextLayoutSelected() {
        //findViewById(R.id.focus_layout).setBackgroundResource(R.drawable.top_page_item_selected);
    }

    public void setTextLayoutUnSelect() {
        //findViewById(R.id.focus_layout).setBackgroundResource(android.R.color.transparent);
    }


}
