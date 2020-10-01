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
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionKBinding;
import edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.blf_screening.CONSTANTS.FSTATUS_END_FLAG;
import static edu.aku.hassannaqvi.blf_screening.core.MainApp.form;
import static edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt.contextBackActivity;

public class SectionKActivity extends AppCompatActivity {

    ActivitySectionKBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_k);
        bi.setCallback(this);
        bi.setForm(form);
        setupSkips();
    }


    private void setupSkips() {

        bi.k01.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.k0104.getId() || i == bi.k0102.getId()) {
                Clear.clearAllFields(bi.fldGrpCVk02);
            }
        }));

        bi.k03.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.k0302.getId() || i == bi.k0304.getId()) {
                Clear.clearAllFields(bi.fldGrpSecK01);
            }
        }));

        bi.k06.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.k0602.getId()) {
                Clear.clearAllFields(bi.fldGrpCVk07);
            }
        }));

        bi.k08.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.k0802.getId()) {
                Clear.clearAllFields(bi.fldGrpCVk09);
            }
        }));

        bi.k10.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.k1002.getId()) {
                Clear.clearAllFields(bi.fldGrpCVk11);
            }
        }));

        bi.k12.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.k1202.getId()) {
                Clear.clearAllFields(bi.fldGrpCVk13);
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
            startActivity(new Intent(this, SectionLActivity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }


    public void BtnEnd() {
        AppUtilsKt.openFormEndActivity(this, FSTATUS_END_FLAG, 2);
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SK, form.sKtoString());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        // Checked
        form.setK01(bi.k0101.isChecked() ? "1"
                : bi.k0102.isChecked() ? "2"
                : bi.k0103.isChecked() ? "3"
                : bi.k0104.isChecked() ? "4"
                : "-1");

        // Checked
        form.setK02(bi.k0201.isChecked() ? "1"
                : bi.k0202.isChecked() ? "2"
                : "-1");

        // Checked
        form.setK03(bi.k0301.isChecked() ? "1"
                : bi.k0302.isChecked() ? "2"
                : bi.k0303.isChecked() ? "3"
                : bi.k0304.isChecked() ? "4"
                : "-1");

        // Checked
        form.setK04(bi.k0401.isChecked() ? "1"
                : bi.k0402.isChecked() ? "2"
                : "-1");

        // Checked
        form.setK05(bi.k0501.isChecked() ? "1"
                : bi.k0502.isChecked() ? "2"
                : bi.k0503.isChecked() ? "3"
                : bi.k0504.isChecked() ? "4"
                : bi.k0505.isChecked() ? "5"
                : bi.k0506.isChecked() ? "6"
                : bi.k05096.isChecked() ? "96"
                : "-1");
        // Checked
        form.setk05096x(bi.k05096x.getText().toString());

        form.setK06(bi.k0601.isChecked() ? "1"
                : bi.k0602.isChecked() ? "2"
                : "-1");

        form.setK07(bi.k0701.isChecked() ? "1"
                : bi.k0702.isChecked() ? "2"
                : bi.k0703.isChecked() ? "3"
                : bi.k0704.isChecked() ? "4"
                : "-1");

        form.setK08(bi.k0801.isChecked() ? "1"
                : bi.k0802.isChecked() ? "2"
                : "-1");

        form.setK09(bi.k0901.isChecked() ? "1"
                : bi.k0902.isChecked() ? "2"
                : "-1");

        form.setK10(bi.k1001.isChecked() ? "1"
                : bi.k1002.isChecked() ? "2"
                : "-1");

        form.setK11(bi.k1101.isChecked() ? "1"
                : bi.k1102.isChecked() ? "2"
                : "-1");

        form.setK12(bi.k1201.isChecked() ? "1"
                : bi.k1202.isChecked() ? "2"
                : "-1");

        form.setK13(bi.k1301.isChecked() ? "1"
                : bi.k1302.isChecked() ? "2"
                : "-1");

        form.setK14(bi.k1401.isChecked() ? "1"
                : bi.k1402.isChecked() ? "2"
                : bi.k1403.isChecked() ? "3"
                : bi.k14096.isChecked() ? "96"
                : "-1");
        form.setk14096x(bi.k14096x.getText().toString());

        form.setK15(bi.k1501.isChecked() ? "1"
                : bi.k1502.isChecked() ? "2"
                : "-1");

        form.setK16(bi.k1601.isChecked() ? "1"
                : bi.k1602.isChecked() ? "2"
                : bi.k16096.isChecked() ? "96"
                : "-1");
        form.setk16096x(bi.k16096x.getText().toString());

        form.setK17(bi.k1701.isChecked() ? "1"
                : bi.k1702.isChecked() ? "2"
                : bi.k1703.isChecked() ? "3"
                : "-1");

       /* JSONObject json = new JSONObject();

        json.put("k01", bi.k0101.isChecked() ? "1"
                : bi.k0102.isChecked() ? "2"
                : bi.k0103.isChecked() ? "3"
                : bi.k0104.isChecked() ? "4"
                : "-1");

        json.put("k02", bi.k0201.isChecked() ? "1"
                : bi.k0202.isChecked() ? "2"
                : "-1");

        json.put("k03", bi.k0301.isChecked() ? "1"
                : bi.k0302.isChecked() ? "2"
                : bi.k0303.isChecked() ? "3"
                : bi.k0304.isChecked() ? "4"
                : "-1");

        json.put("k04", bi.k0401.isChecked() ? "1"
                : bi.k0402.isChecked() ? "2"
                : "-1");

        json.put("k05", bi.k0501.isChecked() ? "1"
                : bi.k0502.isChecked() ? "2"
                : bi.k0503.isChecked() ? "3"
                : bi.k0504.isChecked() ? "4"
                : bi.k0505.isChecked() ? "5"
                : bi.k0506.isChecked() ? "6"
                : bi.k05096.isChecked() ? "96"
                : "-1");

        json.put("k06", bi.k0601.isChecked() ? "1"
                : bi.k0602.isChecked() ? "2"
                : "-1");

        json.put("k07", bi.k0701.isChecked() ? "1"
                : bi.k0702.isChecked() ? "2"
                : bi.k0703.isChecked() ? "3"
                : bi.k0704.isChecked() ? "4"
                : "-1");

        json.put("k08", bi.k0801.isChecked() ? "1"
                : bi.k0802.isChecked() ? "2"
                : "-1");

        json.put("k09", bi.k0901.isChecked() ? "1"
                : bi.k0902.isChecked() ? "2"
                : "-1");

        json.put("k10", bi.k1001.isChecked() ? "1"
                : bi.k1002.isChecked() ? "2"
                : "-1");

        json.put("k11", bi.k1101.isChecked() ? "1"
                : bi.k1102.isChecked() ? "2"
                : "-1");

        json.put("k12", bi.k1201.isChecked() ? "1"
                : bi.k1202.isChecked() ? "2"
                : "-1");

        json.put("k13", bi.k1301.isChecked() ? "1"
                : bi.k1302.isChecked() ? "2"
                : "-1");

        json.put("k14", bi.k1401.isChecked() ? "1"
                : bi.k1402.isChecked() ? "2"
                : bi.k1403.isChecked() ? "3"
                : bi.k14096.isChecked() ? "96"
                : "-1");

        json.put("k15", bi.k1501.isChecked() ? "1"
                : bi.k1502.isChecked() ? "2"
                : "-1");

        json.put("k16", bi.k1601.isChecked() ? "1"
                : bi.k1602.isChecked() ? "2"
                : bi.k16096.isChecked() ? "96"
                : "-1");

        json.put("k17", bi.k1701.isChecked() ? "1"
                : bi.k1702.isChecked() ? "2"
                : bi.k1703.isChecked() ? "3"
                : "-1");

        MainApp.form.setsK(json.toString());*/

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionK);

    }


    @Override
    public void onBackPressed() {
        contextBackActivity(this);
    }
}