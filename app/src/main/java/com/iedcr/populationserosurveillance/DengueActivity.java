package com.iedcr.populationserosurveillance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.Toast;

import com.iedcr.populationserosurveillance.model.ModelDemographic;

public class DengueActivity extends AppCompatActivity {
    RadioGroup dengue,dengue_day_type,dengue_how,dengue_hos,cikon,cikon_day_type,cikon_how,cikon_hos,preg,trim;
    EditText dengue_day,cikon_day;
    Button submit;
    public static DengueActivity dengueActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_dengue);
        init();
        dengueActivity = this;
    }
    private void init(){
//Init
        dengue=(RadioGroup) findViewById(R.id.dengue);
        dengue.setOnCheckedChangeListener(checkedChangeListener);
        dengue_day_type=(RadioGroup) findViewById(R.id.dengue_day_type);
        dengue_how=(RadioGroup) findViewById(R.id.dengue_how);
        dengue_hos=(RadioGroup) findViewById(R.id.dengue_hos);
        cikon=(RadioGroup) findViewById(R.id.cikon);
        cikon.setOnCheckedChangeListener(checkedChangeListener);
        cikon_day_type=(RadioGroup) findViewById(R.id.cikon_day_type);
        cikon_how=(RadioGroup) findViewById(R.id.cikon_how);
        cikon_hos=(RadioGroup) findViewById(R.id.cikon_hos);
        preg=(RadioGroup) findViewById(R.id.preg);
        preg.setOnCheckedChangeListener(checkedChangeListener);
        trim=(RadioGroup) findViewById(R.id.trim);
        dengue_day=(EditText) findViewById(R.id.dengue_day);
        cikon_day=(EditText) findViewById(R.id.cikon_day);
        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(clickListener);
//        Prev Value
        if(ModelDemographic.dengueParams.containsKey("dengue")){
            if(Integer.parseInt(ModelDemographic.dengueParams.get("dengue"))==1){
                dengue.check(R.id.dengue_yes);
                if (((CardView) findViewById(R.id.dengue_card)).getVisibility()!=View.VISIBLE)
                    ((CardView) findViewById(R.id.dengue_card)).setVisibility(View.VISIBLE);
            }
            else if(Integer.parseInt(ModelDemographic.dengueParams.get("dengue"))==2){
                dengue.check(R.id.dengue_no);
                if (((CardView) findViewById(R.id.dengue_card)).getVisibility()==View.VISIBLE)
                    ((CardView) findViewById(R.id.dengue_card)).setVisibility(View.GONE);
            }
            else if(Integer.parseInt(ModelDemographic.dengueParams.get("dengue"))==3){
                dengue.check(R.id.dengue_un);
                if (((CardView) findViewById(R.id.dengue_card)).getVisibility()==View.VISIBLE)
                    ((CardView) findViewById(R.id.dengue_card)).setVisibility(View.GONE);
            }
        }
        if(ModelDemographic.dengueParams.containsKey("dengue_day_type")){
            if(Integer.parseInt(ModelDemographic.dengueParams.get("dengue_day_type"))==1){
                dengue_day_type.check(R.id.dengue_year);
            }
            else if(Integer.parseInt(ModelDemographic.dengueParams.get("dengue_day_type"))==2){
                dengue_day_type.check(R.id.dengue_mth);
            }
        }
        if(ModelDemographic.dengueParams.containsKey("dengue_how")){
            if(Integer.parseInt(ModelDemographic.dengueParams.get("dengue_how"))==1){
                dengue_how.check(R.id.dengue_how_yes);

            }
            else if(Integer.parseInt(ModelDemographic.dengueParams.get("dengue_how"))==2){
                dengue_how.check(R.id.dengue_how_no);
            }
            else if(Integer.parseInt(ModelDemographic.dengueParams.get("dengue_how"))==3){
                dengue_how.check(R.id.dengue_how_un);
            }
        }
        if(ModelDemographic.dengueParams.containsKey("dengue_hos")){
            if(Integer.parseInt(ModelDemographic.dengueParams.get("dengue_hos"))==1){
                dengue_hos.check(R.id.dengue_hos_yes);

            }
            else if(Integer.parseInt(ModelDemographic.dengueParams.get("dengue_hos"))==2){
                dengue_hos.check(R.id.dengue_hos_no);
            }
            else if(Integer.parseInt(ModelDemographic.dengueParams.get("dengue_hos"))==3){
                dengue_hos.check(R.id.dengue_hos_un);
            }
        }
        if(ModelDemographic.dengueParams.containsKey("cikon")){
            if(Integer.parseInt(ModelDemographic.dengueParams.get("cikon"))==1){
                cikon.check(R.id.cikon_yes);
                if (((CardView) findViewById(R.id.cikon_card)).getVisibility()!=View.VISIBLE)
                    ((CardView) findViewById(R.id.cikon_card)).setVisibility(View.VISIBLE);
            }
            else if(Integer.parseInt(ModelDemographic.dengueParams.get("cikon"))==2){
                cikon.check(R.id.cikon_no);
                if (((CardView) findViewById(R.id.cikon_card)).getVisibility()==View.VISIBLE)
                    ((CardView) findViewById(R.id.cikon_card)).setVisibility(View.GONE);
            }
            else if(Integer.parseInt(ModelDemographic.dengueParams.get("cikon"))==3){
                cikon.check(R.id.cikon_un);
                if (((CardView) findViewById(R.id.cikon_card)).getVisibility()==View.VISIBLE)
                    ((CardView) findViewById(R.id.cikon_card)).setVisibility(View.GONE);
            }
        }
        if(ModelDemographic.dengueParams.containsKey("cikon_day_type")){
            if(Integer.parseInt(ModelDemographic.dengueParams.get("cikon_day_type"))==1){
                cikon_day_type.check(R.id.cikon_year);
            }
            else if(Integer.parseInt(ModelDemographic.dengueParams.get("cikon_day_type"))==2){
                cikon_day_type.check(R.id.cikon_mth);
            }
        }
        if(ModelDemographic.dengueParams.containsKey("cikon_how")){
            if(Integer.parseInt(ModelDemographic.dengueParams.get("cikon_how"))==1){
                cikon_how.check(R.id.cikon_how_yes);

            }
            else if(Integer.parseInt(ModelDemographic.dengueParams.get("cikon_how"))==2){
                cikon_how.check(R.id.cikon_how_no);
            }
            else if(Integer.parseInt(ModelDemographic.dengueParams.get("cikon_how"))==3){
                cikon_how.check(R.id.cikon_how_un);
            }
        }
        if(ModelDemographic.dengueParams.containsKey("cikon_hos")){
            if(Integer.parseInt(ModelDemographic.dengueParams.get("cikon_hos"))==1){
                cikon_hos.check(R.id.cikon_hos_yes);

            }
            else if(Integer.parseInt(ModelDemographic.dengueParams.get("cikon_hos"))==2){
                cikon_hos.check(R.id.cikon_hos_no);
            }
            else if(Integer.parseInt(ModelDemographic.dengueParams.get("cikon_hos"))==3){
                cikon_hos.check(R.id.cikon_hos_un);
            }
        }
        if(ModelDemographic.dengueParams.containsKey("preg")){
            if(Integer.parseInt(ModelDemographic.dengueParams.get("preg"))==1){
                preg.check(R.id.preg_yes);
                if (((TableRow) findViewById(R.id.trim_card)).getVisibility()!=View.VISIBLE)
                    ((TableRow) findViewById(R.id.trim_card)).setVisibility(View.VISIBLE);
            }
            else if(Integer.parseInt(ModelDemographic.dengueParams.get("preg"))==2){
                preg.check(R.id.preg_no);
                if (((TableRow) findViewById(R.id.trim_card)).getVisibility()==View.VISIBLE)
                    ((TableRow) findViewById(R.id.trim_card)).setVisibility(View.GONE);
            }
        }
        if(ModelDemographic.dengueParams.containsKey("trim")){
            switch (Integer.parseInt(ModelDemographic.dengueParams.get("trim"))){
                case 1:
                    trim.check(R.id.trim_1);
                    break;
                case 2:
                    trim.check(R.id.trim_2);
                    break;
                case 3:
                    trim.check(R.id.trim_3);
                    break;
                case 4:
                    trim.check(R.id.trim_4);
                    break;
            }
        }
        dengue_day.setText(ModelDemographic.dengueParams.containsKey("dengue_day")?ModelDemographic.dengueParams.get("dengue_day"):"");
        cikon_day.setText(ModelDemographic.dengueParams.containsKey("cikon_day")?ModelDemographic.dengueParams.get("cikon_day"):"");
    }
    private void setValues(){
        if (dengue.getCheckedRadioButtonId()==R.id.dengue_yes)
            ModelDemographic.dengueParams.put("dengue","1");
        else if (dengue.getCheckedRadioButtonId()==R.id.dengue_no)
            ModelDemographic.dengueParams.put("dengue","2");
        else if (dengue.getCheckedRadioButtonId()==R.id.dengue_un)
            ModelDemographic.dengueParams.put("dengue","3");
        if (dengue_day_type.getCheckedRadioButtonId()==R.id.dengue_year)
            ModelDemographic.dengueParams.put("dengue_day_type","1");
        else if (dengue_day_type.getCheckedRadioButtonId()==R.id.dengue_mth)
            ModelDemographic.dengueParams.put("dengue_day_type","2");
        if (dengue_how.getCheckedRadioButtonId()==R.id.dengue_how_yes)
            ModelDemographic.dengueParams.put("dengue_how","1");
        else if (dengue_how.getCheckedRadioButtonId()==R.id.dengue_how_no)
            ModelDemographic.dengueParams.put("dengue_how","2");
        else if (dengue_how.getCheckedRadioButtonId()==R.id.dengue_how_un)
            ModelDemographic.dengueParams.put("dengue_how","3");
        if (dengue_hos.getCheckedRadioButtonId()==R.id.dengue_hos_yes)
            ModelDemographic.dengueParams.put("dengue_hos","1");
        else if (dengue_hos.getCheckedRadioButtonId()==R.id.dengue_hos_no)
            ModelDemographic.dengueParams.put("dengue_hos","2");
        else if (dengue_hos.getCheckedRadioButtonId()==R.id.dengue_hos_un)
            ModelDemographic.dengueParams.put("dengue_hos","3");
        if (cikon.getCheckedRadioButtonId()==R.id.cikon_yes)
            ModelDemographic.dengueParams.put("cikon","1");
        else if (cikon.getCheckedRadioButtonId()==R.id.cikon_no)
            ModelDemographic.dengueParams.put("cikon","2");
        else if (cikon.getCheckedRadioButtonId()==R.id.cikon_un)
            ModelDemographic.dengueParams.put("cikon","3");
        if (cikon_day_type.getCheckedRadioButtonId()==R.id.cikon_year)
            ModelDemographic.dengueParams.put("cikon_day_type","1");
        else if (cikon_day_type.getCheckedRadioButtonId()==R.id.cikon_mth)
            ModelDemographic.dengueParams.put("cikon_day_type","2");
        if (cikon_how.getCheckedRadioButtonId()==R.id.cikon_how_yes)
            ModelDemographic.dengueParams.put("cikon_how","1");
        else if (cikon_how.getCheckedRadioButtonId()==R.id.cikon_how_no)
            ModelDemographic.dengueParams.put("cikon_how","2");
        else if (cikon_how.getCheckedRadioButtonId()==R.id.cikon_how_un)
            ModelDemographic.dengueParams.put("cikon_how","3");
        if (cikon_hos.getCheckedRadioButtonId()==R.id.cikon_hos_yes)
            ModelDemographic.dengueParams.put("cikon_hos","1");
        else if (cikon_hos.getCheckedRadioButtonId()==R.id.cikon_hos_no)
            ModelDemographic.dengueParams.put("cikon_hos","2");
        else if (cikon_hos.getCheckedRadioButtonId()==R.id.cikon_hos_un)
            ModelDemographic.dengueParams.put("cikon_hos","3");
        if (preg.getCheckedRadioButtonId()==R.id.preg_yes)
            ModelDemographic.dengueParams.put("preg","1");
        else if (preg.getCheckedRadioButtonId()==R.id.preg_no)
            ModelDemographic.dengueParams.put("preg","2");
        if (trim.getCheckedRadioButtonId()==R.id.trim_1)
            ModelDemographic.dengueParams.put("trim","1");
        else if (trim.getCheckedRadioButtonId()==R.id.trim_2)
            ModelDemographic.dengueParams.put("trim","2");
        else if (trim.getCheckedRadioButtonId()==R.id.trim_3)
            ModelDemographic.dengueParams.put("trim","3");
        else if (trim.getCheckedRadioButtonId()==R.id.trim_4)
            ModelDemographic.dengueParams.put("trim","4");
        ModelDemographic.dengueParams.put("dengue_day",dengue_day.getText().toString());
        ModelDemographic.dengueParams.put("cikon_day",cikon_day.getText().toString());
    }
    private boolean check(){
        if (!ModelDemographic.dengueParams.containsKey("dengue")) return false;
        if (!ModelDemographic.dengueParams.containsKey("cikon")) return false;
        if (ModelDemographic.dengueParams.containsKey("preg")&&
                Integer.parseInt(ModelDemographic.dengueParams.get("preg"))==1&&
                !ModelDemographic.dengueParams.containsKey("trim"))
            return false;

        return true;
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==submit.getId()){
                setValues();
                if (check()){
                    startActivity(new Intent(DengueActivity.this,ComorbidityActivity.class));
                }else {
                    Toast.makeText(DengueActivity.this,"Check Input",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(group.getId()==dengue.getId()){
                if(checkedId==R.id.dengue_yes){
                    if (((CardView) findViewById(R.id.dengue_card)).getVisibility()!=View.VISIBLE)
                        ((CardView) findViewById(R.id.dengue_card)).setVisibility(View.VISIBLE);
                }else{
                    if (((CardView) findViewById(R.id.dengue_card)).getVisibility()==View.VISIBLE)
                        ((CardView) findViewById(R.id.dengue_card)).setVisibility(View.GONE);
                }
            }
            if(group.getId()==cikon.getId()){
                if(checkedId==R.id.cikon_yes){
                    if (((CardView) findViewById(R.id.cikon_card)).getVisibility()!=View.VISIBLE)
                        ((CardView) findViewById(R.id.cikon_card)).setVisibility(View.VISIBLE);
                }else{
                    if (((CardView) findViewById(R.id.cikon_card)).getVisibility()==View.VISIBLE)
                        ((CardView) findViewById(R.id.cikon_card)).setVisibility(View.GONE);
                }
            }
            if(group.getId()==preg.getId()){
                if(checkedId==R.id.preg_yes){
                    if (((TableRow) findViewById(R.id.trim_card)).getVisibility()!=View.VISIBLE)
                        ((TableRow) findViewById(R.id.trim_card)).setVisibility(View.VISIBLE);
                }else{
                    if (((TableRow) findViewById(R.id.trim_card)).getVisibility()==View.VISIBLE)
                        ((TableRow) findViewById(R.id.trim_card)).setVisibility(View.GONE);
                }
            }
        }
    };
}