package com.rushabhvakharwala.helpmev2.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rushabhvakharwala.helpmev2.API.RetrofitClient;
import com.rushabhvakharwala.helpmev2.Models.SignUpModels.SignUpRequest;
import com.rushabhvakharwala.helpmev2.Models.SignUpModels.SignUpResponse;
import com.rushabhvakharwala.helpmev2.Models.SignUpModels.SignUpUser;
import com.rushabhvakharwala.helpmev2.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    TextView editTextEmail;
    TextView editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.buttonSignUp).setOnClickListener(this);

    }

    private void UserSignUp(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //email validation
        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Email is invalid");
            editTextEmail.requestFocus();
            return;
        }

        //password validation
        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextPassword.setError("Password must be of 6 or more letters");
            editTextPassword.requestFocus();
            return;
        }

        SignUpUser signUpUser = new SignUpUser(email,password);
        final SignUpRequest signUpRequest = new SignUpRequest(signUpUser);

        Call<SignUpResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .signUpUser(signUpRequest);

        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {

                SignUpResponse signUpResponse = response.body();
                if(response.code()==201){
                    Toast.makeText(SignUpActivity.this, "You can now sign in using "+signUpResponse.getEmail(), Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(SignUpActivity.this, "Email already Exists", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

            }
        });
    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.buttonLogin:
                finish();
                break;
            case R.id.buttonSignUp:
                UserSignUp();
                break;
        }
    }
}
