package com.schibsted.android.chatbot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * A login screen that offers login via Name & Surname.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private EditText mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mName = (EditText) findViewById(R.id.name);
        Button mNameSignInButton = (Button) findViewById(R.id.sign_in_button);
        mNameSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }


    /**
     * Attempts to sign in with Name & Surname.
     * If there are form errors (invalid name, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mName.setError(null);

        // Store values at the time of the login attempt.
        String name = mName.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(name)) {
            mName.setError(getString(R.string.error_field_required));
            focusView = mName;
            cancel = true;
        } else if (!isNameValid(name)) {
            mName.setError(getString(R.string.error_invalid_name));
            focusView = mName;
            cancel = true;
        }

        if (cancel) {
            // There was an error: don't attempt login and focus the first form field with an error.
            focusView.requestFocus();
        } else {
            // Start the new activity
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
        }
    }

    private boolean isNameValid(String name) {
        //TODO: Replace this with your own logic
        return name.contains(" ");
    }
}

