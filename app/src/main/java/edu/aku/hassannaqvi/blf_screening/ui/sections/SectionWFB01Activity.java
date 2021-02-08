package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.edittextpicker.aliazaz.EditTextPicker;
import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsWFContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionWfb01Binding;
import edu.aku.hassannaqvi.blf_screening.databinding.Wfb108CardBinding;
import edu.aku.hassannaqvi.blf_screening.models.SubModel;
import edu.aku.hassannaqvi.blf_screening.models.WFA303Model;
import edu.aku.hassannaqvi.blf_screening.models.WFB108;
import edu.aku.hassannaqvi.blf_screening.models.WFBSubModel;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;
import io.reactivex.disposables.CompositeDisposable;

public class SectionWFB01Activity extends AppCompatActivity {

    ActivitySectionWfb01Binding bi;
    Intent oF = null;
    String week, delivery_date;
    int col_id;
    int wfa106;
    int wfa108Days;
    String FD;
    ArrayList<WFBSubModel> wfb108;
    private ArrayList<View> collectionOfViews = new ArrayList();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        week = intent.getStringExtra("week");
        delivery_date = intent.getStringExtra("delivery_date");
        col_id = intent.getIntExtra("col_id", 0);
        wfa106 = intent.getIntExtra("wfa106", 0);
        FD = intent.getStringExtra("FD");

