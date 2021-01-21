package edu.aku.hassannaqvi.blf_screening.models;

import android.database.Cursor;

import androidx.lifecycle.LiveData;

import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.blf_screening.contracts.DiseasesContract;
import edu.aku.hassannaqvi.blf_screening.contracts.EpisodesContract;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsWFContract.FormsWFTable;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class Diseases extends LiveData<Diseases> {

    private final String projectName = "blf";

    // Diseases Section variables
    private String Did = "";
    private String Duid = "";
    private String Duuid = "";
    private String Dsysdate = "";
    private String Dsynced = "";
    private String Dsynced_date = "";
    private String Dqno = "";
    private String Dmrno = "";
    private String Dstudyid = "";
    private String Ddeviceid = "";


    public String getDid() {
        return Did;
    }

    public void setDid(String did) {
        Did = did;
    }

    public String getDuid() {
        return Duid;
    }

    public void setDuid(String duid) {
        Duid = duid;
    }

    public String getDuuid() {
        return Duuid;
    }

    public void setDuuid(String duuid) {
        Duuid = duuid;
    }

    public String getDsysdate() {
        return Dsysdate;
    }

    public void setDsysdate(String dsysdate) {
        Dsysdate = dsysdate;
    }

    public String getDsynced() {
        return Dsynced;
    }

    public void setDsynced(String dsynced) {
        Dsynced = dsynced;
    }

    public String getDsynced_date() {
        return Dsynced_date;
    }

    public void setDsynced_date(String dsynced_date) {
        Dsynced_date = dsynced_date;
    }

    public String getDqno() {
        return Dqno;
    }

    public void setDqno(String dqno) {
        Dqno = dqno;
    }

    public String getDmrno() {
        return Dmrno;
    }

    public void setDmrno(String dmrno) {
        Dmrno = dmrno;
    }

    public String getDstudyid() {
        return Dstudyid;
    }

    public void setDstudyid(String dstudyid) {
        Dstudyid = dstudyid;
    }

    public String getDdeviceid() {
        return Ddeviceid;
    }

    public void setDdeviceid(String ddeviceid) {
        Ddeviceid = ddeviceid;
    }

    //For section selection
    private SectionSelection secSelection;


    public Diseases() {
    }

    public Diseases Sync(JSONObject jsonObject) throws JSONException {

        this.Did       = jsonObject.getString(DiseasesContract.DiseasesTable.ID);
        this.Duid      = jsonObject.getString(DiseasesContract.DiseasesTable.COLUMN_UID);
        this.Duuid     = jsonObject.getString(DiseasesContract.DiseasesTable.COLUMN_UUID);
        this.Dsysdate  = jsonObject.getString(DiseasesContract.DiseasesTable.COLUMN_SYSDATE);
        this.Dqno      = jsonObject.getString(DiseasesContract.DiseasesTable.COLUMN_Q_NO);
        this.Ddeviceid = jsonObject.getString(DiseasesContract.DiseasesTable.COLUMN_DEVICE_ID);
        return this;
    }


    public Diseases Hydrate(Cursor cursor) {

        this.Did = cursor.getString(cursor.getColumnIndex(DiseasesContract.DiseasesTable.ID));
        this.Duid = cursor.getString(cursor.getColumnIndex(DiseasesContract.DiseasesTable.COLUMN_UID));
        this.Duuid = cursor.getString(cursor.getColumnIndex(DiseasesContract.DiseasesTable.COLUMN_UUID));
        this.Dsysdate = cursor.getString(cursor.getColumnIndex(DiseasesContract.DiseasesTable.COLUMN_SYSDATE));
        this.Dqno = cursor.getString(cursor.getColumnIndex(DiseasesContract.DiseasesTable.COLUMN_Q_NO));
        this.Ddeviceid = cursor.getString(cursor.getColumnIndex(DiseasesContract.DiseasesTable.COLUMN_DEVICE_ID));

        return this;
    }


    //TODO: Try this instead of toJSONObject
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Diseases.class);
    }

    public JSONObject toJSONObject() {

        JSONObject json = new JSONObject();

        try {
            json.put(DiseasesContract.DiseasesTable.ID, this.Did == null ? JSONObject.NULL : this.Did);
            json.put(DiseasesContract.DiseasesTable.COLUMN_UID, this.Duid == null ? JSONObject.NULL : this.Duid);
            json.put(DiseasesContract.DiseasesTable.COLUMN_UUID, this.Duuid == null ? JSONObject.NULL : this.Duuid);
            json.put(DiseasesContract.DiseasesTable.COLUMN_SYSDATE, this.Dsysdate == null ? JSONObject.NULL : this.Dsysdate);
            json.put(DiseasesContract.DiseasesTable.COLUMN_Q_NO, this.Dqno == null ? JSONObject.NULL : this.Dqno);
            json.put(DiseasesContract.DiseasesTable.COLUMN_DEVICE_ID, this.Ddeviceid == null ? JSONObject.NULL : this.Ddeviceid);

            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
