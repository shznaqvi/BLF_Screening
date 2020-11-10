package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsS3Contract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySection303Binding;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;

public class Section303Activity extends AppCompatActivity {

    ActivitySection303Binding bi;
    Intent oF = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section303);
        bi.setCallback(this);
        setupSkips();
    }

    private void setupSkips() {

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
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormsS3Column(FormsS3Contract.FormsS3Table.COLUMN_S3, MainApp.formsS3.s3toString());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() {

        MainApp.formsS3.setS3q1(bi.s3q101.isChecked() ? "1"
                : bi.s3q102.isChecked() ? "2"
                : bi.s3q199.isChecked() ? "99"
                : "-1");

        MainApp.formsS3.setS3q2(bi.s3q201.isChecked() ? "1"
                : bi.s3q202.isChecked() ? "2"
                : bi.s3q203.isChecked() ? "3"
                : bi.s3q204.isChecked() ? "4"
                : bi.s3q205.isChecked() ? "5"
                : bi.s3q206.isChecked() ? "6"
                : bi.s3q2096.isChecked() ? "96"
                : "-1");
        MainApp.formsS3.setS3q2096x(bi.s3q2096x.getText().toString().trim().isEmpty() ? "-1" : bi.s3q2096x.getText().toString());

        MainApp.formsS3.setS3q3(bi.s3q301.isChecked() ? "1"
                : bi.s3q302.isChecked() ? "2"
                : bi.s3q399.isChecked() ? "99"
                : "-1");

        MainApp.formsS3.setS3q401(bi.s3q401.isChecked() ? "1" : "-1");
        MainApp.formsS3.setS3q402(bi.s3q402.isChecked() ? "2" : "-1");
        MainApp.formsS3.setS3q403(bi.s3q403.isChecked() ? "3" : "-1");
        MainApp.formsS3.setS3q496(bi.s3q496.isChecked() ? "96" : "-1");
        MainApp.formsS3.setS3q496x(bi.s3q496x.getText().toString().trim().isEmpty() ? "-1" : bi.s3q496x.getText().toString());

        MainApp.formsS3.setS3q5(bi.s3q501.isChecked() ? "1"
                : bi.s3q502.isChecked() ? "2"
                : bi.s3q503.isChecked() ? "3"
                : bi.s3q504.isChecked() ? "4"
                : bi.s3q505.isChecked() ? "5"
                : bi.s3q596.isChecked() ? "96"
                : "-1");
        MainApp.formsS3.setS3q596x(bi.s3q596x.getText().toString().trim().isEmpty() ? "-1" : bi.s3q596x.getText().toString());

        MainApp.formsS3.setS3q601(bi.s3q601.isChecked() ? "1" : "-1");
        MainApp.formsS3.setS3q602(bi.s3q602.isChecked() ? "2" : "-1");
        MainApp.formsS3.setS3q603(bi.s3q603.isChecked() ? "3" : "-1");
        MainApp.formsS3.setS3q604(bi.s3q604.isChecked() ? "4" : "-1");
        MainApp.formsS3.setS3q605(bi.s3q605.isChecked() ? "5" : "-1");
        MainApp.formsS3.setS3q696(bi.s3q696.isChecked() ? "96" : "-1");
        MainApp.formsS3.setS3q696x(bi.s3q696x.getText().toString().trim().isEmpty() ? "-1" : bi.s3q696x.getText().toString());

        MainApp.formsS3.setS3q7(bi.s3q701.isChecked() ? "1"
                : bi.s3q702.isChecked() ? "2"
                : "-1");

        MainApp.formsS3.setS3q801(bi.s3q801.isChecked() ? "1" : "-1");
        MainApp.formsS3.setS3q802(bi.s3q802.isChecked() ? "2" : "-1");
        MainApp.formsS3.setS3q803(bi.s3q803.isChecked() ? "3" : "-1");
        MainApp.formsS3.setS3q896(bi.s3q896.isChecked() ? "96" : "-1");
        MainApp.formsS3.setS3q896x(bi.s3q896x.getText().toString().trim().isEmpty() ? "-1" : bi.s3q896x.getText().toString());

        MainApp.formsS3.setS3q9(bi.s3q901.isChecked() ? "1"
                : bi.s3q902.isChecked() ? "2"
                : bi.s3q999.isChecked() ? "99"
                : "-1");

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}