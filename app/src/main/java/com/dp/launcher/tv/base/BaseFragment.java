package com.dp.launcher.tv.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dp.launcher.tv.utils.Utils;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class BaseFragment extends Fragment {


    private String TAG = "BaseFragment";

    protected View mRootView;

    protected Unbinder unbinder;

    public Context mContext;

    public CompositeDisposable compositeDisposable;


    public void addDisposable(Disposable d){
        compositeDisposable.add(d);
    }


    protected abstract int getLayoutId();


    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        compositeDisposable = new CompositeDisposable();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Utils.print(TAG,"base fragment onCreateView");
        if (null == mRootView) {
            mRootView = inflater.inflate(getLayoutId(), container,false);
        }

        if(mRootView != null) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if(parent != null) {
                parent.removeView(mRootView);
            }
        }

        unbinder= ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (null != compositeDisposable && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != compositeDisposable && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }


}
