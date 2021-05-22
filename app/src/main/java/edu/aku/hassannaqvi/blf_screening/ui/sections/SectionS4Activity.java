package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.edittextpicker.aliazaz.EditTextPicker;
import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.jar.JarException;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsENContract;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsSESContract;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsWFContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionS4Binding;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionWfa03Binding;
import edu.aku.hassannaqvi.blf_screening.databinding.WfaCardLayoutBinding;
import edu.aku.hassannaqvi.blf_screening.models.SubModel;
import edu.aku.hassannaqvi.blf_screening.models.WFA303Model;
import edu.aku.hassannaqvi.blf_screening.ui.other.EndingActivity;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;
import edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.blf_screening.core.MainApp.formsSES;

public class SectionS4Activity extends AppCompatActivity {

    ActivitySectionS4Binding bi;
    Intent oF = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_s4);
        bi.setCallback(this);
        setupSkips();
    }

    private void setupSkips() {

        //se4q1
        bi.se4q1.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.se4q101.getId()) {
                Clear.clearAllFields(bi.fldGrpCVse4q2);
                bi.fldGrpCVse4q2.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVse4q2.setVisibility(View.VISIBLE);
            }
        });


        //se4q3
        bi.se4q3.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.se4q302.getId()) {
                Clear.clearAllFields(bi.fldGrpCVse4q4);
                bi.fldGrpCVse4q4.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVse4q4.setVisibility(View.VISIBLE);
            }
        });

        //se4q7
        bi.se4q7.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.se4q702.getId()) {
                Clear.clearAllFields(bi.fldGrpCVse4q8);
                Clear.clearAllFields(bi.fldGrpCVse4q9);
                Clear.clearAllFields(bi.fldGrpCVse4q10);
                Clear.clearAllFields(bi.fldGrpCVse4q11);
                bi.fldGrpCVse4q8.setVisibility(View.GONE);
                bi.fldGrpCVse4q9.setVisibility(View.GONE);
                bi.fldGrpCVse4q10.setVisibility(View.GONE);
                bi.fldGrpCVse4q11.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVse4q8.setVisibility(View.VISIBLE);
                bi.fldGrpCVse4q9.setVisibility(View.VISIBLE);
                bi.fldGrpCVse4q10.setVisibility(View.VISIBLE);
                bi.fldGrpCVse4q11.setVisibility(View.VISIBLE);
            }
        });

        //se4q12
        bi.se4q12.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.se4q1202.getId()) {
                Clear.clearAllFields(bi.fldGrpCVse4q13);
                Clear.clearAllFields(bi.fldGrpCVse4q14);
                Clear.clearAllFields(bi.fldGrpCVse4q15);
                Clear.clearAllFields(bi.fldGrpCVse4q16);
                bi.fldGrpCVse4q13.setVisibility(View.GONE);
                bi.fldGrpCVse4q14.setVisibility(View.GONE);
                bi.fldGrpCVse4q15.setVisibility(View.GONE);
                bi.fldGrpCVse4q16.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVse4q13.setVisibility(View.VISIBLE);
                bi.fldGrpCVse4q14.setVisibility(View.VISIBLE);
                bi.fldGrpCVse4q15.setVisibility(View.VISIBLE);
                bi.fldGrpCVse4q16.setVisibility(View.VISIBLE);
            }
        });

        //se4q17
        bi.se4q17.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.se4q1702.getId()) {
                Clear.clearAllFields(bi.fldGrpCVse4q18);
                bi.fldGrpCVse4q18.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVse4q18.setVisibility(View.VISIBLE);
            }
        });

        //g1251
        bi.se4q1899.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Clear.clearAllFields(bi.se4q18check, !isChecked);
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
            startActivity(new Intent(this, EndingActivity.class).putExtra("form", "SES").putExtra("complete", true));
        }
    }


    public void BtnEnd() {
        String form = "SES";
        AppUtilsKt.openEndActivity(this, form);
    }


    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormsSES(FormsSESContract.FormsSESTable.COLUMN_S4, MainApp.formsSES.getS4());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("se4q0", bi.se4q001.isChecked() ? "1"
                : bi.se4q002.isChecked() ? "2"
                : bi.se4q003.isChecked() ? "3"
                : bi.se4q004.isChecked() ? "4"
                : "-1");

        json.put("se4q1", bi.se4q101.isChecked() ? "1"
                : bi.se4q102.isChecked() ? "2"
                : bi.se4q199.isChecked() ? "99"
                : "-1");

        json.put("se4q2", bi.se4q201.isChecked() ? "1"
                : bi.se4q202.isChecked() ? "2"
                : bi.se4q203.isChecked() ? "3"
                : bi.se4q299.isChecked() ? "99"
                : bi.se4q296.isChecked() ? "96"
                : "-1");

        json.put("se4q296x", bi.se4q296x.getText().toString().trim().isEmpty() ? "-1" : bi.se4q296x.getText().toString());
        json.put("se4q3", bi.se4q301.isChecked() ? "1"
                : bi.se4q302.isChecked() ? "2"
                : bi.se4q399.isChecked() ? "99"
                : "-1");

        json.put("se4q4", bi.se4q401.isChecked() ? "1"
                : bi.se4q402.isChecked() ? "2"
                : bi.se4q499.isChecked() ? "99"
                : "-1");

        json.put("se4q5", bi.se4q501.isChecked() ? "1"
                : bi.se4q502.isChecked() ? "2"
                : bi.se4q599.isChecked() ? "99"
                : "-1");

        json.put("se4q6", bi.se4q601.isChecked() ? "1"
                : bi.se4q602.isChecked() ? "2"
                : bi.se4q603.isChecked() ? "3"
                : bi.se4q697.isChecked() ? "97"
                : bi.se4q699.isChecked() ? "99"
                : "-1");

        json.put("se4q602x", bi.se4q602x.getText().toString().trim().isEmpty() ? "-1" : bi.se4q602x.getText().toString());
        json.put("se4q603x", bi.se4q603x.getText().toString().trim().isEmpty() ? "-1" : bi.se4q603x.getText().toString());

        json.put("se4q7", bi.se4q701.isChecked() ? "1"
                : bi.se4q702.isChecked() ? "2"
                : "-1");

        json.put("se4q801", bi.se4q801.isChecked() ? "1" : "-1");

        json.put("se4q802", bi.se4q802.isChecked() ? "2" : "-1");

        json.put("se4q803", bi.se4q803.isChecked() ? "3" : "-1");

        json.put("se4q896", bi.se4q896.isChecked() ? "96" : "-1");

        json.put("se4q896x", bi.se4q896x.getText().toString().trim().isEmpty() ? "-1" : bi.se4q896x.getText().toString());
        json.put("se4q9", bi.se4q901.isChecked() ? "1"
                : bi.se4q902.isChecked() ? "2"
                : bi.se4q903.isChecked() ? "3"
                : bi.se4q999.isChecked() ? "99"
                : "-1");

        json.put("se4q901x", bi.se4q901x.getText().toString().trim().isEmpty() ? "-1" : bi.se4q901x.getText().toString());
        json.put("se4q902x", bi.se4q902x.getText().toString().trim().isEmpty() ? "-1" : bi.se4q902x.getText().toString());
        json.put("se4q903x", bi.se4q903x.getText().toString().trim().isEmpty() ? "-1" : bi.se4q903x.getText().toString());

        json.put("se4q10", bi.se4q10.getText().toString().trim().isEmpty() ? "-1" : bi.se4q10.getText().toString());

        json.put("se4q1101", bi.se4q1101.isChecked() ? "1" : "-1");

        json.put("se4q1102", bi.se4q1102.isChecked() ? "2" : "-1");

        json.put("se4q1103", bi.se4q1103.isChecked() ? "3" : "-1");

        json.put("se4q1104", bi.se4q1104.isChecked() ? "4" : "-1");

        json.put("se4q1105", bi.se4q1105.isChecked() ? "5" : "-1");

        json.put("se4q1106", bi.se4q1106.isChecked() ? "6" : "-1");

        json.put("se4q1196", bi.se4q1196.isChecked() ? "96" : "-1");

        json.put("se4q1196x", bi.se4q1196x.getText().toString().trim().isEmpty() ? "-1" : bi.se4q1196x.getText().toString());
        json.put("se4q12", bi.se4q1201.isChecked() ? "1"
                : bi.se4q1202.isChecked() ? "2"
                : "-1");

        json.put("se4q1301", bi.se4q1301.isChecked() ? "1" : "-1");

        json.put("se4q1302", bi.se4q1302.isChecked() ? "2" : "-1");

        json.put("se4q1303", bi.se4q1303.isChecked() ? "3" : "-1");

        json.put("se4q1396", bi.se4q1396.isChecked() ? "96" : "-1");

        json.put("se4q1396x", bi.se4q1396x.getText().toString().trim().isEmpty() ? "-1" : bi.se4q1396x.getText().toString());
        json.put("se4q14", bi.se4q1401.isChecked() ? "1"
                : bi.se4q1402.isChecked() ? "2"
                : bi.se4q1403.isChecked() ? "3"
                : bi.se4q1499.isChecked() ? "99"
                : "-1");

        json.put("se4q1401x", bi.se4q1401x.getText().toString().trim().isEmpty() ? "-1" : bi.se4q1401x.getText().toString());
        json.put("se4q1402x", bi.se4q1402x.getText().toString().trim().isEmpty() ? "-1" : bi.se4q1402x.getText().toString());
        json.put("se4q1403x", bi.se4q1403x.getText().toString().trim().isEmpty() ? "-1" : bi.se4q1403x.getText().toString());

        json.put("se4q15", bi.se4q15.getText().toString().trim().isEmpty() ? "-1" : bi.se4q15.getText().toString());

        json.put("se4q1601", bi.se4q1601.isChecked() ? "1" : "-1");

        json.put("se4q1602", bi.se4q1602.isChecked() ? "2" : "-1");

        json.put("se4q1603", bi.se4q1603.isChecked() ? "3" : "-1");

        json.put("se4q1605", bi.se4q1605.isChecked() ? "5" : "-1");

        json.put("se4q1606", bi.se4q1606.isChecked() ? "6" : "-1");

        json.put("se4q1696", bi.se4q1696.isChecked() ? "96" : "-1");

        json.put("se4q1696x", bi.se4q1696x.getText().toString().trim().isEmpty() ? "-1" : bi.se4q1696x.getText().toString());
        json.put("se4q17", bi.se4q1701.isChecked() ? "1"
                : bi.se4q1702.isChecked() ? "2"
                : bi.se4q1799.isChecked() ? "99"
                : "-1");

        json.put("se4q1801", bi.se4q1801.isChecked() ? "1" : "-1");

        json.put("se4q1802", bi.se4q1802.isChecked() ? "2" : "-1");

        json.put("se4q1803", bi.se4q1803.isChecked() ? "3" : "-1");

        json.put("se4q1804", bi.se4q1804.isChecked() ? "4" : "-1");

        json.put("se4q1805", bi.se4q1805.isChecked() ? "5" : "-1");

        json.put("se4q1806", bi.se4q1806.isChecked() ? "6" : "-1");

        json.put("se4q1899", bi.se4q1899.isChecked() ? "99" : "-1");

        json.put("se4q1896", bi.se4q1896.isChecked() ? "96" : "-1");

        json.put("se4q1896x", bi.se4q1896x.getText().toString().trim().isEmpty() ? "-1" : bi.se4q1896x.getText().toString());

        formsSES.setS4(String.valueOf(json));
    }


    private boolean formValidation() {

        if (!Validator.emptyCheckingContainer(this, bi.GrpName)) {
            return false;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}