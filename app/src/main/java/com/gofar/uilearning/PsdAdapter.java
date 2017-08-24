package com.gofar.uilearning;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/8/24 16:12
 */
public class PsdAdapter extends RecyclerView.Adapter<PsdAdapter.ViewHolder> {

    private List<Integer> mList;
    private IPsdChangeListener mListener;

    public PsdAdapter(List<Integer> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_psd, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Integer data = mList.get(position);
        if (data == null) {
            holder.item.setEnabled(false);
            holder.text.setVisibility(View.GONE);
            holder.icon.setVisibility(View.GONE);
        } else {
            holder.item.setEnabled(true);
            if (data == -1) {
                holder.text.setVisibility(View.GONE);
                holder.icon.setVisibility(View.VISIBLE);
                holder.icon.setImageResource(R.mipmap.ic_delete);
                holder.item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.delete();
                        }
                    }
                });
            } else {
                holder.text.setVisibility(View.VISIBLE);
                holder.text.setText(String.valueOf(data));
                holder.icon.setVisibility(View.GONE);
                holder.item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.add(data);
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setIPsdChangeListener(IPsdChangeListener mListener) {
        this.mListener = mListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        FrameLayout item;
        TextView text;
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            item = (FrameLayout) itemView.findViewById(R.id.layout_item);
            text = (TextView) itemView.findViewById(R.id.tv_text);
            icon = (ImageView) itemView.findViewById(R.id.iv_icon);
        }
    }

}
