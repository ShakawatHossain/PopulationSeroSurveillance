package com.iedcr.populationserosurveillance.model;

import java.util.ArrayList;
import java.util.HashMap;

public class ModelDemographic {

    public static String slum_id = null;
    public static String user_id = null;
    public static String slum_house_id = null;
    public static String sero_id = null;
    public static int fam_qty = 0;
    public static HashMap<String,String> demoParams = new HashMap<String,String>();
    public static HashMap<String,String> ptInfoParams = new HashMap<String,String>();
    public static HashMap<String,String> resParams = new HashMap<String,String>();
    public static HashMap<String,String> hisParams = new HashMap<String,String>();
    public static HashMap<String,String> pastDisParams = new HashMap<String,String>();
    public static HashMap<String,String> treatParams = new HashMap<String,String>();
    public static HashMap<String,String> dengueParams = new HashMap<String,String>();
    public static HashMap<String,String> comParams = new HashMap<String,String>();
    public static HashMap<String,String> riskParams = new HashMap<String,String>();
    public static ArrayList<AdmContent> admContents = new ArrayList<>();
    public static ArrayList<DeathContent> deathContents = new ArrayList<>();
    public static void clear(){
        demoParams.clear();
        fam_qty = 0;
        ptInfoParams.clear();
        resParams.clear();
        hisParams.clear();
        pastDisParams.clear();
        treatParams.clear();
        dengueParams.clear();
        comParams.clear();
        riskParams.clear();
        admContents.clear();
        deathContents.clear();
    }
}
