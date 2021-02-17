package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsWFContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionWfb02Binding;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;

public class SectionWFB02Activity extends AppCompatActivity {

    ActivitySectionWfb02Binding bi;
    Intent oF = null;
    String week, delivery_date;
    int col_id;
    int wfa106;
    String FD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        week = intent.getStringExtra("week");
        delivery_date = intent.getStringExtra("delivery_date");
        col_id = intent.getIntExtra("col_id", 0);
        wfa106 = intent.getIntExtra("wfa106", 0);
        FD = intent.getStringExtra("FD");

        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_wfb02);
        bi.setCallback(this);
        setupSkips();

        if (wfa106 == 1) {
            bi.fldGrpCVwfb203.setVisibility(View.GONE);
            bi.fldGrpCVwfb204.setVisibility(View.GONE);
        } else {
            bi.fldGrpCVwfb203.setVisibility(View.VISIBLE);
            bi.fldGrpCVwfb204.setVisibility(View.VISIBLE);
        }
    }


    private void setupSkips() {

        bi.wfb201.setOnCheckedChangeListener((group, i) -> {

            Clear.clearAllFields(bi.fldGrpCVwfb202);
            bi.fldGrpCVwfb202.setVisibility(View.GONE);

            if (wfa106 == 0) {
                Clear.clearAllFields(bi.fldGrpCVwfb203);
                bi.fldGrpCVwfb203.setVisibility(View.GONE);
            }

            if (i == bi.wfb20102.getId()) {
                bi.fldGrpCVwfb202.setVisibility(View.VISIBLE);
                if (wfa106 == 0) {
                    bi.fldGrpCVwfb203.setVisibility(View.VISIBLE);
                }
            } else {
                Clear.clearAllFields(bi.fldGrpCVwfb202);
                bi.fldGrpCVwfb202.setVisibility(View.GONE);
                if (wfa106 == 0) {
                    Clear.clearAllFields(bi.fldGrpCVwfb203);
                    bi.fldGrpCVwfb203.setVisibility(View.GONE);
                }
            }

        });

        bi.wfb205.setOnCheckedChangeListener((group, i) -> {
            Clear.clearAllFields(bi.fldGrpCVwfb206);
            Clear.clearAllFields(bi.fldGrpCVwfb207);
            bi.fldGrpCVwfb206.setVisibility(View.GONE);
            bi.fldGrpCVwfb207.setVisibility(View.GONE);
            if (i == bi.wfb20503.getId() || i == bi.wfb20504.getId()) {
                bi.fldGrpCVwfb206.setVisibility(View.VISIBLE);
            } else {
                bi.fldGrpCVwfb207.setVisibility(View.VISIBLE);
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
                startActivity(new Intent(this, SectionWFCActivity.class).putExtra("week", week).putExtra("col_id", col_id).putExtra("FD", FD).putExtra("delivery_date", delivery_date));
            }
        }
    }


    public void BtnEnd() {
        oF = new Intent(this, MainActivity.class);
        startActivity(oF);
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormsWFColumn(FormsWFContract.FormsWFTable.COLUMN_SWFB02, MainApp.formsWF.sWFB02toString());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }


    private void SaveDraft() {

        MainApp.formsWF.setWfb201(bi.wfb20101.isChecked() ? "1"
                : bi.wfb20102.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfb202(bi.wfb202.getText().toString().trim().isEmpty() ? "-1" : bi.wfb202.getText().toString());

        MainApp.formsWF.setWfb203(bi.wfb20301.isChecked() ? "1"
                : bi.wfb20302.isChecked() ? "2"
                : bi.wfb20303.isChecked() ? "3"
                : bi.wfb20396.isChecked() ? "96"
                : "-1");
        MainApp.formsWF.setWfb20396x(bi.wfb20396x.getText().toString().trim().isEmpty() ? "-1" : bi.wfb20396x.getText().toString());

        MainApp.formsWF.setWfb204(bi.wfb204.getText().toString().trim().isEmpty() ? "-1" : bi.wfb204.getText().toString());

        MainApp.formsWF.setWfb205(bi.wfb20501.isChecked() ? "1"
                : bi.wfb20502.isChecked() ? "2"
                : bi.wfb20503.isChecked() ? "3"
                : bi.wfb20504.isChecked() ? "4"
                : bi.wfb20596.isChecked() ? "96"
                : "-1");
        MainApp.formsWF.setWfb20596x(bi.wfb20596x.getText().toString().trim().isEmpty() ? "-1" : bi.wfb20596x.getText().toString());

        MainApp.formsWF.setWfb206(bi.wfb206.getText().toString().trim().isEmpty() ? "-1" : bi.wfb206.getText().toString());

        MainApp.formsWF.setWfb207(bi.wfb207.getText().toString().trim().isEmpty() ? "-1" : bi.wfb207.getText().toString());

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}