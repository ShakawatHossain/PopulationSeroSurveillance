package com.iedcr.populationserosurveillance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.iedcr.populationserosurveillance.fragments.CalenderDialog;
import com.iedcr.populationserosurveillance.iview.CalenderInterface;
import com.iedcr.populationserosurveillance.model.ModelDemographic;

import java.util.Objects;

public class TreatmentActivity extends AppCompatActivity implements CalenderInterface {
    RadioGroup test,treat,dis,adm,oth_test,death;
    TextView test_date,treat_date;
    EditText treat_freq,oth_test_date,oth_test_result,adm_num,death_num;
//    CheckBox icu,nicu;
    Button submit;
    public static TreatmentActivity treatmentActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_treatment);
        init();
        treatmentActivity = this;
    }
    private void init(){
//        init
        test=(RadioGroup) findViewById(R.id.test);
        test.setOnCheckedChangeListener(checkedChangeListener);
        treat=(RadioGroup) findViewById(R.id.treat);
        treat.setOnCheckedChangeListener(checkedChangeListener);
        dis=(RadioGroup) findViewById(R.id.dis);
        adm=(RadioGroup) findViewById(R.id.adm);
        adm.setOnCheckedChangeListener(checkedChangeListener);
        oth_test=(RadioGroup) findViewById(R.id.oth_test);
        oth_test.setOnCheckedChangeListener(checkedChangeListener);
        oth_test_result=(EditText) findViewById(R.id.oth_test_result);
        death=(RadioGroup) findViewById(R.id.death);
        death.setOnCheckedChangeListener(checkedChangeListener);
        test_date=(TextView) findViewById(R.id.test_date);
        treat_date=(TextView) findViewById(R.id.treat_date);
        oth_test_date=(EditText) findViewById(R.id.oth_test_date);
        test_date.setOnClickListener(clickListener);
        treat_date.setOnClickListener(clickListener);
        treat_freq=(EditText) findViewById(R.id.treat_freq);
        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(clickListener);
        adm_num=(EditText) findViewById(R.id.adm_num);
        death_num=(EditText) findViewById(R.id.death_num);
//        prevValue
        if(ModelDemographic.treatParams.containsKey("test")){
            if (Integer.parseInt(ModelDemographic.treatParams.get("test"))==1){
                test.check(R.id.test_yes);
                if(test_date.getVisibility()!=View.VISIBLE)test_date.setVisibility(View.VISIBLE);
            }else if (Integer.parseInt(ModelDemographic.treatParams.get("test"))==2){
                test.check(R.id.test_no);
                if(test_date.getVisibility()==View.VISIBLE)test_date.setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.treatParams.get("test"))==3){
                test.check(R.id.test_un);
                if(test_date.getVisibility()==View.VISIBLE)test_date.setVisibility(View.GONE);
            }
        }
        if(ModelDemographic.treatParams.containsKey("treat")) {
            if (Integer.parseInt(ModelDemographic.treatParams.get("treat")) == 1) {
                treat.check(R.id.treat_yes);
                if (treat_date.getVisibility() != View.VISIBLE) {
                    treat_date.setVisibility(View.VISIBLE);
                    treat_freq.setVisibility(View.VISIBLE);
                }
            } else if (Integer.parseInt(ModelDemographic.treatParams.get("treat")) == 2) {
                treat.check(R.id.treat_no);
                if (treat_date.getVisibility() == View.VISIBLE) {treat_date.setVisibility(View.GONE);treat_freq.setVisibility(View.GONE);}
            } else if (Integer.parseInt(ModelDemographic.treatParams.get("treat")) == 3) {
                treat.check(R.id.treat_un);
                if (treat_date.getVisibility() == View.VISIBLE){ treat_date.setVisibility(View.GONE);treat_freq.setVisibility(View.GONE);}
            }
        }
        if(ModelDemographic.treatParams.containsKey("dis")){
            if (Integer.parseInt(ModelDemographic.treatParams.get("dis"))==1){
                dis.check(R.id.dis_yes);
            }else if (Integer.parseInt(ModelDemographic.treatParams.get("dis"))==2){
                dis.check(R.id.dis_no);
            }else if (Integer.parseInt(ModelDemographic.treatParams.get("dis"))==3){
                dis.check(R.id.dis_un);
            }
        }
        if(ModelDemographic.treatParams.containsKey("adm")){
            if (Integer.parseInt(ModelDemographic.treatParams.get("adm"))==1){
                adm.check(R.id.adm_yes);
                if(((LinearLayout)findViewById(R.id.adm_lin)).getVisibility()!=View.VISIBLE)((LinearLayout)findViewById(R.id.adm_lin)).setVisibility(View.VISIBLE);
            }else if (Integer.parseInt(ModelDemographic.treatParams.get("adm"))==2){
                adm.check(R.id.adm_no);
                if(((LinearLayout)findViewById(R.id.adm_lin)).getVisibility()==View.VISIBLE)((LinearLayout)findViewById(R.id.adm_lin)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.treatParams.get("adm"))==3){
                adm.check(R.id.adm_un);
                if(((LinearLayout)findViewById(R.id.adm_lin)).getVisibility()==View.VISIBLE)((LinearLayout)findViewById(R.id.adm_lin)).setVisibility(View.GONE);
            }
        }
        if(ModelDemographic.treatParams.containsKey("oth_test")){
            if (Integer.parseInt(ModelDemographic.treatParams.get("oth_test"))==1){
                oth_test.check(R.id.oth_test_yes);
                if(((LinearLayout)findViewById(R.id.oth_test_lin)).getVisibility()!=View.VISIBLE)((LinearLayout)findViewById(R.id.oth_test_lin)).setVisibility(View.VISIBLE);
            }else if (Integer.parseInt(ModelDemographic.treatParams.get("oth_test"))==2){
                oth_test.check(R.id.oth_test_no);
                if(((LinearLayout)findViewById(R.id.oth_test_lin)).getVisibility()==View.VISIBLE)((LinearLayout)findViewById(R.id.oth_test_lin)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.treatParams.get("oth_test"))==3){
                oth_test.check(R.id.oth_test_un);
                if(((LinearLayout)findViewById(R.id.oth_test_lin)).getVisibility()==View.VISIBLE)((LinearLayout)findViewById(R.id.oth_test_lin)).setVisibility(View.GONE);
            }
        }
        oth_test_result.setText(ModelDemographic.treatParams.containsKey("oth_test_result")?ModelDemographic.treatParams.get("oth_test_result"):"");
        if(ModelDemographic.treatParams.containsKey("death")){
            if (Integer.parseInt(ModelDemographic.treatParams.get("death"))==1){
                death.check(R.id.death_yes);
                if(((LinearLayout)findViewById(R.id.death_lin)).getVisibility()!=View.VISIBLE)((LinearLayout)findViewById(R.id.death_lin)).setVisibility(View.VISIBLE);
            }else if (Integer.parseInt(ModelDemographic.treatParams.get("death"))==2){
                death.check(R.id.death_no);
                if(((LinearLayout)findViewById(R.id.death_lin)).getVisibility()==View.VISIBLE)((LinearLayout)findViewById(R.id.death_lin)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.treatParams.get("death"))==3){
                death.check(R.id.death_un);
                if(((LinearLayout)findViewById(R.id.death_lin)).getVisibility()==View.VISIBLE)((LinearLayout)findViewById(R.id.death_lin)).setVisibility(View.GONE);
            }
        }
        treat_freq.setText(ModelDemographic.treatParams.containsKey("treat_freq")?ModelDemographic.treatParams.get("treat_freq"):"");
        adm_num.setText(ModelDemographic.treatParams.containsKey("adm_num")?ModelDemographic.treatParams.get("adm_num"):"");
        test_date.setText(ModelDemographic.treatParams.containsKey("test_date")?ModelDemographic.treatParams.get("test_date"):"");
        treat_date.setText(ModelDemographic.treatParams.containsKey("treat_date")?ModelDemographic.treatParams.get("treat_date"):"");
        death_num.setText(ModelDemographic.treatParams.containsKey("death_num")?ModelDemographic.treatParams.get("death_num"):"");
        oth_test_date.setText(ModelDemographic.treatParams.containsKey("oth_test_date")?ModelDemographic.treatParams.get("oth_test_date"):"");
    }
    private void setValues(){
        if (test.getCheckedRadioButtonId()==R.id.test_yes)
            ModelDemographic.treatParams.put("test","1");
        else if (test.getCheckedRadioButtonId()==R.id.test_no)
            ModelDemographic.treatParams.put("test","2");
        else if (test.getCheckedRadioButtonId()==R.id.test_un)
            ModelDemographic.treatParams.put("test","3");
        if (treat.getCheckedRadioButtonId()==R.id.treat_yes)
            ModelDemographic.treatParams.put("treat","1");
        else if (treat.getCheckedRadioButtonId()==R.id.treat_no)
            ModelDemographic.treatParams.put("treat","2");
        else if (treat.getCheckedRadioButtonId()==R.id.treat_un)
            ModelDemographic.treatParams.put("treat","3");
        if (dis.getCheckedRadioButtonId()==R.id.dis_yes)
            ModelDemographic.treatParams.put("dis","1");
        else if (dis.getCheckedRadioButtonId()==R.id.dis_no)
            ModelDemographic.treatParams.put("dis","2");
        else if (dis.getCheckedRadioButtonId()==R.id.dis_un)
            ModelDemographic.treatParams.put("dis","3");
        if (adm.getCheckedRadioButtonId()==R.id.adm_yes)
            ModelDemographic.treatParams.put("adm","1");
        else if (adm.getCheckedRadioButtonId()==R.id.adm_no)
            ModelDemographic.treatParams.put("adm","2");
        else if (adm.getCheckedRadioButtonId()==R.id.adm_un)
            ModelDemographic.treatParams.put("adm","3");
        if (oth_test.getCheckedRadioButtonId()==R.id.oth_test_yes)
            ModelDemographic.treatParams.put("oth_test","1");
        else if (oth_test.getCheckedRadioButtonId()==R.id.oth_test_no)
            ModelDemographic.treatParams.put("oth_test","2");
        else if (oth_test.getCheckedRadioButtonId()==R.id.oth_test_un)
            ModelDemographic.treatParams.put("oth_test","3");
        if (death.getCheckedRadioButtonId()==R.id.death_yes)
            ModelDemographic.treatParams.put("death","1");
        else if (death.getCheckedRadioButtonId()==R.id.death_no)
            ModelDemographic.treatParams.put("death","2");
        else if (death.getCheckedRadioButtonId()==R.id.death_un)
            ModelDemographic.treatParams.put("death","3");

        ModelDemographic.treatParams.put("oth_test_result",oth_test_result.getText().toString());
        ModelDemographic.treatParams.put("treat_freq",treat_freq.getText().toString());
        if(ModelDemographic.treatParams.get("adm").compareTo("1")!=0 ||adm_num.getText().toString()==null || adm_num.getText().toString().isEmpty())
            ModelDemographic.treatParams.put("adm_num","0");
        else
            ModelDemographic.treatParams.put("adm_num",adm_num.getText().toString());
        ModelDemographic.treatParams.put("test_date",test_date.getText().toString());
        ModelDemographic.treatParams.put("treat_date",treat_date.getText().toString());
        if(ModelDemographic.treatParams.get("death").compareTo("1")!=0 ||death_num.getText().toString()==null || death_num.getText().toString().isEmpty())
            ModelDemographic.treatParams.put("death_num","0");
        else
            ModelDemographic.treatParams.put("death_num",death_num.getText().toString());
        ModelDemographic.treatParams.put("oth_test_date",oth_test_date.getText().toString());
    }
    private boolean check(){
        if(test.getCheckedRadioButtonId()==R.id.test_yes && (test_date.getText().toString()==null || test_date.getText().toString().isEmpty())) return false;
        if(treat.getCheckedRadioButtonId()==R.id.treat_yes && (treat_date.getText().toString()==null || treat_date.getText().toString().isEmpty())) return false;
        if(treat.getCheckedRadioButtonId()==R.id.treat_yes && (treat_freq.getText().toString()==null || treat_freq.getText().toString().isEmpty())) return false;
        if(!ModelDemographic.treatParams.containsKey("dis")) return false;
        if(!ModelDemographic.treatParams.containsKey("adm")) return false;
        if(Integer.parseInt(ModelDemographic.treatParams.get("adm"))==1){
            if (!ModelDemographic.treatParams.containsKey("adm_num")|| ModelDemographic.treatParams.get("adm_num")==null) return false;
        }
        if(oth_test.getCheckedRadioButtonId()==R.id.oth_test_yes && (oth_test_date.getText().toString()==null || oth_test_date.getText().toString().isEmpty())) return false;
        if(!ModelDemographic.treatParams.containsKey("death")) return false;
        if(Integer.parseInt(ModelDemographic.treatParams.get("death"))==1){
            if (!ModelDemographic.treatParams.containsKey("death_num")|| ModelDemographic.treatParams.get("death_num")==null) return false;
        }
        return true;
    }
    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(group.getId()==test.getId()){
                if (checkedId==R.id.test_yes){
                    if(test_date.getVisibility()!=View.VISIBLE)test_date.setVisibility(View.VISIBLE);
                }else
                if(test_date.getVisibility()==View.VISIBLE)test_date.setVisibility(View.GONE);
            }
            else if(group.getId()==treat.getId()){
                if (checkedId==R.id.treat_yes){
                    if(treat_date.getVisibility()!=View.VISIBLE){
                        treat_date.setVisibility(View.VISIBLE);
                        treat_freq.setVisibility(View.VISIBLE);
                    }
                }else
                if(treat_date.getVisibility()==View.VISIBLE){treat_date.setVisibility(View.GONE);treat_freq.setVisibility(View.GONE);}
            }
            else if(group.getId()==adm.getId()){
                if (checkedId==R.id.adm_yes){
                    if(((LinearLayout)findViewById(R.id.adm_lin)).getVisibility()!=View.VISIBLE)
                        ((LinearLayout)findViewById(R.id.adm_lin)).setVisibility(View.VISIBLE);
                }else
                if(((LinearLayout)findViewById(R.id.adm_lin)).getVisibility()==View.VISIBLE)
                    ((LinearLayout)findViewById(R.id.adm_lin)).setVisibility(View.GONE);
            }
            else if(group.getId()==oth_test.getId()){
                if (checkedId==R.id.oth_test_yes){
                    if(((LinearLayout)findViewById(R.id.oth_test_lin)).getVisibility()!=View.VISIBLE)((LinearLayout)findViewById(R.id.oth_test_lin)).setVisibility(View.VISIBLE);
                }else
                if(((LinearLayout)findViewById(R.id.oth_test_lin)).getVisibility()==View.VISIBLE)((LinearLayout)findViewById(R.id.oth_test_lin)).setVisibility(View.GONE);
            }
            else if(group.getId()==death.getId()){
                if (checkedId==R.id.death_yes){
                    if(((LinearLayout)findViewById(R.id.death_lin)).getVisibility()!=View.VISIBLE)((LinearLayout)findViewById(R.id.death_lin)).setVisibility(View.VISIBLE);
                }else
                if(((LinearLayout)findViewById(R.id.death_lin)).getVisibility()==View.VISIBLE)((LinearLayout)findViewById(R.id.death_lin)).setVisibility(View.GONE);
            }
        }
    };
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==submit.getId()){
                setValues();
                if (check()){
                    startActivity(new Intent(TreatmentActivity.this,TreatmentActivity2.class));
                }else {
                    Toast.makeText(TreatmentActivity.this,"Check Input Please",Toast.LENGTH_LONG).show();
                }
            }else
                new CalenderDialog(TreatmentActivity.this,TreatmentActivity.this,
                        ((TextView)v).getText().toString(),"Date",(TextView)v).show();
        }
    };

    @Override
    public void getDate(String dat, TextView editText) {
        editText.setText(dat);
    }
}