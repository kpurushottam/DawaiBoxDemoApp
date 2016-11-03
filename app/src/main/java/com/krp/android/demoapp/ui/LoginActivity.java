package com.krp.android.demoapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.krp.android.demoapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    /**
     * On "Login" button is clicked
     * @param view
     */
    public void onLogin(View view) {
        startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
        finish();
    }
}
