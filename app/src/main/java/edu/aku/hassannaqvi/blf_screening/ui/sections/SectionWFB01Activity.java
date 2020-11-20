package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionWfb01Binding;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;

public class SectionWFB01Activity extends AppCompatActivity {

    ActivitySectionWfb01Binding bi;
    Intent oF = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_wfb01);
        bi.setCallback(this);
        setupSkips();
    }

    private void setupSkips() {

        /*bi.ah51.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.ah51e.getId()) {
                Clear.clearAllFields(bi.fldGrpAH52);
            }
        });*/

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


       /* form.setWfb101( bi.wfb10101.isChecked() ? "1"
                : bi.wfb10102.isChecked() ? "2"
                :  "-1");

        form.setWfb102( bi.wfb10201.isChecked() ? "1"
                : bi.wfb10202.isChecked() ? "2"
                : bi.wfb10203.isChecked() ? "3"
                : bi.wfb10204.isChecked() ? "4"
                : bi.wfb10296.isChecked() ? "96"
                :  "-1");

        form.setWfb10296x(bi.wfb10296x.getText().toString());
        form.setWfb103(bi.wfb103.getText().toString());

        form.setWfb104(bi.wfb104.getText().toString());

        form.setWfb105(bi.wfb105.getText().toString());

        form.setWfi06(bi.wfi06.getText().toString());

        form.setWfi0601(bi.wfi0601.getText().toString());
        form.setWfi0701(bi.wfi0701.isChecked() ? "1" : "-1");

        form.setWfi0702(bi.wfi0702.isChecked() ? "2" : "-1");

        form.setWfi0796(bi.wfi0796.isChecked() ? "96" : "-1");

        form.setWfi0796x(bi.wfi0796x.getText().toString());
        form.setWfb1081a( bi.wfb1081a01.isChecked() ? "1"
                : bi.wfb1081a02.isChecked() ? "2"
                :  "-1");

        form.setWfb1081b( bi.wfb1081b01.isChecked() ? "1"
                : bi.wfb1081b02.isChecked() ? "2"
                :  "-1");

        form.setWfb1081c( bi.wfb1081c01.isChecked() ? "1"
                : bi.wfb1081c02.isChecked() ? "2"
                : bi.wfb1081c03.isChecked() ? "3"
                :  "-1");

        form.setWfb1081d( bi.wfb1081d01.isChecked() ? "1"
                : bi.wfb1081d02.isChecked() ? "2"
                : bi.wfb1081d03.isChecked() ? "3"
                : bi.wfb1081d04.isChecked() ? "4"
                : bi.wfb1081d05.isChecked() ? "5"
                : bi.wfb1081d06.isChecked() ? "6"
                : bi.wfb1081d96.isChecked() ? "96"
                :  "-1");

        form.setWfb1081d06x(bi.wfb1081d06x.getText().toString());
        form.setWfb1081d96x(bi.wfb1081d96x.getText().toString());*/

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}