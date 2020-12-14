package com.iedcr.populationserosurveillance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.iedcr.populationserosurveillance.fragments.CalenderDialog;
import com.iedcr.populationserosurveillance.iview.CalenderInterface;
import com.iedcr.populationserosurveillance.model.ModelDemographic;

public class PastDisActivity extends AppCompatActivity implements CalenderInterface {
    RadioGroup prev_dis,fev,shivr,nasal,cough,asthma,vomit,nausea,diarr,head,rush,conj,musc,join,hung,ageu
            ,smell,epis,tired,seizure,faint,oth;
    TextView fev_date,nasal_date,cough_date,asthma_date,vomit_date,nausea_date,diarr_date,head_date,
            rush_date,conj_date,musc_date,join_date,hung_date,ageu_date,smell_date,
            epis_date,tired_date,seizure_date,faint_date,oth_date;
    EditText fev_temp,oth_txt;
    Button submit;
    public static PastDisActivity pastDisActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_past_dis);
        init();
        pastDisActivity = this;
    }
    private void init(){
//init
        prev_dis=(RadioGroup) findViewById(R.id.prev_dis);
        prev_dis.setOnCheckedChangeListener(checkedChangeListener);
        fev=(RadioGroup) findViewById(R.id.fev);
        fev.setOnCheckedChangeListener(checkedChangeListener);
        shivr=(RadioGroup) findViewById(R.id.shivr);
        nasal=(RadioGroup) findViewById(R.id.nasal);
        cough=(RadioGroup) findViewById(R.id.cough);
        asthma=(RadioGroup) findViewById(R.id.asthma);
        vomit=(RadioGroup) findViewById(R.id.vomit);
        nausea=(RadioGroup) findViewById(R.id.nausea);
        diarr=(RadioGroup) findViewById(R.id.diarr);
        head=(RadioGroup) findViewById(R.id.head);
        rush=(RadioGroup) findViewById(R.id.rush);
        conj=(RadioGroup) findViewById(R.id.conj);
        musc=(RadioGroup) findViewById(R.id.musc);
        join=(RadioGroup) findViewById(R.id.join);
        hung=(RadioGroup) findViewById(R.id.hung);
        ageu=(RadioGroup) findViewById(R.id.ageu);
        smell=(RadioGroup) findViewById(R.id.smell);
        epis=(RadioGroup) findViewById(R.id.epis);
        tired=(RadioGroup) findViewById(R.id.tired);
        seizure=(RadioGroup) findViewById(R.id.seizure);
        faint=(RadioGroup) findViewById(R.id.faint);
        oth=(RadioGroup) findViewById(R.id.oth);
        fev_date=(TextView) findViewById(R.id.fev_date);
        nasal_date=(TextView) findViewById(R.id.nasal_date);
        cough_date=(TextView) findViewById(R.id.cough_date);
        asthma_date=(TextView) findViewById(R.id.asthma_date);
        vomit_date=(TextView) findViewById(R.id.vomit_date);
        nausea_date=(TextView) findViewById(R.id.nausea_date);
        diarr_date=(TextView) findViewById(R.id.diarr_date);
        head_date=(TextView) findViewById(R.id.head_date);
        rush_date=(TextView) findViewById(R.id.rush_date);
        conj_date=(TextView) findViewById(R.id.conj_date);
        musc_date=(TextView) findViewById(R.id.musc_date);
        join_date=(TextView) findViewById(R.id.join_date);
        hung_date=(TextView) findViewById(R.id.hung_date);
        ageu_date=(TextView) findViewById(R.id.ageu_date);
        smell_date=(TextView) findViewById(R.id.smell_date);
        epis_date=(TextView) findViewById(R.id.epis_date);
        tired_date=(TextView) findViewById(R.id.tired_date);
        seizure_date=(TextView) findViewById(R.id.seizure_date);
        faint_date=(TextView) findViewById(R.id.faint_date);
        oth_date=(TextView) findViewById(R.id.oth_date);
        fev_temp=(EditText) findViewById(R.id.fev_temp);
        oth_txt=(EditText) findViewById(R.id.oth_txt);
        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(clickListener);
        fev_date.setOnClickListener(clickListener);
        nasal_date.setOnClickListener(clickListener);
        cough_date.setOnClickListener(clickListener);
        asthma_date.setOnClickListener(clickListener);
        vomit_date.setOnClickListener(clickListener);
        nausea_date.setOnClickListener(clickListener);
        diarr_date.setOnClickListener(clickListener);
        head_date.setOnClickListener(clickListener);
        rush_date.setOnClickListener(clickListener);
        conj_date.setOnClickListener(clickListener);
        musc_date.setOnClickListener(clickListener);
        join_date.setOnClickListener(clickListener);
        hung_date.setOnClickListener(clickListener);
        ageu_date.setOnClickListener(clickListener);
        smell_date.setOnClickListener(clickListener);
        epis_date.setOnClickListener(clickListener);
        tired_date.setOnClickListener(clickListener);
        seizure_date.setOnClickListener(clickListener);
        faint_date.setOnClickListener(clickListener);
        oth_date.setOnClickListener(clickListener);
//        prev value
        if(ModelDemographic.pastDisParams.containsKey("prev_dis")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("prev_dis"))==1){
                prev_dis.check(R.id.prev_dis_yes);
                if(((CardView) findViewById(R.id.prev_dis_card)).getVisibility()!= View.VISIBLE)
                    ((CardView) findViewById(R.id.prev_dis_card)).setVisibility(View.VISIBLE);
            }else if (Integer.parseInt(ModelDemographic.pastDisParams.get("prev_dis"))==2){
                prev_dis.check(R.id.prev_dis_no);
                if(((CardView) findViewById(R.id.prev_dis_card)).getVisibility()== View.VISIBLE)
                    ((CardView) findViewById(R.id.prev_dis_card)).setVisibility(View.GONE);
            }
        }
        if(ModelDemographic.pastDisParams.containsKey("fev")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("fev"))==1)
                fev.check(R.id.fev_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("fev"))==2)
                fev.check(R.id.fev_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("fev"))==3)
                fev.check(R.id.fev_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("shivr")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("shivr"))==1)
                shivr.check(R.id.shivr_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("shivr"))==2)
                shivr.check(R.id.shivr_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("shivr"))==3)
                shivr.check(R.id.shivr_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("nasal")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("nasal"))==1)
                nasal.check(R.id.nasal_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("nasal"))==2)
                nasal.check(R.id.nasal_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("nasal"))==3)
                nasal.check(R.id.nasal_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("cough")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("cough"))==1)
                cough.check(R.id.cough_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("cough"))==2)
                cough.check(R.id.cough_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("cough"))==3)
                cough.check(R.id.cough_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("asthma")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("asthma"))==1)
                asthma.check(R.id.asthma_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("asthma"))==2)
                asthma.check(R.id.asthma_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("asthma"))==3)
                asthma.check(R.id.asthma_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("vomit")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("vomit"))==1)
                vomit.check(R.id.vomit_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("vomit"))==2)
                vomit.check(R.id.vomit_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("vomit"))==3)
                vomit.check(R.id.vomit_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("nausea")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("nausea"))==1)
                nausea.check(R.id.nausea_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("nausea"))==2)
                nausea.check(R.id.nausea_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("nausea"))==3)
                nausea.check(R.id.nausea_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("diarr")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("diarr"))==1)
                diarr.check(R.id.diarr_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("diarr"))==2)
                diarr.check(R.id.diarr_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("diarr"))==3)
                diarr.check(R.id.diarr_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("head")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("head"))==1)
                head.check(R.id.head_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("head"))==2)
                head.check(R.id.head_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("head"))==3)
                head.check(R.id.head_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("rush")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("rush"))==1)
                rush.check(R.id.rush_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("rush"))==2)
                rush.check(R.id.rush_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("rush"))==3)
                rush.check(R.id.rush_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("conj")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("conj"))==1)
                conj.check(R.id.conj_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("conj"))==2)
                conj.check(R.id.conj_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("conj"))==3)
                conj.check(R.id.conj_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("musc")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("musc"))==1)
                musc.check(R.id.musc_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("musc"))==2)
                musc.check(R.id.musc_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("musc"))==3)
                musc.check(R.id.musc_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("join")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("join"))==1)
                join.check(R.id.join_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("join"))==2)
                join.check(R.id.join_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("join"))==3)
                join.check(R.id.join_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("hung")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("hung"))==1)
                hung.check(R.id.hung_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("hung"))==2)
                hung.check(R.id.hung_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("hung"))==3)
                hung.check(R.id.hung_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("ageu")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("ageu"))==1)
                ageu.check(R.id.ageu_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("ageu"))==2)
                ageu.check(R.id.ageu_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("ageu"))==3)
                ageu.check(R.id.ageu_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("smell")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("smell"))==1)
                smell.check(R.id.smell_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("smell"))==2)
                smell.check(R.id.smell_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("smell"))==3)
                smell.check(R.id.smell_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("epis")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("epis"))==1)
                epis.check(R.id.epis_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("epis"))==2)
                epis.check(R.id.epis_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("epis"))==3)
                epis.check(R.id.epis_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("tired")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("tired"))==1)
                tired.check(R.id.tired_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("tired"))==2)
                tired.check(R.id.tired_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("tired"))==3)
                tired.check(R.id.tired_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("seizure")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("seizure"))==1)
                seizure.check(R.id.seizure_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("seizure"))==2)
                seizure.check(R.id.seizure_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("seizure"))==3)
                seizure.check(R.id.seizure_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("faint")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("faint"))==1)
                faint.check(R.id.faint_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("faint"))==2)
                faint.check(R.id.faint_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("faint"))==3)
                faint.check(R.id.faint_un);
        }
        if(ModelDemographic.pastDisParams.containsKey("oth")){
            if (Integer.parseInt(ModelDemographic.pastDisParams.get("oth"))==1)
                oth.check(R.id.oth_yes);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("oth"))==2)
                oth.check(R.id.oth_no);
            else if (Integer.parseInt(ModelDemographic.pastDisParams.get("oth"))==3)
                oth.check(R.id.oth_un);
        }
        fev_date.setText(ModelDemographic.pastDisParams.containsKey("fev_date")?ModelDemographic.pastDisParams.get("fev_date"):"");
        nasal_date.setText(ModelDemographic.pastDisParams.containsKey("nasal_date")?ModelDemographic.pastDisParams.get("nasal_date"):"");
        cough_date.setText(ModelDemographic.pastDisParams.containsKey("cough_date")?ModelDemographic.pastDisParams.get("cough_date"):"");
        asthma_date.setText(ModelDemographic.pastDisParams.containsKey("asthma_date")?ModelDemographic.pastDisParams.get("asthma_date"):"");
        vomit_date.setText(ModelDemographic.pastDisParams.containsKey("vomit_date")?ModelDemographic.pastDisParams.get("vomit_date"):"");
        nausea_date.setText(ModelDemographic.pastDisParams.containsKey("nausea_date")?ModelDemographic.pastDisParams.get("nausea_date"):"");
        diarr_date.setText(ModelDemographic.pastDisParams.containsKey("diarr_date")?ModelDemographic.pastDisParams.get("diarr_date"):"");
        head_date.setText(ModelDemographic.pastDisParams.containsKey("head_date")?ModelDemographic.pastDisParams.get("head_date"):"");
        rush_date.setText(ModelDemographic.pastDisParams.containsKey("rush_date")?ModelDemographic.pastDisParams.get("rush_date"):"");
        conj_date.setText(ModelDemographic.pastDisParams.containsKey("conj_date")?ModelDemographic.pastDisParams.get("conj_date"):"");
        musc_date.setText(ModelDemographic.pastDisParams.containsKey("musc_date")?ModelDemographic.pastDisParams.get("musc_date"):"");
        join_date.setText(ModelDemographic.pastDisParams.containsKey("join_date")?ModelDemographic.pastDisParams.get("join_date"):"");
        hung_date.setText(ModelDemographic.pastDisParams.containsKey("hung_date")?ModelDemographic.pastDisParams.get("hung_date"):"");
        ageu_date.setText(ModelDemographic.pastDisParams.containsKey("ageu_date")?ModelDemographic.pastDisParams.get("ageu_date"):"");
        smell_date.setText(ModelDemographic.pastDisParams.containsKey("smell_date")?ModelDemographic.pastDisParams.get("smell_date"):"");
        epis_date.setText(ModelDemographic.pastDisParams.containsKey("epis_date")?ModelDemographic.pastDisParams.get("epis_date"):"");
        tired_date.setText(ModelDemographic.pastDisParams.containsKey("tired_date")?ModelDemographic.pastDisParams.get("tired_date"):"");
        seizure_date.setText(ModelDemographic.pastDisParams.containsKey("seizure_date")?ModelDemographic.pastDisParams.get("seizure_date"):"");
        faint_date.setText(ModelDemographic.pastDisParams.containsKey("faint_date")?ModelDemographic.pastDisParams.get("faint_date"):"");
        oth_date.setText(ModelDemographic.pastDisParams.containsKey("oth_date")?ModelDemographic.pastDisParams.get("oth_date"):"");
        fev_temp.setText(ModelDemographic.pastDisParams.containsKey("fev_temp")?ModelDemographic.pastDisParams.get("fev_temp"):"");
        oth_txt.setText(ModelDemographic.pastDisParams.containsKey("oth_txt")? ModelDemographic.pastDisParams.get("oth_txt"):"");
    }
    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (group.getId()==prev_dis.getId()){
                if (checkedId==R.id.prev_dis_yes){
                    if(((CardView) findViewById(R.id.prev_dis_card)).getVisibility()!= View.VISIBLE)
                        ((CardView) findViewById(R.id.prev_dis_card)).setVisibility(View.VISIBLE);
                }else if(checkedId==R.id.prev_dis_no){
                    if(((CardView) findViewById(R.id.prev_dis_card)).getVisibility()== View.VISIBLE)
                        ((CardView) findViewById(R.id.prev_dis_card)).setVisibility(View.GONE);
                }
            }else if(group.getId()==fev.getId()){
                if (checkedId==R.id.fev_yes){
                    if (((LinearLayout)findViewById(R.id.fev_lin)).getVisibility()!=View.VISIBLE){
                        ((LinearLayout)findViewById(R.id.fev_lin)).setVisibility(View.VISIBLE);
                    }
                    if (((TableRow)findViewById(R.id.shivr_row)).getVisibility()!=View.VISIBLE)
                        ((TableRow)findViewById(R.id.shivr_row)).setVisibility(View.VISIBLE);
                }else{
                    if (((LinearLayout)findViewById(R.id.fev_lin)).getVisibility()==View.VISIBLE){
                        ((LinearLayout)findViewById(R.id.fev_lin)).setVisibility(View.GONE);
                    }
                    if (((TableRow)findViewById(R.id.shivr_row)).getVisibility()==View.VISIBLE)
                        ((TableRow)findViewById(R.id.shivr_row)).setVisibility(View.GONE);
                }
            }
        }
    };
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==submit.getId()){
                setValues();
                if(check()){
                    startActivity(new Intent(PastDisActivity.this,TreatmentActivity.class));
                }else{
                    Toast.makeText(PastDisActivity.this,"Check Input",Toast.LENGTH_SHORT).show();
                }
            }else {
                new CalenderDialog(PastDisActivity.this,PastDisActivity.this,
                        ((TextView)v).getText().toString(),"Date",((TextView)v)).show();
            }
        }
    };
    private void setValues(){
        if(prev_dis.getCheckedRadioButtonId()==R.id.prev_dis_yes)
            ModelDemographic.pastDisParams.put("prev_dis","1");
        else if(prev_dis.getCheckedRadioButtonId()==R.id.prev_dis_no)
            ModelDemographic.pastDisParams.put("prev_dis","2");
        if(fev.getCheckedRadioButtonId()==R.id.fev_yes)
            ModelDemographic.pastDisParams.put("fev","1");
        else if(fev.getCheckedRadioButtonId()==R.id.fev_no)
            ModelDemographic.pastDisParams.put("fev","2");
        else if(fev.getCheckedRadioButtonId()==R.id.fev_un)
            ModelDemographic.pastDisParams.put("fev","3");
        if(shivr.getCheckedRadioButtonId()==R.id.shivr_yes)
            ModelDemographic.pastDisParams.put("shivr","1");
        else if(shivr.getCheckedRadioButtonId()==R.id.shivr_no)
            ModelDemographic.pastDisParams.put("shivr","2");
        else if(shivr.getCheckedRadioButtonId()==R.id.shivr_un)
            ModelDemographic.pastDisParams.put("shivr","3");
        if(nasal.getCheckedRadioButtonId()==R.id.nasal_yes)
            ModelDemographic.pastDisParams.put("nasal","1");
        else if(nasal.getCheckedRadioButtonId()==R.id.nasal_no)
            ModelDemographic.pastDisParams.put("nasal","2");
        else if(nasal.getCheckedRadioButtonId()==R.id.nasal_un)
            ModelDemographic.pastDisParams.put("nasal","3");
        if(cough.getCheckedRadioButtonId()==R.id.cough_yes)
            ModelDemographic.pastDisParams.put("cough","1");
        else if(cough.getCheckedRadioButtonId()==R.id.cough_no)
            ModelDemographic.pastDisParams.put("cough","2");
        else if(cough.getCheckedRadioButtonId()==R.id.cough_un)
            ModelDemographic.pastDisParams.put("cough","3");
        if(asthma.getCheckedRadioButtonId()==R.id.asthma_yes)
            ModelDemographic.pastDisParams.put("asthma","1");
        else if(asthma.getCheckedRadioButtonId()==R.id.asthma_no)
            ModelDemographic.pastDisParams.put("asthma","2");
        else if(asthma.getCheckedRadioButtonId()==R.id.asthma_un)
            ModelDemographic.pastDisParams.put("asthma","3");
        if(vomit.getCheckedRadioButtonId()==R.id.vomit_yes)
            ModelDemographic.pastDisParams.put("vomit","1");
        else if(vomit.getCheckedRadioButtonId()==R.id.vomit_no)
            ModelDemographic.pastDisParams.put("vomit","2");
        else if(vomit.getCheckedRadioButtonId()==R.id.vomit_un)
            ModelDemographic.pastDisParams.put("vomit","3");
        if(nausea.getCheckedRadioButtonId()==R.id.nausea_yes)
            ModelDemographic.pastDisParams.put("nausea","1");
        else if(nausea.getCheckedRadioButtonId()==R.id.nausea_no)
            ModelDemographic.pastDisParams.put("nausea","2");
        else if(nausea.getCheckedRadioButtonId()==R.id.nausea_un)
            ModelDemographic.pastDisParams.put("nausea","3");
        if(diarr.getCheckedRadioButtonId()==R.id.diarr_yes)
            ModelDemographic.pastDisParams.put("diarr","1");
        else if(diarr.getCheckedRadioButtonId()==R.id.diarr_no)
            ModelDemographic.pastDisParams.put("diarr","2");
        else if(diarr.getCheckedRadioButtonId()==R.id.diarr_un)
            ModelDemographic.pastDisParams.put("diarr","3");
        if(head.getCheckedRadioButtonId()==R.id.head_yes)
            ModelDemographic.pastDisParams.put("head","1");
        else if(head.getCheckedRadioButtonId()==R.id.head_no)
            ModelDemographic.pastDisParams.put("head","2");
        else if(head.getCheckedRadioButtonId()==R.id.head_un)
            ModelDemographic.pastDisParams.put("head","3");
        if(rush.getCheckedRadioButtonId()==R.id.rush_yes)
            ModelDemographic.pastDisParams.put("rush","1");
        else if(rush.getCheckedRadioButtonId()==R.id.rush_no)
            ModelDemographic.pastDisParams.put("rush","2");
        else if(rush.getCheckedRadioButtonId()==R.id.rush_un)
            ModelDemographic.pastDisParams.put("rush","3");
        if(conj.getCheckedRadioButtonId()==R.id.conj_yes)
            ModelDemographic.pastDisParams.put("conj","1");
        else if(conj.getCheckedRadioButtonId()==R.id.conj_no)
            ModelDemographic.pastDisParams.put("conj","2");
        else if(conj.getCheckedRadioButtonId()==R.id.conj_un)
            ModelDemographic.pastDisParams.put("conj","3");
        if(musc.getCheckedRadioButtonId()==R.id.musc_yes)
            ModelDemographic.pastDisParams.put("musc","1");
        else if(musc.getCheckedRadioButtonId()==R.id.musc_no)
            ModelDemographic.pastDisParams.put("musc","2");
        else if(musc.getCheckedRadioButtonId()==R.id.musc_un)
            ModelDemographic.pastDisParams.put("musc","3");
        if(join.getCheckedRadioButtonId()==R.id.join_yes)
            ModelDemographic.pastDisParams.put("join","1");
        else if(join.getCheckedRadioButtonId()==R.id.join_no)
            ModelDemographic.pastDisParams.put("join","2");
        else if(join.getCheckedRadioButtonId()==R.id.join_un)
            ModelDemographic.pastDisParams.put("join","3");
        if(hung.getCheckedRadioButtonId()==R.id.hung_yes)
            ModelDemographic.pastDisParams.put("hung","1");
        else if(hung.getCheckedRadioButtonId()==R.id.hung_no)
            ModelDemographic.pastDisParams.put("hung","2");
        else if(hung.getCheckedRadioButtonId()==R.id.hung_un)
            ModelDemographic.pastDisParams.put("hung","3");
        if(ageu.getCheckedRadioButtonId()==R.id.ageu_yes)
            ModelDemographic.pastDisParams.put("ageu","1");
        else if(ageu.getCheckedRadioButtonId()==R.id.ageu_no)
            ModelDemographic.pastDisParams.put("ageu","2");
        else if(ageu.getCheckedRadioButtonId()==R.id.ageu_un)
            ModelDemographic.pastDisParams.put("ageu","3");
        if(smell.getCheckedRadioButtonId()==R.id.smell_yes)
            ModelDemographic.pastDisParams.put("smell","1");
        else if(smell.getCheckedRadioButtonId()==R.id.smell_no)
            ModelDemographic.pastDisParams.put("smell","2");
        else if(smell.getCheckedRadioButtonId()==R.id.smell_un)
            ModelDemographic.pastDisParams.put("smell","3");
        if(epis.getCheckedRadioButtonId()==R.id.epis_yes)
            ModelDemographic.pastDisParams.put("epis","1");
        else if(epis.getCheckedRadioButtonId()==R.id.epis_no)
            ModelDemographic.pastDisParams.put("epis","2");
        else if(epis.getCheckedRadioButtonId()==R.id.epis_un)
            ModelDemographic.pastDisParams.put("epis","3");
        if(tired.getCheckedRadioButtonId()==R.id.tired_yes)
            ModelDemographic.pastDisParams.put("tired","1");
        else if(tired.getCheckedRadioButtonId()==R.id.tired_no)
            ModelDemographic.pastDisParams.put("tired","2");
        else if(tired.getCheckedRadioButtonId()==R.id.tired_un)
            ModelDemographic.pastDisParams.put("tired","3");
        if(seizure.getCheckedRadioButtonId()==R.id.seizure_yes)
            ModelDemographic.pastDisParams.put("seizure","1");
        else if(seizure.getCheckedRadioButtonId()==R.id.seizure_no)
            ModelDemographic.pastDisParams.put("seizure","2");
        else if(seizure.getCheckedRadioButtonId()==R.id.seizure_un)
            ModelDemographic.pastDisParams.put("seizure","3");
        if(faint.getCheckedRadioButtonId()==R.id.faint_yes)
            ModelDemographic.pastDisParams.put("faint","1");
        else if(faint.getCheckedRadioButtonId()==R.id.faint_no)
            ModelDemographic.pastDisParams.put("faint","2");
        else if(faint.getCheckedRadioButtonId()==R.id.faint_un)
            ModelDemographic.pastDisParams.put("faint","3");
        if(oth.getCheckedRadioButtonId()==R.id.oth_yes)
            ModelDemographic.pastDisParams.put("oth","1");
        else if(oth.getCheckedRadioButtonId()==R.id.oth_no)
            ModelDemographic.pastDisParams.put("oth","2");
        else if(oth.getCheckedRadioButtonId()==R.id.oth_un)
            ModelDemographic.pastDisParams.put("oth","3");
        ModelDemographic.pastDisParams.put("fev_date",fev_date.getText().toString());
        ModelDemographic.pastDisParams.put("nasal_date",nasal_date.getText().toString());
        ModelDemographic.pastDisParams.put("cough_date",cough_date.getText().toString());
        ModelDemographic.pastDisParams.put("asthma_date",asthma_date.getText().toString());
        ModelDemographic.pastDisParams.put("vomit_date",vomit_date.getText().toString());
        ModelDemographic.pastDisParams.put("nausea_date",nausea_date.getText().toString());
        ModelDemographic.pastDisParams.put("diarr_date",diarr_date.getText().toString());
        ModelDemographic.pastDisParams.put("head_date",head_date.getText().toString());
        ModelDemographic.pastDisParams.put("rush_date",rush_date.getText().toString());
        ModelDemographic.pastDisParams.put("conj_date",conj_date.getText().toString());
        ModelDemographic.pastDisParams.put("musc_date",musc_date.getText().toString());
        ModelDemographic.pastDisParams.put("join_date",join_date.getText().toString());
        ModelDemographic.pastDisParams.put("hung_date",hung_date.getText().toString());
        ModelDemographic.pastDisParams.put("ageu_date",ageu_date.getText().toString());
        ModelDemographic.pastDisParams.put("smell_date",smell_date.getText().toString());
        ModelDemographic.pastDisParams.put("epis_date",epis_date.getText().toString());
        ModelDemographic.pastDisParams.put("tired_date",tired_date.getText().toString());
        ModelDemographic.pastDisParams.put("seizure_date",seizure_date.getText().toString());
        ModelDemographic.pastDisParams.put("faint_date",faint_date.getText().toString());
        ModelDemographic.pastDisParams.put("oth_date",oth_date.getText().toString());
        ModelDemographic.pastDisParams.put("fev_temp",fev_temp.getText().toString());
        ModelDemographic.pastDisParams.put("oth_txt",oth_txt.getText().toString());
    }
    private boolean check(){
        if(!ModelDemographic.pastDisParams.containsKey("prev_dis")) return false;
        if (prev_dis.getCheckedRadioButtonId()==R.id.prev_dis_no) return true;
        if(!ModelDemographic.pastDisParams.containsKey("fev")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("nasal")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("cough")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("asthma")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("vomit")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("nausea")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("diarr")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("head")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("rush")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("conj")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("musc")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("join")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("hung")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("ageu")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("smell")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("epis")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("tired")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("seizure")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("faint")) return false;
        if(!ModelDemographic.pastDisParams.containsKey("oth")) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("fev"))==1 && (fev_date.getText().toString()==null ||fev_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("nasal"))==1 && (nasal_date.getText().toString()==null ||nasal_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("cough"))==1 && (cough_date.getText().toString()==null ||cough_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("asthma"))==1 && (asthma_date.getText().toString()==null ||asthma_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("vomit"))==1 && (vomit_date.getText().toString()==null ||vomit_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("nausea"))==1 && (nausea_date.getText().toString()==null ||nausea_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("diarr"))==1 && (diarr_date.getText().toString()==null ||diarr_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("head"))==1 && (head_date.getText().toString()==null ||head_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("rush"))==1 && (rush_date.getText().toString()==null ||rush_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("conj"))==1 && (conj_date.getText().toString()==null ||conj_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("musc"))==1 && (musc_date.getText().toString()==null ||musc_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("join"))==1 && (join_date.getText().toString()==null ||join_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("hung"))==1 && (hung_date.getText().toString()==null ||hung_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("ageu"))==1 && (ageu_date.getText().toString()==null ||ageu_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("smell"))==1 && (smell_date.getText().toString()==null ||smell_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("epis"))==1 && (epis_date.getText().toString()==null ||epis_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("tired"))==1 && (tired_date.getText().toString()==null ||tired_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("seizure"))==1 && (seizure_date.getText().toString()==null ||seizure_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("faint"))==1 && (faint_date.getText().toString()==null ||faint_date.getText().toString().isEmpty())) return false;
//        if(Integer.parseInt(ModelDemographic.pastDisParams.get("oth"))==1 && (oth_date.getText().toString()==null ||oth_date.getText().toString().isEmpty())) return false;
        return true;
    }

    @Override
    public void getDate(String dat, TextView editText) {
        editText.setText(dat);
    }
}