package com.iedcr.populationserosurveillance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.iedcr.populationserosurveillance.fragments.CalenderDialog;
import com.iedcr.populationserosurveillance.iview.CalenderInterface;
import com.iedcr.populationserosurveillance.model.ModelDemographic;

import java.util.Objects;

public class HistoryActivity extends AppCompatActivity implements CalenderInterface {
    RadioGroup covid_pt,join,test,journey,health,quack,inv;
    TextView test_date;
    EditText journey_from,journey_to,health_name,quack_name,inv_day;
    Button submit;
    public static HistoryActivity historyActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_history);
        init();
        historyActivity = this;
    }
    private void init(){
//  init
        covid_pt=(RadioGroup) findViewById(R.id.covid_pt);
        join=(RadioGroup) findViewById(R.id.join);
        test=(RadioGroup) findViewById(R.id.test);
        journey=(RadioGroup) findViewById(R.id.journey);
        health=(RadioGroup) findViewById(R.id.health);
        quack=(RadioGroup) findViewById(R.id.quack);
        test.setOnCheckedChangeListener(checkedChangeListener);
        journey.setOnCheckedChangeListener(checkedChangeListener);
        health.setOnCheckedChangeListener(checkedChangeListener);
        quack.setOnCheckedChangeListener(checkedChangeListener);
        test_date=(TextView) findViewById(R.id.test_date);
        test_date.setOnClickListener(clickListener);
        journey_from=(EditText) findViewById(R.id.journey_from);
        journey_to=(EditText) findViewById(R.id.journey_to);
        health_name=(EditText) findViewById(R.id.health_name);
        quack_name=(EditText) findViewById(R.id.quack_name);
        inv=(RadioGroup) findViewById(R.id.inv);
        inv_day=(EditText) findViewById(R.id.inv_day);
        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(clickListener);
        inv.setOnCheckedChangeListener(checkedChangeListener);
//        prevValue
        if(ModelDemographic.hisParams.containsKey("covid_pt")){
            if(Integer.parseInt(Objects.requireNonNull(ModelDemographic.hisParams.get("covid_pt")))==1){
                covid_pt.check(R.id.covid_pt_yes);
            }else if(Integer.parseInt(Objects.requireNonNull(ModelDemographic.hisParams.get("covid_pt")))==2){
                covid_pt.check(R.id.covid_pt_no);
            }else if(Integer.parseInt(Objects.requireNonNull(ModelDemographic.hisParams.get("covid_pt")))==3){
                covid_pt.check(R.id.covid_pt_un);
            }
        }
        if(ModelDemographic.hisParams.containsKey("join")){
            if(Integer.parseInt(Objects.requireNonNull(ModelDemographic.hisParams.get("join")))==1){
                join.check(R.id.join_yes);
            }else if(Integer.parseInt(Objects.requireNonNull(ModelDemographic.hisParams.get("join")))==2){
                join.check(R.id.join_no);
            }else if(Integer.parseInt(Objects.requireNonNull(ModelDemographic.hisParams.get("join")))==3){
                join.check(R.id.join_un);
            }
        }
        if(ModelDemographic.hisParams.containsKey("test")){
            if(Integer.parseInt(Objects.requireNonNull(ModelDemographic.hisParams.get("test")))==1){
                test.check(R.id.test_yes);
                if (test_date.getVisibility()!=View.VISIBLE)
                    test_date.setVisibility(View.VISIBLE);
            }else if(Integer.parseInt(Objects.requireNonNull(ModelDemographic.hisParams.get("test")))==2){
                test.check(R.id.test_no);
                if (test_date.getVisibility()==View.VISIBLE)
                    test_date.setVisibility(View.GONE);
            }else if(Integer.parseInt(Objects.requireNonNull(ModelDemographic.hisParams.get("test")))==3){
                test.check(R.id.test_un);
                if (test_date.getVisibility()==View.VISIBLE)
                    test_date.setVisibility(View.GONE);
            }
        }
        if(ModelDemographic.hisParams.containsKey("journey")){
            if(Integer.parseInt(Objects.requireNonNull(ModelDemographic.hisParams.get("journey")))==1){
                journey.check(R.id.journey_yes);
                if(( (TableRow) findViewById(R.id.journey_row)).getVisibility()!=View.VISIBLE)
                    ( (TableRow) findViewById(R.id.journey_row)).setVisibility(View.VISIBLE);
            }else if(Integer.parseInt(Objects.requireNonNull(ModelDemographic.hisParams.get("journey")))==2){
                journey.check(R.id.journey_no);
                if(( (TableRow) findViewById(R.id.journey_row)).getVisibility()==View.VISIBLE)
                    ( (TableRow) findViewById(R.id.journey_row)).setVisibility(View.GONE);
            }else if(Integer.parseInt(Objects.requireNonNull(ModelDemographic.hisParams.get("journey")))==3){
                journey.check(R.id.journey_un);
                if(( (TableRow) findViewById(R.id.journey_row)).getVisibility()==View.VISIBLE)
                    ( (TableRow) findViewById(R.id.journey_row)).setVisibility(View.GONE);
            }
        }
        if(ModelDemographic.hisParams.containsKey("health")){
            if(Integer.parseInt(Objects.requireNonNull(ModelDemographic.hisParams.get("health")))==1){
                health.check(R.id.health_yes);
                if (health_name.getVisibility()!=View.VISIBLE)health_name.setVisibility(View.VISIBLE);
            }else if(Integer.parseInt(Objects.requireNonNull(ModelDemographic.hisParams.get("health")))==2){
                health.check(R.id.health_no);
                if (health_name.getVisibility()==View.VISIBLE)health_name.setVisibility(View.GONE);
            }else if(Integer.parseInt(Objects.requireNonNull(ModelDemographic.hisParams.get("health")))==3){
                health.check(R.id.health_un);
                if (health_name.getVisibility()==View.VISIBLE)health_name.setVisibility(View.GONE);
            }
        }
        if(ModelDemographic.hisParams.containsKey("quack")){
            if(Integer.parseInt(Objects.requireNonNull(ModelDemographic.hisParams.get("quack")))==1){
                quack.check(R.id.quack_yes);
                if (quack_name.getVisibility()!=View.VISIBLE)quack_name.setVisibility(View.VISIBLE);
            }else if(Integer.parseInt(Objects.requireNonNull(ModelDemographic.hisParams.get("quack")))==2){
                quack.check(R.id.quack_no);
                if (quack_name.getVisibility()==View.VISIBLE)quack_name.setVisibility(View.GONE);
            }else if(Integer.parseInt(Objects.requireNonNull(ModelDemographic.hisParams.get("quack")))==3){
                quack.check(R.id.quack_un);
                if (quack_name.getVisibility()==View.VISIBLE)quack_name.setVisibility(View.GONE);
            }
        }
        if(ModelDemographic.riskParams.containsKey("inv")){
            if (Integer.parseInt(ModelDemographic.riskParams.get("inv")) == 1) {
                inv.check(R.id.inv_yes);
                if (((TextInputLayout) findViewById(R.id.inv_layout)).getVisibility() != View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.inv_layout)).setVisibility(View.VISIBLE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("inv")) == 2) {
                inv.check(R.id.inv_no);
                if (((TextInputLayout) findViewById(R.id.inv_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.inv_layout)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("inv")) == 3) {
                inv.check(R.id.inv_un);
                if (((TextInputLayout) findViewById(R.id.inv_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.inv_layout)).setVisibility(View.GONE);
            }
        }
        inv_day.setText(ModelDemographic.riskParams.containsKey("inv_day")?ModelDemographic.riskParams.get("inv_day"):"");
        journey_from.setText(ModelDemographic.hisParams.containsKey("journey_from")?ModelDemographic.hisParams.get("journey_from"):"");
        journey_to.setText(ModelDemographic.hisParams.containsKey("journey_to")?ModelDemographic.hisParams.get("journey_to"):"");
        health_name.setText(ModelDemographic.hisParams.containsKey("health_name")?ModelDemographic.hisParams.get("health_name"):"");
        quack_name.setText(ModelDemographic.hisParams.containsKey("quack_name")? ModelDemographic.hisParams.get("quack_name"):"");
        test_date.setText(ModelDemographic.hisParams.containsKey("test_date")?ModelDemographic.hisParams.get("test_date"):"");
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==test_date.getId())
                new CalenderDialog(HistoryActivity.this,HistoryActivity.this,test_date.getText().toString(),
                        "Date",test_date).show();
            else if(v.getId()==submit.getId()) {
                setValues();
                if (check()){
                    startActivity(new Intent(HistoryActivity.this,PastDisActivity.class));
                }else {
                    Toast.makeText(HistoryActivity.this,"Check Input",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (group.getId()==test.getId()){
                if (checkedId==R.id.test_yes){
                    if (test_date.getVisibility()!=View.VISIBLE)
                        test_date.setVisibility(View.VISIBLE);
                }else if (checkedId==R.id.test_no){
                    if (test_date.getVisibility()==View.VISIBLE)
                        test_date.setVisibility(View.GONE);
                }else if (checkedId==R.id.test_un){
                    if (test_date.getVisibility()==View.VISIBLE)
                        test_date.setVisibility(View.GONE);
                }
            }else if (group.getId()==journey.getId()){
                if (checkedId==R.id.journey_yes){
                    if (( (TableRow) findViewById(R.id.journey_row)).getVisibility()!=View.VISIBLE)
                        ( (TableRow) findViewById(R.id.journey_row)).setVisibility(View.VISIBLE);
                }else if (checkedId==R.id.journey_no){
                    if (( (TableRow) findViewById(R.id.journey_row)).getVisibility()==View.VISIBLE)
                        ( (TableRow) findViewById(R.id.journey_row)).setVisibility(View.GONE);
                }else if (checkedId==R.id.journey_un){
                    if (( (TableRow) findViewById(R.id.journey_row)).getVisibility()==View.VISIBLE)
                        ( (TableRow) findViewById(R.id.journey_row)).setVisibility(View.GONE);
                }
            }else if (group.getId()==health.getId()){
                if (checkedId==R.id.health_yes){
                    if (health_name.getVisibility()!=View.VISIBLE)
                        health_name.setVisibility(View.VISIBLE);
                }else if (checkedId==R.id.health_no){
                    if (health_name.getVisibility()==View.VISIBLE)
                        health_name.setVisibility(View.GONE);
                }else if (checkedId==R.id.health_un){
                    if (health_name.getVisibility()==View.VISIBLE)
                        health_name.setVisibility(View.GONE);
                }
            }else if (group.getId()==quack.getId()){
                if (checkedId==R.id.quack_yes){
                    if (quack_name.getVisibility()!=View.VISIBLE)
                        quack_name.setVisibility(View.VISIBLE);
                }else if (checkedId==R.id.quack_no){
                    if (quack_name.getVisibility()==View.VISIBLE)
                        quack_name.setVisibility(View.GONE);
                }else if (checkedId==R.id.quack_un){
                    if (quack_name.getVisibility()==View.VISIBLE)
                        quack_name.setVisibility(View.GONE);
                }
            }
            else if(group.getId()==inv.getId()){
                if (checkedId==R.id.inv_yes) {
                    if (((TextInputLayout) findViewById(R.id.inv_layout)).getVisibility() != View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.inv_layout)).setVisibility(View.VISIBLE);
                }else{
                    if (((TextInputLayout) findViewById(R.id.inv_layout)).getVisibility() == View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.inv_layout)).setVisibility(View.GONE);
                }
            }
        }
    };
    private void setValues(){
        if(covid_pt.getCheckedRadioButtonId()==R.id.covid_pt_yes)
            ModelDemographic.hisParams.put("covid_pt","1");
        else if(covid_pt.getCheckedRadioButtonId()==R.id.covid_pt_no)
            ModelDemographic.hisParams.put("covid_pt","2");
        else if(covid_pt.getCheckedRadioButtonId()==R.id.covid_pt_un)
            ModelDemographic.hisParams.put("covid_pt","3");
        if(join.getCheckedRadioButtonId()==R.id.join_yes)
            ModelDemographic.hisParams.put("join","1");
        else if(join.getCheckedRadioButtonId()==R.id.join_no)
            ModelDemographic.hisParams.put("join","2");
        else if(join.getCheckedRadioButtonId()==R.id.join_un)
            ModelDemographic.hisParams.put("join","3");
        if(test.getCheckedRadioButtonId()==R.id.test_yes)
            ModelDemographic.hisParams.put("test","1");
        else if(test.getCheckedRadioButtonId()==R.id.test_no)
            ModelDemographic.hisParams.put("test","2");
        else  if(test.getCheckedRadioButtonId()==R.id.test_un)
            ModelDemographic.hisParams.put("test","3");
        if(journey.getCheckedRadioButtonId()==R.id.journey_yes)
            ModelDemographic.hisParams.put("journey","1");
        else if(journey.getCheckedRadioButtonId()==R.id.journey_no)
            ModelDemographic.hisParams.put("journey","2");
        else if(journey.getCheckedRadioButtonId()==R.id.journey_un)
            ModelDemographic.hisParams.put("journey","3");
        if(health.getCheckedRadioButtonId()==R.id.health_yes)
            ModelDemographic.hisParams.put("health","1");
        else if(health.getCheckedRadioButtonId()==R.id.health_no)
            ModelDemographic.hisParams.put("health","2");
        else if(health.getCheckedRadioButtonId()==R.id.health_un)
            ModelDemographic.hisParams.put("health","3");
        if(quack.getCheckedRadioButtonId()==R.id.quack_yes)
            ModelDemographic.hisParams.put("quack","1");
        else if(quack.getCheckedRadioButtonId()==R.id.quack_no)
            ModelDemographic.hisParams.put("quack","2");
        else if(quack.getCheckedRadioButtonId()==R.id.quack_un)
            ModelDemographic.hisParams.put("quack","3");
        if (inv.getCheckedRadioButtonId()==R.id.inv_yes)
            ModelDemographic.riskParams.put("inv","1");
        else if (inv.getCheckedRadioButtonId()==R.id.inv_no)
            ModelDemographic.riskParams.put("inv","2");
        else if (inv.getCheckedRadioButtonId()==R.id.inv_un)
            ModelDemographic.riskParams.put("inv","3");
        ModelDemographic.hisParams.put("test_date",test_date.getText().toString());
        ModelDemographic.hisParams.put("journey_from",journey_from.getText().toString());
        ModelDemographic.hisParams.put("journey_to",journey_to.getText().toString());
        ModelDemographic.hisParams.put("health_name",health_name.getText().toString());
        ModelDemographic.hisParams.put("quack_name",quack_name.getText().toString());
        ModelDemographic.riskParams.put("inv_day",inv_day.getText().toString());
    }
    private boolean check(){
        if(test.getCheckedRadioButtonId()==R.id.test_yes && (test_date.getText()==null || test_date.getText().toString().isEmpty()))
            return false;
        if(journey.getCheckedRadioButtonId()==R.id.journey_yes && (journey_from.getText()==null || journey_from.getText().toString().isEmpty()))
            return false;
        if(journey.getCheckedRadioButtonId()==R.id.journey_yes && (journey_to.getText()==null || journey_to.getText().toString().isEmpty()))
            return false;
        if(health.getCheckedRadioButtonId()==R.id.health_yes && (health_name.getText()==null || health_name.getText().toString().isEmpty()))
            return false;
        if(quack.getCheckedRadioButtonId()==R.id.quack_yes && (quack_name.getText()==null || quack_name.getText().toString().isEmpty()))
            return false;
        if (Integer.parseInt(ModelDemographic.riskParams.get("inv")) == 1) {
            if(inv_day.getText().toString()==null || inv_day.getText().toString().isEmpty()) return false;
        }
        return true;
    }

    @Override
    public void getDate(String dat, TextView editText) {
        editText.setText(dat);
    }
}