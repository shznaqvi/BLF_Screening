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

public class FetchFollowupWorker extends Worker {

    private static final Object APP_NAME = PROJECT_NAME;
    private final String TAG = "FetchChildMRWorker()";
    HttpURLConnection urlConnection;
    JSONObject json;
    String nTitle;
    String nTask;
    private Context mContext;
    private URL serverURL = null;
    private ProgressDialog pd;
    private int length;
    private Data data;

    public FetchFollowupWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        try {
            this.json = new JSONObject(workerParams.getInputData().getString("json"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.nTitle = workerParams.getInputData().getString("title");

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
        displayNotification(nTitle, "Starting Sync");

        StringBuilder result = new StringBuilder();

        URL url = null;
        try {
            Log.d(TAG, "doInBackground: Trying...");
            if (serverURL == null) {
                url = new URL(MainApp._HOST_URL + "fetchmr.php");
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
            displayNotification(nTitle, "Connecting to Server...");


            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());

            //================
            Log.d(TAG, "doWork: URL: " + url.toString());
            Log.d(TAG, "doWork: JSON: " + json);

            wr.writeBytes(String.valueOf(json));
            wr.flush();
            wr.close();
            Log.d(TAG, "doInBackground: " + urlConnection.getResponseCode());
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                displayNotification(nTitle, "Connection Established");

                length = urlConnection.getContentLength();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {

                    result.append(line);
                    displayNotification(nTitle, line);

                }
            } else {
                displayNotification(nTitle, "Connection Failed, Status: " + urlConnection.getResponseCode());

            }
        } catch (java.net.SocketTimeoutException e) {
            Log.d(TAG, "doWork (Timeout): " + e.getMessage());
            displayNotification(nTitle, "Timeout Error: " + e.getMessage());
            data = new Data.Builder()
                    .putString("error", String.valueOf(e.getMessage())).build();
            return Result.failure(data);

        } catch (IOException e) {
            Log.d(TAG, "doWork (IO Error): " + e.getMessage());
            displayNotification(nTitle, "IO Error: " + e.getMessage());
            data = new Data.Builder()
                    .putString("error", String.valueOf(e.getMessage())).build();

            return Result.failure(data);

        } finally {
//            urlConnection.disconnect();
        }

        Log.d(TAG, "onPostExecute: Starting");
        displayNotification(nTitle, "Received Data");
        //Do something with the JSON string
        Data data = null;
        if (result != null) {
            displayNotification(nTitle, "Starting Data Processing");

            //String json = result.toString();
            /*if (json.length() > 0) {*/
            displayNotification(nTitle, "Data Size: " + result.length());

            // JSONArray jsonArray = new JSONArray(json);

            //JSONObject jsonObjectCC = jsonArray.getJSONObject(0);
            data = new Data.Builder()
                    .putString("data", String.valueOf(result)).build();
            displayNotification(nTitle, " Data received successfully");
            Log.d(TAG, "doWork: data rec: " + result);
            return Result.success(data);

        } else {
            displayNotification(nTitle, "No Data received");
            Log.d(TAG, "doWork Fail: ");
            data = new Data.Builder()
                    .putString("error", "error").build();
            return Result.failure(data);

        }


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