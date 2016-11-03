package com.krp.android.demoapp.modals.response;

import com.krp.android.demoapp.modals.Drug;

import java.util.List;

/**
 * Created by Surati on 11/3/2016.
 */
public class GetDrugsApiResponse {
    private ApiResponse response;
    private long ID;
    private List<Drug> drugList;
    private int totalCount;

    public ApiResponse getResponse() {
        return response;
    }

    public long getID() {
        return ID;
    }

    public List<Drug> getDrugList() {
        return drugList;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
