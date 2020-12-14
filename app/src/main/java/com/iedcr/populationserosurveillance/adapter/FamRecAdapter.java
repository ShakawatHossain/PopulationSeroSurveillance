package com.iedcr.populationserosurveillance.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iedcr.populationserosurveillance.FamilyMemberActivity;
import com.iedcr.populationserosurveillance.R;
import com.iedcr.populationserosurveillance.model.FamilyMemberContent;
import com.iedcr.populationserosurveillance.model.ModelFamilyMember;

import java.util.ArrayList;

public class FamRecAdapter extends RecyclerView.Adapter<FamRecAdapter.MyViewHolder>{
    Context ctx;
    FamilyMemberActivity familyMemberActivity;
    RecyclerView recyclerView;
    ArrayList<FamilyMemberContent> familyMemberContents;

    public FamRecAdapter(Context ctx, FamilyMemberActivity familyMemberActivity, RecyclerView
            recyclerView,ArrayList<FamilyMemberContent> familyMemberContents){
        this.ctx = ctx;
        this.familyMemberActivity = familyMemberActivity;
        this.recyclerView = recyclerView;
        this.familyMemberContents = familyMemberContents;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fam_rec_content,parent,
                false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        FamilyMemberContent familyMemberContent = familyMemberContents.get(position);
        if (familyMemberContent.age>=0) holder.age.setText(String.valueOf(familyMemberContent.age));
        holder.age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString()==null || s.toString().isEmpty()){
                    return;
                }
                int str = Integer.parseInt(s.toString());
                FamilyMemberContent familyMemberContent1 = familyMemberContents.get(position);
                familyMemberContent1.age = str;
//                ModelFamilyMember.familyMemberContents.add(position,familyMemberContent1);
                Log.d("updateage",String.valueOf(position)+" no element");
            }
        });
        if (familyMemberContent.sex==1)
            holder.sex.check(R.id.sex_male);
        else if (familyMemberContent.sex==2)
            holder.sex.check(R.id.sex_female);
        holder.sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.sex_male)
                    ModelFamilyMember.familyMemberContents.get(position).sex=1;
                else if (checkedId == R.id.sex_female)
                    ModelFamilyMember.familyMemberContents.get(position).sex=2;
            }
        });
        if (familyMemberContent.is_present==1)
            holder.isPresent.setChecked(true);
        else
            holder.isPresent.setChecked(false);
        holder.isPresent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    ModelFamilyMember.familyMemberContents.get(position).is_present=1;
                else
                    ModelFamilyMember.familyMemberContents.get(position).is_present=0;
            }
        });
    }

    @Override
    public int getItemCount() {
        return familyMemberContents.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        EditText age;
        RadioGroup sex;
        CheckBox isPresent;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            age = (EditText) itemView.findViewById(R.id.age);
            sex = (RadioGroup) itemView.findViewById(R.id.sex);
            isPresent = (CheckBox) itemView.findViewById(R.id.present);
        }
    }
}
