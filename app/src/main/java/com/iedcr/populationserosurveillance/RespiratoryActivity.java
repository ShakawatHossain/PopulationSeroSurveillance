package com.iedcr.populationserosurveillance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.iedcr.populationserosurveillance.fragments.CalenderDialog;
import com.iedcr.populationserosurveillance.iview.CalenderInterface;
import com.iedcr.populationserosurveillance.model.ModelDemographic;

public class RespiratoryActivity extends AppCompatActivity implements CalenderInterface {
    CheckBox respiratory,fever,cough,asthma,tired,body_pain,headache,test,smell,throat_pain,cold,diarrhoea;
    TextView respiratory_date,fever_date,cough_date,asthma_date,tired_date,body_pain_date,headache_date,test_date,smell_date,throat_pain_date,cold_date,diarrhoea_date;
    CardView res_card;
    Button submit;
    public static RespiratoryActivity respiratoryActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_respiratory);
        init();
        respiratoryActivity = this;
    }
    private void init(){
//        init
        respiratory=(CheckBox) findViewById(R.id.respiratory);
        respiratory.setOnCheckedChangeListener(checkedChangeListener);
        fever=(CheckBox) findViewById(R.id.fever);
        cough=(CheckBox) findViewById(R.id.cough);
        asthma=(CheckBox) findViewById(R.id.asthma);
        tired=(CheckBox) findViewById(R.id.tired);
        body_pain=(CheckBox) findViewById(R.id.body_pain);
        headache=(CheckBox) findViewById(R.id.headache);
        test=(CheckBox) findViewById(R.id.test);
        smell=(CheckBox) findViewById(R.id.smell);
        throat_pain=(CheckBox) findViewById(R.id.throat_pain);
        cold=(CheckBox) findViewById(R.id.cold);
        diarrhoea=(CheckBox) findViewById(R.id.diarrhoea);
        respiratory_date=(TextView) findViewById(R.id.respiratory_date);
        fever_date=(TextView) findViewById(R.id.fever_date);
        cough_date=(TextView) findViewById(R.id.cough_date);
        asthma_date=(TextView) findViewById(R.id.asthma_date);
        tired_date=(TextView) findViewById(R.id.tired_date);
        body_pain_date=(TextView) findViewById(R.id.body_pain_date);
        headache_date=(TextView) findViewById(R.id.headache_date);
        test_date=(TextView) findViewById(R.id.test_date);
        smell_date=(TextView) findViewById(R.id.smell_date);
        throat_pain_date=(TextView) findViewById(R.id.throat_pain_date);
        cold_date=(TextView) findViewById(R.id.cold_date);
        diarrhoea_date=(TextView) findViewById(R.id.diarrhoea_date);
        respiratory_date.setOnClickListener(clickListener);
        fever_date.setOnClickListener(clickListener);
        cough_date.setOnClickListener(clickListener);
        asthma_date.setOnClickListener(clickListener);
        tired_date.setOnClickListener(clickListener);
        body_pain_date.setOnClickListener(clickListener);
        headache_date.setOnClickListener(clickListener);
        test_date.setOnClickListener(clickListener);
        smell_date.setOnClickListener(clickListener);
        throat_pain_date.setOnClickListener(clickListener);
        cold_date.setOnClickListener(clickListener);
        diarrhoea_date.setOnClickListener(clickListener);
        res_card=(CardView) findViewById(R.id.res_card);
        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(clickListener);
//      Prev Value
        if (ModelDemographic.resParams.containsKey("respiratory")&& Integer.parseInt(ModelDemographic.resParams.get("respiratory"))==1){
            if(res_card.getVisibility()!=View.VISIBLE) res_card.setVisibility(View.VISIBLE);
        }else {
            if(res_card.getVisibility()==View.VISIBLE) res_card.setVisibility(View.GONE);
        }
        respiratory.setChecked(ModelDemographic.resParams.containsKey("respiratory")?Integer.parseInt(ModelDemographic.resParams.get("respiratory"))==1:false);
        fever.setChecked(ModelDemographic.resParams.containsKey("fever")?Integer.parseInt(ModelDemographic.resParams.get("fever"))==1:false);
        cough.setChecked(ModelDemographic.resParams.containsKey("cough")?Integer.parseInt(ModelDemographic.resParams.get("cough"))==1:false);
        asthma.setChecked(ModelDemographic.resParams.containsKey("asthma")?Integer.parseInt(ModelDemographic.resParams.get("asthma"))==1:false);
        tired.setChecked(ModelDemographic.resParams.containsKey("tired")?Integer.parseInt(ModelDemographic.resParams.get("tired"))==1:false);
        body_pain.setChecked(ModelDemographic.resParams.containsKey("body_pain")?Integer.parseInt(ModelDemographic.resParams.get("body_pain"))==1:false);
        headache.setChecked(ModelDemographic.resParams.containsKey("headache")?Integer.parseInt(ModelDemographic.resParams.get("headache"))==1:false);
        test.setChecked(ModelDemographic.resParams.containsKey("test")?Integer.parseInt(ModelDemographic.resParams.get("test"))==1:false);
        smell.setChecked(ModelDemographic.resParams.containsKey("smell")?Integer.parseInt(ModelDemographic.resParams.get("smell"))==1:false);
        throat_pain.setChecked(ModelDemographic.resParams.containsKey("throat_pain")?Integer.parseInt(ModelDemographic.resParams.get("throat_pain"))==1:false);
        cold.setChecked(ModelDemographic.resParams.containsKey("cold")?Integer.parseInt(ModelDemographic.resParams.get("cold"))==1:false);
        diarrhoea.setChecked(ModelDemographic.resParams.containsKey("diarrhoea")?Integer.parseInt(ModelDemographic.resParams.get("diarrhoea"))==1:false);
        respiratory_date.setText(ModelDemographic.resParams.containsKey("respiratory_date")?ModelDemographic.resParams.get("respiratory_date"):"");
        fever_date.setText(ModelDemographic.resParams.containsKey("fever_date")?ModelDemographic.resParams.get("fever_date"):"");
        cough_date.setText(ModelDemographic.resParams.containsKey("cough_date")?ModelDemographic.resParams.get("cough_date"):"");
        asthma_date.setText(ModelDemographic.resParams.containsKey("asthma_date")?ModelDemographic.resParams.get("asthma_date"):"");
        tired_date.setText(ModelDemographic.resParams.containsKey("tired_date")?ModelDemographic.resParams.get("tired_date"):"");
        body_pain_date.setText(ModelDemographic.resParams.containsKey("body_pain_date")?ModelDemographic.resParams.get("body_pain_date"):"");
        headache_date.setText(ModelDemographic.resParams.containsKey("headache_date")?ModelDemographic.resParams.get("headache_date"):"");
        test_date.setText(ModelDemographic.resParams.containsKey("test_date")?ModelDemographic.resParams.get("test_date"):"");
        smell_date.setText(ModelDemographic.resParams.containsKey("smell_date")?ModelDemographic.resParams.get("smell_date"):"");
        throat_pain_date.setText(ModelDemographic.resParams.containsKey("throat_pain_date")?ModelDemographic.resParams.get("throat_pain_date"):"");
        cold_date.setText(ModelDemographic.resParams.containsKey("cold_date")?ModelDemographic.resParams.get("cold_date"):"");
        diarrhoea_date.setText(ModelDemographic.resParams.containsKey("diarrhoea_date")?ModelDemographic.resParams.get("diarrhoea_date"):"");
    }
    private CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.getId()==respiratory.getId()){
                if (isChecked){
                    if (res_card.getVisibility()!=View.VISIBLE)res_card.setVisibility(View.VISIBLE);
                }else{
                    if (res_card.getVisibility()==View.VISIBLE)res_card.setVisibility(View.GONE);
                }
            }
        }
    };
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==submit.getId()){
                setValues();
                if (check()){
                    if(respiratory.isChecked())
                        startActivity(new Intent(RespiratoryActivity.this,HistoryActivity.class));
                    else
                        startActivity(new Intent(RespiratoryActivity.this,PastDisActivity.class));
                }else{
                    Toast.makeText(RespiratoryActivity.this,"Check Input",Toast.LENGTH_SHORT).show();
                }
            }else {
                new CalenderDialog(RespiratoryActivity.this,RespiratoryActivity.this,
                    ((TextView)v).getText().toString(),"Date",((TextView)v)).show();
            }
        }
    };
    private void setValues(){
        ModelDemographic.resParams.put("respiratory",respiratory.isChecked()?"1":"2");
        ModelDemographic.resParams.put("fever",fever.isChecked()?"1":"2");
        ModelDemographic.resParams.put("cough",cough.isChecked()?"1":"2");
        ModelDemographic.resParams.put("asthma",asthma.isChecked()?"1":"2");
        ModelDemographic.resParams.put("tired",tired.isChecked()?"1":"2");
        ModelDemographic.resParams.put("body_pain",body_pain.isChecked()?"1":"2");
        ModelDemographic.resParams.put("headache",headache.isChecked()?"1":"2");
        ModelDemographic.resParams.put("test",test.isChecked()?"1":"2");
        ModelDemographic.resParams.put("smell",smell.isChecked()?"1":"2");
        ModelDemographic.resParams.put("throat_pain",throat_pain.isChecked()?"1":"2");
        ModelDemographic.resParams.put("cold",cold.isChecked()?"1":"2");
        ModelDemographic.resParams.put("diarrhoea",diarrhoea.isChecked()?"1":"2");
        ModelDemographic.resParams.put("respiratory_date",respiratory_date.getText().toString());
        ModelDemographic.resParams.put("fever_date",fever_date.getText().toString());
        ModelDemographic.resParams.put("cough_date",cough_date.getText().toString());
        ModelDemographic.resParams.put("asthma_date",asthma_date.getText().toString());
        ModelDemographic.resParams.put("tired_date",tired_date.getText().toString());
        ModelDemographic.resParams.put("body_pain_date",body_pain_date.getText().toString());
        ModelDemographic.resParams.put("headache_date",headache_date.getText().toString());
        ModelDemographic.resParams.put("test_date",test_date.getText().toString());
        ModelDemographic.resParams.put("smell_date",smell_date.getText().toString());
        ModelDemographic.resParams.put("throat_pain_date",throat_pain_date.getText().toString());
        ModelDemographic.resParams.put("cold_date",cold_date.getText().toString());
        ModelDemographic.resParams.put("diarrhoea_date",diarrhoea_date.getText().toString());
    }
    private boolean check(){
//        if(respiratory.isChecked() && (respiratory_date.getText().toString()==null || respiratory_date.getText().toString().isEmpty())) return false;
        if(fever.isChecked() && (fever_date.getText().toString()==null || fever_date.getText().toString().isEmpty())) return false;
        if(cough.isChecked() && (cough_date.getText().toString()==null ||cough_date.getText().toString().isEmpty())) return false;
        if(asthma.isChecked() && (asthma_date.getText().toString()==null ||asthma_date.getText().toString().isEmpty())) return false;
        if(tired.isChecked() && (tired_date.getText().toString()==null ||tired_date.getText().toString().isEmpty())) return false;
        if(body_pain.isChecked() && (body_pain_date.getText().toString()==null ||body_pain_date.getText().toString().isEmpty())) return false;
        if(headache.isChecked() && (headache_date.getText().toString()==null ||headache_date.getText().toString().isEmpty())) return false;
        if(test.isChecked() && (test_date.getText().toString()==null ||test_date.getText().toString().isEmpty())) return false;
        if(smell.isChecked() && (smell_date.getText().toString()==null ||smell_date.getText().toString().isEmpty())) return false;
        if(throat_pain.isChecked() && (throat_pain_date.getText().toString()==null ||throat_pain_date.getText().toString().isEmpty())) return false;
        if(cold.isChecked() && (cold_date.getText().toString()==null ||cold_date.getText().toString().isEmpty())) return false;
        if(diarrhoea.isChecked() && (diarrhoea_date.getText().toString()==null ||diarrhoea_date.getText().toString().isEmpty())) return false;
        return true;
    }

    @Override
    public void getDate(String dat, TextView editText) {
        editText.setText(dat);
    }
}