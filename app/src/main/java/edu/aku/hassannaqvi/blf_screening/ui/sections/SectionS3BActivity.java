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
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionS3BBinding;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;
import edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt;
import edu.aku.hassannaqvi.blf_screening.utils.JSONUtils;

public class SectionS3BActivity extends AppCompatActivity {

    ActivitySectionS3BBinding bi;
    Intent oF = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_s3_b);
        bi.setCallback(this);
        setupSkips();
    }

    private void setupSkips() {

        //se3q14
        bi.se3q14.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.se3q1402.getId()) {
                Clear.clearAllFields(bi.fldGrpCVse3q15);
                Clear.clearAllFields(bi.fldGrpCVse3q16);
                Clear.clearAllFields(bi.fldGrpCVse3q17);
                Clear.clearAllFields(bi.fldGrpCVse3q18);
                bi.fldGrpCVse3q15.setVisibility(View.GONE);
                bi.fldGrpCVse3q16.setVisibility(View.GONE);
                bi.fldGrpCVse3q17.setVisibility(View.GONE);
                bi.fldGrpCVse3q18.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVse3q15.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q16.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q17.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q18.setVisibility(View.VISIBLE);
            }
        });


        //se3q19
        bi.se3q19.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.se3q1902.getId()) {
                Clear.clearAllFields(bi.fldGrpCVse3q20);
                Clear.clearAllFields(bi.fldGrpCVse3q21);
                Clear.clearAllFields(bi.fldGrpCVse3q22);
                Clear.clearAllFields(bi.fldGrpCVse3q23);
                bi.fldGrpCVse3q20.setVisibility(View.GONE);
                bi.fldGrpCVse3q21.setVisibility(View.GONE);
                bi.fldGrpCVse3q22.setVisibility(View.GONE);
                bi.fldGrpCVse3q23.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVse3q20.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q21.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q22.setVisibility(View.VISIBLE);
                bi.fldGrpCVse3q23.setVisibility(View.VISIBLE);
            }
        });

        //se3q24
        bi.se3q24.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.se3q2402.getId()) {
                Clear.clearAllFields(bi.fldGrpCVse3q25);
                bi.fldGrpCVse3q25.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVse3q25.setVisibility(View.VISIBLE);
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
            startActivity(new Intent(this, SectionS4Activity.class));
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

        json.put("se3q14", bi.se3q1401.isChecked() ? "1"
                : bi.se3q1402.isChecked() ? "2"
                :  "-1");

        json.put("se3q15", bi.se3q1501.isChecked() ? "1"
                : bi.se3q1502.isChecked() ? "2"
                : bi.se3q1503.isChecked() ? "3"
                : bi.se3q1504.isChecked() ? "4"
                : bi.se3q1505.isChecked() ? "5"
                : bi.se3q1506.isChecked() ? "6"
                : bi.se3q1507.isChecked() ? "7"
                : bi.se3q1508.isChecked() ? "8"
                : bi.se3q1596.isChecked() ? "96"
                :  "-1");

        json.put("se3q1596x", bi.se3q1596x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q1596x.getText().toString());

        json.put("se3q1601",bi.se3q1601.isChecked() ? "1" :"-1");

        json.put("se3q1602",bi.se3q1602.isChecked() ? "2" :"-1");

        json.put("se3q1603",bi.se3q1603.isChecked() ? "3" :"-1");

        json.put("se3q1604",bi.se3q1604.isChecked() ? "4" :"-1");

        json.put("se3q1605",bi.se3q1605.isChecked() ? "5" :"-1");

        json.put("se3q16961",bi.se3q16961.isChecked() ? "961" :"-1");

        json.put("se3q16961x", bi.se3q16961x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q16961x.getText().toString());

        json.put("se3q1606",bi.se3q1606.isChecked() ? "6" :"-1");

        json.put("se3q1607",bi.se3q1607.isChecked() ? "7" :"-1");

        json.put("se3q1608",bi.se3q1608.isChecked() ? "8" :"-1");

        json.put("se3q1609",bi.se3q1609.isChecked() ? "9" :"-1");

        json.put("se3q1610",bi.se3q1610.isChecked() ? "10" :"-1");

        json.put("se3q16962",bi.se3q16962.isChecked() ? "962" :"-1");

        json.put("se3q16962x", bi.se3q16962x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q16962x.getText().toString());

        json.put("se3q1611",bi.se3q1611.isChecked() ? "11" :"-1");

        json.put("se3q1612",bi.se3q1612.isChecked() ? "12" :"-1");

        json.put("se3q1613",bi.se3q1613.isChecked() ? "13" :"-1");

        json.put("se3q16963",bi.se3q16963.isChecked() ? "963" :"-1");

        json.put("se3q16963x", bi.se3q16963x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q16963x.getText().toString());
        json.put("se3q17", bi.se3q1701.isChecked() ? "1"
                : bi.se3q1702.isChecked() ? "2"
                : bi.se3q1703.isChecked() ? "3"
                : bi.se3q1704.isChecked() ? "4"
                : bi.se3q1705.isChecked() ? "5"
                :  "-1");

        json.put("se3q1801", bi.se3q1801.getText().toString().trim().isEmpty() ? "-1" : bi.se3q1801.getText().toString());
        json.put("se3q1802", bi.se3q1802.getText().toString().trim().isEmpty() ? "-1" : bi.se3q1802.getText().toString());

        json.put("se3q19", bi.se3q1901.isChecked() ? "1"
                : bi.se3q1902.isChecked() ? "2"
                :  "-1");

        json.put("se3q20", bi.se3q2001.isChecked() ? "1"
                : bi.se3q2002.isChecked() ? "2"
                : bi.se3q2003.isChecked() ? "3"
                : bi.se3q2004.isChecked() ? "4"
                : bi.se3q2005.isChecked() ? "5"
                : bi.se3q2006.isChecked() ? "6"
                : bi.se3q2007.isChecked() ? "7"
                : bi.se3q2008.isChecked() ? "8"
                : bi.se3q2096.isChecked() ? "96"
                :  "-1");

        json.put("se3q2096x", bi.se3q2096x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q2096x.getText().toString());

        json.put("se3q2101",bi.se3q2101.isChecked() ? "1" :"-1");

        json.put("se3q2102",bi.se3q2102.isChecked() ? "2" :"-1");

        json.put("se3q2103",bi.se3q2103.isChecked() ? "3" :"-1");

        json.put("se3q2104",bi.se3q2104.isChecked() ? "4" :"-1");

        json.put("se3q2105",bi.se3q2105.isChecked() ? "5" :"-1");

        json.put("se3q21961",bi.se3q21961.isChecked() ? "961" :"-1");

        json.put("se3q21961x", bi.se3q21961x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q21961x.getText().toString());

        json.put("se3q2106",bi.se3q2106.isChecked() ? "6" :"-1");

        json.put("se3q2107",bi.se3q2107.isChecked() ? "7" :"-1");

        json.put("se3q2108",bi.se3q2108.isChecked() ? "8" :"-1");

        json.put("se3q2109",bi.se3q2109.isChecked() ? "9" :"-1");

        json.put("se3q2110",bi.se3q2110.isChecked() ? "10" :"-1");

        json.put("se3q21962",bi.se3q21962.isChecked() ? "962" :"-1");

        json.put("se3q21962x", bi.se3q21962x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q21962x.getText().toString());

        json.put("se3q2111",bi.se3q2111.isChecked() ? "11" :"-1");

        json.put("se3q2112",bi.se3q2112.isChecked() ? "12" :"-1");

        json.put("se3q2113",bi.se3q2113.isChecked() ? "13" :"-1");

        json.put("se3q21963",bi.se3q21963.isChecked() ? "963" :"-1");

        json.put("se3q21963x", bi.se3q21963x.getText().toString().trim().isEmpty() ? "-1" : bi.se3q21963x.getText().toString());
        json.put("se3q22", bi.se3q2201.isChecked() ? "1"
                : bi.se3q2202.isChecked() ? "2"
                : bi.se3q2203.isChecked() ? "3"
                : bi.se3q2204.isChecked() ? "4"
                : bi.se3q2205.isChecked() ? "5"
                :  "-1");

        json.put("se3q2301", bi.se3q2301.getText().toString().trim().isEmpty() ? "-1" : bi.se3q2301.getText().toString());
        json.put("se3q2302", bi.se3q2302.getText().toString().trim().isEmpty() ? "-1" : bi.se3q2302.getText().toString());

        json.put("se3q24", bi.se3q2401.isChecked() ? "1"
                : bi.se3q2402.isChecked() ? "2"
                :  "-1");

        json.put("se3q25", bi.se3q2501.isChecked() ? "1"
                : bi.se3q2502.isChecked() ? "2"
                :  "-1");

        json.put("se3q26", bi.se3q2601.isChecked() ? "1"
                : bi.se3q2602.isChecked() ? "2"
                : bi.se3q2603.isChecked() ? "3"
                :  "-1");

        try {
            JSONObject json_merge = JSONUtils.mergeJSONObjects(new JSONObject(MainApp.formsSES.getS3()), json);
            MainApp.formsSES.setS3(String.valueOf(json_merge));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private boolean formValidation() {

        if (!Validator.emptyCheckingContainer(this, bi.GrpName)) {
            return false;
        }

        if (bi.se3q1801.getText().toString().equals("0") && bi.se3q1802.getText().toString().equals("0")) {
            Toast.makeText(this, "The sum of months and days cannot be zero", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (bi.se3q2301.getText().toString().equals("0") && bi.se3q2302.getText().toString().equals("0")) {
            Toast.makeText(this, "The sum of months and days cannot be zero", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}