package com.devsaleh.performancescale.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.devsaleh.performancescale.R;

public class SelectDepartment_H extends AppCompatActivity {

    String[] item = {"cushing academy","fairfax christian school","img academy","martin luther high school","new hampton school","the brook hill school","think global school","woodside priory school","cairo american college","harvard university","ie university","national university of singapore"};

    AutoCompleteTextView autoCompleteText;

    ArrayAdapter<String> adapterItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_department_h);

        autoCompleteText = findViewById(R.id.auto_complete_text);

        adapterItem = new ArrayAdapter<String>(this,R.layout.list_item,item);

        autoCompleteText.setAdapter(adapterItem);

        autoCompleteText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item, Toast.LENGTH_SHORT).show();
            }
        });

    }
}