package com.krp.android.demoapp.modals;

/**
 * Created by Surati on 11/1/2016.
 */
public class Drug {
    private long drugId;            //44332
    private String drugName;        //Nimkul Para 100,500mg
    private String drugType;        //Tablet
    private String pharmaCompName;  //Finecure
    private String compound;        //Nimesulide + Paracetamol
    private String drugInteractions;
    private String drugIndications;
    private String drugContraIndications;


    public Drug() {}

    public Drug(String drugName) {
        this.drugName = drugName;
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


    @Override
    public String toString() {
        return drugName;
    }
}
