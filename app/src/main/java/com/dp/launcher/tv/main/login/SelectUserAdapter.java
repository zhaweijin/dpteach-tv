package com.dp.launcher.tv.main.login;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dp.launcher.tv.R;
import com.dp.launcher.tv.utils.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;


public class SelectUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "login";

    private Context mContext;
    private List<String> mDatas;

    /**
     * activity 调用
     *
     * @param context
     * @param mDatas
     */
    public SelectUserAdapter(Context context, List<String> mDatas) {
        mContext = context;
        this.mDatas = mDatas;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(View.inflate(mContext, R.layout.login_main_select_user_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //获取控制权
        final RecyclerViewHolder viewHolder = (RecyclerViewHolder) holder;
        /*MainModuleData entity = mDatas.get(position);
        viewHolder.itemView.setTag(position);
        try {
            viewHolder.mName.setText(entity.getEntryTitle());
            FrescoHelper.setImage(viewHolder.icon, Uri.parse(entity.getBgImage()));
        } catch (Exception e) {
            e.printStackTrace();
        }*/


    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        SimpleDraweeView icon;
        TextView mName;
        TextView subName;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.name);
            icon = (SimpleDraweeView) itemView.findViewById(R.id.icon);
            subName = (TextView) itemView.findViewById(R.id.sub_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            try {
                int id = (int) v.getTag();
                Utils.print(TAG, "==" + id);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
