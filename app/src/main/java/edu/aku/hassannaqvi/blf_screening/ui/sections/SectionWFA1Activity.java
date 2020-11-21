package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.aku.hassannaqvi.blf_screening.R;

public class SectionWFA1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_w_f_a1);
    }

    private void setupSkips() {

        /* *//* bi.wfc101.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != bi.wfc10102.getId()) {
                Clear.clearAllFields(bi.llgrpsec1101);
            }*//*
        });
*/
    }


    /*public void BtnContinue() {
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
    }*/


   /* public void BtnEnd() {
        oF = new Intent(this, MainActivity.class);
        startActivity(oF);
    }
*/

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


       /* form.setWfa101(bi.wfa101.getText().toString());

        form.setWfa102(bi.wfa102.getText().toString());

        form.setWfa103(bi.wfa103.getText().toString());

        form.setWfa10401(bi.wfa10401.getText().toString());
        form.setWfa10402(bi.wfa10402.getText().toString());
        form.setWfa10403(bi.wfa10403.getText().toString());
        form.setWfa10404(bi.wfa10404.getText().toString());
        form.setWfa10405(bi.wfa10405.getText().toString());
        form.setWfa105( bi.wfa10501.isChecked() ? "1"
                : bi.wfa10502.isChecked() ? "2"
                : bi.wfa10503.isChecked() ? "3"
                : bi.wfa10504.isChecked() ? "4"
                : bi.wfa10505.isChecked() ? "5"
                : bi.wfa10506.isChecked() ? "6"
                : bi.wfa10507.isChecked() ? "7"
                : bi.wfa10508.isChecked() ? "8"
                : bi.wfa10509.isChecked() ? "9"
                : bi.wfa10510.isChecked() ? "10"
                : bi.wfa10511.isChecked() ? "11"
                : bi.wfa10512.isChecked() ? "12"
                : bi.wfa10513.isChecked() ? "13"
                :  "-1");

        form.setWfa106( bi.wfa10601.isChecked() ? "1"
                : bi.wfa10602.isChecked() ? "2"
                : bi.wfa10696.isChecked() ? "96"
                :  "-1");

        form.setWfa10696x(bi.wfa10696x.getText().toString());
        form.setWfa107( bi.wfa10701.isChecked() ? "1"
                : bi.wfa10702.isChecked() ? "2"
                :  "-1");

        form.setWfa108( bi.wfa108.isChecked() ? "1"
                : bi.wfa108.isChecked() ? "2"
                :  "-1");

        form.setWfa109( bi.wfa10901.isChecked() ? "1"
                : bi.wfa10902.isChecked() ? "2"
                : bi.wfa10903.isChecked() ? "3"
                : bi.wfa10904.isChecked() ? "4"
                : bi.wfa10905.isChecked() ? "5"
                : bi.wfa10906.isChecked() ? "6"
                : bi.wfa10907.isChecked() ? "7"
                : bi.wfa10908.isChecked() ? "8"
                : bi.wfa10998.isChecked() ? "98"
                : bi.wfa10996.isChecked() ? "96"
                :  "-1");

        form.setWfa10996x(bi.wfa10996x.getText().toString());
        form.setWfa11001(bi.wfa11001.getText().toString());
        form.setWfa11002(bi.wfa11002.getText().toString());
        form.setWfa11003(bi.wfa11003.getText().toString());
        form.setWfa11004(bi.wfa11004.getText().toString());
        form.setWfa11005(bi.wfa11005.getText().toString());
        form.setWfa111( bi.wfa11101.isChecked() ? "1"
                : bi.wfa11102.isChecked() ? "2"
                : bi.wfa11103.isChecked() ? "3"
                :  "-1");


*/
    }


 /*   private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }
*/

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }


}