package com.example.rexita_pc.admin.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rexita_pc.admin.R;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SigninActivity extends AppCompatActivity {

    private static final String TAG = "GoogleActivity";
    private static final String TAG_EmailPassword = "EmailPassword";
    private static final int RC_SIGN_IN = 9001;

    private static final int REQUEST_SIGNUP = 0;
    private FirebaseAuth mAuth;

    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;
    @BindView(R.id.link_signup)
    TextView _signupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();
        ButterKnife.bind(this);
        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInLogin(_emailText.getText().toString(), _passwordText.getText().toString());
            }
        });
    }

    private void signInLogin(String email, String password) {
        if (!validateForm()) {
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG_EmailPassword, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG_EmailPassword, "signInWithEmail:failure", task.getException());
                            Snackbar.make(getCurrentFocus(), "Authentication failed.",Snackbar.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            startActivity(new Intent(SigninActivity.this, MainActivity.class));
            finish();
        }
    }

    private boolean validateForm() {
        boolean valid = true;
        String email = _emailText.getText().toString();
        if (TextUtils.isEmpty(email)||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        String password = _passwordText.getText().toString();
        if (TextUtils.isEmpty(password)){
            _passwordText.setError("please input password");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    // [EN
}
