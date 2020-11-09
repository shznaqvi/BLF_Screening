package edu.aku.hassannaqvi.blf_screening.ui.sections;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsS3Contract;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySection301Binding;
import edu.aku.hassannaqvi.blf_screening.models.FormsS3;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;

import static edu.aku.hassannaqvi.blf_screening.core.MainApp.formsS3;

public class Section301Activity extends AppCompatActivity {

    ActivitySection301Binding bi;
    Intent oF = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section301);
        bi.setCallback(this);
        setupSkips();
    }

    private void setupSkips() {

        bi.s1q18.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.s1q1802.getId()) {
                Clear.clearAllFields(bi.llGrpsec31);
            }
        });

        bi.s1q20.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != bi.s1q2002.getId()) {
                Clear.clearAllFields(bi.fldGrpCVs1q21);
            }
        });

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
                startActivity(new Intent(this, Section302Activity.class));
            }
        }
    }


    public void BtnEnd() {
        oF = new Intent(this, MainActivity.class);
        startActivity(oF);
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addFormS3(formsS3);
        formsS3.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            formsS3.set_UID(formsS3.getDeviceID() + formsS3.get_ID());
            db.updatesFormsS3Column(FormsS3Contract.FormsS3Table.COLUMN_UID, formsS3.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }

    }


    private void SaveDraft() {

        formsS3 = new FormsS3();
        formsS3.setSysdate(new SimpleDateFormat("dd-MM-yy HH:mm", Locale.getDefault()).format(new Date().getTime()));
        formsS3.setDeviceID(MainApp.appInfo.getDeviceID());
        formsS3.setDevicetagID(MainApp.appInfo.getTagName());
        formsS3.setAppversion(MainApp.appInfo.getAppVersion());
        formsS3.setUsername(MainApp.userName);

        setGPS(this);

        formsS3.setS1q1(bi.s1q1.getText().toString().trim().isEmpty() ? "-1" : bi.s1q1.getText().toString());

        formsS3.setS1q2(bi.s1q2.getText().toString().trim().isEmpty() ? "-1" : bi.s1q2.getText().toString());

        formsS3.setS1q3(bi.s1q3.getText().toString().trim().isEmpty() ? "-1" : bi.s1q3.getText().toString());

        formsS3.setS1q4(bi.s1q401.isChecked() ? "1"
                : bi.s1q402.isChecked() ? "2"
                : "-1");

        formsS3.setS1q501(bi.s1q501.getText().toString().trim().isEmpty() ? "-1" : bi.s1q501.getText().toString());
        formsS3.setS1q502(bi.s1q502.getText().toString().trim().isEmpty() ? "-1" : bi.s1q502.getText().toString());

        formsS3.setS1q6(bi.s1q6.getText().toString().trim().isEmpty() ? "-1" : bi.s1q6.getText().toString());

        formsS3.setS1q7(bi.s1q7.getText().toString().trim().isEmpty() ? "-1" : bi.s1q7.getText().toString());

        formsS3.setS1q8(bi.s1q8.getText().toString().trim().isEmpty() ? "-1" : bi.s1q8.getText().toString());

        formsS3.setS1q9(bi.s1q9.getText().toString().trim().isEmpty() ? "-1" : bi.s1q9.getText().toString());

        formsS3.setS1q10(bi.s1q10.getText().toString().trim().isEmpty() ? "-1" : bi.s1q10.getText().toString());

        formsS3.setS1q11(bi.s1q11.getText().toString().trim().isEmpty() ? "-1" : bi.s1q11.getText().toString());

        formsS3.setS1q12(bi.s1q12.getText().toString().trim().isEmpty() ? "-1" : bi.s1q12.getText().toString());

        formsS3.setS1q13(bi.s1q13.getText().toString().trim().isEmpty() ? "-1" : bi.s1q13.getText().toString());

        formsS3.setS1q14(bi.s1q14.getText().toString().trim().isEmpty() ? "-1" : bi.s1q14.getText().toString());

        formsS3.setS1q15(bi.s1q15.getText().toString().trim().isEmpty() ? "-1" : bi.s1q15.getText().toString());

        formsS3.setS1q16(bi.s1q1601.isChecked() ? "1"
                : bi.s1q1602.isChecked() ? "2"
                : "-1");

        formsS3.setS1q17(bi.s1q1701.isChecked() ? "1"
                : bi.s1q1702.isChecked() ? "2"
                : "-1");

        formsS3.setS1q18(bi.s1q1801.isChecked() ? "1"
                : bi.s1q1802.isChecked() ? "2"
                : "-1");

        formsS3.setS1q1802x(bi.s1q1802x.getText().toString().trim().isEmpty() ? "-1" : bi.s1q1802x.getText().toString());

        formsS3.setS1q1901(bi.s1q1901.getText().toString().trim().isEmpty() ? "-1" : bi.s1q1901.getText().toString());
        formsS3.setS1q1902(bi.s1q1902.getText().toString().trim().isEmpty() ? "-1" : bi.s1q1902.getText().toString());

        formsS3.setS1q20(bi.s1q2001.isChecked() ? "1"
                : bi.s1q2002.isChecked() ? "2"
                : "-1");

        formsS3.setS1q21(bi.s1q2101.isChecked() ? "1"
                : bi.s1q2102.isChecked() ? "2"
                : bi.s1q2103.isChecked() ? "3"
                : bi.s1q2104.isChecked() ? "4"
                : "-1");

        formsS3.setS1q22(bi.s1q22.getText().toString().trim().isEmpty() ? "-1" : bi.s1q22.getText().toString());

        formsS3.setS1q2301(bi.s1q2301.getText().toString().trim().isEmpty() ? "-1" : bi.s1q2301.getText().toString());
        formsS3.setS1q2302(bi.s1q2302.getText().toString().trim().isEmpty() ? "-1" : bi.s1q2302.getText().toString());

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }


    private void setGPS(Activity activity) {
        SharedPreferences GPSPref = activity.getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);

        try {
            String lat = GPSPref.getString("Latitude", "0");
            String lang = GPSPref.getString("Longitude", "0");
            String acc = GPSPref.getString("Accuracy", "0");
            String dt = GPSPref.getString("Time", "0");

            if (lat.equals("0") && lang.equals("0")) {
                Toast.makeText(activity, "Could not obtained GPS points", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "GPS set", Toast.LENGTH_SHORT).show();
            }

            String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

            MainApp.formsSF.setGpsLat(GPSPref.getString("Latitude", "0"));
            MainApp.formsSF.setGpsLng(GPSPref.getString("Longitude", "0"));
            MainApp.formsSF.setGpsAcc(GPSPref.getString("Accuracy", "0"));
//            MainApp.fc.setGpsTime(GPSPref.getString(date, "0")); // Timestamp is converted to date above
            MainApp.formsSF.setGpsDT(date); // Timestamp is converted to date above

        } catch (Exception e) {
            Log.e("GPS", "setGPS: " + e.getMessage());
        }
    }

}