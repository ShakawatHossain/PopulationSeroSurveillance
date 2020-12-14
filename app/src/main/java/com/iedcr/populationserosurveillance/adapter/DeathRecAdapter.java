package com.iedcr.populationserosurveillance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iedcr.populationserosurveillance.R;
import com.iedcr.populationserosurveillance.TreatmentActivity2;
import com.iedcr.populationserosurveillance.fragments.CalenderDialog;
import com.iedcr.populationserosurveillance.iview.PositionedCalenderInterface;
import com.iedcr.populationserosurveillance.model.AdmContent;
import com.iedcr.populationserosurveillance.model.DeathContent;
import com.iedcr.populationserosurveillance.model.ModelDemographic;

import java.util.ArrayList;

public class DeathRecAdapter extends RecyclerView.Adapter<DeathRecAdapter.MyViewHolder> implements PositionedCalenderInterface {
    Context ctx;
    TreatmentActivity2 treatmentActivity2;
    RecyclerView recyclerView;
    ArrayList<DeathContent> deathContents;

    public DeathRecAdapter(Context ctx, TreatmentActivity2 treatmentActivity2, RecyclerView
            recyclerView,ArrayList<DeathContent> deathContents){
        this.ctx = ctx;
        this.treatmentActivity2 = treatmentActivity2;
        this.recyclerView = recyclerView;
        this.deathContents = deathContents;
    }

    @Override
    public void getDate(String dat, TextView editText, int position, String editTextName) {
        editText.setText(dat);
        ModelDemographic.deathContents.get(position).death_date=dat;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.death_rec_content,parent,
                false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final DeathContent deathContent = deathContents.get(position);
        holder.hos.setChecked(deathContent.hos==1);
        holder.home.setChecked(deathContent.home==1);
        holder.death_date.setText(deathContent.death_date);
        holder.home.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ModelDemographic.deathContents.get(position).home=1;
                }else {
                    ModelDemographic.deathContents.get(position).home=0;
                }
            }
        });
        holder.hos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ModelDemographic.deathContents.get(position).hos=1;
                }else {
                    ModelDemographic.deathContents.get(position).hos=0;
                }
            }
        });
        holder.death_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CalenderDialog(ctx,DeathRecAdapter.this,
                        holder.death_date.getText().toString(),"Date",holder.death_date,
                        position,"death_date").show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return deathContents.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView death_date;
        CheckBox home,hos;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            death_date=(TextView) itemView.findViewById(R.id.death_date);
            home=(CheckBox) itemView.findViewById(R.id.home);
            hos=(CheckBox) itemView.findViewById(R.id.hos);
        }
    }
}
