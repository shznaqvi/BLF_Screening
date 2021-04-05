package edu.aku.hassannaqvi.blf_screening.models;

import android.database.Cursor;

import androidx.lifecycle.LiveData;

import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.blf_screening.contracts.FormsSESContract;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsSESContract.FormsSESTable;

/**
 * Created by hassan.naqvi on 11/30/2016.
 * */

public class FormsSES extends LiveData<FormsSES> {

    private String projectName = "blf";

    // Section 1
    public String se1q1 = "";
    public String se1q196x = "";
    public String se1q2 = "";
    public String se1q296x = "";
    public String se1q3 = "";
    public String se1q396x = "";
    public String se1q4 = "";
    public String se1q5 = "";
    public String se1q596x = "";
    public String se1q6 = "";
    public String se1q696x = "";
    public String se1q7 = "";
    public String se1q796x = "";
    public String se1q8 = "";
    public String se1q9 = "";
    public String se1q10 = "";
    public String se1q11 = "";
    public String se1q12 = "";
    public String se1q1296x = "";
    public String se1q1301 = "";
    public String se1q1302 = "";
    public String se1q1303 = "";
    public String se1q1304 = "";
    public String se1q1305 = "";
    public String se1q1306 = "";
    public String se1q1307 = "";
    public String se1q1308 = "";
    public String se1q1309 = "";
    public String se1q13010 = "";
    public String se1q13011 = "";
    public String se1q13012 = "";
    public String se1q13013 = "";
    public String se1q13014 = "";
    public String se1q13015 = "";
    public String se1q13016 = "";
    public String se1q13017 = "";
    public String se1q13018 = "";
    public String se1q1401 = "";
    public String se1q1402 = "";
    public String se1q1403 = "";
    public String se1q1404 = "";
    public String se1q1405 = "";
    public String se1q1406 = "";
    public String se1q1407 = "";
    public String se1q1408 = "";
    public String se1q1409 = "";
    public String se1q15 = "";
    public String se1q1596x = "";
    public String se1q16 = "";
    public String se1q1696x = "";
    public String se1q17 = "";
    public String se1q18 = "";
    public String se1q1896x = "";
    public String se1q19 = "";
    public String se1q1996x = "";
    public String se1q20 = "";
    public String se1q21 = "";
    public String se1q22 = "";
    public String se1q23 = "";
    public String se1q2401 = "";
    public String se1q2402 = "";
    public String se1q2403 = "";
    public String se1q2404 = "";
    public String se1q2405 = "";
    public String se1q2406 = "";
    public String se1q2407 = "";
    public String se1q25 = "";

    // Section 2
    public String se2q0 = "";
    public String se2q1 = "";
    public String se2q2 = "";
    public String se2q3 = "";
    public String se2q4 = "";
    public String se2q5 = "";
    public String se2q6 = "";
    public String se2q7 = "";
    public String se2q8 = "";
    public String se2q9 = "";
    public String se2q10 = "";

    // Section 3
    public String se3q0 = "";
    public String se3q101 = "";
    public String se3q102 = "";
    public String se3q103 = "";
    public String se3q104 = "";
    public String se3q105 = "";
    public String se3q106 = "";
    public String se3q107 = "";
    public String se3q108 = "";
    public String se3q196 = "";
    public String se3q196x = "";
    public String se3q201 = "";
    public String se3q202 = "";
    public String se3q203 = "";
    public String se3q204 = "";
    public String se3q205 = "";
    public String se3q2961 = "";
    public String se3q206 = "";
    public String se3q207 = "";
    public String se3q208 = "";
    public String se3q2962 = "";
    public String se3q2962x = "";
    public String se3q2963 = "";
    public String se3q2963x = "";
    public String se3q3 = "";
    public String se3q301x = "";
    public String se3q302x = "";
    public String se3q4 = "";
    public String se3q401x = "";
    public String se3q501 = "";
    public String se3q502 = "";
    public String se3q503 = "";
    public String se3q504 = "";
    public String se3q505 = "";
    public String se3q506 = "";
    public String se3q507 = "";
    public String se3q508 = "";
    public String se3q509 = "";
    public String se3q510 = "";
    public String se3q511 = "";
    public String se3q511ax = "";
    public String se3q6 = "";
    public String se3q7 = "";
    public String se3q701x = "";
    public String se3q9 = "";
    public String se3q10 = "";
    public String se3q1096x = "";
    public String se3q11 = "";
    public String se3q11961x = "";
    public String se3q11962x = "";
    public String se3q11963x = "";
    public String se3q12 = "";
    public String se3q1301 = "";
    public String se3q1302 = "";
    public String se3q14 = "";
    public String se3q15 = "";
    public String se3q1596x = "";
    public String se3q1601 = "";
    public String se3q1602 = "";
    public String se3q1603 = "";
    public String se3q1604 = "";
    public String se3q1605 = "";
    public String se3q16961 = "";
    public String se3q16961x = "";
    public String se3q1606 = "";
    public String se3q1607 = "";
    public String se3q1608 = "";
    public String se3q1609 = "";
    public String se3q1610 = "";
    public String se3q16962 = "";
    public String se3q16962x = "";
    public String se3q1611 = "";
    public String se3q1612 = "";
    public String se3q1613 = "";
    public String se3q16963 = "";
    public String se3q16963x = "";
    public String se3q17 = "";
    public String se3q1801 = "";
    public String se3q1802 = "";
    public String se3q19 = "";
    public String se3q20 = "";
    public String se3q2096x = "";
    public String se3q2101 = "";
    public String se3q2102 = "";
    public String se3q2103 = "";
    public String se3q2104 = "";
    public String se3q2105 = "";
    public String se3q21961 = "";
    public String se3q21961x = "";
    public String se3q2106 = "";
    public String se3q2107 = "";
    public String se3q2108 = "";
    public String se3q2109 = "";
    public String se3q2110 = "";
    public String se3q21962 = "";
    public String se3q21962x = "";
    public String se3q2111 = "";
    public String se3q2112 = "";
    public String se3q2113 = "";
    public String se3q21963 = "";
    public String se3q21963x = "";
    public String se3q22 = "";
    public String se3q2301 = "";
    public String se3q2302 = "";
    public String se3q24 = "";
    public String se3q25 = "";
    public String se3q26 = "";

