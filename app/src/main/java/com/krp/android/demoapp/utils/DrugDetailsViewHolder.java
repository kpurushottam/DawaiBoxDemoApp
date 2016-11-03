package com.krp.android.demoapp.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.krp.android.demoapp.R;

/**
 * Created by Surati on 11/1/2016.
 */
public class DrugDetailsViewHolder extends RecyclerView.ViewHolder {

    private TextView tvId, tvName, tvType, tvCompny,
            tvCompound, tvInteration, tvIndication, tvContraIndication;

    public DrugDetailsViewHolder(View itemView) {
        super(itemView);

        tvId = (TextView) itemView.findViewById(R.id.tv_id);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        tvCompny = (TextView) itemView.findViewById(R.id.tv_compny_name);
        tvType = (TextView) itemView.findViewById(R.id.tv_type);
        tvCompound = (TextView) itemView.findViewById(R.id.tv_compound);
        tvInteration = (TextView) itemView.findViewById(R.id.tv_interaction);
        tvIndication = (TextView) itemView.findViewById(R.id.tv_indication);
        tvContraIndication = (TextView) itemView.findViewById(R.id.tv_contra_indication);
    }

    public void setId(long id) {
        tvId.setText(new StringBuilder().append("Id : ").append(id).toString());
    }

    public void setName(String name) {
        tvName.setText(new StringBuilder().append("Name : ").append(name).toString());
    }

    public void setType(String type) {
        tvType.setText(new StringBuilder().append("Type : ").append(type).toString());
    }

    public void setCompanyName(String companyName) {
        tvCompny.setText(new StringBuilder().append("Para Company Name : ").append(companyName).toString());
    }

    public void setCompound(String compound) {
        tvCompound.setText(new StringBuilder().append("Compound : ").append(compound).toString());
    }

    public void setInteration(String interation) {
        tvInteration.setText(new StringBuilder().append("Interactions : ").append(interation).toString());
    }

    public void setIndication(String indication) {
        tvIndication.setText(new StringBuilder().append("Indications : ").append(indication).toString());
    }

    public void setContraIndication(String contraIndication) {
        tvContraIndication.setText(new StringBuilder().append("Contra Indications : ").append(contraIndication).toString());
    }
}
