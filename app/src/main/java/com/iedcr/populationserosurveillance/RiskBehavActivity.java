package com.iedcr.populationserosurveillance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.iedcr.populationserosurveillance.fragments.CalenderDialog;
import com.iedcr.populationserosurveillance.iview.CalenderInterface;
import com.iedcr.populationserosurveillance.model.ModelDemographic;
import com.iedcr.populationserosurveillance.model.ModelFamilyMember;
import com.iedcr.populationserosurveillance.model.MyDB;

public class RiskBehavActivity extends AppCompatActivity implements CalenderInterface {
    RadioGroup mask,mask_type,hwash,tsec,cover,ctsec,cmeet,cmeet_place,pt,sdist,net_time,media,htips,kit;
    EditText mask_oth_txt,mask_type_oth_txt,hwash_freq,cover_by_oth,cmeet_oth_txt,pt_by_oth,mbite_oth,net_oth_txt,inf_oth,name,kitorg_name;
    TextView cmeet_day;
    CheckBox elbow,clth,palm,cough,touch,shake,inf,food,govt,pvt,net,coil,dhup,mbite_no;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_risk_behav);
        init();
    }
    private void init(){
//        Init
        mask=(RadioGroup) findViewById(R.id.mask);
        mask_type=(RadioGroup) findViewById(R.id.mask_type);
        hwash=(RadioGroup) findViewById(R.id.hwash);
        tsec=(RadioGroup) findViewById(R.id.tsec);
        cover=(RadioGroup) findViewById(R.id.cover);
        ctsec=(RadioGroup) findViewById(R.id.ctsec);

        cmeet=(RadioGroup) findViewById(R.id.cmeet);
        cmeet_place=(RadioGroup) findViewById(R.id.cmeet_place);
        pt=(RadioGroup) findViewById(R.id.pt);
        sdist=(RadioGroup) findViewById(R.id.sdist);
        net_time=(RadioGroup) findViewById(R.id.net_time);
        media=(RadioGroup) findViewById(R.id.media);
        htips=(RadioGroup) findViewById(R.id.htips);
        kit=(RadioGroup) findViewById(R.id.kit);
        mask.setOnCheckedChangeListener(checkedChangeListener);
        mask_type.setOnCheckedChangeListener(checkedChangeListener);
        hwash.setOnCheckedChangeListener(checkedChangeListener);
        cover.setOnCheckedChangeListener(checkedChangeListener);

        cmeet.setOnCheckedChangeListener(checkedChangeListener);
        cmeet_place.setOnCheckedChangeListener(checkedChangeListener);
        net_time.setOnCheckedChangeListener(checkedChangeListener);
        pt.setOnCheckedChangeListener(checkedChangeListener);
        htips.setOnCheckedChangeListener(checkedChangeListener);
        kit.setOnCheckedChangeListener(checkedChangeListener);
        mask_oth_txt=(EditText) findViewById(R.id.mask_oth_txt);
        mask_type_oth_txt=(EditText) findViewById(R.id.mask_type_oth_txt);
        hwash_freq=(EditText) findViewById(R.id.hwash_freq);
        cover_by_oth=(EditText) findViewById(R.id.cover_by_oth);

        cmeet_oth_txt=(EditText) findViewById(R.id.cmeet_oth_txt);
        pt_by_oth=(EditText) findViewById(R.id.pt_by_oth);
        mbite_oth=(EditText) findViewById(R.id.mbite_oth);
        net_oth_txt=(EditText) findViewById(R.id.net_oth_txt);
        inf_oth=(EditText) findViewById(R.id.inf_oth);
        name=(EditText) findViewById(R.id.name);
        kitorg_name=(EditText) findViewById(R.id.kitorg_name);
        cmeet_day=(TextView) findViewById(R.id.cmeet_day);
        cmeet_day.setOnClickListener(clickListener);
        elbow=(CheckBox) findViewById(R.id.elbow);
        clth=(CheckBox) findViewById(R.id.clth);
        palm=(CheckBox) findViewById(R.id.palm);
        cough=(CheckBox) findViewById(R.id.cough);
        touch=(CheckBox) findViewById(R.id.touch);
        shake=(CheckBox) findViewById(R.id.shake);
        inf=(CheckBox) findViewById(R.id.inf);
        food=(CheckBox) findViewById(R.id.food);
        govt=(CheckBox) findViewById(R.id.govt);
        pvt=(CheckBox) findViewById(R.id.pvt);
        net=(CheckBox) findViewById(R.id.net);
        net.setOnCheckedChangeListener(comChangeListener);
        coil=(CheckBox) findViewById(R.id.coil);
        dhup=(CheckBox) findViewById(R.id.dhup);
        mbite_no=(CheckBox) findViewById(R.id.mbite_no);
        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(clickListener);
//        PrevValue
        if(ModelDemographic.riskParams.containsKey("mask")) {
            if (Integer.parseInt(ModelDemographic.riskParams.get("mask")) == 1) {
                mask.check(R.id.mask_always);
                if (((TextInputLayout) findViewById(R.id.mask_oth_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.mask_oth_layout)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("mask")) == 2) {
                mask.check(R.id.mask_some);
                if (((TextInputLayout) findViewById(R.id.mask_oth_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.mask_oth_layout)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("mask")) == 3) {
                mask.check(R.id.mask_no);
                if (((TextInputLayout) findViewById(R.id.mask_oth_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.mask_oth_layout)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("mask")) == 4) {
                mask.check(R.id.mask_oth);
                if (((TextInputLayout) findViewById(R.id.mask_oth_layout)).getVisibility() != View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.mask_oth_layout)).setVisibility(View.VISIBLE);
            }
        }
        if(ModelDemographic.riskParams.containsKey("mask_type")){
            if (Integer.parseInt(ModelDemographic.riskParams.get("mask_type")) == 1) {
                mask_type.check(R.id.surge);
                if (((TextInputLayout) findViewById(R.id.mask_type_oth_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.mask_type_oth_layout)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("mask_type")) == 2) {
                mask_type.check(R.id.cloth);
                if (((TextInputLayout) findViewById(R.id.mask_type_oth_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.mask_type_oth_layout)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("mask_type")) == 3) {
                mask_type.check(R.id.mask_type_oth);
                if (((TextInputLayout) findViewById(R.id.mask_type_oth_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.mask_type_oth_layout)).setVisibility(View.GONE);
            }
        }
        if(ModelDemographic.riskParams.containsKey("hwash")){
            if (Integer.parseInt(ModelDemographic.riskParams.get("hwash")) == 1) {
                hwash.check(R.id.hwash_yes);
                if (((TextInputLayout) findViewById(R.id.hwash_layout)).getVisibility() != View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.hwash_layout)).setVisibility(View.VISIBLE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("hwash")) == 2) {
                hwash.check(R.id.hwash_no);
                if (((TextInputLayout) findViewById(R.id.hwash_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.hwash_layout)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("hwash")) == 3) {
                hwash.check(R.id.hwash_un);
                if (((TextInputLayout) findViewById(R.id.hwash_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.hwash_layout)).setVisibility(View.GONE);
            }
        }
        if(ModelDemographic.riskParams.containsKey("tsec")){
            if (Integer.parseInt(ModelDemographic.riskParams.get("tsec")) == 1) {
                tsec.check(R.id.tsec_yes);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("tsec")) == 2) {
                tsec.check(R.id.tsec_no);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("tsec")) == 3) {
                tsec.check(R.id.tsec_un);
            }
        }
        if(ModelDemographic.riskParams.containsKey("cover")){
            if (Integer.parseInt(ModelDemographic.riskParams.get("cover")) == 1) {
                cover.check(R.id.cover_yes);
                if (((LinearLayout) findViewById(R.id.cover_lin)).getVisibility() != View.VISIBLE)
                    ((LinearLayout) findViewById(R.id.cover_lin)).setVisibility(View.VISIBLE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("cover")) == 2) {
                cover.check(R.id.cover_no);
                if (((LinearLayout) findViewById(R.id.cover_lin)).getVisibility() == View.VISIBLE)
                    ((LinearLayout) findViewById(R.id.cover_lin)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("cover")) == 3) {
                cover.check(R.id.cover_un);
                if (((LinearLayout) findViewById(R.id.cover_lin)).getVisibility() == View.VISIBLE)
                    ((LinearLayout) findViewById(R.id.cover_lin)).setVisibility(View.GONE);
            }
        }
        if(ModelDemographic.riskParams.containsKey("ctsec")){
            if (Integer.parseInt(ModelDemographic.riskParams.get("ctsec")) == 1) {
                ctsec.check(R.id.ctsec_yes);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("ctsec")) == 2) {
                ctsec.check(R.id.ctsec_no);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("ctsec")) == 3) {
                ctsec.check(R.id.ctsec_un);
            }
        }

        if(ModelDemographic.riskParams.containsKey("cmeet")){
            if (Integer.parseInt(ModelDemographic.riskParams.get("cmeet")) == 1) {
                cmeet.check(R.id.cmeet_yes);
                if (((TableRow) findViewById(R.id.cmeet_row)).getVisibility() != View.VISIBLE)
                    ((TableRow) findViewById(R.id.cmeet_row)).setVisibility(View.VISIBLE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("cmeet")) == 2) {
                cmeet.check(R.id.cmeet_no);
                if (((TableRow) findViewById(R.id.cmeet_row)).getVisibility() == View.VISIBLE)
                    ((TableRow) findViewById(R.id.cmeet_row)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("cmeet")) == 3) {
                cmeet.check(R.id.cmeet_un);
                if (((TableRow) findViewById(R.id.cmeet_row)).getVisibility() == View.VISIBLE)
                    ((TableRow) findViewById(R.id.cmeet_row)).setVisibility(View.GONE);
            }
        }
        if(ModelDemographic.riskParams.containsKey("cmeet_place")){
            if (Integer.parseInt(ModelDemographic.riskParams.get("cmeet_place")) == 1) {
                cmeet_place.check(R.id.cmeet_place_home);
                if (((TextInputLayout) findViewById(R.id.cmeet_oth_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.cmeet_oth_layout)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("cmeet_place")) == 2) {
                cmeet_place.check(R.id.cmeet_place_hos);
                if (((TextInputLayout) findViewById(R.id.cmeet_oth_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.cmeet_oth_layout)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("cmeet_place")) == 3) {
                cmeet_place.check(R.id.cmeet_place_ofc);
                if (((TextInputLayout) findViewById(R.id.cmeet_oth_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.cmeet_oth_layout)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("cmeet_place")) == 4) {
                cmeet_place.check(R.id.cmeet_place_team);
                if (((TextInputLayout) findViewById(R.id.cmeet_oth_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.cmeet_oth_layout)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("cmeet_place")) == 5) {
                cmeet_place.check(R.id.cmeet_place_school);
                if (((TextInputLayout) findViewById(R.id.cmeet_oth_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.cmeet_oth_layout)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("cmeet_place")) == 6) {
                cmeet_place.check(R.id.cmeet_place_unk);
                if (((TextInputLayout) findViewById(R.id.cmeet_oth_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.cmeet_oth_layout)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("cmeet_place")) == 7) {
                cmeet_place.check(R.id.cmeet_place_oth);
                if (((TextInputLayout) findViewById(R.id.cmeet_oth_layout)).getVisibility() != View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.cmeet_oth_layout)).setVisibility(View.VISIBLE);
            }
        }
        if(ModelDemographic.riskParams.containsKey("pt")){
            if (Integer.parseInt(ModelDemographic.riskParams.get("pt")) == 1) {
                pt.check(R.id.pt_yes);
                if (((LinearLayout) findViewById(R.id.pt_lin)).getVisibility() != View.VISIBLE)
                    ((LinearLayout) findViewById(R.id.pt_lin)).setVisibility(View.VISIBLE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("pt")) == 2) {
                pt.check(R.id.pt_no);
                if (((LinearLayout) findViewById(R.id.pt_lin)).getVisibility() == View.VISIBLE)
                    ((LinearLayout) findViewById(R.id.pt_lin)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("pt")) == 3) {
                pt.check(R.id.pt_un);
                if (((LinearLayout) findViewById(R.id.pt_lin)).getVisibility() == View.VISIBLE)
                    ((LinearLayout) findViewById(R.id.pt_lin)).setVisibility(View.GONE);
            }
        }
        if(ModelDemographic.riskParams.containsKey("sdist")){
            if (Integer.parseInt(ModelDemographic.riskParams.get("sdist")) == 1) {
                sdist.check(R.id.sdist1);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("sdist")) == 2) {
                sdist.check(R.id.sdist2);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("sdist")) == 3) {
                sdist.check(R.id.sdist3);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("sdist")) == 4) {
                sdist.check(R.id.sdist4);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("sdist")) == 0) {
                sdist.check(R.id.sdist0);
            }
        }
        if(ModelDemographic.riskParams.containsKey("net_time")){
            if (Integer.parseInt(ModelDemographic.riskParams.get("net_time")) == 1) {
                net_time.check(R.id.day);
                if (((TextInputLayout) findViewById(R.id.net_oth_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.net_oth_layout)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("net_time")) == 2) {
                net_time.check(R.id.night);
                if (((TextInputLayout) findViewById(R.id.net_oth_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.net_oth_layout)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("net_time")) == 3) {
                net_time.check(R.id.all);
                if (((TextInputLayout) findViewById(R.id.net_oth_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.net_oth_layout)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("net_time")) == 4) {
                net_time.check(R.id.net_oth);
                if (((TextInputLayout) findViewById(R.id.net_oth_layout)).getVisibility() != View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.net_oth_layout)).setVisibility(View.VISIBLE);
            }
        }
        if(ModelDemographic.riskParams.containsKey("media")){
            if (Integer.parseInt(ModelDemographic.riskParams.get("media")) == 1) {
                media.check(R.id.media_yes);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("media")) == 2) {
                media.check(R.id.media_no);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("media")) == 3) {
                media.check(R.id.media_un);
            }
        }
        if(ModelDemographic.riskParams.containsKey("htips")){
            if (Integer.parseInt(ModelDemographic.riskParams.get("htips")) == 1) {
                htips.check(R.id.htips_yes);
                if (((LinearLayout) findViewById(R.id.htips_row)).getVisibility() != View.VISIBLE)
                    ((LinearLayout) findViewById(R.id.htips_row)).setVisibility(View.VISIBLE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("htips")) == 2) {
                htips.check(R.id.htips_no);
                if (((LinearLayout) findViewById(R.id.htips_row)).getVisibility() == View.VISIBLE)
                    ((LinearLayout) findViewById(R.id.htips_row)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("htips")) == 3) {
                htips.check(R.id.htips_un);
                if (((LinearLayout) findViewById(R.id.htips_row)).getVisibility() == View.VISIBLE)
                    ((LinearLayout) findViewById(R.id.htips_row)).setVisibility(View.GONE);
            }
        }
        if(ModelDemographic.riskParams.containsKey("kit")){
            if (Integer.parseInt(ModelDemographic.riskParams.get("kit")) == 1) {
                kit.check(R.id.kit_yes);
                if (((TextInputLayout) findViewById(R.id.kit_layout)).getVisibility() != View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.kit_layout)).setVisibility(View.VISIBLE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("kit")) == 2) {
                kit.check(R.id.kit_no);
                if (((TextInputLayout) findViewById(R.id.kit_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.kit_layout)).setVisibility(View.GONE);
            }else if (Integer.parseInt(ModelDemographic.riskParams.get("kit")) == 3) {
                kit.check(R.id.kit_un);
                if (((TextInputLayout) findViewById(R.id.kit_layout)).getVisibility() == View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.kit_layout)).setVisibility(View.GONE);
            }
        }
        mask_oth_txt.setText(ModelDemographic.riskParams.containsKey("mask_oth_txt")?ModelDemographic.riskParams.get("mask_oth_txt"):"");
        mask_type_oth_txt.setText(ModelDemographic.riskParams.containsKey("mask_type_oth_txt")?ModelDemographic.riskParams.get("mask_type_oth_txt"):"");
        hwash_freq.setText(ModelDemographic.riskParams.containsKey("hwash_freq")?ModelDemographic.riskParams.get("hwash_freq"):"");
        cover_by_oth.setText(ModelDemographic.riskParams.containsKey("cover_by_oth")?ModelDemographic.riskParams.get("cover_by_oth"):"");

        cmeet_oth_txt.setText(ModelDemographic.riskParams.containsKey("cmeet_oth_txt")?ModelDemographic.riskParams.get("cmeet_oth_txt"):"");
        pt_by_oth.setText(ModelDemographic.riskParams.containsKey("pt_by_oth")?ModelDemographic.riskParams.get("pt_by_oth"):"");
        mbite_oth.setText(ModelDemographic.riskParams.containsKey("mbite_oth")?ModelDemographic.riskParams.get("mbite_oth"):"");
        net_oth_txt.setText(ModelDemographic.riskParams.containsKey("net_oth_txt")?ModelDemographic.riskParams.get("net_oth_txt"):"");
        inf_oth.setText(ModelDemographic.riskParams.containsKey("inf_oth")?ModelDemographic.riskParams.get("inf_oth"):"");
        name.setText(ModelDemographic.riskParams.containsKey("name")?ModelDemographic.riskParams.get("name"):"");
        kitorg_name.setText(ModelDemographic.riskParams.containsKey("kitorg_name")?ModelDemographic.riskParams.get("kitorg_name"):"");
        cmeet_day.setText(ModelDemographic.riskParams.containsKey("cmeet_day")?ModelDemographic.riskParams.get("cmeet_day"):"");
        elbow.setChecked(ModelDemographic.riskParams.containsKey("elbow")&&Integer.parseInt(ModelDemographic.riskParams.get("elbow"))==1);
        clth.setChecked(ModelDemographic.riskParams.containsKey("clth")&&Integer.parseInt(ModelDemographic.riskParams.get("clth"))==1);
        palm.setChecked(ModelDemographic.riskParams.containsKey("palm")&&Integer.parseInt(ModelDemographic.riskParams.get("palm"))==1);
        cough.setChecked(ModelDemographic.riskParams.containsKey("cough")&&Integer.parseInt(ModelDemographic.riskParams.get("cough"))==1);
        touch.setChecked(ModelDemographic.riskParams.containsKey("touch")&&Integer.parseInt(ModelDemographic.riskParams.get("touch"))==1);
        shake.setChecked(ModelDemographic.riskParams.containsKey("shake")&&Integer.parseInt(ModelDemographic.riskParams.get("shake"))==1);
        inf.setChecked(ModelDemographic.riskParams.containsKey("inf")&&Integer.parseInt(ModelDemographic.riskParams.get("inf"))==1);
        food.setChecked(ModelDemographic.riskParams.containsKey("food")&&Integer.parseInt(ModelDemographic.riskParams.get("food"))==1);
        govt.setChecked(ModelDemographic.riskParams.containsKey("govt")&&Integer.parseInt(ModelDemographic.riskParams.get("govt"))==1);
        pvt.setChecked(ModelDemographic.riskParams.containsKey("pvt")&&Integer.parseInt(ModelDemographic.riskParams.get("pvt"))==1);
        net.setChecked(ModelDemographic.riskParams.containsKey("net")&&Integer.parseInt(ModelDemographic.riskParams.get("net"))==1);
        coil.setChecked(ModelDemographic.riskParams.containsKey("coil")&&Integer.parseInt(ModelDemographic.riskParams.get("coil"))==1);
        dhup.setChecked(ModelDemographic.riskParams.containsKey("dhup")&&Integer.parseInt(ModelDemographic.riskParams.get("dhup"))==1);
        mbite_no.setChecked(ModelDemographic.riskParams.containsKey("mbite_no")&&Integer.parseInt(ModelDemographic.riskParams.get("mbite_no"))==1);
        if (ModelDemographic.riskParams.containsKey("net")&&Integer.parseInt(ModelDemographic.riskParams.get("net"))==1){
            if(((TableRow)findViewById(R.id.net_row)).getVisibility()!=View.VISIBLE)((TableRow)findViewById(R.id.net_row)).setVisibility(View.VISIBLE);
        }else {
            if(((TableRow)findViewById(R.id.net_row)).getVisibility()==View.VISIBLE)((TableRow)findViewById(R.id.net_row)).setVisibility(View.GONE);
        }
    }
    private void setValues(){
        if (mask.getCheckedRadioButtonId()==R.id.mask_always)
            ModelDemographic.riskParams.put("mask","1");
        else if (mask.getCheckedRadioButtonId()==R.id.mask_some)
            ModelDemographic.riskParams.put("mask","2");
        else if (mask.getCheckedRadioButtonId()==R.id.mask_no)
            ModelDemographic.riskParams.put("mask","3");
        else if (mask.getCheckedRadioButtonId()==R.id.mask_oth)
            ModelDemographic.riskParams.put("mask","4");
        if (mask_type.getCheckedRadioButtonId()==R.id.surge)
            ModelDemographic.riskParams.put("mask_type","1");
        else if (mask_type.getCheckedRadioButtonId()==R.id.cloth)
            ModelDemographic.riskParams.put("mask_type","2");
        else if (mask_type.getCheckedRadioButtonId()==R.id.mask_type_oth)
            ModelDemographic.riskParams.put("mask_type","3");
        if (hwash.getCheckedRadioButtonId()==R.id.hwash_yes)
            ModelDemographic.riskParams.put("hwash","1");
        else if (hwash.getCheckedRadioButtonId()==R.id.hwash_no)
            ModelDemographic.riskParams.put("hwash","2");
        else if (hwash.getCheckedRadioButtonId()==R.id.hwash_un)
            ModelDemographic.riskParams.put("hwash","3");
        if (tsec.getCheckedRadioButtonId()==R.id.tsec_yes)
            ModelDemographic.riskParams.put("tsec","1");
        else if (tsec.getCheckedRadioButtonId()==R.id.tsec_no)
            ModelDemographic.riskParams.put("tsec","2");
        else if (tsec.getCheckedRadioButtonId()==R.id.tsec_un)
            ModelDemographic.riskParams.put("tsec","3");
        if (cover.getCheckedRadioButtonId()==R.id.cover_yes)
            ModelDemographic.riskParams.put("cover","1");
        else if (cover.getCheckedRadioButtonId()==R.id.cover_no)
            ModelDemographic.riskParams.put("cover","2");
        else if (cover.getCheckedRadioButtonId()==R.id.cover_un)
            ModelDemographic.riskParams.put("cover","3");
        if (ctsec.getCheckedRadioButtonId()==R.id.ctsec_yes)
            ModelDemographic.riskParams.put("ctsec","1");
        else if (ctsec.getCheckedRadioButtonId()==R.id.ctsec_no)
            ModelDemographic.riskParams.put("ctsec","2");
        else if (ctsec.getCheckedRadioButtonId()==R.id.ctsec_un)
            ModelDemographic.riskParams.put("ctsec","3");

        if (cmeet.getCheckedRadioButtonId()==R.id.cmeet_yes)
            ModelDemographic.riskParams.put("cmeet","1");
        else if (cmeet.getCheckedRadioButtonId()==R.id.cmeet_no)
            ModelDemographic.riskParams.put("cmeet","2");
        else if (cmeet.getCheckedRadioButtonId()==R.id.cmeet_un)
            ModelDemographic.riskParams.put("cmeet","3");
        if (cmeet_place.getCheckedRadioButtonId()==R.id.cmeet_place_home)
            ModelDemographic.riskParams.put("cmeet_place","1");
        else if (cmeet_place.getCheckedRadioButtonId()==R.id.cmeet_place_hos)
            ModelDemographic.riskParams.put("cmeet_place","2");
        else if (cmeet_place.getCheckedRadioButtonId()==R.id.cmeet_place_ofc)
            ModelDemographic.riskParams.put("cmeet_place","3");
        if (cmeet_place.getCheckedRadioButtonId()==R.id.cmeet_place_team)
            ModelDemographic.riskParams.put("cmeet_place","4");
        else if (cmeet_place.getCheckedRadioButtonId()==R.id.cmeet_place_school)
            ModelDemographic.riskParams.put("cmeet_place","5");
        else if (cmeet_place.getCheckedRadioButtonId()==R.id.cmeet_place_unk)
            ModelDemographic.riskParams.put("cmeet_place","6");
        else if (cmeet_place.getCheckedRadioButtonId()==R.id.cmeet_place_oth)
            ModelDemographic.riskParams.put("cmeet_place","7");
        if (pt.getCheckedRadioButtonId()==R.id.pt_yes)
            ModelDemographic.riskParams.put("pt","1");
        else if (pt.getCheckedRadioButtonId()==R.id.pt_no)
            ModelDemographic.riskParams.put("pt","2");
        else if (pt.getCheckedRadioButtonId()==R.id.pt_un)
            ModelDemographic.riskParams.put("pt","3");
        if (sdist.getCheckedRadioButtonId()==R.id.sdist1)
            ModelDemographic.riskParams.put("sdist","1");
        if (sdist.getCheckedRadioButtonId()==R.id.sdist2)
            ModelDemographic.riskParams.put("sdist","2");
        if (sdist.getCheckedRadioButtonId()==R.id.sdist3)
            ModelDemographic.riskParams.put("sdist","3");
        if (sdist.getCheckedRadioButtonId()==R.id.sdist4)
            ModelDemographic.riskParams.put("sdist","4");
        if (sdist.getCheckedRadioButtonId()==R.id.sdist0)
            ModelDemographic.riskParams.put("sdist","0");
        if (net_time.getCheckedRadioButtonId()==R.id.day)
            ModelDemographic.riskParams.put("net_time","1");
        else if (net_time.getCheckedRadioButtonId()==R.id.night)
            ModelDemographic.riskParams.put("net_time","2");
        else if (net_time.getCheckedRadioButtonId()==R.id.all)
            ModelDemographic.riskParams.put("net_time","3");
        else if (net_time.getCheckedRadioButtonId()==R.id.net_oth)
            ModelDemographic.riskParams.put("net_time","4");
        if (media.getCheckedRadioButtonId()==R.id.media_yes)
            ModelDemographic.riskParams.put("media","1");
        else if (media.getCheckedRadioButtonId()==R.id.media_no)
            ModelDemographic.riskParams.put("media","2");
        else if (media.getCheckedRadioButtonId()==R.id.media_un)
            ModelDemographic.riskParams.put("media","3");
        if (htips.getCheckedRadioButtonId()==R.id.htips_yes)
            ModelDemographic.riskParams.put("htips","1");
        else if (htips.getCheckedRadioButtonId()==R.id.htips_no)
            ModelDemographic.riskParams.put("htips","2");
        else if (htips.getCheckedRadioButtonId()==R.id.htips_un)
            ModelDemographic.riskParams.put("htips","3");
        if (kit.getCheckedRadioButtonId()==R.id.kit_yes)
            ModelDemographic.riskParams.put("kit","1");
        else if (kit.getCheckedRadioButtonId()==R.id.kit_no)
            ModelDemographic.riskParams.put("kit","2");
        else if (kit.getCheckedRadioButtonId()==R.id.kit_un)
            ModelDemographic.riskParams.put("kit","3");
        ModelDemographic.riskParams.put("mask_oth_txt",mask_oth_txt.getText().toString());
        ModelDemographic.riskParams.put("mask_type_oth_txt",mask_type_oth_txt.getText().toString());
        ModelDemographic.riskParams.put("hwash_freq",hwash_freq.getText().toString());
        ModelDemographic.riskParams.put("cover_by_oth",cover_by_oth.getText().toString());

        ModelDemographic.riskParams.put("cmeet_oth_txt",cmeet_oth_txt.getText().toString());
        ModelDemographic.riskParams.put("pt_by_oth",pt_by_oth.getText().toString());
        ModelDemographic.riskParams.put("mbite_oth",mbite_oth.getText().toString());
        ModelDemographic.riskParams.put("net_oth_txt",net_oth_txt.getText().toString());
        ModelDemographic.riskParams.put("inf_oth",inf_oth.getText().toString());
        ModelDemographic.riskParams.put("name",name.getText().toString());
        ModelDemographic.riskParams.put("kitorg_name",kitorg_name.getText().toString());
        ModelDemographic.riskParams.put("cmeet_day",cmeet_day.getText().toString());
        ModelDemographic.riskParams.put("elbow",elbow.isChecked()?"1":"2");
        ModelDemographic.riskParams.put("clth",clth.isChecked()?"1":"2");
        ModelDemographic.riskParams.put("palm",palm.isChecked()?"1":"2");
        ModelDemographic.riskParams.put("cough",cough.isChecked()?"1":"2");
        ModelDemographic.riskParams.put("touch",touch.isChecked()?"1":"2");
        ModelDemographic.riskParams.put("shake",shake.isChecked()?"1":"2");
        ModelDemographic.riskParams.put("inf",inf.isChecked()?"1":"2");
        ModelDemographic.riskParams.put("food",food.isChecked()?"1":"2");
        ModelDemographic.riskParams.put("govt",govt.isChecked()?"1":"2");
        ModelDemographic.riskParams.put("pvt",pvt.isChecked()?"1":"2");
        ModelDemographic.riskParams.put("net",net.isChecked()?"1":"2");
        ModelDemographic.riskParams.put("coil",coil.isChecked()?"1":"2");
        ModelDemographic.riskParams.put("dhup",dhup.isChecked()?"1":"2");
        ModelDemographic.riskParams.put("mbite_no",mbite_no.isChecked()?"1":"2");
    }
    private CompoundButton.OnCheckedChangeListener comChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked){
                if(((TableRow)findViewById(R.id.net_row)).getVisibility()!=View.VISIBLE)((TableRow)findViewById(R.id.net_row)).setVisibility(View.VISIBLE);
            }else {
                if(((TableRow)findViewById(R.id.net_row)).getVisibility()==View.VISIBLE)((TableRow)findViewById(R.id.net_row)).setVisibility(View.GONE);
            }
        }
    };
    private boolean check(){
        if (Integer.parseInt(ModelDemographic.riskParams.get("mask")) == 4) {
            if(mask_oth_txt.getText().toString()==null || mask_oth_txt.getText().toString().isEmpty()) return false;
        }
        if (Integer.parseInt(ModelDemographic.riskParams.get("mask_type")) == 3) {
            if(mask_type_oth_txt.getText().toString()==null || mask_type_oth_txt.getText().toString().isEmpty()) return false;
        }
        if (Integer.parseInt(ModelDemographic.riskParams.get("hwash")) == 1) {
            if(hwash_freq.getText().toString()==null || hwash_freq.getText().toString().isEmpty()) return false;
        }

        if (Integer.parseInt(ModelDemographic.riskParams.get("cmeet")) == 1) {
            if(cmeet_day.getText().toString()==null || cmeet_day.getText().toString().isEmpty()) return false;
        }
        if (ModelDemographic.riskParams.containsKey("net_time") && Integer.parseInt(ModelDemographic.riskParams.get("net_time")) == 4) {
            if(net_oth_txt.getText().toString()==null || net_oth_txt.getText().toString().isEmpty()) return false;
        }
        if (Integer.parseInt(ModelDemographic.riskParams.get("htips")) == 1) {
            if(name.getText().toString()==null || name.getText().toString().isEmpty()) return false;
        }
        if (Integer.parseInt(ModelDemographic.riskParams.get("kit")) == 1) {
            if(kitorg_name.getText().toString()==null || kitorg_name.getText().toString().isEmpty()) return false;
        }
        if(!ModelDemographic.riskParams.containsKey("elbow")) return false;
        if(!ModelDemographic.riskParams.containsKey("clth")) return false;
        if(!ModelDemographic.riskParams.containsKey("palm")) return false;
        if(!ModelDemographic.riskParams.containsKey("cough")) return false;
        if(!ModelDemographic.riskParams.containsKey("touch")) return false;
        if(!ModelDemographic.riskParams.containsKey("shake")) return false;
        if(!ModelDemographic.riskParams.containsKey("inf")) return false;
        if(!ModelDemographic.riskParams.containsKey("food")) return false;
        if(!ModelDemographic.riskParams.containsKey("govt")) return false;
        if(!ModelDemographic.riskParams.containsKey("pvt")) return false;
        if(!ModelDemographic.riskParams.containsKey("net")) return false;
        if(!ModelDemographic.riskParams.containsKey("coil")) return false;
        if(!ModelDemographic.riskParams.containsKey("dhup")) return false;
        if(!ModelDemographic.riskParams.containsKey("mbite_no")) return false;
        return true;
    }
    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
