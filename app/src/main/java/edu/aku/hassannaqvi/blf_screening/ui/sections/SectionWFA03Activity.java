package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.nfc.cardemulation.CardEmulation;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.edittextpicker.aliazaz.EditTextPicker;
import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsWFContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionWfa03Binding;
import edu.aku.hassannaqvi.blf_screening.databinding.WfaCardLayoutBinding;
import edu.aku.hassannaqvi.blf_screening.models.SubModel;
import edu.aku.hassannaqvi.blf_screening.models.WFA303Model;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;

public class SectionWFA03Activity extends AppCompatActivity {

    ActivitySectionWfa03Binding bi;
    Intent oF = null;
    ArrayMap<String, Integer> myIDs = new ArrayMap<>();
    ArrayList<SubModel> disease;
    String week, delivery_date, fupdate;
    int col_id;
    int wfa106;
    String FD;

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

                EditTextPicker edt;
                CardView cv;
                LinearLayout ll;

                String type;

                if (bi.wfa302.isFocused()) {
                    edt = bi.wfa302;
                    cv = bi.fldGrpCVwfa303title1;
                    ll = bi.llwfa303;
                    type = "MHD";
                } else if (bi.wfa305.isFocused()) {
                    edt = bi.wfa305;
                    cv = bi.fldGrpCVwfa306title1;
                    ll = bi.llwfa306;
                    type = "MHD";
                } else if (bi.wfa308.isFocused()) {
                    edt = bi.wfa308;
                    cv = bi.fldGrpCVwfa309title1;
                    ll = bi.llwfa309;
                    type = "MS";
                } else if (bi.wfa325.isFocused()) {
                    edt = bi.wfa325;
                    cv = bi.fldGrpCVwfa326title1;
                    ll = bi.llwfa326;
                    type = "MS";
                } else if (bi.wfa314.isFocused()) {
                    edt = bi.wfa314;
                    cv = bi.fldGrpCVwfa315title1;
                    ll = bi.llwfa315;
                    type = "MH";
                } else if (bi.wfa331.isFocused()) {
                    edt = bi.wfa331;
                    cv = bi.fldGrpCVwfa332title1;
                    ll = bi.llwfa332;
                    type = "HD";
                } else if (bi.wfa334.isFocused()) {
                    edt = bi.wfa334;
                    cv = bi.fldGrpCVwfa335title1;
                    ll = bi.llwfa335;
                    type = "HD";
                } else if (bi.wfa337.isFocused()) {
                    edt = bi.wfa337;
                    cv = bi.fldGrpCVwfa338title1;
                    ll = bi.llwfa338;
                    type = "S";
                } else {
                    edt = null;
                    cv = null;
                    ll = null;
                    type = "MHD0";
                }

