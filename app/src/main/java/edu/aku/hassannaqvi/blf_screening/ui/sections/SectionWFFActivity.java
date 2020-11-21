package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.format.DateFormat;
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

import com.validatorcrawler.aliazaz.Clear;
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
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionWffBinding;
import edu.aku.hassannaqvi.blf_screening.models.FormsSL;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;
import edu.aku.hassannaqvi.blf_screening.utils.DateUtils;
import edu.aku.hassannaqvi.blf_screening.workers.DataUpWorkerSL;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.aku.hassannaqvi.blf_screening.R;

public class SectionWFFActivity extends AppCompatActivity {


    ActivitySectionWffBinding bi;
    Intent oF = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_wff);
        bi.setCallback(this);
        /*setupSkips();*/
    }




    private void setupSkips() {

        bi.wf101.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.wf10102.getId()) {
                Clear.clearAllFields(bi.fldGrpCVwfi02);
            }
        });

    }
    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, SectionWFB02Activity.class));
            }
        }
    }


    public void BtnEnd() {
        oF = new Intent(this, MainActivity.class);
        startActivity(oF);
    }



    private boolean UpdateDB() {
        /*DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormsS3Column(FormsENContract.FormsS3Table.COLUMN_EN, MainApp.formsEN.s3toString());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }*/
        return true;

    }


    private void SaveDraft() {


        JSONObject json = new JSONObject();
        /*json.put("wf101", bi.wf10101.isChecked() ? "1"
                : bi.wf10102.isChecked() ? "2"
                :  "-1");

        json.put("wf10102x", bi.wf10102x.getText().toString());
        json.put("wfi02", bi.wfi02.getText().toString());
*/

    }
    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}