package com.devsaleh.performancescale.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.devsaleh.performancescale.Adapter.SelectSubjectAdapter;
import com.devsaleh.performancescale.Model.SelectSubject;
import com.devsaleh.performancescale.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class selectSubject_H extends AppCompatActivity {

    RecyclerView RV_selectSubject;
    ArrayList<SelectSubject> selectSubjectsList;
    String URL_POST = "https://performance.drwafi.be/public/api/getStudentSubjects";
    RequestQueue mQueue;
    SelectSubjectAdapter selectSubjectAdapter;
    String id;

    ImageView custom_img_select_subject;
    TextView custom_tv_subject_name, custom_tv_subject_group_number;

    String resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject_h);

        Intent intent = getIntent();
        resultTv = intent.getStringExtra("resultTv");
        Toast.makeText(getApplicationContext(), resultTv, Toast.LENGTH_SHORT).show();


//        // test hamza code
//        TextView b = findViewById(R.id.test2);
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), MathsGroup_H.class);
//                startActivity(i);
//            }
//        });
//
//
//        mQueue = Volley.newRequestQueue(getApplicationContext());
//
//        //Start Find View By ID
//        RV_selectSubject = findViewById(R.id.RV_selectSubject);
//        //End find View By ID
//        id = login.USER_ID;
//        //Start method
//        //End method
//        RV_selectSubject.setHasFixedSize(true);
//        RV_selectSubject.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

    }
   /*
    private void read_API_Data() {
        selectSubjectsList = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.GET, URL_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if (jsonArray.length() != 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObjectSelectSubjects = (JSONObject) jsonArray.get(i);
                            selectSubjectsList.add(new com.devsaleh.performancescale.Model.SelectSubject(jsonObjectSelectSubjects.getString("BRA_NM")
                                    , jsonObjectSelectSubjects.getString("WAN_DISSUE")
                                    , "مطلوب للمحكمخ"));
                        }
                        selectSubjectAdapter = new SelectSubjectAdapter(getApplicationContext(), selectSubjectsList);
                        RV_selectSubject.setAdapter(selectSubjectAdapter);
                    } else {
                        Toast.makeText(getApplicationContext(), "لا يوجد مواد", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("p1", id);
                return params;
            }
        };
        mQueue.add(request);
    }

    */


    // ****************************************************************************** *****************************//

    private void getJSONArray() {
        selectSubjectsList = new ArrayList<>();
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, URL_POST, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String subjectName = jsonObject.getString("sub1_group");
                        String subjectGroupNumber = jsonObject.getString("id");

                        if (resultTv.equals("Scientific")) {

//                            switch (subjectName) {
//                                case "1.g1":
//                                    custom_tv_subject_name.setText("Arabic");
//                                case "2.g1":
//                                    custom_tv_subject_name.setText("English");
//                                case "3.g3":
//                                    custom_tv_subject_name.setText("Maths");
//                                case "4.g1":
//                                    custom_tv_subject_name.setText("Chemistry");
//                                case "5.g3":
//                                    custom_tv_subject_name.setText("Physics");
//                                case "6.g1":
//                                    custom_tv_subject_name.setText("Biology");
//                                case "7.g1":
//                                    custom_tv_subject_name.setText("Religion");
//                                case "12.g3":
//                                    custom_tv_subject_name.setText("History");
//                                    break;
//                            }
                        } else if (resultTv.equals("Literary")) {

                        } else if (resultTv.equals("Industrial")) {

                        }


                        selectSubjectsList.add(new SelectSubject(subjectName, subjectGroupNumber));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                SelectSubjectAdapter adapter = new SelectSubjectAdapter(getApplicationContext(), selectSubjectsList);
                RV_selectSubject.setAdapter(adapter);
                RV_selectSubject.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //AppController.getInstance().addToRequestQueue(arrayRequest);
    }
}

