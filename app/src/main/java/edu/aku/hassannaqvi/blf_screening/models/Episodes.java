package edu.aku.hassannaqvi.blf_screening.models;

import android.database.Cursor;

import androidx.lifecycle.LiveData;

import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.blf_screening.contracts.DiseasesContract;
import edu.aku.hassannaqvi.blf_screening.contracts.EpisodesContract;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class Episodes extends LiveData<Episodes> {

    private final String projectName = "blf";

    // Episodes Section Variables
    private String Eid = "";
    private String Euid = "";
    private String Euuid = "";
    private String Eduid = "";
    private String Esysdate = "";
    private String Esynced = "";
    private String Esynced_date = "";
    private String Eminutes = "";
    private String Ehours = "";
    private String Edays = "";
    private String Eseconds = "";
    private String Edeviceid = "";


    public String getEid() {
        return Eid;
    }

    public void setEid(String eid) {
        Eid = eid;
    }

    public String getEuid() {
        return Euid;
    }

    public void setEuid(String euid) {
        Euid = euid;
    }

    public String getEuuid() {
        return Euuid;
    }

    public void setEuuid(String euuid) {
        Euuid = euuid;
    }

    public String getEduid() {
        return Eduid;
    }

    public void setEduid(String eduid) {
        Eduid = eduid;
    }

    public String getEsysdate() {
        return Esysdate;
    }

    public void setEsysdate(String esysdate) {
        Esysdate = esysdate;
    }

    public String getEsynced() {
        return Esynced;
    }

    public void setEsynced(String esynced) {
        Esynced = esynced;
    }

    public String getEsynced_date() {
        return Esynced_date;
    }

    public void setEsynced_date(String esynced_date) {
        Esynced_date = esynced_date;
    }

    public String getEminutes() {
        return Eminutes;
    }

    public void setEminutes(String eminutes) {
        Eminutes = eminutes;
    }

    public String getEhours() {
        return Ehours;
    }

    public void setEhours(String ehours) {
        Ehours = ehours;
    }

    public String getEdays() {
        return Edays;
    }

    public void setEdays(String edays) {
        Edays = edays;
    }

    public String getEseconds() {
        return Eseconds;
    }

    public void setEseconds(String eseconds) {
        Eseconds = eseconds;
    }

    public String getEdeviceid() {
        return Edeviceid;
    }

    public void setEdeviceid(String edeviceid) {
        Edeviceid = edeviceid;
    }

    //For section selection
    private SectionSelection secSelection;


    public Episodes() {
    }

    public Episodes Sync(JSONObject jsonObject) throws JSONException {

        this.Eid = jsonObject.getString(EpisodesContract.EpisodesTable.ID);
        this.Euid = jsonObject.getString(EpisodesContract.EpisodesTable.COLUMN_UID);
        this.Euuid = jsonObject.getString(EpisodesContract.EpisodesTable.COLUMN_UUID);
        this.Eduid = jsonObject.getString(EpisodesContract.EpisodesTable.COLUMN_DUID);
        this.Esysdate = jsonObject.getString(EpisodesContract.EpisodesTable.COLUMN_SYSDATE);
        this.Esynced = jsonObject.getString(EpisodesContract.EpisodesTable.COLUMN_SYNCED);
        this.Esynced_date = jsonObject.getString(EpisodesContract.EpisodesTable.COLUMN_SYNCED_DATE);
        this.Eminutes = jsonObject.getString(EpisodesContract.EpisodesTable.COLUMN_MINUTES);
        this.Ehours = jsonObject.getString(EpisodesContract.EpisodesTable.COLUMN_HOURS);
        this.Edays = jsonObject.getString(EpisodesContract.EpisodesTable.COLUMN_DAYS);
        this.Eseconds = jsonObject.getString(EpisodesContract.EpisodesTable.COLUMN_SECONDS);
        this.Edeviceid = jsonObject.getString(EpisodesContract.EpisodesTable.COLUMN_DEVICE_ID);

        return this;
    }


    public Episodes Hydrate(Cursor cursor) {

        this.Eid = cursor.getString(cursor.getColumnIndex(EpisodesContract.EpisodesTable.ID));
        this.Euid = cursor.getString(cursor.getColumnIndex(EpisodesContract.EpisodesTable.COLUMN_UID));
        this.Euuid = cursor.getString(cursor.getColumnIndex(EpisodesContract.EpisodesTable.COLUMN_UUID));
        this.Eduid = cursor.getString(cursor.getColumnIndex(EpisodesContract.EpisodesTable.COLUMN_DUID));
        this.Esysdate = cursor.getString(cursor.getColumnIndex(EpisodesContract.EpisodesTable.COLUMN_SYSDATE));
        this.Eminutes = cursor.getString(cursor.getColumnIndex(EpisodesContract.EpisodesTable.COLUMN_MINUTES));
        this.Ehours = cursor.getString(cursor.getColumnIndex(EpisodesContract.EpisodesTable.COLUMN_HOURS));
        this.Edays = cursor.getString(cursor.getColumnIndex(EpisodesContract.EpisodesTable.COLUMN_DAYS));
        this.Eseconds = cursor.getString(cursor.getColumnIndex(EpisodesContract.EpisodesTable.COLUMN_SECONDS));
        this.Edeviceid = cursor.getString(cursor.getColumnIndex(EpisodesContract.EpisodesTable.COLUMN_DEVICE_ID));

        return this;
    }


    //TODO: Try this instead of toJSONObject
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Episodes.class);
    }

    public JSONObject toJSONObject() {

        JSONObject json = new JSONObject();

        try {
            json.put(EpisodesContract.EpisodesTable.ID, this.Eid == null ? JSONObject.NULL : this.Eid);
            json.put(EpisodesContract.EpisodesTable.COLUMN_UID, this.Euid == null ? JSONObject.NULL : this.Euid);
            json.put(EpisodesContract.EpisodesTable.COLUMN_UUID, this.Euuid == null ? JSONObject.NULL : this.Euuid);
            json.put(EpisodesContract.EpisodesTable.COLUMN_DUID, this.Eduid == null ? JSONObject.NULL : this.Eduid);
            json.put(EpisodesContract.EpisodesTable.COLUMN_SYSDATE, this.Esysdate == null ? JSONObject.NULL : this.Esysdate);
            json.put(EpisodesContract.EpisodesTable.COLUMN_MINUTES, this.Eminutes == null ? JSONObject.NULL : this.Eminutes);
            json.put(EpisodesContract.EpisodesTable.COLUMN_HOURS, this.Ehours == null ? JSONObject.NULL : this.Ehours);
            json.put(EpisodesContract.EpisodesTable.COLUMN_DAYS, this.Edays == null ? JSONObject.NULL : this.Edays);
            json.put(EpisodesContract.EpisodesTable.COLUMN_SECONDS, this.Eseconds == null ? JSONObject.NULL : this.Eseconds);
            json.put(EpisodesContract.EpisodesTable.COLUMN_DEVICE_ID, this.Edeviceid == null ? JSONObject.NULL : this.Edeviceid);

            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
