package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import java.util.Arrays;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsWFContract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionWfeBinding;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;

public class SectionWFEActivity extends AppCompatActivity {

    ActivitySectionWfeBinding bi;
    Intent oF = null;

    String week, delivery_date;
    int col_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_wfe);
        bi.setCallback(this);
        setupSkips();


        Intent intent = getIntent();
        week = intent.getStringExtra("week");
        delivery_date = intent.getStringExtra("delivery_date");
        col_id = intent.getIntExtra("col_id", 0);

        String[] weekarray = {"6", "10", "18"};
        if (!Arrays.asList(weekarray).contains(week)) {
            startActivity(new Intent(this, SectionWFFActivity.class).putExtra("week", week).putExtra("col_id", col_id));
        }
        EditText BLEditText = bi.wfe102;
        SimpleMaskFormatter f = new SimpleMaskFormatter("LL-NN-NNNN-LLL");
        MaskTextWatcher mtw = new MaskTextWatcher(BLEditText, f);
        BLEditText.addTextChangedListener(mtw);
        BLEditText.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
    }

    private void setupSkips() {
        bi.wfe101.setOnCheckedChangeListener((group, i) -> Clear.clearAllFields(bi.fldGrpCVwfe102));
    }


    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, SectionWFFActivity.class).putExtra("week", week).putExtra("col_id", col_id));
            }
        }
    }


    public void BtnEnd() {
        oF = new Intent(this, MainActivity.class);
        startActivity(oF);
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormsWFColumn(FormsWFContract.FormsWFTable.COLUMN_SWFE, MainApp.formsWF.sWFEtoString());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }


    private void SaveDraft() {

        MainApp.formsWF.setWfe101(bi.wfe10101.isChecked() ? "1"
                : bi.wfe10102.isChecked() ? "2"
                : "-1");
        MainApp.formsWF.setWfe10102x(bi.wfe10102x.getText().toString().trim().isEmpty() ? "-1" : bi.wfe10102x.getText().toString());

        MainApp.formsWF.setWfe102(bi.wfe102.getText().toString().trim().isEmpty() ? "-1" : bi.wfe102.getText().toString());

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

    // START QR-CODE

    public void QRCode(View view) {

        // INTENT TO START QR-CODE CAMERA
        new IntentIntegrator(this).initiateScan(); // `this` is the current Activity
    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {


                // TODO: SET CODE TO EDIT TEXT FIELD
                bi.wfe102.setText(result.getContents());


                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    // END QR-CODE


}