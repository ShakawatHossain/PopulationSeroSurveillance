package com.iedcr.populationserosurveillance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.iedcr.populationserosurveillance.model.ModelDemographic;
import com.iedcr.populationserosurveillance.model.ModelUtil;
import com.iedcr.populationserosurveillance.model.MyDB;

public class LoginActivity extends AppCompatActivity {
    EditText uid,pass;
    Button submit,see_db,del_db;
    ProgressBar pBar;
    MyDB myDB;

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (ModelDemographic.user_id!=null && !ModelDemographic.user_id.isEmpty()){
            startActivity(new Intent(LoginActivity.this,ListActivity.class));
        }
        init();
    }
    private void init(){
        myDB = new MyDB(LoginActivity.this);
        uid = (EditText) findViewById(R.id.user_id);
        pass = (EditText) findViewById(R.id.pass);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(this.listener);
        pBar = (ProgressBar) findViewById(R.id.pBar);
        see_db = (Button) findViewById(R.id.see_db);
        see_db.setOnClickListener(listener);
        del_db = (Button) findViewById(R.id.del_db);
        del_db.setOnClickListener(listener);
    }
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.see_db){
                printDB();
                return;
            }
            if(v.getId()==del_db.getId()){
                myDB.delete();
                return;
            }
            if (uid.getText().toString().isEmpty()){
                Toast.makeText(LoginActivity.this,"Please fill user name",Toast.LENGTH_SHORT).show();
                return;
            }else if(pass.getText().toString().isEmpty()){
                Toast.makeText(LoginActivity.this,"Please fill password",Toast.LENGTH_SHORT).show();
                return;
            }
