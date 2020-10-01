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
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionEBinding;
import edu.aku.hassannaqvi.blf_screening.models.SectionSelection;
import edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.blf_screening.CONSTANTS.FSTATUS_END_FLAG;
import static edu.aku.hassannaqvi.blf_screening.core.MainApp.appInfo;
import static edu.aku.hassannaqvi.blf_screening.core.MainApp.form;
import static edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt.contextBackActivity;

public class SectionEActivity extends AppCompatActivity {


    ActivitySectionEBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_e);
        bi.setCallback(this);
        bi.setForm(form);
        setupSkip();
    }


    private void setupSkip() {

        /*bi.e02.setOnCheckedChangeListener((group, checkId) -> {
                Clear.clearAllFields(bi.fldGrpCVe03);
        }));*/

        bi.e02.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i != bi.e0101.getId()) {
                Clear.clearAllFields(bi.fldGrpE1);
            }
        }));

        bi.e04.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i != bi.e0401.getId()) {
                Clear.clearAllFields(bi.fldGrpCVe05);
            }
        }));


        bi.e07.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i != bi.e0701.getId()) {
                Clear.clearAllFields(bi.fldGrpCVe08);
            }
        }));

        bi.e09.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i != bi.e0901.getId()) {
                Clear.clearAllFields(bi.fldGrpCVe10);
            }
        }));

        bi.e1106.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Clear.clearAllFields(bi.e11check, false);
            } else {
                Clear.clearAllFields(bi.e11check, true);
            }
        });

        //e09
        /*bi.e09.setOnCheckedChangeListener((group, checkId) -> {
            Clear.clearAllFields(bi.fldGrpCVe10);
        });*/

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

            //This section needs to fill: If A15 selected option is 2 AND A20 selected option is 2 AND A13 age criteria is 15-49 Years
            SectionSelection routeSection = form.getSecSelection();
            boolean routeFlag = (Integer.parseInt(routeSection.getA14()) >= 15 && Integer.parseInt(routeSection.getA14()) <= 49) && routeSection.getA15() && routeSection.getA20();
            startActivity(new Intent(this, routeFlag ? SectionFActivity.class : Integer.parseInt(routeSection.getA14()) < 10 ? SectionGActivity.class : SectionHActivity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }


    public void BtnEnd() {
        AppUtilsKt.openFormEndActivity(this, FSTATUS_END_FLAG, 2);
    }


    private boolean UpdateDB() {
        DatabaseHelper db = appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SE, form.sEtoString());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        form.setE01(bi.e0101.isChecked() ? "1"
                : bi.e0102.isChecked() ? "2"
                : bi.e0103.isChecked() ? "3"
                : bi.e0104.isChecked() ? "4"
                : "-1");

        form.setE02(bi.e0201.isChecked() ? "1"
                : bi.e0202.isChecked() ? "2"
                : "-1");

        //checkbox
        form.setE0301(bi.e0301.isChecked() ? "1" : "-1");
        form.setE0302(bi.e0302.isChecked() ? "2" : "-1");
        form.setE0303(bi.e0303.isChecked() ? "3" : "-1");
        form.setE0304(bi.e0304.isChecked() ? "4" : "-1");
        form.setE0305(bi.e0305.isChecked() ? "5" : "-1");
        form.setE0306(bi.e0306.isChecked() ? "6" : "-1");
        form.setE03096(bi.e03096.isChecked() ? "96" : "-1");

        form.setE03096x(bi.e03096x.getText().toString());

        //radiobutton
        form.setE04(bi.e0401.isChecked() ? "1"
                : bi.e0402.isChecked() ? "2"
                : "-1");

        //input
        form.setE05(bi.e05.getText().toString());

        form.setE06(bi.e0601.isChecked() ? "1"
                : bi.e0602.isChecked() ? "2"
                : bi.e0603.isChecked() ? "3"
                : bi.e0604.isChecked() ? "4"
                : "-1");

        form.setE07("-1");

        //radiobutton
        form.setE07(bi.e0701.isChecked() ? "1"
                : bi.e0702.isChecked() ? "2"
                : "-1");

        // checkbox
        form.setE0801(bi.e0801.isChecked() ? "1" : "-1");
        form.setE0802(bi.e0802.isChecked() ? "2" : "-1");
        form.setE0803(bi.e0803.isChecked() ? "3" : "-1");
        form.setE0804(bi.e0804.isChecked() ? "4" : "-1");
        form.setE0805(bi.e0805.isChecked() ? "5" : "-1");
        form.setE0806(bi.e0806.isChecked() ? "6" : "-1");
        form.setE08096(bi.e08096.isChecked() ? "96" : "-1");
        form.setE08096x(bi.e08096x.getText().toString());

        form.setE09(bi.e0901.isChecked() ? "1"
                : bi.e0902.isChecked() ? "2"
                : "-1");

        form.setE10(bi.e1001.isChecked() ? "1"
                : bi.e1002.isChecked() ? "2"
                : "-1");

        // checkbox
        form.setE1101(bi.e1101.isChecked() ? "1" : "-1");
        form.setE1102(bi.e1102.isChecked() ? "2" : "-1");
        form.setE1103(bi.e1103.isChecked() ? "3" : "-1");
        form.setE1104(bi.e1104.isChecked() ? "4" : "-1");
        form.setE1105(bi.e1105.isChecked() ? "5" : "-1");
        form.setE1106(bi.e1106.isChecked() ? "97" : "-1");

        form.setE12(bi.e1201.isChecked() ? "1"
                : bi.e1202.isChecked() ? "2"
                : bi.e1203.isChecked() ? "3"
                : bi.e1204.isChecked() ? "4"
                : bi.e1205.isChecked() ? "5"
                : bi.e12096.isChecked() ? "96"
                : "-1");
        form.setE12096x(bi.e12096x.getText().toString());

        form.setE13(bi.e1301.isChecked() ? "1"
                : bi.e1302.isChecked() ? "2"
                : bi.e1303.isChecked() ? "3"
                : bi.e13096.isChecked() ? "96"
                : "-1");
        form.setE13096x(bi.e13096x.getText().toString());

        form.setE1401(bi.e1401.getText().toString());

        form.setE1402(bi.e1402.getText().toString());

        form.setE15(bi.e15.getText().toString());

       /* JSONObject json = new JSONObject();
        json.put("e01", bi.e0101.isChecked() ? "1"
                : bi.e0102.isChecked() ? "2"
                : bi.e0103.isChecked() ? "3"
                : bi.e0104.isChecked() ? "4"
                : "-1");

        json.put("e02", "-1");

        json.put("e0201", "-1");

        json.put("e0202", "-1");

        json.put("e03", bi.e0301.isChecked() ? "1"
                : bi.e0302.isChecked() ? "2"
                : bi.e0303.isChecked() ? "3"
                : bi.e0304.isChecked() ? "4"
                : bi.e0305.isChecked() ? "5"
                : bi.e0306.isChecked() ? "6"
                : bi.e03096.isChecked() ? "96"
                : "-1");

        json.put("e04", bi.e0401.isChecked() ? "1"
                : bi.e0402.isChecked() ? "2"
                : bi.e0701.isChecked() ? "1"
                : bi.e0702.isChecked() ? "2"
                : "-1");

        json.put("e05", bi.e05.getText().toString());

        json.put("e06", bi.e0601.isChecked() ? "1"
                : bi.e0602.isChecked() ? "2"
                : bi.e0603.isChecked() ? "3"
                : bi.e0604.isChecked() ? "4"
                : "-1");

        json.put("e07", "-1");

        json.put("e08", bi.e0801.isChecked() ? ""
                : bi.e0801.isChecked() ? "1"
                : bi.e0802.isChecked() ? "2"
                : bi.e0803.isChecked() ? "3"
                : bi.e0804.isChecked() ? "4"
                : bi.e0805.isChecked() ? "5"
                : bi.e0806.isChecked() ? "6"
                : bi.e08096.isChecked() ? "96"
                : "-1");

        json.put("e09", bi.e0901.isChecked() ? "1"
                : bi.e0902.isChecked() ? "2"
                : "-1");

        json.put("e10", bi.e1001.isChecked() ? "1"
                : bi.e1002.isChecked() ? "2"
                : "-1");

        json.put("e11", bi.e1101.isChecked() ? "1"
                : bi.e1102.isChecked() ? "2"
                : bi.e1103.isChecked() ? "3"
                : bi.e1104.isChecked() ? "4"
                : bi.e1105.isChecked() ? "5"
                : bi.e1106.isChecked() ? "6"
                : "-1");

        json.put("e12", bi.e1201.isChecked() ? "1"
                : bi.e1202.isChecked() ? "2"
                : bi.e1203.isChecked() ? "3"
                : bi.e1204.isChecked() ? "4"
                : bi.e1205.isChecked() ? "5"
                : bi.e12096.isChecked() ? "96"
                : "-1");

        json.put("e13", bi.e1301.isChecked() ? "1"
                : bi.e1302.isChecked() ? "2"
                : bi.e1303.isChecked() ? "3"
                : bi.e13096.isChecked() ? "96"
                : "-1");

        json.put("e1401", bi.e1401.getText().toString());

        json.put("e1402", bi.e1402.getText().toString());

        //json.put("e15", bi.e15.getText().toString());

        MainApp.form.setsE(json.toString());*/

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