package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsWFContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionWfa01Binding;
import edu.aku.hassannaqvi.blf_screening.models.FormsWF;
import edu.aku.hassannaqvi.blf_screening.ui.other.EndingActivity;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;
import edu.aku.hassannaqvi.blf_screening.utils.DateUtils;
import edu.aku.hassannaqvi.blf_screening.workers.FetchFollowupWorker;

import static edu.aku.hassannaqvi.blf_screening.core.MainApp.formsWF;

public class SectionWFA01Activity extends AppCompatActivity {

    public static int followupNo;
    private final String TAG = "SectionWFA01Activity";
    ActivitySectionWfa01Binding bi;
    Intent oF = null;
    DatabaseHelper db;
    String delivery_date;
    String fupdate;
    int col_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_wfa01);
        bi.setCallback(this);
        setupSkips();
    }

    private void setupSkips() {

        bi.wfa107.setOnCheckedChangeListener((group, checkedId) -> Clear.clearAllFields(bi.fldGrpCVwfa108));
        bi.wfa108.setOnCheckedChangeListener((group, checkedId) -> Clear.clearAllFields(bi.llGrpseca01));
        //followupNo = Integer.parseInt(bi.wfa105.getText().toString());

        bi.wfa101.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(bi.wfa101.getText()))
                    return;
                Clear.clearAllFields(bi.llsectionwfa01);
                bi.llsectionwfa01.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        bi.wfa10401.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    Toast.makeText(getApplicationContext(), "" + charSequence.toString().trim(), Toast.LENGTH_LONG).show();

                    String str = charSequence.toString().trim();
                    String strDate = str.replace("-", "/");
                    bi.wfa11001.setMaxDate(strDate);
                    fupdate = strDate;
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    /*    public Class<?> getIntentClass() {
        switch (followupNo) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return SectionWFB01Activity.class;
            case 9:
            case 10:
            case 11:
            case 12:
            default:
                return SectionWFA01Activity.class;
        }
    }*/

    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            finish();
            if (bi.wfa10702.isChecked()) {

                Toast.makeText(this, "A1: " + col_id, Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", false).putExtra("form", "FP").putExtra("col_id", col_id));
            } else {
                if (bi.wfa10802.isChecked()) {
                    startActivity(new Intent(this, EndingActivity.class).putExtra("complete", false).putExtra("form", "FP").putExtra("col_id", col_id));
                } else {
                    startActivity(new Intent(this, SectionWFA02Activity.class).putExtra("week", bi.wfa105.getText().toString()).putExtra("delivery_date", delivery_date).putExtra("fupdate", fupdate).putExtra("col_id", col_id));
                }
            }
        }
    }

    public void BtnEnd() {
        oF = new Intent(this, MainActivity.class);
        startActivity(oF);
    }

    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addFormWF(formsWF);
        formsWF.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            formsWF.set_UID(formsWF.getDeviceID() + formsWF.get_ID());
            db.updatesFormsWFColumn(FormsWFContract.FormsWFTable.COLUMN_UID, formsWF.get_UID());
            db.updatesFormsWFColumn(FormsWFContract.FormsWFTable.COLUMN_SWFA01, formsWF.sWFA01toString());
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() {

        formsWF = new FormsWF();
        formsWF.setSysdate(new SimpleDateFormat("dd-MM-yy HH:mm", Locale.getDefault()).format(new Date().getTime()));
        formsWF.setDeviceID(MainApp.appInfo.getDeviceID());
        formsWF.setDevicetagID(MainApp.appInfo.getTagName());
        formsWF.setAppversion(MainApp.appInfo.getAppVersion());
        formsWF.setUsername(MainApp.userName);

        MainApp.setGPS(this);

        String[] swf1 = bi.wfa10401.getText().toString().split("-");
        String wfa10401 = swf1[0];
        String wfa10402 = swf1[1];
        String wfa10403 = swf1[2];
        MainApp.formsWF.setWfa10401(wfa10401);
        MainApp.formsWF.setWfa10402(wfa10402);
        MainApp.formsWF.setWfa10403(wfa10403);

        formsWF.setWfa101(bi.wfa101.getText().toString().trim().isEmpty() ? "-1" : bi.wfa101.getText().toString());

        formsWF.setWfa102(bi.wfa102.getText().toString().trim().isEmpty() ? "-1" : bi.wfa102.getText().toString());

        formsWF.setWfa103(bi.wfa103.getText().toString().trim().isEmpty() ? "-1" : bi.wfa103.getText().toString());

        swf1 = bi.wfa10404.getText().toString().split(":");
        String wfa10404 = swf1[0];
        String wfa10405 = swf1[1];
        MainApp.formsWF.setWfa10404(wfa10404);
        MainApp.formsWF.setWfa10405(wfa10405);

        formsWF.setWfa105(bi.wfa105.getText().toString());

        formsWF.setWfa106(bi.wfa10601.isChecked() ? "1"
                : bi.wfa10602.isChecked() ? "2"
                : bi.wfa10696.isChecked() ? "96"
                : "-1");
        formsWF.setWfa10696x(bi.wfa10696x.getText().toString().trim().isEmpty() ? "-1" : bi.wfa10696x.getText().toString());

        formsWF.setWfa107(bi.wfa10701.isChecked() ? "1"
                : bi.wfa10702.isChecked() ? "2"
                : "-1");

        formsWF.setWfa108(bi.wfa10801.isChecked() ? "1"
                : bi.wfa10802.isChecked() ? "2"
                : "-1");

        formsWF.setWfa109(bi.wfa10901.isChecked() ? "1"
                : bi.wfa10902.isChecked() ? "2"
                : bi.wfa10903.isChecked() ? "3"
                : bi.wfa10904.isChecked() ? "4"
                : bi.wfa10905.isChecked() ? "5"
                : bi.wfa10906.isChecked() ? "6"
                : bi.wfa10907.isChecked() ? "7"
                : bi.wfa10908.isChecked() ? "8"
                : bi.wfa10998.isChecked() ? "98"
                : bi.wfa10996.isChecked() ? "96"
                : "-1");
        formsWF.setWfa10996x(bi.wfa10996x.getText().toString().trim().isEmpty() ? "-1" : bi.wfa10996x.getText().toString());

        String wfa11001;
        String wfa11002;
        String wfa11003;
        if (bi.wfa11001.getText().toString().trim().isEmpty()) {
            wfa11001 = "-1";
            wfa11002 = "-1";
            wfa11003 = "-1";
        } else {
            String[] swf10 = bi.wfa11001.getText().toString().split("-");
            wfa11001 = swf10[0];
            wfa11002 = swf10[1];
            wfa11003 = swf10[2];
        }
        MainApp.formsWF.setWfa11001(wfa11001);
        MainApp.formsWF.setWfa11002(wfa11002);
        MainApp.formsWF.setWfa11003(wfa11003);

        String wfa11004;
        String wfa11005;
        if (bi.wfa11004.getText().toString().trim().isEmpty()) {
            wfa11004 = "-1";
            wfa11005 = "-1";
        } else {
            String[] swf10 = bi.wfa11004.getText().toString().split(":");
            wfa11004 = swf10[0];
            wfa11005 = swf10[1];
        }
        MainApp.formsWF.setWfa11004(wfa11004);
        MainApp.formsWF.setWfa11005(wfa11005);

        formsWF.setWfa111(bi.wfa11101.isChecked() ? "1"
                : bi.wfa11102.isChecked() ? "2"
                : bi.wfa11103.isChecked() ? "3"
                : "-1");

    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }

    private void setGPS(Activity activity) {
        SharedPreferences GPSPref = activity.getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);

        try {
            String lat = GPSPref.getString("Latitude", "0");
            String lang = GPSPref.getString("Longitude", "0");
            String acc = GPSPref.getString("Accuracy", "0");
            String dt = GPSPref.getString("Time", "0");

            if (lat.equals("0") && lang.equals("0")) {
                Toast.makeText(activity, "Could not obtained GPS points", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "GPS set", Toast.LENGTH_SHORT).show();
            }

            String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

            MainApp.formsEN.setGpsLat(GPSPref.getString("Latitude", "0"));
            MainApp.formsEN.setGpsLng(GPSPref.getString("Longitude", "0"));
            MainApp.formsEN.setGpsAcc(GPSPref.getString("Accuracy", "0"));
//            MainApp.fc.setGpsTime(GPSPref.getString(date, "0")); // Timestamp is converted to date above
            MainApp.formsEN.setGpsDT(date); // Timestamp is converted to date above

        } catch (Exception e) {
            Log.e("GPS", "setGPS: " + e.getMessage());
        }
    }

    /*public void FetchFollowups(View view) {

     *//*MainApp.sf2 = bi.sf2.getText().toString();
        MainApp.scrdt = bi.sf101.getText().toString() + " " + bi.sf102.getText().toString();*//*

        if (formValidation()) {

            bi.checkMR.setVisibility(View.GONE);
            bi.pbarMR.setVisibility(View.VISIBLE);

            bi.wmError.setVisibility(View.GONE);
            bi.wmError.setText(null);

            *//*bi.sf6a01.setMinDate(DateUtils.calculatedDate(bi.sf101.getText().toString().replace("-", "/"), "dd/MM/yyyy", -3, "d"));

            bi.sf6a01.setMaxDate(bi.sf101.getText().toString().replace("-", "/"));
*//*
            // Sending data to Worker class

            *//*final OneTimeWorkRequest workRequest1 = new OneTimeWorkRequest.Builder({"table":"fetchMR","filter":"curfupdt is not null"}.class)
                .setInputData(data)
                .build();*//*

            JSONObject json = new JSONObject();
            try {
                json.put("table", "fetchMR");
                //json.put("select", "sl2, sl4, sl5, sf6a");
                json.put("filter", "sf5 = '" + bi.wfa101.getText().toString() + "'");
                //json.put("scrdt", MainApp.scrdt);
            } catch (JSONException e1) {
                e1.printStackTrace();
                Log.d(TAG, "doWork: " + e1.getMessage());
            }

            Data data = new Data.Builder()
                    .putString("json", json.toString())
                    .build();

            Toast.makeText(this, "Data = "+data, Toast.LENGTH_LONG).show();

            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();


            final OneTimeWorkRequest workRequest1 = new OneTimeWorkRequest.Builder(FetchFollowupWorker.class).setInputData(data).setConstraints(constraints).build();
            WorkManager.getInstance().enqueue(workRequest1);

            WorkManager.getInstance().getWorkInfoByIdLiveData(workRequest1.getId())
                    .observe(this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {


                            if (workInfo.getState() != null &&
                                    workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                bi.wmError.setVisibility(View.GONE);
                                bi.pbarMR.setVisibility(View.GONE);
                                bi.checkMR.setVisibility(View.VISIBLE);
                                //Displaying the status into TextView
                                //mTextView1.append("\n" + workInfo.getState().name());
                                String message = workInfo.getOutputData().getString("data");
                                DatabaseHelper db = new DatabaseHelper(SectionWFA01Activity.this); // Database Helper
                                StringBuilder sSyncedError = new StringBuilder();
                                JSONObject jsonObject;
                                try {

                                    JSONArray json = new JSONArray(message);
                                    for (int i = 0; i < json.length(); i++) {
                                        jsonObject = new JSONObject(json.getString(i));

                                        if (!jsonObject.isNull("curfupweek")) {
                                            if (!jsonObject.isNull("curfupdt")) {
                                                Toast.makeText(SectionWFA01Activity.this, "Child followup found.", Toast.LENGTH_SHORT).show();

                                                bi.wfa102.setText(jsonObject.getString("sf20"));
                                                bi.wfa103.setText(jsonObject.getString("s1q3"));
                                                bi.wfa105.setText(jsonObject.getString("curfupweek"));

                                                bi.llsectionwfa01.setVisibility(View.VISIBLE);

                                                // CONTINUE VISIBLE
                                                bi.btnContinue.setVisibility(View.VISIBLE);
                                                bi.btnEnd.setVisibility(View.GONE);
                                                // Clear.clearAllFields(bi.llsectionwfa01);
                                            } else {
                                                Toast.makeText(SectionWFA01Activity.this, jsonObject.getString("curfupweek"), Toast.LENGTH_SHORT).show();
                                                bi.llsectionwfa01.setVisibility(View.GONE);

                                                // CONTINUE VISIBLE
                                                bi.btnContinue.setVisibility(View.GONE);
                                                bi.btnEnd.setVisibility(View.VISIBLE);
                                                Clear.clearAllFields(bi.llsectionwfa01);
                                                bi.wmError.setText(jsonObject.getString("curfupweek"));
                                                bi.wmError.setVisibility(View.VISIBLE);

                                            }
                                        } else {

                                            Toast.makeText(SectionWFA01Activity.this, "Child follow-up not found.", Toast.LENGTH_SHORT).show();
                                            bi.llsectionwfa01.setVisibility(View.GONE);
                                            bi.wmError.setText("Child follow-up not found.");
                                            bi.wmError.setVisibility(View.VISIBLE);
                                            // CONTINUE VISIBLE
                                            bi.btnContinue.setVisibility(View.GONE);
                                            bi.btnEnd.setVisibility(View.VISIBLE);
                                            Clear.clearAllFields(bi.llsectionwfa01);

                                        }

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                //bi.sl2.setText(message);
                            }
                            if (workInfo.getState() != null &&
                                    workInfo.getState() == WorkInfo.State.FAILED) {
                                bi.pbarMR.setVisibility(View.GONE);
                                bi.checkMR.setVisibility(View.VISIBLE);
                                String message = workInfo.getOutputData().getString("error");
                                bi.wmError.setText(message);
                                bi.wmError.setVisibility(View.VISIBLE);

                            }
                        }
                    });
        }
    }*/


    public void FetchFollowups(View view) {

        if (formValidation()) {

            bi.checkMR.setVisibility(View.GONE);
            bi.pbarMR.setVisibility(View.VISIBLE);
            bi.wmError.setVisibility(View.GONE);
            bi.wmError.setText(null);

            String mrno = bi.wfa101.getText().toString();
            db = MainApp.appInfo.getDbHelper();
            Cursor followups = db.getFollowups(mrno);

            //oast.makeText(this, "" + mrno, Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "" + followups.getCount(), Toast.LENGTH_SHORT).show();

            if (followups.getCount() > 0) {

                //Toast.makeText(this, "" + followups.getString(followups.getColumnIndex("s1q501")), Toast.LENGTH_SHORT).show();

                followups.moveToFirst();

                bi.wmError.setVisibility(View.GONE);
                bi.pbarMR.setVisibility(View.GONE);
                bi.checkMR.setVisibility(View.VISIBLE);

                col_id = Integer.parseInt(followups.getString(followups.getColumnIndex("id")));
                if (!followups.getString(followups.getColumnIndex("curfupweek")).equals("") && followups.getString(followups.getColumnIndex("curfupweek")) != null) {

                    if (!followups.getString(followups.getColumnIndex("curfupdt")).equals("") && followups.getString(followups.getColumnIndex("curfupdt")) != null) {

                        String str = followups.getString(followups.getColumnIndex("sf6a"));
                        delivery_date = str.replace("-", "/");

                        bi.wfa10401.setMinDate(delivery_date);

                        try {
                            bi.wfa10401.setMinDate(DateUtils.getNextDate(delivery_date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        bi.wfa11001.setMinDate(delivery_date);

                        Toast.makeText(SectionWFA01Activity.this, "Child followup found.", Toast.LENGTH_SHORT).show();

                        bi.wfa102.setText(followups.getString(followups.getColumnIndex("sf20")));
                        bi.wfa103.setText(followups.getString(followups.getColumnIndex("s1q3")));
                        bi.wfa105.setText(followups.getString(followups.getColumnIndex("curfupweek")));
                        bi.llsectionwfa01.setVisibility(View.VISIBLE);
                        // CONTINUE VISIBLE
                        bi.btnContinue.setVisibility(View.VISIBLE);
                        bi.btnEnd.setVisibility(View.GONE);
                        // Clear.clearAllFields(bi.llsectionwfa01);
                    } else {
                        Toast.makeText(SectionWFA01Activity.this, followups.getString(followups.getColumnIndex("curfupweek")), Toast.LENGTH_SHORT).show();
                        bi.llsectionwfa01.setVisibility(View.GONE);

                        // CONTINUE VISIBLE
                        bi.btnContinue.setVisibility(View.GONE);
                        bi.btnEnd.setVisibility(View.VISIBLE);
                        Clear.clearAllFields(bi.llsectionwfa01);
                        bi.wmError.setText(followups.getString(followups.getColumnIndex("curfupweek")));
                        bi.wmError.setVisibility(View.VISIBLE);

                    }

                } else {

                    Toast.makeText(SectionWFA01Activity.this, "Child follow-up not found.", Toast.LENGTH_SHORT).show();
                    bi.llsectionwfa01.setVisibility(View.GONE);
                    bi.wmError.setText("Child follow-up not found.");
                    bi.wmError.setVisibility(View.VISIBLE);
                    // CONTINUE VISIBLE
                    bi.btnContinue.setVisibility(View.GONE);
                    bi.btnEnd.setVisibility(View.VISIBLE);
                    Clear.clearAllFields(bi.llsectionwfa01);
                }

            } else {

                bi.pbarMR.setVisibility(View.GONE);
                bi.checkMR.setVisibility(View.VISIBLE);
                //String message = workInfo.getOutputData().getString("error");
                bi.wmError.setText("MR No not found");
                bi.wmError.setVisibility(View.VISIBLE);

            }
        }
    }

}