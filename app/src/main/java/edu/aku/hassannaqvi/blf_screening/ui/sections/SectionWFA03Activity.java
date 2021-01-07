package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.jetbrains.annotations.NotNull;
import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsWFContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionWfa03Binding;
import edu.aku.hassannaqvi.blf_screening.databinding.WfaCardLayoutBinding;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;

public class SectionWFA03Activity extends AppCompatActivity {

    ActivitySectionWfa03Binding bi;
    Intent oF = null;
    ArrayList<View> wfa303;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_wfa03);
        bi.setCallback(this);
        setupSkips();


        TextWatcher textwatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Toast.makeText(getApplicationContext(), ""+ getCurrentFocus(), Toast.LENGTH_SHORT).show();

                int id = getCurrentFocus().getId();

                // Main Parent Linear Layout
                bi.fldGrpCVwfa303title1.setVisibility(View.GONE);
                // Card Under Parent Linear Layout
                bi.fldGrpWfa303.removeAllViews();

                wfa303 = new ArrayList<>();

                if (!bi.wfa302.isRangeTextValidate())
                    return;

                for (int i = 0; i < Integer.parseInt(bi.wfa302.getText().toString()); i++) {
                    ConstraintLayout view = (ConstraintLayout) LayoutInflater.from(SectionWFA03Activity.this).inflate(R.layout.wfa_card_layout, bi.fldGrpWfa303, false);
                    bi.fldGrpWfa303.addView(view);
                    wfa303.add(view);
                }

                bi.fldGrpCVwfa303title1.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        bi.wfa302.addTextChangedListener(textwatcher);
    }

    /*public void CreateCardViewProgrammatically(){

        cardview = new CardView(context);
        layoutparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardview.setLayoutParams(layoutparams);
        cardview.setRadius(15);
        cardview.setPadding(25, 25, 25, 25);
        cardview.setCardBackgroundColor(Color.MAGENTA);
        cardview.setMaxCardElevation(30);
        cardview.setMaxCardElevation(6);
        textview = new TextView(context);
        textview.setLayoutParams(layoutparams);
        textview.setText("CardView Programmatically");
        textview.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
        textview.setTextColor(Color.WHITE);
        textview.setPadding(25,25,25,25);
        textview.setGravity(Gravity.CENTER);
        cardview.addView(textview);
        relativeLayout.addView(cardview);
    }*/

    private void setupSkips() {

        radioGroupListener(bi.wfa301, bi.llGrpseca301);
        radioGroupListener(bi.wfa307, bi.llGrpseca303);
        radioGroupListener(bi.wfa310, bi.llGrpseca304);
        radioGroupListener(bi.wfa313, bi.llGrpseca305);
        radioGroupListener(bi.wfa316, bi.llGrpseca306);
        radioGroupListener(bi.wfa319, bi.llGrpseca307);
        radioGroupListener(bi.wfa322, bi.fldGrpCVwfa323);
        radioGroupListener(bi.wfa324, bi.llGrpseca308);
        radioGroupListener(bi.wfa327, bi.fldGrpCVwfa328);
        radioGroupListener(bi.wfa329, bi.llGrpseca309);
        radioGroupListener(bi.wfa333, bi.llGrpseca310);
        radioGroupListener(bi.wfa336, bi.llGrpseca311);
    }


    public void radioGroupListener(@NotNull RadioGroup rg, ViewGroup vg) {
        rg.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(vg));
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
            startActivity(new Intent(this, SectionWFA04Activity.class));
        }
    }


    public void BtnEnd() {
        oF = new Intent(this, MainActivity.class);
        startActivity(oF);
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormsWFColumn(FormsWFContract.FormsWFTable.COLUMN_SWFA03, MainApp.formsWF.sWFA03toString());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }


    private void SaveDraft() {

        MainApp.formsWF.setWfa301(bi.wfa30101.isChecked() ? "1"
                : bi.wfa30102.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa302(bi.wfa302.getText().toString().trim().isEmpty() ? "-1" : bi.wfa302.getText().toString());

        for (View view : wfa303) {
            WfaCardLayoutBinding bind = DataBindingUtil.bind(view);
            MainApp.formsWF.setWfa30301(bind.wfa30301.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30301.getText().toString());
            MainApp.formsWF.setWfa30302(bind.wfa30302.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30302.getText().toString());
            MainApp.formsWF.setWfa30303(bind.wfa30303.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30303.getText().toString());
        }


        MainApp.formsWF.setWfa304(bi.wfa30401.isChecked() ? "1"
                : bi.wfa30402.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa305(bi.wfa305.getText().toString().trim().isEmpty() ? "-1" : bi.wfa305.getText().toString());

        MainApp.formsWF.setWfa30601(bi.wfa30601.getText().toString().trim().isEmpty() ? "-1" : bi.wfa30601.getText().toString());
        MainApp.formsWF.setWfa30602(bi.wfa30602.getText().toString().trim().isEmpty() ? "-1" : bi.wfa30602.getText().toString());
        MainApp.formsWF.setWfa30603(bi.wfa30603.getText().toString().trim().isEmpty() ? "-1" : bi.wfa30603.getText().toString());

        MainApp.formsWF.setWfa307(bi.wfa30701.isChecked() ? "1"
                : bi.wfa30702.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa308(bi.wfa308.getText().toString().trim().isEmpty() ? "-1" : bi.wfa308.getText().toString());

        MainApp.formsWF.setWfa30901(bi.wfa30901.getText().toString().trim().isEmpty() ? "-1" : bi.wfa30901.getText().toString());
        MainApp.formsWF.setWfa30902(bi.wfa30902.getText().toString().trim().isEmpty() ? "-1" : bi.wfa30902.getText().toString());

        MainApp.formsWF.setWfa310(bi.wfa31001.isChecked() ? "1"
                : bi.wfa31002.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa311(bi.wfa311.getText().toString().trim().isEmpty() ? "-1" : bi.wfa311.getText().toString());

        MainApp.formsWF.setWfa31201(bi.wfa31201.getText().toString().trim().isEmpty() ? "-1" : bi.wfa31201.getText().toString());
        MainApp.formsWF.setWfa31202(bi.wfa31202.getText().toString().trim().isEmpty() ? "-1" : bi.wfa31202.getText().toString());
        MainApp.formsWF.setWfa31203(bi.wfa31203.getText().toString().trim().isEmpty() ? "-1" : bi.wfa31203.getText().toString());

        MainApp.formsWF.setWfa313(bi.wfa31301.isChecked() ? "1"
                : bi.wfa31302.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa314(bi.wfa314.getText().toString().trim().isEmpty() ? "-1" : bi.wfa314.getText().toString());

        MainApp.formsWF.setWfa31501(bi.wfa31501.getText().toString().trim().isEmpty() ? "-1" : bi.wfa31501.getText().toString());
        MainApp.formsWF.setWfa31502(bi.wfa31502.getText().toString().trim().isEmpty() ? "-1" : bi.wfa31502.getText().toString());

        MainApp.formsWF.setWfa316(bi.wfa31601.isChecked() ? "1"
                : bi.wfa31602.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa317(bi.wfa317.getText().toString().trim().isEmpty() ? "-1" : bi.wfa317.getText().toString());

        MainApp.formsWF.setWfa318(bi.wfa318.getText().toString().trim().isEmpty() ? "-1" : bi.wfa318.getText().toString());

        MainApp.formsWF.setWfa319(bi.wfa31901.isChecked() ? "1"
                : bi.wfa31902.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa320(bi.wfa320.getText().toString().trim().isEmpty() ? "-1" : bi.wfa320.getText().toString());

        MainApp.formsWF.setWfa321(bi.wfa321.getText().toString().trim().isEmpty() ? "-1" : bi.wfa321.getText().toString());

        MainApp.formsWF.setWfa322(bi.wfa32201.isChecked() ? "1"
                : bi.wfa32202.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa32301(bi.wfa32301.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32301.getText().toString());
        MainApp.formsWF.setWfa32302(bi.wfa32302.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32302.getText().toString());
        MainApp.formsWF.setWfa32303(bi.wfa32303.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32303.getText().toString());

        MainApp.formsWF.setWfa324(bi.wfa32401.isChecked() ? "1"
                : bi.wfa32402.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa325(bi.wfa325.getText().toString().trim().isEmpty() ? "-1" : bi.wfa325.getText().toString());

        MainApp.formsWF.setWfa32601(bi.wfa32601.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32601.getText().toString());
        MainApp.formsWF.setWfa32602(bi.wfa32602.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32602.getText().toString());

        MainApp.formsWF.setWfa327(bi.wfa32701.isChecked() ? "1"
                : bi.wfa32702.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa328(bi.wfa328.getText().toString().trim().isEmpty() ? "-1" : bi.wfa328.getText().toString());

        MainApp.formsWF.setWfa329(bi.wfa32901.isChecked() ? "1"
                : bi.wfa32902.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa330(bi.wfa33001.isChecked() ? "1"
                : bi.wfa33002.isChecked() ? "2"
                : bi.wfa33003.isChecked() ? "3"
                : "-1");

        MainApp.formsWF.setWfa331(bi.wfa331.getText().toString().trim().isEmpty() ? "-1" : bi.wfa331.getText().toString());

        MainApp.formsWF.setWfa33201(bi.wfa33201.getText().toString().trim().isEmpty() ? "-1" : bi.wfa33201.getText().toString());
        MainApp.formsWF.setWfa33202(bi.wfa33202.getText().toString().trim().isEmpty() ? "-1" : bi.wfa33202.getText().toString());

        MainApp.formsWF.setWfa333(bi.wfa33301.isChecked() ? "1"
                : bi.wfa33302.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa334(bi.wfa334.getText().toString().trim().isEmpty() ? "-1" : bi.wfa334.getText().toString());

        MainApp.formsWF.setWfa33501(bi.wfa33501.getText().toString().trim().isEmpty() ? "-1" : bi.wfa33501.getText().toString());
        MainApp.formsWF.setWfa33502(bi.wfa33502.getText().toString().trim().isEmpty() ? "-1" : bi.wfa33502.getText().toString());

        MainApp.formsWF.setWfa336(bi.wfa33601.isChecked() ? "1"
                : bi.wfa33602.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa337(bi.wfa337.getText().toString().trim().isEmpty() ? "-1" : bi.wfa337.getText().toString());

        MainApp.formsWF.setWfa338(bi.wfa338.getText().toString().trim().isEmpty() ? "-1" : bi.wfa338.getText().toString());

        MainApp.formsWF.setWfa339(bi.wfa339.getText().toString().trim().isEmpty() ? "-1" : bi.wfa339.getText().toString());

    }


    private boolean formValidation() {

        if (!Validator.emptyCheckingContainer(this, bi.GrpName)) {
            return false;
        }

        /*if (Integer.parseInt(bi.wfa30301.getText().toString()) == 0 && Integer.parseInt(bi.wfa30302.getText().toString()) == 0 && Integer.parseInt(bi.wfa30303.getText().toString()) == 0) {
            Toast.makeText(this, "Sum of minutes, hours and days cannot be zero", Toast.LENGTH_LONG).show();
            return false;
        }*/

        return true;

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}