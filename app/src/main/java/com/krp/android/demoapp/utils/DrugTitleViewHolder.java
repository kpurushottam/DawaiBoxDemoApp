package com.krp.android.demoapp.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Surati on 11/1/2016.
 */
public class DrugTitleViewHolder extends RecyclerView.ViewHolder {

    private TextView tvText1;

    public DrugTitleViewHolder(View itemView) {
        super(itemView);

        tvText1 = (TextView) itemView.findViewById(android.R.id.text1);
    }

    public void setTitle(String title) {
        this.tvText1.setText(title);
    }
}