    // Section 4
    public String se4q0 = "";
    public String se4q1 = "";
    public String se4q2 = "";
    public String se4q296x = "";
    public String se4q3 = "";
    public String se4q4 = "";
    public String se4q5 = "";
    public String se4q6 = "";
    public String se4q602x = "";
    public String se4q603x = "";
    public String se4q7 = "";
    public String se4q801 = "";
    public String se4q802 = "";
    public String se4q803 = "";
    public String se4q896 = "";
    public String se4q896x = "";
    public String se4q9 = "";
    public String se4q901x = "";
    public String se4q902x = "";
    public String se4q903x = "";
    public String se4q10 = "";
    public String se4q1101 = "";
    public String se4q1102 = "";
    public String se4q1103 = "";
    public String se4q1104 = "";
    public String se4q1105 = "";
    public String se4q1106 = "";
    public String se4q1196 = "";
    public String se4q1196x = "";
    public String se4q12 = "";
    public String se4q1301 = "";
    public String se4q1302 = "";
    public String se4q1303 = "";
    public String se4q1396 = "";
    public String se4q1396x = "";
    public String se4q14 = "";
    public String se4q1401x = "";
    public String se4q1402x = "";
    public String se4q1403x = "";
    public String se4q15 = "";
    public String se4q1601 = "";
    public String se4q1602 = "";
    public String se4q1603 = "";
    public String se4q1605 = "";
    public String se4q1606 = "";
    public String se4q1696 = "";
    public String se4q1696x = "";
    public String se4q17 = "";
    public String se4q1801 = "";
    public String se4q1802 = "";
    public String se4q1803 = "";
    public String se4q1804 = "";
    public String se4q1805 = "";
    public String se4q1806 = "";
    public String se4q1899 = "";
    public String se4q1896 = "";
    public String se4q1896x = "";

    private String _ID = "";
    private String _UID = "";
    private String sysdate = "";
    private String username = ""; // Interviewer
    private String istatus = ""; // Interview Status
    private String istatus96x = ""; // Interview Status
    private String endingdatetime = "";
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion = "";
    private String s1 = "";
    private String s2 = "";
    private String s3 = "";
    private String s4 = "";

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_UID() {
        return _UID;
    }

