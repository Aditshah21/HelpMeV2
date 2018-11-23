package com.rushabhvakharwala.helpmev2.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rushabhvakharwala.helpmev2.API.RetrofitClient;
import com.rushabhvakharwala.helpmev2.Activities.PostLogin.NearbyPlacesActivity;
import com.rushabhvakharwala.helpmev2.Models.LoginModels.UserToken;
import com.rushabhvakharwala.helpmev2.R;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.buttonRegister).setOnClickListener(this);
    }

    private void userLogin(){
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

        String grant_type = "password";
        Call<UserToken> call = RetrofitClient
                .getInstance()
                .getApi()
                .signInUser(grant_type,email,password);

        call.enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(Call<UserToken> call, Response<UserToken> response) {
                UserToken userToken = response.body();

                if(response.code()==200){

                    Toast.makeText(MainActivity.this, userToken.getUser().getAccess_token(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, NearbyPlacesActivity.class);
                    intent.putExtra("access_token", userToken.getUser().getAccess_token());
                    startActivity(intent);
                    finish();


                }else{
                    Toast.makeText(MainActivity.this,"Invalid credentials",Toast.LENGTH_LONG).show();

                }

            }



            @Override
            public void onFailure(Call<UserToken> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage() , Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogin:
                userLogin();
                break;
            case R.id.buttonRegister:
                Intent intent = new Intent(MainActivity.this , SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }


}
