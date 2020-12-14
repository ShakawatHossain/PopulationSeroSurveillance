package com.iedcr.populationserosurveillance.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDB extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 3;
    // Database Name
    private static final String DATABASE_NAME = "caseSelection";
    private static String TABLE_DEMO = "demo";
    private static String TABLE_FAM = "family";
    private static String TABLE_ADM = "admission";
    private static String TABLE_DEATH = "death";

    public static String KEY_ID = "id";
    public static String ISUPLOAD = "is_upload";
    public static String CREATED_AT = "created_at";
    public static String SEROID = "sero_id";
    public static String AGE = "age";
    public static String SEX = "sex";
    public static String PRESENT = "is_present";


    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_DEMO = "CREATE TABLE " + TABLE_DEMO
            + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + SEROID + " TEXT,"
//            + SELF_ID + " INTEGER,"
            +"demo_h_no TEXT NOT NULL DEFAULT '',"
                    +"demo_h_name TEXT NOT NULL DEFAULT '',"
                    +"demo_h_mob TEXT NOT NULL DEFAULT '',"
                    +"demo_fam_qty TEXT NOT NULL DEFAULT '',"
                    +"demo_room_qty TEXT NOT NULL DEFAULT '',"
                    +"demo_toilet_qty TEXT NOT NULL DEFAULT '',"
                    +"demo_drink_src_type TEXT NOT NULL DEFAULT '',"
                    +"demo_drink_src TEXT NOT NULL DEFAULT '',"
                    +"demo_boil TEXT NOT NULL DEFAULT '',"
                    +"demo_filter TEXT NOT NULL DEFAULT '',"
                    +"demo_water_puri_type_oth TEXT NOT NULL DEFAULT '',"
                    +"demo_water_puri_type_oth_txt TEXT NOT NULL DEFAULT '',"
                    +"demo_present_mem TEXT NOT NULL DEFAULT '',"
                    +"demo_expense TEXT NOT NULL DEFAULT '',"
                    +"demo_consent TEXT NOT NULL DEFAULT '',"
                    +"demo_kitchen TEXT NOT NULL DEFAULT '',"
                    +"demo_basin TEXT NOT NULL DEFAULT '',"
                    +"pt_participant TEXT NOT NULL DEFAULT '',"
                    +"pt_ans_gen TEXT NOT NULL DEFAULT '',"
                    +"pt_pt_gen TEXT NOT NULL DEFAULT '',"
                    +"pt_ans_name TEXT NOT NULL DEFAULT '',"
                    +"pt_ans_age TEXT NOT NULL DEFAULT '',"
                    +"pt_ans_rel TEXT NOT NULL DEFAULT '',"
                    +"pt_ans_home TEXT NOT NULL DEFAULT '',"
                    +"pt_ans_block TEXT NOT NULL DEFAULT '',"
                    +"pt_ans_subblock TEXT NOT NULL DEFAULT '',"
                    +"pt_ans_ward TEXT NOT NULL DEFAULT '',"
                    +"pt_ans_area TEXT NOT NULL DEFAULT '',"
                    +"pt_ans_mob TEXT NOT NULL DEFAULT '',"
                    +"pt_pt_name TEXT NOT NULL DEFAULT '',"
                    +"pt_pt_age TEXT NOT NULL DEFAULT '',"
                    +"pt_pt_rel TEXT NOT NULL DEFAULT '',"
                    +"pt_pt_home TEXT NOT NULL DEFAULT '',"
                    +"pt_pt_block TEXT NOT NULL DEFAULT '',"
                    +"pt_pt_subblock TEXT NOT NULL DEFAULT '',"
                    +"pt_pt_ward TEXT NOT NULL DEFAULT '',"
                    +"pt_pt_area TEXT NOT NULL DEFAULT '',"
                    +"pt_pt_mob TEXT NOT NULL DEFAULT '',"
                    +"pt_edu TEXT NOT NULL DEFAULT '',"
                    +"pt_edu_oth_txt TEXT NOT NULL DEFAULT '',"
                    +"pt_ans_dob TEXT NOT NULL DEFAULT '',"
                    +"pt_pt_dob TEXT NOT NULL DEFAULT '',"
                    +"pt_ans_dob_un TEXT NOT NULL DEFAULT '',"
                    +"pt_ans_age_un TEXT NOT NULL DEFAULT '',"
                    +"pt_pt_dob_un TEXT NOT NULL DEFAULT '',"
                    +"pt_pt_age_un TEXT NOT NULL DEFAULT '',"
                    +"pt_edu_il TEXT NOT NULL DEFAULT '',"
                    +"pt_edu_sig TEXT NOT NULL DEFAULT '',"
                    +"pt_edu_un TEXT NOT NULL DEFAULT '',"
                    +"pt_edu_oth TEXT NOT NULL DEFAULT '',"
                    +"pt_ocu TEXT NOT NULL DEFAULT '',"
                    +"res_respiratory TEXT NOT NULL DEFAULT '',"
                    +"res_fever TEXT NOT NULL DEFAULT '',"
                    +"res_cough TEXT NOT NULL DEFAULT '',"
                    +"res_asthma TEXT NOT NULL DEFAULT '',"
                    +"res_tired TEXT NOT NULL DEFAULT '',"
                    +"res_body_pain TEXT NOT NULL DEFAULT '',"
                    +"res_headache TEXT NOT NULL DEFAULT '',"
                    +"res_test TEXT NOT NULL DEFAULT '',"
                    +"res_smell TEXT NOT NULL DEFAULT '',"
                    +"res_throat_pain TEXT NOT NULL DEFAULT '',"
                    +"res_cold TEXT NOT NULL DEFAULT '',"
                    +"res_diarrhoea TEXT NOT NULL DEFAULT '',"
                    +"res_respiratory_date TEXT NOT NULL DEFAULT '',"
                    +"res_fever_date TEXT NOT NULL DEFAULT '',"
                    +"res_cough_date TEXT NOT NULL DEFAULT '',"
                    +"res_asthma_date TEXT NOT NULL DEFAULT '',"
                    +"res_tired_date TEXT NOT NULL DEFAULT '',"
                    +"res_body_pain_date TEXT NOT NULL DEFAULT '',"
                    +"res_headache_date TEXT NOT NULL DEFAULT '',"
                    +"res_test_date TEXT NOT NULL DEFAULT '',"
                    +"res_smell_date TEXT NOT NULL DEFAULT '',"
                    +"res_throat_pain_date TEXT NOT NULL DEFAULT '',"
                    +"res_cold_date TEXT NOT NULL DEFAULT '',"
                    +"res_diarrhoea_date TEXT NOT NULL DEFAULT '',"
                    +"res_res_card TEXT NOT NULL DEFAULT '',"
                    +"his_covid_pt TEXT NOT NULL DEFAULT '',"
                    +"his_join TEXT NOT NULL DEFAULT '',"
                    +"his_test TEXT NOT NULL DEFAULT '',"
                    +"his_journey TEXT NOT NULL DEFAULT '',"
                    +"his_health TEXT NOT NULL DEFAULT '',"
                    +"his_quack TEXT NOT NULL DEFAULT '',"
                    +"his_test_date TEXT NOT NULL DEFAULT '',"
                    +"his_journey_from TEXT NOT NULL DEFAULT '',"
                    +"his_journey_to TEXT NOT NULL DEFAULT '',"
                    +"his_health_name TEXT NOT NULL DEFAULT '',"
                    +"his_quack_name TEXT NOT NULL DEFAULT '',"
                    +"past_prev_dis TEXT NOT NULL DEFAULT '',"
                    +"past_fev TEXT NOT NULL DEFAULT '',"
                    +"past_shivr TEXT NOT NULL DEFAULT '',"
                    +"past_nasal TEXT NOT NULL DEFAULT '',"
                    +"past_cough TEXT NOT NULL DEFAULT '',"
                    +"past_asthma TEXT NOT NULL DEFAULT '',"
                    +"past_vomit TEXT NOT NULL DEFAULT '',"
                    +"past_nausea TEXT NOT NULL DEFAULT '',"
                    +"past_diarr TEXT NOT NULL DEFAULT '',"
                    +"past_head TEXT NOT NULL DEFAULT '',"
                    +"past_rush TEXT NOT NULL DEFAULT '',"
                    +"past_conj TEXT NOT NULL DEFAULT '',"
                    +"past_musc TEXT NOT NULL DEFAULT '',"
                    +"past_join TEXT NOT NULL DEFAULT '',"
                    +"past_hung TEXT NOT NULL DEFAULT '',"
                    +"past_ageu TEXT NOT NULL DEFAULT '',"
                    +"past_smell TEXT NOT NULL DEFAULT '',"
                    +"past_epis TEXT NOT NULL DEFAULT '',"
                    +"past_tired TEXT NOT NULL DEFAULT '',"
                    +"past_seizure TEXT NOT NULL DEFAULT '',"
                    +"past_faint TEXT NOT NULL DEFAULT '',"
                    +"past_oth TEXT NOT NULL DEFAULT '',"
                    +"past_fev_date TEXT NOT NULL DEFAULT '',"
                    +"past_nasal_date TEXT NOT NULL DEFAULT '',"
                    +"past_cough_date TEXT NOT NULL DEFAULT '',"
                    +"past_asthma_date TEXT NOT NULL DEFAULT '',"
                    +"past_vomit_date TEXT NOT NULL DEFAULT '',"
                    +"past_nausea_date TEXT NOT NULL DEFAULT '',"
                    +"past_diarr_date TEXT NOT NULL DEFAULT '',"
                    +"past_head_date TEXT NOT NULL DEFAULT '',"
                    +"past_rush_date TEXT NOT NULL DEFAULT '',"
                    +"past_conj_date TEXT NOT NULL DEFAULT '',"
                    +"past_musc_date TEXT NOT NULL DEFAULT '',"
                    +"past_join_date TEXT NOT NULL DEFAULT '',"
                    +"past_hung_date TEXT NOT NULL DEFAULT '',"
                    +"past_ageu_date TEXT NOT NULL DEFAULT '',"
                    +"past_smell_date TEXT NOT NULL DEFAULT '',"
                    +"past_epis_date TEXT NOT NULL DEFAULT '',"
                    +"past_tired_date TEXT NOT NULL DEFAULT '',"
                    +"past_seizure_date TEXT NOT NULL DEFAULT '',"
                    +"past_faint_date TEXT NOT NULL DEFAULT '',"
                    +"past_oth_date TEXT NOT NULL DEFAULT '',"
                    +"past_fev_temp TEXT NOT NULL DEFAULT '',"
                    +"past_oth_txt TEXT NOT NULL DEFAULT '',"
                    +"treat_test TEXT NOT NULL DEFAULT '',"
                    +"treat_treat TEXT NOT NULL DEFAULT '',"
                    +"treat_dis TEXT NOT NULL DEFAULT '',"
                    +"treat_adm TEXT NOT NULL DEFAULT '',"
                    +"treat_oth_test TEXT NOT NULL DEFAULT '',"
                    +"treat_oth_test_result TEXT NOT NULL DEFAULT '',"
                    +"treat_death TEXT NOT NULL DEFAULT '',"
                    +"treat_death_num TEXT NOT NULL DEFAULT '',"
                    +"treat_test_date TEXT NOT NULL DEFAULT '',"
                    +"treat_treat_date TEXT NOT NULL DEFAULT '',"
                    +"treat_adm_num TEXT NOT NULL DEFAULT '',"
                    +"treat_oth_test_date TEXT NOT NULL DEFAULT '',"
                    +"treat_treat_freq TEXT NOT NULL DEFAULT '',"
                    +"dengue_dengue TEXT NOT NULL DEFAULT '',"
                    +"dengue_dengue_day_type TEXT NOT NULL DEFAULT '',"
                    +"dengue_dengue_how TEXT NOT NULL DEFAULT '',"
                    +"dengue_dengue_hos TEXT NOT NULL DEFAULT '',"
                    +"dengue_cikon TEXT NOT NULL DEFAULT '',"
                    +"dengue_cikon_day_type TEXT NOT NULL DEFAULT '',"
                    +"dengue_cikon_how TEXT NOT NULL DEFAULT '',"
                    +"dengue_cikon_hos TEXT NOT NULL DEFAULT '',"
                    +"dengue_preg TEXT NOT NULL DEFAULT '',"
                    +"dengue_trim TEXT NOT NULL DEFAULT '',"
                    +"dengue_dengue_day TEXT NOT NULL DEFAULT '',"
                    +"dengue_cikon_day TEXT NOT NULL DEFAULT '',"
                    +"com_cancer TEXT NOT NULL DEFAULT '',"
                    +"com_dia TEXT NOT NULL DEFAULT '',"
                    +"com_aids TEXT NOT NULL DEFAULT '',"
                    +"com_heart TEXT NOT NULL DEFAULT '',"
                    +"com_asthma TEXT NOT NULL DEFAULT '',"
                    +"com_resp TEXT NOT NULL DEFAULT '',"
                    +"com_liver TEXT NOT NULL DEFAULT '',"
                    +"com_hema TEXT NOT NULL DEFAULT '',"
                    +"com_kidney TEXT NOT NULL DEFAULT '',"
                    +"com_nuro TEXT NOT NULL DEFAULT '',"
                    +"com_joint TEXT NOT NULL DEFAULT '',"
                    +"com_smoke TEXT NOT NULL DEFAULT '',"
                    +"com_tobac TEXT NOT NULL DEFAULT '',"
                    +"com_oth TEXT NOT NULL DEFAULT '',"
                    +"risk_mask TEXT NOT NULL DEFAULT '',"
                    +"risk_mask_type TEXT NOT NULL DEFAULT '',"
                    +"risk_hwash TEXT NOT NULL DEFAULT '',"
                    +"risk_tsec TEXT NOT NULL DEFAULT '',"
                    +"risk_cover TEXT NOT NULL DEFAULT '',"
                    +"risk_ctsec TEXT NOT NULL DEFAULT '',"
                    +"risk_inv TEXT NOT NULL DEFAULT '',"
                    +"risk_cmeet TEXT NOT NULL DEFAULT '',"
                    +"risk_cmeet_place TEXT NOT NULL DEFAULT '',"
                    +"risk_pt TEXT NOT NULL DEFAULT '',"
                    +"risk_sdist TEXT NOT NULL DEFAULT '',"
                    +"risk_net_time TEXT NOT NULL DEFAULT '',"
                    +"risk_media TEXT NOT NULL DEFAULT '',"
                    +"risk_htips TEXT NOT NULL DEFAULT '',"
                    +"risk_kit TEXT NOT NULL DEFAULT '',"
                    +"risk_mask_oth_txt TEXT NOT NULL DEFAULT '',"
                    +"risk_mask_type_oth_txt TEXT NOT NULL DEFAULT '',"
                    +"risk_hwash_freq TEXT NOT NULL DEFAULT '',"
                    +"risk_cover_by_oth TEXT NOT NULL DEFAULT '',"
                    +"risk_inv_day TEXT NOT NULL DEFAULT '',"
                    +"risk_cmeet_oth_txt TEXT NOT NULL DEFAULT '',"
                    +"risk_pt_by_oth TEXT NOT NULL DEFAULT '',"
                    +"risk_mbite_oth TEXT NOT NULL DEFAULT '',"
                    +"risk_net_oth_txt TEXT NOT NULL DEFAULT '',"
                    +"risk_inf_oth TEXT NOT NULL DEFAULT '',"
                    +"risk_name TEXT NOT NULL DEFAULT '',"
                    +"risk_kitorg_name TEXT NOT NULL DEFAULT '',"
                    +"risk_cmeet_day TEXT NOT NULL DEFAULT '',"
                    +"risk_elbow TEXT NOT NULL DEFAULT '',"
                    +"risk_clth TEXT NOT NULL DEFAULT '',"
                    +"risk_palm TEXT NOT NULL DEFAULT '',"
                    +"risk_cough TEXT NOT NULL DEFAULT '',"
                    +"risk_touch TEXT NOT NULL DEFAULT '',"
                    +"risk_shake TEXT NOT NULL DEFAULT '',"
                    +"risk_inf TEXT NOT NULL DEFAULT '',"
                    +"risk_food TEXT NOT NULL DEFAULT '',"
                    +"risk_govt TEXT NOT NULL DEFAULT '',"
                    +"risk_pvt TEXT NOT NULL DEFAULT '',"
                    +"risk_net TEXT NOT NULL DEFAULT '',"
                    +"risk_coil TEXT NOT NULL DEFAULT '',"
                    +"risk_dhup TEXT NOT NULL DEFAULT '',"
                    +"risk_mbite_no TEXT NOT NULL DEFAULT '',"
