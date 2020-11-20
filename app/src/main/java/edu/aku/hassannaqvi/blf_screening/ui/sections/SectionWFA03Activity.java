package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionWfa03Binding;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;

public class SectionWFA03Activity extends AppCompatActivity {

    ActivitySectionWfa03Binding bi;
    Intent oF = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_wfa03);
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
                startActivity(new Intent(this, SectionWFA04Activity.class));
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

        /*form.setWfa301( bi.wfa30101.isChecked() ? "1"
                : bi.wfa30102.isChecked() ? "2"
                :  "-1");

        form.setWfa302(bi.wfa302.getText().toString());

        form.setWfa30301(bi.wfa30301.getText().toString());
        form.setWfa30302(bi.wfa30302.getText().toString());
        form.setWfa30303(bi.wfa30303.getText().toString());
        form.setWfa304( bi.wfa30401.isChecked() ? "1"
                : bi.wfa30402.isChecked() ? "2"
                :  "-1");

        form.setWfa305(bi.wfa305.getText().toString());

        form.setWfa30601(bi.wfa30601.getText().toString());
        form.setWfa30602(bi.wfa30602.getText().toString());
        form.setWfa30603(bi.wfa30603.getText().toString());
        form.setWfa307( bi.wfa30701.isChecked() ? "1"
                : bi.wfa30702.isChecked() ? "2"
                :  "-1");

        form.setWfa308(bi.wfa308.getText().toString());

        form.setWfa30901(bi.wfa30901.getText().toString());
        form.setWfa30902(bi.wfa30902.getText().toString());
        form.setWfa310( bi.wfa31001.isChecked() ? "1"
                : bi.wfa31002.isChecked() ? "2"
                :  "-1");

        form.setWfa311(bi.wfa311.getText().toString());

        form.setWfa31201(bi.wfa31201.getText().toString());
        form.setWfa31202(bi.wfa31202.getText().toString());
        form.setWfa31203(bi.wfa31203.getText().toString());
        form.setWfa313( bi.wfa31301.isChecked() ? "1"
                : bi.wfa31302.isChecked() ? "2"
                :  "-1");

        form.setWfa314(bi.wfa314.getText().toString());

        form.setWfa31501(bi.wfa31501.getText().toString());
        form.setWfa31502(bi.wfa31502.getText().toString());
        form.setWfa316( bi.wfa31601.isChecked() ? "1"
                : bi.wfa31602.isChecked() ? "2"
                :  "-1");

        form.setWfa317(bi.wfa317.getText().toString());

        form.setWfa318(bi.wfa318.getText().toString());

        form.setWfa319( bi.wfa31901.isChecked() ? "1"
                : bi.wfa31902.isChecked() ? "2"
                :  "-1");

        form.setWfa320(bi.wfa320.getText().toString());

        form.setWfa321(bi.wfa321.getText().toString());

        form.setWfa322( bi.wfa32201.isChecked() ? "1"
                : bi.wfa32202.isChecked() ? "2"
                :  "-1");

        form.setWfa32301(bi.wfa32301.getText().toString());
        form.setWfa32302(bi.wfa32302.getText().toString());
        form.setWfa32303(bi.wfa32303.getText().toString());
        form.setWfa324( bi.wfa32401.isChecked() ? "1"
                : bi.wfa32402.isChecked() ? "2"
                :  "-1");

        form.setWfa325(bi.wfa325.getText().toString());

        form.setWfa32601(bi.wfa32601.getText().toString());
        form.setWfa32602(bi.wfa32602.getText().toString());
        form.setWfa327( bi.wfa32701.isChecked() ? "1"
                : bi.wfa32702.isChecked() ? "2"
                :  "-1");

        form.setWfa328(bi.wfa328.getText().toString());

        form.setWfa329( bi.wfa32901.isChecked() ? "1"
                : bi.wfa32902.isChecked() ? "2"
                :  "-1");

        form.setWfa330( bi.wfa33001.isChecked() ? "1"
                : bi.wfa33002.isChecked() ? "2"
                : bi.wfa33003.isChecked() ? "3"
                :  "-1");

        form.setWfa331(bi.wfa331.getText().toString());

        form.setWfa33201(bi.wfa33201.getText().toString());
        form.setWfa33202(bi.wfa33202.getText().toString());
        form.setWfa333( bi.wfa33301.isChecked() ? "1"
                : bi.wfa33302.isChecked() ? "2"
                :  "-1");

        form.setWfa334(bi.wfa334.getText().toString());

        form.setWfa33501(bi.wfa33501.getText().toString());
        form.setWfa33502(bi.wfa33502.getText().toString());
        form.setWfa336( bi.wfa33601.isChecked() ? "1"
                : bi.wfa33602.isChecked() ? "2"
                :  "-1");

        form.setWfa337(bi.wfa337.getText().toString());

        form.setWfa338(bi.wfa338.getText().toString());

        form.setWfa339(bi.wfa339.getText().toString());*/

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}