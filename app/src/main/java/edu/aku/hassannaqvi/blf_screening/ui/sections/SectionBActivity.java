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
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionBBinding;
import edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.blf_screening.CONSTANTS.FSTATUS_END_FLAG;
import static edu.aku.hassannaqvi.blf_screening.core.MainApp.form;
import static edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt.contextBackActivity;

public class SectionBActivity extends AppCompatActivity {
    ActivitySectionBBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_b);
        bi.setCallback(this);
        bi.setForm(form);
        setupSkip();
//        setupTextWatchers();
    }

    private void setupSkip() {
        //b01
        bi.b01.setOnCheckedChangeListener((group, checkedId) -> {
            Clear.clearAllFields(bi.llb0203);
        });

        //b14
        bi.b14.setOnCheckedChangeListener((group, checkId) -> {
            Clear.clearAllFields(bi.llb1517);
        });

        //b17
        bi.b16.setOnCheckedChangeListener((group, checkId) -> {
            Clear.clearAllFields(bi.llb17);
        });
    }

    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            //  finish();
            int a16 = Integer.parseInt(MainApp.form.getSecSelection().getA16());
            startActivity(new Intent(this, a16 == 7 || a16 == 10 ? SectionDActivity.class : a16 >= 1 && a16 <= 6 ? SectionCActivity.class : SectionEActivity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SB, form.sBtoString());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {


        form.setB01(bi.b0101.isChecked() ? "1"
                : bi.b0102.isChecked() ? "2"
                : "-1");

        form.setB02(bi.b02.getText().toString());

        form.setB03(bi.b0301.isChecked() ? "1"
                : bi.b0302.isChecked() ? "2"
                : bi.b0303.isChecked() ? "3"
                : bi.b0304.isChecked() ? "4"
                : bi.b0305.isChecked() ? "5"
                : bi.b03096.isChecked() ? "96"
                : "-1");

        form.setB04(bi.b0401.isChecked() ? "1"
                : bi.b0402.isChecked() ? "2"
                : bi.b0403.isChecked() ? "3"
                : bi.b04096.isChecked() ? "96"
                : "-1");

        form.setB05(bi.b0501.isChecked() ? "1"
                : bi.b0502.isChecked() ? "2"
                : bi.b0503.isChecked() ? "3"
                : bi.b0504.isChecked() ? "4"
                : bi.b05096.isChecked() ? "96"
                : "-1");

        form.setB06(bi.b06ft.isChecked() ? "1"
                : bi.b06yrd.isChecked() ? "2"
                : "-1");

        form.setB06ax(bi.b06ax.getText().toString());
        form.setB07(bi.b07.getText().toString());

        form.setB0801(bi.b0801.getText().toString());

        form.setB0802(bi.b0802.getText().toString());

        form.setB0803(bi.b0803.getText().toString());

        form.setB0804(bi.b0804.getText().toString());

        form.setB0805(bi.b0805.getText().toString());

        form.setB0806(bi.b0806.getText().toString());

        form.setB0807(bi.b0807.getText().toString());

        form.setB9(bi.b9.getText().toString());

        form.setB10(bi.b10.getText().toString());

        form.setB11mc(bi.b011t01.isChecked() ? "1"
                : bi.b011t02.isChecked() ? "2"
                : "-1");

        form.setB11(bi.b1101.isChecked() ? "1"
                : bi.b1102.isChecked() ? "2"
                : bi.b1103.isChecked() ? "3"
                : bi.b1104.isChecked() ? "4"
                : bi.b1105.isChecked() ? "5"
                : bi.b1106.isChecked() ? "6"
                : bi.b1107.isChecked() ? "7"
                : bi.b1108.isChecked() ? "8"
                : bi.b1109.isChecked() ? "9"
                : bi.b1110.isChecked() ? "10"
                : bi.b1111.isChecked() ? "11"
                : bi.b1112.isChecked() ? "12"
                : bi.b1113.isChecked() ? "13"
                : bi.b1114.isChecked() ? "14"
                : bi.b11096.isChecked() ? "96"
                : "-1");

        form.setB11096x(bi.b11096x.getText().toString());
        form.setB12(bi.b1201.isChecked() ? "1"
                : bi.b1202.isChecked() ? "2"
                : bi.b1203.isChecked() ? "3"
                : bi.b1204.isChecked() ? "4"
                : bi.b1205.isChecked() ? "5"
                : bi.b1206.isChecked() ? "6"
                : bi.b1207.isChecked() ? "7"
                : bi.b1208.isChecked() ? "8"
                : bi.b1209.isChecked() ? "9"
                : bi.b1210.isChecked() ? "10"
                : bi.b1211.isChecked() ? "11"
                : bi.b12096.isChecked() ? "96"
                : "-1");

        form.setB1301(bi.b130101.isChecked() ? "1"
                : bi.b130102.isChecked() ? "2"
                : "-1");

        form.setB1302(bi.b130201.isChecked() ? "1"
                : bi.b130202.isChecked() ? "2"
                : "-1");

        form.setB1303(bi.b130301.isChecked() ? "1"
                : bi.b130302.isChecked() ? "2"
                : "-1");

        form.setB1304(bi.b130401.isChecked() ? "1"
                : bi.b130402.isChecked() ? "2"
                : "-1");

        form.setB1305(bi.b130501.isChecked() ? "1"
                : bi.b130502.isChecked() ? "2"
                : "-1");

        form.setB1306(bi.b130601.isChecked() ? "1"
                : bi.b130602.isChecked() ? "2"
                : "-1");

        form.setB1307(bi.b130701.isChecked() ? "1"
                : bi.b130702.isChecked() ? "2"
                : "-1");

        form.setB1308(bi.b130801.isChecked() ? "1"
                : bi.b130802.isChecked() ? "2"
                : "-1");

        form.setB1309(bi.b130901.isChecked() ? "1"
                : bi.b130902.isChecked() ? "2"
                : "-1");

        form.setB13096("-1");

        form.setB14(bi.b1401.isChecked() ? "1"
                : bi.b1402.isChecked() ? "2"
                : "-1");

        form.setB1501(bi.b150101.isChecked() ? "1"
                : bi.b150102.isChecked() ? "2"
                : "-1");

        form.setB1502(bi.b150201.isChecked() ? "1"
                : bi.b150202.isChecked() ? "2"
                : "-1");

        form.setB1503(bi.b150301.isChecked() ? "1"
                : bi.b150302.isChecked() ? "2"
                : "-1");

        form.setB1504(bi.b150401.isChecked() ? "1"
                : bi.b150402.isChecked() ? "2"
                : "-1");

        form.setB1505(bi.b150501.isChecked() ? "1"
                : bi.b150502.isChecked() ? "2"
                : "-1");

        form.setB15096("-1");

        form.setB16(bi.b1601.isChecked() ? "1"
                : bi.b1602.isChecked() ? "2"
                : "-1");

        form.setB17(bi.b17.getText().toString());

        /*JSONObject json = new JSONObject();


        form.setB01", bi.b0101.isChecked() ? "1"
                : bi.b0102.isChecked() ? "2"
                : "-1");

        form.setB02", bi.b02.getText().toString());

        form.setB03", bi.b0301.isChecked() ? "1"
                : bi.b0302.isChecked() ? "2"
                : bi.b0303.isChecked() ? "3"
                : bi.b0304.isChecked() ? "4"
                : bi.b0305.isChecked() ? "5"
                : bi.b03096.isChecked() ? "96"
                : "-1");

        form.setB04", bi.b0401.isChecked() ? "1"
                : bi.b0402.isChecked() ? "2"
                : bi.b0403.isChecked() ? "3"
                : bi.b04096.isChecked() ? "96"
                : "-1");

        form.setB05", bi.b0501.isChecked() ? "1"
                : bi.b0502.isChecked() ? "2"
                : bi.b0503.isChecked() ? "3"
                : bi.b0504.isChecked() ? "4"
                : bi.b05096.isChecked() ? "96"
                : "-1");

        form.setB06", bi.b06ft.isChecked() ? "1"
                : bi.b06yrd.isChecked() ? "2"
                : bi.b06a.isChecked() ? ""
                : "-1");

        form.setB06ax", bi.b06ax.getText().toString());
        form.setB07", bi.b07.getText().toString());

        form.setB0801", bi.b0801.getText().toString());

        form.setB0802", bi.b0802.getText().toString());

        form.setB0803", bi.b0803.getText().toString());

        form.setB0804", bi.b0804.getText().toString());

        form.setB0805", bi.b0805.getText().toString());

        form.setB0806", bi.b0806.getText().toString());

        form.setB0807", bi.b0807.getText().toString());

        form.setB9", bi.b9.getText().toString());

        form.setB10", bi.b10.getText().toString());

        form.setB11mc", bi.b011t01.isChecked() ? "1"
                : bi.b011t02.isChecked() ? "2"
                : "-1");

        form.setB11", bi.b1101.isChecked() ? "1"
                : bi.b1102.isChecked() ? "2"
                : bi.b1103.isChecked() ? "3"
                : bi.b1104.isChecked() ? "4"
                : bi.b1105.isChecked() ? "5"
                : bi.b1106.isChecked() ? "6"
                : bi.b1107.isChecked() ? "7"
                : bi.b1108.isChecked() ? "8"
                : bi.b1109.isChecked() ? "9"
                : bi.b1110.isChecked() ? "10"
                : bi.b1111.isChecked() ? "11"
                : bi.b1112.isChecked() ? "12"
                : bi.b1113.isChecked() ? "13"
                : bi.b1114.isChecked() ? "14"
                : bi.b11096.isChecked() ? "96"
                : "-1");

        form.setB11096x", bi.b11096x.getText().toString());
        form.setB12", bi.b1201.isChecked() ? "1"
                : bi.b1202.isChecked() ? "2"
                : bi.b1203.isChecked() ? "3"
                : bi.b1204.isChecked() ? "4"
                : bi.b1205.isChecked() ? "5"
                : bi.b1206.isChecked() ? "6"
                : bi.b1207.isChecked() ? "7"
                : bi.b1208.isChecked() ? "8"
                : bi.b1209.isChecked() ? "9"
                : bi.b1210.isChecked() ? "10"
                : bi.b1211.isChecked() ? "11"
                : bi.b12096.isChecked() ? "96"
                : "-1");

        form.setB1301", bi.b130101.isChecked() ? "1"
                : bi.b130102.isChecked() ? "2"
                : "-1");

        form.setB1302", bi.b130201.isChecked() ? "1"
                : bi.b130202.isChecked() ? "2"
                : "-1");

        form.setB1303", bi.b130301.isChecked() ? "1"
                : bi.b130302.isChecked() ? "2"
                : "-1");

        form.setB1304", bi.b130401.isChecked() ? "1"
                : bi.b130402.isChecked() ? "2"
                : "-1");

        form.setB1305", bi.b130501.isChecked() ? "1"
                : bi.b130502.isChecked() ? "2"
                : "-1");

        form.setB1306", bi.b130601.isChecked() ? "1"
                : bi.b130602.isChecked() ? "2"
                : "-1");

        form.setB1307", bi.b130701.isChecked() ? "1"
                : bi.b130702.isChecked() ? "2"
                : "-1");

        form.setB1308", bi.b130801.isChecked() ? "1"
                : bi.b130802.isChecked() ? "2"
                : "-1");

        form.setB1309", bi.b130901.isChecked() ? "1"
                : bi.b130902.isChecked() ? "2"
                : "-1");

        form.setB13096", "-1");

        form.setB14", bi.b1401.isChecked() ? "1"
                : bi.b1402.isChecked() ? "2"
                : "-1");

        form.setB1501", bi.b150101.isChecked() ? "1"
                : bi.b150102.isChecked() ? "2"
                : "-1");

        form.setB1502", bi.b150201.isChecked() ? "1"
                : bi.b150202.isChecked() ? "2"
                : "-1");

        form.setB1503", bi.b150301.isChecked() ? "1"
                : bi.b150302.isChecked() ? "2"
                : "-1");

        form.setB1504", bi.b150401.isChecked() ? "1"
                : bi.b150402.isChecked() ? "2"
                : "-1");

        form.setB1505", bi.b150501.isChecked() ? "1"
                : bi.b150502.isChecked() ? "2"
                : "-1");

        form.setB15096", "-1");

        form.setB16", bi.b1601.isChecked() ? "1"
                : bi.b1602.isChecked() ? "2"
                : "-1");

        form.setB17", bi.b17.getText().toString());

        MainApp.form.setsB(json.toString());*/

    }

    private boolean formValidation() {
        if (!Validator.emptyCheckingContainer(this, bi.GrpName))
            return false;

        int b08alltotal = Integer.parseInt(bi.b0801.getText().toString().trim())
                + Integer.parseInt(bi.b0802.getText().toString().trim())
                + Integer.parseInt(bi.b0803.getText().toString().trim())
                + Integer.parseInt(bi.b0804.getText().toString().trim())
                + Integer.parseInt(bi.b0805.getText().toString().trim())
                + Integer.parseInt(bi.b0806.getText().toString().trim());

        if (b08alltotal == 0) {
            return Validator.emptyCustomTextBox(this, bi.b0807, "Invalid Total Count Please check again");
        } else if (b08alltotal != Integer.parseInt(bi.b0807.getText().toString())) {
            return Validator.emptyCustomTextBox(this, bi.b0807, "Invalid Total Count Please check again");
        }
        return true;
    }

    public void BtnEnd() {
        AppUtilsKt.openFormEndActivity(this, FSTATUS_END_FLAG, 2);
    }

    @Override
    public void onBackPressed() {

        contextBackActivity(this);


    }

    public void showTooltipView(View view) {
        AppUtilsKt.showTooltip(this, view);
    }
}