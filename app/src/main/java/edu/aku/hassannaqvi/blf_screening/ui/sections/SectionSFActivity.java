package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsSFContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionSfBinding;
import edu.aku.hassannaqvi.blf_screening.models.FormsSF;
import edu.aku.hassannaqvi.blf_screening.ui.other.EndingActivity;
import edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt.contextBackActivity;

public class SectionSFActivity extends AppCompatActivity {
    ActivitySectionSfBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_sf);
        bi.setCallback(this);
        setupSkip();
    }

    private void setupSkip() {

//        bi.a07.setOnCheckedChangeListener((group, checkId) -> {
//            if (bi.a0701.isChecked()) {
//                bi.btnContinue.setVisibility(View.VISIBLE);
//                bi.btnEnd.setVisibility(View.GONE);
//            } else {
//                bi.btnContinue.setVisibility(View.GONE);
//                bi.btnEnd.setVisibility(View.VISIBLE);
//                Clear.clearAllFields(bi.lla08);
//            }
//        });
//
//        bi.a05b.setOnCheckedChangeListener((group, checkId) -> {
//            if (bi.a05b1.isChecked()) {
//                bi.btnContinue.setVisibility(View.VISIBLE);
//                bi.btnEnd.setVisibility(View.GONE);
//            } else {
//                bi.btnContinue.setVisibility(View.GONE);
//                bi.btnEnd.setVisibility(View.VISIBLE);
//                bi.a07.clearCheck();
//            }
//        });

        /*bi.a21.setOnCheckedChangeListener((group, checkId) -> {
            Clear.clearAllFields(bi.fldGrpSecA03);
        });*/
    }

    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            startActivity(new Intent(this, EndingActivity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormsSFColumn(FormsSFContract.FormsSFTable.COLUMN_SF, MainApp.formsSF.sFtoString());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        MainApp.formsSF = new FormsSF();

        MainApp.formsSF.setSf101(bi.sf101.getText().toString());
        MainApp.formsSF.setSf102(bi.sf102.getText().toString());
        MainApp.formsSF.setSf103(bi.sf103.getText().toString());
        MainApp.formsSF.setSf104(bi.sf104.getText().toString());
        MainApp.formsSF.setSf105(bi.sf105.getText().toString());

        MainApp.formsSF.setSf2(bi.sf2.getText().toString());
        MainApp.formsSF.setSf3(bi.sf3.getText().toString());
        MainApp.formsSF.setSf4(bi.sf4.getText().toString());
        MainApp.formsSF.setSf5(bi.sf5.getText().toString());
        MainApp.formsSF.setSf6(bi.sf6.getText().toString());

        MainApp.formsSF.setSf701(bi.sf701.getText().toString());
        MainApp.formsSF.setSf702(bi.sf702.getText().toString());

        MainApp.formsSF.setSf8(bi.sf8.getText().toString());

        MainApp.formsSF.setSf9(bi.sf901.isChecked() ? "1"
                : bi.sf902.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf10(bi.sf10.getText().toString());

        MainApp.formsSF.setSf11(bi.sf1101.isChecked() ? "1"
                : bi.sf1102.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf12(bi.sf1201.isChecked() ? "1"
                : bi.sf1202.isChecked() ? "2"
                : bi.sf1203.isChecked() ? "3"
                : bi.sf1204.isChecked() ? "4"
                : bi.sf1296.isChecked() ? "96"
                : "-1");
        MainApp.formsSF.setSf1296x(bi.sf1296x.getText().toString());

        MainApp.formsSF.setSf1301(bi.sf130101.isChecked() ? "1"
                : bi.sf130102.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1302(bi.sf130201.isChecked() ? "1"
                : bi.sf130202.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1303(bi.sf130301.isChecked() ? "1"
                : bi.sf130302.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1304(bi.sf130401.isChecked() ? "1"
                : bi.sf130402.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1305(bi.sf130501.isChecked() ? "1"
                : bi.sf130502.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1306(bi.sf130601.isChecked() ? "1"
                : bi.sf130602.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1307(bi.sf130701.isChecked() ? "1"
                : bi.sf130702.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1308(bi.sf130801.isChecked() ? "1"
                : bi.sf130802.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1309(bi.sf130901.isChecked() ? "1"
                : bi.sf130902.isChecked() ? "2"
                : bi.sf1396.isChecked() ? "96"
                : "-1");
        MainApp.formsSF.setSf1396x(bi.sf1396x.getText().toString());

        MainApp.formsSF.setSf14(bi.sf1401.isChecked() ? "1"
                : bi.sf1402.isChecked() ? "2"
                : bi.sf1403.isChecked() ? "96"
                : "-1");
        MainApp.formsSF.setSf1403x(bi.sf1403x.getText().toString());

        MainApp.formsSF.setSf1501(bi.sf150101.isChecked() ? "1"
                : bi.sf150102.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1502(bi.sf150201.isChecked() ? "1"
                : bi.sf150202.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1503(bi.sf150301.isChecked() ? "1"
                : bi.sf150302.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1504(bi.sf150401.isChecked() ? "1"
                : bi.sf150402.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1505(bi.sf150501.isChecked() ? "1"
                : bi.sf150502.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1506(bi.sf150601.isChecked() ? "1"
                : bi.sf150602.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1507(bi.sf150701.isChecked() ? "1"
                : bi.sf150702.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1508(bi.sf150801.isChecked() ? "1"
                : bi.sf150802.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1509(bi.sf150901.isChecked() ? "1"
                : bi.sf150902.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf16(bi.sf1601.isChecked() ? "1"
                : bi.sf1602.isChecked() ? "2"
                : bi.sf1698.isChecked() ? "98"
                : "-1");

        MainApp.formsSF.setSf17(bi.sf1701.isChecked() ? "1"
                : bi.sf1702.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf18(bi.sf1801.isChecked() ? "1"
                : bi.sf1802.isChecked() ? "2"
                : "-1");

        MainApp.formsSF.setSf1901(bi.sf1901.getText().toString());
        MainApp.formsSF.setSf1902(bi.sf102.getText().toString());

        MainApp.formsSF.setSf20(bi.sf20.getText().toString());

       /* JSONObject json = new JSONObject();

        json.put("sf101", bi.sf101.getText().toString());
        json.put("sf102", bi.sf102.getText().toString());
        json.put("sf103", bi.sf103.getText().toString());
        json.put("sf104", bi.sf104.getText().toString());
        json.put("sf105", bi.sf105.getText().toString());

        json.put("sf2", bi.sf2.getText().toString());
        json.put("sf3", bi.sf3.getText().toString());
        json.put("sf4", bi.sf4.getText().toString());
        json.put("sf5", bi.sf5.getText().toString());
        json.put("sf6", bi.sf6.getText().toString());

        json.put("sf701", bi.sf701.getText().toString());
        json.put("sf702", bi.sf702.getText().toString());

        json.put("sf8", bi.sf8.getText().toString());

        json.put("sf9", bi.sf901.isChecked() ? "1"
                : bi.sf902.isChecked() ? "2"
                : "-1");

        json.put("sf10", bi.sf10.getText().toString());

        json.put("sf11", bi.sf1101.isChecked() ? "1"
                : bi.sf1102.isChecked() ? "2"
                : "-1");

        json.put("sf12", bi.sf1201.isChecked() ? "1"
                : bi.sf1202.isChecked() ? "2"
                : bi.sf1203.isChecked() ? "3"
                : bi.sf1204.isChecked() ? "4"
                : bi.sf1296.isChecked() ? "96"
                : "-1");
        json.put("sf1296x", bi.sf1296x.getText().toString());

        json.put("sf1301", bi.sf130101.isChecked() ? "1"
                : bi.sf130102.isChecked() ? "2"
                : "-1");

        json.put("sf1302", bi.sf130201.isChecked() ? "1"
                : bi.sf130202.isChecked() ? "2"
                : "-1");

        json.put("sf1303", bi.sf130301.isChecked() ? "1"
                : bi.sf130302.isChecked() ? "2"
                : "-1");

        json.put("sf1304", bi.sf130401.isChecked() ? "1"
                : bi.sf130402.isChecked() ? "2"
                : "-1");

        json.put("sf1305", bi.sf130501.isChecked() ? "1"
                : bi.sf130502.isChecked() ? "2"
                : "-1");

        json.put("sf1306", bi.sf130601.isChecked() ? "1"
                : bi.sf130602.isChecked() ? "2"
                : "-1");

        json.put("sf1307", bi.sf130701.isChecked() ? "1"
                : bi.sf130702.isChecked() ? "2"
                : "-1");

        json.put("sf1308", bi.sf130801.isChecked() ? "1"
                : bi.sf130802.isChecked() ? "2"
                : "-1");

        json.put("sf1309", bi.sf130901.isChecked() ? "1"
                : bi.sf130902.isChecked() ? "2"
                : bi.sf1396.isChecked() ? "96"
                : "-1");
        json.put("sf1396x", bi.sf1396x.getText().toString());

        json.put("sf14", bi.sf1401.isChecked() ? "1"
                : bi.sf1402.isChecked() ? "2"
                : bi.sf1403.isChecked() ? ""
                : "-1");
        json.put("sf1403x", bi.sf1403x.getText().toString());

        json.put("sf1501", bi.sf150101.isChecked() ? "1"
                : bi.sf150102.isChecked() ? "2"
                : "-1");

        json.put("sf1502", bi.sf150201.isChecked() ? "1"
                : bi.sf150202.isChecked() ? "2"
                : "-1");

        json.put("sf1503", bi.sf150301.isChecked() ? "1"
                : bi.sf150302.isChecked() ? "2"
                : "-1");

        json.put("sf1504", bi.sf150401.isChecked() ? "1"
                : bi.sf150402.isChecked() ? "2"
                : "-1");

        json.put("sf1505", bi.sf150501.isChecked() ? "1"
                : bi.sf150502.isChecked() ? "2"
                : "-1");

        json.put("sf1506", bi.sf150601.isChecked() ? "1"
                : bi.sf150602.isChecked() ? "2"
                : "-1");

        json.put("sf1507", bi.sf150701.isChecked() ? "1"
                : bi.sf150702.isChecked() ? "2"
                : "-1");

        json.put("sf1508", bi.sf150801.isChecked() ? "1"
                : bi.sf150802.isChecked() ? "2"
                : "-1");

        json.put("sf1509", bi.sf150901.isChecked() ? "1"
                : bi.sf150902.isChecked() ? "2"
                : "-1");

        json.put("sf16", bi.sf1601.isChecked() ? "1"
                : bi.sf1602.isChecked() ? "2"
                : bi.sf1698.isChecked() ? "98"
                : "-1");

        json.put("sf17", bi.sf1701.isChecked() ? "1"
                : bi.sf1702.isChecked() ? "2"
                : "-1");

        json.put("sf18", bi.sf1801.isChecked() ? "1"
                : bi.sf1802.isChecked() ? "2"
                : "-1");

        *//*json.put("sf19", bi.sf19.getText().toString());*//*

        json.put("sf1901", bi.sf1901.getText().toString());
        json.put("sf1902", bi.sf1902.getText().toString());
        json.put("sf20", bi.sf20.getText().toString());*/

    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    public void BtnEnd() {
        AppUtilsKt.contextEndActivity(this, false);
    }

    @Override
    public void onBackPressed() {
        contextBackActivity(this);

    }

}