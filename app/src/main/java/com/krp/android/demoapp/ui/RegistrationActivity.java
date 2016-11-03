package com.krp.android.demoapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.krp.android.demoapp.DawaiBoxApplication;
import com.krp.android.demoapp.R;

public class RegistrationActivity extends AppCompatActivity {

    private EditText tvUser, tvPwd, tvCnfPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        tvUser = (EditText) findViewById(R.id.et_username);
        tvPwd = (EditText) findViewById(R.id.et_pwd);
        tvCnfPwd = (EditText) findViewById(R.id.et_cnf_pwd);
    }



    /**
     * On "Register" button is clicked
     * @param view
     */
    public void onRegister(View view) {
        String user = tvUser.getText().toString();
        String pwd = tvPwd.getText().toString();
        String cnfPwd = tvCnfPwd.getText().toString();

        if(isValidPwd(pwd, cnfPwd)) {
            if(isValidUser(user, pwd)) {
                DawaiBoxApplication.getInstance().setUser(user);
                DawaiBoxApplication.getInstance().setPassword(pwd);

                setResult(RESULT_OK);
                finish();

            } else {
                tvUser.setError(getString(R.string.error_user_not_registered));
            }

        } else {
            tvPwd.setError(getString(R.string.error_invalid_cnf_pwd));
            tvPwd.setText("");
            tvCnfPwd.setText("");
        }
    }

    private boolean isValidUser(String user, String pwd) {
        return !TextUtils.isEmpty(user) && !user.equals(pwd);
    }

    private boolean isValidPwd(String pwd, String cnfPwd) {
        return !TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(cnfPwd) && pwd.equals(cnfPwd);
    }
}