//            + NAME + " TEXT )" ;
            + ISUPLOAD + " INTEGER DEFAULT 0,"
            + CREATED_AT +" DATETIME DEFAULT CURRENT_TIMESTAMP )";
//
    private static final String CREATE_FAM = "CREATE TABLE " + TABLE_FAM
        + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
        + SEROID + " TEXT,"
        + AGE + " INTEGER,"
        + SEX + " INTEGER,"
        + PRESENT + " INTEGER,"
        + ISUPLOAD + " INTEGER DEFAULT 0,"
        + CREATED_AT +" DATETIME DEFAULT CURRENT_TIMESTAMP )";

    private static final String CREATE_ADM = "CREATE TABLE " + TABLE_ADM
            + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + SEROID + " TEXT,"
            + "adm_date TEXT,"
            + "rel_date TEXT,"
            + "icu INTEGER,"
            + ISUPLOAD + " INTEGER DEFAULT 0,"
            + CREATED_AT +" DATETIME DEFAULT CURRENT_TIMESTAMP )";
    private static final String CREATE_DEATH = "CREATE TABLE " + TABLE_DEATH
            + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + SEROID + " TEXT,"
            + "death_date TEXT,"
            + "home INTEGER,"
            + "hospital INTEGER,"
            + ISUPLOAD + " INTEGER DEFAULT 0,"
            + CREATED_AT +" DATETIME DEFAULT CURRENT_TIMESTAMP )";

    public MyDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
