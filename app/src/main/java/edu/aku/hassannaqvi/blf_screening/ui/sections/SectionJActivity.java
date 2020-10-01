package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionJBinding;
import edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.blf_screening.CONSTANTS.FSTATUS_END_FLAG;
import static edu.aku.hassannaqvi.blf_screening.core.MainApp.form;
import static edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt.contextBackActivity;

public class SectionJActivity extends AppCompatActivity {

    ActivitySectionJBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_j);
        bi.setCallback(this);
        bi.setForm(form);
        setupSkips();
    }


    private void setupSkips() {

        bi.j01.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.j0104.getId()) {
                Clear.clearAllFields(bi.fldGrpCVj02);
            }
        }));

        bi.j03.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.j0302.getId()) {
                Clear.clearAllFields(bi.fldGrpCVj04);
            }
        }));

        bi.j06.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.j0602.getId() || i == bi.j0604.getId()) {
                Clear.clearAllFields(bi.fldGrpSecj01);
            }
        }));

        bi.j010.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.j01002.getId()) {
                Clear.clearAllFields(bi.fldGrpSecj02);
            }
        }));

    }


    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, SectionKActivity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }


    public void BtnEnd() {
        AppUtilsKt.openFormEndActivity(this, FSTATUS_END_FLAG, 2);
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SJ, form.sJtoString());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        form.setJ01(bi.j0101.isChecked() ? "1"
                : bi.j0102.isChecked() ? "2"
                : bi.j0103.isChecked() ? "3"
                : bi.j0104.isChecked() ? "4"
                : "-1");

        form.setJ02(bi.j0201.isChecked() ? "1"
                : bi.j0202.isChecked() ? "2"
                : "-1");

        form.setJ03(bi.j0301.isChecked() ? "1"
                : bi.j0302.isChecked() ? "2"
                : "-1");

        form.setJ04(bi.j0401.isChecked() ? "1"
                : bi.j0402.isChecked() ? "2"
                : "-1");

        form.setJ05(bi.j05.getText().toString());

        form.setJ06(bi.j0601.isChecked() ? "1"
                : bi.j0602.isChecked() ? "2"
                : bi.j0603.isChecked() ? "3"
                : bi.j0604.isChecked() ? "4"
                : "-1");

        form.setJ07(bi.j0701.isChecked() ? "1"
                : bi.j0702.isChecked() ? "2"
                : "-1");

        form.setJ0801(bi.j0801.isChecked() ? "1" : "-1");
        form.setJ0802(bi.j0802.isChecked() ? "2" : "-1");
        form.setJ0803(bi.j0803.isChecked() ? "3" : "-1");
        form.setJ0804(bi.j0804.isChecked() ? "4" : "-1");
        form.setJ0805(bi.j0805.isChecked() ? "5" : "-1");
        form.setJ0806(bi.j0806.isChecked() ? "6" : "-1");
        form.setJ08096(bi.j08096.isChecked() ? "96" : "-1");
        form.setJ08096x(bi.j08096x.getText().toString());

        form.setJ09(bi.j0901.isChecked() ? "1"
                : bi.j0902.isChecked() ? "2"
                : bi.j0903.isChecked() ? "3"
                : bi.j09096.isChecked() ? "96"
                : "-1");
        form.setJ09096x(bi.j09096x.getText().toString());

        form.setJ010(bi.j01001.isChecked() ? "1"
                : bi.j01002.isChecked() ? "2"
                : "-1");

        form.setJ011(bi.j01101.isChecked() ? "1"
                : bi.j01102.isChecked() ? "2"
                : "-1");

        form.setJ012(bi.j012.getText().toString());

    /*    JSONObject json = new JSONObject();

        json.put("j01", bi.j0101.isChecked() ? "1"
                : bi.j0102.isChecked() ? "2"
                : bi.j0103.isChecked() ? "3"
                : bi.j0104.isChecked() ? "4"
                : "-1");

        json.put("j02", bi.j0201.isChecked() ? "1"
                : bi.j0202.isChecked() ? "2"
                : "-1");

        json.put("j03", bi.j0301.isChecked() ? "1"
                : bi.j0302.isChecked() ? "2"
                : "-1");

        json.put("j04", bi.j0401.isChecked() ? "1"
                : bi.j0402.isChecked() ? "2"
                : "-1");

       // json.put("j05", bi.j05.getText().toString());

        json.put("j06", bi.j0601.isChecked() ? "1"
                : bi.j0602.isChecked() ? "2"
                : bi.j0603.isChecked() ? "3"
                : bi.j0604.isChecked() ? "4"
                : "-1");

        json.put("j07", bi.j0701.isChecked() ? "1"
                : bi.j0702.isChecked() ? "2"
                : "-1");

        json.put("j09", bi.j0901.isChecked() ? "1"
                : bi.j0902.isChecked() ? "2"
                : bi.j0903.isChecked() ? "3"
                : bi.j09096.isChecked() ? "96"
                : "-1");
        json.put("j09096x", bi.j09096x.getText().toString());

        json.put("j010", bi.j01001.isChecked() ? "1"
                : bi.j01002.isChecked() ? "2"
                : "-1");

        json.put("j011", bi.j01101.isChecked() ? "1"
                : bi.j01102.isChecked() ? "2"
                : "-1");

        json.put("j012", bi.j012.getText().toString());


        MainApp.form.setsJ(json.toString());*/

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionJ);
    }


    @Override
    public void onBackPressed() {
        contextBackActivity(this);
    }

}