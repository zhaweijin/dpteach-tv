package com.dp.launcher.tv.main.login;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.dp.launcher.tv.R;
import com.dp.launcher.tv.base.BaseActivity;
import java.util.LinkedList;
import java.util.Queue;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;


public class LoginMainActivity extends BaseActivity {

    private Queue<Integer> receive_phone_code = new LinkedList<Integer>();
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


    private void initPhoneInputRecyclerview(){
        GridLayoutManager manager = new GridLayoutManager(mContext, 3);
        //rectangle_recyclerview.setLayoutManager(manager);

        /*rectangle_recyclerview.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0, 0, 0, 0);
            }
        });*/
    }

    private void showPhoneInputUI(){

    }
}
