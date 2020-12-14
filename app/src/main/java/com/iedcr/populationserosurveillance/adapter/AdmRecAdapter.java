package com.iedcr.populationserosurveillance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iedcr.populationserosurveillance.R;
import com.iedcr.populationserosurveillance.TreatmentActivity2;
import com.iedcr.populationserosurveillance.fragments.CalenderDialog;
import com.iedcr.populationserosurveillance.iview.CalenderInterface;
import com.iedcr.populationserosurveillance.iview.PositionedCalenderInterface;
import com.iedcr.populationserosurveillance.model.AdmContent;
import com.iedcr.populationserosurveillance.model.FamilyMemberContent;
import com.iedcr.populationserosurveillance.model.ModelDemographic;

import java.util.ArrayList;

public class AdmRecAdapter extends RecyclerView.Adapter<AdmRecAdapter.MyViewHolder> implements PositionedCalenderInterface {
    Context ctx;
    TreatmentActivity2 treatmentActivity2;
    RecyclerView recyclerView;
    ArrayList<AdmContent> admContents;

    public AdmRecAdapter(Context ctx, TreatmentActivity2 treatmentActivity2, RecyclerView
            recyclerView,ArrayList<AdmContent> admContents){
        this.ctx = ctx;
        this.treatmentActivity2 = treatmentActivity2;
        this.recyclerView = recyclerView;
        this.admContents = admContents;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adm_content,parent,
                false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        AdmContent admContent = admContents.get(position);
        holder.icu.setChecked(admContent.icu==1);
        holder.adm_date.setText(admContent.adm_date);
        holder.rel_date.setText(admContent.rel_date);
        holder.icu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ModelDemographic.admContents.get(position).icu=1;
                }else {
                    ModelDemographic.admContents.get(position).icu=0;
                }
            }
        });
        holder.adm_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CalenderDialog(ctx,AdmRecAdapter.this,holder.adm_date.getText().toString(),
                        "date",holder.adm_date,position,"adm_date").show();
            }
        });
        holder.rel_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CalenderDialog(ctx,AdmRecAdapter.this,holder.rel_date.getText().toString(),
                        "date",holder.rel_date,position,"rel_date").show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Integer.parseInt(ModelDemographic.treatParams.get("adm_num"));
    }



    @Override
    public void getDate(String dat, TextView editText, int position,String editTextName) {
        editText.setText(dat);
        if (editTextName.compareTo("adm_date")==0){
            ModelDemographic.admContents.get(position).adm_date=dat;
        }else if(editTextName.compareTo("rel_date")==0){
            ModelDemographic.admContents.get(position).rel_date=dat;
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView adm_date,rel_date;
        CheckBox icu;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            adm_date=(TextView) itemView.findViewById(R.id.adm_date);
            rel_date=(TextView) itemView.findViewById(R.id.rel_date);
            icu=(CheckBox) itemView.findViewById(R.id.icu);
        }
    }
}
