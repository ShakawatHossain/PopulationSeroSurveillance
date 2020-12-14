package com.iedcr.populationserosurveillance;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.iedcr.populationserosurveillance.adapter.FamRecAdapter;
import com.iedcr.populationserosurveillance.adapter.ListRecAdapter;
import com.iedcr.populationserosurveillance.model.ModelDemographic;
import com.iedcr.populationserosurveillance.model.ModelFamilyMember;
import com.iedcr.populationserosurveillance.model.ModelList;
import com.iedcr.populationserosurveillance.model.MyDB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    ArrayList<ModelList>  modelLists;
    ListRecAdapter listRecAdapter;
    LinearLayoutManager linearLayoutManager;
    TextView txt;
    Button sync;
    MyDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        txt = (TextView) findViewById(R.id.txt);
        linearLayoutManager = new LinearLayoutManager(ListActivity.this);
        sync=(Button) findViewById(R.id.sync);
        sync.setOnClickListener(clickListener);
        db = new MyDB(ListActivity.this);
        setSupportActionBar(toolbar);
        modelLists = new ArrayList<>();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelDemographic.clear();
                ModelFamilyMember.clearFamilyMemberContents();
                startActivity(new Intent(ListActivity.this,MainActivity.class));
            }
        });
        checkLogin();
    }
    private void checkLogin(){
        if(ModelDemographic.user_id==null || ModelDemographic.user_id.isEmpty()){
            startActivity(new Intent(ListActivity.this,LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView = (RecyclerView) findViewById(R.id.rec);

        new FacSave().execute();
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==sync.getId()){
                if (modelLists.size()>0){
                    uploadRecord();
                    btn_toggle();
                }
            }
        }
    };
    private void btn_toggle(){
        if (((ProgressBar) findViewById(R.id.progress)).getVisibility()!=View.VISIBLE)
            ((ProgressBar) findViewById(R.id.progress)).setVisibility(View.VISIBLE);
        else
            ((ProgressBar) findViewById(R.id.progress)).setVisibility(View.GONE);
    }
    private void uploadRecord(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String link = "http://119.148.17.100:8080/seroslum/insert.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        btn_toggle();
                        Log.d("Upload Record",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int success = jsonObject.getInt("success");
                            String msg = jsonObject.getString("message");
                            int successf = jsonObject.getInt("successf");
                            String msgf = jsonObject.getString("messagef");
                            if(success == 200) {
                                Toast.makeText(ListActivity.this,msg,Toast.LENGTH_SHORT).show();
                                db.updateDemo();
                                db.updateAdm();
                                db.updateDeath();
                                startActivity(new Intent(ListActivity.this,ListActivity.class));
                                finish();
                            }else{
                                Toast.makeText(ListActivity.this,msg,Toast.LENGTH_SHORT).show();
                            }
                            if(successf == 200) {
                                Toast.makeText(ListActivity.this,msgf,Toast.LENGTH_SHORT).show();
                                db.updateFam();
                            }else{
                                Toast.makeText(ListActivity.this,msgf,Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                btn_toggle();
                Toast.makeText(ListActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<String,String>();
                JSONObject jsonObject = new JSONObject();
                JSONArray jsonArray = new JSONArray();
                JSONArray jsonArrayF = new JSONArray();
                JSONArray jsonArrayAdm = new JSONArray();
                JSONArray jsonArraydeath = new JSONArray();
                try {
                    for(ModelList mlist: modelLists){
                        JSONObject jobj = new JSONObject();
                        jobj.put("sero_id",mlist.sero_id);
                        jobj.put("created_at",mlist.created_at);
                        for (String key:mlist.demoParams.keySet()) jobj.put("demo_"+key,mlist.demoParams.get(key));
                        for (String key:mlist.ptInfoParams.keySet()) jobj.put("pt_"+key,mlist.ptInfoParams.get(key));
                        for (String key:mlist.resParams.keySet()) jobj.put("res_"+key,mlist.resParams.get(key));
                        for (String key:mlist.hisParams.keySet()) jobj.put("his_"+key,mlist.hisParams.get(key));
                        for (String key:mlist.pastDisParams.keySet()) jobj.put("past_"+key,mlist.pastDisParams.get(key));
                        for (String key:mlist.treatParams.keySet()) jobj.put("treat_"+key,mlist.treatParams.get(key));
                        for (String key:mlist.dengueParams.keySet()) jobj.put("dengue_"+key,mlist.dengueParams.get(key));
                        for (String key:mlist.comParams.keySet()) jobj.put("com_"+key,mlist.comParams.get(key));
                        for (String key:mlist.riskParams.keySet()) jobj.put("risk_"+key,mlist.riskParams.get(key));
                        jsonArray.put(jobj);
                    }
                    jsonObject.put("demo",jsonArray);
                    Cursor fam = db.get_fam();
                    if(fam.moveToFirst()){
                        do{
                            JSONObject jobj = new JSONObject();
                            jobj.put("sero_id",fam.getString(fam.getColumnIndex(MyDB.SEROID)));
                            jobj.put("is_present",fam.getString(fam.getColumnIndex(MyDB.PRESENT)));
                            jobj.put("age",fam.getInt(fam.getColumnIndex(MyDB.AGE)));
                            jobj.put("sex",fam.getInt(fam.getColumnIndex(MyDB.SEX)));
                            jobj.put("created_at",fam.getString(fam.getColumnIndex(MyDB.CREATED_AT)));
                            jsonArrayF.put(jobj);
                        }while (fam.moveToNext());
                    }
                    jsonObject.put("fam",jsonArrayF);
                    Cursor adm = db.get_adm();
                    if(adm.moveToFirst()){
                        do{
                            JSONObject jobj = new JSONObject();
                            jobj.put("sero_id",adm.getString(adm.getColumnIndex(MyDB.SEROID)));
                            jobj.put("adm_date",adm.getString(adm.getColumnIndex("adm_date")));
                            jobj.put("rel_date",adm.getString(adm.getColumnIndex("rel_date")));
                            jobj.put("icu",adm.getInt(adm.getColumnIndex("icu")));
                            jobj.put("created_at",adm.getString(adm.getColumnIndex(MyDB.CREATED_AT)));
                            jsonArrayAdm.put(jobj);
                        }while (adm.moveToNext());
                    }
                    jsonObject.put("adm",jsonArrayAdm);
                    Cursor death = db.get_death();
                    if(death.moveToFirst()){
                        do{
                            JSONObject jobj = new JSONObject();
                            jobj.put("sero_id",death.getString(death.getColumnIndex(MyDB.SEROID)));
                            jobj.put("death_date",death.getString(death.getColumnIndex("death_date")));
                            jobj.put("home",death.getString(death.getColumnIndex("home")));
                            jobj.put("hospital",death.getInt(death.getColumnIndex("hospital")));
                            jobj.put("created_at",death.getString(death.getColumnIndex(MyDB.CREATED_AT)));
                            jsonArraydeath.put(jobj);
                        }while (death.moveToNext());
                    }
                    jsonObject.put("death",jsonArraydeath);
                }catch (JSONException ex){
                    ex.printStackTrace();
                }
                params.put("request",jsonObject.toString());
                return params;
            }

        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public class FacSave extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            Cursor cDemo = db.get_demo();
//            Cursor fam = db.get_fam();
            int count =1;
            if(cDemo.moveToFirst()){
                do {
                    ModelList modelList = new ModelList();
                    modelList.sero_id = cDemo.getString(cDemo.getColumnIndex(MyDB.SEROID));
                    modelList.created_at = cDemo.getString(cDemo.getColumnIndex(MyDB.CREATED_AT));
                    modelList.demoParams.put("h_no", cDemo.getString(cDemo.getColumnIndex("demo_h_no")));
                    modelList.demoParams.put("h_name", cDemo.getString(cDemo.getColumnIndex("demo_h_name")));
                    modelList.demoParams.put("h_mob", cDemo.getString(cDemo.getColumnIndex("demo_h_mob")));
                    modelList.demoParams.put("fam_qty", cDemo.getString(cDemo.getColumnIndex("demo_fam_qty")));
                    modelList.demoParams.put("room_qty", cDemo.getString(cDemo.getColumnIndex("demo_room_qty")));
                    modelList.demoParams.put("toilet_qty", cDemo.getString(cDemo.getColumnIndex("demo_toilet_qty")));
                    modelList.demoParams.put("drink_src", cDemo.getString(cDemo.getColumnIndex("demo_drink_src")));
                    modelList.demoParams.put("drink_src_type", cDemo.getString(cDemo.getColumnIndex("demo_drink_src_type")));
                    modelList.demoParams.put("boil", cDemo.getString(cDemo.getColumnIndex("demo_boil")));
                    modelList.demoParams.put("filter", cDemo.getString(cDemo.getColumnIndex("demo_filter")));
                    modelList.demoParams.put("water_puri_type_oth", cDemo.getString(cDemo.getColumnIndex("demo_water_puri_type_oth")));
                    modelList.demoParams.put("water_puri_type_oth_txt", cDemo.getString(cDemo.getColumnIndex("demo_water_puri_type_oth_txt")));
                    modelList.demoParams.put("present_mem", cDemo.getString(cDemo.getColumnIndex("demo_present_mem")));
                    modelList.demoParams.put("expense", cDemo.getString(cDemo.getColumnIndex("demo_expense")));
                    modelList.demoParams.put("consent", cDemo.getString(cDemo.getColumnIndex("demo_consent")));
                    modelList.demoParams.put("kitchen", cDemo.getString(cDemo.getColumnIndex("demo_kitchen")));
                    modelList.demoParams.put("basin", cDemo.getString(cDemo.getColumnIndex("demo_basin")));
                    modelList.ptInfoParams.put("participant", cDemo.getString(cDemo.getColumnIndex("pt_participant")));
                    modelList.ptInfoParams.put("ans_gen", cDemo.getString(cDemo.getColumnIndex("pt_ans_gen")));
                    modelList.ptInfoParams.put("pt_gen", cDemo.getString(cDemo.getColumnIndex("pt_pt_gen")));
                    modelList.ptInfoParams.put("ans_name", cDemo.getString(cDemo.getColumnIndex("pt_ans_name")));
                    modelList.ptInfoParams.put("ans_age", cDemo.getString(cDemo.getColumnIndex("pt_ans_age")));
                    modelList.ptInfoParams.put("ans_rel", cDemo.getString(cDemo.getColumnIndex("pt_ans_rel")));
                    modelList.ptInfoParams.put("ans_home", cDemo.getString(cDemo.getColumnIndex("pt_ans_home")));
                    modelList.ptInfoParams.put("ans_block", cDemo.getString(cDemo.getColumnIndex("pt_ans_block")));
                    modelList.ptInfoParams.put("ans_subblock", cDemo.getString(cDemo.getColumnIndex("pt_ans_subblock")));
                    modelList.ptInfoParams.put("ans_ward", cDemo.getString(cDemo.getColumnIndex("pt_ans_ward")));
                    modelList.ptInfoParams.put("ans_area", cDemo.getString(cDemo.getColumnIndex("pt_ans_area")));
                    modelList.ptInfoParams.put("ans_mob", cDemo.getString(cDemo.getColumnIndex("pt_ans_mob")));
                    modelList.ptInfoParams.put("pt_name", cDemo.getString(cDemo.getColumnIndex("pt_pt_name")));
                    modelList.ptInfoParams.put("pt_age", cDemo.getString(cDemo.getColumnIndex("pt_pt_age")));
                    modelList.ptInfoParams.put("pt_rel", cDemo.getString(cDemo.getColumnIndex("pt_pt_rel")));
                    modelList.ptInfoParams.put("pt_home", cDemo.getString(cDemo.getColumnIndex("pt_pt_home")));
                    modelList.ptInfoParams.put("pt_block", cDemo.getString(cDemo.getColumnIndex("pt_pt_block")));
                    modelList.ptInfoParams.put("pt_subblock", cDemo.getString(cDemo.getColumnIndex("pt_pt_subblock")));
                    modelList.ptInfoParams.put("pt_ward", cDemo.getString(cDemo.getColumnIndex("pt_pt_ward")));
                    modelList.ptInfoParams.put("pt_area", cDemo.getString(cDemo.getColumnIndex("pt_pt_area")));
                    modelList.ptInfoParams.put("pt_mob", cDemo.getString(cDemo.getColumnIndex("pt_pt_mob")));
                    modelList.ptInfoParams.put("edu", cDemo.getString(cDemo.getColumnIndex("pt_edu")));
                    modelList.ptInfoParams.put("edu_oth_txt", cDemo.getString(cDemo.getColumnIndex("pt_edu_oth_txt")));
                    modelList.ptInfoParams.put("ans_dob", cDemo.getString(cDemo.getColumnIndex("pt_ans_dob")));
                    modelList.ptInfoParams.put("pt_dob", cDemo.getString(cDemo.getColumnIndex("pt_pt_dob")));
                    modelList.ptInfoParams.put("ans_dob_un", cDemo.getString(cDemo.getColumnIndex("pt_ans_dob_un")));
                    modelList.ptInfoParams.put("ans_age_un", cDemo.getString(cDemo.getColumnIndex("pt_ans_age_un")));
                    modelList.ptInfoParams.put("pt_dob_un", cDemo.getString(cDemo.getColumnIndex("pt_pt_dob_un")));
                    modelList.ptInfoParams.put("pt_age_un", cDemo.getString(cDemo.getColumnIndex("pt_pt_age_un")));
                    modelList.ptInfoParams.put("edu_il", cDemo.getString(cDemo.getColumnIndex("pt_edu_il")));
                    modelList.ptInfoParams.put("edu_sig", cDemo.getString(cDemo.getColumnIndex("pt_edu_sig")));
                    modelList.ptInfoParams.put("edu_un", cDemo.getString(cDemo.getColumnIndex("pt_edu_un")));
                    modelList.ptInfoParams.put("edu_oth", cDemo.getString(cDemo.getColumnIndex("pt_edu_oth")));
                    modelList.ptInfoParams.put("ocu", cDemo.getString(cDemo.getColumnIndex("pt_ocu")));
                    modelList.resParams.put("respiratory", cDemo.getString(cDemo.getColumnIndex("res_respiratory")));
                    modelList.resParams.put("fever", cDemo.getString(cDemo.getColumnIndex("res_fever")));
                    modelList.resParams.put("cough", cDemo.getString(cDemo.getColumnIndex("res_cough")));
                    modelList.resParams.put("asthma", cDemo.getString(cDemo.getColumnIndex("res_asthma")));
                    modelList.resParams.put("tired", cDemo.getString(cDemo.getColumnIndex("res_tired")));
                    modelList.resParams.put("body_pain", cDemo.getString(cDemo.getColumnIndex("res_body_pain")));
                    modelList.resParams.put("headache", cDemo.getString(cDemo.getColumnIndex("res_headache")));
                    modelList.resParams.put("test", cDemo.getString(cDemo.getColumnIndex("res_test")));
                    modelList.resParams.put("smell", cDemo.getString(cDemo.getColumnIndex("res_smell")));
                    modelList.resParams.put("throat_pain", cDemo.getString(cDemo.getColumnIndex("res_throat_pain")));
                    modelList.resParams.put("cold", cDemo.getString(cDemo.getColumnIndex("res_cold")));
                    modelList.resParams.put("diarrhoea", cDemo.getString(cDemo.getColumnIndex("res_diarrhoea")));
                    modelList.resParams.put("respiratory_date", cDemo.getString(cDemo.getColumnIndex("res_respiratory_date")));
                    modelList.resParams.put("fever_date", cDemo.getString(cDemo.getColumnIndex("res_fever_date")));
                    modelList.resParams.put("cough_date", cDemo.getString(cDemo.getColumnIndex("res_cough_date")));
                    modelList.resParams.put("asthma_date", cDemo.getString(cDemo.getColumnIndex("res_asthma_date")));
                    modelList.resParams.put("tired_date", cDemo.getString(cDemo.getColumnIndex("res_tired_date")));
                    modelList.resParams.put("body_pain_date", cDemo.getString(cDemo.getColumnIndex("res_body_pain_date")));
                    modelList.resParams.put("headache_date", cDemo.getString(cDemo.getColumnIndex("res_headache_date")));
                    modelList.resParams.put("test_date", cDemo.getString(cDemo.getColumnIndex("res_test_date")));
                    modelList.resParams.put("smell_date", cDemo.getString(cDemo.getColumnIndex("res_smell_date")));
                    modelList.resParams.put("throat_pain_date", cDemo.getString(cDemo.getColumnIndex("res_throat_pain_date")));
                    modelList.resParams.put("cold_date", cDemo.getString(cDemo.getColumnIndex("res_cold_date")));
                    modelList.resParams.put("diarrhoea_date", cDemo.getString(cDemo.getColumnIndex("res_diarrhoea_date")));
                    modelList.resParams.put("res_card", cDemo.getString(cDemo.getColumnIndex("res_res_card")));
                    modelList.hisParams.put("covid_pt", cDemo.getString(cDemo.getColumnIndex("his_covid_pt")));
                    modelList.hisParams.put("join", cDemo.getString(cDemo.getColumnIndex("his_join")));
                    modelList.hisParams.put("test", cDemo.getString(cDemo.getColumnIndex("his_test")));
                    modelList.hisParams.put("journey", cDemo.getString(cDemo.getColumnIndex("his_journey")));
                    modelList.hisParams.put("health", cDemo.getString(cDemo.getColumnIndex("his_health")));
                    modelList.hisParams.put("quack", cDemo.getString(cDemo.getColumnIndex("his_quack")));
                    modelList.hisParams.put("test_date", cDemo.getString(cDemo.getColumnIndex("his_test_date")));
                    modelList.hisParams.put("journey_from", cDemo.getString(cDemo.getColumnIndex("his_journey_from")));
                    modelList.hisParams.put("journey_to", cDemo.getString(cDemo.getColumnIndex("his_journey_to")));
                    modelList.hisParams.put("health_name", cDemo.getString(cDemo.getColumnIndex("his_health_name")));
                    modelList.hisParams.put("quack_name", cDemo.getString(cDemo.getColumnIndex("his_quack_name")));
                    modelList.pastDisParams.put("prev_dis", cDemo.getString(cDemo.getColumnIndex("past_prev_dis")));
                    modelList.pastDisParams.put("fev", cDemo.getString(cDemo.getColumnIndex("past_fev")));
                    modelList.pastDisParams.put("shivr", cDemo.getString(cDemo.getColumnIndex("past_shivr")));
                    modelList.pastDisParams.put("nasal", cDemo.getString(cDemo.getColumnIndex("past_nasal")));
                    modelList.pastDisParams.put("cough", cDemo.getString(cDemo.getColumnIndex("past_cough")));
                    modelList.pastDisParams.put("asthma", cDemo.getString(cDemo.getColumnIndex("past_asthma")));
                    modelList.pastDisParams.put("vomit", cDemo.getString(cDemo.getColumnIndex("past_vomit")));
                    modelList.pastDisParams.put("nausea", cDemo.getString(cDemo.getColumnIndex("past_nausea")));
                    modelList.pastDisParams.put("diarr", cDemo.getString(cDemo.getColumnIndex("past_diarr")));
                    modelList.pastDisParams.put("head", cDemo.getString(cDemo.getColumnIndex("past_head")));
                    modelList.pastDisParams.put("rush", cDemo.getString(cDemo.getColumnIndex("past_rush")));
                    modelList.pastDisParams.put("conj", cDemo.getString(cDemo.getColumnIndex("past_conj")));
                    modelList.pastDisParams.put("musc", cDemo.getString(cDemo.getColumnIndex("past_musc")));
                    modelList.pastDisParams.put("join", cDemo.getString(cDemo.getColumnIndex("past_join")));
                    modelList.pastDisParams.put("hung", cDemo.getString(cDemo.getColumnIndex("past_hung")));
                    modelList.pastDisParams.put("ageu", cDemo.getString(cDemo.getColumnIndex("past_ageu")));
                    modelList.pastDisParams.put("smell", cDemo.getString(cDemo.getColumnIndex("past_smell")));
                    modelList.pastDisParams.put("epis", cDemo.getString(cDemo.getColumnIndex("past_epis")));
                    modelList.pastDisParams.put("tired", cDemo.getString(cDemo.getColumnIndex("past_tired")));
                    modelList.pastDisParams.put("seizure", cDemo.getString(cDemo.getColumnIndex("past_seizure")));
                    modelList.pastDisParams.put("faint", cDemo.getString(cDemo.getColumnIndex("past_faint")));
                    modelList.pastDisParams.put("oth", cDemo.getString(cDemo.getColumnIndex("past_oth")));
                    modelList.pastDisParams.put("fev_date", cDemo.getString(cDemo.getColumnIndex("past_fev_date")));
                    modelList.pastDisParams.put("nasal_date", cDemo.getString(cDemo.getColumnIndex("past_nasal_date")));
                    modelList.pastDisParams.put("cough_date", cDemo.getString(cDemo.getColumnIndex("past_cough_date")));
                    modelList.pastDisParams.put("asthma_date", cDemo.getString(cDemo.getColumnIndex("past_asthma_date")));
                    modelList.pastDisParams.put("vomit_date", cDemo.getString(cDemo.getColumnIndex("past_vomit_date")));
                    modelList.pastDisParams.put("nausea_date", cDemo.getString(cDemo.getColumnIndex("past_nausea_date")));
                    modelList.pastDisParams.put("diarr_date", cDemo.getString(cDemo.getColumnIndex("past_diarr_date")));
                    modelList.pastDisParams.put("head_date", cDemo.getString(cDemo.getColumnIndex("past_head_date")));
                    modelList.pastDisParams.put("rush_date", cDemo.getString(cDemo.getColumnIndex("past_rush_date")));
                    modelList.pastDisParams.put("conj_date", cDemo.getString(cDemo.getColumnIndex("past_conj_date")));
                    modelList.pastDisParams.put("musc_date", cDemo.getString(cDemo.getColumnIndex("past_musc_date")));
                    modelList.pastDisParams.put("join_date", cDemo.getString(cDemo.getColumnIndex("past_join_date")));
                    modelList.pastDisParams.put("hung_date", cDemo.getString(cDemo.getColumnIndex("past_hung_date")));
                    modelList.pastDisParams.put("ageu_date", cDemo.getString(cDemo.getColumnIndex("past_ageu_date")));
                    modelList.pastDisParams.put("smell_date", cDemo.getString(cDemo.getColumnIndex("past_smell_date")));
                    modelList.pastDisParams.put("epis_date", cDemo.getString(cDemo.getColumnIndex("past_epis_date")));
                    modelList.pastDisParams.put("tired_date", cDemo.getString(cDemo.getColumnIndex("past_tired_date")));
                    modelList.pastDisParams.put("seizure_date", cDemo.getString(cDemo.getColumnIndex("past_seizure_date")));
                    modelList.pastDisParams.put("faint_date", cDemo.getString(cDemo.getColumnIndex("past_faint_date")));
                    modelList.pastDisParams.put("oth_date", cDemo.getString(cDemo.getColumnIndex("past_oth_date")));
                    modelList.pastDisParams.put("fev_temp", cDemo.getString(cDemo.getColumnIndex("past_fev_temp")));
                    modelList.pastDisParams.put("oth_txt", cDemo.getString(cDemo.getColumnIndex("past_oth_txt")));
                    modelList.treatParams.put("test", cDemo.getString(cDemo.getColumnIndex("treat_test")));
                    modelList.treatParams.put("treat", cDemo.getString(cDemo.getColumnIndex("treat_treat")));
                    modelList.treatParams.put("dis", cDemo.getString(cDemo.getColumnIndex("treat_dis")));
                    modelList.treatParams.put("adm", cDemo.getString(cDemo.getColumnIndex("treat_adm")));
                    modelList.treatParams.put("oth_test", cDemo.getString(cDemo.getColumnIndex("treat_oth_test")));
                    modelList.treatParams.put("oth_test_result", cDemo.getString(cDemo.getColumnIndex("treat_oth_test_result")));
                    modelList.treatParams.put("death", cDemo.getString(cDemo.getColumnIndex("treat_death")));
                    modelList.treatParams.put("death_num", cDemo.getString(cDemo.getColumnIndex("treat_death_num")));
                    modelList.treatParams.put("test_date", cDemo.getString(cDemo.getColumnIndex("treat_test_date")));
                    modelList.treatParams.put("treat_date", cDemo.getString(cDemo.getColumnIndex("treat_treat_date")));
                    modelList.treatParams.put("adm_num", cDemo.getString(cDemo.getColumnIndex("treat_adm_num")));
                    modelList.treatParams.put("oth_test_date", cDemo.getString(cDemo.getColumnIndex("treat_oth_test_date")));
                    modelList.treatParams.put("treat_freq", cDemo.getString(cDemo.getColumnIndex("treat_treat_freq")));
                    modelList.dengueParams.put("dengue", cDemo.getString(cDemo.getColumnIndex("dengue_dengue")));
                    modelList.dengueParams.put("dengue_day_type", cDemo.getString(cDemo.getColumnIndex("dengue_dengue_day_type")));
                    modelList.dengueParams.put("dengue_how", cDemo.getString(cDemo.getColumnIndex("dengue_dengue_how")));
                    modelList.dengueParams.put("dengue_hos", cDemo.getString(cDemo.getColumnIndex("dengue_dengue_hos")));
                    modelList.dengueParams.put("cikon", cDemo.getString(cDemo.getColumnIndex("dengue_cikon")));
                    modelList.dengueParams.put("cikon_day_type", cDemo.getString(cDemo.getColumnIndex("dengue_cikon_day_type")));
                    modelList.dengueParams.put("cikon_how", cDemo.getString(cDemo.getColumnIndex("dengue_cikon_how")));
                    modelList.dengueParams.put("cikon_hos", cDemo.getString(cDemo.getColumnIndex("dengue_cikon_hos")));
                    modelList.dengueParams.put("preg", cDemo.getString(cDemo.getColumnIndex("dengue_preg")));
                    modelList.dengueParams.put("trim", cDemo.getString(cDemo.getColumnIndex("dengue_trim")));
                    modelList.dengueParams.put("dengue_day", cDemo.getString(cDemo.getColumnIndex("dengue_dengue_day")));
                    modelList.dengueParams.put("cikon_day", cDemo.getString(cDemo.getColumnIndex("dengue_cikon_day")));
                    modelList.comParams.put("cancer", cDemo.getString(cDemo.getColumnIndex("com_cancer")));
                    modelList.comParams.put("dia", cDemo.getString(cDemo.getColumnIndex("com_dia")));
                    modelList.comParams.put("aids", cDemo.getString(cDemo.getColumnIndex("com_aids")));
                    modelList.comParams.put("heart", cDemo.getString(cDemo.getColumnIndex("com_heart")));
                    modelList.comParams.put("asthma", cDemo.getString(cDemo.getColumnIndex("com_asthma")));
                    modelList.comParams.put("resp", cDemo.getString(cDemo.getColumnIndex("com_resp")));
                    modelList.comParams.put("liver", cDemo.getString(cDemo.getColumnIndex("com_liver")));
                    modelList.comParams.put("hema", cDemo.getString(cDemo.getColumnIndex("com_hema")));
                    modelList.comParams.put("kidney", cDemo.getString(cDemo.getColumnIndex("com_kidney")));
                    modelList.comParams.put("nuro", cDemo.getString(cDemo.getColumnIndex("com_nuro")));
                    modelList.comParams.put("joint", cDemo.getString(cDemo.getColumnIndex("com_joint")));
                    modelList.comParams.put("smoke", cDemo.getString(cDemo.getColumnIndex("com_smoke")));
                    modelList.comParams.put("tobac", cDemo.getString(cDemo.getColumnIndex("com_tobac")));
                    modelList.comParams.put("oth", cDemo.getString(cDemo.getColumnIndex("com_oth")));
                    modelList.riskParams.put("mask", cDemo.getString(cDemo.getColumnIndex("risk_mask")));
                    modelList.riskParams.put("mask_type", cDemo.getString(cDemo.getColumnIndex("risk_mask_type")));
                    modelList.riskParams.put("hwash", cDemo.getString(cDemo.getColumnIndex("risk_hwash")));
                    modelList.riskParams.put("tsec", cDemo.getString(cDemo.getColumnIndex("risk_tsec")));
                    modelList.riskParams.put("cover", cDemo.getString(cDemo.getColumnIndex("risk_cover")));
                    modelList.riskParams.put("ctsec", cDemo.getString(cDemo.getColumnIndex("risk_ctsec")));
                    modelList.riskParams.put("inv", cDemo.getString(cDemo.getColumnIndex("risk_inv")));
                    modelList.riskParams.put("cmeet", cDemo.getString(cDemo.getColumnIndex("risk_cmeet")));
                    modelList.riskParams.put("cmeet_place", cDemo.getString(cDemo.getColumnIndex("risk_cmeet_place")));
                    modelList.riskParams.put("pt", cDemo.getString(cDemo.getColumnIndex("risk_pt")));
                    modelList.riskParams.put("sdist", cDemo.getString(cDemo.getColumnIndex("risk_sdist")));
                    modelList.riskParams.put("net_time", cDemo.getString(cDemo.getColumnIndex("risk_net_time")));
                    modelList.riskParams.put("media", cDemo.getString(cDemo.getColumnIndex("risk_media")));
                    modelList.riskParams.put("htips", cDemo.getString(cDemo.getColumnIndex("risk_htips")));
                    modelList.riskParams.put("kit", cDemo.getString(cDemo.getColumnIndex("risk_kit")));
                    modelList.riskParams.put("mask_oth_txt", cDemo.getString(cDemo.getColumnIndex("risk_mask_oth_txt")));
                    modelList.riskParams.put("mask_type_oth_txt", cDemo.getString(cDemo.getColumnIndex("risk_mask_type_oth_txt")));
                    modelList.riskParams.put("hwash_freq", cDemo.getString(cDemo.getColumnIndex("risk_hwash_freq")));
                    modelList.riskParams.put("cover_by_oth", cDemo.getString(cDemo.getColumnIndex("risk_cover_by_oth")));
                    modelList.riskParams.put("inv_day", cDemo.getString(cDemo.getColumnIndex("risk_inv_day")));
                    modelList.riskParams.put("cmeet_oth_txt", cDemo.getString(cDemo.getColumnIndex("risk_cmeet_oth_txt")));
                    modelList.riskParams.put("pt_by_oth", cDemo.getString(cDemo.getColumnIndex("risk_pt_by_oth")));
                    modelList.riskParams.put("mbite_oth", cDemo.getString(cDemo.getColumnIndex("risk_mbite_oth")));
                    modelList.riskParams.put("net_oth_txt", cDemo.getString(cDemo.getColumnIndex("risk_net_oth_txt")));
                    modelList.riskParams.put("inf_oth", cDemo.getString(cDemo.getColumnIndex("risk_inf_oth")));
                    modelList.riskParams.put("name", cDemo.getString(cDemo.getColumnIndex("risk_name")));
                    modelList.riskParams.put("kitorg_name", cDemo.getString(cDemo.getColumnIndex("risk_kitorg_name")));
                    modelList.riskParams.put("cmeet_day", cDemo.getString(cDemo.getColumnIndex("risk_cmeet_day")));
                    modelList.riskParams.put("elbow", cDemo.getString(cDemo.getColumnIndex("risk_elbow")));
                    modelList.riskParams.put("clth", cDemo.getString(cDemo.getColumnIndex("risk_clth")));
                    modelList.riskParams.put("palm", cDemo.getString(cDemo.getColumnIndex("risk_palm")));
                    modelList.riskParams.put("cough", cDemo.getString(cDemo.getColumnIndex("risk_cough")));
                    modelList.riskParams.put("touch", cDemo.getString(cDemo.getColumnIndex("risk_touch")));
                    modelList.riskParams.put("shake", cDemo.getString(cDemo.getColumnIndex("risk_shake")));
                    modelList.riskParams.put("inf", cDemo.getString(cDemo.getColumnIndex("risk_inf")));
                    modelList.riskParams.put("food", cDemo.getString(cDemo.getColumnIndex("risk_food")));
                    modelList.riskParams.put("govt", cDemo.getString(cDemo.getColumnIndex("risk_govt")));
                    modelList.riskParams.put("pvt", cDemo.getString(cDemo.getColumnIndex("risk_pvt")));
                    modelList.riskParams.put("net", cDemo.getString(cDemo.getColumnIndex("risk_net")));
                    modelList.riskParams.put("coil", cDemo.getString(cDemo.getColumnIndex("risk_coil")));
                    modelList.riskParams.put("dhup", cDemo.getString(cDemo.getColumnIndex("risk_dhup")));
                    modelList.riskParams.put("mbite_no", cDemo.getString(cDemo.getColumnIndex("risk_mbite_no")));
                    modelLists.add(modelList);
                }while (cDemo.moveToNext());
            }
//            listRecAdapter.notifyDataSetChanged();

//            if(fam.moveToFirst()){
//                do {
//
//                }while (fam.moveToNext());
//            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (modelLists.size()==0){
                if (txt.getVisibility()!=View.VISIBLE)
                    txt.setVisibility(View.VISIBLE);
            }else{
                if (txt.getVisibility()==View.VISIBLE)
                    txt.setVisibility(View.GONE);
            }
            recyclerView.setLayoutManager(linearLayoutManager);
            listRecAdapter = new ListRecAdapter(ListActivity.this,recyclerView,ListActivity.this,modelLists);
            recyclerView.setAdapter(listRecAdapter);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}