                if (s.length() > 0) {

                    if (!edt.isRangeTextValidate())

                    Clear.clearAllFields(ll);
                    ll.removeAllViews();
                    cv.setVisibility(View.GONE);

                    for (int i = 1; i <= Integer.parseInt(edt.getText().toString()); i++) {

                        ConstraintLayout view = (ConstraintLayout) LayoutInflater.from(SectionWFA03Activity.this).inflate(R.layout.wfa_card_layout, ll, false);
                        ll.addView(view);

                        CardView cv2 = (CardView) view.getChildAt(0);
                        LinearLayout ll2 = (LinearLayout) cv2.getChildAt(0);

                        if (type.equals("MHD")) {
                            LinearLayout ll_S = (LinearLayout) ll2.getChildAt(3);
                            ll_S.setVisibility(View.GONE);
                        } else if (type.equals("MS")) {
                            LinearLayout ll_H = (LinearLayout) ll2.getChildAt(1);
                            LinearLayout ll_D = (LinearLayout) ll2.getChildAt(2);
                            ll_H.setVisibility(View.GONE);
                            ll_D.setVisibility(View.GONE);
                        } else if (type.equals("MH")) {
                            LinearLayout ll_D = (LinearLayout) ll2.getChildAt(2);
                            LinearLayout ll_S = (LinearLayout) ll2.getChildAt(3);
                            ll_D.setVisibility(View.GONE);
                            ll_S.setVisibility(View.GONE);
                        } else if (type.equals("HD")) {
                            LinearLayout ll_M = (LinearLayout) ll2.getChildAt(0);
                            LinearLayout ll_S = (LinearLayout) ll2.getChildAt(3);
                            ll_M.setVisibility(View.GONE);
                            ll_S.setVisibility(View.GONE);
                        } else if (type.equals("D")) {
                            LinearLayout ll_M = (LinearLayout) ll2.getChildAt(0);
                            LinearLayout ll_H = (LinearLayout) ll2.getChildAt(1);
                            LinearLayout ll_D = (LinearLayout) ll2.getChildAt(2);
                            LinearLayout ll_S = (LinearLayout) ll2.getChildAt(3);
                            ll_M.setVisibility(View.GONE);
                            ll_H.setVisibility(View.GONE);
                            ll_S.setVisibility(View.GONE);
                        } else if (type.equals("S")) {
                            LinearLayout ll_M = (LinearLayout) ll2.getChildAt(0);
                            LinearLayout ll_H = (LinearLayout) ll2.getChildAt(1);
                            LinearLayout ll_D = (LinearLayout) ll2.getChildAt(2);
                            ll_M.setVisibility(View.GONE);
                            ll_H.setVisibility(View.GONE);
                            ll_D.setVisibility(View.GONE);
                        }
                    }
                    cv.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Length is not Zero", Toast.LENGTH_LONG).show();
                } else {
                    if (ll != null) {
                        Clear.clearAllFields(ll);
                        ll.removeAllViews();
                        cv.setVisibility(View.GONE);

                        Toast.makeText(getApplicationContext(), "Length is Zero", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        // Type MHD
        bi.wfa302.addTextChangedListener(textwatcher);
        bi.wfa305.addTextChangedListener(textwatcher);
        //bi.wfa311.addTextChangedListener(textwatcher);

        // Type MS
        bi.wfa308.addTextChangedListener(textwatcher);
        bi.wfa325.addTextChangedListener(textwatcher);

        // Type MH
        bi.wfa314.addTextChangedListener(textwatcher);

        // Type HD
        bi.wfa331.addTextChangedListener(textwatcher);
        bi.wfa334.addTextChangedListener(textwatcher);

        // Type D
        //bi.wfa317.addTextChangedListener(textwatcher);
        //bi.wfa320.addTextChangedListener(textwatcher);

        // Type S
        bi.wfa337.addTextChangedListener(textwatcher);

        Intent intent = getIntent();
        week = intent.getStringExtra("week");
        delivery_date = intent.getStringExtra("delivery_date");
        fupdate = intent.getStringExtra("fupdate");
        col_id = intent.getIntExtra("col_id", 0);
        wfa106 = intent.getIntExtra("wfa106", 0);
        FD = intent.getStringExtra("FD");

        bi.llwfa31204.setVisibility(View.GONE);

        bi.llwfa31801.setVisibility(View.GONE);
        bi.llwfa31802.setVisibility(View.GONE);
        bi.llwfa31804.setVisibility(View.GONE);

        bi.llwfa32101.setVisibility(View.GONE);
        bi.llwfa32102.setVisibility(View.GONE);
        bi.llwfa32104.setVisibility(View.GONE);

        bi.llwfa32304.setVisibility(View.GONE);
    }

    private void setupSkips() {

        // same layout style
        radioGroupListener(bi.wfa301, bi.llGrpseca301);
        radioGroupListener(bi.wfa304, bi.llGrpseca302);
        radioGroupListener(bi.wfa307, bi.llGrpseca303);
        radioGroupListener(bi.wfa310, bi.llGrpseca304);
        radioGroupListener(bi.wfa313, bi.llGrpseca305);
        radioGroupListener(bi.wfa316, bi.llGrpseca306);
        radioGroupListener(bi.wfa319, bi.llGrpseca307);
        radioGroupListener(bi.wfa324, bi.llGrpseca308);
        radioGroupListener(bi.wfa333, bi.llGrpseca310);
        radioGroupListener(bi.wfa336, bi.llGrpseca311);

        // different layout style
        radioGroupListener(bi.wfa329, bi.llGrpseca309);
        radioGroupListener(bi.wfa322, bi.fldGrpCVwfa323);
        radioGroupListener(bi.wfa327, bi.fldGrpCVwfa328);
    }

    public void radioGroupListener(@NotNull RadioGroup rg, ViewGroup vg) {
        rg.setOnCheckedChangeListener((radioGroup, i) -> {

            Clear.clearAllFields(vg);

            if (vg == bi.llGrpseca301
                    || vg == bi.llGrpseca302
                    || vg == bi.llGrpseca303
                    || vg == bi.llGrpseca304
                    || vg == bi.llGrpseca305
                    || vg == bi.llGrpseca306
                    || vg == bi.llGrpseca307
                    || vg == bi.llGrpseca308
                    || vg == bi.llGrpseca310
                    || vg == bi.llGrpseca311) {

                vg.getChildAt(1).setVisibility(View.GONE);
                Toast.makeText(this, "302 to 311", Toast.LENGTH_SHORT).show();
            } else if (vg == bi.llGrpseca309) {
                vg.getChildAt(2).setVisibility(View.GONE);
                Toast.makeText(this, "309", Toast.LENGTH_SHORT).show();
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
            startActivity(new Intent(this, SectionWFA04Activity.class).putExtra("week", week).putExtra("delivery_date", delivery_date).putExtra("fupdate", fupdate).putExtra("col_id", col_id).putExtra("wfa106", wfa106).putExtra("FD", FD));
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
            for (byte i = 0; i < disease.size(); i++) {
                long rowValue = db.insertDisease(disease.get(i).getDisease());
                if (rowValue > 0) {
                    String duid = db.getDiseaseUID("diseases", rowValue);
                    for (byte j = 0; j < disease.get(i).getSubmodel().size(); j++) {
                        long epValue = db.insertEpisode(disease.get(i).getSubmodel().get(j), duid);
                        if (epValue < 0) {
                            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_LONG).show();
                            return false;
                        }
                    }
                } else {
                    Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_LONG).show();
                    return false;
                }
            }
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR! IF", Toast.LENGTH_SHORT).show();
            return false;
        }

    }


    private void SaveDraft() {

        MainApp.formsWF.setWfa301(bi.wfa30101.isChecked() ? "1"
                : bi.wfa30102.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa302(bi.wfa302.getText().toString().trim().isEmpty() ? "-1" : bi.wfa302.getText().toString());

        MainApp.formsWF.setWfa304(bi.wfa30401.isChecked() ? "1"
                : bi.wfa30402.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa305(bi.wfa305.getText().toString().trim().isEmpty() ? "-1" : bi.wfa305.getText().toString());

        /*MainApp.formsWF.setWfa30601(bi.wfa30601.getText().toString().trim().isEmpty() ? "-1" : bi.wfa30601.getText().toString());
        MainApp.formsWF.setWfa30602(bi.wfa30602.getText().toString().trim().isEmpty() ? "-1" : bi.wfa30602.getText().toString());
        MainApp.formsWF.setWfa30603(bi.wfa30603.getText().toString().trim().isEmpty() ? "-1" : bi.wfa30603.getText().toString());*/

        MainApp.formsWF.setWfa307(bi.wfa30701.isChecked() ? "1"
                : bi.wfa30702.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa308(bi.wfa308.getText().toString().trim().isEmpty() ? "-1" : bi.wfa308.getText().toString());

        /*MainApp.formsWF.setWfa30901(bi.wfa30901.getText().toString().trim().isEmpty() ? "-1" : bi.wfa30901.getText().toString());
        MainApp.formsWF.setWfa30902(bi.wfa30902.getText().toString().trim().isEmpty() ? "-1" : bi.wfa30902.getText().toString());*/

        MainApp.formsWF.setWfa310(bi.wfa31001.isChecked() ? "1"
                : bi.wfa31002.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa311(bi.wfa311.getText().toString().trim().isEmpty() ? "-1" : bi.wfa311.getText().toString());

        /*MainApp.formsWF.setWfa31201(bi.wfa31201.getText().toString().trim().isEmpty() ? "-1" : bi.wfa31201.getText().toString());
        MainApp.formsWF.setWfa31202(bi.wfa31202.getText().toString().trim().isEmpty() ? "-1" : bi.wfa31202.getText().toString());
        MainApp.formsWF.setWfa31203(bi.wfa31203.getText().toString().trim().isEmpty() ? "-1" : bi.wfa31203.getText().toString());*/

        MainApp.formsWF.setWfa313(bi.wfa31301.isChecked() ? "1"
                : bi.wfa31302.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa314(bi.wfa314.getText().toString().trim().isEmpty() ? "-1" : bi.wfa314.getText().toString());

        /*MainApp.formsWF.setWfa31501(bi.wfa31501.getText().toString().trim().isEmpty() ? "-1" : bi.wfa31501.getText().toString());
        MainApp.formsWF.setWfa31502(bi.wfa31502.getText().toString().trim().isEmpty() ? "-1" : bi.wfa31502.getText().toString());*/

        MainApp.formsWF.setWfa316(bi.wfa31601.isChecked() ? "1"
                : bi.wfa31602.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa317(bi.wfa317.getText().toString().trim().isEmpty() ? "-1" : bi.wfa317.getText().toString());

        //MainApp.formsWF.setWfa318(bi.wfa318.getText().toString().trim().isEmpty() ? "-1" : bi.wfa318.getText().toString());

        MainApp.formsWF.setWfa319(bi.wfa31901.isChecked() ? "1"
                : bi.wfa31902.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa320(bi.wfa320.getText().toString().trim().isEmpty() ? "-1" : bi.wfa320.getText().toString());

        //MainApp.formsWF.setWfa321(bi.wfa321.getText().toString().trim().isEmpty() ? "-1" : bi.wfa321.getText().toString());

        MainApp.formsWF.setWfa322(bi.wfa32201.isChecked() ? "1"
                : bi.wfa32202.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa324(bi.wfa32401.isChecked() ? "1"
                : bi.wfa32402.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa325(bi.wfa325.getText().toString().trim().isEmpty() ? "-1" : bi.wfa325.getText().toString());

        //MainApp.formsWF.setWfa32601(bi.wfa32601.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32601.getText().toString());
        //MainApp.formsWF.setWfa32602(bi.wfa32602.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32602.getText().toString());

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

        /*MainApp.formsWF.setWfa33201(bi.wfa33201.getText().toString().trim().isEmpty() ? "-1" : bi.wfa33201.getText().toString());
        MainApp.formsWF.setWfa33202(bi.wfa33202.getText().toString().trim().isEmpty() ? "-1" : bi.wfa33202.getText().toString());*/

        MainApp.formsWF.setWfa333(bi.wfa33301.isChecked() ? "1"
                : bi.wfa33302.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa334(bi.wfa334.getText().toString().trim().isEmpty() ? "-1" : bi.wfa334.getText().toString());

        /*MainApp.formsWF.setWfa33501(bi.wfa33501.getText().toString().trim().isEmpty() ? "-1" : bi.wfa33501.getText().toString());
        MainApp.formsWF.setWfa33502(bi.wfa33502.getText().toString().trim().isEmpty() ? "-1" : bi.wfa33502.getText().toString());*/

        MainApp.formsWF.setWfa336(bi.wfa33601.isChecked() ? "1"
                : bi.wfa33602.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa337(bi.wfa337.getText().toString().trim().isEmpty() ? "-1" : bi.wfa337.getText().toString());
        //MainApp.formsWF.setWfa338(bi.wfa338.getText().toString().trim().isEmpty() ? "-1" : bi.wfa338.getText().toString());
        MainApp.formsWF.setWfa339(bi.wfa339.getText().toString().trim().isEmpty() ? "-1" : bi.wfa339.getText().toString());

        // Minutes Hours and Days
        disease = new ArrayList<>();
        int wfa303Count = bi.llwfa303.getChildCount();
        if (wfa303Count > 0) {
            ArrayList<WFA303Model> subModels = new ArrayList<>();
            for (int i = 0; i < wfa303Count; i++) {
                WfaCardLayoutBinding bind = DataBindingUtil.bind(bi.llwfa303.getChildAt(i));
                if (bind != null) {
                    subModels.add(
                            new WFA303Model(
                                    MainApp.formsWF.getSysdate(),
                                    bind.wfa30301.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30301.getText().toString(),
                                    bind.wfa30302.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30302.getText().toString(),
                                    bind.wfa30303.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30303.getText().toString(),
                                    bind.wfa30304.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30304.getText().toString()
                            )
                    );
                }
            }
            disease.add(new SubModel("wfa303", subModels));
        }

        int wfa306Count = bi.llwfa306.getChildCount();
        if (wfa306Count > 0) {
            ArrayList<WFA303Model> subModels = new ArrayList<>();
            for (int i = 0; i < wfa306Count; i++) {
                WfaCardLayoutBinding bind = DataBindingUtil.bind(bi.llwfa306.getChildAt(i));
                if (bind != null) {
                    subModels.add(
                            new WFA303Model(
                                    MainApp.formsWF.getSysdate(),
                                    bind.wfa30301.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30301.getText().toString(),
                                    bind.wfa30302.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30302.getText().toString(),
                                    bind.wfa30303.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30303.getText().toString(),
                                    bind.wfa30304.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30304.getText().toString()
                            )
                    );
                }
            }
            disease.add(new SubModel("wfa306", subModels));
        }


        int wfa309Count = bi.llwfa309.getChildCount();
        if (wfa309Count > 0) {
            ArrayList<WFA303Model> subModels = new ArrayList<>();
            for (int i = 0; i < wfa309Count; i++) {
                WfaCardLayoutBinding bind = DataBindingUtil.bind(bi.llwfa309.getChildAt(i));
                if (bind != null) {
                    subModels.add(
                            new WFA303Model(
                                    MainApp.formsWF.getSysdate(),
                                    bind.wfa30301.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30301.getText().toString(),
                                    bind.wfa30302.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30302.getText().toString(),
                                    bind.wfa30303.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30303.getText().toString(),
                                    bind.wfa30304.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30304.getText().toString()
                            )
                    );
                }
            }
            disease.add(new SubModel("wfa309", subModels));
        }

        /*int wfa312Count = bi.llwfa312.getChildCount();
        if (wfa312Count > 0) {
            ArrayList<WFA303Model> subModels = new ArrayList<>();
            for (int i = 0; i < wfa312Count; i++) {
                WfaCardLayoutBinding bind = DataBindingUtil.bind(bi.llwfa312.getChildAt(i));
                if (bind != null) {
                    subModels.add(
                            new WFA303Model(
                                    MainApp.formsWF.getSysdate(),
                                    bind.wfa30301.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30301.getText().toString(),
                                    bind.wfa30302.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30302.getText().toString(),
                                    bind.wfa30303.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30303.getText().toString(),
                                    bind.wfa30304.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30304.getText().toString()
                            )
                    );
                }
            }
            disease.add(new SubModel("wfa312", subModels));
        }*/


        int wfa315Count = bi.llwfa315.getChildCount();
        if (wfa315Count > 0) {
            ArrayList<WFA303Model> subModels = new ArrayList<>();
            for (int i = 0; i < wfa315Count; i++) {
                WfaCardLayoutBinding bind = DataBindingUtil.bind(bi.llwfa315.getChildAt(i));
                if (bind != null) {
                    subModels.add(
                            new WFA303Model(
                                    MainApp.formsWF.getSysdate(),
                                    bind.wfa30301.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30301.getText().toString(),
                                    bind.wfa30302.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30302.getText().toString(),
                                    bind.wfa30303.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30303.getText().toString(),
                                    bind.wfa30304.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30304.getText().toString()
                            )
                    );
                }
            }
            disease.add(new SubModel("wfa315", subModels));
        }

        if (bi.wfa31001.isChecked()) {
            ArrayList<WFA303Model> subModels = new ArrayList<>();
            subModels.add(
                    new WFA303Model(
                            MainApp.formsWF.getSysdate(),
                            bi.wfa31201.getText().toString().trim().isEmpty() ? "-1" : bi.wfa31201.getText().toString(),
                            bi.wfa31202.getText().toString().trim().isEmpty() ? "-1" : bi.wfa31202.getText().toString(),
                            bi.wfa31203.getText().toString().trim().isEmpty() ? "-1" : bi.wfa31203.getText().toString(),
                            bi.wfa31204.getText().toString().trim().isEmpty() ? "-1" : bi.wfa31203.getText().toString()
                    )
            );
            disease.add(new SubModel("wfa312", subModels));
        }

        if (bi.wfa31601.isChecked()) {
            ArrayList<WFA303Model> subModels = new ArrayList<>();
            subModels.add(
                    new WFA303Model(
                            MainApp.formsWF.getSysdate(),
                            bi.wfa32101.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32101.getText().toString(),
                            bi.wfa32102.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32102.getText().toString(),
                            bi.wfa32103.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32103.getText().toString(),
                            bi.wfa32104.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32103.getText().toString()
                    )
            );
            disease.add(new SubModel("wfa321", subModels));
        }

        if (bi.wfa31901.isChecked()) {
            ArrayList<WFA303Model> subModels = new ArrayList<>();
            subModels.add(
                    new WFA303Model(
                            MainApp.formsWF.getSysdate(),
                            bi.wfa32101.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32101.getText().toString(),
                            bi.wfa32102.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32102.getText().toString(),
                            bi.wfa32103.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32103.getText().toString(),
                            bi.wfa32104.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32103.getText().toString()
                    )
            );
            disease.add(new SubModel("wfa318", subModels));
        }

        if (bi.wfa32201.isChecked()) {
            ArrayList<WFA303Model> subModels = new ArrayList<>();
            subModels.add(
                    new WFA303Model(
                            MainApp.formsWF.getSysdate(),
                            bi.wfa32301.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32301.getText().toString(),
                            bi.wfa32302.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32302.getText().toString(),
                            bi.wfa32303.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32303.getText().toString(),
                            bi.wfa32304.getText().toString().trim().isEmpty() ? "-1" : bi.wfa32303.getText().toString()
                    )
            );
            disease.add(new SubModel("wfa323", subModels));
        }

        int wfa326Count = bi.llwfa326.getChildCount();
        if (wfa326Count > 0) {
            ArrayList<WFA303Model> subModels = new ArrayList<>();
            for (int i = 0; i < wfa326Count; i++) {
                WfaCardLayoutBinding bind = DataBindingUtil.bind(bi.llwfa326.getChildAt(i));
                if (bind != null) {
                    subModels.add(
                            new WFA303Model(
                                    MainApp.formsWF.getSysdate(),
                                    bind.wfa30301.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30301.getText().toString(),
                                    bind.wfa30302.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30302.getText().toString(),
                                    bind.wfa30303.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30303.getText().toString(),
                                    bind.wfa30304.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30304.getText().toString()
                            )
                    );
                }
            }
            disease.add(new SubModel("wfa326", subModels));
        }

        int wfa332Count = bi.llwfa332.getChildCount();
        if (wfa332Count > 0) {
            ArrayList<WFA303Model> subModels = new ArrayList<>();
            for (int i = 0; i < wfa332Count; i++) {
                WfaCardLayoutBinding bind = DataBindingUtil.bind(bi.llwfa332.getChildAt(i));
                if (bind != null) {
                    subModels.add(
                            new WFA303Model(
                                    MainApp.formsWF.getSysdate(),
                                    bind.wfa30301.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30301.getText().toString(),
                                    bind.wfa30302.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30302.getText().toString(),
                                    bind.wfa30303.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30303.getText().toString(),
                                    bind.wfa30304.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30304.getText().toString()
                            )
                    );
                }
            }
            disease.add(new SubModel("wfa332", subModels));
        }

        int wfa335Count = bi.llwfa335.getChildCount();
        if (wfa335Count > 0) {
            ArrayList<WFA303Model> subModels = new ArrayList<>();
            for (int i = 0; i < wfa335Count; i++) {
                WfaCardLayoutBinding bind = DataBindingUtil.bind(bi.llwfa335.getChildAt(i));
                if (bind != null) {
                    subModels.add(
                            new WFA303Model(
                                    MainApp.formsWF.getSysdate(),
                                    bind.wfa30301.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30301.getText().toString(),
                                    bind.wfa30302.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30302.getText().toString(),
                                    bind.wfa30303.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30303.getText().toString(),
                                    bind.wfa30304.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30304.getText().toString()
                            )
                    );
                }
            }
            disease.add(new SubModel("wfa335", subModels));
        }

        int wfa338Count = bi.llwfa338.getChildCount();
        if (wfa338Count > 0) {
            ArrayList<WFA303Model> subModels = new ArrayList<>();
            for (int i = 0; i < wfa338Count; i++) {
                WfaCardLayoutBinding bind = DataBindingUtil.bind(bi.llwfa338.getChildAt(i));
                if (bind != null) {
                    subModels.add(
                            new WFA303Model(
                                    MainApp.formsWF.getSysdate(),
                                    bind.wfa30301.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30301.getText().toString(),
                                    bind.wfa30302.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30302.getText().toString(),
                                    bind.wfa30303.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30303.getText().toString(),
                                    bind.wfa30304.getText().toString().trim().isEmpty() ? "-1" : bind.wfa30304.getText().toString()
                            )
                    );
                }
            }
            disease.add(new SubModel("wfa338", subModels));
        }

    }


    private boolean formValidation() {

        if (!Validator.emptyCheckingContainer(this, bi.GrpName)) {
            return false;
        }

        CardView cardWfa303 = (CardView) bi.llwfa303.getParent().getParent();
        if (cardWfa303.getVisibility() == View.VISIBLE && !checkZeroFilled(bi.llwfa303)) {
            Toast.makeText(getApplicationContext(), "WFA303: sum of minutes, hours and days must be greater than 0", Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "Visibility: " + cardWfa303.getVisibility(), Toast.LENGTH_LONG).show();
            return false;
        }
        CardView cardWfa306 = (CardView) bi.llwfa306.getParent().getParent();
        if (cardWfa306.getVisibility() == View.VISIBLE && !checkZeroFilled(bi.llwfa306)) {
            Toast.makeText(getApplicationContext(), "WFA306: sum of minutes, hours and days must be greater than 0", Toast.LENGTH_LONG).show();
            return false;
        }
        CardView cardWfa309 = (CardView) bi.llwfa309.getParent().getParent();
        if (cardWfa309.getVisibility() == View.VISIBLE && !checkZeroFilled(bi.llwfa309)) {
            Toast.makeText(getApplicationContext(), "WFA309: sum of minutes, hours and days must be greater than 0", Toast.LENGTH_LONG).show();
            return false;
        }
        CardView cardWfa315 = (CardView) bi.llwfa315.getParent().getParent();
        if (cardWfa315.getVisibility() == View.VISIBLE && !checkZeroFilled(bi.llwfa315)) {
            Toast.makeText(getApplicationContext(), "WFA315: sum of minutes, hours and days must be greater than 0", Toast.LENGTH_LONG).show();
            return false;
        }

        if (bi.wfa31201.getText().toString().trim().equals("0") && bi.wfa31202.getText().toString().trim().equals("0") && bi.wfa31203.getText().toString().trim().equals("0")) {
            Toast.makeText(getApplicationContext(), "WFA318: sum of minutes, hours and days must be greater than 0", Toast.LENGTH_LONG).show();
            return false;
        }
        if (bi.wfa31803.getText().toString().trim().equals("0")) {
            Toast.makeText(getApplicationContext(), "WFA318: number of days must be greater than 0", Toast.LENGTH_LONG).show();
            return false;
        }
        if (bi.wfa32103.getText().toString().trim().equals("0")) {
            Toast.makeText(getApplicationContext(), "WFA321: number of days must be greater than 0", Toast.LENGTH_LONG).show();
            return false;
        }
        if (bi.wfa32301.getText().toString().trim().equals("0") && bi.wfa32302.getText().toString().trim().equals("0") && bi.wfa32303.getText().toString().trim().equals("0")) {
            Toast.makeText(getApplicationContext(), "WFA323: sum of minutes, hours and days must be greater than 0", Toast.LENGTH_LONG).show();
            return false;
        }

        CardView cardWfa326 = (CardView) bi.llwfa326.getParent().getParent();
        if (cardWfa326.getVisibility() == View.VISIBLE && !checkZeroFilled(bi.llwfa326)) {
            Toast.makeText(getApplicationContext(), "WFA326: sum of minutes, hours and days must be greater than 0", Toast.LENGTH_LONG).show();
            return false;
        }
        CardView cardWfa332 = (CardView) bi.llwfa332.getParent().getParent();
        if (cardWfa332.getVisibility() == View.VISIBLE && !checkZeroFilled(bi.llwfa332)) {
            Toast.makeText(getApplicationContext(), "WFA332: sum of minutes, hours and days must be greater than 0", Toast.LENGTH_LONG).show();
            return false;
        }
        CardView cardWfa335 = (CardView) bi.llwfa335.getParent().getParent();
        if (cardWfa335.getVisibility() == View.VISIBLE && !checkZeroFilled(bi.llwfa335)) {
            Toast.makeText(getApplicationContext(), "WFA335: sum of minutes, hours and days must be greater than 0", Toast.LENGTH_LONG).show();
            return false;
        }
        CardView cardWfa338 = (CardView) bi.llwfa338.getParent().getParent();
        if (cardWfa338.getVisibility() == View.VISIBLE && !checkZeroFilled(bi.llwfa338)) {
            Toast.makeText(getApplicationContext(), "WFA338: sum of minutes, hours and days must be greater than 0", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public boolean checkZeroFilled(LinearLayout ll) {

        for (int i = 0; i < ll.getChildCount(); i++) {

            int total, M, H, D, S;

            ConstraintLayout cl = (ConstraintLayout) ll.getChildAt(i);
            CardView cv = (CardView) cl.getChildAt(0);
            LinearLayout llp = (LinearLayout) cv.getChildAt(0);

            LinearLayout llM = (LinearLayout) llp.getChildAt(0);
            LinearLayout llH = (LinearLayout) llp.getChildAt(1);
            LinearLayout llD = (LinearLayout) llp.getChildAt(2);
            LinearLayout llS = (LinearLayout) llp.getChildAt(3);
            EditTextPicker etM = (EditTextPicker) llM.getChildAt(1);
            EditTextPicker etH = (EditTextPicker) llH.getChildAt(1);
            EditTextPicker etD = (EditTextPicker) llD.getChildAt(1);
            EditTextPicker etS = (EditTextPicker) llS.getChildAt(1);

            if (etM.getText().toString().trim().isEmpty()) {
                M = 0;
            } else {
                M = Integer.parseInt(etM.getText().toString());
            }
            if (etH.getText().toString().trim().isEmpty()) {
                H = 0;
            } else {
                H = Integer.parseInt(etH.getText().toString());
            }
            if (etD.getText().toString().trim().isEmpty()) {
                D = 0;
            } else {
                D = Integer.parseInt(etD.getText().toString());
            }
            if (etS.getText().toString().trim().isEmpty()) {
                S = 0;
            } else {
                S = Integer.parseInt(etS.getText().toString());
            }
            total = M + H + D + S;

            if (total == 0) {
                ll.setFocusable(true);
                ll.setFocusableInTouchMode(true);
                ll.requestFocus();
                return false;
            }
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}