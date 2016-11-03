package com.krp.android.demoapp.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.krp.android.demoapp.DawaiBoxApplication;
import com.krp.android.demoapp.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ((TextView) findViewById(R.id.tv_username))
                .setText(DawaiBoxApplication.getInstance().getUser());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // exit welcome page after 3secs & redirects to search page
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, SearchActivity.class));
                finish();
            }
        }, 3000);
    }
}
