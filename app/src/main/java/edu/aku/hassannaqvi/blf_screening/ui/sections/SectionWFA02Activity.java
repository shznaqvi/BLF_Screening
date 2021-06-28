package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import java.util.Arrays;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsWFContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionWfa02Binding;
import edu.aku.hassannaqvi.blf_screening.utils.AppUtilsKt;

public class SectionWFA02Activity extends AppCompatActivity {

    ActivitySectionWfa02Binding bi;
    Intent oF = null;

    String week;
    String delivery_date;
    String fupdate;
    int col_id;
    int wfa106;
    String FD;
    String pFollowUpDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_wfa02);
        bi.setCallback(this);
        setupSkips();

        Intent intent = getIntent();
        week = intent.getStringExtra("week");
        delivery_date = intent.getStringExtra("delivery_date");
        fupdate = intent.getStringExtra("fupdate");
        col_id = intent.getIntExtra("col_id", 0);
        wfa106 = intent.getIntExtra("wfa106", 0);
        FD = intent.getStringExtra("FD");
        pFollowUpDate = intent.getStringExtra("pFollowUpDate");

        String[] weekarray = {"1", "2", "3", "4", "5", "6"};
        if (!Arrays.asList(weekarray).contains(week)) {
            startActivity(new Intent(this, SectionWFCActivity.class)
                    .putExtra("week", week).putExtra("col_id", col_id)
                    .putExtra("wfa106", wfa106).putExtra("FD", FD)
                    .putExtra("delivery_date", delivery_date));
        }
    }


    private void setupSkips() {

        /*bi.wfa201.setOnCheckedChangeListener((group, id) -> {
            Clear.clearAllFields(bi.fldGrpCVwfa202);
            bi.fldGrpCVwfa202.setVisibility(View.VISIBLE);
            if (id == bi.wfa20101.getId()) {
                bi.fldGrpCVwfa202.setVisibility(View.GONE);
            }
        });*/

        bi.wfa203.setOnCheckedChangeListener((group, id) -> {

            Clear.clearAllFields(bi.fldGrpCVwfa204);
            Clear.clearAllFields(bi.fldGrpCVwfa205);
            bi.fldGrpCVwfa204.setVisibility(View.VISIBLE);
            bi.fldGrpCVwfa205.setVisibility(View.VISIBLE);

            if (id == bi.wfa20302.getId()) {
                bi.fldGrpCVwfa204.setVisibility(View.GONE);
                bi.fldGrpCVwfa205.setVisibility(View.GONE);
            }

        });

        bi.wfa204.setOnCheckedChangeListener((group, id) -> {

            if (bi.wfa203.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "select a radio for wfa203 first", Toast.LENGTH_LONG).show();
            } else {

                if (bi.wfa20301.isChecked() && id == bi.wfa20401.getId()) {
                    Clear.clearAllFields(bi.fldGrpCVwfa205);
                    Clear.clearAllFields(bi.fldGrpCVwfa206);
                    bi.fldGrpCVwfa205.setVisibility(View.GONE);
                    bi.fldGrpCVwfa206.setVisibility(View.GONE);
                } else if (bi.wfa20301.isChecked() && id == bi.wfa20402.getId()) {
                    bi.fldGrpCVwfa205.setVisibility(View.VISIBLE);
                    bi.fldGrpCVwfa206.setVisibility(View.VISIBLE);
                } else if (bi.wfa20303.isChecked() && id == bi.wfa20401.getId()) {
                    Clear.clearAllFields(bi.fldGrpCVwfa205);
                    bi.fldGrpCVwfa205.setVisibility(View.GONE);
                    bi.fldGrpCVwfa206.setVisibility(View.VISIBLE);
                } else if (bi.wfa20303.isChecked() && id == bi.wfa20402.getId()) {
                    bi.fldGrpCVwfa205.setVisibility(View.VISIBLE);
                    bi.fldGrpCVwfa206.setVisibility(View.VISIBLE);
                }
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
            startActivity(new Intent(this, SectionWFA03Activity.class)
                    .putExtra("week", week)
                    .putExtra("delivery_date", delivery_date)
                    .putExtra("fupdate", fupdate)
                    .putExtra("col_id", col_id)
                    .putExtra("wfa106", wfa106)
                    .putExtra("FD", FD)
                    .putExtra("pFollowUpDate", pFollowUpDate));

            /*if (bi.wfa20302.isChecked()) {
                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", false).putExtra("form", "FP").putExtra("col_id", col_id).putExtra("wfa106", wfa106).putExtra("FD", FD).putExtra("pFollowUpDate", pFollowUpDate));
            } else {
                startActivity(new Intent(this, SectionWFA03Activity.class).putExtra("week", week).putExtra("delivery_date", delivery_date).putExtra("fupdate", fupdate).putExtra("col_id", col_id).putExtra("wfa106", wfa106).putExtra("FD", FD).putExtra("pFollowUpDate", pFollowUpDate));
            }*/
        }
    }


    public void BtnEnd() {
        String form = "FP";
        AppUtilsKt.openEndActivity(this, form);
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormsWFColumn(FormsWFContract.FormsWFTable.COLUMN_SWFA02, MainApp.formsWF.sWFA02toString());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }


    private void SaveDraft() {

        MainApp.formsWF.setWfa201(bi.wfa20101.isChecked() ? "1"
                : bi.wfa20102.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa20201(bi.wfa20201.isChecked() ? "1" : "-1");
        MainApp.formsWF.setWfa20202(bi.wfa20202.isChecked() ? "2" : "-1");
        MainApp.formsWF.setWfa20203(bi.wfa20203.isChecked() ? "3" : "-1");
        MainApp.formsWF.setWfa20204(bi.wfa20204.isChecked() ? "4" : "-1");
        MainApp.formsWF.setWfa20205(bi.wfa20205.isChecked() ? "5" : "-1");
        MainApp.formsWF.setWfa20206(bi.wfa20206.isChecked() ? "6" : "-1");
        MainApp.formsWF.setWfa20296m(bi.wfa20296m.isChecked() ? "96" : "-1");
        MainApp.formsWF.setWfa20296mx(bi.wfa20296mx.getText().toString().trim().isEmpty() ? "-1" : bi.wfa20296mx.getText().toString());
        MainApp.formsWF.setWfa20207(bi.wfa20207.isChecked() ? "7" : "-1");
        MainApp.formsWF.setWfa20208(bi.wfa20208.isChecked() ? "8" : "-1");
        MainApp.formsWF.setWfa20296b(bi.wfa20296b.isChecked() ? "96" : "-1");
        MainApp.formsWF.setWfa20296bx(bi.wfa20296bx.getText().toString().trim().isEmpty() ? "-1" : bi.wfa20296bx.getText().toString());

        MainApp.formsWF.setWfa203(bi.wfa20301.isChecked() ? "1"
                : bi.wfa20302.isChecked() ? "2"
                : bi.wfa20303.isChecked() ? "3"
                : "-1");

        MainApp.formsWF.setWfa204(bi.wfa20401.isChecked() ? "1"
                : bi.wfa20402.isChecked() ? "2"
                : "-1");

        MainApp.formsWF.setWfa205(bi.wfa20501.isChecked() ? "1"
                : bi.wfa20502.isChecked() ? "2"
                : bi.wfa20503.isChecked() ? "3"
                : bi.wfa20596.isChecked() ? "96"
                : "-1");
        MainApp.formsWF.setWfa20596x(bi.wfa20596x.getText().toString().trim().isEmpty() ? "-1" : bi.wfa20596x.getText().toString());

        MainApp.formsWF.setWfa20601(bi.wfa20601.isChecked() ? "1" : "-1");
        MainApp.formsWF.setWfa20602(bi.wfa20602.isChecked() ? "2" : "-1");
        MainApp.formsWF.setWfa20603(bi.wfa20603.isChecked() ? "3" : "-1");
        MainApp.formsWF.setWfa20604(bi.wfa20604.isChecked() ? "4" : "-1");
        MainApp.formsWF.setWfa20605(bi.wfa20605.isChecked() ? "5" : "-1");
        MainApp.formsWF.setWfa20606(bi.wfa20606.isChecked() ? "6" : "-1");
        MainApp.formsWF.setWfa20607(bi.wfa20607.isChecked() ? "7" : "-1");
        MainApp.formsWF.setWfa20608(bi.wfa20608.isChecked() ? "8" : "-1");
        MainApp.formsWF.setWfa20609(bi.wfa20609.isChecked() ? "9" : "-1");
        MainApp.formsWF.setWfa20610(bi.wfa20610.isChecked() ? "10" : "-1");
        MainApp.formsWF.setWfa20696(bi.wfa20696.isChecked() ? "96" : "-1");
        MainApp.formsWF.setWfa20696x(bi.wfa20696x.getText().toString().trim().isEmpty() ? "-1" : bi.wfa20696x.getText().toString());

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}