    public void set_UID(String _UID) {
        this._UID = _UID;
    }

    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }

    public String getIstatus96x() {
        return istatus96x;
    }

    public void setIstatus96x(String istatus96x) {
        this.istatus96x = istatus96x;
    }

    public String getEndingdatetime() {
        return endingdatetime;
    }

    public void setEndingdatetime(String endingdatetime) {
        this.endingdatetime = endingdatetime;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public String getS4() {
        return s4;
    }

    public void setS4(String s4) {
        this.s4 = s4;
    }

    public FormsSES Sync(JSONObject jsonObject) throws JSONException {

        this._ID = jsonObject.getString(FormsSESTable.COLUMN_ID);
        this._UID = jsonObject.getString(FormsSESTable.COLUMN_UID);
        this.sysdate = jsonObject.getString(FormsSESTable.COLUMN_SYSDATE);
        this.istatus = jsonObject.getString(FormsSESTable.COLUMN_ISTATUS);
        this.istatus96x = jsonObject.getString(FormsSESTable.COLUMN_ISTATUS96x);
        this.endingdatetime = jsonObject.getString(FormsSESTable.COLUMN_ENDINGDATETIME);
        this.deviceID = jsonObject.getString(FormsSESTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(FormsSESTable.COLUMN_DEVICETAGID);
        this.synced = jsonObject.getString(FormsSESTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(FormsSESTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(FormsSESTable.COLUMN_APPVERSION);
        this.username = jsonObject.getString(FormsSESTable.COLUMN_USERNAME);
        this.s1 = jsonObject.getString(FormsSESContract.FormsSESTable.COLUMN_S1);
        this.s2 = jsonObject.getString(FormsSESContract.FormsSESTable.COLUMN_S2);
        this.s3 = jsonObject.getString(FormsSESContract.FormsSESTable.COLUMN_S3);
        this.s4 = jsonObject.getString(FormsSESContract.FormsSESTable.COLUMN_S4);

        return this;

    }

    public FormsSES Hydrate(Cursor cursor) {

        this._ID = cursor.getString(cursor.getColumnIndex(FormsSESContract.FormsSESTable.COLUMN_ID));
        this._UID = cursor.getString(cursor.getColumnIndex(FormsSESContract.FormsSESTable.COLUMN_UID));
        this.sysdate = cursor.getString(cursor.getColumnIndex(FormsSESContract.FormsSESTable.COLUMN_SYSDATE));
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsSESContract.FormsSESTable.COLUMN_ISTATUS));
        this.istatus96x = cursor.getString(cursor.getColumnIndex(FormsSESContract.FormsSESTable.COLUMN_ISTATUS96x));
        this.endingdatetime = cursor.getString(cursor.getColumnIndex(FormsSESContract.FormsSESTable.COLUMN_ENDINGDATETIME));
        this.deviceID = cursor.getString(cursor.getColumnIndex(FormsSESContract.FormsSESTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(FormsSESContract.FormsSESTable.COLUMN_DEVICETAGID));
        this.appversion = cursor.getString(cursor.getColumnIndex(FormsSESContract.FormsSESTable.COLUMN_APPVERSION));
        this.username = cursor.getString(cursor.getColumnIndex(FormsSESTable.COLUMN_USERNAME));

        this.s1 = cursor.getString(cursor.getColumnIndex(FormsSESContract.FormsSESTable.COLUMN_S1));
        this.s2 = cursor.getString(cursor.getColumnIndex(FormsSESContract.FormsSESTable.COLUMN_S2));
        this.s3 = cursor.getString(cursor.getColumnIndex(FormsSESContract.FormsSESTable.COLUMN_S3));
        this.s4 = cursor.getString(cursor.getColumnIndex(FormsSESContract.FormsSESTable.COLUMN_S4));

        s1Hydrate(cursor.getString(cursor.getColumnIndex(FormsSESContract.FormsSESTable.COLUMN_S1)));
        s2Hydrate(cursor.getString(cursor.getColumnIndex(FormsSESContract.FormsSESTable.COLUMN_S2)));
        s3Hydrate(cursor.getString(cursor.getColumnIndex(FormsSESContract.FormsSESTable.COLUMN_S3)));
        s4Hydrate(cursor.getString(cursor.getColumnIndex(FormsSESContract.FormsSESTable.COLUMN_S4)));

        return this;
    }

    public JSONObject toJSONObject() {

        JSONObject json = new JSONObject();

        try {

            json.put(FormsSESContract.FormsSESTable.COLUMN_ID, this._ID == null ? JSONObject.NULL : this._ID);
            json.put(FormsSESContract.FormsSESTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
            json.put(FormsSESContract.FormsSESTable.COLUMN_SYSDATE, this.sysdate == null ? JSONObject.NULL : this.sysdate);
            json.put(FormsSESContract.FormsSESTable.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
            json.put(FormsSESContract.FormsSESTable.COLUMN_ISTATUS96x, this.istatus96x == null ? JSONObject.NULL : this.istatus96x);
            json.put(FormsSESContract.FormsSESTable.COLUMN_ENDINGDATETIME, this.endingdatetime == null ? JSONObject.NULL : this.endingdatetime);

            json.put(FormsSESContract.FormsSESTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
            json.put(FormsSESContract.FormsSESTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
            json.put(FormsSESContract.FormsSESTable.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);
            json.put(FormsSESTable.COLUMN_USERNAME, this.username == null ? JSONObject.NULL : this.username);

            json.put(FormsSESContract.FormsSESTable.COLUMN_S1, new JSONObject(s1toString()));
            json.put(FormsSESContract.FormsSESTable.COLUMN_S2, new JSONObject(s2toString()));
            json.put(FormsSESContract.FormsSESTable.COLUMN_S3, new JSONObject(s3toString()));
            json.put(FormsSESContract.FormsSESTable.COLUMN_S4, new JSONObject(s4toString()));

            if (this.s1 != null && !this.s1.equals("")) {
                json.put(FormsSESContract.FormsSESTable.COLUMN_S1, new JSONObject(this.s1));
            }

            if (this.s2 != null && !this.s2.equals("")) {
                json.put(FormsSESContract.FormsSESTable.COLUMN_S2, new JSONObject(this.s2));
            }

            if (this.s3 != null && !this.s3.equals("")) {
                json.put(FormsSESContract.FormsSESTable.COLUMN_S3, new JSONObject(this.s3));
            }

            if (this.s4 != null && !this.s4.equals("")) {
                json.put(FormsSESContract.FormsSESTable.COLUMN_S4, new JSONObject(this.s4));
            }

            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    //TODO: Try this instead of toJSONObject
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, FormsSES.class);
    }


    public String s1toString() {
        JSONObject json = new JSONObject();
        try {

            json.put("se1q1", se1q1)
                    .put("se1q196x", se1q196x)
                    .put("se1q2", se1q2)
                    .put("se1q296x", se1q296x)
                    .put("se1q3", se1q3)
                    .put("se1q396x", se1q396x)
                    .put("se1q4", se1q4)
                    .put("se1q5", se1q5)
                    .put("se1q596x", se1q596x)
                    .put("se1q6", se1q6)
                    .put("se1q696x", se1q696x)
                    .put("se1q7", se1q7)
                    .put("se1q796x", se1q796x)
                    .put("se1q8", se1q8)
                    .put("se1q9", se1q9)
                    .put("se1q10", se1q10)
                    .put("se1q11", se1q11)
                    .put("se1q12", se1q12)
                    .put("se1q1296x", se1q1296x)
                    .put("se1q1301", se1q1301)
                    .put("se1q1302", se1q1302)
                    .put("se1q1303", se1q1303)
                    .put("se1q1304", se1q1304)
                    .put("se1q1305", se1q1305)
                    .put("se1q1306", se1q1306)
                    .put("se1q1307", se1q1307)
                    .put("se1q1308", se1q1308)
                    .put("se1q1309", se1q1309)
                    .put("se1q13010", se1q13010)
                    .put("se1q13011", se1q13011)
                    .put("se1q13012", se1q13012)
                    .put("se1q13013", se1q13013)
                    .put("se1q13014", se1q13014)
                    .put("se1q13015", se1q13015)
                    .put("se1q13016", se1q13016)
                    .put("se1q13017", se1q13017)
                    .put("se1q13018", se1q13018)
                    .put("se1q1401", se1q1401)
                    .put("se1q1402", se1q1402)
                    .put("se1q1403", se1q1403)
                    .put("se1q1404", se1q1404)
                    .put("se1q1405", se1q1405)
                    .put("se1q1406", se1q1406)
                    .put("se1q1407", se1q1407)
                    .put("se1q1408", se1q1408)
                    .put("se1q1409", se1q1409)
                    .put("se1q15", se1q15)
                    .put("se1q1596x", se1q1596x)
                    .put("se1q16", se1q16)
                    .put("se1q1696x", se1q1696x)
                    .put("se1q17", se1q17)
                    .put("se1q18", se1q18)
                    .put("se1q1896x", se1q1896x)
                    .put("se1q19", se1q19)
                    .put("se1q1996x", se1q1996x)
                    .put("se1q20", se1q20)
                    .put("se1q21", se1q21)
                    .put("se1q22", se1q22)
                    .put("se1q23", se1q23)
                    .put("se1q2401", se1q2401)
                    .put("se1q2402", se1q2402)
                    .put("se1q2403", se1q2403)
                    .put("se1q2404", se1q2404)
                    .put("se1q2405", se1q2405)
                    .put("se1q2406", se1q2406)
                    .put("se1q2407", se1q2407)
                    .put("se1q25", se1q25);

        } catch (JSONException e) {
            e.printStackTrace();
            return "\"error\":, \"" + e.getMessage() + "\"";

        }
        return json.toString();
    }

    private void s1Hydrate(String string) {

        if (string != null) {

            try {
                JSONObject json = null;
                json = new JSONObject(string);

                this.se1q2 = json.getString("se1q1");
                this.se1q2 = json.getString("se1q2");
                this.se1q196x = json.getString("se1q196x ");
                this.se1q2 = json.getString("se1q2");
                this.se1q296x = json.getString("se1q296x");
                this.se1q3 = json.getString("se1q3");
                this.se1q396x = json.getString("se1q396x");
                this.se1q4 = json.getString("se1q4");
                this.se1q5 = json.getString("se1q5");
                this.se1q596x = json.getString("se1q596x");
                this.se1q6 = json.getString("se1q6");
                this.se1q696x = json.getString("se1q696x");
                this.se1q7 = json.getString("se1q7");
                this.se1q796x = json.getString("se1q796x");
                this.se1q8 = json.getString("se1q8");
                this.se1q9 = json.getString("se1q9");
                this.se1q10 = json.getString("se1q10");
                this.se1q11 = json.getString("se1q11");
                this.se1q12 = json.getString("se1q12");
                this.se1q1296x = json.getString("se1q1296x");
                this.se1q1301 = json.getString("se1q1301");
                this.se1q1302 = json.getString("se1q1302");
                this.se1q1303 = json.getString("se1q1303 ");
                this.se1q1304 = json.getString("se1q1304");
                this.se1q1305 = json.getString("se1q1305");
                this.se1q1306 = json.getString("se1q1306");
                this.se1q1307 = json.getString("se1q1307");
                this.se1q1308 = json.getString("se1q1308");
                this.se1q1309 = json.getString("se1q1309");
                this.se1q13010 = json.getString("se1q13010");
                this.se1q13011 = json.getString("se1q13011");
                this.se1q13012 = json.getString("se1q13012");
                this.se1q13013 = json.getString("se1q13013");
                this.se1q13014 = json.getString("se1q13014");
                this.se1q13015 = json.getString("se1q13015");
                this.se1q13016 = json.getString("se1q13016");
                this.se1q13017 = json.getString("se1q13017");
                this.se1q13018 = json.getString("se1q13018");
                this.se1q1401 = json.getString("se1q1401");
                this.se1q1402 = json.getString("se1q1402");
                this.se1q1403 = json.getString("se1q1403");
                this.se1q1404 = json.getString("se1q1404");
                this.se1q1405 = json.getString("se1q1405");
                this.se1q1406 = json.getString("se1q1406");
                this.se1q1407 = json.getString("se1q1407");
                this.se1q1408 = json.getString("se1q1408");
                this.se1q1409 = json.getString("se1q1409");
                this.se1q15 = json.getString("se1q15");
                this.se1q1596x = json.getString("se1q1596x");
                this.se1q16 = json.getString("se1q16");
                this.se1q1696x = json.getString("se1q1696x");
                this.se1q17 = json.getString("se1q17");
                this.se1q18 = json.getString("se1q18");
                this.se1q1896x = json.getString("se1q1896x");
                this.se1q19 = json.getString("se1q19");
                this.se1q1996x = json.getString("se1q1996x");
                this.se1q20 = json.getString("se1q20");
                this.se1q21 = json.getString("se1q21");
                this.se1q22 = json.getString("se1q22");
                this.se1q23 = json.getString("se1q23");
                this.se1q2401 = json.getString("se1q2401");
                this.se1q2402 = json.getString("se1q2402");
                this.se1q2403 = json.getString("se1q2403");
                this.se1q2404 = json.getString("se1q2404");
                this.se1q2405 = json.getString("se1q2405");
                this.se1q2406 = json.getString("se1q2406");
                this.se1q2407 = json.getString("se1q2407");
                this.se1q25 = json.getString("se1q25");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public String s2toString() {
        JSONObject json = new JSONObject();
        try {

            json.put("se2q0", se2q0)
                    .put("se2q1", se2q1)
                    .put("se2q2", se2q2)
                    .put("se2q3", se2q3)
                    .put("se2q4", se2q4)
                    .put("se2q5", se2q5)
                    .put("se2q6", se2q6)
                    .put("se2q7", se2q7)
                    .put("se2q8", se2q8)
                    .put("se2q9", se2q9)
                    .put("se2q10", se2q10);

        } catch (JSONException e) {
            e.printStackTrace();
            return "\"error\":, \"" + e.getMessage() + "\"";

        }
        return json.toString();
    }

    private void s2Hydrate(String string) {

        if (string != null) {

            try {
                JSONObject json = null;
                json = new JSONObject(string);
                this.se2q0 = json.getString("se2q0");
                this.se2q1 = json.getString("se2q1");
                this.se2q2 = json.getString("se2q2");
                this.se2q3 = json.getString("se2q3");
                this.se2q4 = json.getString("se2q4");
                this.se2q5 = json.getString("se2q5");
                this.se2q6 = json.getString("se2q6");
                this.se2q7 = json.getString("se2q7");
                this.se2q8 = json.getString("se2q8");
                this.se2q9 = json.getString("se2q9");
                this.se2q10 = json.getString("se2q10");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public String s3toString() {
        JSONObject json = new JSONObject();
        try {

            json.put("se3q0", se3q0)
                    .put("se3q101", se3q101)
                    .put("se3q102", se3q102)
                    .put("se3q103", se3q103)
                    .put("se3q104", se3q104)
                    .put("se3q105", se3q105)
                    .put("se3q106", se3q106)
                    .put("se3q107", se3q107)
                    .put("se3q108", se3q108)
                    .put("se3q196", se3q196)
                    .put("se3q196x", se3q196x)
                    .put("se3q201", se3q201)
                    .put("se3q202", se3q202)
                    .put("se3q203", se3q203)
                    .put("se3q204", se3q204)
                    .put("se3q205", se3q205)
                    .put("se3q2961", se3q2961)
                    .put("se3q206", se3q206)
                    .put("se3q207", se3q207)
                    .put("se3q208", se3q208)
                    .put("se3q2962", se3q2962)
                    .put("se3q2962x", se3q2962x)
                    .put("se3q2963", se3q2963)
                    .put("se3q2963x", se3q2963x)
                    .put("se3q3", se3q3)
                    .put("se3q301x", se3q301x)
                    .put("se3q302x", se3q302x)
                    .put("se3q4", se3q4)
                    .put("se3q401x", se3q401x)
                    .put("se3q501", se3q501)
                    .put("se3q502", se3q502)
                    .put("se3q503", se3q503)
                    .put("se3q504", se3q504)
                    .put("se3q505", se3q505)
                    .put("se3q506", se3q506)
                    .put("se3q507", se3q507)
                    .put("se3q508", se3q508)
                    .put("se3q509", se3q509)
                    .put("se3q510", se3q510)
                    .put("se3q511", se3q511)
                    .put("se3q511ax", se3q511ax)
                    .put("se3q6", se3q6)
                    .put("se3q7", se3q7)
                    .put("se3q701x", se3q701x)
                    .put("se3q9", se3q9)
                    .put("se3q10", se3q10)
                    .put("se3q1096x", se3q1096x)
                    .put("se3q11", se3q11)
                    .put("se3q11961x", se3q11961x)
                    .put("se3q11962x", se3q11962x)
                    .put("se3q11963x", se3q11963x)
                    .put("se3q12", se3q12)
                    .put("se3q1301", se3q1301)
                    .put("se3q1302", se3q1302)
                    .put("se3q14", se3q14)
                    .put("se3q15", se3q15)
                    .put("se3q1596x", se3q1596x)
                    .put("se3q1601", se3q1601)
                    .put("se3q1602", se3q1602)
                    .put("se3q1603", se3q1603)
                    .put("se3q1604", se3q1604)
                    .put("se3q1605", se3q1605)
                    .put("se3q16961", se3q16961)
                    .put("se3q16961x", se3q16961x)
                    .put("se3q1606", se3q1606)
                    .put("se3q1607", se3q1607)
                    .put("se3q1608", se3q1608)
                    .put("se3q1609", se3q1609)
                    .put("se3q1610", se3q1610)
                    .put("se3q16962", se3q16962)
                    .put("se3q16962x", se3q16962x)
                    .put("se3q1611", se3q1611)
                    .put("se3q1612", se3q1612)
                    .put("se3q1613", se3q1613)
                    .put("se3q16963", se3q16963)
                    .put("se3q16963x", se3q16963x)
                    .put("se3q17", se3q17)
                    .put("se3q1801", se3q1801)
                    .put("se3q1802", se3q1802)
                    .put("se3q19", se3q19)
                    .put("se3q20", se3q20)
                    .put("se3q2096x", se3q2096x)
                    .put("se3q2101", se3q2101)
                    .put("se3q2102", se3q2102)
                    .put("se3q2103", se3q2103)
                    .put("se3q2104", se3q2104)
                    .put("se3q2105", se3q2105)
                    .put("se3q21961", se3q21961)
                    .put("se3q21961x", se3q21961x)
                    .put("se3q2106", se3q2106)
                    .put("se3q2107", se3q2107)
                    .put("se3q2108", se3q2108)
                    .put("se3q2109", se3q2109)
                    .put("se3q2110", se3q2110)
                    .put("se3q21962", se3q21962)
                    .put("se3q21962x", se3q21962x)
                    .put("se3q2111", se3q2111)
                    .put("se3q2112", se3q2112)
                    .put("se3q2113", se3q2113)
                    .put("se3q21963", se3q21963)
                    .put("se3q21963x", se3q21963x)
                    .put("se3q22", se3q22)
                    .put("se3q2301", se3q2301)
                    .put("se3q2302", se3q2302)
                    .put("se3q24", se3q24)
                    .put("se3q25", se3q25)
                    .put("se3q26", se3q26);

        } catch (JSONException e) {
            e.printStackTrace();
            return "\"error\":, \"" + e.getMessage() + "\"";

        }
        return json.toString();
    }

    private void s3Hydrate(String string) {

        if (string != null) {

            try {
                JSONObject json = null;
                json = new JSONObject(string);

                this.se3q0 = json.getString("se3q0");
                this.se3q101 = json.getString("se3q101");
                this.se3q102 = json.getString("se3q102");
                this.se3q103 = json.getString("se3q103");
                this.se3q104 = json.getString("se3q104");
                this.se3q105 = json.getString("se3q105");
                this.se3q106 = json.getString("se3q106");
                this.se3q107 = json.getString("se3q107");
                this.se3q108 = json.getString("se3q108");
                this.se3q196 = json.getString("se3q196");
                this.se3q196x = json.getString("se3q196x");
                this.se3q201 = json.getString("se3q201");
                this.se3q202 = json.getString("se3q202");
                this.se3q203 = json.getString("se3q203");
                this.se3q204 = json.getString("se3q204");
                this.se3q205 = json.getString("se3q205");
                this.se3q2961 = json.getString("se3q2961");
                this.se3q206 = json.getString("se3q206");
                this.se3q207 = json.getString("se3q207");
                this.se3q208 = json.getString("se3q208");
                this.se3q2962 = json.getString("se3q2962");
                this.se3q2962x = json.getString("se3q2962x");
                this.se3q2963 = json.getString("se3q2963");
                this.se3q2963x = json.getString("se3q2963x");
                this.se3q3 = json.getString("se3q3");
                this.se3q301x = json.getString("se3q301x");
                this.se3q302x = json.getString("se3q302x");
                this.se3q4 = json.getString("se3q4");
                this.se3q401x = json.getString("se3q401x");
                this.se3q501 = json.getString("se3q501");
                this.se3q502 = json.getString("se3q502");
                this.se3q503 = json.getString("se3q503");
                this.se3q504 = json.getString("se3q504");
                this.se3q505 = json.getString("se3q505");
                this.se3q506 = json.getString("se3q506");
                this.se3q507 = json.getString("se3q507");
                this.se3q508 = json.getString("se3q508");
                this.se3q509 = json.getString("se3q509");
                this.se3q510 = json.getString("se3q510");
                this.se3q511 = json.getString("se3q511");
                this.se3q511ax = json.getString("se3q511ax");
                this.se3q6 = json.getString("se3q6");
                this.se3q7 = json.getString("se3q7");
                this.se3q701x = json.getString("se3q701x");
                this.se3q9 = json.getString("se3q9");
                this.se3q10 = json.getString("se3q10");
                this.se3q1096x = json.getString("se3q1096x");
                this.se3q11 = json.getString("se3q11");
                this.se3q11961x = json.getString("se3q11961x");
                this.se3q11962x = json.getString("se3q11962x");
                this.se3q11963x = json.getString("se3q11963x");
                this.se3q12 = json.getString("se3q12");
                this.se3q1301 = json.getString("se3q1301");
                this.se3q1302 = json.getString("se3q1302");
                this.se3q14 = json.getString("se3q14");
                this.se3q15 = json.getString("se3q15");
                this.se3q1596x = json.getString("se3q1596x");
                this.se3q1601 = json.getString("se3q1601");
                this.se3q1602 = json.getString("se3q1602");
                this.se3q1603 = json.getString("se3q1603");
                this.se3q1604 = json.getString("se3q1604");
                this.se3q1605 = json.getString("se3q1605");
                this.se3q16961 = json.getString("se3q16961");
                this.se3q16961x = json.getString("se3q16961x");
                this.se3q1606 = json.getString("se3q1606");
                this.se3q1607 = json.getString("se3q1607");
                this.se3q1608 = json.getString("se3q1608");
                this.se3q1609 = json.getString("se3q1609");
                this.se3q1610 = json.getString("se3q1610");
                this.se3q16962 = json.getString("se3q16962");
                this.se3q16962x = json.getString("se3q16962x");
                this.se3q1611 = json.getString("se3q1611");
                this.se3q1612 = json.getString("se3q1612");
                this.se3q1613 = json.getString("se3q1613");
                this.se3q16963 = json.getString("se3q16963");
                this.se3q16963x = json.getString("se3q16963x");
                this.se3q17 = json.getString("se3q17");
                this.se3q1801 = json.getString("se3q1801");
                this.se3q1802 = json.getString("se3q1802");
                this.se3q19 = json.getString("se3q19");
                this.se3q20 = json.getString("se3q20");
                this.se3q2096x = json.getString("se3q2096x");
                this.se3q2101 = json.getString("se3q2101");
                this.se3q2102 = json.getString("se3q2102");
                this.se3q2103 = json.getString("se3q2103");
                this.se3q2104 = json.getString("se3q2104");
                this.se3q2105 = json.getString("se3q2105");
                this.se3q21961 = json.getString("se3q21961");
                this.se3q21961x = json.getString("se3q21961x");
                this.se3q2106 = json.getString("se3q2106");
                this.se3q2107 = json.getString("se3q2107");
                this.se3q2108 = json.getString("se3q2108");
                this.se3q2109 = json.getString("se3q2109");
                this.se3q2110 = json.getString("se3q2110");
                this.se3q21962 = json.getString("se3q21962");
                this.se3q21962x = json.getString("se3q21962x");
                this.se3q2111 = json.getString("se3q2111");
                this.se3q2112 = json.getString("se3q2112");
                this.se3q2113 = json.getString("se3q2113");
                this.se3q21963 = json.getString("se3q21963");
                this.se3q21963x = json.getString("se3q21963x");
                this.se3q22 = json.getString("se3q22");
                this.se3q2301 = json.getString("se3q2301");
                this.se3q2302 = json.getString("se3q2302");
                this.se3q24 = json.getString("se3q24");
                this.se3q25 = json.getString("se3q25");
                this.se3q26 = json.getString("se3q26");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public String s4toString() {
        JSONObject json = new JSONObject();
        try {

            json.put("se4q0", se4q0)
                    .put("se4q1", se4q1)
                    .put("se4q2", se4q2)
                    .put("se4q296x", se4q296x)
                    .put("se4q3", se4q3)
                    .put("se4q4", se4q4)
                    .put("se4q5", se4q5)
                    .put("se4q6", se4q6)
                    .put("se4q602x", se4q602x)
                    .put("se4q603x", se4q603x)
                    .put("se4q7", se4q7)
                    .put("se4q801", se4q801)
                    .put("se4q802", se4q802)
                    .put("se4q803", se4q803)
                    .put("se4q896", se4q896)
                    .put("se4q896x", se4q896x)
                    .put("se4q9", se4q9)
                    .put("se4q901x", se4q901x)
                    .put("se4q902x", se4q902x)
                    .put("se4q903x", se4q903x)
                    .put("se4q10", se4q10)
                    .put("se4q1101", se4q1101)
                    .put("se4q1102", se4q1102)
                    .put("se4q1103", se4q1103)
                    .put("se4q1104", se4q1104)
                    .put("se4q1105", se4q1105)
                    .put("se4q1106", se4q1106)
                    .put("se4q1196", se4q1196)
                    .put("se4q1196x", se4q1196x)
                    .put("se4q12", se4q12)
                    .put("se4q1301", se4q1301)
                    .put("se4q1302", se4q1302)
                    .put("se4q1303", se4q1303)
                    .put("se4q1396", se4q1396)
                    .put("se4q1396x", se4q1396x)
                    .put("se4q14", se4q14)
                    .put("se4q1401x", se4q1401x)
                    .put("se4q1402x", se4q1402x)
                    .put("se4q1403x", se4q1403x)
                    .put("se4q15", se4q15)
                    .put("se4q1601", se4q1601)
                    .put("se4q1602", se4q1602)
                    .put("se4q1603", se4q1603)
                    .put("se4q1605", se4q1605)
                    .put("se4q1606", se4q1606)
                    .put("se4q1696", se4q1696)
                    .put("se4q1696x", se4q1696x)
                    .put("se4q17", se4q17)
                    .put("se4q1801", se4q1801)
                    .put("se4q1802", se4q1802)
                    .put("se4q1803", se4q1803)
                    .put("se4q1804", se4q1804)
                    .put("se4q1805", se4q1805)
                    .put("se4q1806", se4q1806)
                    .put("se4q1899", se4q1899)
                    .put("se4q1896", se4q1896)
                    .put("se4q1896x", se4q1896x);

        } catch (JSONException e) {
            e.printStackTrace();
            return "\"error\":, \"" + e.getMessage() + "\"";

        }
        return json.toString();
    }

    private void s4Hydrate(String string) {

        if (string != null) {

            try {
                JSONObject json = null;
                json = new JSONObject(string);

                this.se4q0 = json.getString("se4q0");
                this.se4q1 = json.getString("se4q1");
                this.se4q2 = json.getString("se4q2");
                this.se4q296x = json.getString("se4q296x");
                this.se4q3 = json.getString("se4q3");
                this.se4q4 = json.getString("se4q4");
                this.se4q5 = json.getString("se4q5");
                this.se4q6 = json.getString("se4q6");
                this.se4q602x = json.getString("se4q602x");
                this.se4q603x = json.getString("se4q603x");
                this.se4q7 = json.getString("se4q7");
                this.se4q801 = json.getString("se4q801");
                this.se4q802 = json.getString("se4q802");
                this.se4q803 = json.getString("se4q803");
                this.se4q896 = json.getString("se4q896");
                this.se4q896x = json.getString("se4q896x");
                this.se4q9 = json.getString("se4q9");
                this.se4q901x = json.getString("se4q901x");
                this.se4q902x = json.getString("se4q902x");
                this.se4q903x = json.getString("se4q903x");
                this.se4q10 = json.getString("se4q10");
                this.se4q1101 = json.getString("se4q1101");
                this.se4q1102 = json.getString("se4q1102");
                this.se4q1103 = json.getString("se4q1103");
                this.se4q1104 = json.getString("se4q1104");
                this.se4q1105 = json.getString("se4q1105");
                this.se4q1106 = json.getString("se4q1106");
                this.se4q1196 = json.getString("se4q1196");
                this.se4q1196x = json.getString("se4q1196x");
                this.se4q12 = json.getString("se4q12");
                this.se4q1301 = json.getString("se4q1301");
                this.se4q1302 = json.getString("se4q1302");
                this.se4q1303 = json.getString("se4q1303");
                this.se4q1396 = json.getString("se4q1396");
                this.se4q1396x = json.getString("se4q1396x");
                this.se4q14 = json.getString("se4q14");
                this.se4q1401x = json.getString("se4q1401x");
                this.se4q1402x = json.getString("se4q1402x");
                this.se4q1403x = json.getString("se4q1403x");
                this.se4q15 = json.getString("se4q15");
                this.se4q1601 = json.getString("se4q1601");
                this.se4q1602 = json.getString("se4q1602");
                this.se4q1603 = json.getString("se4q1603");
                this.se4q1605 = json.getString("se4q1605");
                this.se4q1606 = json.getString("se4q1606");
                this.se4q1696 = json.getString("se4q1696");
                this.se4q1696x = json.getString("se4q1696x");
                this.se4q17 = json.getString("se4q17");
                this.se4q1801 = json.getString("se4q1801");
                this.se4q1802 = json.getString("se4q1802");
                this.se4q1803 = json.getString("se4q1803");
                this.se4q1804 = json.getString("se4q1804");
                this.se4q1805 = json.getString("se4q1805");
                this.se4q1806 = json.getString("se4q1806");
                this.se4q1899 = json.getString("se4q1899");
                this.se4q1896 = json.getString("se4q1896");
                this.se4q1896x = json.getString("se4q1896x");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
