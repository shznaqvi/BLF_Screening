package edu.aku.hassannaqvi.blf_screening.workers;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.core.MainApp;

import static edu.aku.hassannaqvi.blf_screening.utils.CreateTable.PROJECT_NAME;

public class DataUpWorkerSF extends Worker {

    private static final Object APP_NAME = PROJECT_NAME;
    private final String TAG = "DataDlWorkerSF()";
    HttpURLConnection urlConnection;
    private Context mContext;
    private URL serverURL = null;
    private ProgressDialog pd;
    private int length;

    public DataUpWorkerSF(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    /*
     * This method is responsible for doing the work
     * so whatever work that is needed to be performed
     * we will put it here
     *
     * For example, here I am calling the method displayNotification()
     * It will display a notification
     * So that we will understand the work is executed
     * */

    @NonNull
    @Override
    public Result doWork() {

        Log.d(TAG, "doInBackground: Starting");
        displayNotification("Screening Log", "Starting Sync");

        StringBuilder result = new StringBuilder();

        URL url = null;
        try {
            Log.d(TAG, "doInBackground: Trying...");
            if (serverURL == null) {
                url = new URL("http://f38158/blf/api/formscr.php");
            } else {
                url = serverURL;
            }
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(100000 /* milliseconds */);
            urlConnection.setConnectTimeout(150000 /* milliseconds */);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("charset", "utf-8");
            urlConnection.setUseCaches(false);
            urlConnection.connect();
            JSONArray jsonSync = new JSONArray();

            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
            JSONObject json = new JSONObject();
            try {
                json.put("table", "formscr");
                Log.d(TAG, "json.put: Done");
            } catch (JSONException e1) {
                e1.printStackTrace();
                Log.d(TAG, e1.getMessage());
            }
            Log.d(TAG, "downloadUrl: " + json.toString());

            // ============
            JSONObject jsonTable = new JSONObject();
            JSONArray jsonParam = new JSONArray();
            try {
                jsonTable.put("table", "formscr");
                jsonSync.put(MainApp.formsSF.toJSONObject());
                jsonParam
                        .put(jsonTable)
                        .put(jsonSync);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //================


            wr.writeBytes(String.valueOf(jsonParam));
            wr.flush();
            wr.close();
            Log.d(TAG, "doInBackground: " + urlConnection.getResponseCode());
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                displayNotification("Form Screen", "Connection Established");

                length = urlConnection.getContentLength();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    Log.i(TAG, "SCR ID: " + line);
                    result.append(line);
                    displayNotification("SCR ID", line);

                }
            }
        } catch (java.net.SocketTimeoutException e) {
            Log.d(TAG, "doInBackground: " + e.getMessage());
            displayNotification("SCR ID", "Timeout Error: " + e.getMessage());
            return Result.failure();

        } catch (IOException e) {
            Log.d(TAG, "doInBackground: " + e.getMessage());
            displayNotification("SCR ID", "IO Error: " + e.getMessage());

            return Result.failure();

        } finally {
//            urlConnection.disconnect();
        }

        Log.d(TAG, "onPostExecute: Starting");
        displayNotification("SCR ID", "Received Data");
        //Do something with the JSON string
        Data data = null;
        if (result != null) {
            displayNotification("SCR ID", "Starting Data Processing");

            //String json = result.toString();
            /*if (json.length() > 0) {*/
            displayNotification("SCR ID", "Data Size: " + result.length());


            // JSONArray jsonArray = new JSONArray(json);


            //JSONObject jsonObjectCC = jsonArray.getJSONObject(0);
            data = new Data.Builder()
                    .putString("study_id", String.valueOf(result)).build();



           /* } else {

            }*/
        } else {

        }

        displayNotification("SCR ID", " SCR ID received successfully");
        return Result.success(data);
    }

    /*
     * The method is doing nothing but only generating
     * a simple notification
     * If you are confused about it
     * you should check the Android Notification Tutorial
     * */
    private void displayNotification(String title, String task) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("simplifiedcoding", "simplifiedcoding", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "simplifiedcoding")
                .setContentTitle(title)
                .setContentText(task)
                .setSmallIcon(R.mipmap.ic_launcher);

        final int maxProgress = 100;
        int curProgress = 0;
        notification.setProgress(length, curProgress, false);

        notificationManager.notify(1, notification.build());
    }
}