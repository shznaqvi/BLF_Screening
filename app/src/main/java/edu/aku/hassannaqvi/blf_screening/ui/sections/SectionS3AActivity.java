package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsENContract;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsSESContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionS3ABinding;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;
import edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.blf_screening.core.MainApp.formsSES;

public class SectionS3AActivity extends AppCompatActivity {

    ActivitySectionS3ABinding bi;
    Intent oF = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_s3_a);
        bi.setCallback(this);
        setupSkips();
    }

    private void setupSkips() {

        //se3q6
        bi.se3q6.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.se3q602.getId()) {
                Clear.clearAllFields(bi.fldGrpCVse3q7);
                bi.fldGrpCVse3q7.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVse3q7.setVisibility(View.VISIBLE);
            }
        });

        //se3q8
        bi.se3q8.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.se3q802.getId()) {
                Clear.clearAllFields(bi.fldGrpCVse3q9);
                Clear.clearAllFields(bi.fldGrpCVse3q10);
                Clear.clearAllFields(bi.fldGrpCVse3q11);
                Clear.clearAllFields(bi.fldGrpCVse3q12);
                Clear.clearAllFields(bi.fldGrpCVse3q13);
                bi.fldGrpCVse3q9.setVisibility(View.GONE);
                bi.fldGrpCVse3q10.setVisibility(View.GONE);
                bi.fldGrpCVse3q11.setVisibility(View.GONE);
                bi.fldGrpCVse3q12.setVisibility(View.GONE);
                bi.fldGrpCVse3q13.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVse3q9.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q10.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q11.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q12.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q13.setVisibility(View.VISIBLE);
            }
        });


        bi.se3q510.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.se3q510a.getId()) {
                Clear.clearAllFields(bi.fldGrpCVse3q501);
                Clear.clearAllFields(bi.fldGrpCVse3q502);
                Clear.clearAllFields(bi.fldGrpCVse3q503);
                Clear.clearAllFields(bi.fldGrpCVse3q504);
                Clear.clearAllFields(bi.fldGrpCVse3q505);
                Clear.clearAllFields(bi.fldGrpCVse3q506);
                Clear.clearAllFields(bi.fldGrpCVse3q507);
                Clear.clearAllFields(bi.fldGrpCVse3q508);
                Clear.clearAllFields(bi.fldGrpCVse3q509);
                Clear.clearAllFields(bi.fldGrpCVse3q511);
                bi.fldGrpCVse3q501.setVisibility(View.GONE);
                bi.fldGrpCVse3q502.setVisibility(View.GONE);
                bi.fldGrpCVse3q503.setVisibility(View.GONE);
                bi.fldGrpCVse3q504.setVisibility(View.GONE);
                bi.fldGrpCVse3q505.setVisibility(View.GONE);
                bi.fldGrpCVse3q506.setVisibility(View.GONE);
                bi.fldGrpCVse3q507.setVisibility(View.GONE);
                bi.fldGrpCVse3q508.setVisibility(View.GONE);
                bi.fldGrpCVse3q509.setVisibility(View.GONE);
                bi.fldGrpCVse3q511.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVse3q501.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q502.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q503.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q504.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q505.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q506.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q507.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q508.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q509.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q511.setVisibility(View.VISIBLE);
            }
        });

    }


    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, SectionS3BActivity.class));
        }
    }


    public void BtnEnd() {
        String form = "SES";
        AppUtilsKt.openEndActivity(this, form);
    }


    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormsSES(FormsSESContract.FormsSESTable.COLUMN_S3, MainApp.formsSES.getS3());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("se3q0", bi.se3q001.isChecked() ? "1"
                : bi.se3q002.isChecked() ? "2"
                :  "-1");

        json.put("se3q101",bi.se3q101.isChecked() ? "1" :"-1");

        json.put("se3q102",bi.se3q102.isChecked() ? "2" :"-1");

        json.put("se3q103",bi.se3q103.isChecked() ? "3" :"-1");

        json.put("se3q104",bi.se3q104.isChecked() ? "4" :"-1");

        json.put("se3q105",bi.se3q105.isChecked() ? "5" :"-1");

        json.put("se3q106",bi.se3q106.isChecked() ? "6" :"-1");

        json.put("se3q107",bi.se3q107.isChecked() ? "7" :"-1");

        json.put("se3q108",bi.se3q108.isChecked() ? "8" :"-1");

        json.put("se3q196",bi.se3q196.isChecked() ? "96" :"-1");

        json.put("se3q196x", bi.se3q196x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q196x.getText().toString());

        json.put("se3q201",bi.se3q201.isChecked() ? "1" :"-1");

        json.put("se3q202",bi.se3q202.isChecked() ? "2" :"-1");

        json.put("se3q203",bi.se3q203.isChecked() ? "3" :"-1");

        json.put("se3q204",bi.se3q204.isChecked() ? "4" :"-1");

        json.put("se3q205",bi.se3q205.isChecked() ? "5" :"-1");

        json.put("se3q2961",bi.se3q2961.isChecked() ? "961" :"-1");

        json.put("se3q2961x", bi.se3q2961x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q2961x.getText().toString());

        json.put("se3q206",bi.se3q206.isChecked() ? "6" :"-1");

        json.put("se3q207",bi.se3q207.isChecked() ? "7" :"-1");

        json.put("se3q208",bi.se3q208.isChecked() ? "8" :"-1");

        json.put("se3q2962",bi.se3q2962.isChecked() ? "962" :"-1");

        json.put("se3q2962x", bi.se3q2962x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q2962x.getText().toString());
        json.put("se3q2963",bi.se3q2963.isChecked() ? "963" :"-1");

        json.put("se3q2963x", bi.se3q2963x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q2963x.getText().toString());
        json.put("se3q3", bi.se3q399.isChecked() ? "99" :  "-1");

        json.put("se3q301x", bi.se3q301x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q301x.getText().toString());
        json.put("se3q302x", bi.se3q302x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q302x.getText().toString());

        json.put("se3q4", bi.se3q401.isChecked() ? "1"
                : bi.se3q499.isChecked() ? "99"
                :  "-1");

        json.put("se3q401x", bi.se3q401x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q401x.getText().toString());
        json.put("se3q501", bi.se3q501a.isChecked() ? "1"
                : bi.se3q501b.isChecked() ? "2"
                :  "-1");

        json.put("se3q502", bi.se3q502a.isChecked() ? "1"
                : bi.se3q502b.isChecked() ? "2"
                :  "-1");

        json.put("se3q503", bi.se3q503a.isChecked() ? "1"
                : bi.se3q503b.isChecked() ? "2"
                :  "-1");

        json.put("se3q504", bi.se3q504a.isChecked() ? "1"
                : bi.se3q504b.isChecked() ? "2"
                :  "-1");

        json.put("se3q505", bi.se3q505a.isChecked() ? "1"
                : bi.se3q505b.isChecked() ? "2"
                :  "-1");

        json.put("se3q506", bi.se3q506a.isChecked() ? "1"
                : bi.se3q506b.isChecked() ? "2"
                :  "-1");

        json.put("se3q507", bi.se3q507a.isChecked() ? "1"
                : bi.se3q507b.isChecked() ? "2"
                :  "-1");

        json.put("se3q508", bi.se3q508a.isChecked() ? "1"
                : bi.se3q508b.isChecked() ? "2"
                :  "-1");

        json.put("se3q509", bi.se3q509a.isChecked() ? "1"
                : bi.se3q509b.isChecked() ? "2"
                :  "-1");

        json.put("se3q510", bi.se3q510a.isChecked() ? "1"
                : bi.se3q510b.isChecked() ? "2"
                :  "-1");

        json.put("se3q511", bi.se3q511a.isChecked() ? "1"
                : bi.se3q511b.isChecked() ? "2"
                :  "-1");

        json.put("se3q511ax", bi.se3q511ax.getText().toString().trim().isEmpty() ? "-1" : bi.se3q511ax.getText().toString());
        json.put("se3q6", bi.se3q601.isChecked() ? "1"
                : bi.se3q602.isChecked() ? "2"
                :  "-1");

        json.put("se3q7", bi.se3q701.isChecked() ? "1"
                : bi.se3q799.isChecked() ? "99"
                :  "-1");

        json.put("se3q701x", bi.se3q701x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q701x.getText().toString());
        json.put("se3q8", bi.se3q801.isChecked() ? "1"
                : bi.se3q802.isChecked() ? "2"
                :  "-1");

        json.put("se3q9", bi.se3q901.isChecked() ? "1"
                : bi.se3q902.isChecked() ? "2"
                : bi.se3q903.isChecked() ? "3"
                :  "-1");

        json.put("se3q10", bi.se3q1001.isChecked() ? "1"
                : bi.se3q1002.isChecked() ? "2"
                : bi.se3q1003.isChecked() ? "3"
                : bi.se3q1004.isChecked() ? "4"
                : bi.se3q1005.isChecked() ? "5"
                : bi.se3q1006.isChecked() ? "6"
                : bi.se3q1007.isChecked() ? "7"
                : bi.se3q1008.isChecked() ? "8"
                : bi.se3q1096.isChecked() ? "96"
                :  "-1");

        json.put("se3q1096x", bi.se3q1096x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q1096x.getText().toString());
        json.put("se3q11", bi.se3q1101.isChecked() ? "1"
                : bi.se3q1102.isChecked() ? "2"
                : bi.se3q1103.isChecked() ? "3"
                : bi.se3q1104.isChecked() ? "4"
                : bi.se3q1105.isChecked() ? "5"
                : bi.se3q11961.isChecked() ? "961"
                : bi.se3q1106.isChecked() ? "6"
                : bi.se3q1107.isChecked() ? "7"
                : bi.se3q1108.isChecked() ? "8"
                : bi.se3q1109.isChecked() ? "9"
                : bi.se3q1110.isChecked() ? "10"
                : bi.se3q11962.isChecked() ? "962"
                : bi.se3q1111.isChecked() ? "11"
                : bi.se3q1112.isChecked() ? "12"
                : bi.se3q1113.isChecked() ? "13"
                : bi.se3q11963.isChecked() ? "963"
                :  "-1");

        json.put("se3q11961x", bi.se3q11961x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q11961x.getText().toString());
        json.put("se3q11962x", bi.se3q11962x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q11962x.getText().toString());
        json.put("se3q11963x", bi.se3q11963x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q11963x.getText().toString());
        json.put("se3q12", bi.se3q1201.isChecked() ? "1"
                : bi.se3q1202.isChecked() ? "2"
                : bi.se3q1203.isChecked() ? "3"
                : bi.se3q1204.isChecked() ? "4"
                : bi.se3q1205.isChecked() ? "5"
                :  "-1");

        json.put("se3q1301", bi.se3q1301.getText().toString().trim().isEmpty() ? "-1" : bi.se3q1301.getText().toString());
        json.put("se3q1302", bi.se3q1302.getText().toString().trim().isEmpty() ? "-1" : bi.se3q1302.getText().toString());

        formsSES.setS3(String.valueOf(json));
    }


    private boolean formValidation() {

        if (!Validator.emptyCheckingContainer(this, bi.GrpName)) {
            return false;
        }

        /*if (bi.se3q301x.getText().toString().equals("0") && bi.se3q301x.getText().toString().equals("0")) {
            Toast.makeText(this, "The sum of days and months cannot be zero in se3q3", Toast.LENGTH_SHORT).show();
            bi.se3q301x.requestFocus();
            return false;
        }

        if (bi.se3q1301.getText().toString().equals("0") && bi.se3q1302.getText().toString().equals("0")) {
            Toast.makeText(this, "The sum of days and months cannot be zero in se3q13", Toast.LENGTH_SHORT).show();
            bi.se3q1301.requestFocus();
            return false;
        }*/


        return true;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}