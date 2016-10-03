package com.dhl.proyclient1;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
        /*botomLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user2 = user1.getText().toString();
                final String pass2 = pass1.getText().toString();
                Log.i("LoginActivity","user2!!"+user2);
                Log.i("LoginActivity","pass2!!"+pass2);

                Map<String,String> params = new HashMap<>();
                params.put("username",user2);
                params.put("password",pass2);
                params.put("rememberMe","true");

                StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_REQUEST_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i("LoginActivity","response!!"+response);
//                                mTextView.setText("Response is: "+ response.substring(0,500));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("LoginActivity","error!!"+error);
                        Log.i("LoginActivity","error!!"+error);
//                        mTextView.setText("That didn't work!");
                    }
                });
                Volley.newRequestQueue(LoginActivity.this).add(stringRequest);

            }
        });*/
        botomLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user2 = user1.getText().toString();
                final String pass2 = pass1.getText().toString();
                Log.i("LoginActivity","user2!!"+user2);
                Log.i("LoginActivity","pass2!!"+pass2);

                Map<String,String> params;

                params = new HashMap<>();
                params.put("username",user2);
                params.put("password",pass2);
                params.put("rememberMe","true");


                JsonObjectRequest req = new JsonObjectRequest(LOGIN_REQUEST_URL, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("LoginActivity","response!!"+response);
                            try {
                                Log.i("LoginActivity","response!!"+response.get("id_token").toString());
                                requestWithSomeHttpHeaders(response.get("id_token").toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("User o Pass incorrectos").setNegativeButton("Retry",null).create().show();

                        VolleyLog.e("Error1: ", error.getMessage());
                    }
                });
                Volley.newRequestQueue(LoginActivity.this).add(req);




            }
        });
    }







    public void requestWithSomeHttpHeaders(final String token) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.GET, ACCOUNT_GET_URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String login = jsonObject.getString("login");
                            Log.i("LoginActivity","login!!"+login);
                            String firstName = jsonObject.getString("firstName");
                            Log.i("LoginActivity","firstName!!"+firstName);
                            String lastName = jsonObject.getString("lastName");
                            Log.i("LoginActivity","lastName!!"+lastName);
                            String email = jsonObject.getString("email");
                            Log.i("LoginActivity","email!!"+email);


                            Intent intent = new Intent(LoginActivity.this,WelcomeActivity.class);
                            intent.putExtra("login",login);
                            intent.putExtra("firstName",firstName);
                            intent.putExtra("lastName",lastName);
                            intent.putExtra("email",email);


                            LoginActivity.this.startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }







                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR","error => "+error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders()  {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Accept", "application/json, text/plain, */*");
                params.put("Authorization", "Bearer " + token);
                return params;
            }
        };
        queue.add(postRequest);

    }
}
