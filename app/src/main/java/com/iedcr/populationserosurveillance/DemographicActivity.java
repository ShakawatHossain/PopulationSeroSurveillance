package com.iedcr.populationserosurveillance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.iedcr.populationserosurveillance.model.ModelDemographic;
import com.iedcr.populationserosurveillance.model.ModelFamilyMember;
import com.iedcr.populationserosurveillance.model.MyDB;

public class DemographicActivity extends AppCompatActivity {
    EditText h_no,h_name,h_mob,fam_qty,room_qty,toilet_qty,drink_src,present_mem,expense,water_puri_type_oth_txt;
    RadioGroup consent,kitchen,basin,drink_src_type;
    CheckBox boil,filter,water_puri_type_oth;
    Button submit;
    public static DemographicActivity demographicActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_demographic);
        init();
        demographicActivity = this;
    }
    private void init(){
//        init
        h_no=(EditText) findViewById(R.id.h_no);
        h_name=(EditText) findViewById(R.id.h_name);
        h_mob=(EditText) findViewById(R.id.h_mob);
        fam_qty=(EditText) findViewById(R.id.fam_qty);
        room_qty=(EditText) findViewById(R.id.room_qty);
        toilet_qty=(EditText) findViewById(R.id.toilet_qty);
        drink_src=(EditText) findViewById(R.id.drink_src);
        present_mem=(EditText) findViewById(R.id.present_mem);
        expense=(EditText) findViewById(R.id.expense);
        consent=(RadioGroup) findViewById(R.id.consent);
        consent.setOnCheckedChangeListener(checkedChangeListener);
        kitchen=(RadioGroup) findViewById(R.id.kitchen);
        basin=(RadioGroup) findViewById(R.id.basin);
        drink_src_type=(RadioGroup) findViewById(R.id.drink_src_type);
        submit = (Button) findViewById(R.id.submit);
        boil=(CheckBox) findViewById(R.id.boil);
        filter=(CheckBox) findViewById(R.id.filter);
        water_puri_type_oth=(CheckBox) findViewById(R.id.water_puri_type_oth);
        water_puri_type_oth_txt=(EditText) findViewById(R.id.water_puri_type_oth_txt);
//        load prev
        h_no.setText(ModelDemographic.demoParams.containsKey("h_no")?ModelDemographic.demoParams.get("h_no"):"");
        h_name.setText(ModelDemographic.demoParams.containsKey("h_name")?ModelDemographic.demoParams.get("h_name"):"");
        h_mob.setText(ModelDemographic.demoParams.containsKey("h_mob")?ModelDemographic.demoParams.get("h_mob"):"");
        fam_qty.setText(ModelDemographic.demoParams.containsKey("fam_qty")?ModelDemographic.demoParams.get("fam_qty"):"");
        room_qty.setText(ModelDemographic.demoParams.containsKey("room_qty")?ModelDemographic.demoParams.get("room_qty"):"");
        toilet_qty.setText(ModelDemographic.demoParams.containsKey("toilet_qty")?ModelDemographic.demoParams.get("toilet_qty"):"");
        drink_src.setText(ModelDemographic.demoParams.containsKey("drink_src")?ModelDemographic.demoParams.get("drink_src"):"");
        present_mem.setText(ModelDemographic.demoParams.containsKey("present_mem")?ModelDemographic.demoParams.get("present_mem"):"");
        expense.setText(ModelDemographic.demoParams.containsKey("expense")?ModelDemographic.demoParams.get("expense"):"");
        boil.setChecked(ModelDemographic.demoParams.containsKey("boil")&&Integer.parseInt(ModelDemographic.demoParams.get("boil"))==1);
        filter.setChecked(ModelDemographic.demoParams.containsKey("filter")&&Integer.parseInt(ModelDemographic.demoParams.get("filter"))==1);
        water_puri_type_oth.setChecked(ModelDemographic.demoParams.containsKey("water_puri_type_oth")&&Integer.parseInt(ModelDemographic.demoParams.get("water_puri_type_oth"))==1);
        water_puri_type_oth_txt.setText(ModelDemographic.demoParams.containsKey("water_puri_type_oth_txt")?ModelDemographic.demoParams.get("water_puri_type_oth_txt"):"");
        if(ModelDemographic.demoParams.containsKey("consent")) {
            int consent_value = Integer.parseInt(ModelDemographic.demoParams.get("consent"));
            if (consent_value == 1) {
                consent.check(R.id.consent_yes);
            } else {
                consent.check(R.id.consent_no);
            }
        }
        if(ModelDemographic.demoParams.containsKey("kitchen")) {
            int kitchen_value = Integer.parseInt(ModelDemographic.demoParams.get("kitchen"));
            if (kitchen_value == 1) {
                kitchen.check(R.id.kitchen_yes);
            } else {
                kitchen.check(R.id.kitchen_no);
            }
        }
        if(ModelDemographic.demoParams.containsKey("basin")) {
            int basin_value = Integer.parseInt(ModelDemographic.demoParams.get("basin"));
            if (basin_value == 1) {
                basin.check(R.id.basin_yes);
            } else {
                basin.check(R.id.basin_no);
            }
        }
        if(ModelDemographic.demoParams.containsKey("drink_src_type")) {
            int dst = Integer.parseInt(ModelDemographic.demoParams.get("drink_src_type"));
            if (dst == 1) {
                drink_src_type.check(R.id.supply);
                if(drink_src.getVisibility()==View.VISIBLE)drink_src.setVisibility(View.GONE);
            }else if (dst == 2) {
                drink_src_type.check(R.id.tube);
                if(drink_src.getVisibility()==View.VISIBLE)drink_src.setVisibility(View.GONE);
            } else if (dst == 3)  {
                basin.check(R.id.drink_src_type_oth);
                if(drink_src.getVisibility()!=View.VISIBLE)drink_src.setVisibility(View.VISIBLE);
            }
        }
        drink_src_type.setOnCheckedChangeListener(checkedChangeListener);
        submit.setOnClickListener(this.clickListener);
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.submit){
                setValues();
                if(!check()){
                    Toast.makeText(DemographicActivity.this,"Correct Input Please",Toast.LENGTH_LONG).show();
                    return;
                }else if(Integer.parseInt(ModelDemographic.demoParams.get("consent"))==2){
                    ModelDemographic.ptInfoParams.clear();
                    ModelDemographic.resParams.clear();
                    ModelDemographic.hisParams.clear();
                    ModelDemographic.pastDisParams.clear();
                    ModelDemographic.treatParams.clear();
                    ModelDemographic.dengueParams.clear();
                    ModelDemographic.comParams.clear();
                    ModelDemographic.riskParams.clear();
                    ModelFamilyMember.clearFamilyMemberContents();
                    MyDB myDB = new MyDB(DemographicActivity.this);
                    myDB.insert();
                    ModelDemographic.clear();
                    startActivity(new Intent(DemographicActivity.this,ListActivity.class));
                    MainActivity.mainActivity.finish();
                    finish();
                    return;
                }
                startActivity(new Intent(DemographicActivity.this,FamilyMemberActivity.class));
                return;
            }
        }
    };
    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (group.getId()==consent.getId()){
                if (checkedId==R.id.consent_no){
                    if( ((CardView) findViewById(R.id.pos_con)).getVisibility()==View.VISIBLE)
                        ((CardView) findViewById(R.id.pos_con)).setVisibility(View.GONE);
                }else if (checkedId==R.id.consent_yes){
                    if( ((CardView) findViewById(R.id.pos_con)).getVisibility()!=View.VISIBLE)
                        ((CardView) findViewById(R.id.pos_con)).setVisibility(View.VISIBLE);
                }
            }
            if (group.getId()==drink_src_type.getId()){
                if (checkedId==R.id.supply){
                    ModelDemographic.demoParams.put("drink_src_type","1");
                    if(drink_src.getVisibility()==View.VISIBLE)drink_src.setVisibility(View.GONE);
                }else if(checkedId==R.id.tube){
                    ModelDemographic.demoParams.put("drink_src_type","2");
                    if(drink_src.getVisibility()==View.VISIBLE)drink_src.setVisibility(View.GONE);
                }else if(checkedId==R.id.drink_src_type_oth){
                    ModelDemographic.demoParams.put("drink_src_type","3");
                    if(drink_src.getVisibility()!=View.VISIBLE)drink_src.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    private boolean check(){
//        if(!ModelDemographic.demoParams.containsKey("h_no")) return false;
        if(!ModelDemographic.demoParams.containsKey("h_name")) return false;
        if(!ModelDemographic.demoParams.containsKey("h_mob") || ModelDemographic.demoParams.get("h_mob").length()!=11) return false;
        if(!ModelDemographic.demoParams.containsKey("consent")) return false;
        if(Integer.parseInt(ModelDemographic.demoParams.get("consent"))==2) return true;
        if(!ModelDemographic.demoParams.containsKey("fam_qty")) return false;
        if(!ModelDemographic.demoParams.containsKey("room_qty")) return false;
        if(!ModelDemographic.demoParams.containsKey("toilet_qty")) return false;
        if(!ModelDemographic.demoParams.containsKey("drink_src_type")){ return false;}
        else{ if(!ModelDemographic.demoParams.containsKey("drink_src")) return false;}
        if(!ModelDemographic.demoParams.containsKey("present_mem")) return false;
        if(!ModelDemographic.demoParams.containsKey("expense")) return false;
        if(!ModelDemographic.demoParams.containsKey("kitchen")) return false;
        if(!ModelDemographic.demoParams.containsKey("basin")) return false;
        return true;
    }
    private void setValues(){
        if (fam_qty.getText().toString()!=null && !fam_qty.getText().toString().isEmpty())
            ModelDemographic.fam_qty = Integer.parseInt(fam_qty.getText().toString());
        ModelDemographic.demoParams.put("h_no",h_no.getText().toString());
        ModelDemographic.demoParams.put("h_name",h_name.getText().toString());
        ModelDemographic.demoParams.put("h_mob",h_mob.getText().toString());
        ModelDemographic.demoParams.put("fam_qty",fam_qty.getText().toString());
        ModelDemographic.demoParams.put("room_qty",room_qty.getText().toString());
        ModelDemographic.demoParams.put("toilet_qty",toilet_qty.getText().toString());
        ModelDemographic.demoParams.put("drink_src",drink_src.getText().toString());
        ModelDemographic.demoParams.put("present_mem",present_mem.getText().toString());
        ModelDemographic.demoParams.put("expense",expense.getText().toString());
        ModelDemographic.demoParams.put("boil",boil.isChecked()?"1":"2");
        ModelDemographic.demoParams.put("filter",filter.isChecked()?"1":"2");
        ModelDemographic.demoParams.put("water_puri_type_oth",water_puri_type_oth.isChecked()?"1":"2");
        ModelDemographic.demoParams.put("water_puri_type_oth_txt",water_puri_type_oth_txt.getText().toString());
        if(consent.getCheckedRadioButtonId()==R.id.consent_yes)
            ModelDemographic.demoParams.put("consent","1");
        else if(consent.getCheckedRadioButtonId()==R.id.consent_no)
            ModelDemographic.demoParams.put("consent","2");
        if(basin.getCheckedRadioButtonId()==R.id.basin_yes)
            ModelDemographic.demoParams.put("basin","1");
        else if(basin.getCheckedRadioButtonId()==R.id.basin_no)
            ModelDemographic.demoParams.put("basin","2");
        if(kitchen.getCheckedRadioButtonId()==R.id.kitchen_yes)
            ModelDemographic.demoParams.put("kitchen","1");
        else if(kitchen.getCheckedRadioButtonId()==R.id.kitchen_no)
            ModelDemographic.demoParams.put("kitchen","2");
    }
}