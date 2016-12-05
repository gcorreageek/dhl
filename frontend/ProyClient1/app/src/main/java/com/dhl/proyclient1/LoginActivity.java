package com.dhl.proyclient1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DatabaseUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import dao.OperacionesBaseDatos;
import model.User;
import util.Constants;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);





        getApplicationContext().deleteDatabase("dhl.db");
        datos = OperacionesBaseDatos.obtenerInstancia(getApplicationContext());
        new TareaPruebaDatos().execute();




        if(canEnter()){
            Log.i("LoginActivity","======================>con token");
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            LoginActivity.this.startActivity(intent);
        }
        Log.i("LoginActivity","======================>sin token");

        final EditText user1 = (EditText) findViewById(R.id.login_textuser);
        final EditText pass1 = (EditText) findViewById(R.id.login_textpassword);
        final Button botomLogin = (Button) findViewById(R.id.login_buttonlogin);
        final TextView textViewRegisterUser = (TextView) findViewById(R.id.login_registeruser);

        textViewRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = LoginActivity.this;
                Intent intent = new Intent(context, RegisterUser.class);
                context.startActivity(intent);
            }
        });


        botomLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getToken(user1.getText().toString(),pass1.getText().toString());
            }
        });
    }


    public void getToken(String user, String pass) {


        Map<String, String> params = new HashMap<>();
        params.put("username", user);
        params.put("password", pass);
        params.put("rememberMe", "true");
        JsonObjectRequest req = new JsonObjectRequest(Constants.URL_API_LOGIN, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.i("LoginActivity", "TOKEN=>" + response.get("id_token").toString());
                            Context context = LoginActivity.this;
                            SharedPreferences sharedPref = context.getSharedPreferences(Constants.TOKEN, Context.MODE_PRIVATE);
                            if (sharedPref.getAll().get(Constants.TOKEN) == null) {
                                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putString(Constants.TOKEN, response.get("id_token").toString());
                                editor.commit();


                            }
                            getUserAndInsertUser();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage("User o Pass incorrectos").setNegativeButton("Retry", null).create().show();
                VolleyLog.e("Error1: ", error.getMessage());
            }
        });
        Volley.newRequestQueue(LoginActivity.this).add(req);



    }
    public void getUserAndInsertUser() {
        Log.d("getUserAndInsertUser", "getUserAndInsertUser");
        Context context = LoginActivity.this;
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        final String token = settings.getString(Constants.TOKEN, ""/*default value*/);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.GET, Constants.URL_API_ACCOUNT_GET,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String login = jsonObject.getString("login");
                            String email = jsonObject.getString("email");
                            User user = new User();
                            user.setLogin(login);
                            user.setEmail(email);


                            User userddd= null;
                            datos.getDb().beginTransaction();
                            try{
                                userddd = datos.getUser();
                            } finally {
                                datos.getDb().endTransaction();
                            }
                            if(userddd==null){
                                Log.d("IMAGE WEB", "IMAGE => " + "datos.getUser()");
                                JSONObject jsonObjectUserPlus = jsonObject.getJSONObject("userPlus");
                                user.setWeigh(jsonObjectUserPlus.getString("weigh"));
                                user.setHeight(jsonObjectUserPlus.getString("height"));
//                                if(jsonObjectUserPlus.getString("birthday")!=null){
//                                    //TODO: FORMAT DATE
//                                }
//                                user.setBirthday(null);
//                                user.setSex(jsonObjectUserPlus.getString("sex"));
//                                user.setCountry(jsonObjectUserPlus.getString("country"));
//                                user.setLangKey(jsonObjectUserPlus.getString("languaje"));
//                                user.setDisabledProfile(jsonObjectUserPlus.getString("disabledProfile"));
//                                user.setShowWeigh(jsonObjectUserPlus.getString("showWeigh"));
//                                user.setShowHeight(jsonObjectUserPlus.getString("showHeight"));
//                                user.setShowBirthday(jsonObjectUserPlus.getString("showBirthday"));
//                                user.setShowSex(jsonObjectUserPlus.getString("showSex"));
//                                user.setShowCountry(jsonObjectUserPlus.getString("showCountry"));
//                                user.setShowLanguaje(jsonObjectUserPlus.getString("showLanguaje"));
//                                user.setNotificationNews(jsonObjectUserPlus.getString("notificationNews"));
//                                user.setOptions(jsonObjectUserPlus.getString("options"));
//
//
//                                JSONArray jsonObjectUserHash = jsonObject.getJSONArray("userHash");
//                                user.setHashLike(jsonObjectUserHash.get(0).toString());
//                                user.setHashLike(jsonObjectUserHash.get(1).toString());


                                JSONObject jsonObjectUserImagen =   jsonObject.getJSONObject("userImagen");

                                user.setImage(jsonObjectUserImagen.getString("userImagenPathImage"));
                                Log.d("IMAGE WEB", "IMAGE => " + user.getImage().toString());
//                                datos.getDb().beginTransaction();
//                                try{
                                    datos.insertUser(user);
//                                } finally {
//                                    datos.getDb().endTransaction();
//                                }

                            }















                            if(canEnter()){
                                Log.i("LoginActivity","======================>con token");
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                LoginActivity.this.startActivity(intent);
                                finish();
                            }
                            Log.i("LoginActivity","======================>sin token");






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR", "error => " + error.toString());
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("ERROR").setNegativeButton("ERROR obteniendo datos del usuario",null).create().show();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Accept", "application/json, text/plain, */*");
                params.put("Authorization", "Bearer " + token);
                return params;
            }
        };
        queue.add(postRequest);

    }


//    public void getUserPlusAndUpdateUser() {
//        Log.i("getUserPlus", "getUserPlusAndUpdateUser");
//        Context context = LoginActivity.this;
//        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
//        final String token = settings.getString(Constants.TOKEN, ""/*default value*/);
//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest postRequest = new StringRequest(Request.Method.GET, Constants.URL_API_USERPLUS_GET,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("Response", response);
//                        try {
//                            JSONArray jsonArray = new JSONArray(response);
//                            JSONObject jsonObject = jsonArray.getJSONObject(0);
//                            User user = new User();
//                            user.setWeigh(jsonObject.getString("weigh"));
//                            user.setHeight(jsonObject.getString("height"));
//                            if(jsonObject.getString("birthday")!=null){
//                                //TODO: FORMAT DATE
//                            }
//                            user.setBirthday(null);
//                            user.setSex(jsonObject.getString("sex"));
//                            user.setCountry(jsonObject.getString("country"));
//                            user.setLangKey(jsonObject.getString("languaje"));
//                            user.setDisabledProfile(jsonObject.getString("disabledProfile"));
//                            user.setShowWeigh(jsonObject.getString("showWeigh"));
//                            user.setShowHeight(jsonObject.getString("showHeight"));
//                            user.setShowBirthday(jsonObject.getString("showBirthday"));
//                            user.setShowSex(jsonObject.getString("showSex"));
//                            user.setShowCountry(jsonObject.getString("showCountry"));
//                            user.setShowLanguaje(jsonObject.getString("showLanguaje"));
//                            user.setNotificationNews(jsonObject.getString("notificationNews"));
//                            user.setOptions(jsonObject.getString("options"));
//
//
//
//
//                            datos.updateUser(user);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("ERROR", "error => " + error.toString());
//                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                        builder.setMessage("ERROR").setNegativeButton("ERROR obteniendo datos del usuario",null).create().show();
//                    }
//                }
//        ) {
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("Accept", "application/json, text/plain, */*");
//                params.put("Authorization", "Bearer " + token);
//                return params;
//            }
//        };
//        queue.add(postRequest);
//    }
//    public void getUserImagenAndUpdateUser() {
//        Log.i("getUserImage", "getUserImagenAndUpdateUser");
//    }
//    public void getUserHashAndUpdateUser() {
//
//    }





    public boolean canEnter(){
        Context context = LoginActivity.this;
        SharedPreferences settings = PreferenceManager .getDefaultSharedPreferences(context);
        final String token = settings.getString(Constants.TOKEN, ""/*default value*/);
        Log.d("User","token:"+token);
//        Boolean result = token!=null && !token.equals("") && datos.getUser()!=null && datos.getUser().getImage()!=null;
        Boolean result = token!=null && !token.equals("");
        Log.d("User","token result:"+result.toString());
        User ddserch = null;
        datos.getDb().beginTransaction();
        try{
            ddserch = datos.getUser();
        } finally {
            datos.getDb().endTransaction();
        }
//        Log.d("User","getUser result:"+ddserch.toString());
        if(ddserch!=null){
            Log.d("User","imagen result:"+"SE INGRESO! :)");
            Log.d("User","imagen result:"+ddserch.getImage());
        }else{
            Log.d("User","imagen result:"+"NO SE INGRESO! :(");
        }
        return result;
    }

























    OperacionesBaseDatos datos;

    public class TareaPruebaDatos extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            // [INSERCIONES]
            String fechaActual = Calendar.getInstance().getTime().toString();

            try {

                datos.getDb().beginTransaction();

//                // Inserción Clientes
//                String cliente1 = datos.insertarCliente(new Cliente(null, "Veronica", "Del Topo", "4552000"));
//                String cliente2 = datos.insertarCliente(new Cliente(null, "Carlos", "Villagran", "4440000"));
//
//                // Inserción Formas de pago
//                String formaPago1 = datos.insertarFormaPago(new FormaPago(null, "Efectivo"));
//                String formaPago2 = datos.insertarFormaPago(new FormaPago(null, "Crédito"));
//
//                // Inserción Productos
//                String producto1 = datos.insertarProducto(new Producto(null, "Manzana unidad", 2, 100));
//                String producto2 = datos.insertarProducto(new Producto(null, "Pera unidad", 3, 230));
//                String producto3 = datos.insertarProducto(new Producto(null, "Guayaba unidad", 5, 55));
//                String producto4 = datos.insertarProducto(new Producto(null, "Maní unidad", 3.6f, 60));
//
//                // Inserción Pedidos
//                String pedido1 = datos.insertarCabeceraPedido(
//                        new CabeceraPedido(null, fechaActual, cliente1, formaPago1));
//                String pedido2 = datos.insertarCabeceraPedido(
//                        new CabeceraPedido(null, fechaActual,cliente2, formaPago2));
//
//                // Inserción Detalles
//                datos.insertarDetallePedido(new DetallePedido(pedido1, 1, producto1, 5, 2));
//                datos.insertarDetallePedido(new DetallePedido(pedido1, 2, producto2, 10, 3));
//                datos.insertarDetallePedido(new DetallePedido(pedido2, 1, producto3, 30, 5));
//                datos.insertarDetallePedido(new DetallePedido(pedido2, 2, producto4, 20, 3.6f));
//
//                // Eliminación Pedido
//                datos.eliminarCabeceraPedido(pedido1);
//
//                // Actualización Cliente
//                datos.actualizarCliente(new Cliente(cliente2, "Carlos Alberto", "Villagran", "3333333"));

                datos.getDb().setTransactionSuccessful();
            } finally {
                datos.getDb().endTransaction();
            }

            // [QUERIES]
            Log.d("User","Users");
            DatabaseUtils.dumpCursor(datos.obtenerUser());
//            Log.d("Formas de pago", "Formas de pago");
//            DatabaseUtils.dumpCursor(datos.obtenerFormasPago());
//            Log.d("Productos", "Productos");
//            DatabaseUtils.dumpCursor(datos.obtenerProductos());
//            Log.d("Cabeceras de pedido", "Cabeceras de pedido");
//            DatabaseUtils.dumpCursor(datos.obtenerCabecerasPedidos());
//            Log.d("Detalles de pedido", "Detalles de pedido");
//            DatabaseUtils.dumpCursor(datos.obtenerDetallesPedido());

            return null;
        }
    }
}
