package com.dp.launcher.tv.main.login;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.dp.launcher.tv.R;
import com.dp.launcher.tv.utils.Utils;

import java.util.List;
import androidx.recyclerview.widget.RecyclerView;


public class LoginTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "login";

    private Context mContext;
    private List<LoginTypeData> mDatas;

    private LoginItemFocusListener loginItemFocusListener;

    public void setLoginItemFocusListener(LoginItemFocusListener loginItemFocusListener) {
        this.loginItemFocusListener = loginItemFocusListener;
    }

    /**
     * activity 调用
     *
     * @param context
     * @param mDatas
     */
    public LoginTypeAdapter(Context context, List<LoginTypeData> mDatas) {
        mContext = context;
        this.mDatas = mDatas;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(View.inflate(mContext, R.layout.login_main_input_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //获取控制权
        final RecyclerViewHolder viewHolder = (RecyclerViewHolder) holder;
        LoginTypeData entity = mDatas.get(position);
        viewHolder.itemView.setTag(position);

        viewHolder.mName.setText(entity.getName());
        viewHolder.icon.setImageResource(entity.getIcon());
        viewHolder.subName.setText(entity.getSub_name());



    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnFocusChangeListener {
        ImageView icon;
        TextView mName;
        TextView subName;
        ImageView arrow;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.name);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            subName = (TextView) itemView.findViewById(R.id.sub_name);
            arrow = (ImageView)itemView.findViewById(R.id.arrow);
            itemView.setOnFocusChangeListener(this);
        }


        @Override
        public void onFocusChange(View view, boolean b) {
            int id = (int) view.getTag();
            if(b && loginItemFocusListener!=null) loginItemFocusListener.onFocusItem(id);
            Utils.print(TAG,"focus="+b);
            if(b){
                arrow.setVisibility(View.VISIBLE);
            }else{
                arrow.setVisibility(View.INVISIBLE);
            }
        }
    }


}
