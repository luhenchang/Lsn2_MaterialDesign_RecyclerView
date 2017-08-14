package com.example.ls.lsn2_materialdesign_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 路很长~ on 2017/7/27.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHoler> {
    private List<String> mList;
    private ListView mListview;
    private OnItemClickListener onItemClickListener;

    interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MyRecyclerAdapter(ArrayList<String> mList) {
        this.mList = mList;
    }

    @Override
    public MyViewHoler onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //创建ViewHorlder
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, null);
        MyViewHoler myViewHoler = new MyViewHoler(view);
        return myViewHoler;
    }

    @Override
    public void onBindViewHolder(MyViewHoler holder, final int position) {

        //绑定数据：
        holder.mTv.setText(mList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClickListener(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHoler extends RecyclerView.ViewHolder {
        private TextView mTv;

        public MyViewHoler(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }

    public void addList(String message, int position) {
        mList.add(position, message);
        //notifyDataSetChanged();
        notifyItemInserted(position);
       // notifyItemRangeChanged(int ,int);
    }

    public void removeList(int position) {
        mList.remove(position);
      //  notifyDataSetChanged();
       notifyItemRemoved(position);
    }
}
