package com.iedcr.populationserosurveillance.model;

import java.util.ArrayList;
import java.util.HashMap;

public class ModelList {
    public String sero_id = null;
    public String created_at = null;
    public  HashMap<String,String> demoParams = new HashMap<String,String>();
    public  HashMap<String,String> ptInfoParams = new HashMap<String,String>();
    public  HashMap<String,String> resParams = new HashMap<String,String>();
    public  HashMap<String,String> hisParams = new HashMap<String,String>();
    public  HashMap<String,String> pastDisParams = new HashMap<String,String>();
    public  HashMap<String,String> treatParams = new HashMap<String,String>();
    public  HashMap<String,String> dengueParams = new HashMap<String,String>();
    public  HashMap<String,String> comParams = new HashMap<String,String>();
    public  HashMap<String,String> riskParams = new HashMap<String,String>();
    public ArrayList<FamilyMemberContent> familyMemberContents = new ArrayList<>();
}
