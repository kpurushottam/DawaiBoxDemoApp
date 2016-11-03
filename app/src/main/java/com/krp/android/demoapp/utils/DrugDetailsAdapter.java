package com.krp.android.demoapp.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.krp.android.demoapp.modals.Drug;

import java.util.List;

/**
 * Created by Surati on 11/1/2016.
 */
public class DrugDetailsAdapter extends RecyclerView.Adapter<DrugDetialsViewHolder> {

    private List<Drug> drugs;

    public DrugDetailsAdapter(List<Drug> drugs) {
        this.drugs = drugs;
    }

    @Override
    public DrugDetialsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DrugDetialsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.select_dialog_item, null));
    }

    @Override
    public void onBindViewHolder(DrugDetialsViewHolder holder, int position) {
        holder.setTitle(drugs.get(position).getDrugName());
    }

    @Override
    public int getItemCount() {
        return drugs == null ? 0 : drugs.size();
    }
}
