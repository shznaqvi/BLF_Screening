package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsENContract;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsSESContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionS1BBinding;
import edu.aku.hassannaqvi.blf_screening.models.FormsSES;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;
import edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt;
import edu.aku.hassannaqvi.blf_screening.utils.JSONUtils;

import static edu.aku.hassannaqvi.blf_screening.core.MainApp.formsEN;

public class SectionS1BActivity extends AppCompatActivity {

    ActivitySectionS1BBinding bi;
    Intent oF = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_s1_b);
        bi.setCallback(this);
        setupSkips();
    }

    private void setupSkips() {

        //a1q16
        bi.se1q16.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.se1q1601.getId()) {
                bi.fldGrpCVse1q17.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.fldGrpCVse1q17);
                bi.fldGrpCVse1q17.setVisibility(View.GONE);
            }
        });

        //a1q21
        bi.se1q21.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.se1q2102.getId()) {
                Clear.clearAllFields(bi.fldGrpCVse1q22);
                bi.fldGrpCVse1q22.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVse1q22.setVisibility(View.VISIBLE);
            }
        });

        //a1q23
        bi.se1q23.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.se1q2302.getId()) {
                Clear.clearAllFields(bi.fldGrpCVse1q24);
                bi.fldGrpCVse1q24.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVse1q24.setVisibility(View.VISIBLE);
            }
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
            startActivity(new Intent(this, SectionS2Activity.class));
        }
    }

    public void BtnEnd() {
        String form = "SES";
        AppUtilsKt.openEndActivity(this, form);
        /*oF = new Intent(this, MainActivity.class);
        startActivity(oF);*/
    }

    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormsSES(FormsSESContract.FormsSESTable.COLUMN_S1, MainApp.formsSES.getS1());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("se1q1310", bi.se1q1310a.isChecked() ? "1"
                : bi.se1q1310b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1311", bi.se1q1311a.isChecked() ? "1"
                : bi.se1q1311b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1312", bi.se1q1312a.isChecked() ? "1"
                : bi.se1q1312b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1313", bi.se1q1313a.isChecked() ? "1"
                : bi.se1q1313b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1314", bi.se1q1314a.isChecked() ? "1"
                : bi.se1q1314b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1315", bi.se1q1315a.isChecked() ? "1"
                : bi.se1q1315b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1316", bi.se1q1316a.isChecked() ? "1"
                : bi.se1q1316b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1317", bi.se1q1317a.isChecked() ? "1"
                : bi.se1q1317b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1318", bi.se1q1318a.isChecked() ? "1"
                : bi.se1q1318b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1319", bi.se1q1319a.isChecked() ? "1"
                : bi.se1q1319b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1401", bi.se1q1401a.isChecked() ? "1"
                : bi.se1q1401b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1402", bi.se1q1402a.isChecked() ? "1"
                : bi.se1q1402b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1403", bi.se1q1403a.isChecked() ? "1"
                : bi.se1q1403b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1404", bi.se1q1404a.isChecked() ? "1"
                : bi.se1q1404b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1405", bi.se1q1405a.isChecked() ? "1"
                : bi.se1q1405b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1406", bi.se1q1406a.isChecked() ? "1"
                : bi.se1q1406b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1407", bi.se1q1407a.isChecked() ? "1"
                : bi.se1q1407b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1408", bi.se1q1408a.isChecked() ? "1"
                : bi.se1q1408b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1409", bi.se1q1409a.isChecked() ? "1"
                : bi.se1q1409b.isChecked() ? "2"
                :  "-1");

        json.put("se1q15", bi.se1q1501.isChecked() ? "1"
                : bi.se1q1502.isChecked() ? "2"
                : bi.se1q1503.isChecked() ? "3"
                : bi.se1q1504.isChecked() ? "4"
                : bi.se1q1505.isChecked() ? "5"
                : bi.se1q1506.isChecked() ? "6"
                : bi.se1q1507.isChecked() ? "7"
                : bi.se1q1508.isChecked() ? "8"
                : bi.se1q1509.isChecked() ? "9"
                : bi.se1q1510.isChecked() ? "10"
                : bi.se1q1511.isChecked() ? "11"
                : bi.se1q1596.isChecked() ? "96"
                :  "-1");

        json.put("se1q1596x", bi.se1q1596x.getText().toString().trim().isEmpty() ? "-1" : bi.se1q1596x.getText().toString());

        json.put("se1q16", bi.se1q1601.isChecked() ? "1"
                : bi.se1q1602.isChecked() ? "2"
                : bi.se1q1603.isChecked() ? "3"
                : bi.se1q1696.isChecked() ? "96"
                :  "-1");

        json.put("se1q1696x", bi.se1q1696x.getText().toString().trim().isEmpty() ? "-1" : bi.se1q1696x.getText().toString());

        json.put("se1q17", bi.se1q1701.isChecked() ? "1"
                : bi.se1q1702.isChecked() ? "2"
                :  "-1");

        json.put("se1q18", bi.se1q1811.isChecked() ? "11"
                : bi.se1q1812.isChecked() ? "12"
                : bi.se1q1821.isChecked() ? "21"
                : bi.se1q1822.isChecked() ? "22"
                : bi.se1q1831.isChecked() ? "31"
                : bi.se1q1832.isChecked() ? "32"
                : bi.se1q1833.isChecked() ? "33"
                : bi.se1q1834.isChecked() ? "34"
                : bi.se1q1835.isChecked() ? "35"
                : bi.se1q1836.isChecked() ? "36"
                : bi.se1q1837.isChecked() ? "37"
                : bi.se1q1896.isChecked() ? "96"
                :  "-1");

        json.put("se1q1896x", bi.se1q1896x.getText().toString().trim().isEmpty() ? "-1" : bi.se1q1896x.getText().toString());

        json.put("se1q19", bi.se1q1911.isChecked() ? "11"
                : bi.se1q1912.isChecked() ? "12"
                : bi.se1q1913.isChecked() ? "13"
                : bi.se1q1921.isChecked() ? "21"
                : bi.se1q1922.isChecked() ? "22"
                : bi.se1q1923.isChecked() ? "23"
                : bi.se1q1924.isChecked() ? "24"
                : bi.se1q1931.isChecked() ? "31"
                : bi.se1q1932.isChecked() ? "32"
                : bi.se1q1933.isChecked() ? "33"
                : bi.se1q1934.isChecked() ? "34"
                : bi.se1q1935.isChecked() ? "35"
                : bi.se1q1936.isChecked() ? "36"
                : bi.se1q1937.isChecked() ? "37"
                : bi.se1q1996.isChecked() ? "96"
                :  "-1");

        json.put("se1q1996x", bi.se1q1996x.getText().toString().trim().isEmpty() ? "-1" : bi.se1q1996x.getText().toString());

        json.put("se1q20", bi.se1q20.getText().toString().trim().isEmpty() ? "-1" : bi.se1q20.getText().toString());

        json.put("se1q21", bi.se1q2101.isChecked() ? "1"
                : bi.se1q2102.isChecked() ? "2"
                :  "-1");

        json.put("se1q22", bi.se1q2201.isChecked() ? "1"
                : bi.se1q2202.isChecked() ? "2"
                : bi.se1q2298.isChecked() ? "98"
                :  "-1");

        json.put("se1q2201x", bi.se1q2201x.getText().toString().trim().isEmpty() ? "-1" : bi.se1q2201x.getText().toString());
        json.put("se1q2202x", bi.se1q2202x.getText().toString().trim().isEmpty() ? "-1" : bi.se1q2202x.getText().toString());

        json.put("se1q23", bi.se1q2301.isChecked() ? "1"
                : bi.se1q2302.isChecked() ? "2"
                :  "-1");

        json.put("se1q2401", bi.se1q2401.getText().toString().trim().isEmpty() ? "-1" : bi.se1q2401.getText().toString());
        json.put("se1q2402", bi.se1q2402.getText().toString().trim().isEmpty() ? "-1" : bi.se1q2402.getText().toString());
        json.put("se1q2403", bi.se1q2403.getText().toString().trim().isEmpty() ? "-1" : bi.se1q2403.getText().toString());
        json.put("se1q2404", bi.se1q2404.getText().toString().trim().isEmpty() ? "-1" : bi.se1q2404.getText().toString());
        json.put("se1q2405", bi.se1q2405.getText().toString().trim().isEmpty() ? "-1" : bi.se1q2405.getText().toString());
        json.put("se1q2406", bi.se1q2406.getText().toString().trim().isEmpty() ? "-1" : bi.se1q2406.getText().toString());
        json.put("se1q2407", bi.se1q2407.getText().toString().trim().isEmpty() ? "-1" : bi.se1q2407.getText().toString());

        json.put("se1q25", bi.se1q2501.isChecked() ? "1"
                : bi.se1q2502.isChecked() ? "2"
                : bi.se1q2598.isChecked() ? "98"
                :  "-1");

        try {
            JSONObject json_merge = JSONUtils.mergeJSONObjects(new JSONObject(MainApp.formsSES.getS1()), json);
            MainApp.formsSES.setS1(String.valueOf(json_merge));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean formValidation() {

        if (!Validator.emptyCheckingContainer(this, bi.GrpName)) {
            return false;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}