package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionHBinding;
import edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.blf_screening.CONSTANTS.FSTATUS_END_FLAG;
import static edu.aku.hassannaqvi.blf_screening.core.MainApp.form;
import static edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt.contextBackActivity;

public class SectionHActivity extends AppCompatActivity {

    ActivitySectionHBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_h);
        bi.setCallback(this);
        bi.setForm(form);
        setupSkips();
        bi.h02yy.setMaxvalue(Float.parseFloat(new SimpleDateFormat("yyyy").format(new Date().getTime())));

    }


    private void setupSkips() {

        bi.h02yy.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(bi.h02yy.getText()))
                    return;
                if (bi.h02yy.getText().toString().equals("9998")) {
                    bi.h02dd.setText("");
                    bi.h02dd.setEnabled(false);
                    bi.h02mm.setText("");
                    bi.h02mm.setEnabled(false);
                } else {
                    bi.h02dd.setEnabled(true);
                    bi.h02mm.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        bi.h04.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.h0402.getId()) {
                Clear.clearAllFields(bi.fldGrpCVh05);
            }
        }));

        bi.h06.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.h0602.getId()) {
                Clear.clearAllFields(bi.fldGrpSecH01);
            }
        }));

        bi.h10.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.h1002.getId()) {
                Clear.clearAllFields(bi.fldGrpSecH02);
            }
        }));

        //h0316
        bi.h0316.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Clear.clearAllFields(bi.h03check, false);
                bi.h03check.setTag("-1");
            } else {
                Clear.clearAllFields(bi.h03check, true);
                bi.h03check.setTag("0");
            }
        });

        bi.h01a.setOnCheckedChangeListener((radioButton, i) -> {
            if (i == bi.h01a2.getId())
                Clear.clearAllFields(bi.fldGrpCVh01);
        });

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
            startActivity(new Intent(this, SectionIActivity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SH, form.sHtoString());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        form.setH01a(bi.h01a1.isChecked() ? "1" : bi.h01a2.isChecked() ? "2" : "-1");

        form.setH01dd(bi.h01dd.getText().toString());
        form.setH01mm(bi.h01mm.getText().toString());
        form.setH01yy(bi.h01yy.getText().toString());

        form.setH02dd(bi.h02dd.getText().toString());
        form.setH02mm(bi.h02mm.getText().toString());
        form.setH02yy(bi.h02yy.getText().toString());

        form.setH0301(bi.h0301.isChecked() ? "1" : "-1");
        form.setH0302(bi.h0302.isChecked() ? "2" : "-1");
        form.setH0303(bi.h0303.isChecked() ? "3" : "-1");
        form.setH0304(bi.h0304.isChecked() ? "4" : "-1");
        form.setH0305(bi.h0305.isChecked() ? "5" : "-1");
        form.setH0306(bi.h0306.isChecked() ? "6" : "-1");
        form.setH0307(bi.h0307.isChecked() ? "7" : "-1");
        form.setH0308(bi.h0308.isChecked() ? "8" : "-1");
        form.setH0309(bi.h0309.isChecked() ? "9" : "-1");
        form.setH0310(bi.h0310.isChecked() ? "10" : "-1");
        form.setH0311(bi.h0311.isChecked() ? "11" : "-1");
        form.setH0312(bi.h0312.isChecked() ? "12" : "-1");
        form.setH0313(bi.h0313.isChecked() ? "13" : "-1");
        form.setH0314(bi.h0314.isChecked() ? "14" : "-1");
        form.setH0315(bi.h0315.isChecked() ? "15" : "-1");
        form.setH0316(bi.h0316.isChecked() ? "16" : "-1");
        form.setH03096(bi.h03096.isChecked() ? "96" : "-1");
        form.setH03096x(bi.h03096x.getText().toString());

        form.setH04(bi.h0401.isChecked() ? "1"
                : bi.h0402.isChecked() ? "2"
                : "-1");

        form.setH05(bi.h05.getText().toString());

        form.setH06(bi.h0601.isChecked() ? "1"
                : bi.h0602.isChecked() ? "2"
                : "-1");

        form.setH0701(bi.h0701.getText().toString());

        form.setH0702(bi.h07021.isChecked() ? "1"
                : bi.h07022.isChecked() ? "2"
                : bi.h07023.isChecked() ? "3"
                : bi.h07024.isChecked() ? "4"
                : bi.h07025.isChecked() ? "5"
                : bi.h07026.isChecked() ? "6"
                : "-1");

        form.setH0801(bi.h0801.isChecked() ? "1" : "-1");
        form.setH0802(bi.h0802.isChecked() ? "2" : "-1");
        form.setH0803(bi.h0803.isChecked() ? "3" : "-1");
        form.setH0804(bi.h0804.isChecked() ? "4" : "-1");
        form.setH08096(bi.h08096.isChecked() ? "96" : "-1");
        form.setH08096x(bi.h08096x.getText().toString());

        form.setH09(bi.h0901.isChecked() ? "1"
                : bi.h0902.isChecked() ? "2"
                : bi.h0903.isChecked() ? "3"
                : "-1");

        form.setH10(bi.h1001.isChecked() ? "1"
                : bi.h1002.isChecked() ? "2"
                : "-1");

        form.setH11(bi.h1101.isChecked() ? "1"
                : bi.h1102.isChecked() ? "2"
                : bi.h1103.isChecked() ? "3"
                : bi.h11096.isChecked() ? "96"
                : "-1");
        form.setH11096x(bi.h11096x.getText().toString());

        form.setH12(bi.h1201.isChecked() ? "1"
                : bi.h1202.isChecked() ? "2"
                : bi.h1203.isChecked() ? "3"
                : bi.h12096.isChecked() ? "96"
                : bi.h12098.isChecked() ? "98"
                : "-1");
        form.setH12096x(bi.h12096x.getText().toString());

        form.setH13dd(bi.h13dd.getText().toString());
        form.setH13mm(bi.h13mm.getText().toString());
        form.setH13yy(bi.h13yy.getText().toString());

        form.setH14(bi.h1401.isChecked() ? "1"
                : bi.h1402.isChecked() ? "2"
                : bi.h1403.isChecked() ? "3"
                : bi.h14096.isChecked() ? "96"
                : "-1");
        form.setH14096x(bi.h14096x.getText().toString());

        form.setH15(bi.h1501.isChecked() ? "1"
                : bi.h1502.isChecked() ? "2"
                : bi.h1503.isChecked() ? "3"
                : bi.h1504.isChecked() ? "4"
                : bi.h1505.isChecked() ? "5"
                : bi.h1506.isChecked() ? "6"
                : "-1");

        form.setH16(bi.h16.getText().toString());

        form.setH17(bi.h1701.isChecked() ? "1"
                : bi.h1702.isChecked() ? "2"
                : "-1");

        form.setH1801(bi.h1801.getText().toString());
        form.setH1802(bi.h1802.getText().toString());

        /*JSONObject json = new JSONObject();

        json.put("h01dd", bi.h01dd.getText().toString());

        json.put("h01mm", bi.h01mm.getText().toString());

        json.put("h01yy", bi.h01yy.getText().toString());

        json.put("h02dd", bi.h02dd.getText().toString());

        json.put("h02mm", bi.h02mm.getText().toString());

        json.put("h02yy", bi.h02yy.getText().toString());

        json.put("h04", bi.h0401.isChecked() ? "1"
                : bi.h0402.isChecked() ? "2"
                : "-1");

        json.put("h05", bi.h05.getText().toString());

        json.put("h06", bi.h0601.isChecked() ? "1"
                : bi.h0602.isChecked() ? "2"
                : "-1");

        json.put("h0701", bi.h0701.getText().toString());

        json.put("h0702", bi.h0702.getText().toString());

        json.put("h09", bi.h0901.isChecked() ? "1"
                : bi.h0902.isChecked() ? "2"
                : bi.h0903.isChecked() ? "3"
                : "-1");

        json.put("h10", bi.h1001.isChecked() ? "1"
                : bi.h1002.isChecked() ? "2"
                : "-1");

        json.put("h11", bi.h1101.isChecked() ? "1"
                : bi.h1102.isChecked() ? "2"
                : bi.h1103.isChecked() ? "3"
                : bi.h11096.isChecked() ? "96"
                : bi.h11098.isChecked() ? "98"
                : "-1");

        json.put("h12dd", bi.h12dd.getText().toString());

        json.put("h12mm", bi.h12mm.getText().toString());

        json.put("h12yy", bi.h12yy.getText().toString());

        json.put("h13", bi.h1301.isChecked() ? "1"
                : bi.h1302.isChecked() ? "2"
                : bi.h1303.isChecked() ? "3"
                : bi.h13096.isChecked() ? "96"
                : "-1");

        json.put("h14", bi.h1401.isChecked() ? "1"
                : bi.h1402.isChecked() ? "2"
                : bi.h1403.isChecked() ? "3"
                : bi.h14096.isChecked() ? "96"
                : "-1");

        json.put("h15", bi.h1501.isChecked() ? "1"
                : bi.h1502.isChecked() ? "2"
                : bi.h1503.isChecked() ? "3"
                : bi.h1504.isChecked() ? "4"
                : bi.h1505.isChecked() ? "5"
                : bi.h1506.isChecked() ? "6"
                : "-1");

        json.put("h16", bi.h16.getText().toString());

        json.put("h17", bi.h1701.isChecked() ? "1"
                : bi.h1702.isChecked() ? "2"
                : "-1");

        json.put("h1801", bi.h1801.getText().toString());

        json.put("h1802", bi.h1802.getText().toString());

        MainApp.form.setsH(json.toString());
*/
    }


    public void BtnEnd() {
        AppUtilsKt.openFormEndActivity(this, FSTATUS_END_FLAG, 2);
    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionH);

    }


    @Override
    public void onBackPressed() {
        contextBackActivity(this);
    }

    public void showTooltipView(View view) {
        AppUtilsKt.showTooltip(this, view);
    }
}