package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionWfa05Binding;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;

public class SectionWFA05Activity extends AppCompatActivity {

    ActivitySectionWfa05Binding bi;
    Intent oF = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_wfa05);
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
                startActivity(new Intent(this, MainActivity.class));
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

        /*form.setWfa401( bi.wfa40101.isChecked() ? "1"
                : bi.wfa40102.isChecked() ? "2"
                :  "-1");

        form.setWfa40201(bi.wfa40201.getText().toString());
        form.setWfa40202(bi.wfa40202.getText().toString());
        form.setWfa40203(bi.wfa40203.getText().toString());
        form.setWfa403( bi.wfa40301.isChecked() ? "1"
                : bi.wfa40396.isChecked() ? "96"
                :  "-1");

        form.setWfa40396x(bi.wfa40396x.getText().toString());
        form.setWfa404( bi.wfa40401.isChecked() ? "666"
                : bi.wfa40402.isChecked() ? "2"
                : bi.wfa40403.isChecked() ? "3"
                :  "-1");

        form.setWfa40402x(bi.wfa40402x.getText().toString());
        form.setWfa40403x(bi.wfa40403x.getText().toString());
        form.setWfa405(bi.wfa405.getText().toString());

        form.setWfa406( bi.wfa40601.isChecked() ? "1"
                : bi.wfa40602.isChecked() ? "2"
                :  "-1");

        form.setWfa40701(bi.wfa40701.getText().toString());
        form.setWfa40702(bi.wfa40702.getText().toString());
        form.setWfa40703(bi.wfa40703.getText().toString());
        form.setWfa40801(bi.wfa40801.getText().toString());
        form.setWfa40803(bi.wfa40803.getText().toString());
        form.setWfa40804(bi.wfa40804.getText().toString());
        form.setWfa40805(bi.wfa40805.getText().toString());
        form.setWfa409( bi.wfa40901.isChecked() ? "1"
                : bi.wfa40902.isChecked() ? "2"
                : bi.wfa40903.isChecked() ? "3"
                : bi.wfa40904.isChecked() ? "4"
                : bi.wfa40905.isChecked() ? "5"
                :  "-1");

        form.setWfa40903x(bi.wfa40903x.getText().toString());
        form.setWfa40904x(bi.wfa40904x.getText().toString());
        form.setWfa40905x(bi.wfa40905x.getText().toString());*/

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}