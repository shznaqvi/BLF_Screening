package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsSFContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionSfBinding;
import edu.aku.hassannaqvi.blf_screening.models.FormsSF;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;
import edu.aku.hassannaqvi.blf_screening.workers.DataUpWorkerSF;
import edu.aku.hassannaqvi.blf_screening.workers.FetchMRWorker;

import static edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt.contextBackActivity;

public class SectionSFActivity extends AppCompatActivity {
    ActivitySectionSfBinding bi;
    Intent oF = null;
    private boolean EligibilityFlag;
    private String mmrno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_sf);
        bi.setCallback(this);
        setupSkip();
    }

    public void checkEligibility() {
        Toast.makeText(this, "Checking Eligibility", Toast.LENGTH_SHORT).show();
        if (bi.llsectionsf01.getVisibility() == View.VISIBLE

                && !bi.sf6.getText().toString().equals("")
                && !bi.sf701.getText().toString().equals("")
                && !bi.sf8.getText().toString().equals("")
                && !bi.sf10.getText().toString().equals("")
        ) {

            if ((Integer.parseInt(bi.sf6.getText().toString()) > 18 && Integer.parseInt(bi.sf6.getText().toString()) < 45)
                    && Integer.parseInt(bi.sf701.getText().toString()) >= 37
                    && Integer.parseInt(bi.sf8.getText().toString()) >= 7
                    && bi.sf901.isChecked()
                    && Integer.parseInt(bi.sf10.getText().toString()) >= 1200
                    && bi.sf1102.isChecked()
                    && bi.sf1401.isChecked()
                    && bi.sf1602.isChecked()
            ) {
                bi.sf1701.setChecked(true);
                bi.sf1702.setChecked(false);
                Toast.makeText(this, "Eligible", Toast.LENGTH_SHORT).show();
            } else {
                bi.sf1701.setChecked(false);
                bi.sf1702.setChecked(true);
                Toast.makeText(this, "Not Eligible", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void setupSkip() {

        bi.sf6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(bi.sf6.getText())) {
                    checkEligibility();
                }
                /*if ((Integer.parseInt(bi.sf6.getText().toString()) > 18 && Integer.parseInt(bi.sf6.getText().toString()) < 45)) {
                    Eligibility(true);
                    EligibilityFlag = true;
                } else {
                    Eligibility(false);
                    EligibilityFlag = false;
                }*/
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        bi.sf701.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(bi.sf701.getText())) {
                    checkEligibility();
                }
               /* if (Integer.parseInt(bi.sf701.getText().toString()) < 37) {
                    Eligibility(true);
                    EligibilityFlag = true;
                } else {
                    Eligibility(false);
                    EligibilityFlag = false;
                }*/
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        bi.sf8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(bi.sf8.getText())) {
                    checkEligibility();
                }
                /*if (Integer.parseInt(bi.sf8.getText().toString()) >= 7) {
                    Eligibility(true);
                    EligibilityFlag = true;
                } else {
                    Eligibility(false);
                    EligibilityFlag = false;
                }*/
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        bi.sf10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(bi.sf10.getText())) {
                    checkEligibility();
                }
                /*if (Integer.parseInt(bi.sf10.getText().toString()) > 1200) {
                    Eligibility(true);
                    EligibilityFlag = true;
                } else {
                    Eligibility(false);
                    EligibilityFlag = false;
                }*/
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        bi.sf9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                checkEligibility();
               /* if(bi.sf901.isChecked()) {
                    SectionSFActivity.this.Eligibility(EligibilityFlag && bi.sf901.isChecked());
                } else {
                    EligibilityFlag = false;
                    SectionSFActivity.this.Eligibility(EligibilityFlag && bi.sf901.isChecked());

                }*/
            }
        });


        bi.sf11.setOnCheckedChangeListener((radioGroup, i) -> {
            if (bi.sf1102.isChecked()) {
                SectionSFActivity.this.Eligibility(EligibilityFlag && bi.sf1102.isChecked());
                Clear.clearAllFields(bi.fldGrpCVsf12);
            } else {
                EligibilityFlag = false;
                SectionSFActivity.this.Eligibility(EligibilityFlag && bi.sf1102.isChecked());

            }
        });


        bi.sf1301.setOnCheckedChangeListener((radioGroup, i) -> {
            Clear.clearAllFields(bi.fldGrpsf13);
            bi.fldGrpsf13.setVisibility(View.VISIBLE);
            if (bi.sf130101.isChecked()) {
                bi.fldGrpsf13.setVisibility(View.GONE);
            }
        });


        bi.sf14.setOnCheckedChangeListener(((radioGroup, i) -> {
            checkEligibility();
            Clear.clearAllFields(bi.fldGrpCVsf18);
           /* if(bi.sf1401.isChecked()) {
                SectionSFActivity.this.Eligibility(EligibilityFlag && bi.sf1401.isChecked());
                Clear.clearAllFields(bi.llsf1501);
            } else {
                EligibilityFlag = false;
                SectionSFActivity.this.Eligibility(EligibilityFlag && bi.sf1401.isChecked());
            }*/
        }));


        bi.sf16.setOnCheckedChangeListener((radioGroup, i) -> {
            checkEligibility();
            /*if (EligibilityFlag && bi.sf1602.isChecked()) {
                SectionSFActivity.this.Eligibility(EligibilityFlag && bi.sf1602.isChecked());
            } else {
                Eligibility();
                SectionSFActivity.this.Eligibility(EligibilityFlag && bi.sf1602.isChecked());
            }*/
        });


        bi.sf2.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(this.getClass().getSimpleName(), "onTextChanged: S " + start + " A " + after + " C " + count);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(this.getClass().getSimpleName(), "onTextChanged: S " + start + " B " + before + " C " + count);
                // if (TextUtils.isEmpty(bi.sf2.getText())) return;
                //if (bi.sf2.getText().toString().length() != 10) {
                if (bi.llsectionsf01.getVisibility() == View.VISIBLE) {
                    bi.llsectionsf01.setVisibility(View.GONE);
                    bi.btnContinue.setVisibility(View.GONE);
                    Clear.clearAllFields(bi.llsectionsf01);
                }
                //}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void Eligibility(boolean sf17Flag) {
        bi.sf1701.setChecked(sf17Flag);
        bi.sf1702.setChecked(!sf17Flag);
    }

    public void BtnContinue() {
        bi.pBar3.setVisibility(View.GONE);

        if (bi.sf4.getText().toString().equals("99999") && (bi.sf3.getText().toString().contains("Mother") || bi.sf3.getText().toString().contains("Found"))) {
            bi.sf3.setText(null);
            bi.sf3.setEnabled(true);
        }
        if (!formValidation()) return;
        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            bi.pBar3.setVisibility(View.VISIBLE);

            RetrieveSrcID();
            //startActivity(new Intent(this, EndingActivity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean RetrieveSrcID() {
        final OneTimeWorkRequest workRequest1 = new OneTimeWorkRequest.Builder(DataUpWorkerSF.class).build();
        WorkManager.getInstance().enqueue(workRequest1);


        WorkManager.getInstance().getWorkInfoByIdLiveData(workRequest1.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workInfo) {


                        if (workInfo.getState() != null &&
                                workInfo.getState() == WorkInfo.State.SUCCEEDED) {

                            bi.wmError.setVisibility(View.GONE);
                            bi.pBar3.setVisibility(View.GONE);
                            //Displaying the status into TextView
                            //mTextView1.append("\n" + workInfo.getState().name());
                            String message = workInfo.getOutputData().getString("study_id");
                            DatabaseHelper db = new DatabaseHelper(SectionSFActivity.this); // Database Helper
                            StringBuilder sSyncedError = new StringBuilder();
                            JSONObject jsonObject;
                            try {


                                JSONArray json = new JSONArray(message);
                                for (int i = 0; i < json.length(); i++) {
                                    jsonObject = new JSONObject(json.getString(i));


                                    if (!jsonObject.getString("error").equals("1")) {
                                        bi.llsectionsf01.setVisibility(View.GONE);
                                        bi.btnContinue.setVisibility(View.GONE);
                                        Clear.clearAllFields(bi.llsectionsf01);

                                        if (jsonObject.getString("status").equals("1") && jsonObject.getString("error").equals("0")) {

                                            db.updateSyncedFormsSL(jsonObject.getString("id"));  // UPDATE SYNCED
                                            bi.sf20.setText(jsonObject.getString("study_id"));
                                            bi.sf20.setVisibility(View.VISIBLE);

                                            bi.wmError.setText(
                                                    "Screening Form saved for MR No: " + bi.sf2.getText().toString()
                                                            + "\r\n Study ID: " + jsonObject.getString("study_id")
                                            );
                                            bi.wmError.setTextColor(getResources().getColor(R.color.green));
                                            bi.wmError.setVisibility(View.VISIBLE);
                                            Toast.makeText(SectionSFActivity.this, "Screen Form saved for MR No: " + bi.sf2.getText().toString(), Toast.LENGTH_LONG).show();
                                            bi.btnEnd.setVisibility(View.VISIBLE);
                                            bi.btnContinue.setVisibility(View.GONE);
                                            //method.invoke(db, jsonObject.getString("id"));

                                            final Handler handler = new Handler(Looper.getMainLooper());
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    //Do something after 100ms
                                                    oF = new Intent(SectionSFActivity.this, SectionSFActivity.class);
                                                    //   startActivity(oF);
                                                }
                                            }, 3500);


                                        } else if (jsonObject.getString("status").equals("3")) {

                                            bi.wmError.setText(
                                                    "Screening Form saved for MR No: " + bi.sf2.getText().toString()
                                                            + "\r\n Child is Ineligible or Consent not signed"
                                            );
                                            bi.wmError.setTextColor(getResources().getColor(R.color.green));
                                            bi.wmError.setVisibility(View.VISIBLE);
                                            Toast.makeText(SectionSFActivity.this, "Screen Form saved for MR No: " + bi.sf2.getText().toString(), Toast.LENGTH_LONG).show();
                                            bi.btnEnd.setVisibility(View.VISIBLE);
                                            bi.btnContinue.setVisibility(View.GONE);


                                            db.updateSyncedFormsSF(jsonObject.getString("id")); // UPDATE DUPLICATES
                                            //   method.invoke(db, jsonObject.getString("id"));

                                            // sDuplicate++;
                                        } else {
                                            sSyncedError.append("\nError: ").append(jsonObject.getString("message"));

                                        }
                                    } else {
                                        bi.wmError.setText(jsonObject.getString("message"));
                                        bi.wmError.setVisibility(View.VISIBLE);
                                        bi.wmError.setTextColor(getResources().getColor(R.color.red));
                                        //bi.sf2.setError("MR No. already exists");
                                    }
                                }
                            } catch (JSONException e) {
                                bi.wmError.setText("JSON Error: " + message);
                                bi.wmError.setVisibility(View.VISIBLE);
                                Log.d("JSON Error", "onChanged: " + message);
                                e.printStackTrace();
                            }
                            //bi.sl2.setText(message);
                        }
                        //mTextView1.append("\n" + workInfo.getState().name());
                        if (workInfo.getState() != null &&
                                workInfo.getState() == WorkInfo.State.FAILED) {
                            bi.pBar3.setVisibility(View.GONE);
                            String message = workInfo.getOutputData().getString("error");
                            bi.wmError.setText(message);
                            bi.wmError.setVisibility(View.VISIBLE);

                        }
                    }
                });
        return false;
    }

    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addFormSF(MainApp.formsSF);
        MainApp.formsSF.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            MainApp.formsSF.set_UID(MainApp.formsSF.getDeviceID() + MainApp.formsSF.get_ID());
            db.updatesFormsSFColumn(FormsSFContract.FormsSFTable.COLUMN_UID, MainApp.formsSF.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        MainApp.formsSF = new FormsSF();
        MainApp.formsSF.setSysdate(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));
        MainApp.formsSF.setDeviceID(MainApp.appInfo.getDeviceID());
        MainApp.formsSF.setDevicetagID(MainApp.appInfo.getTagName());
        MainApp.formsSF.setAppversion(MainApp.appInfo.getAppVersion());

        String[] sf1 = bi.sf101.getText().toString().split("-");

        String sf101 = sf1[0];
        String sf102 = sf1[1];
        String sf103 = sf1[2];

        MainApp.formsSF.setSf101(sf101);
        MainApp.formsSF.setSf102(sf102);
        MainApp.formsSF.setSf103(sf103);

        sf1 = bi.sf102.getText().toString().split(":");
        String sf104 = sf1[0];
        String sf105 = sf1[1];
        MainApp.formsSF.setSf104(sf104);
        MainApp.formsSF.setSf105(sf105);

        MainApp.formsSF.setSf2(bi.sf2.getText().toString());
        MainApp.formsSF.setSf3(bi.sf3.getText().toString());
        MainApp.formsSF.setSf4(bi.sf4.getText().toString());
        MainApp.formsSF.setSf5(bi.sf5.getText().toString());
        MainApp.formsSF.setSf6(bi.sf6.getText().toString());
        MainApp.formsSF.setSf6a01(bi.sf6a01.getText().toString());
        MainApp.formsSF.setSf6a02(bi.sf6a02.getText().toString());

        MainApp.formsSF.setSf701(bi.sf701.getText().toString());
        MainApp.formsSF.setSf702(bi.sf702.getText().toString());

        MainApp.formsSF.setSf8(bi.sf8.getText().toString());

        MainApp.formsSF.setSf9(bi.sf901.isChecked() ? "1"
                : bi.sf902.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf10(bi.sf10.getText().toString());

        MainApp.formsSF.setSf11(bi.sf1101.isChecked() ? "1"
                : bi.sf1102.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf12(bi.sf1201.isChecked() ? "1"
                : bi.sf1202.isChecked() ? "2"
                : bi.sf1203.isChecked() ? "3"
                : bi.sf1204.isChecked() ? "4"
                : bi.sf1296.isChecked() ? "96"
                : "-1");
        MainApp.formsSF.setSf1296x(bi.sf1296x.getText().toString());

        MainApp.formsSF.setSf1301(bi.sf130101.isChecked() ? "1"
                : bi.sf130102.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1302(bi.sf130201.isChecked() ? "1"
                : bi.sf130202.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1303(bi.sf130301.isChecked() ? "1"
                : bi.sf130302.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1304(bi.sf130401.isChecked() ? "1"
                : bi.sf130402.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1305(bi.sf130501.isChecked() ? "1"
                : bi.sf130502.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1306(bi.sf130601.isChecked() ? "1"
                : bi.sf130602.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1307(bi.sf130701.isChecked() ? "1"
                : bi.sf130702.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1308(bi.sf130801.isChecked() ? "1"
                : bi.sf130802.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1309(bi.sf130901.isChecked() ? "1"
                : bi.sf130902.isChecked() ? "2"
                : bi.sf1396.isChecked() ? "96"
                : "-1");
        MainApp.formsSF.setSf1396x(bi.sf1396x.getText().toString());

        MainApp.formsSF.setSf14(bi.sf1401.isChecked() ? "1"
                : bi.sf1402.isChecked() ? "2"
                : bi.sf1403.isChecked() ? "96"
                : "-1");
        MainApp.formsSF.setSf1403x(bi.sf1403x.getText().toString());
/*
        MainApp.formsSF.setSf1501(bi.sf150101.isChecked() ? "1"
                : bi.sf150102.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1502(bi.sf150201.isChecked() ? "1"
                : bi.sf150202.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1503(bi.sf150301.isChecked() ? "1"
                : bi.sf150302.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1504(bi.sf150401.isChecked() ? "1"
                : bi.sf150402.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1505(bi.sf150501.isChecked() ? "1"
                : bi.sf150502.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1506(bi.sf150601.isChecked() ? "1"
                : bi.sf150602.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1507(bi.sf150701.isChecked() ? "1"
                : bi.sf150702.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1508(bi.sf150801.isChecked() ? "1"
                : bi.sf150802.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1509(bi.sf150901.isChecked() ? "1"
                : bi.sf150902.isChecked() ? "2"
                : "-1");*/

        MainApp.formsSF.setSf16(bi.sf1601.isChecked() ? "1"
                : bi.sf1602.isChecked() ? "2"
                : bi.sf1698.isChecked() ? "98"
                : "-1");

        MainApp.formsSF.setSf17(bi.sf1701.isChecked() ? "1"
                : bi.sf1702.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf18(bi.sf1801.isChecked() ? "1"
                : bi.sf1802.isChecked() ? "2"
                : "-1");

        //MainApp.formsSF.setSf1901(bi.sf1901.getText().toString());
        //MainApp.formsSF.setSf1902(bi.setSf1902.getText().toString());

        MainApp.formsSF.setSf20(bi.sf20.getText().toString());

       /* JSONObject json = new JSONObject();

        json.put("sf101", bi.sf101.getText().toString());
        json.put("sf102", bi.sf102.getText().toString());
        json.put("sf103", bi.sf103.getText().toString());
        json.put("sf104", bi.sf104.getText().toString());
        json.put("sf105", bi.sf105.getText().toString());

        json.put("sf2", bi.sf2.getText().toString());
        json.put("sf3", bi.sf3.getText().toString());
        json.put("sf4", bi.sf4.getText().toString());
        json.put("sf5", bi.sf5.getText().toString());
        json.put("sf6", bi.sf6.getText().toString());

        json.put("sf701", bi.sf701.getText().toString());
        json.put("sf702", bi.sf702.getText().toString());

        json.put("sf8", bi.sf8.getText().toString());

        json.put("sf9", bi.sf901.isChecked() ? "1"
                : bi.sf902.isChecked() ? "2"
                : "-1");

        json.put("sf10", bi.sf10.getText().toString());

        json.put("sf11", bi.sf1101.isChecked() ? "1"
                : bi.sf1102.isChecked() ? "2"
                : "-1");

        json.put("sf12", bi.sf1201.isChecked() ? "1"
                : bi.sf1202.isChecked() ? "2"
                : bi.sf1203.isChecked() ? "3"
                : bi.sf1204.isChecked() ? "4"
                : bi.sf1296.isChecked() ? "96"
                : "-1");
        json.put("sf1296x", bi.sf1296x.getText().toString());

        json.put("sf1301", bi.sf130101.isChecked() ? "1"
                : bi.sf130102.isChecked() ? "2"
                : "-1");

        json.put("sf1302", bi.sf130201.isChecked() ? "1"
                : bi.sf130202.isChecked() ? "2"
                : "-1");

        json.put("sf1303", bi.sf130301.isChecked() ? "1"
                : bi.sf130302.isChecked() ? "2"
                : "-1");

        json.put("sf1304", bi.sf130401.isChecked() ? "1"
                : bi.sf130402.isChecked() ? "2"
                : "-1");

        json.put("sf1305", bi.sf130501.isChecked() ? "1"
                : bi.sf130502.isChecked() ? "2"
                : "-1");

        json.put("sf1306", bi.sf130601.isChecked() ? "1"
                : bi.sf130602.isChecked() ? "2"
                : "-1");

        json.put("sf1307", bi.sf130701.isChecked() ? "1"
                : bi.sf130702.isChecked() ? "2"
                : "-1");

        json.put("sf1308", bi.sf130801.isChecked() ? "1"
                : bi.sf130802.isChecked() ? "2"
                : "-1");

        json.put("sf1309", bi.sf130901.isChecked() ? "1"
                : bi.sf130902.isChecked() ? "2"
                : bi.sf1396.isChecked() ? "96"
                : "-1");
        json.put("sf1396x", bi.sf1396x.getText().toString());

        json.put("sf14", bi.sf1401.isChecked() ? "1"
                : bi.sf1402.isChecked() ? "2"
                : bi.sf1403.isChecked() ? ""
                : "-1");
        json.put("sf1403x", bi.sf1403x.getText().toString());

        json.put("sf1501", bi.sf150101.isChecked() ? "1"
                : bi.sf150102.isChecked() ? "2"
                : "-1");

        json.put("sf1502", bi.sf150201.isChecked() ? "1"
                : bi.sf150202.isChecked() ? "2"
                : "-1");

        json.put("sf1503", bi.sf150301.isChecked() ? "1"
                : bi.sf150302.isChecked() ? "2"
                : "-1");

        json.put("sf1504", bi.sf150401.isChecked() ? "1"
                : bi.sf150402.isChecked() ? "2"
                : "-1");

        json.put("sf1505", bi.sf150501.isChecked() ? "1"
                : bi.sf150502.isChecked() ? "2"
                : "-1");

        json.put("sf1506", bi.sf150601.isChecked() ? "1"
                : bi.sf150602.isChecked() ? "2"
                : "-1");

        json.put("sf1507", bi.sf150701.isChecked() ? "1"
                : bi.sf150702.isChecked() ? "2"
                : "-1");

        json.put("sf1508", bi.sf150801.isChecked() ? "1"
                : bi.sf150802.isChecked() ? "2"
                : "-1");

        json.put("sf1509", bi.sf150901.isChecked() ? "1"
                : bi.sf150902.isChecked() ? "2"
                : "-1");

        json.put("sf16", bi.sf1601.isChecked() ? "1"
                : bi.sf1602.isChecked() ? "2"
                : bi.sf1698.isChecked() ? "98"
                : "-1");

        json.put("sf17", bi.sf1701.isChecked() ? "1"
                : bi.sf1702.isChecked() ? "2"
                : "-1");

        json.put("sf18", bi.sf1801.isChecked() ? "1"
                : bi.sf1802.isChecked() ? "2"
                : "-1");

        *//*json.put("sf19", bi.sf19.getText().toString());*//*

        json.put("sf1901", bi.sf1901.getText().toString());
        json.put("sf1902", bi.sf1902.getText().toString());
        json.put("sf20", bi.sf20.getText().toString());*/

    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    public void BtnEnd() {
        oF = new Intent(this, MainActivity.class);
        startActivity(oF);
    }

    @Override
    public void onBackPressed() {
        contextBackActivity(this);

    }

    public void checkMR(View view) {

        MainApp.sf2 = bi.sf2.getText().toString();


        if (formValidation()) {
            bi.checkMR.setVisibility(View.GONE);
            bi.pbarMR.setVisibility(View.VISIBLE);
            FetchMR();

        }

    }

    private boolean FetchMR() {
        bi.wmError.setVisibility(View.GONE);
        bi.wmError.setText(null);

        // Sending data to Worker class

       /* Data data = new Data.Builder()
                .putString(KEY, VALUE)
                .build();


        final OneTimeWorkRequest workRequest1 = new OneTimeWorkRequest.Builder(FetchMRWorker.class)
                .setInputData(data)
                .build();
*/

        final OneTimeWorkRequest workRequest1 = new OneTimeWorkRequest.Builder(FetchMRWorker.class).build();
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
                            String message = workInfo.getOutputData().getString("mrno");
                            DatabaseHelper db = new DatabaseHelper(SectionSFActivity.this); // Database Helper
                            StringBuilder sSyncedError = new StringBuilder();
                            JSONObject jsonObject;
                            try {


                                JSONArray json = new JSONArray(message);
                                for (int i = 0; i < json.length(); i++) {
                                    jsonObject = new JSONObject(json.getString(i));

                                    if (jsonObject.getString("sl2") != null) {
                                        if (!jsonObject.getString("sl2").equals("00000")) {
                                            bi.sf4.setText(jsonObject.getString("sl2"));
                                            bi.sf3.setText(jsonObject.getString("sl5"));

                                            bi.llsectionsf01.setVisibility(View.VISIBLE);
                                            bi.btnContinue.setVisibility(View.VISIBLE);

                                            bi.sf3.setEnabled(false);
                                        } else if (jsonObject.getString("sl2").equals("88888")) {
                                            if (bi.llsectionsf01.getVisibility() == View.VISIBLE) {
                                                bi.llsectionsf01.setVisibility(View.GONE);
                                                bi.btnContinue.setVisibility(View.GONE);
                                                Clear.clearAllFields(bi.llsectionsf01);
                                            }
                                        } else {
                                            bi.wmError.setText(jsonObject.getString("sl5"));
                                            bi.wmError.setVisibility(View.VISIBLE);

                                        }
                                    }
                                  /*  if (jsonObject.getString("status").equals("1") && jsonObject.getString("error").equals("0")) {

                                        db.updateSyncedFormsSL(jsonObject.getString("id"));  // UPDATE SYNCED
                                        bi.sf20.setText(jsonObject.getString("study_id"));

                                        //method.invoke(db, jsonObject.getString("id"));

                                    } else if (jsonObject.getString("status").equals("2") && jsonObject.getString("error").equals("0")) {

                                        db.updateSyncedFormsSL(jsonObject.getString("id")); // UPDATE DUPLICATES
                                        //   method.invoke(db, jsonObject.getString("id"));

                                        // sDuplicate++;
                                    } else {
                                        sSyncedError.append("\nError: ").append(jsonObject.getString("message"));

                                    }*/

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
        return false;
    }

    public void deleteName(View view) {
        if (bi.sf4.getText().toString().equals("99999") && (bi.sf3.getText().toString().contains("Mother") || bi.sf3.getText().toString().contains("Found"))) {
            bi.sf3.setText(null);
            bi.sf3.setEnabled(true);
        }
    }


}