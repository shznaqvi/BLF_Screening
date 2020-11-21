package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionWfa02Binding;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;

public class SectionWFA02Activity extends AppCompatActivity {

    ActivitySectionWfa02Binding bi;
    Intent oF = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_wfa02);
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
                startActivity(new Intent(this, SectionWFA03Activity.class));
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


       /* form.setWfa201( bi.wfa20101.isChecked() ? "1"
                : bi.wfa20102.isChecked() ? "2"
                :  "-1");

        form.setWfa20201(bi.wfa20201.isChecked() ? "1" : "-1");

        form.setWfa20202(bi.wfa20202.isChecked() ? "2" : "-1");

        form.setWfa20203(bi.wfa20203.isChecked() ? "3" : "-1");

        form.setWfa20204(bi.wfa20204.isChecked() ? "4" : "-1");

        form.setWfa20205(bi.wfa20205.isChecked() ? "5" : "-1");

        form.setWfa20206(bi.wfa20206.isChecked() ? "6" : "-1");

        form.setWfa20296m(bi.wfa20296m.isChecked() ? "96" : "-1");

        form.setWfa20296mx(bi.wfa20296mx.getText().toString());
        form.setWfa20207(bi.wfa20207.isChecked() ? "7" : "-1");

        form.setWfa20208(bi.wfa20208.isChecked() ? "8" : "-1");

        form.setWfa20296b(bi.wfa20296b.isChecked() ? "96" : "-1");

        form.setWfa20296bx(bi.wfa20296bx.getText().toString());
        form.setWfa203( bi.wfa20301.isChecked() ? "1"
                : bi.wfa20302.isChecked() ? "2"
                : bi.wfa20303.isChecked() ? "3"
                :  "-1");

        form.setWfa204( bi.wfa20401.isChecked() ? "1"
                : bi.wfa20402.isChecked() ? "2"
                :  "-1");

        form.setWfa205( bi.wfa20501.isChecked() ? "1"
                : bi.wfa20502.isChecked() ? "2"
                : bi.wfa20503.isChecked() ? "3"
                : bi.wfa20596.isChecked() ? "96"
                :  "-1");

        form.setWfa20596x(bi.wfa20596x.getText().toString());
        form.setWfa20601(bi.wfa20601.isChecked() ? "1" : "-1");

        form.setWfa20602(bi.wfa20602.isChecked() ? "2" : "-1");

        form.setWfa20603(bi.wfa20603.isChecked() ? "3" : "-1");

        form.setWfa20604(bi.wfa20604.isChecked() ? "4" : "-1");

        form.setWfa20605(bi.wfa20605.isChecked() ? "5" : "-1");

        form.setWfa20606(bi.wfa20606.isChecked() ? "6" : "-1");

        form.setWfa20607(bi.wfa20607.isChecked() ? "7" : "-1");

        form.setWfa20608(bi.wfa20608.isChecked() ? "8" : "-1");

        form.setWfa20609(bi.wfa20609.isChecked() ? "9" : "-1");

        form.setWfa20696(bi.wfa20696.isChecked() ? "96" : "-1");

        form.setWfa20696x(bi.wfa20696x.getText().toString());*/

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}