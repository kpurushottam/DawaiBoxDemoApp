package com.krp.android.demoapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.krp.android.demoapp.DawaiBoxApplication;
import com.krp.android.demoapp.R;

public class LoginActivity extends AppCompatActivity {

    private final int REQ_CODE_REGISTRATION = 101;

    private EditText etUser, etPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = (EditText) findViewById(R.id.et_username);
        etPwd = (EditText) findViewById(R.id.et_pwd);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQ_CODE_REGISTRATION :
                if(resultCode == RESULT_OK) {
                    onLoginVerificationCompleted();
                } else {
                    etUser.setText("");
                    etPwd.setText("");
                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void onLoginVerificationCompleted() {
        startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
        finish();
    }

    /**
     * On "Login" button is clicked
     * @param view
     */
    public void onLogin(View view) {
        String user = etUser.getText().toString();
        String pwd = etPwd.getText().toString();

        String savedUser = DawaiBoxApplication.getInstance().getUser();
        String savedPwd = DawaiBoxApplication.getInstance().getPassword();

        if(TextUtils.isEmpty(savedUser)) {
            etUser.setError(getString(R.string.error_user_not_registered));

        } else if(isValidUser(user, savedUser)) {
            if(isValidPwd(pwd, savedPwd)) {
                onLoginVerificationCompleted();
            } else {
                etPwd.setError(getString(R.string.error_invalid_pwd));
            }
        } else {
            etUser.setError(getString(R.string.error_invalid_user));
        }
    }

    private boolean isValidUser(String user, String savedUser) {
        return user.equalsIgnoreCase(savedUser);
    }

    private boolean isValidPwd(String pwd, String savedPwd) {
        return pwd.equals(savedPwd);
    }


    /**
     * On "Register" button is clicked
     * @param view
     */
    public void onRegister(View view) {
        startActivityForResult(new Intent(LoginActivity.this, RegistrationActivity.class),
                REQ_CODE_REGISTRATION);
    }
}
