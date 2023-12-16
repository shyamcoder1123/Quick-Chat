package com.example.quickchat.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quickchat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    TextView submitTextView;
    ProgressDialog progressDialog;
    TextView textViewToRegistration;
    EditText emailEditTextView;
    EditText passwordEditTextView;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        submitTextView=findViewById(R.id.submitButtonTextView);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        progressDialog=new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Please wait.\nValidation in progress. ");
        emailEditTextView=findViewById(R.id.emailEditTextViewLogin);
        passwordEditTextView = findViewById(R.id.passwordEditTextViewLogin);
        if(mAuth.getCurrentUser()!=null && !mAuth.getCurrentUser().getEmail().isEmpty()){
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(mAuth.getCurrentUser().getEmail().substring(0,5)) // Replace with the desired display name
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Display name updated successfully
                                Log.d("DisplayName", "User profile updated.");
                            } else {
                                // Display name update failed
                                Log.w("DisplayName", "User profile update failed.");
                            }
                        }
                    });

            Toast.makeText(this, "Welcome "+mAuth.getCurrentUser().getDisplayName(), Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onCreate: "+mAuth.getCurrentUser().getDisplayName());
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        submitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUserAccount();
            }
        });
        textViewToRegistration=findViewById(R.id.textViewToRegistration);
        textViewToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(i);
            }
        });
    }

    private void loginUserAccount()
    {

        // show the visibility of progress bar to show loading
        progressDialog.show();

        // Take the value of two edit texts in Strings
        String email, password;
        email = emailEditTextView.getText().toString();
        password = passwordEditTextView.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {

                                    Toast.makeText(getApplicationContext(),
                                                    "Login successful!!",
                                                    Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar

                                    // if sign-in is successful
                                    // intent to home activity
                                    Intent intent
                                            = new Intent(LoginActivity.this,
                                            MainActivity.class);
                                    startActivity(intent);
                                }

                                else {

                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(),
                                                    "Login failed!!\n"+task.getException()
                                                            .getMessage().toString(),
                                                    Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar
                                }
                            }
                        });

        // signin existing user

    }
}