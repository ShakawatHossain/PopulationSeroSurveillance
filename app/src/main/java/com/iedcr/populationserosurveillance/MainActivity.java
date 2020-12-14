package com.iedcr.populationserosurveillance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.iedcr.populationserosurveillance.model.ModelDemographic;
import com.iedcr.populationserosurveillance.model.MyDB;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    TextView sero_id,user_id;
    EditText h_id;
    Button submit;
    public static MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_main);
//        printDB();
        init();
        mainActivity = this;
    }
    private void init(){
//        init
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(selectedListener);
        sero_id = (TextView) findViewById(R.id.sero_id);
        user_id = (TextView) findViewById(R.id.user_id);
        h_id = (EditText) findViewById(R.id.h_id);
        h_id.addTextChangedListener(textWatcher);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(clickListener);

//        Prev Value
        if(ModelDemographic.sero_id!=null && !ModelDemographic.sero_id.isEmpty()){
            String s = ModelDemographic.sero_id;
            spinner.setSelection(Integer.parseInt(s.substring(0,2)));
            sero_id.setText(ModelDemographic.sero_id);
            user_id.setText(s.substring(2,4));
            h_id.setText(s.substring(4));
        }else {
            user_id.setText(ModelDemographic.user_id);
        }
        if(ModelDemographic.user_id==null || ModelDemographic.user_id.isEmpty()){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
        }
    }
    private Spinner.OnItemSelectedListener selectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position<10)
                sero_id.setText("0"+position+user_id.getText().toString()+h_id.getText().toString());
            else
                sero_id.setText(position+user_id.getText().toString()+h_id.getText().toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(spinner.getSelectedItemPosition()<10)
                sero_id.setText("0"+spinner.getSelectedItemPosition()+user_id.getText().toString()+s.toString());
            else
                sero_id.setText(spinner.getSelectedItemPosition()+user_id.getText().toString()+s.toString());
        }
    };
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String s = sero_id.getText().toString();
            if(spinner.getSelectedItemPosition()==0) {
                Toast.makeText(MainActivity.this, "Select a slum", Toast.LENGTH_SHORT).show();
                return;}
            if (h_id.getText().toString()==null || h_id.getText().toString().isEmpty()||h_id.getText().toString().length()!=3){
                    Toast.makeText(MainActivity.this, "Check house number", Toast.LENGTH_SHORT).show();
                    return;
            }
            ModelDemographic.sero_id=s;
            startActivity(new Intent(MainActivity.this,DemographicActivity.class));
        }
    };



}