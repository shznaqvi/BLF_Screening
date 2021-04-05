package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.jetbrains.annotations.NotNull;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsWFContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionWfa04Binding;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;
import edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt;

public class SectionWFA04Activity extends AppCompatActivity {

    ActivitySectionWfa04Binding bi;
    Intent oF = null;
    String week, delivery_date, fupdate;
    int col_id;
    int wfa106;
    String FD;
    String pFollowUpDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_wfa04);
        bi.setCallback(this);
        setupSkips();

        Intent intent = getIntent();
        week = intent.getStringExtra("week");
        delivery_date = intent.getStringExtra("delivery_date");
        fupdate = intent.getStringExtra("fupdate");
        col_id = intent.getIntExtra("col_id", 0);
        wfa106 = intent.getIntExtra("wfa106", 0);
        FD = intent.getStringExtra("FD");
        pFollowUpDate = intent.getStringExtra("pFollowUpDate");

        bi.wfa40201.setMinDate(delivery_date);
        bi.wfa40201.setMaxDate(fupdate);

        bi.wfa40802.setMinDate(delivery_date);
        bi.wfa40802.setMaxDate(fupdate);



        TextWatcher textwatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bi.wfa40902x.setMinDate(s.toString().replace("-", "/"));
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        bi.wfa40802.addTextChangedListener(textwatcher);
    }


    private void setupSkips() {
        radioGroupListener(bi.wfa401, bi.wfa40102, bi.llGrpsec401);
        radioGroupListener(bi.wfa406, bi.wfa40602, bi.llGrpsec402);

        /*bi.wfa409.setOnCheckedChangeListener((radioGroup, i) -> {
            Clear.clearAllFields(bi.wfa40902x);
            bi.wfa40902x.setVisibility(View.GONE);
            if (i == bi.wfa40902.getId()) {
                bi.wfa40902x.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.wfa40902x);
                bi.wfa40902x.setVisibility(View.GONE);
            }
        });*/
    }


    public void radioGroupListener(@NotNull RadioGroup rg, RadioButton rb, ViewGroup vg) {
        rg.setOnCheckedChangeListener((radioGroup, i) -> {

            /*if (rg == bi.wfa401) {
                bi.wfa403.requestFocus();
            }*/

            if (i == rb.getId()){
                Clear.clearAllFields(vg);
                vg.setVisibility(View.GONE);
            } else {
                vg.setVisibility(View.VISIBLE);
            }
        });
    }


    /*public void radioGroupListener(@NotNull RadioGroup rg, ViewGroup vg) {
        rg.setOnCheckedChangeListener((radioGroup, i) -> {
            Clear.clearAllFields(vg);
            if (vg == bi.llGrpsec401) {
                vg.getChildAt(1).setVisibility(View.GONE);
            }
        });
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
            startActivity(new Intent(this, SectionWFA05Activity.class).putExtra("week", week).putExtra("col_id", col_id).putExtra("wfa106", wfa106).putExtra("FD", FD).putExtra("delivery_date", delivery_date).putExtra("pFollowUpDate", pFollowUpDate));
        }
    }


    public void BtnEnd() {
        String form = "FP";
        AppUtilsKt.openEndActivity(this, form);
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormsWFColumn(FormsWFContract.FormsWFTable.COLUMN_SWFA04, MainApp.formsWF.sWFA04toString());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }


    private void SaveDraft() {

        MainApp.formsWF.setWfa401(bi.wfa40101.isChecked() ? "1"
                : bi.wfa40102.isChecked() ? "2"
                : "-1");

        String wfa40201;
        String wfa40202;
        String wfa40203;
        if (bi.wfa40201.getText().toString().trim().isEmpty()) {
            wfa40201 = "-1";
            wfa40202 = "-1";
            wfa40203 = "-1";
        } else {
            String[] swf402 = bi.wfa40201.getText().toString().split("-");
            wfa40201 = swf402[0];
            wfa40202 = swf402[1];
            wfa40203 = swf402[2];
        }

        MainApp.formsWF.setWfa40201(wfa40201);
        MainApp.formsWF.setWfa40202(wfa40202);
        MainApp.formsWF.setWfa40203(wfa40203);

        MainApp.formsWF.setWfa403(bi.wfa40301.isChecked() ? "1"
                : bi.wfa40396.isChecked() ? "96"
                : "-1");
        MainApp.formsWF.setWfa40396x(bi.wfa40396x.getText().toString().trim().isEmpty() ? "-1" : bi.wfa40396x.getText().toString());

        MainApp.formsWF.setWfa404(bi.wfa40401.isChecked() ? "666"
                : bi.wfa40402.isChecked() ? "2"
                : bi.wfa40403.isChecked() ? "3"
                : "-1");
        MainApp.formsWF.setWfa40402x(bi.wfa40402x.getText().toString().trim().isEmpty() ? "-1" : bi.wfa40402x.getText().toString());
        MainApp.formsWF.setWfa40403x(bi.wfa40403x.getText().toString().trim().isEmpty() ? "-1" : bi.wfa40403x.getText().toString());

        MainApp.formsWF.setWfa405(bi.wfa405.getText().toString().trim().isEmpty() ? "-1" : bi.wfa405.getText().toString());

        MainApp.formsWF.setWfa406(bi.wfa40601.isChecked() ? "1"
                : bi.wfa40602.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa40701(bi.wfa40701.getText().toString().trim().isEmpty() ? "-1" : bi.wfa40701.getText().toString());
        MainApp.formsWF.setWfa40702(bi.wfa40702.getText().toString().trim().isEmpty() ? "-1" : bi.wfa40702.getText().toString());
        MainApp.formsWF.setWfa40703(bi.wfa40703.getText().toString().trim().isEmpty() ? "-1" : bi.wfa40703.getText().toString());

        MainApp.formsWF.setWfa40801(bi.wfa40801.getText().toString().trim().isEmpty() ? "-1" : bi.wfa40801.getText().toString());

        String wfa40802;
        String wfa40803;
        String wfa40804;
        if (bi.wfa40802.getText().toString().trim().isEmpty()) {
            wfa40802 = "-1";
            wfa40803 = "-1";
            wfa40804 = "-1";
        } else {
            String[] swf408 = bi.wfa40802.getText().toString().split("-");
            wfa40802 = swf408[0];
            wfa40803 = swf408[1];
            wfa40804 = swf408[2];
        }

        MainApp.formsWF.setWfa40803(wfa40802);
        MainApp.formsWF.setWfa40804(wfa40803);
        MainApp.formsWF.setWfa40805(wfa40804);

        MainApp.formsWF.setWfa409(bi.wfa40901.isChecked() ? "1"
                : bi.wfa40902.isChecked() ? "2"
                : "-1");
        MainApp.formsWF.setWfa40903x(bi.wfa40902x.getText().toString().trim().isEmpty() ? "-1" : bi.wfa40902x.getText().toString());

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}