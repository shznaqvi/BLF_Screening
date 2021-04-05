package edu.aku.hassannaqvi.blf_screening.models;


import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.blf_screening.contracts.EnrollmentsContract;

public class Enrollments {

    private static final String TAG = "Enrollments_CONTRACT";
    String mrno;

    public String getMrno() {
        return mrno;
    }

    public void setMrno(String mrno) {
        this.mrno = mrno;
    }

    public Enrollments() {
        // Default Constructor
    }

    public Enrollments Hydrate(JSONObject enr) throws JSONException {

        this.mrno = enr.getString("s1q2");
        return this;
    }


    public JSONObject toJSONObject() {

        JSONObject json = new JSONObject();
        try {
            json.put(EnrollmentsContract.enrollmentsTable.COLUMN_S1Q2, this.mrno == null ? JSONObject.NULL : this.mrno);
            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Enrollments Sync(JSONObject jsonObject) throws JSONException {

        this.mrno = jsonObject.getString(EnrollmentsContract.enrollmentsTable.COLUMN_S1Q2);
        return this;
    }
}