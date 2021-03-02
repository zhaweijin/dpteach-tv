package com.dp.launcher.tv.main.login;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import com.dp.launcher.tv.R;
import com.dp.launcher.tv.base.BaseFragment;
import com.dp.launcher.tv.utils.HiveRecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class SelectUserFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    HiveRecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.login_main_select_user;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(manager);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0, 0, mContext.getResources().getDimensionPixelSize(R.dimen.dp_18), mContext.getResources().getDimensionPixelSize(R.dimen.dp_34));
            }
        });

        List<String> mDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mDatas.add("i"+i);
        }
        SelectUserAdapter adapter =  new SelectUserAdapter(mContext,mDatas);
        recyclerView.setAdapter(adapter);
    }
}
