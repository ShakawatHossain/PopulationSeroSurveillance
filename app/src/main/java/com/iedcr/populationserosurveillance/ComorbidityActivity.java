package com.iedcr.populationserosurveillance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.iedcr.populationserosurveillance.model.ModelDemographic;

public class ComorbidityActivity extends AppCompatActivity {
    RadioGroup cancer,dia,aids,heart,asthma,resp,liver,hema,kidney,nuro,joint,smoke,tobac;
    EditText oth;
    Button submit;
    public static ComorbidityActivity comorbidityActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_comorbidity);
        init();
        comorbidityActivity = this;
    }
    private void init(){
//init
        cancer=(RadioGroup) findViewById(R.id.cancer);
        dia=(RadioGroup) findViewById(R.id.dia);
        aids=(RadioGroup) findViewById(R.id.aids);
        heart=(RadioGroup) findViewById(R.id.heart);
        asthma=(RadioGroup) findViewById(R.id.asthma);
        resp=(RadioGroup) findViewById(R.id.resp);
        liver=(RadioGroup) findViewById(R.id.liver);
        hema=(RadioGroup) findViewById(R.id.hema);
        kidney=(RadioGroup) findViewById(R.id.kidney);
        nuro=(RadioGroup) findViewById(R.id.nuro);
        joint=(RadioGroup) findViewById(R.id.joint);
        smoke=(RadioGroup) findViewById(R.id.smoke);
        tobac=(RadioGroup) findViewById(R.id.tobac);
        oth=(EditText)findViewById(R.id.oth);
        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(clickListener);
//        prev value
        if(ModelDemographic.comParams.containsKey("cancer")){
            if (Integer.parseInt(ModelDemographic.comParams.get("cancer"))==1)
                cancer.check(R.id.cancer_yes);
            else if (Integer.parseInt(ModelDemographic.comParams.get("cancer"))==2)
                cancer.check(R.id.cancer_no);
            else if (Integer.parseInt(ModelDemographic.comParams.get("cancer"))==3)
                cancer.check(R.id.cancer_un);
        }
        if(ModelDemographic.comParams.containsKey("dia")){
            if (Integer.parseInt(ModelDemographic.comParams.get("dia"))==1)
                dia.check(R.id.dia_yes);
            else if (Integer.parseInt(ModelDemographic.comParams.get("dia"))==2)
                dia.check(R.id.dia_no);
            else if (Integer.parseInt(ModelDemographic.comParams.get("dia"))==3)
                dia.check(R.id.dia_un);
        }
        if(ModelDemographic.comParams.containsKey("aids")){
            if (Integer.parseInt(ModelDemographic.comParams.get("aids"))==1)
                aids.check(R.id.aids_yes);
            else if (Integer.parseInt(ModelDemographic.comParams.get("aids"))==2)
                aids.check(R.id.aids_no);
            else if (Integer.parseInt(ModelDemographic.comParams.get("aids"))==3)
                aids.check(R.id.aids_un);
        }
        if(ModelDemographic.comParams.containsKey("heart")){
            if (Integer.parseInt(ModelDemographic.comParams.get("heart"))==1)
                heart.check(R.id.heart_yes);
            else if (Integer.parseInt(ModelDemographic.comParams.get("heart"))==2)
                heart.check(R.id.heart_no);
            else if (Integer.parseInt(ModelDemographic.comParams.get("heart"))==3)
                heart.check(R.id.heart_un);
        }
        if(ModelDemographic.comParams.containsKey("asthma")){
            if (Integer.parseInt(ModelDemographic.comParams.get("asthma"))==1)
                asthma.check(R.id.asthma_yes);
            else if (Integer.parseInt(ModelDemographic.comParams.get("asthma"))==2)
                asthma.check(R.id.asthma_no);
            else if (Integer.parseInt(ModelDemographic.comParams.get("asthma"))==3)
                asthma.check(R.id.asthma_un);
        }
        if(ModelDemographic.comParams.containsKey("resp")){
            if (Integer.parseInt(ModelDemographic.comParams.get("resp"))==1)
                resp.check(R.id.resp_yes);
            else if (Integer.parseInt(ModelDemographic.comParams.get("resp"))==2)
                resp.check(R.id.resp_no);
            else if (Integer.parseInt(ModelDemographic.comParams.get("resp"))==3)
                resp.check(R.id.resp_un);
        }
        if(ModelDemographic.comParams.containsKey("liver")){
            if (Integer.parseInt(ModelDemographic.comParams.get("liver"))==1)
                liver.check(R.id.liver_yes);
            else if (Integer.parseInt(ModelDemographic.comParams.get("liver"))==2)
                liver.check(R.id.liver_no);
            else if (Integer.parseInt(ModelDemographic.comParams.get("liver"))==3)
                liver.check(R.id.liver_un);
        }
        if(ModelDemographic.comParams.containsKey("hema")){
            if (Integer.parseInt(ModelDemographic.comParams.get("hema"))==1)
                hema.check(R.id.hema_yes);
            else if (Integer.parseInt(ModelDemographic.comParams.get("hema"))==2)
                hema.check(R.id.hema_no);
            else if (Integer.parseInt(ModelDemographic.comParams.get("hema"))==3)
                hema.check(R.id.hema_un);
        }
        if(ModelDemographic.comParams.containsKey("kidney")){
            if (Integer.parseInt(ModelDemographic.comParams.get("kidney"))==1)
                kidney.check(R.id.kidney_yes);
            else if (Integer.parseInt(ModelDemographic.comParams.get("kidney"))==2)
                kidney.check(R.id.kidney_no);
            else if (Integer.parseInt(ModelDemographic.comParams.get("kidney"))==3)
                kidney.check(R.id.kidney_un);
        }
        if(ModelDemographic.comParams.containsKey("nuro")){
            if (Integer.parseInt(ModelDemographic.comParams.get("nuro"))==1)
                nuro.check(R.id.nuro_yes);
            else if (Integer.parseInt(ModelDemographic.comParams.get("nuro"))==2)
                nuro.check(R.id.nuro_no);
            else if (Integer.parseInt(ModelDemographic.comParams.get("nuro"))==3)
                nuro.check(R.id.nuro_un);
        }
        if(ModelDemographic.comParams.containsKey("joint")){
            if (Integer.parseInt(ModelDemographic.comParams.get("joint"))==1)
                joint.check(R.id.joint_yes);
            else if (Integer.parseInt(ModelDemographic.comParams.get("joint"))==2)
                joint.check(R.id.joint_no);
            else if (Integer.parseInt(ModelDemographic.comParams.get("joint"))==3)
                joint.check(R.id.joint_un);
        }
        if(ModelDemographic.comParams.containsKey("smoke")){
            if (Integer.parseInt(ModelDemographic.comParams.get("smoke"))==1)
                smoke.check(R.id.smoke_reg);
            else if (Integer.parseInt(ModelDemographic.comParams.get("smoke"))==2)
                smoke.check(R.id.smoke_irreg);
            else if (Integer.parseInt(ModelDemographic.comParams.get("smoke"))==3)
                smoke.check(R.id.smoke_no);
        }
        if(ModelDemographic.comParams.containsKey("tobac")){
            if (Integer.parseInt(ModelDemographic.comParams.get("tobac"))==1)
                tobac.check(R.id.tobac_reg);
            else if (Integer.parseInt(ModelDemographic.comParams.get("tobac"))==2)
                tobac.check(R.id.tobac_irreg);
            else if (Integer.parseInt(ModelDemographic.comParams.get("tobac"))==3)
                tobac.check(R.id.tobac_no);
        }
        oth.setText(ModelDemographic.comParams.containsKey("oth")?ModelDemographic.comParams.get("oth"):"");
    }
    private void setValues(){
        if (cancer.getCheckedRadioButtonId()==R.id.cancer_yes)
            ModelDemographic.comParams.put("cancer","1");
        else if (cancer.getCheckedRadioButtonId()==R.id.cancer_no)
            ModelDemographic.comParams.put("cancer","2");
        else if (cancer.getCheckedRadioButtonId()==R.id.cancer_un)
            ModelDemographic.comParams.put("cancer","3");
        if (dia.getCheckedRadioButtonId()==R.id.dia_yes)
            ModelDemographic.comParams.put("dia","1");
        else if (dia.getCheckedRadioButtonId()==R.id.dia_no)
            ModelDemographic.comParams.put("dia","2");
        else if (dia.getCheckedRadioButtonId()==R.id.dia_un)
            ModelDemographic.comParams.put("dia","3");
        if (aids.getCheckedRadioButtonId()==R.id.aids_yes)
            ModelDemographic.comParams.put("aids","1");
        else if (aids.getCheckedRadioButtonId()==R.id.aids_no)
            ModelDemographic.comParams.put("aids","2");
        else if (aids.getCheckedRadioButtonId()==R.id.aids_un)
            ModelDemographic.comParams.put("aids","3");
        if (heart.getCheckedRadioButtonId()==R.id.heart_yes)
            ModelDemographic.comParams.put("heart","1");
        else if (heart.getCheckedRadioButtonId()==R.id.heart_no)
            ModelDemographic.comParams.put("heart","2");
        else if (heart.getCheckedRadioButtonId()==R.id.heart_un)
            ModelDemographic.comParams.put("heart","3");
        if (asthma.getCheckedRadioButtonId()==R.id.asthma_yes)
            ModelDemographic.comParams.put("asthma","1");
        else if (asthma.getCheckedRadioButtonId()==R.id.asthma_no)
            ModelDemographic.comParams.put("asthma","2");
        else if (asthma.getCheckedRadioButtonId()==R.id.asthma_un)
            ModelDemographic.comParams.put("asthma","3");
        if (resp.getCheckedRadioButtonId()==R.id.resp_yes)
            ModelDemographic.comParams.put("resp","1");
        else if (resp.getCheckedRadioButtonId()==R.id.resp_no)
            ModelDemographic.comParams.put("resp","2");
        else if (resp.getCheckedRadioButtonId()==R.id.resp_un)
            ModelDemographic.comParams.put("resp","3");
        if (liver.getCheckedRadioButtonId()==R.id.liver_yes)
            ModelDemographic.comParams.put("liver","1");
        else if (liver.getCheckedRadioButtonId()==R.id.liver_no)
            ModelDemographic.comParams.put("liver","2");
        else if (liver.getCheckedRadioButtonId()==R.id.liver_un)
            ModelDemographic.comParams.put("liver","3");
        if (hema.getCheckedRadioButtonId()==R.id.hema_yes)
            ModelDemographic.comParams.put("hema","1");
        else if (hema.getCheckedRadioButtonId()==R.id.hema_no)
            ModelDemographic.comParams.put("hema","2");
        else if (hema.getCheckedRadioButtonId()==R.id.hema_un)
            ModelDemographic.comParams.put("hema","3");
        if (kidney.getCheckedRadioButtonId()==R.id.kidney_yes)
            ModelDemographic.comParams.put("kidney","1");
        else if (kidney.getCheckedRadioButtonId()==R.id.kidney_no)
            ModelDemographic.comParams.put("kidney","2");
        else if (kidney.getCheckedRadioButtonId()==R.id.kidney_un)
            ModelDemographic.comParams.put("kidney","3");
        if (nuro.getCheckedRadioButtonId()==R.id.nuro_yes)
            ModelDemographic.comParams.put("nuro","1");
        else if (nuro.getCheckedRadioButtonId()==R.id.nuro_no)
            ModelDemographic.comParams.put("nuro","2");
        else if (nuro.getCheckedRadioButtonId()==R.id.nuro_un)
            ModelDemographic.comParams.put("nuro","3");
        if (joint.getCheckedRadioButtonId()==R.id.joint_yes)
            ModelDemographic.comParams.put("joint","1");
        else if (joint.getCheckedRadioButtonId()==R.id.joint_no)
            ModelDemographic.comParams.put("joint","2");
        else if (joint.getCheckedRadioButtonId()==R.id.joint_un)
            ModelDemographic.comParams.put("joint","3");
        if (smoke.getCheckedRadioButtonId()==R.id.smoke_reg)
            ModelDemographic.comParams.put("smoke","1");
        else if (smoke.getCheckedRadioButtonId()==R.id.smoke_irreg)
            ModelDemographic.comParams.put("smoke","2");
        else if (smoke.getCheckedRadioButtonId()==R.id.smoke_no)
            ModelDemographic.comParams.put("smoke","3");
        if (tobac.getCheckedRadioButtonId()==R.id.tobac_reg)
            ModelDemographic.comParams.put("tobac","1");
        else if (tobac.getCheckedRadioButtonId()==R.id.tobac_irreg)
            ModelDemographic.comParams.put("tobac","2");
        else if (tobac.getCheckedRadioButtonId()==R.id.tobac_no)
            ModelDemographic.comParams.put("tobac","3");
        ModelDemographic.comParams.put("oth",oth.getText().toString());
    }
    private boolean check(){
        if(!ModelDemographic.comParams.containsKey("cancer")){ return false;}
        if(!ModelDemographic.comParams.containsKey("dia")){ return false;}
        if(!ModelDemographic.comParams.containsKey("aids")){ return false;}
        if(!ModelDemographic.comParams.containsKey("heart")){ return false;}
        if(!ModelDemographic.comParams.containsKey("asthma")){ return false;}
        if(!ModelDemographic.comParams.containsKey("resp")){ return false;}
        if(!ModelDemographic.comParams.containsKey("liver")){ return false;}
        if(!ModelDemographic.comParams.containsKey("hema")){ return false;}
        if(!ModelDemographic.comParams.containsKey("kidney")){ return false;}
        if(!ModelDemographic.comParams.containsKey("nuro")){ return false;}
        if(!ModelDemographic.comParams.containsKey("joint")){ return false;}
        if(!ModelDemographic.comParams.containsKey("smoke")){ return false;}
        if(!ModelDemographic.comParams.containsKey("tobac")){ return false;}
        return true;
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==submit.getId()){
                setValues();
                if (check()){
                    startActivity(new Intent(ComorbidityActivity.this,RiskBehavActivity.class));
                }else {
                    Toast.makeText(ComorbidityActivity.this,"Check Input",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}