package com.example.barcode;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.symbol.emdk.EMDKManager;
import com.symbol.emdk.EMDKResults;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    RequestQueue requestQueue;
    TextView textView9 = null;
    EditText editText7,editText9 =null;
    Spinner spinner1;
    Button button;

    private String profileNameMSR = "DataCaptureProfileMSR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        textView9 = findViewById(R.id.textView9);
        editText7 = findViewById(R.id.editText7);
        editText9 = findViewById(R.id.editText9);
        spinner1 = findViewById(R.id.spinner1);
        button = findViewById(R.id.button);
        Bundle datos = this.getIntent().getExtras();
        textView9.setText(datos.getString("codigo"));
        editText7.setText(datos.getString("custodio"));
        editText9.setText(datos.getString("descripcion"));


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String ubicacion =spinner1.getSelectedItem().toString();

                 String custodio =editText7.getText().toString();
                 String descripcion =editText9.getText().toString();
                 String id = textView9.getText().toString();
                updateActivo(""+custodio+"",""+ubicacion+"",""+descripcion+"",""+id+"");
            }
        });



        buscarUbicacion();
        Intent i = getIntent();
        handleDecodeData(i);

    }
    @Override
    public void onNewIntent(Intent i) {
        handleDecodeData(i);

    }
    private void handleDecodeData(Intent i)
    {

            String source = i.getStringExtra("com.motorolasolutions.emdk.datawedge.source");


//Check if the data has come from the msr


                String data = i.getStringExtra("com.motorolasolutions.emdk.datawedge.data_string");

//Check that we have received data
                if(data != null && data.length() > 0)
                {
                    Log.d("Hola",("Data = " + data) );
                }


    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void buscarUbicacion() {

        JsonArrayRequest jsonArrayRequest  = new JsonArrayRequest(Request.Method.GET, "http://10.14.1.43:9000/ia/api/ubicacion", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                try {

                    List<String> list;
                    list = new ArrayList<String>();
                    for (int i=0; i<response.length(); i++) {
                        JSONObject jresponse = response.getJSONObject(i);
                        list.add( jresponse.getString("descripcion"));
                        ArrayAdapter<String>  adapter = new ArrayAdapter<String>(Main2.this,android.R.layout.simple_spinner_item,list);
                        spinner1.setAdapter(adapter);



                    }



                    ////////for para recorrer el Json Array
                    //##        for(int i=0;i<response.length();i++){
                    //##        JSONObject jresponse = response.getJSONObject(i);
                    //##         String  nickname = jresponse.getString("idUbicacion");
                    //##       //   String subjson = jresponse.getJSONObject("activo").getString("codigoActivo");
                    //##         Log.d("nickname",nickname);
                    //##     //     Log.d("coidgo",subjson);
                    //##         //  textView1.append(subjson+" ");
                    //##}

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //pDialog.dismiss();


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue= Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);

    }

    public void updateActivo(String custodio, String ubicacion, String descripcion,String id){

        String webAddress = "http://10.14.1.43:9000/ia/api/activo/"+id+"/"+ubicacion+"";
        RequestQueue queue = Volley.newRequestQueue(this);
        Log.d("ok","isnsert");
        JSONObject object = new JSONObject();
        try {
            object.put("custodio",""+custodio+"");
        //    object.put("idUbicacion",ubicacion);
            object.put("descripcion",""+descripcion+"");
            //  object.put("usuario", sendUser);
            Log.d("sen",ubicacion);


        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        Toast toast2 =
                Toast.makeText(getApplicationContext(),
                        "Datos Actualizados", Toast.LENGTH_SHORT);

    //    toast2.setGravity(Gravity.CENTER|Gravity.CENTER_VERTICAL,0,0);

        toast2.show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, webAddress,object, new Response.Listener<JSONObject>() {

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

    @Override
    public void onBackPressed(){


        Log.d("atras","atras");
      // super.onBackPressed();
  // Intent i = new Intent(Main2.this,MainActivity.class);
        Toast toast3 =
                Toast.makeText(getApplicationContext(),
                        "Cargando Scaner..", Toast.LENGTH_SHORT);

        toast3.setGravity(Gravity.CENTER,0,0);

        toast3.show();

 //  startActivity(i);
        Intent i = new Intent(Main2.this,MainActivity.class);
        startActivityForResult(i, 0);


    }







    }
