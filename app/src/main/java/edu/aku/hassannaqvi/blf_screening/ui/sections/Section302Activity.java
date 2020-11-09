package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsS3Contract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySection302Binding;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;

public class Section302Activity extends AppCompatActivity {

    ActivitySection302Binding bi;
    Intent oF = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section302);
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
                startActivity(new Intent(this, Section303Activity.class));
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

        MainApp.formsS3.setS2q1(bi.s2q1.getText().toString().trim().isEmpty() ? "-1" : bi.s2q1.getText().toString());

        MainApp.formsS3.setS2q2(bi.s2q2.getText().toString().trim().isEmpty() ? "-1" : bi.s2q2.getText().toString());

        MainApp.formsS3.setS2q3(bi.s2q3.getText().toString().trim().isEmpty() ? "-1" : bi.s2q3.getText().toString());

        MainApp.formsS3.setS2q4(bi.s2q4.getText().toString().trim().isEmpty() ? "-1" : bi.s2q4.getText().toString());

        MainApp.formsS3.setS2q5(bi.s2q5.getText().toString().trim().isEmpty() ? "-1" : bi.s2q5.getText().toString());

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}