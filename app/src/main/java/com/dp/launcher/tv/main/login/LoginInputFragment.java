package com.dp.launcher.tv.main.login;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.dp.launcher.tv.R;
import com.dp.launcher.tv.base.BaseFragment;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class LoginInputFragment extends BaseFragment {


    @BindView(R.id.mode_recyclerview)
    RecyclerView mode_recyclerview;

    //右侧扫描UI
    @BindView(R.id.layout_scan_code)
    FrameLayout layout_scan_code;

    //右侧手机输入验证码UI
    @BindView(R.id.layout_phone_input)
    FrameLayout layout_phone_input;
    @BindView(R.id.number_recyclerview)
    RecyclerView number_recyclerview;


    @Override
    protected int getLayoutId() {
        return R.layout.login_main_input;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initMode();
        initNumberRecyclerview();

        layout_phone_input.setVisibility(View.VISIBLE);
        layout_scan_code.setVisibility(View.INVISIBLE);
    }

    private void initNumberRecyclerview(){
        GridLayoutManager manager = new GridLayoutManager(mContext,3);
        number_recyclerview.setLayoutManager(manager);
        List<LoginNumberData> mDatas = new ArrayList<>();
        for (int i = 1; i <=9; i++) {
            LoginNumberData data = new LoginNumberData();
            data.setName(""+i);
            data.setValue(""+i);
            mDatas.add(data);
        }

        LoginNumberData clear_data = new LoginNumberData();
        clear_data.setName(mContext.getResources().getString(R.string.clear));
        clear_data.setValue(LoginNumberData.CLEAR);
        mDatas.add(clear_data);

        LoginNumberData zero_data = new LoginNumberData();
        zero_data.setName(""+0);
        zero_data.setValue(""+0);
        mDatas.add(zero_data);


        LoginNumberData delete_data = new LoginNumberData();
        delete_data.setName(mContext.getResources().getString(R.string.delete));
        delete_data.setValue(LoginNumberData.DELETE);
        mDatas.add(delete_data);


        NumberAdapter numberAdapter =  new NumberAdapter(mContext,mDatas);
        number_recyclerview.setAdapter(numberAdapter);
    }

    private void initMode(){
        mode_recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        List<LoginTypeData> loginTypeData = new ArrayList<>();

        LoginTypeData scan_code = new LoginTypeData();
        scan_code.setIcon(R.drawable.ic_launcher_background);
        scan_code.setName(mContext.getResources().getString(R.string.login_scan_login));
        scan_code.setSub_name(mContext.getResources().getString(R.string.login_scan_login_sub));
        loginTypeData.add(scan_code);

        LoginTypeData phone_data = new LoginTypeData();
        phone_data.setIcon(R.drawable.ic_launcher_background);
        phone_data.setName(mContext.getResources().getString(R.string.login_phone_login));
        phone_data.setSub_name(mContext.getResources().getString(R.string.login_phone_login_sub));
        loginTypeData.add(phone_data);

        LoginTypeAdapter loginTypeAdapter =  new LoginTypeAdapter(mContext,loginTypeData);
        mode_recyclerview.setAdapter(loginTypeAdapter);

        mode_recyclerview.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0, mContext.getResources().getDimensionPixelSize(R.dimen.logon_item_div), 0, 0);
            }
        });


        loginTypeAdapter.setLoginItemFocusListener(new LoginItemFocusListener() {
            @Override
            public void onFocusItem(int position) {
                if(position==0){
                    switchItemScanCode();
                }else if(position==1){
                    switchItemPhone();
                }
            }
        });
    }



    private void switchItemScanCode(){
        layout_scan_code.setVisibility(View.VISIBLE);
        layout_phone_input.setVisibility(View.INVISIBLE);
    }


    private void switchItemPhone(){
        layout_scan_code.setVisibility(View.INVISIBLE);
        layout_phone_input.setVisibility(View.VISIBLE);
    }
}
