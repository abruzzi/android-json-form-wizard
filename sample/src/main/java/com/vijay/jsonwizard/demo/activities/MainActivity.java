package com.vijay.jsonwizard.demo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vijay.jsonwizard.demo.R;
import com.vijay.jsonwizard.activities.JsonFormActivity;
import com.vijay.jsonwizard.demo.utils.CommonUtils;

/**
 * Created by vijay on 5/16/15.
 */
public class MainActivity extends ActionBarActivity {

    private static final int    REQUEST_CODE_GET_JSON = 1;
    private static final String TAG                   = "MainActivity";
    private static final String DATA_JSON_PATH        = "data.json";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RequestQueue queue = Volley.newRequestQueue(this);

        findViewById(R.id.button_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="http://172.16.16.253:9527/forms/59c5ff5bc19efea6cc9a3726";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, response);
                                Intent intent = new Intent(MainActivity.this, JsonFormActivity.class);
                                intent.putExtra("json", response);
                                startActivityForResult(intent, REQUEST_CODE_GET_JSON);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.getMessage());
                    }
                });

                queue.add(stringRequest);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_GET_JSON && resultCode == RESULT_OK) {
            Log.d(TAG, data.getStringExtra("json"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
