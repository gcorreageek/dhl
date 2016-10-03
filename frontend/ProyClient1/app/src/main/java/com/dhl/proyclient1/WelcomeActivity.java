package com.dhl.proyclient1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class WelcomeActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        final EditText editText = (EditText) findViewById(R.id.editText3);

        Intent intent = getIntent();
        String login = intent.getStringExtra("login");
        Log.i("WelcomeActivity","login!!"+login);
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String email = intent.getStringExtra("email");


        editText.setText("Welcome "+login+", "+firstName+" "+lastName+", "+email);





    }
}
