package com.krp.android.demoapp.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.krp.android.demoapp.BuildConfig;
import com.krp.android.demoapp.R;
import com.krp.android.demoapp.modals.Drug;
import com.krp.android.demoapp.modals.response.GetDrugsApiResponse;
import com.krp.android.demoapp.utils.DawaiBoxApis;
import com.krp.android.demoapp.utils.DrugDetailsAdapter;
import com.krp.android.demoapp.utils.DrugListAdapter;
import com.krp.android.demoapp.utils.EndlessRecyclerViewScrollListener;
import com.krp.android.demoapp.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity implements TextWatcher {

    private final String TAG = SearchActivity.class.getSimpleName();

    private final long DAWAI_BOX_SEARCH_DRUGS_ID = 14120;
    private final String DAWAI_BOX_SEARCH_DRUGS_ROLE = "Doctor";
    private final int DAWAI_BOX_SEARCH_LIMIT = 10;

    private DawaiBoxApis dawaiBoxApis;

    private ImageView ivPrevResult;
    private AutoCompleteTextView mAutoCompleteTv;

    private List<Drug> mDrugNameList;
    private List<Drug> mDrugDetailList;
    private DrugListAdapter mDrugListAdapter;
    private DrugDetailsAdapter mDrugDetailsAdapter;
    private RecyclerView mRecyclerView, mSuggestionsRecyclerView;
    private EndlessRecyclerViewScrollListener mEndlessRecyclerViewScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initRetrofit();

        initAutoCompleteTextView();
        initSuggestionsRecyclerView();
        initRecyclerView();
    }

    private void initAutoCompleteTextView() {
        mAutoCompleteTv= (AutoCompleteTextView) findViewById(R.id.autoCompleteTv);
        mAutoCompleteTv.setTextColor(Color.BLACK);

        mAutoCompleteTv.addTextChangedListener(SearchActivity.this);

        ivPrevResult = (ImageView) findViewById(R.id.iv_prev_result);
        ivPrevResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSuggestionsRecyclerView.setVisibility(View.VISIBLE);
                ivPrevResult.setVisibility(View.GONE);
            }
        });
    }

    private void initSuggestionsRecyclerView() {
        mDrugNameList = new ArrayList<Drug>();
        mDrugListAdapter = new DrugListAdapter(mDrugNameList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);

        mSuggestionsRecyclerView = (RecyclerView) findViewById(R.id.recycler_suggestions);
        mSuggestionsRecyclerView.setLayoutManager(layoutManager);
        mSuggestionsRecyclerView.setAdapter(mDrugListAdapter);

        mSuggestionsRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(SearchActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        mSuggestionsRecyclerView.setVisibility(View.GONE);
                        ivPrevResult.setVisibility(View.VISIBLE);

                        mDrugDetailList.add(mDrugNameList.get(position));
                        mDrugDetailsAdapter.notifyItemInserted(mDrugDetailsAdapter.getItemCount()-1);
                    }
                })
        );

        mEndlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // get more drugs
                Call<GetDrugsApiResponse> getDrugsCall = dawaiBoxApis.getDrugs(DAWAI_BOX_SEARCH_DRUGS_ID,
                        mAutoCompleteTv.getText().toString(), page * DAWAI_BOX_SEARCH_LIMIT,
                        DAWAI_BOX_SEARCH_LIMIT, DAWAI_BOX_SEARCH_DRUGS_ROLE);
                getDrugsCall.enqueue(mGetMoreDrugsCallback);
            }
        };

        mSuggestionsRecyclerView.addOnScrollListener(mEndlessRecyclerViewScrollListener);
    }

    private void initRecyclerView() {
        mDrugDetailList = new ArrayList<Drug>();
        mDrugDetailsAdapter = new DrugDetailsAdapter(mDrugDetailList);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        mRecyclerView.setAdapter(mDrugDetailsAdapter);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(SearchActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        onRecyclerViewItemTouch(position);
                    }
                })
        );
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.DAWAI_BOX_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // get api services
        dawaiBoxApis = retrofit.create(DawaiBoxApis.class);
    }

    private void onRecyclerViewItemTouch(int position) {
        Drug currentDrugItem = null, nextDrugItem = null;
        List<Drug> drugs = mDrugDetailsAdapter.getDrugs();

        try {
            currentDrugItem = (Drug) mDrugDetailList.get(position).clone();
        } catch (CloneNotSupportedException e) {
            return; //cloneable not supported
        }

        if(currentDrugItem.getViewType() == DrugDetailsAdapter.TYPE_DETAILS) {
            mDrugDetailList.remove(position);
            mDrugDetailsAdapter.notifyItemRemoved(position);
            return; // bread execution
        }

        if (mDrugDetailsAdapter.getItemCount() > position+1) {
            nextDrugItem = drugs.get(position + 1);
        }

        if (nextDrugItem != null && nextDrugItem.getDrugId() == currentDrugItem.getDrugId()) {
            mDrugDetailList.remove(position + 1);
            mDrugDetailsAdapter.notifyItemRemoved(position + 1);
        }else {
            currentDrugItem.setViewType(DrugDetailsAdapter.TYPE_DETAILS);
            mDrugDetailList.add(position + 1, currentDrugItem);
            mDrugDetailsAdapter.notifyItemInserted(position + 1);
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if(!TextUtils.isEmpty(s)) {
            mEndlessRecyclerViewScrollListener.resetState();
            // get drugs
            Call<GetDrugsApiResponse> getDrugsCall = dawaiBoxApis.getDrugs(DAWAI_BOX_SEARCH_DRUGS_ID,
                    s.toString(), 0, DAWAI_BOX_SEARCH_LIMIT, DAWAI_BOX_SEARCH_DRUGS_ROLE);
            getDrugsCall.enqueue(mGetDrugsCallback);
        } else {
            mSuggestionsRecyclerView.setVisibility(View.GONE);
        }
    }

    /**
     * API callback response
     */
    private Callback<GetDrugsApiResponse> mGetDrugsCallback = new Callback<GetDrugsApiResponse>() {

        @Override
        public void onResponse(Call<GetDrugsApiResponse> call, Response<GetDrugsApiResponse> response) {
            int statusCode = response.code();
            GetDrugsApiResponse getDrugsApiResponse = response.body();

            List<Drug> mDrugListResponse = getDrugsApiResponse.getDrugList();
            if(mDrugListResponse != null) {
                mSuggestionsRecyclerView.setVisibility(View.VISIBLE);
                ivPrevResult.setVisibility(View.GONE);

                mDrugNameList.clear();
                mDrugNameList.addAll(mDrugListResponse);
                mDrugListAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onFailure(Call<GetDrugsApiResponse> call, Throwable t) {
            Log.e(TAG, "Unable to fetch drugList");
        }
    };

    /**
     * API callback response
     */
    private Callback<GetDrugsApiResponse> mGetMoreDrugsCallback = new Callback<GetDrugsApiResponse>() {

        @Override
        public void onResponse(Call<GetDrugsApiResponse> call, Response<GetDrugsApiResponse> response) {
            int statusCode = response.code();
            GetDrugsApiResponse getDrugsApiResponse = response.body();

            List<Drug> mDrugListResponse = getDrugsApiResponse.getDrugList();
            if(mDrugListResponse != null) {
                mDrugNameList.addAll(mDrugListResponse);
                mDrugListAdapter.notifyItemRangeInserted(mDrugListAdapter.getItemCount(), mDrugNameList.size());
            }
        }

        @Override
        public void onFailure(Call<GetDrugsApiResponse> call, Throwable t) {
            Log.e(TAG, "Unable to fetch drugList");
        }
    };
}
