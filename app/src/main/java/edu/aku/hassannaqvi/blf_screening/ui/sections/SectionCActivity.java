package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsENContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionCBinding;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;

public class SectionCActivity extends AppCompatActivity {
    ActivitySectionCBinding bi;
    Intent oF = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* setContentView(R.layout.activity_section_c);*/
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_c);
        bi.setCallback(this);
        /*setupSkips();*/

    }
   /* private void setupSkips() {

        bi.fldGrpCVs3q9.setVisibility(MainApp.childAgeinHours > 24 ? View.VISIBLE : View.GONE);

        bi.s3q3.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.s3q301.getId()) {
                Clear.clearAllFields(bi.fldGrpCVs3q4);
            }
        });

        bi.s3q5.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != bi.s3q505.getId()) {
                Clear.clearAllFields(bi.fldGrpCVs3q6);
            }
        });

        bi.s3q7.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != bi.s3q702.getId()) {
                Clear.clearAllFields(bi.fldGrpCVs3q8);
            }
        });

    }*/


   /* public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
   *//*             finish();
                startActivity(new Intent(this, MainActivity.class));*//*
                bi.pBar3.setVisibility(View.VISIBLE);

                UploadData();

            }
        }
    }*/

   /* private boolean UploadData() {
        final OneTimeWorkRequest workRequest1 = new OneTimeWorkRequest.Builder(DataUpWorkerEN.class).build();
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
                            String message = workInfo.getOutputData().getString("message");
                            DatabaseHelper db = new DatabaseHelper(SectionEN03Activity.this); // Database Helper
                            StringBuilder sSyncedError = new StringBuilder();
                            JSONObject jsonObject;
                            try {

                                JSONArray json = new JSONArray(message);
                                for (int i = 0; i < json.length(); i++) {
                                    jsonObject = new JSONObject(json.getString(i));

                                    if (!jsonObject.getString("error").equals("1")) {

                                        if (jsonObject.getString("status").equals("1")) {

                                            db.updateSyncedFormsEN(jsonObject.getString("id"));  // UPDATE SYNCED

                                            Toast.makeText(SectionEN03Activity.this, "Enrolment Form saved for MR No: " + MainApp.s1q2, Toast.LENGTH_LONG).show();

                                            //method.invoke(db, jsonObject.getString("id"));

                                            final Handler handler = new Handler(Looper.getMainLooper());
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    //Do something after 100ms
                                                    oF = new Intent(SectionEN03Activity.this, MainActivity.class);
                                                    startActivity(oF);
                                                }
                                            }, 3500);

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
    }*/

    public void BtnEnd() {
        oF = new Intent(this, MainActivity.class);
        startActivity(oF);
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormsS3Column(FormsENContract.FormsS3Table.COLUMN_EN, MainApp.formsEN.s3toString());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {


      /*  form.setWfc101( bi.wfc10101.isChecked() ? "1"
                : bi.wfc10102.isChecked() ? "2"
                : bi.wfc10196.isChecked() ? "96"
                :  "-1");

        form.setWfc10196x(bi.wfc10196x.getText().toString());
        form.setWfc102(bi.wfc102.getText().toString());

        form.setWfc103(bi.wfc103.getText().toString());

        form.setWfc104(bi.wfc104.getText().toString());*/


    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}