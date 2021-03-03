package com.dp.launcher.tv.main;



import android.os.Bundle;

import com.dp.launcher.tv.R;
import com.dp.launcher.tv.base.BaseActivity;
import com.dp.launcher.tv.main.login.LoginMainActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //试提代码
        //LoginMainActivity.launch(mContext);
    }
}
