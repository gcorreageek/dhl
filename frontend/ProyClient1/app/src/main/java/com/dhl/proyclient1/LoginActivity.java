package com.dhl.proyclient1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private static final String LOGIN_REQUEST_URL = "http://192.168.0.30:8080/api/authenticate";
    private static final String ACCOUNT_GET_URL = "http://192.168.0.30:8080/api/account";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        Log.i("LoginActivity","ENTRAA!!");

        final EditText user1 = (EditText) findViewById(R.id.editText);
        final EditText pass1 = (EditText) findViewById(R.id.editText2);
        final Button botomLogin = (Button) findViewById(R.id.button);

        botomLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user2 = user1.getText().toString();
                final String pass2 = pass1.getText().toString();
                Log.i("LoginActivity","user2!!"+user2);
                Log.i("LoginActivity","pass2!!"+pass2);


                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("login","gcorreageek");
                intent.putExtra("firstName","Gustavo Antonio");
                intent.putExtra("lastName","Correa Caja");
                intent.putExtra("email","gcorreageek@gmail.com");


                LoginActivity.this.startActivity(intent);




            }
        });
    }






}
