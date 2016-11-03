package com.krp.android.demoapp.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.krp.android.demoapp.R;
import com.krp.android.demoapp.modals.Drug;
import com.krp.android.demoapp.utils.DrugDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    String[] language ={"C","C++","Java",".NET","iPhone","Android","ASP.NET","PHP"};

    private ArrayAdapter<String> mDrugsAdapter;
    private AutoCompleteTextView mAutoCompleteTv;

    private List<Drug> mDrugList;
    private DrugDetailsAdapter mDrugDetailsAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initAutoCompleteTextView();
        initRecyclerView();
    }

    private void initAutoCompleteTextView() {
        //Creating the instance of ArrayAdapter containing list of language names
        mDrugsAdapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, language);

        //Getting the instance of AutoCompleteTextView
        mAutoCompleteTv= (AutoCompleteTextView) findViewById(R.id.autoCompleteTv);
        mAutoCompleteTv.setThreshold(1);//will start working from first character
        mAutoCompleteTv.setAdapter(mDrugsAdapter);//setting the adapter data into the AutoCompleteTextView
        mAutoCompleteTv.setTextColor(Color.BLACK);

        mAutoCompleteTv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDrugList.add(new Drug(mDrugsAdapter.getItem(position)));
                mDrugDetailsAdapter.notifyItemInserted(mDrugDetailsAdapter.getItemCount()-1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });
    }

    private void initRecyclerView() {
        mDrugList = new ArrayList<Drug>();
        mDrugDetailsAdapter = new DrugDetailsAdapter(mDrugList);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        mRecyclerView.setAdapter(mDrugDetailsAdapter);
    }
}
