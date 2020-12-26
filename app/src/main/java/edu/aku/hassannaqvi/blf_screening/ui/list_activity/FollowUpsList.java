package edu.aku.hassannaqvi.blf_screening.ui.list_activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.adapter.FollowupsAdapter;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.databinding.ActivityFollowupsListBinding;
import edu.aku.hassannaqvi.blf_screening.models.FollowUps;
import edu.aku.hassannaqvi.blf_screening.workers.DataDownWorkerALL;


public class FollowUpsList extends AppCompatActivity {

    ActivityFollowupsListBinding bi;
    DatabaseHelper db;
    Collection<FollowUps> fups;
    String sysdateToday = new SimpleDateFormat("dd-MM-yy").format(new Date());
    TextView dtFilter;
    private RecyclerView recyclerView;
    private Collection<FollowUps> allFollowUps;
    private RecyclerView.Adapter fupsAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followups_list);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_followups_list);
        bi.setCallback(this);
        recyclerView = bi.fcRecyclerView;

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        dtFilter = bi.dtFilter;
/*          db = new DatabaseHelper(this);
        form = db.getFormsByCluster("0000000"); */
        getFollowups(null);

        // specify an adapter (see also next example)
        //fupsAdapter = new FollowupsAdapter((List<FollowUps>) fups, this);
        //recyclerView.setAdapter(fupsAdapter);
        bi.swipeContainer.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorAccent_overlay,
                R.color.colorPrimaryLight);
        bi.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bi.swipeContainer.setRefreshing(true);
                filterForms(bi.btnsearch);
                bi.swipeContainer.setRefreshing(false);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 2 && resultCode == 1) {

            filterForms(bi.btnsearch);
            Toast.makeText(this, "Updated!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getFollowups(String filter) {
        Animation bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_out_animation);
        bi.fupFor.startAnimation(bounceAnimation);
        bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_animation);
        bi.fupFor.setText(bi.dtFilter.getText().toString().equals("") ? "" : " [ For: " + bi.dtFilter.getText().toString() + " ]");
        bi.fupFor.startAnimation(bounceAnimation);


        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation);

        recyclerView.setLayoutAnimation(controller);
//        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();


        bi.wmError.setError(null);
        bi.pBar3.setVisibility(View.VISIBLE);
        String where = filter == null ? "" : " mrno='" + filter + "' ";
        JSONObject json = new JSONObject();
        try {
            json.put("table", "childFollowup");
            //json.put("select", "sl2, sl4, sl5, sf6a");
            //json.put("filter", "sf5 = '" + bi.wfa101.getText().toString() + "'");
            //json.put("scrdt", MainApp.scrdt);
        } catch (JSONException e1) {
            e1.printStackTrace();
            //Log.d(TAG, "doWork: " + e1.getMessage());
        }

        Data data = new Data.Builder()
                .putString("table", "childFollowup")
                //.putString("columns", "_id, sysdate")
                .putString("where", where)
                .build();

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();


        final OneTimeWorkRequest workRequest1 = new OneTimeWorkRequest.Builder(DataDownWorkerALL.class).setInputData(data).setConstraints(constraints).build();
        WorkManager.getInstance().enqueue(workRequest1);


        WorkManager.getInstance().getWorkInfoByIdLiveData(workRequest1.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workInfo) {


                        String progress = workInfo.getProgress().getString("progress");
                        bi.wmError.setText("Progress: " + progress);


                        if (workInfo.getState() != null &&
                                workInfo.getState() == WorkInfo.State.SUCCEEDED) {

                            allFollowUps = new ArrayList<FollowUps>();

                            //Displaying the status into TextView
                            //mTextView1.append("\n" + workInfo.getState().name());
                            bi.wmError.setVisibility(View.GONE);
                            bi.pBar3.setVisibility(View.GONE);


                            String message = workInfo.getOutputData().getString("data");
                            DatabaseHelper db = new DatabaseHelper(FollowUpsList.this); // Database Helper
                            StringBuilder sSyncedError = new StringBuilder();
                            JSONObject jsonObject;
                            try {

                                JSONArray json = new JSONArray(message);
                                for (int i = 0; i < json.length(); i++) {
                                    FollowUps followUps = new FollowUps();

                                    allFollowUps.add(followUps.Hydrate(new JSONObject(json.getString(i))));

                                }
                            } catch (JSONException e) {
                                bi.wmError.setText("JSON Error: " + message);
                                bi.wmError.setVisibility(View.VISIBLE);
                                Log.d("JSON Error", "onChanged: " + message);
                                e.printStackTrace();


                            }
                            fupsAdapter = new FollowupsAdapter((List<FollowUps>) allFollowUps, FollowUpsList.this);
                            recyclerView.setAdapter(fupsAdapter);
                            //bi.sl2.setText(message);
                        }
                        //mTextView1.append("\n" + workInfo.getState().name());
                        if (workInfo.getState() != null &&
                                workInfo.getState() == WorkInfo.State.FAILED) {
                            bi.pBar3.setVisibility(View.GONE);
                            String message = workInfo.getOutputData().getString("error");
                            bi.wmError.setText(message);
                            bi.wmError.setVisibility(View.VISIBLE);

                        }
                    }
                });


    }

    public void filterForms(View view) {
        Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
        //followups = db.getFormsByCluster();
        int size = allFollowUps.size();
        allFollowUps.clear();
        fupsAdapter.notifyItemRangeRemoved(0, size);
        getFollowups(dtFilter.getText().toString().length() == 9 ? dtFilter.getText().toString() : null);

    }
}