package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionLBinding;
import edu.aku.hassannaqvi.blf_screening.ui.other.EndingActivity;
import edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.blf_screening.CONSTANTS.FSTATUS_END_FLAG;
import static edu.aku.hassannaqvi.blf_screening.core.MainApp.form;
import static edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt.contextBackActivity;

public class SectionLActivity extends AppCompatActivity {

    ActivitySectionLBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_l);
        bi.setCallback(this);
        bi.setForm(form);
        setupSkips();
    }


    private void setupSkips() {

        bi.l01.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.l0102.getId()) {
                Clear.clearAllFields(bi.fldGrpSecL01);
            }
        }));

        bi.l09.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.l0902.getId()) {
                Clear.clearAllFields(bi.fldGrpCVl010);
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
            finish();
            startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }


    public void BtnEnd() {
        AppUtilsKt.openFormEndActivity(this, FSTATUS_END_FLAG, 2);
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SL, form.sLtoString());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        // Checked
        form.setL01(bi.l0101.isChecked() ? "1"
                : bi.l0102.isChecked() ? "2"
                : "-1");

        // Checked
        form.setL02(bi.l0201.isChecked() ? "1"
                : bi.l0202.isChecked() ? "2"
                : bi.l0203.isChecked() ? "3"
                : bi.l0204.isChecked() ? "4"
                : bi.l0205.isChecked() ? "5"
                : bi.l0206.isChecked() ? "6"
                : bi.l02096.isChecked() ? "96"
                : "-1");
        // Checked
        form.setl02096x(bi.l02096x.getText().toString());

        // Checked
        form.setL03(bi.l0301.isChecked() ? "1"
                : bi.l0302.isChecked() ? "2"
                : bi.l0303.isChecked() ? "3"
                : bi.l0304.isChecked() ? "4"
                : bi.l0305.isChecked() ? "5"
                : "-1");

        // Checked
        form.setL04(bi.l0401.isChecked() ? "1"
                : bi.l0402.isChecked() ? "2"
                : bi.l0403.isChecked() ? "3"
                : bi.l0404.isChecked() ? "4"
                : bi.l0405.isChecked() ? "5"
                : "-1");

        // Checked
        form.setL05(bi.l0501.isChecked() ? "1"
                : bi.l0502.isChecked() ? "2"
                : "-1");

        // Checked
        form.setL06(bi.l0601.isChecked() ? "1"
                : bi.l0602.isChecked() ? "2"
                : "-1");

        // Checked
        form.setL07(bi.l0701.isChecked() ? "1"
                : bi.l0702.isChecked() ? "2"
                : "-1");

        // Checked
        form.setL08(bi.l0801.isChecked() ? "1"
                : bi.l0802.isChecked() ? "2"
                : "-1");

        // Checked
        form.setL09(bi.l0901.isChecked() ? "1"
                : bi.l0902.isChecked() ? "2"
                : "-1");

        // Checked
        form.setL010(bi.l01001.isChecked() ? "1"
                : bi.l01002.isChecked() ? "2"
                : bi.l01003.isChecked() ? "3"
                : "-1");


        /*JSONObject json = new JSONObject();

        json.put("l01", bi.l0101.isChecked() ? "1"
                : bi.l0102.isChecked() ? "2"
                : "-1");

        json.put("l02", bi.l0201.isChecked() ? "1"
                : bi.l0202.isChecked() ? "2"
                : bi.l0203.isChecked() ? "3"
                : bi.l0204.isChecked() ? "4"
                : bi.l0205.isChecked() ? "5"
                : bi.l0206.isChecked() ? "6"
                : bi.l02096.isChecked() ? "96"
                : "-1");

        json.put("l03", bi.l0301.isChecked() ? "1"
                : bi.l0302.isChecked() ? "2"
                : bi.l0303.isChecked() ? "3"
                : bi.l0304.isChecked() ? "4"
                : bi.l0305.isChecked() ? "5"
                : "-1");

        json.put("l04", bi.l0401.isChecked() ? "1"
                : bi.l0402.isChecked() ? "2"
                : bi.l0403.isChecked() ? "3"
                : bi.l0404.isChecked() ? "4"
                : bi.l0405.isChecked() ? "5"
                : "-1");

        json.put("l05", bi.l0501.isChecked() ? "1"
                : bi.l0502.isChecked() ? "2"
                : "-1");

        json.put("l06", bi.l0601.isChecked() ? "1"
                : bi.l0602.isChecked() ? "2"
                : "-1");

        json.put("l07", bi.l0701.isChecked() ? "1"
                : bi.l0702.isChecked() ? "2"
                : "-1");

        json.put("l08", bi.l0801.isChecked() ? "1"
                : bi.l0802.isChecked() ? "2"
                : "-1");

        json.put("l09", bi.l0901.isChecked() ? "1"
                : bi.l0902.isChecked() ? "2"
                : "-1");

        json.put("l010", bi.l01001.isChecked() ? "1"
                : bi.l01002.isChecked() ? "2"
                : bi.l01003.isChecked() ? "3"
                : "-1");

        MainApp.form.setsL(json.toString());*/

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionL);

    }

    @Override
    public void onBackPressed() {
        contextBackActivity(this);
    }
}