package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsSLContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionSlBinding;
import edu.aku.hassannaqvi.blf_screening.models.FormsSL;
import edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt;
import edu.aku.hassannaqvi.blf_screening.workers.DataUpWorkerSL;

import static edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt.contextBackActivity;

public class SectionSLActivity extends AppCompatActivity {

    ActivitySectionSlBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_sl);
        bi.setCallback(this);

        setupSkip();
    }

    private void setupSkip() {

//        bi.a07.setOnCheckedChangeListener((group, checkId) -> {
//            if (bi.a0701.isChecked()) {
//                bi.btnContinue.setVisibility(View.VISIBLE);
//                bi.btnEnd.setVisibility(View.GONE);
//            } else {
//                bi.btnContinue.setVisibility(View.GONE);
//                bi.btnEnd.setVisibility(View.VISIBLE);
//                Clear.clearAllFields(bi.lla08);
//            }
//        });
//
//        bi.a05b.setOnCheckedChangeListener((group, checkId) -> {
//            if (bi.a05b1.isChecked()) {
//                bi.btnContinue.setVisibility(View.VISIBLE);
//                bi.btnEnd.setVisibility(View.GONE);
//            } else {
//                bi.btnContinue.setVisibility(View.GONE);
//                bi.btnEnd.setVisibility(View.VISIBLE);
//                bi.a07.clearCheck();
//            }
//        });

        /*bi.a21.setOnCheckedChangeListener((group, checkId) -> {
            Clear.clearAllFields(bi.fldGrpSecA03);
        });*/
    }

    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            RetrieveSLNo();
            // startActivity(new Intent(this, EndingActivity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean RetrieveSLNo() {
        bi.sl4.setError(null);
        final OneTimeWorkRequest workRequest1 = new OneTimeWorkRequest.Builder(DataUpWorkerSL.class).build();
        WorkManager.getInstance().enqueue(workRequest1);


        WorkManager.getInstance().getWorkInfoByIdLiveData(workRequest1.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workInfo) {


                        if (workInfo.getState() != null &&
                                workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                            //Displaying the status into TextView
                            //mTextView1.append("\n" + workInfo.getState().name());
                            bi.wmError.setVisibility(View.GONE);

                            String message = workInfo.getOutputData().getString("slno");
                            DatabaseHelper db = new DatabaseHelper(SectionSLActivity.this); // Database Helper
                            StringBuilder sSyncedError = new StringBuilder();
                            JSONObject jsonObject;
                            try {


                                JSONArray json = new JSONArray(message);
                                for (int i = 0; i < json.length(); i++) {
                                    jsonObject = new JSONObject(json.getString(i));


                                    if (jsonObject.getString("status").equals("1") && jsonObject.getString("error").equals("0")) {

                                        db.updateSyncedFormsSL(jsonObject.getString("id"));  // UPDATE SYNCED
                                        bi.sl2.setText(jsonObject.getString("slno"));

                                        //method.invoke(db, jsonObject.getString("id"));

                                    } else if (jsonObject.getString("status").equals("2") && jsonObject.getString("error").equals("0")) {

                                        if (jsonObject.getString("sl4").equals(bi.sl4.getText().toString())) {
                                            bi.wmError.setText("MR No. already exists.");
                                            bi.wmError.setVisibility(View.VISIBLE);
                                            bi.sl4.setError("MR No. already exists");
                                        } else {
                                            bi.wmError.setText("Server Error: UID not unique.");
                                        }

                                        db.updateSyncedFormsSL(jsonObject.getString("id")); // UPDATE DUPLICATES

                                    } else {
                                        sSyncedError.append("\nError: ").append(jsonObject.getString("message"));

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
        long updcount = db.addFormSL(MainApp.formsSL);
        MainApp.formsSL.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            MainApp.formsSL.set_UID(MainApp.formsSL.getDeviceID() + MainApp.formsSL.get_ID());
            db.updatesFormsSLColumn(FormsSLContract.FormsSLTable.COLUMN_UID, MainApp.formsSL.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {
        MainApp.formsSL = new FormsSL();

        MainApp.formsSL.setSysdate(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));
        MainApp.formsSL.setDeviceID(MainApp.appInfo.getDeviceID());
        MainApp.formsSL.setDevicetagID(MainApp.appInfo.getTagName());
        MainApp.formsSL.setAppversion(MainApp.appInfo.getAppVersion());
        MainApp.formsSL.setSl2(bi.sl2.getText().toString());


        MainApp.formsSL.setSl301(bi.sl301.getText().toString());
        MainApp.formsSL.setSl302(bi.sl302.getText().toString());
        MainApp.formsSL.setSl303(bi.sl303.getText().toString());

        MainApp.formsSL.setSl4(bi.sl4.getText().toString());

        MainApp.formsSL.setSl5(bi.sl5.getText().toString());

        MainApp.formsSL.setSl601(bi.sl601.getText().toString());
        MainApp.formsSL.setSl602(bi.sl602.getText().toString());

        MainApp.formsSL.setSl701(bi.sl701.getText().toString());
        MainApp.formsSL.setSl702(bi.sl702.getText().toString());
        MainApp.formsSL.setSl703(bi.sl703.getText().toString());


        MainApp.formsSL.setSl8(bi.sl801.isChecked() ? "1"
                : bi.sl802.isChecked() ? "2"
                : "-1");

        MainApp.formsSL.setSl9(bi.sl9.getText().toString());

        MainApp.formsSL.setSl10(bi.sl10.getText().toString());

        MainApp.formsSL.setSl11(bi.sl11.getText().toString());

       /* JSONObject json = new JSONObject();

        json.put("sl2", bi.sl2.getText().toString());
       *//* MainApp.jsonSL = new JSONObject();*//*


        //MainApp.jsonSL .put("sl2", bi.sl2.getText().toString());

        json.put("sl302", bi.sl302.getText().toString());

        json.put("sl303", bi.sl303.getText().toString());

        json.put("sl4", bi.sl4.getText().toString());

        json.put("sl5", bi.sl5.getText().toString());

        json.put("sl601", bi.sl601.getText().toString());

        json.put("sl602", bi.sl602.getText().toString());

        json.put("sl701", bi.sl701.getText().toString());

        json.put("sl702", bi.sl702.getText().toString());

        json.put("sl703", bi.sl703.getText().toString());

        json.put("sl8", bi.sl801.isChecked() ? ""
                : bi.sl802.isChecked() ? ""
                : "-1");

        json.put("sl9", bi.sl9.getText().toString());

        json.put("sl10", bi.sl10.getText().toString());

        json.put("sl11", bi.sl11.getText().toString());*/



   /* private boolean formValidation() {
        if (!Validator.emptyCheckingContainer(this, bi.GrpName)) return false;
        if (bi.a05b2.isChecked() || bi.a0702.isChecked()) return true;

        if (!dtFlag) {
            return Validator.emptyCustomTextBox(this, bi.a13yy, "Invalid date!", false);
        }

        if (Integer.parseInt(bi.a14mm.getText().toString()) == 0 && Integer.parseInt(bi.a14yy.getText().toString()) == 0)
            return Validator.emptyCustomTextBox(this, bi.a14yy, "Both Month & Year don't be zero!!", false);

        return true;
    }*/

    /*public void BtnEnd() {
        if (!Validator.emptyCheckingContainer(this, bi.fldGrpSecA00)) return;
        AppUtilsKt.contextEndActivity(this, false);
    }*/

    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    public void BtnEnd() {
        AppUtilsKt.contextEndActivity(this, false);
    }

    @Override
    public void onBackPressed() {
        contextBackActivity(this);

    }

}