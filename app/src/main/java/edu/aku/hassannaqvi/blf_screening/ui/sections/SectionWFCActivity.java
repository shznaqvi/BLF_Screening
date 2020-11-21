package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionWfcBinding;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;

public class SectionWFCActivity extends AppCompatActivity {

    ActivitySectionWfcBinding bi;
    Intent oF = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_wfc);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_wfc);
        bi.setCallback(this);
        setupSkips();

    }

    private void setupSkips() {

        /*bi.s3q7.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != bi.s3q702.getId()) {
                Clear.clearAllFields(bi.fldGrpCVs3q8);
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
       /* DatabaseHelper db = MainApp.appInfo.getDbHelper();
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

      /*  form.setWfc101( bi.wfc10101.isChecked() ? "1"
                : bi.wfc10102.isChecked() ? "2"
                : bi.wfc10196.isChecked() ? "96"
                :  "-1");

        form.setWfc10196x(bi.wfc10196x.getText().toString());
        form.setWfc102(bi.wfc102.getText().toString());

        form.setWfc103(bi.wfc103.getText().toString());

        form.setWfc104(bi.wfc104.getText().toString());*/

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}