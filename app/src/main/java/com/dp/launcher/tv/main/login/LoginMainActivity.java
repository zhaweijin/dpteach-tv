package com.dp.launcher.tv.main.login;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.dp.launcher.tv.R;
import com.dp.launcher.tv.base.BaseActivity;
import java.util.LinkedList;
import java.util.Queue;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import butterknife.BindView;


public class LoginMainActivity extends BaseActivity {

    private FragmentManager fragmentManager;


    public static void launch(Context context) {
        Intent intent = new Intent(context, LoginMainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        fragmentManager = getSupportFragmentManager();
        switchInputFragment();
        //switchSelectUserFragment();
        //switchUserCenterFragment();
    }


    private void switchInputFragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        LoginInputFragment inputFragment = new LoginInputFragment();
        transaction.replace(R.id.container, inputFragment);
        transaction.commitAllowingStateLoss();
    }

    private void switchSelectUserFragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        SelectUserFragment selectUserFragment = new SelectUserFragment();
        transaction.replace(R.id.container, selectUserFragment);
        transaction.commitAllowingStateLoss();
    }

    private void switchUserCenterFragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        UserCenterFragment userCenterFragment = new UserCenterFragment();
        transaction.replace(R.id.container, userCenterFragment);
        transaction.commitAllowingStateLoss();
    }




}
