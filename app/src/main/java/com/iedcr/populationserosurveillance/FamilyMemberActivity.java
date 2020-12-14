package com.iedcr.populationserosurveillance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.iedcr.populationserosurveillance.adapter.FamRecAdapter;
import com.iedcr.populationserosurveillance.model.FamilyMemberContent;
import com.iedcr.populationserosurveillance.model.ModelDemographic;
import com.iedcr.populationserosurveillance.model.ModelFamilyMember;

public class FamilyMemberActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FamRecAdapter famRecAdapter;
    Button submit;
    public static FamilyMemberActivity familyMemberActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_family_member);
        init();
        familyMemberActivity = this;
    }
    private void init(){
        recyclerView = (RecyclerView) findViewById(R.id.fam_rec);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        famRecAdapter = new FamRecAdapter(FamilyMemberActivity.this,
                FamilyMemberActivity.this,recyclerView,ModelFamilyMember.familyMemberContents);
        recyclerView.setAdapter(famRecAdapter);
//        list logic
//        Log.d("precalfamqty",String.valueOf(ModelDemographic.fam_qty));
//        Log.d("precalfamqty",String.valueOf(ModelFamilyMember.familyMemberContents.size()));
        if (ModelDemographic.fam_qty!= ModelFamilyMember.familyMemberContents.size()){
            if (ModelDemographic.fam_qty < ModelFamilyMember.familyMemberContents.size()){
                int dif = ModelFamilyMember.familyMemberContents.size()-ModelDemographic.fam_qty;
                while (dif>0){
                    ModelFamilyMember.familyMemberContents.remove(ModelFamilyMember.familyMemberContents.size()-1);
                    dif--;
                }
            }else if(ModelDemographic.fam_qty > ModelFamilyMember.familyMemberContents.size()){
                int dif = ModelDemographic.fam_qty - ModelFamilyMember.familyMemberContents.size();
                while (dif>0){
                    FamilyMemberContent familyMemberContent = new FamilyMemberContent();
                    ModelFamilyMember.familyMemberContents.add(familyMemberContent);
                    dif--;
                }
            }
        }
//        Log.d("aftrcalfamqty",String.valueOf(ModelFamilyMember.familyMemberContents.size()));
        int count=0;
        for (FamilyMemberContent fc : ModelFamilyMember.familyMemberContents){
            Log.d(""+count++,""+fc.age);
        }
        famRecAdapter.notifyDataSetChanged();
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(clickListen);
    }
    private View.OnClickListener clickListen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==submit.getId())
                startActivity(new Intent(FamilyMemberActivity.this,ParticipantInfoActivity.class));
        }
    };
}