//            btn_toggle();
            check_login();
        }
    };
    private void check_login(){
        ModelUtil modelUtil = new ModelUtil();
        ModelDemographic.user_id = modelUtil.getLogin(uid.getText().toString(),pass.getText().toString());
        if (ModelDemographic.user_id!=null && !ModelDemographic.user_id.isEmpty()){
            startActivity(new Intent(LoginActivity.this,ListActivity.class));
        }else {
            Toast.makeText(LoginActivity.this,"Please invalid name or password",Toast.LENGTH_SHORT).show();
        }
    }
    private void btn_toggle(){
        if(pBar.getVisibility()==View.GONE) pBar.setVisibility(View.VISIBLE); else pBar.setVisibility(View.GONE);
        if(submit.getVisibility()==View.GONE) submit.setVisibility(View.VISIBLE); else submit.setVisibility(View.GONE);
    }

    private void printDB(){
//        MyDB myDB = new MyDB(LoginActivity.this);
        Cursor cDemo = myDB.getAll_demo();
        Cursor cfam = myDB.getAll_fam();
        Cursor cadm = myDB.getAll_adm();
        Cursor cdeath = myDB.getAll_death();
        if (cDemo.moveToFirst()){
            do {
                String s = MyDB.SEROID + cDemo.getString(cDemo.getColumnIndex(MyDB.SEROID));
                s +=" demo_h_no : "+  cDemo.getString(cDemo.getColumnIndex("demo_h_no"));
                s +=" demo_h_name : "+  cDemo.getString(cDemo.getColumnIndex("demo_h_name"));
                s +=" demo_h_mob : "+  cDemo.getString(cDemo.getColumnIndex("demo_h_mob"));
                s +=" demo_fam_qty : "+  cDemo.getString(cDemo.getColumnIndex("demo_fam_qty"));
                s +=" demo_room_qty : "+  cDemo.getString(cDemo.getColumnIndex("demo_room_qty"));
                s +=" demo_toilet_qty : "+  cDemo.getString(cDemo.getColumnIndex("demo_toilet_qty"));
                s +=" demo_drink_src : "+  cDemo.getString(cDemo.getColumnIndex("demo_drink_src"));

                s +=" demo_drink_src_type : "+  cDemo.getString(cDemo.getColumnIndex("demo_drink_src_type"));
                s +=" demo_boil : "+  cDemo.getString(cDemo.getColumnIndex("demo_boil"));
                s +=" demo_filter : "+  cDemo.getString(cDemo.getColumnIndex("demo_filter"));
                s +=" demo_water_puri_type_oth : "+  cDemo.getString(cDemo.getColumnIndex("demo_water_puri_type_oth"));
                s +=" demo_water_puri_type_oth_txt : "+  cDemo.getString(cDemo.getColumnIndex("demo_water_puri_type_oth_txt"));

                s +=" demo_present_mem : "+  cDemo.getString(cDemo.getColumnIndex("demo_present_mem"));
                s +=" demo_expense : "+  cDemo.getString(cDemo.getColumnIndex("demo_expense"));
                s +=" demo_consent : "+  cDemo.getString(cDemo.getColumnIndex("demo_consent"));
                s +=" demo_kitchen : "+  cDemo.getString(cDemo.getColumnIndex("demo_kitchen"));
                s +=" demo_basin : "+  cDemo.getString(cDemo.getColumnIndex("demo_basin"));
                s +=" pt_participant : "+  cDemo.getString(cDemo.getColumnIndex("pt_participant"));
                s +=" pt_ans_gen : "+  cDemo.getString(cDemo.getColumnIndex("pt_ans_gen"));
                s +=" pt_pt_gen : "+  cDemo.getString(cDemo.getColumnIndex("pt_pt_gen"));
                s +=" pt_ans_name : "+  cDemo.getString(cDemo.getColumnIndex("pt_ans_name"));
                s +=" pt_ans_age : "+  cDemo.getString(cDemo.getColumnIndex("pt_ans_age"));
                s +=" pt_ans_rel : "+  cDemo.getString(cDemo.getColumnIndex("pt_ans_rel"));
                s +=" pt_ans_home : "+  cDemo.getString(cDemo.getColumnIndex("pt_ans_home"));
                s +=" pt_ans_block : "+  cDemo.getString(cDemo.getColumnIndex("pt_ans_block"));
                s +=" pt_ans_subblock : "+  cDemo.getString(cDemo.getColumnIndex("pt_ans_subblock"));
                s +=" pt_ans_ward : "+  cDemo.getString(cDemo.getColumnIndex("pt_ans_ward"));
                s +=" pt_ans_area : "+  cDemo.getString(cDemo.getColumnIndex("pt_ans_area"));
                s +=" pt_ans_mob : "+  cDemo.getString(cDemo.getColumnIndex("pt_ans_mob"));
                s +=" pt_pt_name : "+  cDemo.getString(cDemo.getColumnIndex("pt_pt_name"));
                s +=" pt_pt_age : "+  cDemo.getString(cDemo.getColumnIndex("pt_pt_age"));
                s +=" pt_pt_rel : "+  cDemo.getString(cDemo.getColumnIndex("pt_pt_rel"));
                s +=" pt_pt_home : "+  cDemo.getString(cDemo.getColumnIndex("pt_pt_home"));
                s +=" pt_pt_block : "+  cDemo.getString(cDemo.getColumnIndex("pt_pt_block"));
                s +=" pt_pt_subblock : "+  cDemo.getString(cDemo.getColumnIndex("pt_pt_subblock"));
                s +=" pt_pt_ward : "+  cDemo.getString(cDemo.getColumnIndex("pt_pt_ward"));
                s +=" pt_pt_area : "+  cDemo.getString(cDemo.getColumnIndex("pt_pt_area"));
                s +=" pt_pt_mob : "+  cDemo.getString(cDemo.getColumnIndex("pt_pt_mob"));
                s +=" pt_edu : "+  cDemo.getString(cDemo.getColumnIndex("pt_edu"));
                s +=" pt_edu_oth_txt : "+  cDemo.getString(cDemo.getColumnIndex("pt_edu_oth_txt"));
                s +=" pt_ans_dob : "+  cDemo.getString(cDemo.getColumnIndex("pt_ans_dob"));
                s +=" pt_pt_dob : "+  cDemo.getString(cDemo.getColumnIndex("pt_pt_dob"));
                s +=" pt_ans_dob_un : "+  cDemo.getString(cDemo.getColumnIndex("pt_ans_dob_un"));
                s +=" pt_ans_age_un : "+  cDemo.getString(cDemo.getColumnIndex("pt_ans_age_un"));
                s +=" pt_pt_dob_un : "+  cDemo.getString(cDemo.getColumnIndex("pt_pt_dob_un"));
                s +=" pt_pt_age_un : "+  cDemo.getString(cDemo.getColumnIndex("pt_pt_age_un"));
                s +=" pt_edu_il : "+  cDemo.getString(cDemo.getColumnIndex("pt_edu_il"));
                s +=" pt_edu_sig : "+  cDemo.getString(cDemo.getColumnIndex("pt_edu_sig"));
                s +=" pt_edu_un : "+  cDemo.getString(cDemo.getColumnIndex("pt_edu_un"));
                s +=" pt_edu_oth : "+  cDemo.getString(cDemo.getColumnIndex("pt_edu_oth"));
                s +=" pt_ocu : "+  cDemo.getString(cDemo.getColumnIndex("pt_ocu"));
                s +=" res_respiratory : "+  cDemo.getString(cDemo.getColumnIndex("res_respiratory"));
                s +=" res_fever : "+  cDemo.getString(cDemo.getColumnIndex("res_fever"));
                s +=" res_cough : "+  cDemo.getString(cDemo.getColumnIndex("res_cough"));
                s +=" res_asthma : "+  cDemo.getString(cDemo.getColumnIndex("res_asthma"));
                s +=" res_tired : "+  cDemo.getString(cDemo.getColumnIndex("res_tired"));
                s +=" res_body_pain : "+  cDemo.getString(cDemo.getColumnIndex("res_body_pain"));
                s +=" res_headache : "+  cDemo.getString(cDemo.getColumnIndex("res_headache"));
                s +=" res_test : "+  cDemo.getString(cDemo.getColumnIndex("res_test"));
                s +=" res_smell : "+  cDemo.getString(cDemo.getColumnIndex("res_smell"));
                s +=" res_throat_pain : "+  cDemo.getString(cDemo.getColumnIndex("res_throat_pain"));
                s +=" res_cold : "+  cDemo.getString(cDemo.getColumnIndex("res_cold"));
                s +=" res_diarrhoea : "+  cDemo.getString(cDemo.getColumnIndex("res_diarrhoea"));
                s +=" res_respiratory_date : "+  cDemo.getString(cDemo.getColumnIndex("res_respiratory_date"));
                s +=" res_fever_date : "+  cDemo.getString(cDemo.getColumnIndex("res_fever_date"));
                s +=" res_cough_date : "+  cDemo.getString(cDemo.getColumnIndex("res_cough_date"));
                s +=" res_asthma_date : "+  cDemo.getString(cDemo.getColumnIndex("res_asthma_date"));
                s +=" res_tired_date : "+  cDemo.getString(cDemo.getColumnIndex("res_tired_date"));
                s +=" res_body_pain_date : "+  cDemo.getString(cDemo.getColumnIndex("res_body_pain_date"));
                s +=" res_headache_date : "+  cDemo.getString(cDemo.getColumnIndex("res_headache_date"));
                s +=" res_test_date : "+  cDemo.getString(cDemo.getColumnIndex("res_test_date"));
                s +=" res_smell_date : "+  cDemo.getString(cDemo.getColumnIndex("res_smell_date"));
                s +=" res_throat_pain_date : "+  cDemo.getString(cDemo.getColumnIndex("res_throat_pain_date"));
                s +=" res_cold_date : "+  cDemo.getString(cDemo.getColumnIndex("res_cold_date"));
                s +=" res_diarrhoea_date : "+  cDemo.getString(cDemo.getColumnIndex("res_diarrhoea_date"));
                s +=" res_res_card : "+  cDemo.getString(cDemo.getColumnIndex("res_res_card"));
                s +=" his_covid_pt : "+  cDemo.getString(cDemo.getColumnIndex("his_covid_pt"));
                s +=" his_join : "+  cDemo.getString(cDemo.getColumnIndex("his_join"));
                s +=" his_test : "+  cDemo.getString(cDemo.getColumnIndex("his_test"));
                s +=" his_journey : "+  cDemo.getString(cDemo.getColumnIndex("his_journey"));
                s +=" his_health : "+  cDemo.getString(cDemo.getColumnIndex("his_health"));
                s +=" his_quack : "+  cDemo.getString(cDemo.getColumnIndex("his_quack"));
                s +=" his_test_date : "+  cDemo.getString(cDemo.getColumnIndex("his_test_date"));
                s +=" his_journey_from : "+  cDemo.getString(cDemo.getColumnIndex("his_journey_from"));
                s +=" his_journey_to : "+  cDemo.getString(cDemo.getColumnIndex("his_journey_to"));
                s +=" his_health_name : "+  cDemo.getString(cDemo.getColumnIndex("his_health_name"));
                s +=" his_quack_name : "+  cDemo.getString(cDemo.getColumnIndex("his_quack_name"));
                s +=" past_prev_dis : "+  cDemo.getString(cDemo.getColumnIndex("past_prev_dis"));
                s +=" past_fev : "+  cDemo.getString(cDemo.getColumnIndex("past_fev"));
                s +=" past_nasal : "+  cDemo.getString(cDemo.getColumnIndex("past_nasal"));
                s +=" past_cough : "+  cDemo.getString(cDemo.getColumnIndex("past_cough"));
                s +=" past_asthma : "+  cDemo.getString(cDemo.getColumnIndex("past_asthma"));
                s +=" past_vomit : "+  cDemo.getString(cDemo.getColumnIndex("past_vomit"));
                s +=" past_nausea : "+  cDemo.getString(cDemo.getColumnIndex("past_nausea"));
                s +=" past_diarr : "+  cDemo.getString(cDemo.getColumnIndex("past_diarr"));
                s +=" past_head : "+  cDemo.getString(cDemo.getColumnIndex("past_head"));
                s +=" past_rush : "+  cDemo.getString(cDemo.getColumnIndex("past_rush"));
                s +=" past_conj : "+  cDemo.getString(cDemo.getColumnIndex("past_conj"));
                s +=" past_musc : "+  cDemo.getString(cDemo.getColumnIndex("past_musc"));
                s +=" past_join : "+  cDemo.getString(cDemo.getColumnIndex("past_join"));
                s +=" past_hung : "+  cDemo.getString(cDemo.getColumnIndex("past_hung"));
                s +=" past_ageu : "+  cDemo.getString(cDemo.getColumnIndex("past_ageu"));
                s +=" past_smell : "+  cDemo.getString(cDemo.getColumnIndex("past_smell"));
                s +=" past_epis : "+  cDemo.getString(cDemo.getColumnIndex("past_epis"));
                s +=" past_tired : "+  cDemo.getString(cDemo.getColumnIndex("past_tired"));
                s +=" past_seizure : "+  cDemo.getString(cDemo.getColumnIndex("past_seizure"));
                s +=" past_faint : "+  cDemo.getString(cDemo.getColumnIndex("past_faint"));
                s +=" past_oth : "+  cDemo.getString(cDemo.getColumnIndex("past_oth"));
                s +=" past_fev_date : "+  cDemo.getString(cDemo.getColumnIndex("past_fev_date"));
                s +=" past_nasal_date : "+  cDemo.getString(cDemo.getColumnIndex("past_nasal_date"));
                s +=" past_cough_date : "+  cDemo.getString(cDemo.getColumnIndex("past_cough_date"));
                s +=" past_asthma_date : "+  cDemo.getString(cDemo.getColumnIndex("past_asthma_date"));
                s +=" past_vomit_date : "+  cDemo.getString(cDemo.getColumnIndex("past_vomit_date"));
                s +=" past_nausea_date : "+  cDemo.getString(cDemo.getColumnIndex("past_nausea_date"));
                s +=" past_diarr_date : "+  cDemo.getString(cDemo.getColumnIndex("past_diarr_date"));
                s +=" past_head_date : "+  cDemo.getString(cDemo.getColumnIndex("past_head_date"));
                s +=" past_rush_date : "+  cDemo.getString(cDemo.getColumnIndex("past_rush_date"));
                s +=" past_conj_date : "+  cDemo.getString(cDemo.getColumnIndex("past_conj_date"));
                s +=" past_musc_date : "+  cDemo.getString(cDemo.getColumnIndex("past_musc_date"));
                s +=" past_join_date : "+  cDemo.getString(cDemo.getColumnIndex("past_join_date"));
                s +=" past_hung_date : "+  cDemo.getString(cDemo.getColumnIndex("past_hung_date"));
                s +=" past_ageu_date : "+  cDemo.getString(cDemo.getColumnIndex("past_ageu_date"));
                s +=" past_smell_date : "+  cDemo.getString(cDemo.getColumnIndex("past_smell_date"));
                s +=" past_epis_date : "+  cDemo.getString(cDemo.getColumnIndex("past_epis_date"));
                s +=" past_tired_date : "+  cDemo.getString(cDemo.getColumnIndex("past_tired_date"));
                s +=" past_seizure_date : "+  cDemo.getString(cDemo.getColumnIndex("past_seizure_date"));
                s +=" past_faint_date : "+  cDemo.getString(cDemo.getColumnIndex("past_faint_date"));
                s +=" past_oth_date : "+  cDemo.getString(cDemo.getColumnIndex("past_oth_date"));
                s +=" past_fev_temp : "+  cDemo.getString(cDemo.getColumnIndex("past_fev_temp"));
                s +=" past_oth_txt : "+  cDemo.getString(cDemo.getColumnIndex("past_oth_txt"));
                s +=" treat_test : "+  cDemo.getString(cDemo.getColumnIndex("treat_test"));
                s +=" treat_treat : "+  cDemo.getString(cDemo.getColumnIndex("treat_treat"));
                s +=" treat_dis : "+  cDemo.getString(cDemo.getColumnIndex("treat_dis"));
                s +=" treat_adm : "+  cDemo.getString(cDemo.getColumnIndex("treat_adm"));
                s +=" treat_oth_test : "+  cDemo.getString(cDemo.getColumnIndex("treat_oth_test"));
                s +=" treat_oth_test_result : "+  cDemo.getString(cDemo.getColumnIndex("treat_oth_test_result"));
                s +=" treat_death : "+  cDemo.getString(cDemo.getColumnIndex("treat_death"));
                s +=" treat_test_date : "+  cDemo.getString(cDemo.getColumnIndex("treat_test_date"));
                s +=" treat_treat_date : "+  cDemo.getString(cDemo.getColumnIndex("treat_treat_date"));
                s +=" treat_adm_num : "+  cDemo.getString(cDemo.getColumnIndex("treat_adm_num"));
                s +=" treat_oth_test_date : "+  cDemo.getString(cDemo.getColumnIndex("treat_oth_test_date"));
                s +=" treat_treat_freq : "+  cDemo.getString(cDemo.getColumnIndex("treat_treat_freq"));
                s +=" treat_death_num : "+  cDemo.getString(cDemo.getColumnIndex("treat_death_num"));
                s +=" dengue_dengue : "+  cDemo.getString(cDemo.getColumnIndex("dengue_dengue"));
                s +=" dengue_dengue_day_type : "+  cDemo.getString(cDemo.getColumnIndex("dengue_dengue_day_type"));
                s +=" dengue_dengue_how : "+  cDemo.getString(cDemo.getColumnIndex("dengue_dengue_how"));
                s +=" dengue_dengue_hos : "+  cDemo.getString(cDemo.getColumnIndex("dengue_dengue_hos"));
                s +=" dengue_cikon : "+  cDemo.getString(cDemo.getColumnIndex("dengue_cikon"));
                s +=" dengue_cikon_day_type : "+  cDemo.getString(cDemo.getColumnIndex("dengue_cikon_day_type"));
                s +=" dengue_cikon_how : "+  cDemo.getString(cDemo.getColumnIndex("dengue_cikon_how"));
                s +=" dengue_cikon_hos : "+  cDemo.getString(cDemo.getColumnIndex("dengue_cikon_hos"));
                s +=" dengue_preg : "+  cDemo.getString(cDemo.getColumnIndex("dengue_preg"));
                s +=" dengue_trim : "+  cDemo.getString(cDemo.getColumnIndex("dengue_trim"));
                s +=" dengue_dengue_day : "+  cDemo.getString(cDemo.getColumnIndex("dengue_dengue_day"));
                s +=" dengue_cikon_day : "+  cDemo.getString(cDemo.getColumnIndex("dengue_cikon_day"));
                s +=" com_cancer : "+  cDemo.getString(cDemo.getColumnIndex("com_cancer"));
                s +=" com_dia : "+  cDemo.getString(cDemo.getColumnIndex("com_dia"));
                s +=" com_aids : "+  cDemo.getString(cDemo.getColumnIndex("com_aids"));
                s +=" com_heart : "+  cDemo.getString(cDemo.getColumnIndex("com_heart"));
                s +=" com_asthma : "+  cDemo.getString(cDemo.getColumnIndex("com_asthma"));
                s +=" com_resp : "+  cDemo.getString(cDemo.getColumnIndex("com_resp"));
                s +=" com_liver : "+  cDemo.getString(cDemo.getColumnIndex("com_liver"));
                s +=" com_hema : "+  cDemo.getString(cDemo.getColumnIndex("com_hema"));
                s +=" com_kidney : "+  cDemo.getString(cDemo.getColumnIndex("com_kidney"));
                s +=" com_nuro : "+  cDemo.getString(cDemo.getColumnIndex("com_nuro"));
                s +=" com_joint : "+  cDemo.getString(cDemo.getColumnIndex("com_joint"));
                s +=" com_smoke : "+  cDemo.getString(cDemo.getColumnIndex("com_smoke"));
                s +=" com_tobac : "+  cDemo.getString(cDemo.getColumnIndex("com_tobac"));
                s +=" com_oth : "+  cDemo.getString(cDemo.getColumnIndex("com_oth"));
                s +=" risk_mask : "+  cDemo.getString(cDemo.getColumnIndex("risk_mask"));
                s +=" risk_mask_type : "+  cDemo.getString(cDemo.getColumnIndex("risk_mask_type"));
                s +=" risk_hwash : "+  cDemo.getString(cDemo.getColumnIndex("risk_hwash"));
                s +=" risk_tsec : "+  cDemo.getString(cDemo.getColumnIndex("risk_tsec"));
                s +=" risk_cover : "+  cDemo.getString(cDemo.getColumnIndex("risk_cover"));
                s +=" risk_ctsec : "+  cDemo.getString(cDemo.getColumnIndex("risk_ctsec"));
                s +=" risk_inv : "+  cDemo.getString(cDemo.getColumnIndex("risk_inv"));
                s +=" risk_cmeet : "+  cDemo.getString(cDemo.getColumnIndex("risk_cmeet"));
                s +=" risk_cmeet_place : "+  cDemo.getString(cDemo.getColumnIndex("risk_cmeet_place"));
                s +=" risk_pt : "+  cDemo.getString(cDemo.getColumnIndex("risk_pt"));
                s +=" risk_sdist : "+  cDemo.getString(cDemo.getColumnIndex("risk_sdist"));
                s +=" risk_net_time : "+  cDemo.getString(cDemo.getColumnIndex("risk_net_time"));
                s +=" risk_media : "+  cDemo.getString(cDemo.getColumnIndex("risk_media"));
                s +=" risk_htips : "+  cDemo.getString(cDemo.getColumnIndex("risk_htips"));
                s +=" risk_kit : "+  cDemo.getString(cDemo.getColumnIndex("risk_kit"));
                s +=" risk_mask_oth_txt : "+  cDemo.getString(cDemo.getColumnIndex("risk_mask_oth_txt"));
                s +=" risk_mask_type_oth_txt : "+  cDemo.getString(cDemo.getColumnIndex("risk_mask_type_oth_txt"));
                s +=" risk_hwash_freq : "+  cDemo.getString(cDemo.getColumnIndex("risk_hwash_freq"));
                s +=" risk_cover_by_oth : "+  cDemo.getString(cDemo.getColumnIndex("risk_cover_by_oth"));
                s +=" risk_inv_day : "+  cDemo.getString(cDemo.getColumnIndex("risk_inv_day"));
                s +=" risk_cmeet_oth_txt : "+  cDemo.getString(cDemo.getColumnIndex("risk_cmeet_oth_txt"));
                s +=" risk_pt_by_oth : "+  cDemo.getString(cDemo.getColumnIndex("risk_pt_by_oth"));
                s +=" risk_mbite_oth : "+  cDemo.getString(cDemo.getColumnIndex("risk_mbite_oth"));
                s +=" risk_net_oth_txt : "+  cDemo.getString(cDemo.getColumnIndex("risk_net_oth_txt"));
                s +=" risk_inf_oth : "+  cDemo.getString(cDemo.getColumnIndex("risk_inf_oth"));
                s +=" risk_name : "+  cDemo.getString(cDemo.getColumnIndex("risk_name"));
                s +=" risk_kitorg_name : "+  cDemo.getString(cDemo.getColumnIndex("risk_kitorg_name"));
                s +=" risk_cmeet_day : "+  cDemo.getString(cDemo.getColumnIndex("risk_cmeet_day"));
                s +=" risk_elbow : "+  cDemo.getString(cDemo.getColumnIndex("risk_elbow"));
                s +=" risk_clth : "+  cDemo.getString(cDemo.getColumnIndex("risk_clth"));
                s +=" risk_palm : "+  cDemo.getString(cDemo.getColumnIndex("risk_palm"));
                s +=" risk_cough : "+  cDemo.getString(cDemo.getColumnIndex("risk_cough"));
                s +=" risk_touch : "+  cDemo.getString(cDemo.getColumnIndex("risk_touch"));
                s +=" risk_shake : "+  cDemo.getString(cDemo.getColumnIndex("risk_shake"));
                s +=" risk_inf : "+  cDemo.getString(cDemo.getColumnIndex("risk_inf"));
                s +=" risk_food : "+  cDemo.getString(cDemo.getColumnIndex("risk_food"));
                s +=" risk_govt : "+  cDemo.getString(cDemo.getColumnIndex("risk_govt"));
                s +=" risk_pvt : "+  cDemo.getString(cDemo.getColumnIndex("risk_pvt"));
                s +=" risk_net : "+  cDemo.getString(cDemo.getColumnIndex("risk_net"));
                s +=" risk_coil : "+  cDemo.getString(cDemo.getColumnIndex("risk_coil"));
                s +=" risk_dhup : "+  cDemo.getString(cDemo.getColumnIndex("risk_dhup"));
                s +=" risk_mbite_no : "+  cDemo.getString(cDemo.getColumnIndex("risk_mbite_no"));
                s +=" "+ MyDB.ISUPLOAD +" : "+cDemo.getString(cDemo.getColumnIndex(MyDB.ISUPLOAD));
                s +=" "+ MyDB.CREATED_AT +" : "+cDemo.getString(cDemo.getColumnIndex(MyDB.CREATED_AT));
                Log.d("Demo",s);
            }while (cDemo.moveToNext());
        }
        if (cfam.moveToFirst()){
            do {
                String s = MyDB.SEROID + cfam.getString(cfam.getColumnIndex(MyDB.SEROID));
                s +=" "+ MyDB.AGE +" : "+cfam.getInt(cfam.getColumnIndex(MyDB.AGE));
                s +=" "+ MyDB.SEX +" : "+cfam.getInt(cfam.getColumnIndex(MyDB.SEX));
                s +=" "+ MyDB.PRESENT +" : "+cfam.getInt(cfam.getColumnIndex(MyDB.PRESENT));
                s +=" "+ MyDB.ISUPLOAD +" : "+cfam.getString(cfam.getColumnIndex(MyDB.ISUPLOAD));
                s +=" "+ MyDB.CREATED_AT +" : "+cfam.getString(cfam.getColumnIndex(MyDB.CREATED_AT));
                Log.d("fam",s);
            }while (cfam.moveToNext());
        }
        if (cadm.moveToFirst()){
            do {
                String s = MyDB.SEROID + cadm.getString(cadm.getColumnIndex(MyDB.SEROID));
                s +=" adm_date : "+cadm.getInt(cadm.getColumnIndex("adm_date"));
                s +=" rel_date : "+cadm.getInt(cadm.getColumnIndex("rel_date"));
                s +=" icu : "+cadm.getInt(cadm.getColumnIndex("icu"));
                s +=" "+ MyDB.ISUPLOAD +" : "+cadm.getString(cadm.getColumnIndex(MyDB.ISUPLOAD));
                s +=" "+ MyDB.CREATED_AT +" : "+cadm.getString(cadm.getColumnIndex(MyDB.CREATED_AT));
                Log.d("adm",s);
            }while (cadm.moveToNext());
        }
        if (cdeath.moveToFirst()){
            do {
                String s = MyDB.SEROID + cdeath.getString(cdeath.getColumnIndex(MyDB.SEROID));
                s +=" death_date : "+cdeath.getInt(cdeath.getColumnIndex("death_date"));
                s +=" home : "+cdeath.getInt(cdeath.getColumnIndex("home"));
                s +=" hospital : "+cdeath.getInt(cdeath.getColumnIndex("hospital"));
                s +=" "+ MyDB.ISUPLOAD +" : "+cdeath.getString(cdeath.getColumnIndex(MyDB.ISUPLOAD));
                s +=" "+ MyDB.CREATED_AT +" : "+cdeath.getString(cdeath.getColumnIndex(MyDB.CREATED_AT));
                Log.d("cdeath",s);
            }while (cdeath.moveToNext());
        }

    }
}