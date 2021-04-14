package com.example.currenttiem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button btn_add, btn_select, btn_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        tv.setText("bbbb");
        System.out.println("이거는 나오나?");


        btn_test = (Button) findViewById(R.id.btn_test);
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String date = jsonObject.getString("currentDate");
                                tv.setText(date);
                                System.out.println(date);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                Select2Request registerRequest = new Select2Request(responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(registerRequest);
            }
        });


        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                };
                InsertRequest insertRequest = new InsertRequest(responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(insertRequest);
            }
        });

        btn_select = (Button) findViewById(R.id.btn_select);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String date = jsonObject.getString("currentDate");
                            tv.setText(date);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                SelectRequest selectRequest = new SelectRequest(responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(selectRequest);
            }
        });




    }

    public String currentDate(Date cd) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String current = dateFormat.format(cd);
        return current;
    }

}