package edu.aku.hassannaqvi.blf_screening.models;


import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.blf_screening.contracts.DistrictsContract;
import edu.aku.hassannaqvi.blf_screening.contracts.childFollowupContract;

public class FollowUps {

    private static final String TAG = "FormsWF_CONTRACT";

    String mrno;
    String studyid;
    String fupdt;
    String fupweek;
    String chName;
    String mName;
    String mrno_m;
    String fupdonedt;
    String s1q501;

    public FollowUps() {
        // Default Constructor
    }

    public String getS1q501() {
        return s1q501;
    }

    public void setS1q501(String s1q501) {
        this.s1q501 = s1q501;
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



    public FollowUps Hydrate(JSONObject fup) throws JSONException {

        this.mrno = fup.getString("mrno");
        this.studyid = fup.getString("studyid");
        this.fupdt = fup.getString("fupdt");
        this.fupweek = fup.getString("fupweek");
        this.chName = fup.getString("chName");
        this.mName = fup.getString("mName");
        this.mrno_m = fup.getString("mrno_m");
        this.fupdonedt = fup.getString("fupdonedt");
        this.s1q501 = fup.getString("s1q501");

        return this;
    }


    public JSONObject toJSONObject() {

        JSONObject json = new JSONObject();
        try {
            json.put(childFollowupContract.childFollowupTable.COLUMN_MRNO, this.mrno == null ? JSONObject.NULL : this.mrno);
            json.put(childFollowupContract.childFollowupTable.COLUMN_STUDYID, this.studyid == null ? JSONObject.NULL : this.studyid);
            json.put(childFollowupContract.childFollowupTable.COLUMN_FUPDT, this.fupdt == null ? JSONObject.NULL : this.fupdt);
            json.put(childFollowupContract.childFollowupTable.COLUMN_FUPWEEK, this.fupweek == null ? JSONObject.NULL : this.fupweek);
            json.put(childFollowupContract.childFollowupTable.COLUMN_CHNAME, this.chName == null ? JSONObject.NULL : this.chName);
            json.put(childFollowupContract.childFollowupTable.COLUMN_MNAME, this.mName == null ? JSONObject.NULL : this.mName);
            json.put(childFollowupContract.childFollowupTable.COLUMN_MRNO_M, this.mrno_m == null ? JSONObject.NULL : this.mrno_m);
            json.put(childFollowupContract.childFollowupTable.COLUMN_FUPDONEDT, this.fupdonedt == null ? JSONObject.NULL : this.fupdonedt);
            json.put(childFollowupContract.childFollowupTable.COLUMN_S1Q501, this.s1q501 == null ? JSONObject.NULL : this.s1q501);
            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public FollowUps Sync(JSONObject jsonObject) throws JSONException {

        this.mrno = jsonObject.getString(childFollowupContract.childFollowupTable.COLUMN_MRNO);
        this.studyid = jsonObject.getString(childFollowupContract.childFollowupTable.COLUMN_STUDYID);
        this.fupdt = jsonObject.getString(childFollowupContract.childFollowupTable.COLUMN_FUPDT);
        this.fupweek = jsonObject.getString(childFollowupContract.childFollowupTable.COLUMN_FUPWEEK);
        this.chName = jsonObject.getString(childFollowupContract.childFollowupTable.COLUMN_CHNAME);
        this.mName = jsonObject.getString(childFollowupContract.childFollowupTable.COLUMN_MNAME);
        this.mrno_m = jsonObject.getString(childFollowupContract.childFollowupTable.COLUMN_MRNO_M);
        this.fupdonedt = jsonObject.getString(childFollowupContract.childFollowupTable.COLUMN_FUPDONEDT);
        this.s1q501 = jsonObject.getString(childFollowupContract.childFollowupTable.COLUMN_S1Q501);

        return this;
    }

    /*public Districts HydrateDist(Cursor cursor) {
        this.prov = cursor.getString(cursor.getColumnIndex(DistrictsContract.DistrictsTable.COLUMN_PROV));
        this.dist_id = cursor.getString(cursor.getColumnIndex(DistrictsContract.DistrictsTable.COLUMN_DIST_ID));
        this.admin_unit = cursor.getString(cursor.getColumnIndex(DistrictsContract.DistrictsTable.COLUMN_ADMIN_UNIT));
        return this;
    }*/
}