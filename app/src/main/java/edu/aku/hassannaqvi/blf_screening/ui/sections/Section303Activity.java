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

import com.validatorcrawler.aliazaz.Validator;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySection303Binding;
import edu.aku.hassannaqvi.blf_screening.ui.other.MainActivity;

public class Section303Activity extends AppCompatActivity {

    ActivitySection303Binding bi;
    Intent oF = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section303);
        bi.setCallback(this);
        setupSkips();
    }

    private void setupSkips() {

        /*bi.ah51.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.ah51e.getId()) {
                Clear.clearAllFields(bi.fldGrpAH52);
            }
        });*/

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
                startActivity(new Intent(this, MainActivity.class));
            }
        }
    }


    public void BtnEnd() {
        oF = new Intent(this, MainActivity.class);
        startActivity(oF);
    }


    private boolean UpdateDB() {
        /*DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesAdolsColumn(AdolscentContract.SingleAdolscent.COLUMN_SAH3, MainApp.adolscent.getsAH3());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }*/
        return true;
    }


    private void SaveDraft() {

       /* form.setS3q1( bi.s3q101.isChecked() ? "1"
                : bi.s3q102.isChecked() ? "2"
                : bi.s3q199.isChecked() ? "99"
                :  "-1");

        form.setS3q2( bi.s3q201.isChecked() ? "1"
                : bi.s3q202.isChecked() ? "2"
                : bi.s3q203.isChecked() ? "3"
                : bi.s3q204.isChecked() ? "4"
                : bi.s3q205.isChecked() ? "5"
                : bi.s3q206.isChecked() ? "6"
                : bi.s3q2096.isChecked() ? "96"
                :  "-1");
        form.setS3q2096x(bi.s3q2096x.getText().toString());

        form.setS3q3( bi.s3q301.isChecked() ? "1"
                : bi.s3q302.isChecked() ? "2"
                : bi.s3q399.isChecked() ? "99"
                :  "-1");

        form.setS3q401(bi.s3q401.isChecked() ? "1" : "-1");
        form.setS3q402(bi.s3q402.isChecked() ? "2" : "-1");
        form.setS3q403(bi.s3q403.isChecked() ? "3" : "-1");
        form.setS3q496(bi.s3q496.isChecked() ? "96" : "-1");
        form.setS3q496x(bi.s3q496x.getText().toString());

        form.setS3q5( bi.s3q501.isChecked() ? "1"
                : bi.s3q502.isChecked() ? "2"
                : bi.s3q503.isChecked() ? "3"
                : bi.s3q504.isChecked() ? "4"
                : bi.s3q505.isChecked() ? "5"
                : bi.s3q596.isChecked() ? "96"
                :  "-1");
        form.setS3q596x(bi.s3q596x.getText().toString());

        form.setS3q601(bi.s3q601.isChecked() ? "1" : "-1");
        form.setS3q602(bi.s3q602.isChecked() ? "2" : "-1");
        form.setS3q603(bi.s3q603.isChecked() ? "3" : "-1");
        form.setS3q604(bi.s3q604.isChecked() ? "4" : "-1");
        form.setS3q605(bi.s3q605.isChecked() ? "5" : "-1");
        form.setS3q696(bi.s3q696.isChecked() ? "96" : "-1");
        form.setS3q696x(bi.s3q696x.getText().toString());

        form.setS3q7( bi.s3q701.isChecked() ? "1"
                : bi.s3q702.isChecked() ? "2"
                :  "-1");

        form.setS3q801(bi.s3q801.isChecked() ? "1" : "-1");
        form.setS3q802(bi.s3q802.isChecked() ? "2" : "-1");
        form.setS3q803(bi.s3q803.isChecked() ? "3" : "-1");
        form.setS3q896(bi.s3q896.isChecked() ? "96" : "-1");
        form.setS3q896x(bi.s3q896x.getText().toString());

        form.setS3q9( bi.s3q901.isChecked() ? "1"
                : bi.s3q902.isChecked() ? "2"
                : bi.s3q999.isChecked() ? "99"
                :  "-1");*/

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