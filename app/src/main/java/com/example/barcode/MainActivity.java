package com.example.barcode;




import android.app.Activity;

import android.os.Bundle;
import android.util.Log;


import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.symbol.emdk.*;
import com.symbol.emdk.EMDKManager.EMDKListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity implements EMDKListener{
    Button button4;
    String sendCodigoActivo,sendCustodio,sendDescripcion;
    //Declare a variable to store the textViewBarcode
    // private TextView textViewBarcode = null;
    //Assign the profile name used in EMDKConfig.xml
    private String profileName = "DataCaptureProfile";

    //Declare a variable to store ProfileManager object
    private ProfileManager mProfileManager = null;
    //Assign the profile name used in EMDKConfig.xml  for MSR handling


    //Declare a variable to store EMDKManager object
    private EMDKManager emdkManager = null;
    RequestQueue requestQueue;
    TextView textView1,textView2,textView3,textView4,textView5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 =findViewById(R.id.textView5);
        button4 = findViewById(R.id.button4);


        button4.setOnClickListener(buttonMSROnClick);


        //The EMDKManager object will be created and returned in the callback.
        EMDKResults results = EMDKManager.getEMDKManager(getApplicationContext(), MainActivity.this);

//Check the return status of getEMDKManager
        if(results.statusCode == EMDKResults.STATUS_CODE.FAILURE)
        {
            //Failed to create EMDKManager object
            Log.v("Error","EMDKManager object request failed!");
            return;

        }

//Get the textViewBarcode
        // textViewBarcode = (TextView) findViewById(R.id.textViewBarcode);
        Intent i = this.getIntent();
        handleDecodeData(i);
    }
    @Override
    public void onNewIntent(Intent i) {
        handleDecodeData(i);

    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //Clean up the objects created by EMDK manager
        emdkManager.release();
    }
    @Override
    public void onClosed() {
        // TODO Auto-generated method stub
        emdkManager.release();
    }
    //accionar el EMDK para recibir datos
    @Override
    public void onOpened(EMDKManager emdkManager) {

        this.emdkManager = emdkManager;
//Get the ProfileManager object to process the profiles
        mProfileManager = (ProfileManager) emdkManager.getInstance(EMDKManager.FEATURE_TYPE.PROFILE);

        if(mProfileManager != null)
        {


            String[] modifyData = new String[1];
            //Call processPrfoile with profile name and SET flag to create the profile. The modifyData can be null.

            EMDKResults results = mProfileManager.processProfile(profileName, ProfileManager.PROFILE_FLAG.SET, modifyData);
            if(results.statusCode == EMDKResults.STATUS_CODE.FAILURE)
            {
                //Failed to set profile
                Log.d("no cargo","perfil");
            }



        }

    }





    //anlizar si la intencion fue de Scanner
    private void handleDecodeData(Intent i)
    {
        String data = i.getStringExtra("com.motorolasolutions.emdk.datawedge.data_string");

//Check that we have received data
        if(data != null && data.length() > 0)
        {
            // textViewBarcode.setText("Datos Scaneados = " + data);
            Log.v("data",data);
            buscarId("http://10.14.1.43:9000/ia/api/activo/"+data+"");


        }


    }

    private void buscarId(String URL) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            textView1.setText(response.getString("codigoActivo"));
                            sendCodigoActivo= response.getString("codigoActivo");
                            textView2.setText(response.getString("custodio"));
                            sendCustodio=response.getString("custodio");
                            textView3.setText(response.getJSONObject("empresaActivo").getString("descripcion"));
                            textView4.setText(response.getString("descripcion"));
                            sendDescripcion =response.getString("descripcion");
                            textView5.setText(response.getJSONObject("ubicacionActivo").getString("descripcion"));

                            buscarLote();


                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), error.getMessage()+" Error en lectura", Toast.LENGTH_SHORT).show();
                    }


                });  requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);



    }

    private View.OnClickListener buttonMSROnClick = new View.OnClickListener() {
        public void onClick(View v) {
            Intent myIntent = new Intent(MainActivity.this, Main2.class);
            myIntent.putExtra("codigo", sendCodigoActivo);
            myIntent.putExtra("custodio", sendCustodio);
            myIntent.putExtra("descripcion", sendDescripcion);
            startActivity(myIntent);
            finish();

        }


    };


    private void buscarLote() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, "http://10.14.1.43:9000/ia/api/lote/max", null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String sendLote;
                            sendLote= response.getString("idLote");
                            sendScan(sendLote);

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), error.getMessage()+" Error en lectura", Toast.LENGTH_SHORT).show();
                    }


                });  requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);






    }



    public void sendScan(String s){

        String webAddress = "http://10.14.1.43:9000/ia/api/scan";
        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject object = new JSONObject();
        try {
            object.put("codigoActivo",""+sendCodigoActivo+"");
            object.put("idLote",""+s+"");


        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("error", e.getMessage());
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, webAddress,object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject object) {
                Log.d("RESPONSE", object.toString());
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("RESPONSE", volleyError.toString());
            }

        });
        queue.add(request);

    }



}
