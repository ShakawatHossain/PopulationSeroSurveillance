package com.iedcr.populationserosurveillance.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iedcr.populationserosurveillance.ListActivity;
import com.iedcr.populationserosurveillance.MainActivity;
import com.iedcr.populationserosurveillance.R;
import com.iedcr.populationserosurveillance.model.AdmContent;
import com.iedcr.populationserosurveillance.model.DeathContent;
import com.iedcr.populationserosurveillance.model.FamilyMemberContent;
import com.iedcr.populationserosurveillance.model.ModelDemographic;
import com.iedcr.populationserosurveillance.model.ModelFamilyMember;
import com.iedcr.populationserosurveillance.model.ModelList;
import com.iedcr.populationserosurveillance.model.MyDB;

import java.util.ArrayList;

public class ListRecAdapter extends RecyclerView.Adapter<ListRecAdapter.MyViewHolder> {
    Context ctx;
    ListActivity listActivity;
    RecyclerView recyclerView;
    ArrayList<ModelList> modelLists;

    public ListRecAdapter(Context context,RecyclerView recyclerView,ListActivity listActivity,ArrayList<ModelList> listDataStores){
        this.ctx = context;
        this.listActivity = listActivity;
        this.modelLists = listDataStores;
        this.recyclerView = recyclerView;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_recored,parent,
                false);
        v.setOnClickListener(clickListener);
        return new MyViewHolder(v);
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = recyclerView.getChildAdapterPosition(v);
            clickedPosition(position);
        }
    };
    public void clickedPosition(int position) {
        ModelList modelList = modelLists.get(position);
        ModelDemographic.sero_id = modelList.sero_id;
        ModelDemographic.demoParams = modelList.demoParams;
        ModelDemographic.ptInfoParams = modelList.ptInfoParams;
        ModelDemographic.resParams = modelList.resParams;
        ModelDemographic.hisParams = modelList.hisParams;
        ModelDemographic.pastDisParams = modelList.pastDisParams;
        ModelDemographic.treatParams = modelList.treatParams;
        ModelDemographic.dengueParams = modelList.dengueParams;
        ModelDemographic.comParams = modelList.comParams;
        ModelDemographic.riskParams = modelList.riskParams;
        MyDB myDB = new MyDB(ctx);
        Cursor fam = myDB.get_fam_sero(ModelDemographic.sero_id);
        if (fam.moveToFirst()){
            do {
                FamilyMemberContent familyMemberContent = new FamilyMemberContent();
                familyMemberContent.age = fam.getInt(fam.getColumnIndex(myDB.AGE));
                familyMemberContent.sex = fam.getInt(fam.getColumnIndex(myDB.SEX));
                familyMemberContent.is_present = fam.getInt(fam.getColumnIndex(myDB.PRESENT));
                ModelFamilyMember.familyMemberContents.add(familyMemberContent);
            }while (fam.moveToNext());
        }
        Cursor adm = myDB.get_adm_sero(ModelDemographic.sero_id);
        if (adm.moveToFirst()){
            do {
                AdmContent admContent = new AdmContent();
                admContent.adm_date = adm.getString(adm.getColumnIndex("adm_date"));
                admContent.rel_date = adm.getString(adm.getColumnIndex("rel_date"));
                admContent.icu = adm.getInt(adm.getColumnIndex("icu"));
                ModelDemographic.admContents.add(admContent);
            }while (adm.moveToNext());
        }
        Cursor death = myDB.get_death_sero(ModelDemographic.sero_id);
        if (death.moveToFirst()){
            do {
                DeathContent deathContent = new DeathContent();
                deathContent.death_date = death.getString(death.getColumnIndex("death_date"));
                deathContent.home = death.getInt(death.getColumnIndex("home"));
                deathContent.hos = death.getInt(death.getColumnIndex("hospital"));
                ModelDemographic.deathContents.add(deathContent);
            }while (death.moveToNext());
        }
//        Remove Empty cells
        ArrayList<String> keys = new ArrayList<>();
        for(String key:modelList.demoParams.keySet()) if(modelList.demoParams.get(key).isEmpty()) keys.add(key);
        for (String k:keys)  modelList.demoParams.remove(k);
        keys.clear();
        for(String key:modelList.ptInfoParams.keySet()) if(modelList.ptInfoParams.get(key).isEmpty()) keys.add(key);
        for (String k: keys) modelList.ptInfoParams.remove(k);
        keys.clear();
        for(String key:modelList.resParams.keySet()) if(modelList.resParams.get(key).isEmpty()) keys.add(key);
        for (String k: keys) modelList.resParams.remove(k);
        keys.clear();
        for(String key:modelList.hisParams.keySet()) if(modelList.hisParams.get(key).isEmpty()) keys.add(key);
        for (String k: keys) modelList.hisParams.remove(k);
        keys.clear();
        for(String key:modelList.pastDisParams.keySet()) if(modelList.pastDisParams.get(key).isEmpty()) keys.add(key);
        for (String k: keys) modelList.pastDisParams.remove(k);
        keys.clear();
        for(String key:modelList.treatParams.keySet()) if(modelList.treatParams.get(key).isEmpty()) keys.add(key);
        for (String k: keys) modelList.treatParams.remove(k);
        keys.clear();
        for(String key:modelList.dengueParams.keySet()) if(modelList.dengueParams.get(key).isEmpty()) keys.add(key);
        for (String k: keys) modelList.dengueParams.remove(k);
        keys.clear();
        for(String key:modelList.comParams.keySet()) if(modelList.comParams.get(key).isEmpty()) keys.add(key);
        for (String k: keys) modelList.comParams.remove(k);
        keys.clear();
        for(String key:modelList.riskParams.keySet()) if(modelList.riskParams.get(key).isEmpty()) keys.add(key);
        for (String k: keys) modelList.riskParams.remove(k);
        keys.clear();
        ctx.startActivity(new Intent(ctx, MainActivity.class));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ModelList modelList = modelLists.get(position);
        holder.serid.setText("Sero ID: "+modelList.sero_id);
        holder.h_mob.setText("Mobile no: : "+modelList.demoParams.get("h_mob"));
        holder.h_name.setText("Name: "+modelList.demoParams.get("h_name"));
        Log.d(String.valueOf(position)+" Component",modelList.demoParams.get("h_name"));
    }

    @Override
    public int getItemCount() {
        Log.d("No.Record"," "+modelLists.size());return modelLists.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView serid,h_name,h_mob;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            serid = (TextView) itemView.findViewById(R.id.serid);
            h_name = (TextView) itemView.findViewById(R.id.h_name);
            h_mob = (TextView) itemView.findViewById(R.id.h_mob);
        }
    }
}
