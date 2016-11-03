package com.krp.android.demoapp.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.krp.android.demoapp.R;
import com.krp.android.demoapp.modals.Drug;

import java.util.Collections;
import java.util.List;

/**
 * Created by Surati on 11/1/2016.
 */
public class DrugDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_TITLE = 0;
    public static final int TYPE_DETAILS = 1;

    private List<Drug> drugs;

    public DrugDetailsAdapter(List<Drug> drugs) {
        this.drugs = drugs;
    }

    @Override
    public int getItemViewType(int position) {
        switch (drugs.get(position).getViewType()) {
            case 0 : return TYPE_TITLE;
            case 1 : return TYPE_DETAILS;
            default: return -1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TITLE : return new DrugTitleViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.select_dialog_item, null));

            case TYPE_DETAILS : return new DrugDetailsViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_drug, null));

            default: return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        switch (getItemViewType(position)) {
            case TYPE_TITLE :
                DrugTitleViewHolder titleHolder = (DrugTitleViewHolder) viewHolder;
                titleHolder.setTitle(drugs.get(position).getDrugName());
                break;

            case TYPE_DETAILS :
                DrugDetailsViewHolder detailsHolder = (DrugDetailsViewHolder) viewHolder;
                Drug drug = drugs.get(position);
                detailsHolder.setId(drug.getDrugId());
                detailsHolder.setName(drug.getDrugName());
                detailsHolder.setCompanyName(drug.getPharmaCompName());
                detailsHolder.setType(drug.getDrugType());
                detailsHolder.setCompound(drug.getCompound());
                detailsHolder.setInteration(drug.getDrugInteractions());
                detailsHolder.setIndication(drug.getDrugIndications());
                detailsHolder.setContraIndication(drug.getDrugContraIndications());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return drugs == null ? 0 : drugs.size();
    }

    public List<Drug> getDrugs() {
        return Collections.unmodifiableList(drugs);
    }
}