//            mask,mask_type,hwash,cover,inv,cmeet,cmeet_place,pt,htips,kit
            if(group.getId()==mask.getId()){
                if (checkedId==R.id.mask_oth) {
                    if (((TextInputLayout) findViewById(R.id.mask_oth_layout)).getVisibility() != View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.mask_oth_layout)).setVisibility(View.VISIBLE);
                }else{
                    if (((TextInputLayout) findViewById(R.id.mask_oth_layout)).getVisibility() == View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.mask_oth_layout)).setVisibility(View.GONE);
                }
            }
            else if(group.getId()==mask_type.getId()){
                if (checkedId==R.id.mask_type_oth) {
                    if (((TextInputLayout) findViewById(R.id.mask_type_oth_layout)).getVisibility() != View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.mask_type_oth_layout)).setVisibility(View.VISIBLE);
                }else{
                    if (((TextInputLayout) findViewById(R.id.mask_type_oth_layout)).getVisibility() == View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.mask_type_oth_layout)).setVisibility(View.GONE);
                }
            }
            else if(group.getId()==hwash.getId()){
                if (checkedId==R.id.hwash_yes) {
                    if (((TextInputLayout) findViewById(R.id.hwash_layout)).getVisibility() != View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.hwash_layout)).setVisibility(View.VISIBLE);
                }else{
                    if (((TextInputLayout) findViewById(R.id.hwash_layout)).getVisibility() == View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.hwash_layout)).setVisibility(View.GONE);
                }
            }
            else if(group.getId()==cover.getId()){
                if (checkedId==R.id.cover_yes) {
                    if (((LinearLayout) findViewById(R.id.cover_lin)).getVisibility() != View.VISIBLE)
                        ((LinearLayout) findViewById(R.id.cover_lin)).setVisibility(View.VISIBLE);
                }else{
                    if (((LinearLayout) findViewById(R.id.cover_lin)).getVisibility() == View.VISIBLE)
                        ((LinearLayout) findViewById(R.id.cover_lin)).setVisibility(View.GONE);
                }
            }
            else if(group.getId()==cmeet.getId()){
                if (checkedId==R.id.cmeet_yes) {
                    if (((TableRow) findViewById(R.id.cmeet_row)).getVisibility() != View.VISIBLE)
                        ((TableRow) findViewById(R.id.cmeet_row)).setVisibility(View.VISIBLE);
                }else{
                    if (((TableRow) findViewById(R.id.cmeet_row)).getVisibility() == View.VISIBLE)
                        ((TableRow) findViewById(R.id.cmeet_row)).setVisibility(View.GONE);
                }
            }
            else if(group.getId()==cmeet_place.getId()){
                if (checkedId==R.id.cmeet_place_oth) {
                    if (((TextInputLayout) findViewById(R.id.cmeet_oth_layout)).getVisibility() != View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.cmeet_oth_layout)).setVisibility(View.VISIBLE);
                }else{
                    if (((TextInputLayout) findViewById(R.id.cmeet_oth_layout)).getVisibility() == View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.cmeet_oth_layout)).setVisibility(View.GONE);
                }
            }
            else if(group.getId()==pt.getId()){
                if (checkedId==R.id.pt_yes) {
                    if (((LinearLayout) findViewById(R.id.pt_lin)).getVisibility() != View.VISIBLE)
                        ((LinearLayout) findViewById(R.id.pt_lin)).setVisibility(View.VISIBLE);
                }else{
                    if (((LinearLayout) findViewById(R.id.pt_lin)).getVisibility() == View.VISIBLE)
                        ((LinearLayout) findViewById(R.id.pt_lin)).setVisibility(View.GONE);
                }
            }else if(group.getId()==net_time.getId()){
                if (checkedId==R.id.net_oth) {
                    if (((TextInputLayout) findViewById(R.id.net_oth_layout)).getVisibility() != View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.net_oth_layout)).setVisibility(View.VISIBLE);
                }else{
                    if (((TextInputLayout) findViewById(R.id.net_oth_layout)).getVisibility() == View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.net_oth_layout)).setVisibility(View.GONE);
                }
            }
            else if(group.getId()==htips.getId()){
                if (checkedId==R.id.htips_yes) {
                    if (((LinearLayout) findViewById(R.id.htips_row)).getVisibility() != View.VISIBLE)
                        ((LinearLayout) findViewById(R.id.htips_row)).setVisibility(View.VISIBLE);
                }else{
                    if (((LinearLayout) findViewById(R.id.htips_row)).getVisibility() == View.VISIBLE)
                        ((LinearLayout) findViewById(R.id.htips_row)).setVisibility(View.GONE);
                }
            }else if(group.getId()==kit.getId()){
                if (checkedId==R.id.kit_yes) {
                    if (((TextInputLayout) findViewById(R.id.kit_layout)).getVisibility() != View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.kit_layout)).setVisibility(View.VISIBLE);
                }else{
                    if (((TextInputLayout) findViewById(R.id.kit_layout)).getVisibility() == View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.kit_layout)).setVisibility(View.GONE);
                }
            }
        }
    };
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == submit.getId()){
                setValues();
                if (check()){
                    MyDB myDB = new MyDB(RiskBehavActivity.this);
                    myDB.insert();
                    ModelFamilyMember.clearFamilyMemberContents();
                    ModelDemographic.clear();
                    startActivity(new Intent(RiskBehavActivity.this,ListActivity.class));
                    MainActivity.mainActivity.finish();
                    DemographicActivity.demographicActivity.finish();
                    PastDisActivity.pastDisActivity.finish();
                    ParticipantInfoActivity.participantInfoActivity.finish();
                    RespiratoryActivity.respiratoryActivity.finish();
                    if (HistoryActivity.historyActivity!=null)
                        HistoryActivity.historyActivity.finish();
                    TreatmentActivity.treatmentActivity.finish();
                    DengueActivity.dengueActivity.finish();
                    ComorbidityActivity.comorbidityActivity.finish();
                    finish();
                }else{
                    Toast.makeText(RiskBehavActivity.this,"Check Input",Toast.LENGTH_SHORT).show();
                }
            }else if (v.getId()==cmeet_day.getId()){
                new CalenderDialog(RiskBehavActivity.this,RiskBehavActivity.this,cmeet_day.getText().toString(),
                        "Date",cmeet_day).show();
            }
        }
    };
    @Override
    public void getDate(String dat, TextView editText) {
        editText.setText(dat);
    }
}