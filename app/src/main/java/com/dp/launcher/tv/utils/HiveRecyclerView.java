package com.dp.launcher.tv.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by ThinkPad on 2017/9/7.
 */

public class HiveRecyclerView extends RecyclerView {

    private String tag = "HiveRecyclerView";

    public HiveRecyclerView(Context context) {
        super(context);
        init();
    }

    public HiveRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HiveRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        //启用子视图排序功能
        setChildrenDrawingOrderEnabled(true);
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
    }



    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        View view = getFocusedChild();
        if (null != view) {
            int position = getChildAdapterPosition(view) - getFirstVisiblePosition();
            if (position < 0) {
                return i;
            } else {
                if (i == childCount - 1) {//这是最后一个需要刷新的item
                    if (position > i) {
                        position = i;
                    }
                    return position;
                }
                if (i == position) {//这是原本要在最后一个刷新的item
                    return childCount - 1;
                }
            }
        }
        return i;
    }

    public int getFirstVisiblePosition() {
        if (getChildCount() == 0){
            return 0;
        }else{
            return getChildAdapterPosition(getChildAt(0));
        }
    }







}
