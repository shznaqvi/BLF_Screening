package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsWFContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionWfdBinding;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;

public class SectionWFDActivity extends AppCompatActivity {

    ActivitySectionWfdBinding bi;
    Intent oF = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_wfd);
        bi.setCallback(this);
        setupSkips();
    }

    private void setupSkips() {

        bi.wfd101.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.wfd10102.getId()) {
                Clear.clearAllFields(bi.wfdGrpCVwfd101);
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
                startActivity(new Intent(this, SectionWFEActivity.class));
            }
        }
    }


    public void BtnEnd() {
        oF = new Intent(this, MainActivity.class);
        startActivity(oF);
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormsWFColumn(FormsWFContract.FormsWFTable.COLUMN_SWF, MainApp.formsWF.sWFtoString());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }


    private void SaveDraft() {

        MainApp.formsWF.setWfd101(bi.wfd10101.isChecked() ? "1"
                : bi.wfd10102.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfd10102x(bi.wfd10102x.getText().toString().trim().isEmpty() ? "-1" : bi.wfd10102x.getText().toString());
        MainApp.formsWF.setWfd102a01(bi.wfd102a01.getText().toString().trim().isEmpty() ? "-1" : bi.wfd102a01.getText().toString());
        MainApp.formsWF.setWfd102b01(bi.wfd102b01.getText().toString().trim().isEmpty() ? "-1" : bi.wfd102b01.getText().toString());
        MainApp.formsWF.setWfd102c01(bi.wfd102c01.getText().toString().trim().isEmpty() ? "-1" : bi.wfd102c01.getText().toString());
        MainApp.formsWF.setWfd102d01(bi.wfd102d01.getText().toString().trim().isEmpty() ? "-1" : bi.wfd102d01.getText().toString());
        MainApp.formsWF.setWfd102e01(bi.wfd102e01.getText().toString().trim().isEmpty() ? "-1" : bi.wfd102e01.getText().toString());
        MainApp.formsWF.setWfd102f01(bi.wfd102f01.getText().toString().trim().isEmpty() ? "-1" : bi.wfd102f01.getText().toString());
        MainApp.formsWF.setWfd102g01(bi.wfd102g01.getText().toString().trim().isEmpty() ? "-1" : bi.wfd102g01.getText().toString());
        MainApp.formsWF.setWfd102h01(bi.wfd102h01.getText().toString().trim().isEmpty() ? "-1" : bi.wfd102h01.getText().toString());
        MainApp.formsWF.setWfd102i01(bi.wfd102i01.getText().toString().trim().isEmpty() ? "-1" : bi.wfd102i01.getText().toString());
        MainApp.formsWF.setWfd102j01(bi.wfd102j01.getText().toString().trim().isEmpty() ? "-1" : bi.wfd102j01.getText().toString());
        MainApp.formsWF.setWfd102k01(bi.wfd102k01.getText().toString().trim().isEmpty() ? "-1" : bi.wfd102k01.getText().toString());
        MainApp.formsWF.setWfd102l01(bi.wfd102l01.getText().toString().trim().isEmpty() ? "-1" : bi.wfd102l01.getText().toString());
        MainApp.formsWF.setWfd102m01(bi.wfd102m01.getText().toString().trim().isEmpty() ? "-1" : bi.wfd102m01.getText().toString());
        MainApp.formsWF.setWfd102n01(bi.wfd102n01.getText().toString().trim().isEmpty() ? "-1" : bi.wfd102n01.getText().toString());
        MainApp.formsWF.setWfd102o01(bi.wfd102o01.getText().toString().trim().isEmpty() ? "-1" : bi.wfd102o01.getText().toString());

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}