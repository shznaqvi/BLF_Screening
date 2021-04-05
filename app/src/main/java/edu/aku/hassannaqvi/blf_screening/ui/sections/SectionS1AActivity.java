package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.journeyapps.barcodescanner.Util;
import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsENContract;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsSESContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionS1ABinding;
import edu.aku.hassannaqvi.blf_screening.models.FormsSES;
import edu.aku.hassannaqvi.blf_screening.models.FormsWF;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;
import edu.aku.hassannaqvi.blf_screening.utils.DateUtils;

import static edu.aku.hassannaqvi.blf_screening.core.MainApp.formsSES;
import static edu.aku.hassannaqvi.blf_screening.core.MainApp.formsWF;

public class

SectionS1AActivity extends AppCompatActivity {

    ActivitySectionS1ABinding bi;
    Intent oF = null;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_s1_a);
        bi.setCallback(this);
        setupSkips();
    }

    private void setupSkips() {

        bi.mr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(bi.mr.getText()))
                    return;

                bi.btns.setVisibility(View.GONE);
                Clear.clearAllFields(bi.llsectionses01);
                bi.llsectionses01.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        //se1q4
        bi.se1q4.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.se1q402.getId()) {
                Clear.clearAllFields(bi.fldGrpCVse1q5);
                bi.fldGrpCVse1q5.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVse1q5.setVisibility(View.VISIBLE);
            }
        });

        //se1q9
        bi.se1q9.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.se1q902.getId()) {
                Clear.clearAllFields(bi.fldGrpCVse1q10);
                Clear.clearAllFields(bi.fldGrpCVse1q11);
                bi.fldGrpCVse1q10.setVisibility(View.GONE);
                bi.fldGrpCVse1q11.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVse1q10.setVisibility(View.VISIBLE);
                bi.fldGrpCVse1q11.setVisibility(View.VISIBLE);
            }
        });

        //se1q10
        bi.se1q10.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.se1q102.getId()) {
                Clear.clearAllFields(bi.fldGrpCVse1q11);
                bi.fldGrpCVse1q11.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVse1q11.setVisibility(View.VISIBLE);
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
            startActivity(new Intent(this, SectionS1BActivity.class));
        }
    }


    public void BtnEnd() {

        oF = new Intent(this, MainActivity.class);
        startActivity(oF);
    }


    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addFormSES(formsSES);
        formsSES.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            formsSES.set_UID(formsSES.getDeviceID() + formsSES.get_ID());
            db.updatesFormsSES(FormsSESContract.FormsSESTable.COLUMN_UID, formsSES.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        formsSES = new FormsSES();
        formsSES.setSysdate(new SimpleDateFormat("dd-MM-yy HH:mm", Locale.getDefault()).format(new Date().getTime()));
        formsSES.setDeviceID(MainApp.appInfo.getDeviceID());
        formsSES.setDevicetagID(MainApp.appInfo.getTagName());
        formsSES.setAppversion(MainApp.appInfo.getAppVersion());
        formsSES.setUsername(MainApp.userName);

        JSONObject json = new JSONObject();

        json.put("mrno", bi.mr.getText().toString().trim().isEmpty() ? "-1" : bi.mr.getText().toString());

        json.put("se1q1", bi.se1q101.isChecked() ? "1"
                : bi.se1q102.isChecked() ? "2"
                : bi.se1q103.isChecked() ? "3"
                : bi.se1q104.isChecked() ? "4"
                : bi.se1q105.isChecked() ? "5"
                : bi.se1q106.isChecked() ? "6"
                : bi.se1q107.isChecked() ? "7"
                : bi.se1q108.isChecked() ? "8"
                : bi.se1q109.isChecked() ? "9"
                : bi.se1q196.isChecked() ? "96"
                :  "-1");

        json.put("se1q196x", bi.se1q196x.getText().toString().trim().isEmpty() ? "-1" : bi.se1q196x.getText().toString());
        //json.put("se11q11", bi.s11q11.getText().toString());

        json.put("se1q2", bi.se1q201.isChecked() ? "1"
                : bi.se1q202.isChecked() ? "2"
                : bi.se1q296.isChecked() ? "96"
                :  "-1");
        json.put("se1q296x", bi.se1q296x.getText().toString().trim().isEmpty() ? "-1" : bi.se1q296x.getText().toString());

        json.put("se1q3", bi.se1q311.isChecked() ? "11"
                : bi.se1q312.isChecked() ? "12"
                : bi.se1q313.isChecked() ? "13"
                : bi.se1q314.isChecked() ? "14"
                : bi.se1q315.isChecked() ? "15"
                : bi.se1q316.isChecked() ? "16"
                : bi.se1q331.isChecked() ? "31"
                : bi.se1q332.isChecked() ? "32"
                : bi.se1q341.isChecked() ? "41"
                : bi.se1q342.isChecked() ? "42"
                : bi.se1q351.isChecked() ? "51"
                : bi.se1q361.isChecked() ? "61"
                : bi.se1q371.isChecked() ? "71"
                : bi.se1q381.isChecked() ? "81"
                : bi.se1q391.isChecked() ? "91"
                : bi.se1q392.isChecked() ? "92"
                : bi.se1q396.isChecked() ? "96"
                :  "-1");
        json.put("se1q396x", bi.se1q396x.getText().toString().trim().isEmpty() ? "-1" : bi.se1q396x.getText().toString());

        json.put("se1q4", bi.se1q401.isChecked() ? "1"
                : bi.se1q402.isChecked() ? "2"
                :  "-1");

        json.put("se1q5", bi.se1q501.isChecked() ? "1"
                : bi.se1q502.isChecked() ? "2"
                : bi.se1q503.isChecked() ? "3"
                : bi.se1q504.isChecked() ? "4"
                : bi.se1q505.isChecked() ? "5"
                : bi.se1q506.isChecked() ? "6"
                : bi.se1q596.isChecked() ? "96"
                :  "-1");

        json.put("se1q596x", bi.se1q596x.getText().toString().trim().isEmpty() ? "-1" : bi.se1q596x.getText().toString());

        json.put("se1q6", bi.se1q611.isChecked() ? "11"
                : bi.se1q612.isChecked() ? "12"
                : bi.se1q613.isChecked() ? "13"
                : bi.se1q614.isChecked() ? "14"
                : bi.se1q615.isChecked() ? "15"
                : bi.se1q616.isChecked() ? "16"
                : bi.se1q631.isChecked() ? "31"
                : bi.se1q632.isChecked() ? "32"
                : bi.se1q641.isChecked() ? "41"
                : bi.se1q642.isChecked() ? "42"
                : bi.se1q651.isChecked() ? "51"
                : bi.se1q661.isChecked() ? "61"
                : bi.se1q671.isChecked() ? "71"
                : bi.se1q681.isChecked() ? "81"
                : bi.se1q691.isChecked() ? "91"
                : bi.se1q692.isChecked() ? "92"
                : bi.se1q696.isChecked() ? "96"
                :  "-1");

        json.put("se1q696x", bi.se1q696x.getText().toString().trim().isEmpty() ? "-1" : bi.se1q696x.getText().toString());

        json.put("se1q7", bi.se1q701.isChecked() ? "1"
                : bi.se1q702.isChecked() ? "2"
                : bi.se1q703.isChecked() ? "3"
                : bi.se1q704.isChecked() ? "4"
                : bi.se1q705.isChecked() ? "5"
                : bi.se1q706.isChecked() ? "6"
                : bi.se1q707.isChecked() ? "7"
                : bi.se1q708.isChecked() ? "8"
                : bi.se1q709.isChecked() ? "9"
                : bi.se1q796.isChecked() ? "96"
                :  "-1");

        json.put("se1q796x", bi.se1q796x.getText().toString().trim().isEmpty() ? "-1" : bi.se1q796x.getText().toString());

        json.put("se1q8", bi.se1q801.isChecked() ? "1"
                : bi.se1q802.isChecked() ? "2"
                : bi.se1q803.isChecked() ? "3"
                :  "-1");

        json.put("se1q9", bi.se1q901.isChecked() ? "1"
                : bi.se1q902.isChecked() ? "2"
                :  "-1");

        json.put("se1q10", bi.se1q1001.isChecked() ? "1"
                : bi.se1q1002.isChecked() ? "2"
                :  "-1");

        json.put("se1q11", bi.se1q1101.isChecked() ? "1"
                : bi.se1q1110.isChecked() ? "10"
                : bi.se1q1198.isChecked() ? "98"
                :  "-1");

        json.put("se1q1102x", bi.se1q1101x.getText().toString().trim().isEmpty() ? "-1" : bi.se1q1101x.getText().toString());

        json.put("se1q12", bi.se1q1201.isChecked() ? "1"
                : bi.se1q1202.isChecked() ? "2"
                : bi.se1q1203.isChecked() ? "3"
                : bi.se1q1204.isChecked() ? "4"
                : bi.se1q1205.isChecked() ? "5"
                : bi.se1q1206.isChecked() ? "6"
                : bi.se1q1296.isChecked() ? "96"
                :  "-1");

        json.put("se1q1296x", bi.se1q1296x.getText().toString().trim().isEmpty() ? "-1" : bi.se1q1296x.getText().toString());

        json.put("se1q1301", bi.se1q1301a.isChecked() ? "1"
                : bi.se1q1301b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1302", bi.se1q1302a.isChecked() ? "1"
                : bi.se1q1302b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1303", bi.se1q1303a.isChecked() ? "1"
                : bi.se1q1303b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1304", bi.se1q1304a.isChecked() ? "1"
                : bi.se1q1304b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1305", bi.se1q1305a.isChecked() ? "1"
                : bi.se1q1305b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1306", bi.se1q1306a.isChecked() ? "1"
                : bi.se1q1306b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1307", bi.se1q1307a.isChecked() ? "1"
                : bi.se1q1307b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1308", bi.se1q1308a.isChecked() ? "1"
                : bi.se1q1308b.isChecked() ? "2"
                :  "-1");

        json.put("se1q1309", bi.se1q1309a.isChecked() ? "1"
                : bi.se1q1309b.isChecked() ? "2"
                :  "-1");

        formsSES.setS1(String.valueOf(json));
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



    public void FetchFollowups(View view) {

        if (formValidation()) {

            bi.checkMR.setVisibility(View.GONE);
            bi.pbarMR.setVisibility(View.VISIBLE);
            bi.btns.setVisibility(View.GONE);
            bi.wmError.setVisibility(View.GONE);
            bi.wmError.setText(null);

            String mrno = bi.mr.getText().toString();
            db = MainApp.appInfo.getDbHelper();
            Cursor mr = db.checkMRNo(mrno);

            if (mr.getCount() > 0) {

                mr.moveToFirst();

                bi.checkMR.setVisibility(View.VISIBLE);
                bi.llsectionses01.setVisibility(View.VISIBLE);
                bi.pbarMR.setVisibility(View.GONE);
                bi.btns.setVisibility(View.VISIBLE);
                bi.wmError.setVisibility(View.GONE);

            } else {

                bi.llsectionses01.setVisibility(View.GONE);
                bi.pbarMR.setVisibility(View.GONE);
                bi.checkMR.setVisibility(View.VISIBLE);
                bi.btns.setVisibility(View.GONE);
                bi.wmError.setVisibility(View.VISIBLE);
                bi.wmError.setText("Either no child enrolled or SES form already filled for given MR No");

            }
        }
    }

}