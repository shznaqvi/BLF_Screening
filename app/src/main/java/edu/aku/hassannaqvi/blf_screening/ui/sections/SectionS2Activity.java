package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.edittextpicker.aliazaz.EditTextPicker;
import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsSESContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionS2Binding;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;
import edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.blf_screening.core.MainApp.formsSES;

public class SectionS2Activity extends AppCompatActivity {

    ActivitySectionS2Binding bi;
    Intent oF = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_s2);
        bi.setCallback(this);
        setupSkips();

        TextWatcher textwatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 0) {
                    Toast.makeText(getApplication(), "Length is greater than 0" + s, Toast.LENGTH_SHORT).show();
                    if (bi.se2q3.isFocused()) {
                        bi.se2q6.setMinvalue(Float.parseFloat(s.toString()));
                    } else if (bi.se2q4.isFocused()) {
                        bi.se2q5.setMaxvalue(Float.parseFloat(s.toString()));
                    } else {
                        Toast.makeText(getApplication(), "Nothing is Focused", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplication(), "Length is 0", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        bi.se2q3.addTextChangedListener(textwatcher);
        bi.se2q4.addTextChangedListener(textwatcher);



        TextWatcher textwatcher2 = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 0) {
                    Toast.makeText(getApplication(), "Entered Value = " + s, Toast.LENGTH_SHORT).show();
                    if (Integer.parseInt(s.toString()) == 1) {
                        Clear.clearAllFields(bi.fldGrpCVse2q9);
                        Clear.clearAllFields(bi.fldGrpCVse2q10);
                        bi.fldGrpCVse2q9.setVisibility(View.GONE);
                        bi.fldGrpCVse2q10.setVisibility(View.GONE);
                    } else {
                        bi.fldGrpCVse2q9.setVisibility(View.VISIBLE);
                        bi.fldGrpCVse2q10.setVisibility(View.VISIBLE);
                    }
                } else {
                    bi.fldGrpCVse2q9.setVisibility(View.VISIBLE);
                    bi.fldGrpCVse2q10.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        bi.se2q4.addTextChangedListener(textwatcher2);
        bi.se2q5.addTextChangedListener(textwatcher2);

    }

    private void setupSkips() {

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
            startActivity(new Intent(this, SectionS3AActivity.class));
        }
    }

    public void BtnEnd() {
        String form = "SES";
        AppUtilsKt.openEndActivity(this, form);
    }


    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormsSES(FormsSESContract.FormsSESTable.COLUMN_S2, MainApp.formsSES.getS2());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("se2q0", bi.se2q001.isChecked() ? "1"
                : bi.se2q002.isChecked() ? "2"
                : bi.se2q003.isChecked() ? "3"
                : bi.se2q004.isChecked() ? "4"
                : bi.se2q005.isChecked() ? "5"
                : bi.se2q006.isChecked() ? "6"
                : bi.se2q007.isChecked() ? "7"
                : bi.se2q008.isChecked() ? "8"
                : bi.se2q009.isChecked() ? "9"
                :  "-1");

        json.put("se2q1", bi.se2q101.isChecked() ? "1"
                : bi.se2q102.isChecked() ? "2"
                : bi.se2q103.isChecked() ? "3"
                : bi.se2q104.isChecked() ? "4"
                :  "-1");

        json.put("se2q2", bi.se2q201.isChecked() ? "1"
                : bi.se2q202.isChecked() ? "2"
                : bi.se2q203.isChecked() ? "3"
                : bi.se2q204.isChecked() ? "4"
                : bi.se2q205.isChecked() ? "5"
                : bi.se2q206.isChecked() ? "6"
                : bi.se2q207.isChecked() ? "7"
                :  "-1");

        json.put("se2q3", bi.se2q3.getText().toString().trim().isEmpty() ? "-1" : bi.se2q3.getText().toString());
        json.put("se2q4", bi.se2q4.getText().toString().trim().isEmpty() ? "-1" : bi.se2q4.getText().toString());
        json.put("se2q5", bi.se2q5.getText().toString().trim().isEmpty() ? "-1" : bi.se2q5.getText().toString());
        json.put("se2q6", bi.se2q6.getText().toString().trim().isEmpty() ? "-1" : bi.se2q6.getText().toString());

        json.put("se2q7", bi.se2q701.isChecked() ? "1"
                : bi.se2q702.isChecked() ? "2"
                : bi.se2q703.isChecked() ? "3"
                :  "-1");

        json.put("se2q8", bi.se2q8.getText().toString().trim().isEmpty() ? "-1" : bi.se2q8.getText().toString());

        json.put("se2q9", bi.se2q901.isChecked() ? "1"
                : bi.se2q902.isChecked() ? "2"
                :  "-1");

        json.put("se2q10", bi.se2q1001.isChecked() ? "1"
                : bi.se2q1002.isChecked() ? "2"
                :  "-1");

        formsSES.setS2(String.valueOf(json));
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