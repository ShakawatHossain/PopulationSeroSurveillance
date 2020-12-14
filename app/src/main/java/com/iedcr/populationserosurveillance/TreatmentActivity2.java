package com.iedcr.populationserosurveillance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iedcr.populationserosurveillance.adapter.AdmRecAdapter;
import com.iedcr.populationserosurveillance.adapter.DeathRecAdapter;
import com.iedcr.populationserosurveillance.model.AdmContent;
import com.iedcr.populationserosurveillance.model.DeathContent;
import com.iedcr.populationserosurveillance.model.FamilyMemberContent;
import com.iedcr.populationserosurveillance.model.ModelDemographic;
import com.iedcr.populationserosurveillance.model.ModelFamilyMember;

public class TreatmentActivity2 extends AppCompatActivity {
    RecyclerView adm_rec,death_rec;
    Button submit;
    LinearLayoutManager linearLayoutManager,linearLayoutManager1;
    AdmRecAdapter admRecAdapter;
    DeathRecAdapter deathRecAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_treatment2);
        init();
    }

    private void init(){
        adm_rec = (RecyclerView) findViewById(R.id.adm_rec);
        death_rec=(RecyclerView) findViewById(R.id.death_rec);
        linearLayoutManager = new LinearLayoutManager(TreatmentActivity2.this);
        linearLayoutManager1 = new LinearLayoutManager(TreatmentActivity2.this);
        admRecAdapter = new AdmRecAdapter(TreatmentActivity2.this,TreatmentActivity2.this,adm_rec,ModelDemographic.admContents);
        deathRecAdapter=new DeathRecAdapter(TreatmentActivity2.this,TreatmentActivity2.this,death_rec,ModelDemographic.deathContents);
        adm_rec.setLayoutManager(linearLayoutManager);
        death_rec.setLayoutManager(linearLayoutManager1);
        adm_rec.setAdapter(admRecAdapter);
        death_rec.setAdapter(deathRecAdapter);
        if (Integer.parseInt(ModelDemographic.treatParams.get("adm_num"))!= ModelDemographic.admContents.size()){
            if (Integer.parseInt(ModelDemographic.treatParams.get("adm_num")) < ModelDemographic.admContents.size()){
                int dif = ModelDemographic.admContents.size()-Integer.parseInt(ModelDemographic.treatParams.get("adm_num"));
                while (dif>0){
                    ModelDemographic.admContents.remove(ModelDemographic.admContents.size()-1);
                    dif--;
                }
            }else if(Integer.parseInt(ModelDemographic.treatParams.get("adm_num")) > ModelDemographic.admContents.size()){
                int dif = Integer.parseInt(ModelDemographic.treatParams.get("adm_num")) - ModelDemographic.admContents.size();
                while (dif>0){
                    AdmContent admContent = new AdmContent();
                    ModelDemographic.admContents.add(admContent);
                    dif--;
                }
            }
            admRecAdapter.notifyDataSetChanged();
        }
        if (Integer.parseInt(ModelDemographic.treatParams.get("death_num"))!= ModelDemographic.deathContents.size()){
            if (Integer.parseInt(ModelDemographic.treatParams.get("death_num")) < ModelDemographic.deathContents.size()){
                int dif = ModelDemographic.deathContents.size()-Integer.parseInt(ModelDemographic.treatParams.get("death_num"));
                while (dif>0){
                    ModelDemographic.deathContents.remove(ModelDemographic.deathContents.size()-1);
                    dif--;
                }
            }else if(Integer.parseInt(ModelDemographic.treatParams.get("death_num")) > ModelDemographic.deathContents.size()){
                int dif = Integer.parseInt(ModelDemographic.treatParams.get("death_num")) - ModelDemographic.deathContents.size();
                while (dif>0){
                    DeathContent deathContent = new DeathContent();
                    ModelDemographic.deathContents.add(deathContent);
                    dif--;
                }
            }
            deathRecAdapter.notifyDataSetChanged();
        }
        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TreatmentActivity2.this,DengueActivity.class));
            }
        });

    }

}