package com.dp.launcher.tv.main.login;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dp.launcher.tv.R;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class NumberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "login";

    private Context mContext;
    private List<LoginNumberData> mDatas;

    private LoginItemClickListener loginItemClickListener;

    public void setLoginItemClickListener(LoginItemClickListener loginItemClickListener) {
        this.loginItemClickListener = loginItemClickListener;
    }

    /**
     * activity 调用
     *
     * @param context
     * @param mDatas
     */
    public NumberAdapter(Context context, List<LoginNumberData> mDatas) {
        mContext = context;
        this.mDatas = mDatas;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(View.inflate(mContext, R.layout.login_main_phone_input_item_number, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //获取控制权
        final RecyclerViewHolder viewHolder = (RecyclerViewHolder) holder;
        LoginNumberData entity = mDatas.get(position);
        viewHolder.itemView.setTag(position);

        viewHolder.number.setText(entity.getName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView number;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            number = (TextView) itemView.findViewById(R.id.number);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(loginItemClickListener!=null){
                int id = (int) view.getTag();
                loginItemClickListener.onItemClick(id);
            }
        }
    }


}