        String[] weekarray = {"1", "2", "3", "4", "5", "6", "20", "10"};
        if (!Arrays.asList(weekarray).contains(week)) {
            startActivity(new Intent(this, SectionWFB02Activity.class).putExtra("week", week).putExtra("col_id", col_id).putExtra("wfa106", wfa106).putExtra("FD", FD).putExtra("delivery_date", delivery_date));
        }


        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_wfb01);
        bi.setCallback(this);
        setupSkips();


        TextWatcher textwatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!bi.wfb104.getText().toString().equals("") && !bi.wfb105.getText().toString().equals("") && Integer.parseInt(bi.wfb105.getText().toString()) < Integer.parseInt(bi.wfb104.getText().toString())) {
                    bi.fldGrpCVwfi07.setVisibility(View.VISIBLE);
                } else {
                    Clear.clearAllFields(bi.fldGrpCVwfi07);
                    bi.fldGrpCVwfi07.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        // Type MHD
        bi.wfb105.addTextChangedListener(textwatcher);

        String date1 = "01-01-2020";
        String date2 = "03-01-2020";
        //wfa108Days = date2 - date1;

        wfa108Days = 3;

        LinearLayout ll;
        ll = bi.llwfb108;
        for (int i = 1; i <= wfa108Days; i++) {
            ConstraintLayout view = (ConstraintLayout) LayoutInflater.from(SectionWFB01Activity.this).inflate(R.layout.wfb108_card, ll, false);
            Wfb108CardBinding cardBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.wfb108_card, null, false);


            /*CardView cv_outer = (CardView) view.getChildAt(0);
            LinearLayout ll_outer = (LinearLayout) cv_outer.getChildAt(0);

            // Card 1
            CardView cv_inner = (CardView) ll_outer.getChildAt(0);
            // LinearLayout 2
            LinearLayout ll_inner = (LinearLayout) ll_outer.getChildAt(1);
            // Card 3
            CardView cv_inner2 = (CardView) ll_outer.getChildAt(2);

            // 1 elaboration
            LinearLayout ll_inner_inner = (LinearLayout) cv_inner.getChildAt(0);
            RelativeLayout rl = (RelativeLayout) ll_inner_inner.getChildAt(0);
            TextView tv_a = (TextView) rl.getChildAt(0);
            tv_a.setText("Day " + i + ":Have u given the supplement");*/

            cardBinding.qtxtWfb1081a.setText(String.format(Locale.ENGLISH, "Day %d :Have u given the supplement", i));

//            RadioGroup rg = (RadioGroup) ll_inner_inner.getChildAt(1);
            cardBinding.wfb1081a.setOnCheckedChangeListener((group, id) -> {
                if (id == cardBinding.wfb1081a02.getId()) {
                    Clear.clearAllFields(cardBinding.llgrpsecb01);
                    cardBinding.llgrpsecb01.setVisibility(View.GONE);
                    cardBinding.fldGrpCVwfb1081d.setVisibility(View.VISIBLE);
                } else {
                    cardBinding.llgrpsecb01.setVisibility(View.VISIBLE);
                    Clear.clearAllFields(cardBinding.fldGrpCVwfb1081d);
                    cardBinding.fldGrpCVwfb1081d.setVisibility(View.GONE);
                }
            });

            // 2 elaboration
            /*CardView qb_cv = (CardView) ll_inner.getChildAt(0);
            CardView qb_cv2 = (CardView) ll_inner.getChildAt(1);
            LinearLayout qb_ll = (LinearLayout) qb_cv.getChildAt(0);
            LinearLayout qc_ll = (LinearLayout) qb_cv2.getChildAt(0);
            RelativeLayout qc_rl = (RelativeLayout) qc_ll.getChildAt(0);
            TextView tv_c = (TextView) qc_rl.getChildAt(0);
            tv_c.setText("Day " + i + ":If partial, why?");*/
            cardBinding.qtxtWfb1081b.setText(String.format(Locale.ENGLISH, "Day %d :How much quantity have you given?", i));
            /*RelativeLayout qb_rl = (RelativeLayout) qb_ll.getChildAt(0);
            TextView tv_b = (TextView) qb_rl.getChildAt(0);
            tv_b.setText("Day " + i + ":How much quantity have you given?");*/
            cardBinding.qtxtWfb1081c.setText(String.format(Locale.ENGLISH, "Day %d :If partial, why?", i));

//            RadioGroup qb_rg = (RadioGroup) qb_ll.getChildAt(1);
            cardBinding.wfb1081b.setOnCheckedChangeListener((group, id) -> {
                if (id == cardBinding.wfb1081b01.getId()) {
                    Clear.clearAllFields(cardBinding.fldGrpCVwfb1081c);
                    cardBinding.fldGrpCVwfb1081c.setVisibility(View.GONE);
                } else {
                    cardBinding.fldGrpCVwfb1081c.setVisibility(View.VISIBLE);
                }
            });


            // 3 elaboration
            /*LinearLayout qd_ll = (LinearLayout) cv_inner2.getChildAt(0);
            RelativeLayout qd_rl = (RelativeLayout) qd_ll.getChildAt(0);
            TextView tv_d = (TextView) qd_rl.getChildAt(0);
            tv_d.setText("Day " + i + ":If not given, state reason?");
            RadioGroup qd_rg = (RadioGroup) qd_ll.getChildAt(1);
            EditText et1 = (EditText) qd_rg.getChildAt(5);
            EditText et2 = (EditText) qd_rg.getChildAt(7);*/
            cardBinding.qtxtWfb1081d.setText(String.format(Locale.ENGLISH, "Day %d :If not given, state reason?", i));

            /*qd_rg.setOnCheckedChangeListener((group, id) -> {

                if (id == 2131232219) {
                    et1.setVisibility(View.VISIBLE);
                    Clear.clearAllFields(et2);
                    et2.setVisibility(View.GONE);
                } else if (id == 2131232221) {
                    et2.setVisibility(View.VISIBLE);
                    Clear.clearAllFields(et1);
                    et1.setVisibility(View.GONE);
                } else {
                    Clear.clearAllFields(et1);
                    Clear.clearAllFields(et2);
                    et1.setVisibility(View.GONE);
                    et2.setVisibility(View.GONE);
                }
            });*/


            ll.addView(cardBinding.getRoot());

        }

    }


    private void setupSkips() {

        bi.wfb101.setOnCheckedChangeListener((radioGroup, i) -> {
            Clear.clearAllFields(bi.fldGrpCVwfb102);
        });

        /*bi.wfb1081a.setOnCheckedChangeListener((radioGroup, i) -> {
            Clear.clearAllFields(bi.llgrpsecb01);
            Clear.clearAllFields(bi.fldGrpCVwfb1081d);
            bi.llgrpsecb01.setVisibility(View.GONE);
            bi.fldGrpCVwfb1081d.setVisibility(View.GONE);
            if (i == bi.wfb1081a01.getId()) {
                bi.llgrpsecb01.setVisibility(View.VISIBLE);
            } else if (i == bi.wfb1081a02.getId()) {
                bi.fldGrpCVwfb1081d.setVisibility(View.VISIBLE);
            }
        });
        bi.wfb1081b.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpCVwfb1081c));*/

    }

    public void BtnContinue() {
        if (!formValidation()) return;
        SaveDraft();
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, SectionWFB02Activity.class).putExtra("week", week).putExtra("col_id", col_id).putExtra("wfa106", wfa106).putExtra("FD", FD).putExtra("delivery_date", delivery_date));
        }
    }

    public void BtnEnd() {
        oF = new Intent(this, MainActivity.class);
        startActivity(oF);
    }

    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormsWFColumn(FormsWFContract.FormsWFTable.COLUMN_SWFB01, MainApp.formsWF.sWFB01toString());
        if (updcount == 1) {

            for (byte i = 0; i < wfb108.size(); i++) {

                for (byte j = 0; j < wfb108.get(i).getSubmodel2().size(); j++) {
                    int day = j + 1;
                    long rowValue = db.insertWFB108(wfb108.get(i).getSubmodel2().get(j), day);
                    if (rowValue < 0) {
                        Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_LONG).show();
                        return false;
                    }
                }

            }
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void SaveDraft() {

        MainApp.formsWF.setWfb101(bi.wfb10101.isChecked() ? "1"
                : bi.wfb10102.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfb102(bi.wfb10201.isChecked() ? "1"
                : bi.wfb10202.isChecked() ? "2"
                : bi.wfb10203.isChecked() ? "3"
                : bi.wfb10204.isChecked() ? "4"
                : bi.wfb10296.isChecked() ? "96"
                : "-1");
        MainApp.formsWF.setWfb10296x(bi.wfb10296x.getText().toString().trim().isEmpty() ? "-1" : bi.wfb10296x.getText().toString());

        MainApp.formsWF.setWfb103(bi.wfb103.getText().toString().trim().isEmpty() ? "-1" : bi.wfb103.getText().toString());

        MainApp.formsWF.setWfb104(bi.wfb104.getText().toString().trim().isEmpty() ? "-1" : bi.wfb104.getText().toString());

        MainApp.formsWF.setWfb105(bi.wfb105.getText().toString().trim().isEmpty() ? "-1" : bi.wfb105.getText().toString());

        //    MainApp.formsWF.setWfi06(bi.wfi06.getText().toString().trim().isEmpty() ? "-1" : bi.wfa504.getText().toString());

        MainApp.formsWF.setWfi0601(bi.wfi0601.getText().toString().trim().isEmpty() ? "-1" : bi.wfi0601.getText().toString());
        MainApp.formsWF.setwfi0602(bi.wfi0602.getText().toString().trim().isEmpty() ? "-1" : bi.wfi0602.getText().toString());

        MainApp.formsWF.setWfi0701(bi.wfi0701.isChecked() ? "1" : "-1");
        MainApp.formsWF.setWfi0702(bi.wfi0702.isChecked() ? "2" : "-1");
        MainApp.formsWF.setWfi0796(bi.wfi0796.isChecked() ? "96" : "-1");
        MainApp.formsWF.setWfi0796x(bi.wfi0796x.getText().toString().trim().isEmpty() ? "-1" : bi.wfi0796x.getText().toString());


        wfb108 = new ArrayList<>();
        int wfb108INT = bi.llwfb108.getChildCount();
        String wfb108a, wfb108b, wfb108c, wfb108d;

        if (wfb108INT > 0) {
            ArrayList<WFB108> WFBsubModels = new ArrayList<>();
            for (int i = 0; i < wfb108INT; i++) {
                Wfb108CardBinding bind = DataBindingUtil.bind(bi.llwfb108.getChildAt(i));
                if (bind != null) {

                    if (bind.wfb1081a01.isChecked()) {
                        wfb108a = "1";
                    } else if (bind.wfb1081a02.isChecked()) {
                        wfb108a = "2";
                    } else {
                        wfb108a = "-1";
                    }

                    if (bind.wfb1081b01.isChecked()) {
                        wfb108b = "1";
                    } else if (bind.wfb1081b02.isChecked()) {
                        wfb108b = "2";
                    } else {
                        wfb108b = "-1";
                    }

                    if (bind.wfb1081c01.isChecked()) {
                        wfb108c = "1";
                    } else if (bind.wfb1081c02.isChecked()) {
                        wfb108c = "2";
                    } else if (bind.wfb1081c03.isChecked()) {
                        wfb108c = "3";
                    } else {
                        wfb108c = "-1";
                    }

                    if (bind.wfb1081d01.isChecked()) {
                        wfb108d = "1";
                    } else if (bind.wfb1081d02.isChecked()) {
                        wfb108d = "2";
                    } else if (bind.wfb1081d03.isChecked()) {
                        wfb108d = "3";
                    } else if (bind.wfb1081d04.isChecked()) {
                        wfb108d = "4";
                    } else if (bind.wfb1081d05.isChecked()) {
                        wfb108d = "5";
                    } else if (bind.wfb1081d96.isChecked()) {
                        wfb108d = "96";
                    } else {
                        wfb108d = "-1";
                    }

                    WFBsubModels.add(
                            new WFB108(
                                    MainApp.formsWF.getSysdate(),
                                    wfb108a.trim().isEmpty() ? "-1" : wfb108a.trim(),
                                    wfb108b.trim().isEmpty() ? "-1" : wfb108b.trim(),
                                    wfb108c.trim().isEmpty() ? "-1" : wfb108c.trim(),
                                    wfb108d.trim().isEmpty() ? "-1" : wfb108d.trim(),
                                    bind.wfb1081d05x.getText().toString().trim().isEmpty() ? "-1" : bind.wfb1081d05x.getText().toString(),
                                    bind.wfb1081d96x.getText().toString().trim().isEmpty() ? "-1" : bind.wfb1081d96x.getText().toString()
                            )
                    );
                }

                wfb108.add(new WFBSubModel("wfb108", WFBsubModels));
            }
        }

        /*MainApp.formsWF.setWfb1081a(bi.wfb1081a01.isChecked() ? "1"
                : bi.wfb1081a02.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfb1081b(bi.wfb1081b01.isChecked() ? "1"
                : bi.wfb1081b02.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfb1081c(bi.wfb1081c01.isChecked() ? "1"
                : bi.wfb1081c02.isChecked() ? "2"
                : bi.wfb1081c03.isChecked() ? "3"
                : "-1");

        MainApp.formsWF.setWfb1081d(bi.wfb1081d01.isChecked() ? "1"
                : bi.wfb1081d02.isChecked() ? "2"
                : bi.wfb1081d03.isChecked() ? "3"
                : bi.wfb1081d04.isChecked() ? "4"
                : bi.wfb1081d05.isChecked() ? "5"
                : bi.wfb1081d96.isChecked() ? "96"
                : "-1");
        MainApp.formsWF.setWfb1081d05x(bi.wfb1081d05x.getText().toString().trim().isEmpty() ? "-1" : bi.wfb1081d05x.getText().toString());
        MainApp.formsWF.setWfb1081d96x(bi.wfb1081d96x.getText().toString().trim().isEmpty() ? "-1" : bi.wfb1081d96x.getText().toString());*/
    }

    private boolean formValidation() {

        if (!checkWFB108(bi.llwfb108)) {
            Toast.makeText(this, "Please provide value for WFB108D", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!Validator.emptyCheckingContainer(this, bi.GrpName)) {
            return false;
        }

        return true;
    }

    public boolean checkWFB108(LinearLayout ll) {

        ConstraintLayout cl_WFB108 = (ConstraintLayout) ll.getChildAt(0);
        CardView cv_WFB108 = (CardView) cl_WFB108.getChildAt(0);
        LinearLayout lli_WFB108 = (LinearLayout) cv_WFB108.getChildAt(0);
        CardView cvi_WFB108 = (CardView) lli_WFB108.getChildAt(2);
        LinearLayout ll_inner_inner = (LinearLayout) cvi_WFB108.getChildAt(0);
        RadioGroup rg = (RadioGroup) ll_inner_inner.getChildAt(1);
        RadioButton rb1 = (RadioButton) rg.getChildAt(4);
        RadioButton rb2 = (RadioButton) rg.getChildAt(6);
        EditText ed1 = (EditText) rg.getChildAt(5);
        EditText ed2 = (EditText) rg.getChildAt(7);

        if (rb1.isChecked() && ed1.getText().toString().trim().isEmpty()) {
            return false;
        } else if (rb2.isChecked() && ed2.getText().toString().trim().isEmpty()) {
            return false;
        }

        return true;
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}