package edu.aku.hassannaqvi.blf_screening.models;


import org.json.JSONException;
import org.json.JSONObject;

public class FollowUps {

    private static final String TAG = "Districts_CONTRACT";
    String mrno;
    String studyid;
    String fupdt;
    String fupweek;
    String chName;
    String mName;
    String mrno_m;
    String fupdonedt;

    public FollowUps() {
        // Default Constructor
    }


    public FollowUps Hydrate(JSONObject fup) throws JSONException {

        this.studyid = fup.getString("studyid");
        this.mrno = fup.getString("mrno");
        this.fupdt = fup.getString("fupdt");
        this.fupweek = fup.getString("fupweek");
        this.chName = fup.getString("chName");
        this.mName = fup.getString("mName");
        this.mrno_m = fup.getString("mrno_m");
        this.fupdonedt = fup.getString("fupdonedt");

        return this;
    }

    public String getStudyid() {
        return studyid;
    }

    public FollowUps setStudyid(String studyid) {
        this.studyid = studyid;
        return this;
    }

    public String getMrno() {
        return mrno;
    }

    public FollowUps setMrno(String mrno) {
        this.mrno = mrno;
        return this;
    }

    public String getFupdt() {
        return fupdt;
    }

    public FollowUps setFupdt(String fupdt) {
        this.fupdt = fupdt;
        return this;
    }

    public String getChName() {
        return chName;
    }

    public FollowUps setChName(String chName) {
        this.chName = chName;
        return this;
    }

    public String getMrno_m() {
        return mrno_m;
    }

    public FollowUps setMrno_m(String mrno_m) {
        this.mrno_m = mrno_m;
        return this;
    }

    public String getFupdonedt() {
        return fupdonedt;
    }

    public FollowUps setFupdonedt(String fupdonedt) {
        this.fupdonedt = fupdonedt;
        return this;
    }

    public String getFupweek() {
        return fupweek;
    }

    public FollowUps setFupweek(String fupweek) {
        this.fupweek = fupweek;
        return this;
    }

    public String getmName() {
        return mName;
    }

    public FollowUps setmName(String mName) {
        this.mName = mName;
        return this;
    }
}