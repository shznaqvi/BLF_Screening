package edu.aku.hassannaqvi.blf_screening.models;


import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.blf_screening.contracts.EnrollmentsContract;
import edu.aku.hassannaqvi.blf_screening.contracts.SitesContract;

public class Sites {

    private static final String TAG = "Sites_CONTRACT";

    String siteName;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Sites() {
        // Default Constructor
    }

    public Sites Hydrate(Cursor cursor) {
        this.siteName = cursor.getString(cursor.getColumnIndex(SitesContract.sitesTable.COLUMN_SITENAME));
        return this;
    }


    public JSONObject toJSONObject() {

        JSONObject json = new JSONObject();
        try {
            json.put(SitesContract.sitesTable.COLUMN_SITENAME, this.siteName == null ? JSONObject.NULL : this.siteName);
            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Sites Sync(JSONObject jsonObject) throws JSONException {
        this.siteName = jsonObject.getString(SitesContract.sitesTable.COLUMN_SITENAME);
        return this;
    }
}