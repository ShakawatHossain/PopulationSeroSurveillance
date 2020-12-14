package com.iedcr.populationserosurveillance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.iedcr.populationserosurveillance.fragments.CalenderDialog;
import com.iedcr.populationserosurveillance.iview.CalenderInterface;
import com.iedcr.populationserosurveillance.model.ModelDemographic;

public class ParticipantInfoActivity extends AppCompatActivity implements CalenderInterface {
    RadioGroup participant,ans_gen,pt_gen;
    EditText ans_name,ans_age,ans_rel,ans_home,ans_block,ans_subblock,ans_ward,ans_area,ans_mob,
            pt_name,pt_age,pt_rel,pt_home,pt_block,pt_subblock,pt_ward,pt_area,pt_mob,edu,edu_oth_txt;
    TextView ans_dob,pt_dob;
    CheckBox ans_dob_un,ans_age_un,pt_dob_un,pt_age_un,edu_il,edu_sig,edu_un,edu_oth;
    Spinner ocu;
    Button submit;
    public static ParticipantInfoActivity participantInfoActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_participant_info);
        init();
        participantInfoActivity = this;
    }
    private void init(){
//        init
        participant=(RadioGroup) findViewById(R.id.participant);
        participant.setOnCheckedChangeListener(checkedChangeListener);
        ans_gen=(RadioGroup) findViewById(R.id.ans_gen);
        pt_gen=(RadioGroup) findViewById(R.id.pt_gen);
        ans_name=(EditText) findViewById(R.id.ans_name);
        ans_age=(EditText) findViewById(R.id.ans_age);
        ans_rel=(EditText) findViewById(R.id.ans_rel);
        ans_home=(EditText) findViewById(R.id.ans_home);
        ans_block=(EditText) findViewById(R.id.ans_block);
        ans_subblock=(EditText) findViewById(R.id.ans_subblock);
        ans_ward=(EditText) findViewById(R.id.ans_ward);
        ans_area=(EditText) findViewById(R.id.ans_area);
        ans_mob=(EditText) findViewById(R.id.ans_mob);
        pt_name=(EditText) findViewById(R.id.pt_name);
        pt_age=(EditText) findViewById(R.id.pt_age);
        pt_rel=(EditText) findViewById(R.id.pt_rel);
        pt_home=(EditText) findViewById(R.id.pt_home);
        pt_block=(EditText) findViewById(R.id.pt_block);
        pt_subblock=(EditText) findViewById(R.id.pt_subblock);
        pt_ward=(EditText) findViewById(R.id.pt_ward);
        pt_area=(EditText) findViewById(R.id.pt_area);
        pt_mob=(EditText) findViewById(R.id.pt_mob);
        edu=(EditText) findViewById(R.id.edu);
        edu_oth_txt=(EditText) findViewById(R.id.edu_oth_txt);
        ans_dob=(TextView) findViewById(R.id.ans_dob);
        ans_dob.setOnClickListener(clickListener);
        pt_dob=(TextView) findViewById(R.id.pt_dob);
        pt_dob.setOnClickListener(clickListener);
        ans_dob_un=(CheckBox) findViewById(R.id.ans_dob_un);
        ans_dob_un.setOnCheckedChangeListener(onCheckedChangeListener);
        ans_age_un=(CheckBox) findViewById(R.id.ans_age_un);
        pt_dob_un=(CheckBox) findViewById(R.id.pt_dob_un);
        pt_dob_un.setOnCheckedChangeListener(onCheckedChangeListener);
        pt_age_un=(CheckBox) findViewById(R.id.pt_age_un);
        edu_il=(CheckBox) findViewById(R.id.edu_il);
        edu_sig=(CheckBox) findViewById(R.id.edu_sig);
        edu_un=(CheckBox) findViewById(R.id.edu_un);
        edu_oth=(CheckBox) findViewById(R.id.edu_oth);
        edu_il.setOnCheckedChangeListener(onCheckedChangeListener);
        edu_sig.setOnCheckedChangeListener(onCheckedChangeListener);
        edu_un.setOnCheckedChangeListener(onCheckedChangeListener);
        edu_oth.setOnCheckedChangeListener(onCheckedChangeListener);
        submit = (Button) findViewById(R.id.submit);
        ocu = (Spinner) findViewById(R.id.ocu);
        submit.setOnClickListener(clickListener);
//        Prev value
        if(ModelDemographic.ptInfoParams.containsKey("participant")) {
            int participant_value = Integer.parseInt(ModelDemographic.ptInfoParams.get("participant"));
            if (participant_value == 1) {
                participant.check(R.id.participant_yes);
            } else {
                participant.check(R.id.participant_no);
            }
        }
        if(ModelDemographic.ptInfoParams.containsKey("ans_gen")) {
            int ans_gen_value = Integer.parseInt(ModelDemographic.ptInfoParams.get("ans_gen"));
            if (ans_gen_value == 1) {
                ans_gen.check(R.id.ans_gen_male);
            } else if (ans_gen_value == 2) {
                ans_gen.check(R.id.ans_gen_female);
            } else {
                ans_gen.check(R.id.ans_gen_oth);
            }
        }
        if(ModelDemographic.ptInfoParams.containsKey("pt_gen")) {
            int pt_gen_value = Integer.parseInt(ModelDemographic.ptInfoParams.get("pt_gen"));
            if (pt_gen_value == 1) {
                pt_gen.check(R.id.pt_gen_male);
            } else if (pt_gen_value == 2) {
                pt_gen.check(R.id.pt_gen_female);
            } else {
                pt_gen.check(R.id.pt_gen_oth);
            }
        }
        ans_name.setText(ModelDemographic.ptInfoParams.containsKey("ans_name")?ModelDemographic.ptInfoParams.get("ans_name"):"");
        ans_age.setText(ModelDemographic.ptInfoParams.containsKey("ans_age")?ModelDemographic.ptInfoParams.get("ans_age"):"");
        ans_rel.setText(ModelDemographic.ptInfoParams.containsKey("ans_rel")?ModelDemographic.ptInfoParams.get("ans_rel"):"");
        ans_home.setText(ModelDemographic.ptInfoParams.containsKey("ans_home")?ModelDemographic.ptInfoParams.get("ans_home"):"");
        ans_block.setText(ModelDemographic.ptInfoParams.containsKey("ans_block")?ModelDemographic.ptInfoParams.get("ans_block"):"");
        ans_subblock.setText(ModelDemographic.ptInfoParams.containsKey("ans_subblock")?ModelDemographic.ptInfoParams.get("ans_subblock"):"");
        ans_ward.setText(ModelDemographic.ptInfoParams.containsKey("ans_ward")?ModelDemographic.ptInfoParams.get("ans_ward"):"");
        ans_area.setText(ModelDemographic.ptInfoParams.containsKey("ans_area")?ModelDemographic.ptInfoParams.get("ans_area"):"");
        ans_mob.setText(ModelDemographic.ptInfoParams.containsKey("ans_mob")?ModelDemographic.ptInfoParams.get("ans_mob"):"");
        pt_name.setText(ModelDemographic.ptInfoParams.containsKey("pt_name")?ModelDemographic.ptInfoParams.get("pt_name"):"");
        pt_age.setText(ModelDemographic.ptInfoParams.containsKey("pt_age")?ModelDemographic.ptInfoParams.get("pt_age"):"");
        pt_rel.setText(ModelDemographic.ptInfoParams.containsKey("pt_rel")?ModelDemographic.ptInfoParams.get("pt_rel"):"");
        pt_home.setText(ModelDemographic.ptInfoParams.containsKey("pt_home")?ModelDemographic.ptInfoParams.get("pt_home"):"");
        pt_block.setText(ModelDemographic.ptInfoParams.containsKey("pt_block")?ModelDemographic.ptInfoParams.get("pt_block"):"");
        pt_subblock.setText(ModelDemographic.ptInfoParams.containsKey("pt_subblock")?ModelDemographic.ptInfoParams.get("pt_subblock"):"");
        pt_ward.setText(ModelDemographic.ptInfoParams.containsKey("pt_ward")?ModelDemographic.ptInfoParams.get("pt_ward"):"");
        pt_area.setText(ModelDemographic.ptInfoParams.containsKey("pt_area")?ModelDemographic.ptInfoParams.get("pt_area"):"");
        pt_mob.setText(ModelDemographic.ptInfoParams.containsKey("pt_mob")?ModelDemographic.ptInfoParams.get("pt_mob"):"");
        edu.setText(ModelDemographic.ptInfoParams.containsKey("edu")?ModelDemographic.ptInfoParams.get("edu"):"");
        edu_oth_txt.setText(ModelDemographic.ptInfoParams.containsKey("edu_oth_txt")?ModelDemographic.ptInfoParams.get("edu_oth_txt"):"");
        ans_dob.setText(ModelDemographic.ptInfoParams.containsKey("ans_dob")?ModelDemographic.ptInfoParams.get("ans_dob"):"");
        pt_dob.setText(ModelDemographic.ptInfoParams.containsKey("pt_dob")?ModelDemographic.ptInfoParams.get("pt_dob"):"");
        ans_dob_un.setChecked(ModelDemographic.ptInfoParams.containsKey("ans_dob_un")?
                Integer.parseInt(ModelDemographic.ptInfoParams.get("ans_dob_un"))==1:false);
        ans_age_un.setChecked(ModelDemographic.ptInfoParams.containsKey("ans_age_un")?
                Integer.parseInt(ModelDemographic.ptInfoParams.get("ans_age_un"))==1:false);
        pt_dob_un.setChecked(ModelDemographic.ptInfoParams.containsKey("pt_dob_un")?
                Integer.parseInt(ModelDemographic.ptInfoParams.get("pt_dob_un"))==1:false);
        pt_age_un.setChecked(ModelDemographic.ptInfoParams.containsKey("pt_age_un")?
                Integer.parseInt(ModelDemographic.ptInfoParams.get("pt_age_un"))==1:false);
        if (ModelDemographic.ptInfoParams.containsKey("ocu"))
            ocu.setSelection(Integer.parseInt(ModelDemographic.ptInfoParams.get("ocu")));
        edu_il.setChecked(ModelDemographic.ptInfoParams.containsKey("edu_il")?Integer.parseInt(ModelDemographic.ptInfoParams.get("edu_il"))==1:false);
        edu_sig.setChecked(ModelDemographic.ptInfoParams.containsKey("edu_sig")?Integer.parseInt(ModelDemographic.ptInfoParams.get("edu_sig"))==1:false);
        edu_un.setChecked(ModelDemographic.ptInfoParams.containsKey("edu_un")?Integer.parseInt(ModelDemographic.ptInfoParams.get("edu_un"))==1:false);
        edu_oth.setChecked(ModelDemographic.ptInfoParams.containsKey("edu_oth")?Integer.parseInt(ModelDemographic.ptInfoParams.get("edu_oth"))==1:false);
    }
    private CheckBox.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.getId()==ans_dob_un.getId()){
                if (isChecked){
                    if(((TableRow) findViewById(R.id.ans_age_row)).getVisibility()!=View.VISIBLE)
                        ((TableRow) findViewById(R.id.ans_age_row)).setVisibility(View.VISIBLE);
                }else {
                    if(((TableRow) findViewById(R.id.ans_age_row)).getVisibility()==View.VISIBLE)
                        ((TableRow) findViewById(R.id.ans_age_row)).setVisibility(View.GONE);
                }
            }else if (buttonView.getId()==pt_dob_un.getId()){
                if (isChecked){
                    if(((TableRow) findViewById(R.id.pt_age_row)).getVisibility()!=View.VISIBLE)
                        ((TableRow) findViewById(R.id.pt_age_row)).setVisibility(View.VISIBLE);
                }else {
                    if(((TableRow) findViewById(R.id.pt_age_row)).getVisibility()==View.VISIBLE)
                        ((TableRow) findViewById(R.id.pt_age_row)).setVisibility(View.GONE);
                }
            }else if (buttonView.getId()==edu_il.getId()){
                if (isChecked)edu.setText("0");
            }else if (buttonView.getId()==edu_sig.getId()){
                if (isChecked)edu.setText("77");
            }else if (buttonView.getId()==edu_un.getId()){
                if (isChecked)edu.setText("88");
            }else if (buttonView.getId()==edu_oth.getId()){
                if (isChecked){edu.setText("99"); edu_oth_txt.setVisibility(View.VISIBLE);}
                else if (!isChecked){edu_oth_txt.setText(""); edu_oth_txt.setVisibility(View.GONE);}
            }

        }
    };
    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (group.getId()==participant.getId()){
                if (checkedId==R.id.participant_yes){
                    if(((CardView) findViewById(R.id.ans_sec)).getVisibility()== View.VISIBLE)
                        ((CardView) findViewById(R.id.ans_sec)).setVisibility(View.GONE);
                }else if(checkedId==R.id.participant_no){
                    if(((CardView) findViewById(R.id.ans_sec)).getVisibility()!= View.VISIBLE)
                        ((CardView) findViewById(R.id.ans_sec)).setVisibility(View.VISIBLE);
                }
            }
        }
    };
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==ans_dob.getId()){
                new CalenderDialog(ParticipantInfoActivity.this,
                        ParticipantInfoActivity.this,ans_dob.getText().toString(),"DOB",ans_dob).show();
            }else if (v.getId()==pt_dob.getId()){
                new CalenderDialog(ParticipantInfoActivity.this,
                        ParticipantInfoActivity.this,pt_dob.getText().toString(),"DOB",pt_dob).show();
            }else if (v.getId()==submit.getId()){
                setValues();
                if (!check()){
                    Toast.makeText(ParticipantInfoActivity.this,"CHECK Input",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(ParticipantInfoActivity.this,RespiratoryActivity.class));
                }
            }
        }
    };
    private void setValues(){
        if(participant.getCheckedRadioButtonId()==R.id.participant_yes)
            ModelDemographic.ptInfoParams.put("participant","1");
        else if(participant.getCheckedRadioButtonId()==R.id.participant_no)
            ModelDemographic.ptInfoParams.put("participant","2");
        if(ans_gen.getCheckedRadioButtonId()==R.id.ans_gen_male)
            ModelDemographic.ptInfoParams.put("ans_gen","1");
        else if(ans_gen.getCheckedRadioButtonId()==R.id.ans_gen_female)
            ModelDemographic.ptInfoParams.put("ans_gen","2");
        else if(ans_gen.getCheckedRadioButtonId()==R.id.ans_gen_oth)
            ModelDemographic.ptInfoParams.put("ans_gen","3");
        if(pt_gen.getCheckedRadioButtonId()==R.id.pt_gen_male)
            ModelDemographic.ptInfoParams.put("pt_gen","1");
        else if(pt_gen.getCheckedRadioButtonId()==R.id.pt_gen_female)
            ModelDemographic.ptInfoParams.put("pt_gen","2");
        else if(pt_gen.getCheckedRadioButtonId()==R.id.pt_gen_oth)
            ModelDemographic.ptInfoParams.put("pt_gen","3");
        ModelDemographic.ptInfoParams.put("ans_name",ans_name.getText().toString());
        ModelDemographic.ptInfoParams.put("ans_age",ans_age.getText().toString());
        ModelDemographic.ptInfoParams.put("ans_rel",ans_rel.getText().toString());
        ModelDemographic.ptInfoParams.put("ans_home",ans_home.getText().toString());
        ModelDemographic.ptInfoParams.put("ans_block",ans_block.getText().toString());
        ModelDemographic.ptInfoParams.put("ans_subblock",ans_subblock.getText().toString());
        ModelDemographic.ptInfoParams.put("ans_ward",ans_ward.getText().toString());
        ModelDemographic.ptInfoParams.put("ans_area",ans_area.getText().toString());
        ModelDemographic.ptInfoParams.put("ans_mob",ans_mob.getText().toString());
        ModelDemographic.ptInfoParams.put("pt_name",pt_name.getText().toString());
        ModelDemographic.ptInfoParams.put("pt_age",pt_age.getText().toString());
        ModelDemographic.ptInfoParams.put("pt_rel",pt_rel.getText().toString());
        ModelDemographic.ptInfoParams.put("pt_home",pt_home.getText().toString());
        ModelDemographic.ptInfoParams.put("pt_block",pt_block.getText().toString());
        ModelDemographic.ptInfoParams.put("pt_subblock",pt_subblock.getText().toString());
        ModelDemographic.ptInfoParams.put("pt_ward",pt_ward.getText().toString());
        ModelDemographic.ptInfoParams.put("pt_area",pt_area.getText().toString());
        ModelDemographic.ptInfoParams.put("pt_mob",pt_mob.getText().toString());
        ModelDemographic.ptInfoParams.put("edu",edu.getText().toString());
        ModelDemographic.ptInfoParams.put("edu_oth_txt",edu_oth_txt.getText().toString());
        ModelDemographic.ptInfoParams.put("ans_dob",ans_dob.getText().toString());
        ModelDemographic.ptInfoParams.put("pt_dob",pt_dob.getText().toString());
        ModelDemographic.ptInfoParams.put("ans_dob_un",ans_dob_un.isChecked()?"1":"2");
        ModelDemographic.ptInfoParams.put("ans_age_un",ans_age_un.isChecked()?"1":"2");
        ModelDemographic.ptInfoParams.put("pt_dob_un",pt_dob_un.isChecked()?"1":"2");
        ModelDemographic.ptInfoParams.put("pt_age_un",pt_age_un.isChecked()?"1":"2");
        ModelDemographic.ptInfoParams.put("ocu",String.valueOf(ocu.getSelectedItemPosition()));
    }
    private boolean check(){
        if(!ModelDemographic.ptInfoParams.containsKey("participant")) return false;
        if(!ModelDemographic.ptInfoParams.containsKey("pt_name")) return false;
        if(!ModelDemographic.ptInfoParams.containsKey("pt_age")) return false;
        if(!ModelDemographic.ptInfoParams.containsKey("pt_mob") || ModelDemographic.ptInfoParams.get("pt_mob").length()!=11) return false;
        if(!ModelDemographic.ptInfoParams.containsKey("pt_gen")) return false;
        if(!ModelDemographic.ptInfoParams.containsKey("pt_dob")){
            if (ModelDemographic.ptInfoParams.containsKey("pt_dob_un")){
                if (ModelDemographic.ptInfoParams.containsKey("pt_age")||ModelDemographic.ptInfoParams.containsKey("pt_age_un")){

                }else {
                    Toast.makeText(ParticipantInfoActivity.this,"Insert age",Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else
                Toast.makeText(ParticipantInfoActivity.this,"Insert DOB",Toast.LENGTH_SHORT).show();
                return false;
        }
        if(Integer.parseInt(ModelDemographic.ptInfoParams.get("participant"))==1) return true;
        if(!ModelDemographic.ptInfoParams.containsKey("ans_name")) return false;
        if(!ModelDemographic.ptInfoParams.containsKey("ans_age")) return false;
        if(!ModelDemographic.ptInfoParams.containsKey("ans_rel")) return false;
        if(!ModelDemographic.ptInfoParams.containsKey("ans_gen")) return false;
        if(!ModelDemographic.ptInfoParams.containsKey("ans_dob")){
            if (ModelDemographic.ptInfoParams.containsKey("ans_dob_un")){
                if (ModelDemographic.ptInfoParams.containsKey("ans_age")||ModelDemographic.ptInfoParams.containsKey("ans_age_un")){

                }else {
                    Toast.makeText(ParticipantInfoActivity.this,"Insert age",Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else
                Toast.makeText(ParticipantInfoActivity.this,"Insert DOB",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void getDate(String dat, TextView editText) {
        editText.setText(dat);
    }
}