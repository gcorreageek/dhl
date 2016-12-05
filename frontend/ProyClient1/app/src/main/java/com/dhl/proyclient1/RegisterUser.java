package com.dhl.proyclient1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import util.Constants;
import util.UtilLocal;

public class RegisterUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        Toolbar  toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =  getMenuInflater();
        menuInflater.inflate(R.menu.menu_register,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:

                try {


                EditText register_firstname = (EditText) findViewById(R.id.register_firstname);
                EditText register_lastname = (EditText) findViewById(R.id.register_lastname);
                final EditText register_email = (EditText) findViewById(R.id.register_email);
                EditText register_login = (EditText) findViewById(R.id.register_login);
                EditText register_password1 = (EditText) findViewById(R.id.register_password1);
                EditText register_password2 = (EditText) findViewById(R.id.register_password2);

//                Toast.makeText(RegisterUser.this,obj.toString(),Toast.LENGTH_SHORT).show();


                    final Map<String,String> params = new HashMap<>();

                    params.put("activated","false");
                    params.put("email",register_email.getText().toString());
                    params.put("firstName",register_firstname.getText().toString());
                    params.put("langKey", UtilLocal.getShortNameLanguaje(Locale.getDefault().getDisplayLanguage() ));
                    params.put("lastName",register_lastname.getText().toString());
                    params.put("login",register_login.getText().toString());
                    params.put("password",register_password1.getText().toString());




                Log.i("LoginActivity","======================>"+Locale.getDefault().getDisplayLanguage());
                    RequestQueue queue = Volley.newRequestQueue(this);
                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Constants.URL_API_REGISTER,new JSONObject(params),new Response.Listener<JSONObject>(){



                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("LoginActivity","JSONObject======================");
                        Log.i("LoginActivity","JSONObject======================>"+response.toString());


                        finish();
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.i("LoginActivity","VolleyError======================");
                        Log.i("LoginActivity","VolleyError======================>"+volleyError.getMessage());
                        String mensageError = "";
                        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
                            String data = new String(volleyError.networkResponse.data);
                            Log.e("ERROR:",data);
                            Log.e("ERROR:",volleyError.networkResponse.headers.toString());
                            Log.e("ERROR:",volleyError.networkResponse.headers.get("Content-Type"));
                            Log.e("ERROR:",volleyError.networkResponse.statusCode+"");

                            if(volleyError.networkResponse.headers.get("Content-Type").equals("application/json;charset=UTF-8")){
                                try {
                                    JSONObject obj = new JSONObject(data);
                                    Log.i("ERROR:",obj.toString());
                                    mensageError = "error en la informacion ingresada,ingrese datos validos";
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }else if(volleyError.networkResponse.headers.get("Content-Type").equals("text/plain")){
                                mensageError = data;
                            }else{
                                mensageError = "Error "+data;
                            }
                            new AlertDialog.Builder(RegisterUser.this)
                                    .setTitle("ERROR")
                                    .setMessage(mensageError)
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();


                        }else{
                            new AlertDialog.Builder(RegisterUser.this)
                                    .setTitle("EXITO")
                                    .setMessage("Se envio un email de activacion a "+ params.get("email"))
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();

                        }



                    }
                }   ){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("Content-Type", "application/json");
                        return params;
                    }


                    @Override
                    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                        Log.i("LoginActivity","NetworkResponse======================");
                        Log.i("LoginActivity","NetworkResponse======================>"+response.statusCode);
                        return super.parseNetworkResponse(response);
                    }








                };







                queue.add(req);


                }catch(Exception e){
                    Log.e("LoginActivity","error" );
                    Log.e("LoginActivity","==="+e.getMessage());
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
