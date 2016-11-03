package com.krp.android.demoapp.modals;

import com.krp.android.demoapp.utils.DrugDetailsAdapter;

/**
 * Created by Surati on 11/1/2016.
 */
public class Drug implements Cloneable {
    private long drugId;            //44332
    private String drugName;        //Nimkul Para 100,500mg
    private String drugType;        //Tablet
    private String pharmaCompName;  //Finecure
    private String compound;        //Nimesulide + Paracetamol
    private String drugInteractions;
    private String drugIndications;
    private String drugContraIndications;

    private int viewType = DrugDetailsAdapter.TYPE_TITLE;


    public Drug() {}

    public Drug(String drugName) {
        this.drugName = drugName;
    }

    public Drug(String drugName, int viewType) {
        this.drugName = drugName;
        this.viewType = viewType;
    }



    public long getDrugId() {
        return drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public String getDrugType() {
        return drugType;
    }

    public String getPharmaCompName() {
        return pharmaCompName;
    }

    public String getCompound() {
        return compound;
    }

    public String getDrugInteractions() {
        return drugInteractions;
    }

    public String getDrugIndications() {
        return drugIndications;
    }

    public String getDrugContraIndications() {
        return drugContraIndications;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return drugName;
    }
}
