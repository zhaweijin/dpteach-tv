package com.dp.launcher.tv.main.login;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.dp.launcher.tv.R;
import com.dp.launcher.tv.base.BaseFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class UserCenterFragment extends BaseFragment {

    @BindView(R.id.layout_exit)
    FrameLayout layout_exit;

    @BindView(R.id.user_icon)
    SimpleDraweeView user_icon;

    @Override
    protected int getLayoutId() {
        return R.layout.login_main_user_center;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        layout_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

    }
}
