package edu.aku.hassannaqvi.blf_screening.models;

import android.database.Cursor;

import androidx.lifecycle.LiveData;

import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.blf_screening.contracts.WFB108Contract;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class MWFB108 extends LiveData<MWFB108> {

    private final String projectName = "blf";

    // WFB108 Variables
    private String id = "";
    private String uid = "";
    private String uuid = "";
    private String sysdate = "";
    private String synced = "";
    private String synced_date = "";
    private String deviceid = "";
    private String wfb108a = "";
    private String wfb108a2 = "";
    private String wfb108a296x = "";
    private String wfb108b = "";
    private String wfb108c = "";
    private String wfb108d = "";
    private String wfb108d5x = "";
    private String wfb108d96x = "";
    private String dayNo = "";
    //For section selection
    private SectionSelection secSelection;

    public MWFB108() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
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

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getWfb108a() {
        return wfb108a;
    }

    public void setWfb108a(String wfb108a) {
        this.wfb108a = wfb108a;
    }

    public String getWfb108a2() {
        return wfb108a2;
    }

    public void setWfb108a2(String wfb108a2) {
        this.wfb108a2 = wfb108a2;
    }

    public String getWfb108a296x() {
        return wfb108a296x;
    }

    public void setWfb108a296x(String wfb108a296x) {
        this.wfb108a296x = wfb108a296x;
    }

    public String getWfb108b() {
        return wfb108b;
    }

    public void setWfb108b(String wfb108b) {
        this.wfb108b = wfb108b;
    }

    public String getWfb108c() {
        return wfb108c;
    }

    public void setWfb108c(String wfb108c) {
        this.wfb108c = wfb108c;
    }

    public String getWfb108d() {
        return wfb108d;
    }

    public void setWfb108d(String wfb108d) {
        this.wfb108d = wfb108d;
    }

    public String getWfb108d5x() {
        return wfb108d5x;
    }

    public void setWfb108d5x(String wfb108d5x) {
        this.wfb108d5x = wfb108d5x;
    }

    public String getWfb108d96x() {
        return wfb108d96x;
    }

    public void setWfb108d96x(String wfb108d96x) {
        this.wfb108d96x = wfb108d96x;
    }

    public String getDayNo() {
        return dayNo;
    }

    public void setDayNo(String dayNo) {
        this.dayNo = dayNo;
    }

    public MWFB108 Sync(JSONObject jsonObject) throws JSONException {

        this.id = jsonObject.getString(WFB108Contract.WFB108Table.ID);
        this.uid = jsonObject.getString(WFB108Contract.WFB108Table.COLUMN_UID);
        this.uuid = jsonObject.getString(WFB108Contract.WFB108Table.COLUMN_UUID);
        this.sysdate = jsonObject.getString(WFB108Contract.WFB108Table.COLUMN_SYSDATE);
        this.synced = jsonObject.getString(WFB108Contract.WFB108Table.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(WFB108Contract.WFB108Table.COLUMN_SYNCED_DATE);
        this.deviceid = jsonObject.getString(WFB108Contract.WFB108Table.COLUMN_DEVICE_ID);
        this.wfb108a = jsonObject.getString(WFB108Contract.WFB108Table.COLUMN_WFB108_A);
        this.wfb108a2 = jsonObject.getString(WFB108Contract.WFB108Table.COLUMN_WFB108_A2);
        this.wfb108a296x = jsonObject.getString(WFB108Contract.WFB108Table.COLUMN_WFB108_A296X);
        this.wfb108b = jsonObject.getString(WFB108Contract.WFB108Table.COLUMN_WFB108_B);
        this.wfb108c = jsonObject.getString(WFB108Contract.WFB108Table.COLUMN_WFB108_C);
        this.wfb108d = jsonObject.getString(WFB108Contract.WFB108Table.COLUMN_WFB108_D);
        this.wfb108d5x = jsonObject.getString(WFB108Contract.WFB108Table.COLUMN_WFB108_D5X);
        this.wfb108d96x = jsonObject.getString(WFB108Contract.WFB108Table.COLUMN_WFB108_D96X);
        this.dayNo = jsonObject.getString(WFB108Contract.WFB108Table.COLUMN_DAY_NO);

        return this;
    }


    public MWFB108 Hydrate(Cursor cursor) {

        this.id = cursor.getString(cursor.getColumnIndex(WFB108Contract.WFB108Table.ID));
        this.uid = cursor.getString(cursor.getColumnIndex(WFB108Contract.WFB108Table.COLUMN_UID));
        this.uuid = cursor.getString(cursor.getColumnIndex(WFB108Contract.WFB108Table.COLUMN_UUID));
        this.sysdate = cursor.getString(cursor.getColumnIndex(WFB108Contract.WFB108Table.COLUMN_SYSDATE));
        this.deviceid = cursor.getString(cursor.getColumnIndex(WFB108Contract.WFB108Table.COLUMN_DEVICE_ID));
        this.wfb108a = cursor.getString(cursor.getColumnIndex(WFB108Contract.WFB108Table.COLUMN_WFB108_A));
        this.wfb108a2 = cursor.getString(cursor.getColumnIndex(WFB108Contract.WFB108Table.COLUMN_WFB108_A2));
        this.wfb108a296x = cursor.getString(cursor.getColumnIndex(WFB108Contract.WFB108Table.COLUMN_WFB108_A296X));
        this.wfb108b = cursor.getString(cursor.getColumnIndex(WFB108Contract.WFB108Table.COLUMN_WFB108_B));
        this.wfb108c = cursor.getString(cursor.getColumnIndex(WFB108Contract.WFB108Table.COLUMN_WFB108_C));
        this.wfb108d = cursor.getString(cursor.getColumnIndex(WFB108Contract.WFB108Table.COLUMN_WFB108_D));
        this.wfb108d5x = cursor.getString(cursor.getColumnIndex(WFB108Contract.WFB108Table.COLUMN_WFB108_D5X));
        this.wfb108d96x = cursor.getString(cursor.getColumnIndex(WFB108Contract.WFB108Table.COLUMN_WFB108_D96X));
        this.dayNo = cursor.getString(cursor.getColumnIndex(WFB108Contract.WFB108Table.COLUMN_DAY_NO));

        return this;
    }


    //TODO: Try this instead of toJSONObject
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, MWFB108.class);
    }

    public JSONObject toJSONObject() {

        JSONObject json = new JSONObject();

        try {

            json.put(WFB108Contract.WFB108Table.ID, this.id == null ? JSONObject.NULL : this.id);
            json.put(WFB108Contract.WFB108Table.COLUMN_UID, this.uid == null ? JSONObject.NULL : this.uid);
            json.put(WFB108Contract.WFB108Table.COLUMN_UUID, this.uuid == null ? JSONObject.NULL : this.uuid);
            json.put(WFB108Contract.WFB108Table.COLUMN_SYSDATE, this.sysdate == null ? JSONObject.NULL : this.sysdate);
            json.put(WFB108Contract.WFB108Table.COLUMN_DEVICE_ID, this.deviceid == null ? JSONObject.NULL : this.deviceid);
            json.put(WFB108Contract.WFB108Table.COLUMN_WFB108_A, this.wfb108a == null ? JSONObject.NULL : this.wfb108a);
            json.put(WFB108Contract.WFB108Table.COLUMN_WFB108_A2, this.wfb108a2 == null ? JSONObject.NULL : this.wfb108a2);
            json.put(WFB108Contract.WFB108Table.COLUMN_WFB108_A296X, this.wfb108a296x == null ? JSONObject.NULL : this.wfb108a296x);
            json.put(WFB108Contract.WFB108Table.COLUMN_WFB108_B, this.wfb108b == null ? JSONObject.NULL : this.wfb108b);
            json.put(WFB108Contract.WFB108Table.COLUMN_WFB108_C, this.wfb108c == null ? JSONObject.NULL : this.wfb108c);
            json.put(WFB108Contract.WFB108Table.COLUMN_WFB108_D, this.wfb108d == null ? JSONObject.NULL : this.wfb108d);
            json.put(WFB108Contract.WFB108Table.COLUMN_WFB108_D5X, this.wfb108d5x == null ? JSONObject.NULL : this.wfb108d5x);
            json.put(WFB108Contract.WFB108Table.COLUMN_WFB108_D96X, this.wfb108d96x == null ? JSONObject.NULL : this.wfb108d96x);
            json.put(WFB108Contract.WFB108Table.COLUMN_DAY_NO, this.dayNo == null ? JSONObject.NULL : this.dayNo);

            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