//        Log.d("DIS",CREATE_DIS);
//        Log.d("DIS",CREATE_UP);
//        Log.d("DIS",CREATE_MC);
//        Log.d("DIS",CREATE_THANA);
//        Log.d("DIS",CREATE_CITY);
        db.execSQL(CREATE_DEMO);
        db.execSQL(CREATE_FAM);
        db.execSQL(CREATE_ADM);
        db.execSQL(CREATE_DEATH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEMO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEATH);
        onCreate(db);
    }

    public void checkndelRec(){
        Log.d("prev.","Record Check");
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteDatabase db1 = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_DEMO +" WHERE " +SEROID + " = '"+ModelDemographic.sero_id +"'";
        Log.d("checkQ",selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()){
            do {
                Log.d("prev.","Demo Record Found");
                String deletetQuery = "DELETE FROM " + TABLE_DEMO +" WHERE " +SEROID + " = '"+ModelDemographic.sero_id +"'";
                db1.execSQL(deletetQuery);
            }while (c.moveToNext());
        }
        String selectQuery1 = "SELECT  * FROM " + TABLE_FAM +" WHERE " +SEROID + " = '"+ModelDemographic.sero_id +"'";
        Log.d("checkQ",selectQuery1);
        Cursor c1 = db.rawQuery(selectQuery1, null);
        if (c1.moveToFirst()){
//            do {
//                Log.d("prev.","Fam Record Found");
                String deletetQuery = "DELETE FROM " + TABLE_FAM + " WHERE " + SEROID + " = '"+ModelDemographic.sero_id +"'";
                db1.execSQL(deletetQuery);
//            }while (c1.moveToNext());
        }
        String selectQuery3 = "SELECT  * FROM " + TABLE_ADM +" WHERE " +SEROID + " = '"+ModelDemographic.sero_id +"'";
        Log.d("checkQ",selectQuery3);
        Cursor c3 = db.rawQuery(selectQuery3, null);
        if (c3.moveToFirst()){
//            do {
//                Log.d("prev.","ADM Record Found");
                String deletetQuery = "DELETE FROM " + TABLE_ADM + " WHERE " + SEROID + " = '"+ModelDemographic.sero_id +"'";
                db1.execSQL(deletetQuery);
//            }while (c3.moveToNext());
        }
        String selectQuery4 = "SELECT  * FROM " + TABLE_DEATH +" WHERE " +SEROID + " = '"+ModelDemographic.sero_id +"'";
        Log.d("checkQ",selectQuery4);
        Cursor c4 = db.rawQuery(selectQuery4, null);
        if (c4.moveToFirst()){
//            do {
//                Log.d("prev.","ADM Record Found");
            String deletetQuery = "DELETE FROM " + TABLE_DEATH + " WHERE " + SEROID + " = '"+ModelDemographic.sero_id +"'";
            db1.execSQL(deletetQuery);
//            }while (c3.moveToNext());
        }
    }

    public void insert(){
        checkndelRec();
        ContentValues cvdemo = new ContentValues();
        cvdemo.put(SEROID,ModelDemographic.sero_id);
        for (String key:ModelDemographic.demoParams.keySet()) cvdemo.put("demo_"+key,ModelDemographic.demoParams.get(key));
        for (String key:ModelDemographic.ptInfoParams.keySet()) cvdemo.put("pt_"+key,ModelDemographic.ptInfoParams.get(key));
        for (String key:ModelDemographic.resParams.keySet()) cvdemo.put("res_"+key,ModelDemographic.resParams.get(key));
        for (String key:ModelDemographic.hisParams.keySet()) cvdemo.put("his_"+key,ModelDemographic.hisParams.get(key));
        for (String key:ModelDemographic.pastDisParams.keySet()) cvdemo.put("past_"+key,ModelDemographic.pastDisParams.get(key));
        for (String key:ModelDemographic.treatParams.keySet()) cvdemo.put("treat_"+key,ModelDemographic.treatParams.get(key));
        for (String key:ModelDemographic.dengueParams.keySet()) cvdemo.put("dengue_"+key,ModelDemographic.dengueParams.get(key));
        for (String key:ModelDemographic.comParams.keySet()) cvdemo.put("com_"+key,ModelDemographic.comParams.get(key));
        for (String key:ModelDemographic.riskParams.keySet()) cvdemo.put("risk_"+key,ModelDemographic.riskParams.get(key));
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cvdemo.put(CREATED_AT,dateFormat.format(currentTime));
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_DEMO, null, cvdemo);
        for(FamilyMemberContent fc: ModelFamilyMember.familyMemberContents){
            ContentValues cvfam = new ContentValues();
            cvfam.put(SEROID,ModelDemographic.sero_id);
            cvfam.put(AGE,fc.age);
            cvfam.put(SEX,fc.sex);
            cvfam.put(PRESENT,fc.is_present);
//            Date currentTime = Calendar.getInstance().getTime();
//            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            cvfam.put(CREATED_AT,dateFormat.format(currentTime));
            db.insert(TABLE_FAM, null, cvfam);
        }
        for(AdmContent adm: ModelDemographic.admContents){
            ContentValues cvadm = new ContentValues();
            cvadm.put(SEROID,ModelDemographic.sero_id);
            cvadm.put("adm_date",adm.adm_date);
            cvadm.put("rel_date",adm.rel_date);
            cvadm.put("icu",adm.icu);
            cvadm.put(CREATED_AT,dateFormat.format(currentTime));
            db.insert(TABLE_ADM, null, cvadm);
        }
        for(DeathContent dc: ModelDemographic.deathContents){
            ContentValues cvdc = new ContentValues();
            cvdc.put(SEROID,ModelDemographic.sero_id);
            cvdc.put("death_date",dc.death_date);
            cvdc.put("home",dc.home);
            cvdc.put("hospital",dc.hos);
            cvdc.put(CREATED_AT,dateFormat.format(currentTime));
            db.insert(TABLE_DEATH, null, cvdc);
        }
    }
    public Cursor getAll_demo(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_DEMO ;
        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor getAll_fam(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_FAM ;
        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor getAll_adm(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_ADM ;
        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor getAll_death(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_DEATH ;
        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor get_demo(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_DEMO +" WHERE "+ ISUPLOAD + " =0";
        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor get_fam(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_FAM +" WHERE "+ ISUPLOAD + " =0";
        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor get_adm(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_ADM +" WHERE "+ ISUPLOAD + " =0";
        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor get_death(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_DEATH +" WHERE "+ ISUPLOAD + " =0";
        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor get_fam_sero(String sero){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_FAM +" WHERE "+ SEROID + " = '"+sero+"'";
        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor get_adm_sero(String sero){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_ADM +" WHERE "+ SEROID + " = '"+sero+"'";
        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor get_death_sero(String sero){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_DEATH +" WHERE "+ SEROID + " = '"+sero+"'";
        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }
    public void updateDemo(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ISUPLOAD,1);
        db.update(TABLE_DEMO,cv,ISUPLOAD+ " = ? ",new String[]{String.valueOf(0)});
    }
    public void updateFam(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ISUPLOAD,1);
        db.update(TABLE_FAM,cv,ISUPLOAD+ " = ? ",new String[]{String.valueOf(0)});
    }
    public void updateAdm(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ISUPLOAD,1);
        db.update(TABLE_ADM,cv,ISUPLOAD+ " = ? ",new String[]{String.valueOf(0)});
    }
    public void updateDeath(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ISUPLOAD,1);
        db.update(TABLE_DEATH,cv,ISUPLOAD+ " = ? ",new String[]{String.valueOf(0)});
    }
    public void delete(){
//        String query = "DELETE FROM "+TABLE_DEMO+" WHERE "+ SEROID + " IS NULL ";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+TABLE_DEMO+" where "+SEROID+" IS NULL");
        db.execSQL("delete from "+TABLE_FAM+" where "+SEROID+" IS NULL");
        db.execSQL("delete from "+TABLE_ADM+" where "+SEROID+" IS NULL");
        db.execSQL("delete from "+TABLE_DEATH+" where "+SEROID+" IS NULL");
    }
}
