package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionGBinding;
import edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.blf_screening.CONSTANTS.FSTATUS_END_FLAG;
import static edu.aku.hassannaqvi.blf_screening.core.MainApp.form;
import static edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt.contextBackActivity;

public class SectionGActivity extends AppCompatActivity {

    ActivitySectionGBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_g);
        bi.setCallback(this);
        bi.setForm(form);
        setupSkips();
    }

    private void setupSkips() {

        bi.g01.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.g0102.getId()) {
                Clear.clearAllFields(bi.fldGrpCVllg01);
            }
        }));

        bi.g02.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.g0202.getId()) {
                Clear.clearAllFields(bi.fldGrpCVllg02);
            }
        }));

        bi.g03.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.g0301.getId()) {
                Clear.clearAllFields(bi.fldGrpCVg04);
            }
        }));

        bi.g06.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.g0601.getId()) {
                Clear.clearAllFields(bi.fldGrpCVg07);
            } else if (i == bi.g0602.getId()) {
                Clear.clearAllFields(bi.fldGrpCVg08);
            }
        }));

        bi.g09.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.g0902.getId()) {
                Clear.clearAllFields(bi.fldGrpCVllg03);
            }
        }));

        bi.g11.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.g1102.getId()) {
                Clear.clearAllFields(bi.fldGrpCVg12);
            } else {
                Clear.clearAllFields(bi.fldGrpCVg13);
            }
        }));
    }

    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SG, form.sGtoString());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
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
            startActivity(new Intent(this, SectionHActivity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }

    private void SaveDraft() throws JSONException {

        form.setG01(bi.g0101.isChecked() ? "1"
                : bi.g0102.isChecked() ? "2"
                : "-1");

        form.setG02(bi.g0201.isChecked() ? "1"
                : bi.g0202.isChecked() ? "2"
                : "-1");

        form.setG03(bi.g0301.isChecked() ? "1"
                : bi.g0302.isChecked() ? "2"
                : "-1");

        form.setG04(bi.g0401.isChecked() ? "1"
                : bi.g0402.isChecked() ? "2"
                : bi.g0403.isChecked() ? "3"
                : bi.g0404.isChecked() ? "4"
                : bi.g0405.isChecked() ? "5"
                : bi.g0406.isChecked() ? "6"
                : bi.g0407.isChecked() ? "7"
                : bi.g04096.isChecked() ? "96"
                : "-1");
        form.setG04096x(bi.g04096x.getText().toString());

        form.setG0501(bi.g0501.isChecked() ? "1" : "-1");
        form.setG0502(bi.g0502.isChecked() ? "2" : "-1");
        form.setG0503(bi.g0503.isChecked() ? "3" : "-1");
        form.setG0503a(bi.g0503a.isChecked() ? "3a" : "-1");
        form.setG0504(bi.g0504.isChecked() ? "4" : "-1");
        form.setG0505(bi.g0505.isChecked() ? "5" : "-1");
        form.setG0506(bi.g0506.isChecked() ? "6" : "-1");
        form.setG0506a(bi.g0506a.isChecked() ? "6a" : "-1");
        form.setG0507(bi.g0507.isChecked() ? "7" : "-1");
        form.setG0508(bi.g0508.isChecked() ? "8" : "-1");
        form.setG0509(bi.g0509.isChecked() ? "9" : "-1");
        form.setG0509a(bi.g0509a.isChecked() ? "9a" : "-1");
        form.setG0510(bi.g0510.isChecked() ? "10" : "-1");
        form.setG0511(bi.g0511.isChecked() ? "11" : "-1");
        form.setG0512(bi.g0512.isChecked() ? "12" : "-1");
        form.setG0513(bi.g0513.isChecked() ? "13" : "-1");
        form.setG0514(bi.g0514.isChecked() ? "14" : "-1");


        form.setG06(bi.g0601.isChecked() ? "1"
                : bi.g0602.isChecked() ? "2"
                : "-1");

        form.setG0701(bi.g0701.isChecked() ? "1" : "-1");
        form.setG0702(bi.g0702.isChecked() ? "2" : "-1");
        form.setG0703(bi.g0703.isChecked() ? "3" : "-1");
        form.setG0704(bi.g0704.isChecked() ? "4" : "-1");
        form.setG0705(bi.g0705.isChecked() ? "5" : "-1");
        form.setG0706(bi.g0706.isChecked() ? "6" : "-1");
        form.setG0707(bi.g0707.isChecked() ? "7" : "-1");

        form.setG0801(bi.g0801.isChecked() ? "1" : "-1");
        form.setG0802(bi.g0802.isChecked() ? "2" : "-1");
        form.setG0803(bi.g0803.isChecked() ? "3" : "-1");
        form.setG0804(bi.g0804.isChecked() ? "4" : "-1");
        form.setG0805(bi.g0805.isChecked() ? "5" : "-1");
        form.setG0806(bi.g0806.isChecked() ? "6" : "-1");
        form.setG0807(bi.g0807.isChecked() ? "7" : "-1");
        form.setG0808(bi.g0808.isChecked() ? "8" : "-1");
        form.setG0809(bi.g0809.isChecked() ? "9" : "-1");
        form.setG08096(bi.g08096.isChecked() ? "96" : "-1");
        form.setG08096x(bi.g08096x.getText().toString());

        form.setG09(bi.g0901.isChecked() ? "1"
                : bi.g0902.isChecked() ? "2"
                : "-1");

        form.setG10(bi.g10.getText().toString());

        form.setG11(bi.g1101.isChecked() ? "1"
                : bi.g1102.isChecked() ? "2"
                : "-1");

        form.setG12(bi.g1201.isChecked() ? "1"
                : bi.g1202.isChecked() ? "2"
                : bi.g1203.isChecked() ? "3"
                : bi.g1204.isChecked() ? "4"
                : bi.g1205.isChecked() ? "5"
                : bi.g12096.isChecked() ? "96"
                : "-1");
        form.setG12096x(bi.g12096x.getText().toString());

        form.setG13(bi.g1301.isChecked() ? "1"
                : bi.g1302.isChecked() ? "2"
                : bi.g1303.isChecked() ? "3"
                : bi.g1304.isChecked() ? "4"
                : bi.g1305.isChecked() ? "5"
                : bi.g1306.isChecked() ? "6"
                : bi.g13096.isChecked() ? "96"
                : "-1");
        form.setG13096x(bi.g13096x.getText().toString());

        /*JSONObject json = new JSONObject();

        json.put("g01", bi.g0101.isChecked() ? "1"
                : bi.g0102.isChecked() ? "2"
                : "-1");

        json.put("g02", bi.g0201.isChecked() ? "1"
                : bi.g0202.isChecked() ? "2"
                : "-1");

        json.put("g03", bi.g0301.isChecked() ? "1"
                : bi.g0302.isChecked() ? "2"
                : "-1");

        json.put("g04", bi.g0401.isChecked() ? "1"
                : bi.g0402.isChecked() ? "2"
                : bi.g0403.isChecked() ? "3"
                : bi.g0404.isChecked() ? "4"
                : bi.g0405.isChecked() ? "5"
                : bi.g0406.isChecked() ? "6"
                : bi.g0407.isChecked() ? "7"
                //        : bi.g04096.isChecked() ? "96"
                : "-1");

        //    json.put("g04096x", bi.g04096x.getText().toString());
        json.put("g06", bi.g0601.isChecked() ? "1"
                : bi.g0602.isChecked() ? "2"
                : "-1");

        json.put("g09", bi.g0901.isChecked() ? "1"
                : bi.g0902.isChecked() ? "2"
                : "-1");

        json.put("g10", bi.g10.getText().toString());

        json.put("g11", bi.g1101.isChecked() ? "1"
                : bi.g1102.isChecked() ? "2"
                : "-1");

        json.put("g12", bi.g1201.isChecked() ? "1"
                : bi.g1202.isChecked() ? "2"
                : bi.g1203.isChecked() ? "3"
                : bi.g1204.isChecked() ? "4"
                : bi.g1205.isChecked() ? "5"
                : bi.g12096.isChecked() ? "96"
                : "-1");

        json.put("g13", bi.g1301.isChecked() ? "1"
                : bi.g1302.isChecked() ? "2"
                : bi.g1303.isChecked() ? "3"
                : bi.g1304.isChecked() ? "4"
                : bi.g1305.isChecked() ? "5"
                : bi.g1306.isChecked() ? "6"
                : bi.g13096.isChecked() ? "96"
                : "-1");

        MainApp.form.setsG(json.toString());*/

    }

    public void BtnEnd() {
        AppUtilsKt.openFormEndActivity(this, FSTATUS_END_FLAG, 2);
    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    @Override
    public void onBackPressed() {
        contextBackActivity(this);
    }

    public void showTooltipView(View view) {
        AppUtilsKt.showTooltip(this, view);
    }

}